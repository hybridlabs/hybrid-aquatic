package dev.hybridlabs.aquatic.network

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.access.CustomFishingBobberEntityData
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.projectile.FishingHook
import net.minecraft.world.item.ItemStack

data class FishingBobberPayload(val id: Int, val lure: ItemStack) : CustomPacketPayload {
    companion object {
        val FISHING_BOBBER_PAYLOAD_TYPE: ResourceLocation = CommonClass.locate("fishing_bobber")
        val type: CustomPacketPayload.Type<FishingBobberPayload> = CustomPacketPayload.Type(FISHING_BOBBER_PAYLOAD_TYPE)
        val CODEC: StreamCodec<RegistryFriendlyByteBuf, FishingBobberPayload> =
            StreamCodec.composite(
                ByteBufCodecs.INT, FishingBobberPayload::id,
                ItemStack.STREAM_CODEC, FishingBobberPayload::lure,
                ::FishingBobberPayload
            )
    }

    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload?> {
        return type
    }

}

object HybridAquaticNetworking {


    init {
        PayloadTypeRegistry.playS2C().register(FishingBobberPayload.type, FishingBobberPayload.CODEC)
        PayloadTypeRegistry.playC2S().register(FishingBobberPayload.type, FishingBobberPayload.CODEC)
    }

    fun registerNetworking() {
        // Sends lure item to the client back
        ServerPlayNetworking.registerGlobalReceiver(FishingBobberPayload.type) { payload: FishingBobberPayload, context: ServerPlayNetworking.Context ->
            context.server().execute {
            }
            val foundEntity = context.player().level().getEntity(payload.id)
            if (foundEntity != null && foundEntity is FishingHook) {
                val additionalBobberData = foundEntity as CustomFishingBobberEntityData

                if (ServerPlayNetworking.canSend(context.player(), FishingBobberPayload.type))
                    ServerPlayNetworking.send(
                        context.player(),
                        FishingBobberPayload(payload.id, additionalBobberData.`hybrid_aquatic$getLureItem`())
                    )
            }
        }
    }
}
