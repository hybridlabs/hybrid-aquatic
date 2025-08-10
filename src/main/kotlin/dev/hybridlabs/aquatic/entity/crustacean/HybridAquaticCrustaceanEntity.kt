package dev.hybridlabs.aquatic.entity.crustacean

import dev.hybridlabs.aquatic.entity.cephalopod.HybridAquaticCephalopodEntity
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity
import net.minecraft.block.Blocks
import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.ai.control.MoveControl
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.ai.pathing.EntityNavigation
import net.minecraft.entity.ai.pathing.MobNavigation
import net.minecraft.entity.ai.pathing.PathNodeType
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.random.Random
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animation.*
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.util.GeckoLibUtil

@Suppress("DEPRECATION", "LeakingThis", "UNUSED_PARAMETER")
open class HybridAquaticCrustaceanEntity(
    type: EntityType<out HybridAquaticCrustaceanEntity>,
    world: World,
    open val canDance: Boolean,
) : WaterCreatureEntity(type, world), GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)
    private var fromFishingNet = false
    private var songPlaying = false
    private var songSource: BlockPos? = null

    private var isHiding: Boolean = false

    private var hidingTimer: Int = 0
    private var lastDamageTime: Long = 0

    var size: Int
        get() = dataTracker.get(CRUSTACEAN_SIZE)
        set(size) {
            dataTracker.set(CRUSTACEAN_SIZE, size)
        }

    override fun initDataTracker(builder: DataTracker.Builder) {
        super.initDataTracker(builder)
        builder.add(CRUSTACEAN_SIZE, 0)
        builder.add(ATTEMPT_ATTACK, false)
    }

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(1, EscapeDangerGoal(this, 1.0))
        goalSelector.add(5, LookAroundGoal(this))
        goalSelector.add(5, LookAtEntityGoal(this, PlayerEntity::class.java, 6.0f))
        goalSelector.add(3, WanderAroundGoal(this, 0.4))
        goalSelector.add(3, WanderAroundFarGoal(this, 0.3))
    }

    override fun initialize(
        world: ServerWorldAccess,
        difficulty: LocalDifficulty,
        spawnReason: SpawnReason,
        entityData: EntityData?
    ): EntityData? {
        this.size = this.random.nextBetween(getMinSize(), getMaxSize())
        return super.initialize(world, difficulty, spawnReason, entityData)
    }

    // region movement

    init {
        setPathfindingPenalty(PathNodeType.WATER, 0.0f)
        setPathfindingPenalty(PathNodeType.WALKABLE, 0.0f)
        moveControl = MoveControl(this)
        navigation = MobNavigation(this, world)
    }

    override fun tickMovement() {
        if (this.songSource == null || !songSource!!.isWithinDistance(
                this.pos,
                3.5
            ) || !world.getBlockState(this.songSource).isOf(Blocks.JUKEBOX)
        ) {
            this.songPlaying = false
            this.songSource = null
        }

        super.tickMovement()
    }

    override fun setNearbySongPlaying(songPosition: BlockPos, playing: Boolean) {
        this.songSource = songPosition
        this.songPlaying = playing
    }

    private fun isSongPlaying(): Boolean {
        return this.songPlaying
    }

    override fun getStepHeight(): Float {
        return 1.0F
    }

    override fun shouldSwimInFluids(): Boolean {
        return !isOnGround
    }

    override fun isPushedByFluids(): Boolean {
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
                attributes.getCustomInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)?.baseValue = 0.3
                attributes.getCustomInstance(EntityAttributes.GENERIC_ARMOR)?.baseValue = 5.0
            } else {
                attributes.getCustomInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)?.baseValue = 0.0
                attributes.getCustomInstance(EntityAttributes.GENERIC_ARMOR)?.baseValue = 50.0
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

    protected open fun getMinSize(): Int {
        return 0
    }

    protected open fun getMaxSize(): Int {
        return 0
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        nbt.putInt(CRUSTACEAN_SIZE_KEY, size)
        nbt.putBoolean("FromFishingNet", fromFishingNet)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        size = nbt.getInt(CRUSTACEAN_SIZE_KEY)
        fromFishingNet = nbt.getBoolean("FromFishingNet")
    }

    //#region SFX

    override fun calculateNextStepSoundDistance(): Float {
        return this.distanceTraveled + 0.25f
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.ENTITY_TURTLE_EGG_CRACK
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.ENTITY_TURTLE_EGG_BREAK
    }

    //#endregion

    override fun createNavigation(world: World): EntityNavigation {
        return MobNavigation(this, world)
    }

    // region water breathing

    override fun tickWaterBreathingAir(air: Int) {
    }

    // endregion

    override fun dropLoot(source: DamageSource, causedByPlayer: Boolean) {
        val attacker = source.attacker
        if (attacker !is HybridAquaticFishEntity && attacker !is HybridAquaticSharkEntity && attacker !is HybridAquaticCephalopodEntity) {
            super.dropLoot(source, causedByPlayer)
        }
    }

    override fun getLimitPerChunk(): Int {
        return 4
    }

    override fun canImmediatelyDespawn(distanceSquared: Double): Boolean {
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
        val CRUSTACEAN_SIZE: TrackedData<Int> =
            DataTracker.registerData(HybridAquaticCrustaceanEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val ATTEMPT_ATTACK: TrackedData<Boolean> =
            DataTracker.registerData(HybridAquaticCrustaceanEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)

        val DANCE: RawAnimation = RawAnimation.begin().thenPlay("misc.dance")
        val HIDE: RawAnimation = RawAnimation.begin().thenPlay("misc.hide")

        fun canSurfaceSpawn(
            type: EntityType<out WaterCreatureEntity>,
            world: ServerWorldAccess,
            reason: SpawnReason,
            pos: BlockPos,
            random: Random
        ): Boolean {
            val topY = world.seaLevel + 8

            return pos.y <= topY &&
                    world.getBlockState(pos.down()).isSolid &&
                    world.isAir(pos)
        }

        fun canWaterSpawn(
            type: EntityType<out WaterCreatureEntity>,
            world: ServerWorldAccess,
            reason: SpawnReason,
            pos: BlockPos,
            random: Random
        ): Boolean {
            val bottomY = world.seaLevel - 24

            return pos.y >= bottomY &&
                    world.getBlockState(pos.down()).isSolid &&
                    world.isWater(pos)
        }

        fun canDeepSpawn(
            type: EntityType<out WaterCreatureEntity>,
            world: ServerWorldAccess,
            reason: SpawnReason,
            pos: BlockPos,
            random: Random
        ): Boolean {
            val topY = world.seaLevel - 24
            val bottomY = world.seaLevel - 128

            return pos.y in bottomY..topY &&
                    world.getBlockState(pos.down()).isSolid &&
                    world.isWater(pos)
        }

        fun getScaleAdjustment(crustacean: HybridAquaticCrustaceanEntity, adjustment: Float): Float {
            return 1.0f + (crustacean.size * adjustment)
        }

        const val CRUSTACEAN_SIZE_KEY = "CrustaceanSize"
    }
}