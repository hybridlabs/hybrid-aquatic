package dev.hybridlabs.aquatic.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;

import dev.hybridlabs.aquatic.client.data.HypnoticEntities;

import net.minecraft.client.MouseHandler;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MouseHandler.class)
public class MouseHandlerMixin {
    @WrapOperation(
            method = "turnPlayer(D)V",
            at =
                    @At(
                            value = "INVOKE",
                            target = "Lnet/minecraft/client/player/LocalPlayer;turn(DD)V"))
    private void doHypnosis(
            LocalPlayer localPlayer, double yRot, double xRot, Operation<Void> original) {
        original.call(localPlayer, yRot, xRot);
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
            nudgeMouse(localPlayer, closest);
        }
    }

    @Unique
    private void nudgeMouse(LocalPlayer localPlayer, Mob target) {
        Vec3 playerPosition = EntityAnchorArgument.Anchor.EYES.apply(localPlayer);
        Vec3 targetPosition = target.position();
        double dX = targetPosition.x - playerPosition.x;
        double dY = targetPosition.y - playerPosition.y;
        double dZ = targetPosition.z - playerPosition.z;
        double distance = Math.sqrt(dX * dX + dZ * dZ);
        float desiredXRot =
                Mth.wrapDegrees(
                        (float) (-(Mth.atan2(dY, distance) * (double) (180F / (float) Math.PI))));
        float desiredYRot =
                Mth.wrapDegrees(
                        (float) (Mth.atan2(dZ, dX) * (double) (180F / (float) Math.PI)) - 90.0F);
        float currentXRot = Mth.wrapDegrees(localPlayer.getXRot());
        float currentYRot = Mth.wrapDegrees(localPlayer.getYRot());

        Vec2 rotationDelta = new Vec2(desiredXRot - currentXRot, desiredYRot - currentYRot);

        Vec2 scaledRotation =
                rotationDelta.normalized().scale(Mth.clamp(rotationDelta.length(), 0, 10) / 10f);

        localPlayer.setXRot(currentXRot + scaledRotation.x);
        localPlayer.setYRot(currentYRot + scaledRotation.y);
        localPlayer.setYHeadRot(localPlayer.getYRot());
    }
}
