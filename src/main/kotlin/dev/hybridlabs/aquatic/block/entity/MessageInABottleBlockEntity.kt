package dev.hybridlabs.aquatic.block.entity

import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.Animation
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.animation.RawAnimation
import software.bernie.geckolib.core.`object`.PlayState
import software.bernie.geckolib.util.GeckoLibUtil
import software.bernie.geckolib.util.RenderUtils

/**
 * Represents the block entity for Message in a Bottle blocks.
 * @see MessageInABottleBlock
 */
class MessageInABottleBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(HybridAquaticBlockEntityTypes.MESSAGE_IN_A_BOTTLE, pos, state), GeoAnimatable {
    private val instanceCache = GeckoLibUtil.createInstanceCache(this)

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

    private fun <E> animate(event: AnimationState<E>): PlayState where E : BlockEntity, E : GeoAnimatable {
        return if (cachedState.get(Properties.WATERLOGGED)) {
            event.controller.setAnimation(WATER_BOB_ANIMATION)
            PlayState.CONTINUE
        } else {
            PlayState.STOP
        }
    }

    override fun registerControllers(registrar: AnimatableManager.ControllerRegistrar) {
        registrar.add(AnimationController(this, "controller", 0, ::animate))
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return instanceCache
    }

    override fun getTick(animatable: Any): Double {
        return RenderUtils.getCurrentTick()
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

        val WATER_BOB_ANIMATION: RawAnimation = RawAnimation.begin().then("water_bob", Animation.LoopType.LOOP)
    }
}
