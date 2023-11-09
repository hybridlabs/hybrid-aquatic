package dev.hybridlabs.aquatic.entity.critter

import dev.hybridlabs.aquatic.entity.ai.goal.CrabDigGoal
import net.minecraft.block.Blocks
import net.minecraft.entity.EntityType
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animation.Animation
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.animation.RawAnimation
import software.bernie.geckolib.core.`object`.PlayState

open class HybridAquaticCrabEntity(type: EntityType<out HybridAquaticCritterEntity>, world: World, variantCount: Int = 1): HybridAquaticCritterEntity(type, world, variantCount) {
    private var songSource: BlockPos? = null
    private var songPlaying: Boolean = false

    var diggingCooldown: Int = 0
    var isDigging: Boolean
        get() = dataTracker.get(IS_DIGGING)
        set(bool) {
            dataTracker.set(IS_DIGGING, bool)
        }

    //TODO: Mystic, you can remove initGoals here and then put it in required crab classes
    override fun initGoals() {
        super.initGoals()
        goalSelector.add(3, CrabDigGoal(this, 0.05))
    }

    override fun initDataTracker() {
        dataTracker.startTracking(IS_DIGGING, false)
        super.initDataTracker()
    }

    override fun setNearbySongPlaying(songPosition: BlockPos?, playing: Boolean) {
        songSource = songPosition
        songPlaying = playing
        super.setNearbySongPlaying(songPosition, playing)
    }

    override fun mobTick() {
        if (diggingCooldown > 0) diggingCooldown--

        super.mobTick()
    }

    override fun tickMovement() {
        val songSourceCopy = songSource
        if (songSourceCopy == null || !songSourceCopy.isWithinDistance(pos, 3.46) || !world.getBlockState(songSourceCopy).isOf(Blocks.JUKEBOX)) {
            songPlaying = false
            songSource = null
        }

        super.tickMovement()
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        nbt.putInt(DIGGING_COOLDOWN_KEY, diggingCooldown)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        diggingCooldown = nbt.getInt(DIGGING_COOLDOWN_KEY)
    }

    override fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        if (songPlaying) {
            event.controller.setAnimation(DANCE_ANIMATION)
            return PlayState.CONTINUE
        }

        if (isDigging) {
            event.controller.setAnimation(DIGGING_ANIMATION)
            return PlayState.CONTINUE
        }

        return super.predicate(event)
    }

    companion object {
        val DANCE_ANIMATION: RawAnimation  = RawAnimation.begin().then("dance", Animation.LoopType.LOOP)
        val DIGGING_ANIMATION: RawAnimation = RawAnimation.begin().then("dig", Animation.LoopType.LOOP)

        val IS_DIGGING: TrackedData<Boolean> = DataTracker.registerData(HybridAquaticCrabEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)
        const val DIGGING_COOLDOWN_KEY = "DiggingCooldown"
    }
}