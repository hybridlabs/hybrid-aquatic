package dev.hybridlabs.aquatic.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import dev.hybridlabs.aquatic.CommonClass;
import dev.hybridlabs.hapi.tag.HAPIBiomeTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
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
	private static ResourceLocation MARINE_SNOW = null;

	@Shadow private int ticks;
	@Final @Shadow private float[] rainSizeX;
	@Final @Shadow private float[] rainSizeZ;
	@Final @Shadow private Minecraft minecraft;

	@Inject(method = "renderSnowAndRain", at=@At("HEAD"))
	void renderWeatherInject(LightTexture lightTexture, float partialTick, double camX, double camY, double camZ, CallbackInfo ci) {
		if (minecraft.player != null && minecraft.level != null) {
			// modernfix loads this class too early
			if (MARINE_SNOW == null) MARINE_SNOW = CommonClass.locate("textures/environment/marine_snow.png");
			
			float f = this.minecraft.level.getRainLevel(partialTick);
			Level world = this.minecraft.level;
			if (f > 0.0f && camY < world.getSeaLevel() && world.getBiome(minecraft.player.blockPosition()).is(HAPIBiomeTags.INSTANCE.getALL_TRENCHES())) {
				lightTexture.turnOnLightLayer();
				int xFloored = Mth.floor(camX);
				int yFloored = Mth.floor(camY);
				int zFloored = Mth.floor(camZ);
                Tesselator tesselator = Tesselator.getInstance();
                BufferBuilder bufferbuilder = null;
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
                float f1 = (float)this.ticks + partialTick;

				for (int n = zFloored - layers; n <= zFloored + layers; ++n) {
					for (int o = xFloored - layers; o <= xFloored + layers; ++o) {
						int p = (n - zFloored + 16) * 32 + o - xFloored + 16;
						double d = (double) this.rainSizeX[p] * 0.5;
						double e = (double) this.rainSizeZ[p] * 0.5;
						mutable.set(o, camY, n);
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
                                if (precipitation == Biome.Precipitation.RAIN) {
									if (m != 0) {

                                        m = 0;
										RenderSystem.setShaderTexture(0, MARINE_SNOW);
                                        bufferbuilder = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.PARTICLE);
									}

                                    float f5 = -((float)(this.ticks + partialTick)) / 768.0F;
                                    float f6 = (float)(random.nextDouble() + (double)f1 * 0.001 * (double)((float)random.nextGaussian()));
                                    float f7 = (float)(random.nextDouble() + (double)(f1 * (float)random.nextGaussian()) * 0.0001);

                                    double dx = (double)o + 0.5 - camX;
                                    double dz = (double)n + 0.5 - camZ;
                                    float distanceFactor = (float)Math.sqrt(dx * dx + dz * dz) / (float)layers;
                                    float fade = Mth.clamp((float)(world.getSeaLevel() - camY) / 48.0f, 0.0f, 0.8f);
                                    float alpha = (((1.0F - distanceFactor * distanceFactor) * 0.3F + 0.5F) * f) * fade;

                                    mutable.set(o, t, n);
                                    int light = getLightColor(world, mutable);
                                    int l3 = light >> 16 & 0xFFFF;
                                    int i4 = light & 0xFFFF;
                                    int j4 = (l3 * 3 + 240) / 4;
                                    int k4 = (i4 * 3 + 240) / 4;

                                    bufferbuilder.addVertex((float)((double)o - camX - d + 0.5), (float)((double)s - camY), (float)((double)n - camZ - e + 0.5))
                                            .setUv(0.0F + f6, (float)r * 0.25F + f5 + f7)
                                            .setColor(1.0F, 1.0F, 1.0F, alpha)
                                            .setUv2(k4, j4);
                                    bufferbuilder.addVertex((float)((double)o - camX + d + 0.5), (float)((double)s - camY), (float)((double)n - camZ + e + 0.5))
                                            .setUv(1.0F + f6, (float)r * 0.25F + f5 + f7)
                                            .setColor(1.0F, 1.0F, 1.0F, alpha)
                                            .setUv2(k4, j4);
                                    bufferbuilder.addVertex((float)((double)o - camX + d + 0.5), (float)((double)r - camY), (float)((double)n - camZ + e + 0.5))
                                            .setUv(1.0F + f6, (float)s * 0.25F + f5 + f7)
                                            .setColor(1.0F, 1.0F, 1.0F, alpha)
                                            .setUv2(k4, j4);
                                    bufferbuilder.addVertex((float)((double)o - camX - d + 0.5), (float)((double)r - camY), (float)((double)n - camZ - e + 0.5))
                                            .setUv(0.0F + f6, (float)s * 0.25F + f5 + f7)
                                            .setColor(1.0F, 1.0F, 1.0F, alpha)
                                            .setUv2(k4, j4);
                                }
							}
						}
					}
				}

                if (m >= 0) {
                    BufferUploader.drawWithShader(bufferbuilder.buildOrThrow());
                }

				RenderSystem.enableCull();
				RenderSystem.enableBlend();
				RenderSystem.enableDepthTest();
				lightTexture.turnOffLightLayer();
			}
		}
	}
}
