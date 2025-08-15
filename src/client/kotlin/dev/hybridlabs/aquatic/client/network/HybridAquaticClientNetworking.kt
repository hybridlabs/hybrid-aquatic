package dev.hybridlabs.aquatic.client.network

import dev.hybridlabs.aquatic.access.CustomFishingBobberEntityData
import dev.hybridlabs.aquatic.network.FishingBobberLurePacket
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.entity.projectile.FishingBobberEntity
import net.minecraft.item.ItemStack

object HybridAquaticClientNetworking {
    init {
        // Receives custom lure item and applies it to the bobber
        ClientPlayNetworking.registerGlobalReceiver(FishingBobberLurePacket.ID) { packet, context ->
            val id: Int = packet.entityId
            val itemStack: ItemStack = packet.lureStack

            val foundEntity = context.player().world.getEntityById(id)
            if (foundEntity != null && foundEntity is FishingBobberEntity) {
                val additionalBobberData = foundEntity as CustomFishingBobberEntityData

                additionalBobberData.`hybrid_aquatic$setLureItem`(itemStack)
            }

        }
    }
}