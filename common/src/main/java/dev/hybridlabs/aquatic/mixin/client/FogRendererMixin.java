package dev.hybridlabs.aquatic.mixin.client;

import com.mojang.blaze3d.shaders.FogShape;
import dev.hybridlabs.aquatic.effect.HybridAquaticMobEffects;
import dev.hybridlabs.aquatic.fog.ClarityFogModifier;
import dev.hybridlabs.aquatic.fog.ConduitPowerFogModifier;
import dev.hybridlabs.aquatic.fog.ThalassophobiaFogModifier;
import net.minecraft.client.Camera;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
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
            } else {
                fogData.start = -8.0F;
                int topY = world.getSeaLevel();
                float fogStep = (float) (topY - camera.getPosition().y) / 64.0f;
                fogData.end = Mth.lerp(fogStep, 80.0f, -64.0f);
                fogData.end *= Math.max(0.5F, clientPlayerEntity.getWaterVision());
                Holder<Biome> registryEntry = world.getBiome(clientPlayerEntity.blockPosition());
                if (registryEntry.is(BiomeTags.HAS_CLOSER_WATER_FOG)) {
                    fogData.end *= 1.0F;
                }

                if (fogData.end > viewDistance) {
                    fogData.end = viewDistance;
                    fogData.shape = FogShape.SPHERE;
                }
                fogData.end = Math.max(fogData.end, 16.0f);
            }
        }
    }
}