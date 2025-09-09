package dev.hybridlabs.aquatic.mixin.client;

import dev.hybridlabs.aquatic.network.HybridAquaticNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.FishingHook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FishingHook.class)
public abstract class FishingBobberEntityClientMixin {

    // Sends a packet that asks server to send custom lure item of fishing bobber after a spawning packet
    @Inject(method = "recreateFromPacket", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity" +
            "/projectile/FishingHook;getPlayerOwner()Lnet/minecraft/world/entity/player/Player;"))
    private void test(ClientboundAddEntityPacket packet, CallbackInfo ci) {
        FriendlyByteBuf packetData = PacketByteBufs.create();
        packetData.writeInt(packet.getId());

        ResourceLocation packetId = HybridAquaticNetworking.INSTANCE.getFISHING_BOBBER_LURE();

        if (ClientPlayNetworking.canSend(packetId))
            ClientPlayNetworking.send(packetId, packetData);
    }
}
