package dev.hybridlabs.aquatic.entity.mammal

import net.minecraft.entity.*
import net.minecraft.entity.ai.control.AquaticMoveControl
import net.minecraft.entity.ai.control.YawAdjustingLookControl
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.ai.pathing.EntityNavigation
import net.minecraft.entity.ai.pathing.SwimNavigation
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.particle.ParticleTypes
import net.minecraft.registry.tag.TagKey
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.Vec3d
import net.minecraft.util.math.random.Random
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
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
    world: World,
    open val prey: List<TagKey<EntityType<*>>>,
    open val predator: List<TagKey<EntityType<*>>>,
) : WaterCreatureEntity(type, world), GeoEntity {

    private val factory = GeckoLibUtil.createInstanceCache(this)

    override fun initialize(
        world: ServerWorldAccess,
        difficulty: LocalDifficulty,
        spawnReason: SpawnReason,
        entityData: EntityData?,
        entityNbt: NbtCompound?
    ): EntityData? {
        this.air = this.maxAir
        this.pitch = 0.0f
        this.size = this.random.nextBetween(getMinSize(), getMaxSize())
        this.size = this.random.nextBetween(getMinSize(), getMaxSize())
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt)
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

    override fun canBreatheInWater(): Boolean {
        return false
    }

    override fun tickWaterBreathingAir(air: Int) {
    }

    private var moistness: Int
        get() = dataTracker.get(MOISTNESS)
        set(moistness) {
            dataTracker.set(MOISTNESS, moistness)
        }

    var size: Int
        get() = dataTracker.get(DOLPHIN_SIZE)
        set(size) {
            dataTracker.set(DOLPHIN_SIZE, size)
        }

    override fun initDataTracker() {
        super.initDataTracker()
        dataTracker.startTracking(MOISTNESS, getMaxMoistness())
        dataTracker.startTracking(DOLPHIN_SIZE, 0)
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        nbt.putInt("Moistness", this.moistness)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        this.moistness = nbt.getInt("Moistness")
    }

    override fun initGoals() {
        goalSelector.add(0, BreatheAirGoal(this))
        goalSelector.add(0, MoveIntoWaterGoal(this))
        goalSelector.add(4, SwimAroundGoal(this, 1.0, 10))
        goalSelector.add(4, LookAroundGoal(this))
        goalSelector.add(5, LookAtEntityGoal(this, PlayerEntity::class.java, 6.0f))
        goalSelector.add(6, MeleeAttackGoal(this, 1.2000000476837158, true))
        goalSelector.add(8, ChaseBoatGoal(this))
    }

    override fun createNavigation(world: World): EntityNavigation {
        return SwimNavigation(this, world)
    }

    override fun tryAttack(target: Entity): Boolean {
        val bl = target.damage(
            this.damageSources.mobAttack(this),
            this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE).toInt().toFloat()
        )
        if (bl) {
            this.applyDamageEffects(this, target)
            this.playSound(SoundEvents.ENTITY_DOLPHIN_ATTACK, 1.0f, 1.0f)
        }

        return bl
    }

    override fun getMaxAir(): Int {
        return 4800
    }

    override fun getNextAirOnLand(air: Int): Int {
        return this.maxAir
    }

    override fun getActiveEyeHeight(pose: EntityPose, dimensions: EntityDimensions): Float {
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
        if (this.isAiDisabled) {
            this.air = this.maxAir
        } else {
            if (this.isWet) {
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

            if (world.isClient && this.isTouchingWater && (velocity.lengthSquared() > 0.03)) {
                val vec3d = this.getRotationVec(0.0f)
                val f = MathHelper.cos(this.yaw * 0.017453292f) * 0.3f
                val g = MathHelper.sin(this.yaw * 0.017453292f) * 0.3f
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
        return SoundEvents.ENTITY_DOLPHIN_HURT
    }

    override fun getDeathSound(): SoundEvent? {
        return SoundEvents.ENTITY_DOLPHIN_DEATH
    }

    override fun getAmbientSound(): SoundEvent? {
        return if (this.isTouchingWater) SoundEvents.ENTITY_DOLPHIN_AMBIENT_WATER else SoundEvents.ENTITY_DOLPHIN_AMBIENT
    }

    override fun getSplashSound(): SoundEvent {
        return SoundEvents.ENTITY_DOLPHIN_SPLASH
    }

    override fun getSwimSound(): SoundEvent {
        return SoundEvents.ENTITY_DOLPHIN_SWIM
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

    init {
        this.moveControl = AquaticMoveControl(this, 85, 10, 0.02f, 0.1f, true)
        this.lookControl = YawAdjustingLookControl(this, 10)
        this.setCanPickUpLoot(true)
    }

    companion object {
        const val MAX_AIR: Int = 4800
        val MOISTNESS: TrackedData<Int> =
            DataTracker.registerData(HybridAquaticDolphinEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val DOLPHIN_SIZE: TrackedData<Int> =
            DataTracker.registerData(HybridAquaticDolphinEntity::class.java, TrackedDataHandlerRegistry.INTEGER)


        fun getScaleAdjustment(fish: HybridAquaticDolphinEntity, adjustment: Float): Float {
            return 1.0f + (fish.size * adjustment)
        }

        fun canSpawn(
            type: EntityType<out WaterCreatureEntity>,
            world: ServerWorldAccess,
            reason: SpawnReason,
            pos: BlockPos,
            random: Random
        ): Boolean {
            val topY = world.seaLevel - 8
            val bottomY = world.seaLevel - 32

            return pos.y in bottomY..topY &&
                    world.isWater(pos) &&
                    world.isSkyVisibleAllowingSea(pos)
        }
    }
}