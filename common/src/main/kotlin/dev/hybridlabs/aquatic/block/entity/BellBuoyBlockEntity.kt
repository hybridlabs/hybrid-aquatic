package dev.hybridlabs.aquatic.block.entity

import com.geckolib.animation.`object`.LoopType

import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import com.geckolib.animatable.GeoAnimatable
import com.geckolib.animatable.instance.AnimatableInstanceCache
import com.geckolib.animatable.manager.AnimatableManager
import com.geckolib.animation.AnimationController
import com.geckolib.animation.AnimationController.AnimationStateHandler
import com.geckolib.animation.RawAnimation
import com.geckolib.animation.state.AnimationTest
import com.geckolib.animation.`object`.PlayState
import com.geckolib.animation.`object`.EasingType
import com.geckolib.util.GeckoLibUtil
import com.geckolib.util.RenderUtil

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
