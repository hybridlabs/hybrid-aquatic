package dev.hybridlabs.aquatic.entity.critter

import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityGroup
import net.minecraft.entity.EntityType
import net.minecraft.entity.MobSpawnType
import net.minecraft.entity.ai.control.MoveControl
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.ai.pathing.EntityNavigation
import net.minecraft.entity.ai.pathing.MobNavigation
import net.minecraft.entity.ai.pathing.BlockPathTypes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.SynchedEntityData
import net.minecraft.entity.data.EntityDataAccessor
import net.minecraft.entity.data.EntityDataSerializers
import net.minecraft.entity.mob.WaterAnimal
import net.minecraft.nbt.CompoundTag
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.random.Random
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.ServerLevelAccess
import net.minecraft.world.World
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
        setPathfindingMalus(BlockPathTypes.WALKABLE, -1.0f)
        moveControl = MoveControl(this)
        navigation = MobNavigation(this, world)
    }

    override fun createNavigation(world: Level): EntityNavigation {
        return MobNavigation(this, world)
    }

    override fun tick() {
        super.tick()

        if (!isUnderWater) {
            this.speed = 0.0F
        }
    }

    override fun getStepHeight(): Float {
        return 1.0F
    }

    override fun initSynchedEntityData() {
        super.initSynchedEntityData()
        entityData.define(CRITTER_SIZE, 0)
        entityData.define(CRITTER_FLAGS, 0.toByte())
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, TryFindWaterGoal(this))
        goalSelector.addGoal(1, PanicGoal(this, 0.3))
        goalSelector.addGoal(5,RandomRandomLookAroundGoal(this))
        goalSelector.addGoal(3, RandomStrollGoal(this, 0.3))
        goalSelector.addGoal(3, WanderAroundFarGoal(this, 0.3))
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?
    ): SpawnGroupData? {
        this.airSupply= this.maxAir
        this.size = this.random.nextIntBetweenInclusive(getMinSize(), getMaxSize())
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun isAffectedByFluids(): Boolean {
        return !isOnGround
    }

    override fun isPushedByFluid(): Boolean {
        return false
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

    override fun getGroup(): EntityGroup {
        return EntityGroup.AQUATIC
    }

    override fun handleAirSupply(air: Int) {}

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            DefaultAnimations.genericWalkIdleController(this)
        )
    }

    protected open fun getMinSize(): Int {
        return 0
    }

    protected open fun getMaxSize(): Int {
        return 0
    }

    override fun removeWhenFarAway(distanceSquared: Double): Boolean {
        return !fromFishingNet && !hasCustomName()
    }

    override fun getSpawnClusterSize(): Int {
        return 4
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents._SLIME_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents._SLIME_DEATH_SMALL
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    override fun canBreatheUnderwater(): Boolean {
        return true
    }

    var size: Int
        get() = entityData.get(CRITTER_SIZE)
        set(size) {
            entityData.set(CRITTER_SIZE, size)
        }

    companion object {
        val CRITTER_SIZE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticCritterEntity::class.java, EntityDataSerializers.INTEGER)
        val CRITTER_FLAGS: EntityDataAccessor<Byte> =
            SynchedEntityData.defineId(HybridAquaticCritterEntity::class.java, EntityDataSerializers.BYTE)

        fun canSpawn(
            type: EntityType<out WaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: Random
        ): Boolean {
            val topY = world.seaLevel
            val bottomY = world.seaLevel - 128

            return pos.y in bottomY..topY &&
                    world.getBlockState(pos.down()).isSolid &&
                    world.isWaterAt(pos)
        }

        fun getScaleAdjustment(critter: HybridAquaticCritterEntity, adjustment: Float): Float {
            return 1.0f + (critter.size * adjustment)
        }

        const val CRITTER_SIZE_KEY = "CritterSize"
    }
}