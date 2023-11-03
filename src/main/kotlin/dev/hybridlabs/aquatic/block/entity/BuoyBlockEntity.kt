package dev.hybridlabs.aquatic.block.entity

import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.math.BlockPos
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.animation.RawAnimation
import software.bernie.geckolib.core.`object`.PlayState
import software.bernie.geckolib.util.GeckoLibUtil
import software.bernie.geckolib.util.RenderUtils


class BuoyBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(HybridAquaticBlockEntityTypes.BUOY, pos, state), GeoAnimatable {
    private val animCache = GeckoLibUtil.createInstanceCache(this)

    fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        event.controller.setAnimation(BOB_ANIMATION)
        return PlayState.CONTINUE
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            AnimationController(
                this,
                "controller",
                0,
                ::predicate
            )
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return animCache
    }

    override fun getTick(p0: Any?): Double {
        return RenderUtils.getCurrentTick()
    }

    companion object {
        val BOB_ANIMATION: RawAnimation = RawAnimation.begin().thenLoop("water_bob")
    }
}
