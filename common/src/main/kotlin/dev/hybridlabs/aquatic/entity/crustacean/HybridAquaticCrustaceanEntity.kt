package dev.hybridlabs.aquatic.entity.crustacean

import dev.hybridlabs.aquatic.entity.cephalopod.HybridAquaticCephalopodEntity
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import dev.hybridlabs.aquatic.entity.mammal.HybridAquaticMammalEntity
import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity
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
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal
import net.minecraft.world.entity.ai.goal.PanicGoal
import net.minecraft.world.entity.ai.goal.RandomStrollGoal
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation
import net.minecraft.world.entity.ai.navigation.PathNavigation
import net.minecraft.world.entity.animal.WaterAnimal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.pathfinder.BlockPathTypes
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
    world: Level,
    open val canDance: Boolean,
    open val hasShell: Boolean = false,
) : WaterAnimal(type, world), GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)
    private var fromFishingNet = false

    override fun createNavigation(level: Level): PathNavigation {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0f)
        setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0f)

        moveControl = MoveControl(this)
        setMaxUpStep(1.0f)

        return GroundPathNavigation(this, level)
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, AvoidEntityGoal(this, Player::class.java, 16.0F, 0.3, 0.75))
        goalSelector.addGoal(1, PanicGoal(this, 1.0))
        goalSelector.addGoal(3, RandomStrollGoal(this, 0.4))
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?,
    ): SpawnGroupData? {
        size = random.nextIntBetweenInclusive(getMinSize(), getMaxSize())
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
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

    //#region Moistness & Air
    override fun getMobType(): MobType {
        return MobType.WATER
    }

    override fun canBreatheUnderwater(): Boolean {
        return true
    }

    override fun handleAirSupply(air: Int) {
    }

    override fun isAffectedByFluids(): Boolean {
        return !onGround()
    }

    override fun isPushedByFluid(): Boolean {
        return false
    }
    //#endregion

    //#region Properties
    var size: Int
        get() = entityData.get(CRUSTACEAN_SIZE)
        set(size) {
            entityData.set(CRUSTACEAN_SIZE, size)
        }

    protected open fun getMinSize(): Int {
        return 0
    }

    protected open fun getMaxSize(): Int {
        return 0
    }

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
    override fun defineSynchedData() {
        super.defineSynchedData()

        entityData.define(CRUSTACEAN_SIZE, 0)
        entityData.define(ATTEMPT_ATTACK, false)
        entityData.define(SHELL_ITEM, ItemStack.EMPTY)
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        super.addAdditionalSaveData(nbt)

        nbt.putInt(CRUSTACEAN_SIZE_KEY, size)
        nbt.putBoolean("FromFishingNet", fromFishingNet)
        if (hasShell) nbt.put("ShellItem", shellItem.save(CompoundTag()))
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        super.readAdditionalSaveData(nbt)

        size = nbt.getInt(CRUSTACEAN_SIZE_KEY)
        fromFishingNet = nbt.getBoolean("FromFishingNet")

        if (hasShell) {
            val shellItemNBT = nbt.getCompound("ShellItem")
            shellItem = if (shellItemNBT.isEmpty) Items.NAUTILUS_SHELL.defaultInstance else ItemStack.of(shellItemNBT)
        }
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
                AnimationController.AnimationStateHandler { state: AnimationState<HybridAquaticCrustaceanEntity> ->
                    if (this.canDance && isSongPlaying()) {
                        return@AnimationStateHandler state.setAndContinue(DANCE_ANIMATION)
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

    override fun dropFromLootTable(source: DamageSource, causedByPlayer: Boolean) {
        val attacker = source.directEntity
        if (attacker !is HybridAquaticFishEntity &&
            attacker !is HybridAquaticSharkEntity &&
            attacker !is HybridAquaticCephalopodEntity &&
            attacker !is HybridAquaticMammalEntity
        ) {
            super.dropFromLootTable(source, causedByPlayer)
        }
    }

    companion object {
        val CRUSTACEAN_SIZE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HybridAquaticCrustaceanEntity::class.java, EntityDataSerializers.INT)
        val ATTEMPT_ATTACK: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(HybridAquaticCrustaceanEntity::class.java, EntityDataSerializers.BOOLEAN)
        val SHELL_ITEM: EntityDataAccessor<ItemStack> =
            SynchedEntityData.defineId(HybridAquaticCrustaceanEntity::class.java, EntityDataSerializers.ITEM_STACK)

        val DANCE_ANIMATION: RawAnimation = RawAnimation.begin().thenPlay("misc.dance")
        val HIDE_ANIMATION: RawAnimation = RawAnimation.begin().thenPlay("misc.hide")

        fun canSurfaceSpawn(
            type: EntityType<out WaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val topY = world.seaLevel + 4

            return pos.y <= topY &&
                    world.getBlockState(pos.below()).isSolid &&
                    world.isEmptyBlock(pos) &&
                    world.canSeeSky(pos)
        }

        fun canWaterSpawn(
            type: EntityType<out WaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val bottomY = world.seaLevel - 24

            return pos.y >= bottomY &&
                    world.getBlockState(pos.below()).isSolid &&
                    world.isWaterAt(pos) &&
                    world.canSeeSkyFromBelowWater(pos)
        }

        fun canDeepSpawn(
            type: EntityType<out WaterAnimal>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val topY = world.seaLevel - 24
            val bottomY = world.seaLevel - 256

            return pos.y in bottomY..topY &&
                    world.getBlockState(pos.below()).isSolid &&
                    world.isWaterAt(pos) &&
                    world.canSeeSkyFromBelowWater(pos)
        }

        fun getScaleAdjustment(crustacean: HybridAquaticCrustaceanEntity, adjustment: Float): Float {
            return 1.0f + (crustacean.size * adjustment)
        }

        const val CRUSTACEAN_SIZE_KEY = "CrustaceanSize"
    }
}