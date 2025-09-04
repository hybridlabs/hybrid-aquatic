package dev.hybridlabs.aquatic.entity.crustacean

import dev.hybridlabs.aquatic.entity.cephalopod.HybridAquaticCephalopodEntity
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity
import net.minecraft.block.Blocks
import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityGroup
import net.minecraft.entity.EntityType
import net.minecraft.entity.MobSpawnType
import net.minecraft.entity.ai.control.MoveControl
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.ai.pathing.EntityNavigation
import net.minecraft.entity.ai.pathing.MobNavigation
import net.minecraft.entity.ai.pathing.BlockPathTypes
import net.minecraft.entity.attribute.Attributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.SynchedEntityData
import net.minecraft.entity.data.EntityDataAccessor
import net.minecraft.entity.data.EntityDataSerializers
import net.minecraft.entity.mob.WaterAnimal
import net.minecraft.entity.player.Player
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
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.animation.RawAnimation
import software.bernie.geckolib.core.`object`.PlayState
import software.bernie.geckolib.util.GeckoLibUtil

@Suppress("DEPRECATION", "LeakingThis", "UNUSED_PARAMETER")
open class HybridAquaticCrustaceanEntity(
    type: EntityType<out HybridAquaticCrustaceanEntity>,
    world: World,
    open val canDance: Boolean,
) : WaterAnimal(type, world), GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)
    private var fromFishingNet = false
    private var songPlaying = false
    private var songSource: BlockPos? = null

    private var isHiding: Boolean = false

    private var hidingTimer: Int = 0
    private var lastDamageTime: Long = 0

    var size: Int
        get() = entityData.get(CRUSTACEAN_SIZE)
        set(size) {
            entityData.set(CRUSTACEAN_SIZE, size)
        }

    override fun initSynchedEntityData() {
        super.initSynchedEntityData()
        entityData.define(CRUSTACEAN_SIZE, 0)
        entityData.define(ATTEMPT_ATTACK, false)
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, PanicGoal(this, 1.0))
        goalSelector.addGoal(5,RandomRandomLookAroundGoal(this))
        goalSelector.addGoal(5, LookAtPlayerGoal(this,Player::class.java, 6.0f))
        goalSelector.addGoal(3, RandomStrollGoal(this, 0.4))
        goalSelector.addGoal(3, WanderAroundFarGoal(this, 0.3))
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?
    ): SpawnGroupData? {
        this.size = this.random.nextIntBetweenInclusive(getMinSize(), getMaxSize())
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
    }

    // region movement

    init {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        setPathfindingMalus(BlockPathTypes.WALKABLE, 0.0f)
        moveControl = MoveControl(this)
        navigation = MobNavigation(this, world)
    }

    override fun aiStep() {
        if (this.songSource == null || !songSource!!.isWithinDistance(
                this.pos,
                3.5
            ) || !world.getBlockState(this.songSource).isOf(Blocks.JUKEBOX)
        ) {
            this.songPlaying = false
            this.songSource = null
        }

        super.aiStep()
    }

    override fun setRecordPlayingNearby(songPosition: BlockPos, playing: Boolean) {
        this.songSource = songPosition
        this.songPlaying = playing
    }

    private fun isSongPlaying(): Boolean {
        return this.songPlaying
    }

    override fun getStepHeight(): Float {
        return 1.0F
    }

    override fun isAffectedByFluids(): Boolean {
        return !isOnGround
    }

    override fun isPushedByFluid(): Boolean {
        return false
    }

    private fun startHiding() {
        isHiding = true
        hidingTimer = 200
    }

    override fun tick() {
        super.tick()

        if ((this is HermitCrabEntity || this is GiantIsopodEntity) && isHiding) {
            hidingTimer--

            if (hidingTimer <= 0 && (world.time - lastDamageTime) >= 200) {
                isHiding = false
                attributes.getCustomInstance(Attributes.MOVEMENT_SPEED)?.baseValue = 0.3
                attributes.getCustomInstance(Attributes.ARMOR)?.baseValue = 5.0
            } else {
                attributes.getCustomInstance(Attributes.MOVEMENT_SPEED)?.baseValue = 0.0
                attributes.getCustomInstance(Attributes.ARMOR)?.baseValue = 50.0
            }
        }
    }

    override fun damage(source: DamageSource, amount: Float): Boolean {
        if (this is HermitCrabEntity || this is GiantIsopodEntity && !isHiding) {
            startHiding()
        }

        lastDamageTime = world.time

        return super.damage(source, amount)
    }

    // end region

    override fun getGroup(): EntityGroup {
        return EntityGroup.AQUATIC
    }

    protected open fun getMinSize(): Int {
        return 0
    }

    protected open fun getMaxSize(): Int {
        return 0
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        super.addAdditionalSaveData(nbt)
        nbt.putInt(CRUSTACEAN_SIZE_KEY, size)
        nbt.putBoolean("FromFishingNet", fromFishingNet)
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        super.readAdditionalSaveData(nbt)
        size = nbt.getInt(CRUSTACEAN_SIZE_KEY)
        fromFishingNet = nbt.getBoolean("FromFishingNet")
    }

    //#region SFX

    override fun calculateNextStepSoundDistance(): Float {
        return this.distanceTraveled + 0.25f
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents._TURTLE_EGG_CRACK
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents._TURTLE_EGG_BREAK
    }

    //#endregion

    override fun createNavigation(world: Level): EntityNavigation {
        return MobNavigation(this, world)
    }

    // region water breathing

    override fun canBreatheUnderwater(): Boolean {
        return true
    }

    override fun handleAirSupply(air: Int) {
    }

    // endregion

    override fun dropLoot(source: DamageSource, causedByPlayer: Boolean) {
        val attacker = source.attacker
        if (attacker !is HybridAquaticFishEntity && attacker !is HybridAquaticSharkEntity && attacker !is HybridAquaticCephalopodEntity) {
            super.dropLoot(source, causedByPlayer)
        }
    }

    override fun getSpawnClusterSize(): Int {
        return 4
    }

    override fun removeWhenFarAway(distanceSquared: Double): Boolean {
        return !fromFishingNet && !hasCustomName()
    }

    //#region Animations
    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            DefaultAnimations.genericWalkIdleController(this)
        )
        controllerRegistrar.add(
            AnimationController(this, "Hide", 5,
                AnimationController.AnimationStateHandler { state: AnimationState<HybridAquaticCrustaceanEntity> ->
                    if (this.isHiding) {
                        return@AnimationStateHandler state.setAndContinue(HIDE)
                    } else {
                        PlayState.STOP
                    }
                }
            )
        )
        controllerRegistrar.add(
            AnimationController(this, "Dance", 5,
                AnimationController.AnimationStateHandler { state: AnimationState<HybridAquaticCrustaceanEntity> ->
                    if (this.canDance && isSongPlaying() && !state.isMoving) {
                        return@AnimationStateHandler state.setAndContinue(DANCE)
                    } else {
                        PlayState.STOP
                    }
                }
            )
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    //#endregion

    companion object {
        val CRUSTACEAN_SIZE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticCrustaceanEntity::class.java, EntityDataSerializers.INTEGER)
        val ATTEMPT_ATTACK: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(HybridAquaticCrustaceanEntity::class.java, EntityDataSerializers.BOOLEAN)

        val DANCE: RawAnimation = RawAnimation.begin().thenPlay("misc.dance")
        val HIDE: RawAnimation = RawAnimation.begin().thenPlay("misc.hide")

        fun canSurfaceSpawn(
            type: EntityType<out WaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: Random
        ): Boolean {
            val topY = world.seaLevel + 8

            return pos.y <= topY &&
                    world.getBlockState(pos.down()).isSolid &&
                    world.isAir(pos)
        }

        fun canWaterSpawn(
            type: EntityType<out WaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: Random
        ): Boolean {
            val bottomY = world.seaLevel - 24

            return pos.y >= bottomY &&
                    world.getBlockState(pos.down()).isSolid &&
                    world.isWaterAt(pos)
        }

        fun canDeepSpawn(
            type: EntityType<out WaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: Random
        ): Boolean {
            val topY = world.seaLevel - 24
            val bottomY = world.seaLevel - 128

            return pos.y in bottomY..topY &&
                    world.getBlockState(pos.down()).isSolid &&
                    world.isWaterAt(pos)
        }

        fun getScaleAdjustment(crustacean: HybridAquaticCrustaceanEntity, adjustment: Float): Float {
            return 1.0f + (crustacean.size * adjustment)
        }

        const val CRUSTACEAN_SIZE_KEY = "CrustaceanSize"
    }
}