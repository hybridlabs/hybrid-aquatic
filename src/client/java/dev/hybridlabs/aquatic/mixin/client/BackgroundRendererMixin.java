package dev.hybridlabs.aquatic.mixin.client;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.client.render.FogShape;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.server.command.WeatherCommand;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BackgroundRenderer.class)
public class BackgroundRendererMixin {

    @Shadow private static float red, green, blue;

//    @Inject(method = "render", at=@At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;clearColor(FFFF)V"), locals = LocalCapture.CAPTURE_FAILHARD)
//    private static void hybrid$fogColor(Camera camera, float tickDelta, ClientWorld world, int viewDistance, float skyDarkness, CallbackInfo ci, CameraSubmersionType cameraSubmersionType, Entity entity) {
//        float f;
//        float g;
//        float h;
//
//        if(cameraSubmersionType == CameraSubmersionType.WATER) {
//            long l = Util.getMeasuringTimeMs();
//            int i = ((Biome)world.getBiome(BlockPos.ofFloored(camera.getPos())).value()).getWaterFogColor();
//            if (lastWaterFogColorUpdateTime < 0L) {
//                waterFogColor = i;
//                nextWaterFogColor = i;
//                lastWaterFogColorUpdateTime = l;
//            }
//
//            int j = waterFogColor >> 16 & 255;
//            int k = waterFogColor >> 8 & 255;
//            int m = waterFogColor & 255;
//            int n = nextWaterFogColor >> 16 & 255;
//            int o = nextWaterFogColor >> 8 & 255;
//            int p = nextWaterFogColor & 255;
//            f = MathHelper.clamp((float)(l - lastWaterFogColorUpdateTime) / 5000.0F, 0.0F, 1.0F);
//            g = MathHelper.lerp(f, (float)n, (float)j);
//            h = MathHelper.lerp(f, (float)o, (float)k);
//            float q = MathHelper.lerp(f, (float)p, (float)m);
//            red = g / 255.0F;
//            green = h / 255.0F;
//            blue = q / 255.0F;
//            if (waterFogColor != i) {
//                waterFogColor = i;
//                nextWaterFogColor = MathHelper.floor(g) << 16 | MathHelper.floor(h) << 8 | MathHelper.floor(q);
//                lastWaterFogColorUpdateTime = l;
//            }
//        }
//    }

    @Inject(method = "applyFog", at=@At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderFogStart(F)V"), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void hybrid$renderFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci, CameraSubmersionType cameraSubmersionType, Entity entity, BackgroundRenderer.FogData fogData) {

        if (entity instanceof ClientPlayerEntity clientPlayerEntity) {

            World world = clientPlayerEntity.getWorld();

            switch (cameraSubmersionType) {

                case LAVA, POWDER_SNOW -> {
                    // Empty cause no real need.
                }
                case WATER -> {
                    fogData.fogStart = -8.0F;

                    int topY = world.getSeaLevel();
                    float fogStep = (float) (topY - camera.getPos().y) / 32.0f; // as usual modify this end variable for when water fog should reach maximum darkness
                    // arg after start is the default fog starting point
                    // arg after that one is where the fog ends distance wise
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
                case NONE -> {

                    RegistryEntry<Biome> biomeEntry = world.getBiome(entity.getBlockPos());

                    if (biomeEntry.isIn(BiomeTags.IS_OCEAN)) {

                    }
                }
            }
        }
    }

    /**
     *
     * @param height
     * @param gradientBegin
     * @param gradientEnd
     * @return
     */
    @Unique private float getSeaLevelGradient(float height, float gradientBegin, float gradientEnd) {
        return 1.0f;
    }
}
