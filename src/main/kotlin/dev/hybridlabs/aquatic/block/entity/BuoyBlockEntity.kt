package dev.hybridlabs.aquatic.block.entity

import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.math.BlockPos
import software.bernie.geckolib3.core.IAnimatable
import software.bernie.geckolib3.core.PlayState
import software.bernie.geckolib3.core.builder.AnimationBuilder
import software.bernie.geckolib3.core.builder.ILoopType
import software.bernie.geckolib3.core.controller.AnimationController
import software.bernie.geckolib3.core.event.predicate.AnimationEvent
import software.bernie.geckolib3.core.manager.AnimationData
import software.bernie.geckolib3.core.manager.AnimationFactory
import software.bernie.geckolib3.util.GeckoLibUtil

class BuoyBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(HybridAquaticBlockEntityTypes.BUOY, pos, state), IAnimatable {
    private val animCache = GeckoLibUtil.createFactory(this)

    private fun <E> predicate(event: AnimationEvent<E>): PlayState where E : BlockEntity?, E : IAnimatable {
        return if (world != null) {
            event.controller.setAnimation(FLOAT_ANIMATION)
            PlayState.CONTINUE
        } else {
            PlayState.STOP
        }
    }

    override fun registerControllers(data: AnimationData) {
        data.addAnimationController(AnimationController(this, "controller", 0.0f, ::predicate))
    }

    override fun getFactory(): AnimationFactory {
        return animCache
    }

    companion object {
        val FLOAT_ANIMATION: AnimationBuilder = AnimationBuilder().addAnimation("float", ILoopType.EDefaultLoopTypes.LOOP)
    }
}
