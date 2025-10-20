package dev.hybridlabs.aquatic.client.network

import dev.hybridlabs.aquatic.access.CustomFishingBobberEntityData
import dev.hybridlabs.aquatic.network.FishingBobberPayload
import dev.hybridlabs.aquatic.network.FishingBobberPayload.Companion.type
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.registerGlobalReceiver
import net.minecraft.world.entity.projectile.FishingHook
import net.minecraft.world.item.ItemStack


object HybridAquaticClientNetworking {

    init {
        // Receives custom lure item and applies it to the bobber

        registerGlobalReceiver(type) { payload: FishingBobberPayload, context: ClientPlayNetworking.Context ->
            context.client().execute {
                val itemStack: ItemStack = payload.lure
                val foundEntity = context.client().level?.getEntity(payload.id)
                if (foundEntity == null || foundEntity !is FishingHook) return@execute

                val additionalBobberData = foundEntity as CustomFishingBobberEntityData
                additionalBobberData.`hybrid_aquatic$setLureItem`(itemStack)
            }
        }
    }
}