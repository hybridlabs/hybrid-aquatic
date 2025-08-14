package dev.hybridlabs.aquatic.network

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.item.ItemStack
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.packet.CustomPayload
import net.minecraft.util.Identifier

class FishingBobberLurePacket(
    val entityId: Int,
    val lureStack: ItemStack,
) : CustomPayload {
    constructor(buf: RegistryByteBuf) : this(
        buf.readInt(),
        ItemStack.OPTIONAL_PACKET_CODEC.decode(buf)
    )

    fun write(buf: RegistryByteBuf) {
        buf.writeVarInt(entityId)
        ItemStack.OPTIONAL_PACKET_CODEC.encode(buf, lureStack)
    }

    override fun getId(): CustomPayload.Id<FishingBobberLurePacket> {
        return ID
    }

    companion object {
        val PACKET_ID: Identifier = Identifier.of(HybridAquatic.MOD_ID, "fishing_bobber_lure")
        val ID: CustomPayload.Id<FishingBobberLurePacket> = CustomPayload.Id(PACKET_ID)

        val PACKET_CODEC: PacketCodec<RegistryByteBuf, FishingBobberLurePacket> = CustomPayload.codecOf(FishingBobberLurePacket::write, ::FishingBobberLurePacket)
    }
}