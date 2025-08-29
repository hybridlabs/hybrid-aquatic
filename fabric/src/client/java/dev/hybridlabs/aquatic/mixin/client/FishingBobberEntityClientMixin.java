package dev.hybridlabs.aquatic.mixin.client;

import dev.hybridlabs.aquatic.network.HybridAquaticNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FishingBobberEntity.class)
public abstract class FishingBobberEntityClientMixin {
  
  // Sends a packet that asks server to send custom lure item of fishing bobber after a spawning packet
  @Inject(method = "onSpawnPacket", at = @At(
    value = "INVOKE", target = "Lnet/minecraft/entity/projectile/FishingBobberEntity;getPlayerOwner()Lnet/minecraft/entity/player/PlayerEntity;"
  ))
  private void test(EntitySpawnS2CPacket packet, CallbackInfo ci) {
    PacketByteBuf packetData = PacketByteBufs.create();
    packetData.writeInt(packet.getId());
    
    Identifier packetId = HybridAquaticNetworking.INSTANCE.getFISHING_BOBBER_LURE();
    
    if(ClientPlayNetworking.canSend(packetId)) ClientPlayNetworking.send(packetId, packetData);
  }
}
