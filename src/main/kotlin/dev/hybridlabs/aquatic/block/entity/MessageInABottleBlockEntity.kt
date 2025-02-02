package dev.hybridlabs.aquatic.block.entity

import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import dev.hybridlabs.aquatic.component.HybridAquaticComponentTypes
import dev.hybridlabs.aquatic.component.HybridAquaticComponentTypes.getEncoded
import dev.hybridlabs.aquatic.component.HybridAquaticComponentTypes.putEncoded
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.component.ComponentMap
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket
import net.minecraft.registry.RegistryWrapper
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import software.bernie.geckolib.animatable.GeoAnimatable
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animation.AnimatableManager
import software.bernie.geckolib.animation.Animation
import software.bernie.geckolib.animation.AnimationController
import software.bernie.geckolib.animation.AnimationState
import software.bernie.geckolib.animation.PlayState
import software.bernie.geckolib.animation.RawAnimation
import software.bernie.geckolib.util.GeckoLibUtil
import software.bernie.geckolib.util.RenderUtil

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

    override fun writeNbt(nbt: NbtCompound, lookup: RegistryWrapper.WrapperLookup) {
        super.writeNbt(nbt, lookup)

        nbt.putEncoded(VARIANT_KEY, MessageInABottleBlock.Variant.CODEC, variant)

        if (!messageItemStack.isEmpty) {
            nbt.putEncoded(MESSAGE_KEY, ItemStack.CODEC, messageItemStack)
        }
    }

    override fun readNbt(nbt: NbtCompound, lookup: RegistryWrapper.WrapperLookup) {
        super.readNbt(nbt, lookup)

        nbt.getEncoded(VARIANT_KEY, MessageInABottleBlock.Variant.CODEC)?.also { variant = it }
        nbt.getEncoded(MESSAGE_KEY, ItemStack.CODEC)?.also { messageItemStack = it }
    }

    override fun readComponents(components: ComponentsAccess) {
        components.get(HybridAquaticComponentTypes.BOTTLE_VARIANT)?.also { variant = it }
        components.get(HybridAquaticComponentTypes.STORED_BOTTLE_MESSAGE)?.also { messageItemStack = it }
    }

    override fun addComponents(builder: ComponentMap.Builder) {
        builder.add(HybridAquaticComponentTypes.BOTTLE_VARIANT, variant)
        builder.add(HybridAquaticComponentTypes.STORED_BOTTLE_MESSAGE, messageItemStack)

        messageItemStack.get(HybridAquaticComponentTypes.SEA_MESSAGE)?.also {
            builder.add(HybridAquaticComponentTypes.SEA_MESSAGE, it)
        }
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
        return RenderUtil.getCurrentTick()
    }

    override fun toInitialChunkDataNbt(lookup: RegistryWrapper.WrapperLookup): NbtCompound {
        return createNbt(lookup)
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
