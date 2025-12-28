package dev.hybridlabs.aquatic.client.network

import dev.hybridlabs.aquatic.access.CustomFishingBobberEntityData
import dev.hybridlabs.aquatic.network.HybridAquaticNetworking
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.world.entity.projectile.FishingHook
import net.minecraft.world.item.ItemStack

object HybridAquaticClientNetworking {
    init {
        // Receives custom lure item and applies it to the bobber
        ClientPlayNetworking.registerGlobalReceiver(HybridAquaticNetworking.FISHING_BOBBER_LURE) { client, handler, buf, responseSender ->
            client.execute {
            val id: Int = buf.readInt()
            val itemStack: ItemStack = buf.readItem()
            if (itemStack.isEmpty) return@execute

            val foundEntity = handler.level.getEntity(id)
            if (foundEntity == null || foundEntity !is FishingHook) return@execute
                val additionalBobberData = foundEntity as CustomFishingBobberEntityData

                additionalBobberData.`hybrid_aquatic$setLureItem`(itemStack)
            }
        }
    }
}