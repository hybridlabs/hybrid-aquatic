package dev.hybridlabs.aquatic.mixin.client;

import dev.hybridlabs.aquatic.effect.HybridAquaticStatusEffects;
import dev.hybridlabs.aquatic.fog.ClarityFogModifier;
import dev.hybridlabs.aquatic.fog.ConduitPowerFogModifier;
import dev.hybridlabs.aquatic.fog.ThalassophobiaFogModifier;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.client.render.FogShape;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(BackgroundRenderer.class)
public class BackgroundRendererMixin {

    @Shadow
    private static float red, green, blue;
    @Mutable
    @Final
    @Shadow
    private static List<BackgroundRenderer.StatusEffectFogModifier> FOG_MODIFIERS;

    @Inject(method = "applyFog", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderFogStart(F)V"), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void hybrid$renderFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci, CameraSubmersionType cameraSubmersionType, Entity entity, BackgroundRenderer.FogData fogData) {

        if (entity instanceof ClientPlayerEntity clientPlayerEntity && cameraSubmersionType == CameraSubmersionType.WATER) {
            World world = clientPlayerEntity.getWorld();
            StatusEffectInstance clarityEffect = clientPlayerEntity.getStatusEffect(HybridAquaticStatusEffects.INSTANCE.getCLARITY());
            StatusEffectInstance conduitEffect = clientPlayerEntity.getStatusEffect(StatusEffects.CONDUIT_POWER);
            StatusEffectInstance thalassophobiaEffect = clientPlayerEntity.getStatusEffect(HybridAquaticStatusEffects.INSTANCE.getTHALASSOPHOBIA());

            if (clarityEffect != null) {
                new ClarityFogModifier().applyStartEndModifier(fogData, clientPlayerEntity, clarityEffect, viewDistance, tickDelta);
            } else if (conduitEffect != null) {
                new ConduitPowerFogModifier().applyStartEndModifier(fogData, clientPlayerEntity, conduitEffect, viewDistance, tickDelta);
            } else if (thalassophobiaEffect != null) {
                new ThalassophobiaFogModifier().applyStartEndModifier(fogData, clientPlayerEntity, thalassophobiaEffect, viewDistance, tickDelta);
            } else {
                fogData.fogStart = -8.0F;
                int topY = world.getSeaLevel();
                float fogStep = (float) (topY - camera.getPos().y) / 32.0f;
                fogData.fogEnd = MathHelper.lerp(fogStep, 80.0f, 12.0f);
                fogData.fogEnd *= Math.max(0.25F, clientPlayerEntity.getUnderwaterVisibility());
                RegistryEntry<Biome> registryEntry = world.getBiome(clientPlayerEntity.getBlockPos());
                if (registryEntry.isIn(BiomeTags.HAS_CLOSER_WATER_FOG)) {
                    fogData.fogEnd *= 1.0F;
                }

                if (fogData.fogEnd > viewDistance) {
                    fogData.fogEnd = viewDistance;
                    fogData.fogShape = FogShape.SPHERE;
                }
                fogData.fogEnd = Math.max(fogData.fogEnd, 12.0f);
            }
        }
    }
}