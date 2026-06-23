package dev.hybridlabs.aquatic.fog;

import com.mojang.blaze3d.shaders.FogShape;
import dev.hybridlabs.aquatic.effect.HAMobEffects;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class ThalassophobiaFogModifier implements FogRenderer.MobEffectFogFunction {
    @Override
    public @NotNull Holder<MobEffect> getMobEffect() {
        return HAMobEffects.INSTANCE.getTHALASSOPHOBIA().asHolder();
    }

    @Override
    public void setupFog(FogRenderer.FogData fogData, @NotNull LivingEntity entity, MobEffectInstance effect,
                         float viewDistance, float tickDelta) {

        float fogDistanceMultiplier = 0.5f;

        float f = effect.isInfiniteDuration() ? 5.0F : Mth.lerp(Math.min(1.0F, (float) effect.getDuration() / 20.0F),
                viewDistance, 5.0F);
        fogData.start = f * 4.0F * fogDistanceMultiplier;
        fogData.end = f * 8.0F * fogDistanceMultiplier;
        fogData.shape = FogShape.SPHERE;
    }
}
