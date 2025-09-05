package dev.hybridlabs.aquatic.block.entity

import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.*
import software.bernie.geckolib.core.`object`.PlayState
import software.bernie.geckolib.util.GeckoLibUtil
import software.bernie.geckolib.util.RenderUtils

class StrawberryAnemoneBlockEntity(pos: BlockPos, state: BlockState) :
    BlockEntity(HybridAquaticBlockEntityTypes.STRAWBERRY_ANEMONE.get(), pos, state), GeoAnimatable {
    private val factory = GeckoLibUtil.createInstanceCache(this)

    private fun <E> predicate(event: AnimationState<E>): PlayState where E : BlockEntity?, E : GeoAnimatable {
        return if (level != null) {
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
        return RenderUtils.getCurrentTick()
    }

    override fun getUpdateTag(): CompoundTag {
        return saveWithoutMetadata()
    }

    override fun getUpdatePacket(): ClientboundBlockEntityDataPacket {
        return ClientboundBlockEntityDataPacket.create(this)
    }

    companion object {
        val SWAY_ANIMATION: RawAnimation = RawAnimation.begin().then("sway", Animation.LoopType.LOOP)

    }
}
