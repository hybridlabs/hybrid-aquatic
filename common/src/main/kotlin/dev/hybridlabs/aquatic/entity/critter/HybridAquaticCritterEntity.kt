package dev.hybridlabs.aquatic.entity.critter

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
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation
import net.minecraft.world.entity.ai.navigation.PathNavigation
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

    init {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        setPathfindingMalus(BlockPathTypes.WATER_BORDER, -1.0f)
        setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0f)
        setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0f)
    }

    override fun createNavigation(level: Level): PathNavigation {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0f)
        setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0f)

        moveControl = MoveControl(this)

        return GroundPathNavigation(this, level)
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

    //#region Moistness & Air
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

    override fun handleAirSupply(air: Int) {}
    //#endregion

    //#region Data
    override fun defineSynchedData() {
        super.defineSynchedData()
        entityData.define(CRITTER_SIZE, 0)
        entityData.define(CRITTER_FLAGS, 0.toByte())
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

    //#region SFX
    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.SLIME_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.SLIME_DEATH_SMALL
    }
    //#endregion

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

    //#region Properties
    var size: Int
        get() = entityData.get(CRITTER_SIZE)
        set(size) {
            entityData.set(CRITTER_SIZE, size)
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
    //#endregion

    override fun removeWhenFarAway(distanceSquared: Double): Boolean {
        return !fromFishingNet && !hasCustomName()
    }

    companion object {
        val CRITTER_SIZE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticCritterEntity::class.java, EntityDataSerializers.INT)
        val CRITTER_FLAGS: EntityDataAccessor<Byte> =
            SynchedEntityData.defineId(HybridAquaticCritterEntity::class.java, EntityDataSerializers.BYTE)

        fun canSpawn(
            type: EntityType<out WaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val topY = world.seaLevel
            val bottomY = world.seaLevel - 256

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