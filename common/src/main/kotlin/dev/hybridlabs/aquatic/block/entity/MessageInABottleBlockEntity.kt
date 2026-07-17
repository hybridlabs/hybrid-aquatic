package dev.hybridlabs.aquatic.block.entity

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
import software.bernie.geckolib.animation.*
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

    override fun saveAdditional(tag: CompoundTag, registries: HolderLookup.Provider) {
        super.saveAdditional(tag, registries)
        tag.putString(VARIANT_KEY, variant.id)
    }

    override fun loadAdditional(tag: CompoundTag, registries: HolderLookup.Provider) {
        super.loadAdditional(tag, registries)
        variant = MessageInABottleBlock.Variant.byId(tag.getString(VARIANT_KEY))
    }

    private fun <E> predicate(
        event: AnimationState<E>
    ): PlayState where E : BlockEntity?, E : GeoAnimatable {
        return if (blockState.hasProperty(WATERLOGGED)) {
            event.controller.setAnimation(WATER_BOB_ANIMATION)
            PlayState.CONTINUE
        } else {
            PlayState.STOP
        }
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(AnimationController(this, "controller", 0, ::predicate))
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return instanceCache
    }

    override fun getTick(animatable: Any): Double {
        return RenderUtil.getCurrentTick()
    }

    override fun getUpdateTag(registries: HolderLookup.Provider): CompoundTag {
        return saveWithId(registries)
    }

    override fun getUpdatePacket(): ClientboundBlockEntityDataPacket {
        return ClientboundBlockEntityDataPacket.create(this)
    }

    companion object {
        /**
         * The nbt key for the variant id.
         */
        const val VARIANT_KEY = "variant"

        val WATER_BOB_ANIMATION: RawAnimation = RawAnimation.begin().then("water_bob", Animation.LoopType.LOOP)
    }
}
