package dev.hybridlabs.aquatic.network

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.util.Identifier

object HybridAquaticNetworking {
    var FISHING_BOBBER_LURE: Identifier = Identifier(HybridAquatic.MOD_ID, "fishing_bobber_lure")

    fun registerNetworking() {
        // Sends lure item to the client back
        /*ServerPlayNetworking.registerGlobalReceiver(FISHING_BOBBER_LURE) { server, client, handler, buf, packetSender ->
            val entityID = buf.readInt()

            val foundEntity = client.serverWorld.getEntityById(entityID)
            if (foundEntity != null && foundEntity is FishingBobberEntity) {
                val additionalBobberData = foundEntity as CustomFishingBobberEntityData

                val packetData = PacketByteBufs.create()
                packetData.writeInt(foundEntity.id)
                packetData.writeItemStack(additionalBobberData.`hybrid_aquatic$getLureItem`())

                val packetId = FISHING_BOBBER_LURE
                if (ServerPlayNetworking.canSend(client, packetId)) ServerPlayNetworking.send(client, packetId, packetData)
            }
        } TODO IMPORTANT*/
    }
}
