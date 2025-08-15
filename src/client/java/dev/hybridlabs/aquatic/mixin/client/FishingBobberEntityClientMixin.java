package dev.hybridlabs.aquatic.mixin.client;

import dev.hybridlabs.aquatic.network.FishingBobberLurePacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FishingBobberEntity.class)
public abstract class FishingBobberEntityClientMixin {
  
  // Sends a packet that asks server to send custom lure item of fishing bobber after a spawning packet
  @Inject(method = "onSpawnPacket", at = @At(
          value = "INVOKE",
          target = "Lnet/minecraft/entity/projectile/FishingBobberEntity;getPlayerOwner()Lnet/minecraft/entity/player/PlayerEntity;"
  ))
  private void sendLureRequest(EntitySpawnS2CPacket packet, CallbackInfo ci) {
    int entityId = packet.getEntityId();
    if (ClientPlayNetworking.canSend(FishingBobberLurePacket.Companion.getID())) {
      ClientPlayNetworking.send(new FishingBobberLurePacket(entityId, ItemStack.EMPTY));
    }
  }
}
