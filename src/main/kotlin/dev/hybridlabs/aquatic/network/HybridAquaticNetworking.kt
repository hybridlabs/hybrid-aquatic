package dev.hybridlabs.aquatic.network

import dev.hybridlabs.aquatic.access.CustomFishingBobberEntityData
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.entity.projectile.FishingBobberEntity

object HybridAquaticNetworking {
    init {
        PayloadTypeRegistry.playS2C().register(FishingBobberLurePacket.ID, FishingBobberLurePacket.PACKET_CODEC)
        PayloadTypeRegistry.playC2S().register(FishingBobberLurePacket.ID, FishingBobberLurePacket.PACKET_CODEC)

        // Sends lure item to the client back
        ServerPlayNetworking.registerGlobalReceiver(FishingBobberLurePacket.ID) { packet, context ->
            val player = context.player()
            if (ServerPlayNetworking.canSend(player, FishingBobberLurePacket.ID)) {
                val world = player.world
                val id = packet.entityId
                world.getEntityById(id)?.also { foundEntity ->
                    if (foundEntity is FishingBobberEntity) {
                        val additionalBobberData = foundEntity as CustomFishingBobberEntityData
                        val returnPacket = FishingBobberLurePacket(foundEntity.id, additionalBobberData.`hybrid_aquatic$getLureItem`())
                        ServerPlayNetworking.send(player, returnPacket)
                    }
                }
            }
        }
    }
}