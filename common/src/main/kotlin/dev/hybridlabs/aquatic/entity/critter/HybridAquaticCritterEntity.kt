package dev.hybridlabs.aquatic.entity.critter

import dev.hybridlabs.aquatic.entity.ai.control.WallClimbNavigation
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.util.RandomSource
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.MobType
import net.minecraft.world.entity.SpawnGroupData
import net.minecraft.world.entity.ai.control.MoveControl
import net.minecraft.world.entity.ai.goal.RandomStrollGoal
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal
import net.minecraft.world.entity.animal.WaterAnimal
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.pathfinder.BlockPathTypes
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.util.GeckoLibUtil


@Suppress("LeakingThis", "DEPRECATION", "UNUSED_PARAMETER")
open class HybridAquaticCritterEntity(
    type: EntityType<out HybridAquaticCritterEntity>,
    world: Level,
) : WaterAnimal(type, world), GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)
    private var fromFishingNet = false
    private var climbingTicks = 0

    init {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        setPathfindingMalus(BlockPathTypes.WATER_BORDER, -1.0f)
        setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0f)
        setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0f)
        moveControl = MoveControl(this)
        navigation = WallClimbNavigation(this, world)
    }

    fun isMoving(): Boolean {
        return (this.onGround() || this.onClimbable()) && deltaMovement.lengthSqr() >= 0.0001
    }

    override fun tick() {
        super.tick()

        if (!level().isClientSide) {
            setClimbingWall(horizontalCollision)
        }

        if (isClimbingWall()) {
            climbingTicks++

        } else {
            climbingTicks = 0
        }

        if (onClimbable()) {
            val velocity = deltaMovement
            setDeltaMovement(velocity.x, velocity.y * 0.33, velocity.z)
        }
    }

    override fun onClimbable(): Boolean {
        return this.climbingTicks > 8 && this.isClimbingWall()
    }

    private fun isClimbingWall(): Boolean {
        return entityData.get(CLIMBING)
    }

    private fun setClimbingWall(isClimbingWall: Boolean) {
        entityData.set(CLIMBING, isClimbingWall)
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(5, TryFindWaterGoal(this))
        goalSelector.addGoal(3, RandomStrollGoal(this, 0.3))
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?,
    ): SpawnGroupData? {
        this.size = this.random.nextIntBetweenInclusive(getMinSize(), getMaxSize())
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun getMobType(): MobType {
        return MobType.WATER
    }

    override fun canBreatheUnderwater(): Boolean {
        return true
    }

    override fun isAffectedByFluids(): Boolean {
        return !onGround()
    }

    override fun isPushedByFluid(): Boolean {
        return false
    }

    //#region NBT
    override fun defineSynchedData() {
        super.defineSynchedData()
        entityData.define(CRITTER_SIZE, 0)
        entityData.define(CRITTER_FLAGS, 0.toByte())
        entityData.define(CLIMBING, false)
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        super.addAdditionalSaveData(nbt)
        nbt.putInt(CRITTER_SIZE_KEY, size)
        nbt.putBoolean("FromFishingNet", fromFishingNet)
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        super.readAdditionalSaveData(nbt)
        size = nbt.getInt(CRITTER_SIZE_KEY)
        fromFishingNet = nbt.getBoolean("FromFishingNet")
    }
    //#endregion

    override fun handleAirSupply(air: Int) {}

    //#region Animations
    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            DefaultAnimations.genericWalkIdleController(this)
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }
    //#endregion

    protected open fun getMinSize(): Int {
        return 0
    }

    protected open fun getMaxSize(): Int {
        return 0
    }

    override fun removeWhenFarAway(distanceSquared: Double): Boolean {
        return !fromFishingNet && !hasCustomName()
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    //#region SFX
    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.SLIME_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.SLIME_DEATH_SMALL
    }
    //#endregion

    var size: Int
        get() = entityData.get(CRITTER_SIZE)
        set(size) {
            entityData.set(CRITTER_SIZE, size)
        }

    companion object {
        val CRITTER_SIZE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticCritterEntity::class.java, EntityDataSerializers.INT)
        val CRITTER_FLAGS: EntityDataAccessor<Byte> =
            SynchedEntityData.defineId(HybridAquaticCritterEntity::class.java, EntityDataSerializers.BYTE)
        val CLIMBING: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(HybridAquaticCritterEntity::class.java, EntityDataSerializers.BOOLEAN)

        fun canSpawn(
            type: EntityType<out WaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val topY = world.seaLevel
            val bottomY = world.seaLevel - 128

            return pos.y in bottomY..topY &&
                    world.getBlockState(pos.below()).isSolid &&
                    world.isWaterAt(pos)
        }

        fun getScaleAdjustment(critter: HybridAquaticCritterEntity, adjustment: Float): Float {
            return 1.0f + (critter.size * adjustment)
        }

        const val CRITTER_SIZE_KEY = "CritterSize"
    }
}