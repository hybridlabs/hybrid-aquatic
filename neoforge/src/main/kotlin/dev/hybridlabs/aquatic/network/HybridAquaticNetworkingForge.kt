package dev.hybridlabs.aquatic.network

import dev.hybridlabs.aquatic.access.CustomFishingBobberEntityData
import net.minecraft.client.Minecraft
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.projectile.FishingHook
import net.minecraft.world.item.ItemStack
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.network.PacketDistributor
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler
import net.neoforged.neoforge.network.handling.IPayloadContext

object HybridAquaticNetworkingForge {
    @SubscribeEvent // on the mod event bus
    fun register(event: RegisterPayloadHandlersEvent) {
        val registrar = event.registrar("1")
        registrar.playBidirectional(
            FishingBobberPayload.type,
            FishingBobberPayload.CODEC,
            DirectionalPayloadHandler(
                ClientPayloadHandler::handleDataOnMain,
                ServerPayloadHandler::handleDataOnMain,
            )
        )
    }

    fun sendHookPacket(entityId: Int, entityData: ItemStack) {
        PacketDistributor.sendToServer(FishingBobberPayload(entityId, entityData))
    }

    fun sendClientHookPacket(player: ServerPlayer, entityId: Int, entityData: ItemStack) {
        PacketDistributor.sendToPlayer(player, FishingBobberPayload(entityId, entityData))
    }

    object ClientPayloadHandler {
        fun handleDataOnMain(data: FishingBobberPayload, context: IPayloadContext) {
            val client = Minecraft.getInstance()
            val foundEntity = client.level!!.getEntity(data.id)
            val itemStack = data.lure
            if (foundEntity == null || foundEntity !is FishingHook) return

            val additionalBobberData = foundEntity as CustomFishingBobberEntityData
            additionalBobberData.lureItem
            sendHookPacket(data.id, itemStack)
        }
    }

    object ServerPayloadHandler {
        fun handleDataOnMain(data: FishingBobberPayload, context: IPayloadContext) {
            val sender = context.player()
            if (sender !is ServerPlayer) return

            val foundEntity = sender.level().getEntity(data.id)
            if (foundEntity !is FishingHook) return

            val additionalBobberData = foundEntity as CustomFishingBobberEntityData
            val lureItem: ItemStack = additionalBobberData.lureItem
            if (lureItem.isEmpty) return

            sendClientHookPacket(sender, foundEntity.id, lureItem)
        }
    }

}