package dev.hybridlabs.aquatic.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;

import dev.hybridlabs.aquatic.client.data.HypnoticEntities;

import net.minecraft.client.MouseHandler;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.world.entity.Mob;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MouseHandler.class)
public class MouseHandlerMixin {
    @WrapOperation(
            method = "turnPlayer()V",
            at =
                    @At(
                            value = "INVOKE",
                            target = "Lnet/minecraft/client/player/LocalPlayer;turn(DD)V"))
    private void doHypnosis(
            LocalPlayer localPlayer, double yRot, double xRot, Operation<Void> original) {
        if (!HypnoticEntities.INSTANCE.getMobs().isEmpty()) {
            Mob closest = null;
            for (Mob candidate : HypnoticEntities.INSTANCE.getMobs()) {
                if (closest == null) {
                    closest = candidate;
                } else {
                    if (localPlayer.distanceToSqr(candidate) < localPlayer.distanceToSqr(closest)) {
                        closest = candidate;
                    }
                }
            }
            localPlayer.lookAt(EntityAnchorArgument.Anchor.EYES, closest.position());
        } else original.call(localPlayer, yRot, xRot);
    }
}
