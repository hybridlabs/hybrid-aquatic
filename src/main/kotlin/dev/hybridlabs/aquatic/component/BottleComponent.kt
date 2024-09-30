package dev.hybridlabs.aquatic.component

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import net.minecraft.item.ItemStack
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.codec.PacketCodecs

data class BottleComponent(
    val variant: MessageInABottleBlock.Variant,
    val messageStack: ItemStack = ItemStack.EMPTY,
) {
    companion object {
        val CODEC: Codec<BottleComponent> = RecordCodecBuilder.create { instance ->
            instance.group(
                MessageInABottleBlock.Variant.CODEC.fieldOf("variant").forGetter(BottleComponent::variant),
                ItemStack.CODEC.fieldOf("message").forGetter(BottleComponent::messageStack)
            ).apply(instance, ::BottleComponent)
        }

        val PACKET_CODEC: PacketCodec<RegistryByteBuf, BottleComponent> = PacketCodec.tuple(
            PacketCodecs.codec(MessageInABottleBlock.Variant.CODEC),
            BottleComponent::variant,
            ItemStack.OPTIONAL_PACKET_CODEC,
            BottleComponent::messageStack,
            ::BottleComponent
        )
    }
}
