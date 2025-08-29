package dev.hybridlabs.aquatic.network

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.access.CustomFishingBobberEntityData
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.projectile.FishingHook

object HybridAquaticNetworking {
    var FISHING_BOBBER_LURE: ResourceLocation = ResourceLocation(HybridAquatic.MOD_ID, "fishing_bobber_lure")

    fun registerNetworking() {
        // Sends lure item to the client back
        ServerPlayNetworking.registerGlobalReceiver(FISHING_BOBBER_LURE) { _, client, _, buf, _ ->
            val entityID = buf.readInt()

            val foundEntity = client.serverLevel().getEntity(entityID)
            if (foundEntity != null && foundEntity is FishingHook) {
                val additionalBobberData = foundEntity as CustomFishingBobberEntityData

                val packetData = PacketByteBufs.create()
                packetData.writeInt(foundEntity.id)
                packetData.writeItem(additionalBobberData.`hybrid_aquatic$getLureItem`())

                val packetId = FISHING_BOBBER_LURE
                if (ServerPlayNetworking.canSend(client, packetId)) ServerPlayNetworking.send(
                    client,
                    packetId,
                    packetData
                )
            }
        }
    }
}
