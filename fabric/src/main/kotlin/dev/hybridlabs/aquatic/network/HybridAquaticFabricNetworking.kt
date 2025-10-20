package dev.hybridlabs.aquatic.network

import dev.hybridlabs.aquatic.access.CustomFishingBobberEntityData
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.world.entity.projectile.FishingHook


object HybridAquaticFabricNetworking {

    init {
        PayloadTypeRegistry.playS2C().register(FishingBobberPayload.type, FishingBobberPayload.CODEC)
        PayloadTypeRegistry.playC2S().register(FishingBobberPayload.type, FishingBobberPayload.CODEC)
    }

    fun registerNetworking() {
        // Sends lure item to the client back
        ServerPlayNetworking.registerGlobalReceiver(FishingBobberPayload.type) { payload: FishingBobberPayload, context: ServerPlayNetworking.Context ->
            val foundEntity = context.player().level().getEntity(payload.id)
            if (foundEntity == null || foundEntity !is FishingHook) return@registerGlobalReceiver

            val additionalBobberData = foundEntity as CustomFishingBobberEntityData

            if (ServerPlayNetworking.canSend(context.player(), FishingBobberPayload.type))
                ServerPlayNetworking.send(
                    context.player(),
                    FishingBobberPayload(payload.id, additionalBobberData.`hybrid_aquatic$getLureItem`()
                ))
        }
    }
}
