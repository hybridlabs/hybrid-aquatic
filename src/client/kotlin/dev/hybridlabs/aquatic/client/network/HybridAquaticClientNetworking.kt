package dev.hybridlabs.aquatic.client.network

import dev.hybridlabs.aquatic.access.CustomFishingBobberEntityData
import dev.hybridlabs.aquatic.network.FishingBobberLurePacket
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.minecraft.entity.projectile.FishingBobberEntity

object HybridAquaticClientNetworking {
    init {
        PayloadTypeRegistry.playC2S().register(FishingBobberLurePacket.ID, FishingBobberLurePacket.PACKET_CODEC)

        ClientPlayNetworking.registerGlobalReceiver(FishingBobberLurePacket.ID) { packet, context ->
            val id = packet.entityId
            val itemStack = packet.lureStack

            val player = context.player()
            val world = player.world

            world.getEntityById(id)?.also { foundEntity ->
                if (foundEntity is FishingBobberEntity) {
                    val additionalBobberData = foundEntity as CustomFishingBobberEntityData
                    additionalBobberData.`hybrid_aquatic$setLureItem`(itemStack)
                }
            }
        }
    }
}
