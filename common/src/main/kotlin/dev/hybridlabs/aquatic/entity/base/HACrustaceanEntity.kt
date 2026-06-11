package dev.hybridlabs.aquatic.entity.base

import dev.hybridlabs.aquatic.world.WorldHelper
import net.minecraft.core.BlockPos
import net.minecraft.core.particles.BlockParticleOption
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.util.RandomSource
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.AgeableMob
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.SpawnGroupData
import net.minecraft.world.entity.ai.control.MoveControl
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal
import net.minecraft.world.entity.ai.goal.PanicGoal
import net.minecraft.world.entity.ai.goal.RandomStrollGoal
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation
import net.minecraft.world.entity.ai.navigation.PathNavigation
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.pathfinder.PathType
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animation.AnimatableManager
import software.bernie.geckolib.animation.AnimationController
import software.bernie.geckolib.animation.AnimationController.AnimationStateHandler
import software.bernie.geckolib.animation.AnimationState
import software.bernie.geckolib.animation.RawAnimation
import software.bernie.geckolib.animation.keyframe.event.ParticleKeyframeEvent
import software.bernie.geckolib.animation.PlayState
import software.bernie.geckolib.util.GeckoLibUtil

@Suppress("DEPRECATION", "LeakingThis", "UNUSED_PARAMETER")
open class HACrustaceanEntity(
    type: EntityType<out HACrustaceanEntity>,
    world: Level,
    open val canDance: Boolean,
) : HAWaterAnimal(type, world), GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)

    override fun createNavigation(level: Level): PathNavigation {
        setPathfindingMalus(PathType.WATER, 0.0f)
        setPathfindingMalus(PathType.DANGER_FIRE, 16.0f)
        setPathfindingMalus(PathType.DAMAGE_FIRE, -1.0f)

        moveControl = MoveControl(this)
        setMaxUpStep(1.0f)

        return GroundPathNavigation(this, level)
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, AvoidEntityGoal(this, Player::class.java, 16.0F, 0.3, 0.75))
        goalSelector.addGoal(1, PanicGoal(this, 1.0))
        goalSelector.addGoal(3, RandomStrollGoal(this, 0.4))
        goalSelector.addGoal(4, MeleeAttackGoal(this, 1.0, true))
    }

    private fun getHandSwingDuration(): Int {
        return 20
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
    ): SpawnGroupData? {
        size = random.nextIntBetweenInclusive(getMinSize(), getMaxSize())
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData)
    }

    override fun getBreedOffspring(
        p0: ServerLevel,
        p1: AgeableMob,
    ): AgeableMob? {
        return null
    }

    //#region Dancing
    private var songPlaying = false
    private var songSource: BlockPos? = null

    override fun aiStep() {
        if (
            this.songSource == null ||
            !songSource!!.closerToCenterThan(this.position(), 3.5) ||
            !level().getBlockState(this.songSource!!).`is`(Blocks.JUKEBOX)
        ) {
            this.songPlaying = false
            this.songSource = null
        }

        this.updateSwingTime()

        super.aiStep()
    }

    override fun setRecordPlayingNearby(songPosition: BlockPos, playing: Boolean) {
        this.songSource = songPosition
        this.songPlaying = playing
    }

    private fun isSongPlaying(): Boolean {
        return this.songPlaying
    }
    //#endregion

    //#region Properties
    override fun getMaxHeadXRot(): Int {
        return 1
    }

    override fun getMaxHeadYRot(): Int {
        return 1
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    override fun removeWhenFarAway(distanceSquared: Double): Boolean {
        return !fromFishingNet && !hasCustomName()
    }
    //#endregion

    //#region Data

    fun isBurrowing(): Boolean {
        return entityData.get(BURROWING)
    }

    private fun setBurrowing(digging: Boolean) {
        entityData.set(BURROWING, digging)
    }

    fun startBurrowing() {
        setBurrowing(true)
        playSound(SoundEvents.SNIFFER_DIGGING, 0.1f, 2.0f)
        navigation.stop()
    }

    fun stopBurrowing() {
        setBurrowing(false)
    }

    override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        defineSynchedData(builder)
        builder.define(BURROWING, false)
        builder.define(SHELL_ITEM, ItemStack.EMPTY)
    }

    override fun addAdditionalSaveData(compound: CompoundTag) {
        super.addAdditionalSaveData(compound)
        this.setBurrowing(compound.getBoolean("Burrowing"))
    }

    override fun readAdditionalSaveData(compound: CompoundTag) {
        super.readAdditionalSaveData(compound)
        this.setBurrowing(compound.getBoolean("Burrowing"))
    }
    //#endregion

    var shellItem: ItemStack
        get() = entityData.get(SHELL_ITEM)
        set(itemStack) {
            entityData.set(SHELL_ITEM, itemStack)
        }

    //#region SFX
    override fun nextStep(): Float {
        return this.moveDist + 0.5f
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.TURTLE_EGG_CRACK
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.TURTLE_EGG_BREAK
    }
    //#endregion

    //#region Animations
    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            DefaultAnimations.genericWalkIdleController(this)
        )
        controllerRegistrar.add(
            AnimationController(
                this, "Dance", 4,
                AnimationStateHandler { state: AnimationState<HACrustaceanEntity> ->
                    if (this.canDance && isSongPlaying())
                        return@AnimationStateHandler state.setAndContinue(DANCE_ANIMATION)
                    PlayState.STOP
                }
            )
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    fun particleEvents(event: ParticleKeyframeEvent<HACrustaceanEntity>) {
        val entity = event.animatable
        val level = entity.level()

        if (!level.isClientSide) return

        val blockpos = entity.blockPosition()
        val blockstate = level.getBlockState(blockpos.below())

        val rand = entity.random

        repeat(rand.nextInt(6) + 8) {
            val xOffset = rand.nextGaussian() * 0.2
            val zOffset = rand.nextGaussian() * 0.2

            level.addParticle(
                BlockParticleOption(ParticleTypes.BLOCK, blockstate),
                entity.x + xOffset,
                entity.y,
                entity.z + zOffset,
                rand.nextGaussian() * 0.05,
                0.3 + rand.nextDouble() * 0.4,
                rand.nextGaussian() * 0.05
            )
        }
    }
    //#endregion

    override fun dropFromLootTable(source: DamageSource, causedByPlayer: Boolean) {
        val attacker = source.directEntity
        if (attacker !is HAFishEntity &&
            attacker !is HASharkEntity &&
            attacker !is HACephalopodEntity &&
            attacker !is HAMammalEntity
        ) {
            super.dropFromLootTable(source, causedByPlayer)
        }
    }

    companion object {
        val ATTEMPT_ATTACK: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(HACrustaceanEntity::class.java, EntityDataSerializers.BOOLEAN)
        val SHELL_ITEM: EntityDataAccessor<ItemStack> =
            SynchedEntityData.defineId(HACrustaceanEntity::class.java, EntityDataSerializers.ITEM_STACK)

        val DANCE_ANIMATION: RawAnimation = RawAnimation.begin().thenPlay("misc.dance")
        val BURROW_ANIMATION: RawAnimation = RawAnimation.begin().thenPlay("misc.burrow")
        val HIDE_ANIMATION: RawAnimation = RawAnimation.begin().thenPlay("misc.hide")

        val BURROWING: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(HACrustaceanEntity::class.java, EntityDataSerializers.BOOLEAN)

        fun canSpawnOnLand(
            type: EntityType<out HAWaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val seaLevel = world.level.chunkSource.generator.seaLevel
            val topY = seaLevel + 4

            return pos.y <= topY &&
                    world.getBlockState(pos.below()).isSolid &&
                    world.isEmptyBlock(pos) &&
                    world.canSeeSky(pos)
        }

        fun canSpawnInWater(
            type: EntityType<out HAWaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val seaLevel = world.level.chunkSource.generator.seaLevel
            val bottomY = seaLevel - 24

            return pos.y >= bottomY &&
                    world.getBlockState(pos.below()).isSolid &&
                    world.isWaterAt(pos) &&
                    WorldHelper.canSeeSkyFromBelowWater(world, pos)
        }

        fun canSpawnInDeepWater(
            type: EntityType<out HAWaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val seaLevel = world.level.chunkSource.generator.seaLevel
            val topY = seaLevel - 24
            val bottomY = seaLevel - 256

            return pos.y in bottomY..topY &&
                    world.getBlockState(pos.below()).isSolid &&
                    world.isWaterAt(pos) &&
                    WorldHelper.canSeeSkyFromBelowWater(world, pos)
        }
    }
}