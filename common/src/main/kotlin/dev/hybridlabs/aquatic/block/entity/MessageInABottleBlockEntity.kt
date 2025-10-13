package dev.hybridlabs.aquatic.block.entity

import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.HolderLookup
import net.minecraft.core.component.DataComponents
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.component.CustomData
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
    BlockEntity(HybridAquaticBlockEntityTypes.MESSAGE_IN_A_BOTTLE.get(), pos, state), GeoAnimatable {
    private val instanceCache = GeckoLibUtil.createInstanceCache(this)

    /**
     * The variant of this bottle.
     */
    var variant: MessageInABottleBlock.Variant = MessageInABottleBlock.Variant.BOTTLE

    /**
     * The message item inside this bottle.
     */
    var messageItemStack: ItemStack = ItemStack.EMPTY

    override fun saveAdditional(nbt: CompoundTag, registries: HolderLookup.Provider) {
        super.saveAdditional(nbt, registries)
        nbt.putString(VARIANT_KEY, variant.id)

        if (!messageItemStack.isEmpty) {
            nbt.put(MESSAGE_KEY, messageItemStack.save(registries, CompoundTag()))
        }
    }

    override fun loadAdditional(nbt: CompoundTag, registries: HolderLookup.Provider) {
        super.loadAdditional(nbt, registries)
        variant = MessageInABottleBlock.Variant.byId(nbt.getString(VARIANT_KEY))
        val message  = nbt.getCompound(MESSAGE_KEY)
        messageItemStack  = ItemStack.parseOptional(registries,nbt)
        messageItemStack.set(DataComponents.CUSTOM_DATA, CustomData.of(message))
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

        /**
         * The nbt key for the message text.
         */
        const val MESSAGE_KEY = "message"

        val WATER_BOB_ANIMATION: RawAnimation = RawAnimation.begin().then("water_bob", Animation.LoopType.LOOP)
    }
}
