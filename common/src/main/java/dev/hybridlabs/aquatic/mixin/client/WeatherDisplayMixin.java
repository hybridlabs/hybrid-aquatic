package dev.hybridlabs.aquatic.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import dev.hybridlabs.aquatic.CommonClass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.Heightmap;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.client.renderer.LevelRenderer.getLightColor;

@Mixin(LevelRenderer.class)
public abstract class WeatherDisplayMixin implements ResourceManagerReloadListener, AutoCloseable {
	
	@Unique
	private static final ResourceLocation MARINE_SNOW = CommonClass.locate("textures/environment/marine_snow.png");
	
	@Inject(method = "renderSnowAndRain", at=@At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/BufferBuilder;begin(Lcom/mojang/blaze3d/vertex/VertexFormat$Mode;Lcom/mojang/blaze3d/vertex/VertexFormat;)V"))
	void renderWeather(LightTexture manager, float tickDelta, double cameraX, double cameraY, double cameraZ, CallbackInfo ci) {
		RenderSystem.setShaderTexture(0, MARINE_SNOW);
	}
	
	@Shadow private int ticks;
	@Final @Shadow private float[] rainSizeX;
	@Final @Shadow private float[] rainSizeZ;
	@Final @Shadow private Minecraft minecraft;
	
	@Inject(method = "renderSnowAndRain", at=@At("HEAD"))
	void renderWeatherInject(LightTexture manager, float tickDelta, double cameraX, double cameraY, double cameraZ, CallbackInfo ci) {
		if(minecraft.player != null && minecraft.level != null) {
			float f = this.minecraft.level.getRainLevel(tickDelta);
			Level world = this.minecraft.level;
			if (f > 0.0f && cameraY < world.getSeaLevel() - 16.0 && world.getBiome(minecraft.player.blockPosition()).is(BiomeTags.IS_DEEP_OCEAN)) {
				manager.turnOnLightLayer();
				int xFloored = Mth.floor(cameraX);
				int yFloored = Mth.floor(cameraY);
				int zFloored = Mth.floor(cameraZ);
				Tesselator tessellator = Tesselator.getInstance();
				BufferBuilder bufferBuilder = tessellator.getBuilder();
				RenderSystem.disableCull();
				RenderSystem.enableBlend();
				RenderSystem.enableDepthTest();
				int layers = 5;
				if (Minecraft.useFancyGraphics()) {
					layers = 10;
				}
				
				RenderSystem.depthMask(Minecraft.useShaderTransparency());
				int m = -1;
				RenderSystem.setShader(GameRenderer::getParticleShader);
				BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
				
				for (int n = zFloored - layers; n <= zFloored + layers; ++n) {
					for (int o = xFloored - layers; o <= xFloored + layers; ++o) {
						int p = (n - zFloored + 16) * 32 + o - xFloored + 16;
						double d = (double) this.rainSizeX[p] * 0.5;
						double e = (double) this.rainSizeZ[p] * 0.5;
						mutable.set(o, cameraY, n);
						Biome biome = world.getBiome(mutable).value();
						if (biome.hasPrecipitation()) {
							int height = world.getHeight(Heightmap.Types.OCEAN_FLOOR, o, n);
							int r = yFloored - layers;
							int s = yFloored + layers;
							if (r < height) {
								r = height;
							}
							
							if (s < height) {
								s = height;
							}
							
							int t = Math.max(height, yFloored);
							
							if (r != s) {
								RandomSource random = RandomSource.create(((long) o * o * 3121 + o * 45238971L ^ (long) n * n * 418711 + n * 13761L));
								mutable.set(o, r, n);
								Biome.Precipitation precipitation = biome.getPrecipitationAt(mutable);
								float h;
								float y;
								if (precipitation == Biome.Precipitation.RAIN) {
									if (m != 0) {
										if (m >= 0) {
											tessellator.end();
										}
										
										m = 0;
										RenderSystem.setShaderTexture(0, MARINE_SNOW);
										bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.PARTICLE);
									}
									
									int u = this.ticks + o * o * 3121 + o * 45238971 + n * n * 418711 + n * 13761 & 31;
									h = -((float) u + tickDelta) / 512.0F * (3.0F + random.nextFloat());
									double v = (double) o + 1.0 - cameraX;
									double w = (double) n + 1.0 - cameraZ;
									float x = (float) Math.sqrt(v * v + w * w) / (float) layers;
									y = ((1.0F - x * x) * 0.5F + 0.5F) * f;
									mutable.set(o, t, n);
									int z = getLightColor(world, mutable);
									
									float seaLevelDist = Math.max(0.0f, (float) (world.getSeaLevel() - cameraY) / 128.0f);
									float lerpedAlpha = Mth.lerp(seaLevelDist, 0.0f, 1.0f);
									float clampedAlpha = Mth.clamp(y - lerpedAlpha, 0.0f, 1.0f);
									
									bufferBuilder.vertex((double) o - cameraX - d + 0.5, (double) s - cameraY, (double) n - cameraZ - e + 0.5).uv(0.0F, (float) r * 0.25F + h).color(1.0F, 1.0F, 1.0F, clampedAlpha * f).uv2(z).endVertex();
									bufferBuilder.vertex((double) o - cameraX + d + 0.5, (double) s - cameraY, (double) n - cameraZ + e + 0.5).uv(1.0F, (float) r * 0.25F + h).color(1.0F, 1.0F, 1.0F, clampedAlpha * f).uv2(z).endVertex();
									bufferBuilder.vertex((double) o - cameraX + d + 0.5, (double) r - cameraY, (double) n - cameraZ + e + 0.5).uv(1.0F, (float) s * 0.25F + h).color(1.0F, 1.0F, 1.0F, clampedAlpha * f).uv2(z).endVertex();
									bufferBuilder.vertex((double) o - cameraX - d + 0.5, (double) r - cameraY, (double) n - cameraZ - e + 0.5).uv(0.0F, (float) s * 0.25F + h).color(1.0F, 1.0F, 1.0F, clampedAlpha * f).uv2(z).endVertex();
								}
							}
						}
					}
				}
				
				if (m >= 0) {
					tessellator.end();
				}
				
				RenderSystem.enableCull();
				RenderSystem.disableBlend();
				manager.turnOffLightLayer();
			}
		}
	}
}