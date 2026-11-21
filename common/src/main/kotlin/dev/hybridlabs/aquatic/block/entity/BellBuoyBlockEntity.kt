package dev.hybridlabs.aquatic.block.entity

import software.bernie.geckolib.animation.`object`.LoopType

import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import software.bernie.geckolib.animatable.GeoAnimatable
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animatable.manager.AnimatableManager
import software.bernie.geckolib.animation.AnimationController
import software.bernie.geckolib.animation.AnimationController.AnimationStateHandler
import software.bernie.geckolib.animation.RawAnimation
import software.bernie.geckolib.animation.state.AnimationTest
import software.bernie.geckolib.animation.`object`.PlayState
import software.bernie.geckolib.animation.`object`.EasingType
import software.bernie.geckolib.util.GeckoLibUtil
import software.bernie.geckolib.util.RenderUtil

class BellBuoyBlockEntity(pos: BlockPos, state: BlockState) :
    BlockEntity(HABlockEntityTypes.BELL_BUOY.get(), pos, state),
    GeoAnimatable {
    private val animCache = GeckoLibUtil.createInstanceCache(this)

    private fun <E> predicate(event: AnimationTest<E>): PlayState where E : BlockEntity?, E : GeoAnimatable {
        return if (level != null) {
            event.controller.setAnimation(FLOAT_ANIMATION)
            PlayState.CONTINUE
        } else {
            PlayState.STOP
        }
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(AnimationController<BellBuoyBlockEntity>("controller", 0, ::predicate))
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return animCache
    }


    companion object {
        val FLOAT_ANIMATION: RawAnimation = RawAnimation.begin().then("float", LoopType.LOOP)
    }
}
