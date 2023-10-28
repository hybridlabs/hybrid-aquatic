package dev.hybridlabs.aquatic.mixin.client;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.client.render.FogShape;
import net.minecraft.entity.Entity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BackgroundRenderer.class)
public class BackgroundRendererMixin {

    @Inject(method = "applyFog", at=@At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderFogStart(F)V"), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void hybrid$renderFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci, CameraSubmersionType cameraSubmersionType, Entity entity, BackgroundRenderer.FogData fogData) {

        if (camera.getSubmersionType() == CameraSubmersionType.WATER) {
            fogData.fogStart = -8.0F;

            int topY = entity.getWorld().getSeaLevel();
            float fogStep = (float) (topY - camera.getPos().y) / 10.0f; // as usual modify this end variable for when water fog should reach maximum darkness
            // arg after start is the default fog starting point
            // arg after that one is where the fog ends distance wise
            fogData.fogEnd = MathHelper.lerp(fogStep, 96.0f, 5.0f);
            if (entity instanceof ClientPlayerEntity) {
                ClientPlayerEntity clientPlayerEntity = (ClientPlayerEntity) entity;
                fogData.fogEnd *= Math.max(0.25F, clientPlayerEntity.getUnderwaterVisibility());
                RegistryEntry<Biome> registryEntry = clientPlayerEntity.getWorld().getBiome(clientPlayerEntity.getBlockPos());
                if (registryEntry.isIn(BiomeTags.HAS_CLOSER_WATER_FOG)) {
                    fogData.fogEnd *= 0.85F;
                }
            }

            if (fogData.fogEnd > viewDistance) {
                fogData.fogEnd = viewDistance;
                fogData.fogShape = FogShape.CYLINDER;
            }
        }
    }

}
