package dev.hybridlabs.aquatic.block.entity

import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.RegistryWrapper
import net.minecraft.util.math.BlockPos
import software.bernie.geckolib.animatable.GeoAnimatable
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animation.*
import software.bernie.geckolib.util.GeckoLibUtil
import software.bernie.geckolib.util.RenderUtil

class TubeSpongeBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(HybridAquaticBlockEntityTypes.TUBE_SPONGE, pos, state),
    GeoAnimatable {
    private val factory = GeckoLibUtil.createInstanceCache(this)
    private fun <E> predicate(event: AnimationState<E>): PlayState where E : BlockEntity?, E : GeoAnimatable {
        return if (world != null) {
            event.controller.setAnimation(SWAY_ANIMATION)
            PlayState.CONTINUE
        } else {
            PlayState.STOP
        }
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(AnimationController(this, "controller", 0, ::predicate))
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    override fun getTick(o: Any): Double {
        return RenderUtil.getCurrentTick()
    }

    override fun toInitialChunkDataNbt(registryLookup: RegistryWrapper.WrapperLookup?): NbtCompound? {
        return createNbt()
    }

    companion object {
        val SWAY_ANIMATION: RawAnimation = RawAnimation.begin().then("sway", Animation.LoopType.LOOP)
    }
}