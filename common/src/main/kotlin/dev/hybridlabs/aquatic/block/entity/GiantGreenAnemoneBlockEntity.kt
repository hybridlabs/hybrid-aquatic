package dev.hybridlabs.aquatic.block.entity

import software.bernie.geckolib.animation.`object`.LoopType

import net.minecraft.core.BlockPos
import net.minecraft.core.HolderLookup
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import software.bernie.geckolib.animatable.GeoAnimatable
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animatable.manager.AnimatableManager
import software.bernie.geckolib.cache.animation.Animation
import software.bernie.geckolib.animation.AnimationController
import software.bernie.geckolib.animation.state.AnimationTest
import software.bernie.geckolib.animation.`object`.PlayState
import software.bernie.geckolib.animation.RawAnimation
import software.bernie.geckolib.util.GeckoLibUtil
import software.bernie.geckolib.util.RenderUtil

class GiantGreenAnemoneBlockEntity(pos: BlockPos, state: BlockState) :
    BlockEntity(HABlockEntityTypes.GIANT_GREEN_ANEMONE.get(), pos, state), GeoAnimatable {
    private val factory = GeckoLibUtil.createInstanceCache(this)

    private fun <E> predicate(event: AnimationTest<E>): PlayState where E : BlockEntity?, E : GeoAnimatable {
        return if (level != null) {
            event.controller.setAnimation(SWAY_ANIMATION)
            PlayState.CONTINUE
        } else {
            PlayState.STOP
        }
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(AnimationController<GiantGreenAnemoneBlockEntity>("controller", 0, ::predicate))
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }


    override fun getUpdateTag(registries: HolderLookup.Provider): CompoundTag {
        return saveWithoutMetadata(registries)
    }

    override fun getUpdatePacket(): ClientboundBlockEntityDataPacket {
        return ClientboundBlockEntityDataPacket.create(this)
    }

    companion object {
        val SWAY_ANIMATION: RawAnimation = RawAnimation.begin().then("sway", LoopType.LOOP)
    }
}
