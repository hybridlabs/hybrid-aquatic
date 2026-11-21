package dev.hybridlabs.aquatic.network

import dev.hybridlabs.aquatic.access.CustomFishingBobberEntityData
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.world.entity.projectile.FishingHook
import net.minecraft.world.item.ItemStack


object HybridAquaticFabricNetworking {

    init {
        PayloadTypeRegistry.clientboundPlay().register(FishingBobberPayload.type, FishingBobberPayload.CODEC)
        PayloadTypeRegistry.serverboundPlay().register(FishingBobberPayload.type, FishingBobberPayload.CODEC)
    }

    fun registerNetworking() {
        // Sends lure item to the client back
        ServerPlayNetworking.registerGlobalReceiver(FishingBobberPayload.type) { payload: FishingBobberPayload, context: ServerPlayNetworking.Context ->
            val foundEntity = context.player().level().getEntity(payload.id)
            if (foundEntity == null || foundEntity !is FishingHook) return@registerGlobalReceiver

            val additionalBobberData = foundEntity as CustomFishingBobberEntityData
            val lureItem: ItemStack = additionalBobberData.lureItem
            if (lureItem.isEmpty) return@registerGlobalReceiver

            if (ServerPlayNetworking.canSend(context.player(), FishingBobberPayload.type))
                ServerPlayNetworking.send(
                    context.player(),
                    FishingBobberPayload(payload.id, lureItem
                ))
        }
    }
}
