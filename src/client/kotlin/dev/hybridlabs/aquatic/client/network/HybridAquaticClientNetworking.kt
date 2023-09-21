package dev.hybridlabs.aquatic.client.network

import dev.hybridlabs.aquatic.network.HybridAquaticNetworking
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.item.ItemStack

object HybridAquaticClientNetworking {
    init {
        ClientPlayNetworking.registerGlobalReceiver(HybridAquaticNetworking.FISHING_BOBBER_LURE) { client, handler, buf, responseSender ->
            val id: Int = buf.readInt()
            val itemStack: ItemStack = buf.readItemStack()
            println("received: $id, $itemStack")
            
            for (entity in handler.world.entities) {
                print("(${entity.uuid}, ${entity.id}), ")
            }

            client.world?.entities?.forEach {

            }
        }
    }
}