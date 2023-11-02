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

    override fun setNearbySongPlaying(songPosition: BlockPos?, playing: Boolean) {
        songSource = songPosition
        songPlaying = playing
        super.setNearbySongPlaying(songPosition, playing)
    }

    override fun mobTick() {
        val songSourceCopy = songSource
        if (songSourceCopy == null || !songSourceCopy.isWithinDistance(this.pos, 6.0) || !this.world.getBlockState(songSourceCopy).isOf(Blocks.JUKEBOX)) {
            songPlaying = false
            songSource = null
        }

        super.mobTick()
    }

    override fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        if (songPlaying) {
            event.controller.setAnimation(DANCE_ANIMATION)
            return PlayState.CONTINUE
        }
        return super.predicate(event)
    }

    companion object {
        val DANCE_ANIMATION: RawAnimation  = RawAnimation.begin().then("dance", Animation.LoopType.LOOP)
    }
}