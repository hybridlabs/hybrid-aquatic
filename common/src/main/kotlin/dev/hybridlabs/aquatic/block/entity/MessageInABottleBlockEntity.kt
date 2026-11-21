package dev.hybridlabs.aquatic.block.entity

import net.minecraft.world.level.storage.ValueInput
import net.minecraft.world.level.storage.ValueOutput

import software.bernie.geckolib.animation.`object`.LoopType

import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.HolderLookup
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED
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

/**
 * Represents the block entity for Message in a Bottle blocks.
 * @see dev.hybridlabs.aquatic.block.MessageInABottleBlock
 */
class MessageInABottleBlockEntity(pos: BlockPos, state: BlockState) :
    BlockEntity(HABlockEntityTypes.MESSAGE_IN_A_BOTTLE.get(), pos, state),
    GeoAnimatable {
    private val instanceCache = GeckoLibUtil.createInstanceCache(this)

    /**
     * The variant of this bottle.
     */
    var variant: MessageInABottleBlock.Variant = MessageInABottleBlock.Variant.BOTTLE

    override fun saveAdditional(output: ValueOutput) {
        super.saveAdditional(output)
        output.putString(VARIANT_KEY, variant.id)
    }

    override fun loadAdditional(input: ValueInput) {
        super.loadAdditional(input)
        variant = MessageInABottleBlock.Variant.byId(input.getStringOr(VARIANT_KEY, variant.id))
    }

    private fun <E> predicate(
        event: AnimationTest<E>
    ): PlayState where E : BlockEntity?, E : GeoAnimatable {
        return if (blockState.hasProperty(WATERLOGGED)) {
            event.controller.setAnimation(WATER_BOB_ANIMATION)
            PlayState.CONTINUE
        } else {
            PlayState.STOP
        }
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(AnimationController<MessageInABottleBlockEntity>("controller", 0, ::predicate))
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return instanceCache
    }


    override fun getUpdateTag(registries: HolderLookup.Provider): CompoundTag {
        return saveWithoutMetadata(registries)
    }

    override fun getUpdatePacket(): ClientboundBlockEntityDataPacket {
        return ClientboundBlockEntityDataPacket.create(this)
    }

    companion object {
        /**
         * The nbt key for the variant id.
         */
        const val VARIANT_KEY = "variant"

        val WATER_BOB_ANIMATION: RawAnimation = RawAnimation.begin().then("water_bob", LoopType.LOOP)
    }
}
