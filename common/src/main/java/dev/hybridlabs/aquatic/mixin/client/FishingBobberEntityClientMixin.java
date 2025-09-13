package dev.hybridlabs.aquatic.mixin.client;

import dev.hybridlabs.aquatic.platform.Services;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
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
        Services.PLATFORM.sendHookToServer(packet.getId(), ItemStack.EMPTY);
    }
}
