package dev.hybridlabs.aquatic.entity.mammal

import dev.hybridlabs.aquatic.entity.ai.goal.HADolphinJumpGoal
import net.minecraft.entity.*
import net.minecraft.entity.ai.control.SmoothSwimmingMoveControl
import net.minecraft.entity.ai.control.SmoothSwimmingLookControl
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.ai.pathing.BlockPathTypes
import net.minecraft.entity.ai.pathing.WaterBoundPathNavigation
import net.minecraft.entity.attribute.Attributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.SynchedEntityData
import net.minecraft.entity.data.EntityDataAccessor
import net.minecraft.entity.data.EntityDataSerializers
import net.minecraft.entity.mob.WaterAnimal
import net.minecraft.entity.player.Player
import net.minecraft.item.ItemStack
import net.minecraft.nbt.CompoundTag
import net.minecraft.particle.ParticleTypes
import net.minecraft.registry.tag.TagKey
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Mth
import net.minecraft.util.math.Vec3d
import net.minecraft.util.math.random.Random
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.ServerLevelAccess
import net.minecraft.world.World
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.util.GeckoLibUtil

@Suppress("LeakingThis", "DEPRECATION", "UNUSED_PARAMETER", "unused")
open class HybridAquaticDolphinEntity(
    type: EntityType<out HybridAquaticDolphinEntity>,
    world: Level,
    open val prey: List<TagKey<EntityType<*>>>,
    open val predator: List<TagKey<EntityType<*>>>,
) : WaterAnimal(type, world), GeoEntity {

    private val factory = GeckoLibUtil.createInstanceCache(this)

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?
    ): SpawnGroupData? {
        this.airSupply= this.maxAir
        this.pitch = 0.0f
        this.size = this.random.nextIntBetweenInclusive(getMinSize(), getMaxSize())
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
    }

    protected open fun getMinSize(): Int {
        return 0
    }

    protected open fun getMaxSize(): Int {
        return 0
    }

    //#region Animations
    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            AnimationController(this, "Swim/Idle", 4
            ) { state: AnimationState<HybridAquaticDolphinEntity> ->
                when {
                    state.isMoving -> state.setAndContinue(DefaultAnimations.SWIM)
                    else -> state.setAndContinue(DefaultAnimations.SWIM)
                }
            }
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    override fun canBreatheUnderwater(): Boolean {
        return false
    }

    override fun handleAirSupply(air: Int) {
    }

    private var moistness: Int
        get() = entityData.get(MOISTNESS)
        set(moistness) {
            entityData.set(MOISTNESS, moistness)
        }

    var size: Int
        get() = entityData.get(DOLPHIN_SIZE)
        set(size) {
            entityData.set(DOLPHIN_SIZE, size)
        }

    override fun initSynchedEntityData() {
        super.initSynchedEntityData()
        entityData.define(MOISTNESS, getMaxMoistness())
        entityData.define(DOLPHIN_SIZE, 0)
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        super.addAdditionalSaveData(nbt)
        nbt.putInt("Moistness", this.moistness)
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        this.moistness = nbt.getInt("Moistness")
    }

    init {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        setPathfindingMalus(BlockPathTypes.WATER_BORDER, -1.0f)
        setPathfindingMalus(BlockPathTypes.WALKABLE, -1.0f)
        setCanPickUpLoot(true)
        moveControl = SmoothSwimmingMoveControl(this, 85, 5, 0.02f, 0.1f, true)
        lookControl = SmoothSwimmingLookControl(this, 15)
        navigation = WaterBoundPathNavigation(this, world)
    }

    override fun registerGoals() {
        goalSelector.addGoal(0, BreatheAirGoal(this))
        goalSelector.addGoal(0, TryFindWaterGoal(this))
        goalSelector.addGoal(4, RandomSwimmingGoal(this, 1.0, 2))
        goalSelector.addGoal(4,RandomRandomLookAroundGoal(this))
        goalSelector.addGoal(5, LookAtPlayerGoal(this,Player::class.java, 6.0f))
        goalSelector.addGoal(5, HADolphinJumpGoal(this, 10))
        goalSelector.addGoal(6, MeleeAttackGoal(this, 1.2000000476837158, true))
        goalSelector.addGoal(8, ChaseBoatGoal(this))
    }

    override fun doHurtTarget(target: Entity): Boolean {
        val bl = target.damage(
            this.damageSources.mobAttack(this),
            this.getAttributeValue(Attributes.ATTACK_DAMAGE).toInt().toFloat()
        )
        if (bl) {
            this.applyDamageEffects(this, target)
            this.playSound(SoundEvents._DOLPHIN_ATTACK, 1.0f, 1.0f)
        }

        return bl
    }

    override fun getMaxAir(): Int {
        return 4800
    }

    override fun getNextAirOnLand(air: Int): Int {
        return this.maxAir
    }

    override fun getStandingEyeHeight(pose: Pose, dimensions: EntityDimensions): Float {
        return 0.3f
    }

    override fun getMaxLookPitchChange(): Int {
        return 1
    }

    override fun getMaxHeadRotation(): Int {
        return 1
    }

    override fun canStartRiding(entity: Entity): Boolean {
        return true
    }

    override fun canEquip(stack: ItemStack): Boolean {
        val equipmentSlot = getPreferredEquipmentSlot(stack)
        return if (!getEquippedStack(equipmentSlot).isEmpty) {
            false
        } else {
            equipmentSlot == EquipmentSlot.MAINHAND && super.canEquip(stack)
        }
    }

    override fun loot(item: ItemEntity) {
        if (getEquippedStack(EquipmentSlot.MAINHAND).isEmpty) {
            val itemStack = item.stack
            if (this.canPickupItem(itemStack)) {
                this.triggerItemPickedUpByEntityCriteria(item)
                this.equipStack(EquipmentSlot.MAINHAND, itemStack)
                this.updateDropChances(EquipmentSlot.MAINHAND)
                this.sendPickup(item, itemStack.count)
                item.discard()
            }
        }
    }

    override fun tick() {
        super.tick()
        if (this.isNoAi) {
            this.airSupply= this.maxAir
        } else {
            if (this.isInWaterRainOrBubble) {
                this.moistness = 2400
            } else {
                this.moistness -= 1
                if (this.moistness <= 0) {
                    this.damage(this.damageSources.dryOut(), 1.0f)
                }

                if (this.isOnGround) {
                    this.velocity = velocity.add(
                        ((random.nextFloat() * 2.0f - 1.0f) * 0.2f).toDouble(),
                        0.5,
                        ((random.nextFloat() * 2.0f - 1.0f) * 0.2f).toDouble()
                    )
                    this.yaw = random.nextFloat() * 360.0f
                    this.isOnGround = false
                    this.velocityDirty = true
                }
            }

            if (world.isClientSide && this.isTouchingWater && (velocity.lengthSquared() > 0.03)) {
                val vec3d = this.getRotationVec(0.0f)
                val f = Mth.cos(this.yaw * 0.017453292f) * 0.3f
                val g = Mth.sin(this.yaw * 0.017453292f) * 0.3f
                val h = 1.2f - random.nextFloat() * 0.7f

                for (i in 0..1) {
                    world.addParticle(
                        ParticleTypes.DOLPHIN,
                        this.x - vec3d.x * h.toDouble() + f.toDouble(),
                        this.y - vec3d.y,
                        this.z - vec3d.z * h.toDouble() + g.toDouble(), 0.0, 0.0, 0.0
                    )
                    world.addParticle(
                        ParticleTypes.DOLPHIN,
                        this.x - (vec3d.x * h.toDouble()) - (f.toDouble()),
                        this.y - vec3d.y,
                        this.z - (vec3d.z * h.toDouble()) - (g.toDouble()), 0.0, 0.0, 0.0
                    )
                }
            }
        }
    }

    override fun getHurtSound(source: DamageSource): SoundEvent? {
        return SoundEvents._DOLPHIN_HURT
    }

    override fun getDeathSound(): SoundEvent? {
        return SoundEvents._DOLPHIN_DEATH
    }

    override fun getAmbientSound(): SoundEvent? {
        return if (this.isTouchingWater) SoundEvents._DOLPHIN_AMBIENT_WATER else SoundEvents._DOLPHIN_AMBIENT
    }

    override fun getSplashSound(): SoundEvent {
        return SoundEvents._DOLPHIN_SPLASH
    }

    override fun getSwimSound(): SoundEvent {
        return SoundEvents._DOLPHIN_SWIM
    }

    override fun travel(movementInput: Vec3d) {
        if (this.canMoveVoluntarily() && this.isTouchingWater) {
            this.updateVelocity(this.movementSpeed, movementInput)
            this.move(MovementType.SELF, this.velocity)
            this.velocity = velocity.multiply(0.9)
            if (this.target == null) {
                this.velocity = velocity.add(0.0, -0.005, 0.0)
            }
        } else {
            super.travel(movementInput)
        }
    }

    private fun getMaxMoistness(): Int {
        return 1800
    }

    companion object {
        const val MAX_AIR: Int = 4800
        val MOISTNESS: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticDolphinEntity::class.java, EntityDataSerializers.INTEGER)
        val DOLPHIN_SIZE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticDolphinEntity::class.java, EntityDataSerializers.INTEGER)


        fun getScaleAdjustment(fish: HybridAquaticDolphinEntity, adjustment: Float): Float {
            return 1.0f + (fish.size * adjustment)
        }

        fun canSpawn(
            type: EntityType<out WaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: Random
        ): Boolean {
            val topY = world.seaLevel - 8
            val bottomY = world.seaLevel - 64

            return pos.y in bottomY..topY &&
                    world.isWaterAt(pos)
        }
    }
}