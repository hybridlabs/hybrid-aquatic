package dev.hybridlabs.aquatic.entity.critter

import net.minecraft.block.Blocks
import net.minecraft.entity.EntityType
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
    private var diggingTimer: Int = 0
    private var isDigging: Boolean = false
    private val blockBelow = world.getBlockState(blockPos.down()).block

    override fun setNearbySongPlaying(songPosition: BlockPos?, playing: Boolean) {
        songSource = songPosition
        songPlaying = playing
        super.setNearbySongPlaying(songPosition, playing)
    }

    override fun tickMovement() {
        val songSourceCopy = songSource
        if (songSourceCopy == null || !songSourceCopy.isWithinDistance(pos, 3.46) || !world.getBlockState(songSourceCopy).isOf(Blocks.JUKEBOX)) {
            songPlaying = false
            songSource = null
        }

        super.tickMovement()
    }

    override fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        if (songPlaying) {
            event.controller.setAnimation(DANCE_ANIMATION)
            return PlayState.CONTINUE
        }
        if (isDigging && blockBelow == Blocks.SAND)
            event.controller.setAnimation(DIGGING_ANIMATION)
        return super.predicate(event)
    }

    companion object {
        val DANCE_ANIMATION: RawAnimation  = RawAnimation.begin().then("dance", Animation.LoopType.LOOP)
        val DIGGING_ANIMATION: RawAnimation = RawAnimation.begin().then("dig", Animation.LoopType.LOOP)
    }
}