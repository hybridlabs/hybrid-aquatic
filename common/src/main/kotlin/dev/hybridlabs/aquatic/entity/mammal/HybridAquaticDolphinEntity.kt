package dev.hybridlabs.aquatic.entity.mammal

import dev.hybridlabs.aquatic.entity.ai.goal.HybridAquaticJumpGoal
import net.minecraft.core.BlockPos
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.TagKey
import net.minecraft.util.Mth
import net.minecraft.util.RandomSource
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
import net.minecraft.world.entity.animal.WaterAnimal
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.pathfinder.BlockPathTypes
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
        this.airSupply = this.maxAirSupply
        this.yRot = 0.0f
        this.size = this.random.nextIntBetweenInclusive(getMinSize(), getMaxSize())
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
    }

    protected open fun getMinSize(): Int {
        return 0
    }

    protected open fun getMaxSize(): Int {
        return 0
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    //#region Animations
    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            AnimationController(
                this, "Swim/Idle", 4
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

    override fun defineSynchedData() {
        super.defineSynchedData()
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
        setCanPickUpLoot(true)
        moveControl = SmoothSwimmingMoveControl(this, 85, 5, 0.02f, 0.1f, true)
        lookControl = SmoothSwimmingLookControl(this, 15)
        navigation = WaterBoundPathNavigation(this, world)
    }

    override fun registerGoals() {
        goalSelector.addGoal(0, BreathAirGoal(this))
        goalSelector.addGoal(0, TryFindWaterGoal(this))
        goalSelector.addGoal(4, RandomSwimmingGoal(this, 1.0, 2))
        goalSelector.addGoal(4, RandomLookAroundGoal(this))
        goalSelector.addGoal(5, LookAtPlayerGoal(this, Player::class.java, 6.0f))
        goalSelector.addGoal(5, HybridAquaticJumpGoal(this, 10))
        goalSelector.addGoal(6, MeleeAttackGoal(this, 1.2000000476837158, true))
        goalSelector.addGoal(8, FollowBoatGoal(this))
    }

    override fun doHurtTarget(target: Entity): Boolean {
        val bl = target.hurt(
            this.damageSources().mobAttack(this),
            this.getAttributeValue(Attributes.ATTACK_DAMAGE).toInt().toFloat()
        )
        if (bl) {
            this.doEnchantDamageEffects(this, target)
            this.playSound(SoundEvents.DOLPHIN_ATTACK, 1.0f, 1.0f)
        }

        return bl
    }

    override fun getMaxAirSupply(): Int {
        return 4800
    }

    override fun getAirSupply(): Int {
        return this.maxAirSupply
    }

    override fun getStandingEyeHeight(pose: Pose, dimensions: EntityDimensions): Float {
        return 0.3f
    }

    override fun getMaxHeadYRot(): Int {
        return 1
    }

    override fun getMaxHeadXRot(): Int {
        return 1
    }

    override fun startRiding(vehicle: Entity): Boolean {
        return true
    }

    override fun canHoldItem(stack: ItemStack): Boolean {
        val equipmentSlot = getEquipmentSlotForItem(stack)
        return if (!getItemBySlot(equipmentSlot).isEmpty) {
            false
        } else {
            equipmentSlot == EquipmentSlot.MAINHAND && super.canHoldItem(stack)
        }
    }

    override fun pickUpItem(item: ItemEntity) {
        if (getItemBySlot(EquipmentSlot.MAINHAND).isEmpty) {
            val itemStack = item.item
            if (this.canHoldItem(itemStack)) {
                this.onItemPickup(item)
                this.setItemSlot(EquipmentSlot.MAINHAND, itemStack)
                this.setGuaranteedDrop(EquipmentSlot.MAINHAND)
                this.take(item, itemStack.count)
                item.discard()
            }
        }
    }

    override fun tick() {
        super.tick()
        if (this.isNoAi) {
            this.airSupply = this.maxAirSupply
        } else {
            if (this.isInWaterRainOrBubble) {
                this.moistness = 2400
            } else {
                this.moistness -= 1
                if (this.moistness <= 0) {
                    this.hurt(this.damageSources().dryOut(), 1.0f)
                }

                if (this.onGround()) {
                    this.deltaMovement = deltaMovement.add(
                        ((random.nextFloat() * 2.0f - 1.0f) * 0.2f).toDouble(),
                        0.5,
                        ((random.nextFloat() * 2.0f - 1.0f) * 0.2f).toDouble()
                    )
                    this.xRot = random.nextFloat() * 360.0f
                    this.setOnGround(false)
                    this.hasImpulse = true
                }
            }

            if (level().isClientSide && this.wasTouchingWater && (deltaMovement.lengthSqr() > 0.03)) {
                val vec3d = this.getViewVector(0.0f)
                val f = Mth.cos(this.xRot * 0.017453292f) * 0.3f
                val g = Mth.sin(this.xRot * 0.017453292f) * 0.3f
                val h = 1.2f - random.nextFloat() * 0.7f

                for (i in 0..1) {
                    level().addParticle(
                        ParticleTypes.DOLPHIN,
                        this.x - vec3d.x * h.toDouble() + f.toDouble(),
                        this.y - vec3d.y,
                        this.z - vec3d.z * h.toDouble() + g.toDouble(), 0.0, 0.0, 0.0
                    )
                    level().addParticle(
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
        return SoundEvents.DOLPHIN_HURT
    }

    override fun getDeathSound(): SoundEvent? {
        return SoundEvents.DOLPHIN_DEATH
    }

    override fun getAmbientSound(): SoundEvent? {
        return if (this.wasTouchingWater) SoundEvents.DOLPHIN_AMBIENT_WATER else SoundEvents.DOLPHIN_AMBIENT
    }

    override fun getSwimSplashSound(): SoundEvent {
        return SoundEvents.DOLPHIN_SPLASH
    }

    override fun getSwimSound(): SoundEvent {
        return SoundEvents.DOLPHIN_SWIM
    }

    private fun getMaxMoistness(): Int {
        return 1800
    }

    companion object {
        const val MAX_AIR: Int = 4800
        val MOISTNESS: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticDolphinEntity::class.java, EntityDataSerializers.INT)
        val DOLPHIN_SIZE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticDolphinEntity::class.java, EntityDataSerializers.INT)


        fun getScaleAdjustment(fish: HybridAquaticDolphinEntity, adjustment: Float): Float {
            return 1.0f + (fish.size * adjustment)
        }

        fun canSpawn(
            type: EntityType<out WaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource
        ): Boolean {
            val topY = world.seaLevel - 8
            val bottomY = world.seaLevel - 64

            return pos.y in bottomY..topY &&
                    world.isWaterAt(pos)
        }
    }
}