package dev.hybridlabs.aquatic.mixin.client;

import dev.hybridlabs.aquatic.effect.HybridAquaticMobEffects;
import dev.hybridlabs.aquatic.fog.ClarityFogModifier;
import dev.hybridlabs.aquatic.fog.ConduitPowerFogModifier;
import dev.hybridlabs.aquatic.fog.ThalassophobiaFogModifier;
import net.minecraft.client.Camera;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FogType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(FogRenderer.class)
public class FogRendererMixin {

    @Inject(method = "setupFog", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;" +
            "setShaderFogStart(F)V"), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void hybrid$renderFog(Camera camera, FogRenderer.FogMode fogType, float viewDistance,
                                         boolean thickFog, float tickDelta, CallbackInfo ci,
                                         FogType cameraSubmersionType, Entity entity, FogRenderer.FogData fogData) {

        if (entity instanceof LocalPlayer clientPlayerEntity && cameraSubmersionType == FogType.WATER) {
            Level world = clientPlayerEntity.level();
            MobEffectInstance clarityEffect = clientPlayerEntity.getEffect(HybridAquaticMobEffects.INSTANCE.getCLARITY().get());
            MobEffectInstance conduitEffect = clientPlayerEntity.getEffect(MobEffects.CONDUIT_POWER);
            MobEffectInstance thalassophobiaEffect = clientPlayerEntity.getEffect(HybridAquaticMobEffects.INSTANCE.getTHALASSOPHOBIA().get());

            if (clarityEffect != null) {
                new ClarityFogModifier().setupFog(fogData, clientPlayerEntity, clarityEffect, viewDistance, tickDelta);
            } else if (conduitEffect != null) {
                new ConduitPowerFogModifier().setupFog(fogData, clientPlayerEntity, conduitEffect, viewDistance,
                        tickDelta);
            } else if (thalassophobiaEffect != null) {
                new ThalassophobiaFogModifier().setupFog(fogData, clientPlayerEntity, thalassophobiaEffect,
                        viewDistance, tickDelta);
            }
        }
    }
}