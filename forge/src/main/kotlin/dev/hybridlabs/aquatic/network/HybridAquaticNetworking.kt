package dev.hybridlabs.aquatic.network

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.access.CustomFishingBobberEntityData
import net.minecraft.client.Minecraft
import net.minecraft.client.multiplayer.ClientPacketListener
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.protocol.game.ServerPacketListener
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.projectile.FishingHook
import net.minecraft.world.item.ItemStack
import net.minecraftforge.network.NetworkEvent
import net.minecraftforge.network.NetworkRegistry
import net.minecraftforge.network.PacketDistributor
import net.minecraftforge.network.simple.SimpleChannel
import java.util.function.Supplier

object HybridAquaticNetworking {
    private const val PROTOCOL_VERSION = "1"
    val CHANNEL: SimpleChannel = NetworkRegistry.newSimpleChannel(
        CommonClass.locate("main"),
        { PROTOCOL_VERSION },
        { anObject: String? -> PROTOCOL_VERSION == anObject },
        { anObject: String? -> PROTOCOL_VERSION == anObject })
    var messageId: Int = 0

    @Suppress("INFERRED_INVISIBLE_RETURN_TYPE_WARNING")
    fun registerPackets() {
        CHANNEL.registerMessage(
            messageId++,
            HookPacket::class.java,
            { obj: HookPacket?, buffer: FriendlyByteBuf? -> obj!!.encoder(buffer!!) },
            { buffer: FriendlyByteBuf? -> HookPacket(buffer!!) },
            { obj: HookPacket?, ctx: Supplier<NetworkEvent.Context?>? -> obj!!.handle(ctx!!) })
    }

    fun sendHookPacket(entityId: Int, entityData: ItemStack) {
        CHANNEL.sendToServer(HookPacket(entityId, entityData))
    }

    fun sendClientHookPacket(player: ServerPlayer?, entityId: Int, entityData: ItemStack) {
        CHANNEL.send(PacketDistributor.PLAYER.with { player }, HookPacket(entityId, entityData))
    }

    fun handle(msg: HookPacket, ctx: Supplier<NetworkEvent.Context?>) {
        ctx.get()!!.enqueueWork { handleHookPacket(msg, ctx) }
        ctx.get()!!.packetHandled = true
    }

    fun handleHookPacket(packet: HookPacket, ctx: Supplier<NetworkEvent.Context?>) {
        val listener = ctx.get()!!.networkManager.packetListener
        if (listener is ServerPacketListener) {
            ctx.get()!!.enqueueWork {
                val sender = ctx.get()!!.sender
                val foundEntity = sender?.level()?.getEntity(packet.entityId)
                if (foundEntity is FishingHook) {
                    val additionalBobberData = foundEntity as CustomFishingBobberEntityData
                    val item: ItemStack = additionalBobberData.lureItem
                    sendClientHookPacket(sender, foundEntity.id, item)
                }
            }
        } else if (listener is ClientPacketListener) {
            ctx.get()!!.enqueueWork {
                val client = Minecraft.getInstance()
                val foundEntity = client.level?.getEntity(packet.entityId)
                val itemStack = packet.entityData
                if (foundEntity != null && foundEntity is FishingHook) {
                    val additionalBobberData = foundEntity as CustomFishingBobberEntityData
                    additionalBobberData.lureItem = itemStack
                }
            }
        }
    }


    class HookPacket {
        var entityId: Int
        var entityData: ItemStack

        constructor(entityId: Int, entityData: ItemStack) {
            this.entityId = entityId
            this.entityData = entityData
        }

        constructor(buffer: FriendlyByteBuf) {
            this.entityId = buffer.readInt()
            this.entityData = buffer.readItem()
        }

        fun encoder(buffer: FriendlyByteBuf) {
            buffer.writeInt(entityId)
            buffer.writeItem(entityData)
        }

        fun handle(ctx: Supplier<NetworkEvent.Context?>) {
            ctx.get()!!.enqueueWork { handle(this, ctx) }
            ctx.get()!!.packetHandled = true
        }
    }
}
