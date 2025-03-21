package dev.hybridlabs.aquatic.block.entity

import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket
import net.minecraft.state.property.Properties
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

/**
 * Represents the block entity for Message in a Bottle blocks.
 * @see MessageInABottleBlock
 */
class MessageInABottleBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(HybridAquaticBlockEntityTypes.MESSAGE_IN_A_BOTTLE, pos, state), IAnimatable {
    private val instanceCache = GeckoLibUtil.createFactory(this)

    /**
     * The variant of this bottle.
     */
    var variant: MessageInABottleBlock.Variant = MessageInABottleBlock.Variant.BOTTLE

    /**
     * The message item inside this bottle.
     */
    var messageItemStack: ItemStack = ItemStack.EMPTY

    override fun writeNbt(nbt: NbtCompound) {
        super.writeNbt(nbt)
        nbt.putString(VARIANT_KEY, variant.id)

        if (!messageItemStack.isEmpty) {
            nbt.put(MESSAGE_KEY, messageItemStack.writeNbt(NbtCompound()))
        }
    }

    override fun readNbt(nbt: NbtCompound) {
        super.readNbt(nbt)
        variant = MessageInABottleBlock.Variant.byId(nbt.getString(VARIANT_KEY))
        messageItemStack = ItemStack.fromNbt(nbt.getCompound(MESSAGE_KEY))
    }

    private fun <E> animate(event: AnimationEvent<E>): PlayState where E : BlockEntity, E : IAnimatable {
        return if (cachedState.get(Properties.WATERLOGGED)) {
            event.controller.setAnimation(WATER_BOB_ANIMATION)
            PlayState.CONTINUE
        } else {
            PlayState.STOP
        }
    }

    override fun registerControllers(registrar: AnimationData) {
        registrar.addAnimationController(AnimationController(this, "controller", 0.0f, ::animate))
    }

    override fun getFactory(): AnimationFactory {
        return instanceCache
    }

    override fun toInitialChunkDataNbt(): NbtCompound {
        return createNbt()
    }

    override fun toUpdatePacket(): BlockEntityUpdateS2CPacket {
        return BlockEntityUpdateS2CPacket.create(this)
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

        val WATER_BOB_ANIMATION: AnimationBuilder = AnimationBuilder().addAnimation("water_bob", ILoopType.EDefaultLoopTypes.LOOP)
    }
}
