package dev.hybridlabs.aquatic.network

import dev.hybridlabs.aquatic.CommonClass
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack

data class FishingBobberPayload(val id: Int, val lure: ItemStack) : CustomPacketPayload {
    companion object {
        val FISHING_BOBBER_PAYLOAD_TYPE: ResourceLocation = CommonClass.locate("fishing_bobber")
        val type: CustomPacketPayload.Type<FishingBobberPayload> = CustomPacketPayload.Type(FISHING_BOBBER_PAYLOAD_TYPE)
        val CODEC: StreamCodec<RegistryFriendlyByteBuf, FishingBobberPayload> =
            StreamCodec.composite(
                ByteBufCodecs.INT, FishingBobberPayload::id,
                ItemStack.OPTIONAL_STREAM_CODEC, FishingBobberPayload::lure,
                ::FishingBobberPayload
            )
    }

    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload> {
        return type
    }

}