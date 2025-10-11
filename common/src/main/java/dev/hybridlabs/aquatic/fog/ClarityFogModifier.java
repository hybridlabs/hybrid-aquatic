package dev.hybridlabs.aquatic.fog;

import com.mojang.blaze3d.shaders.FogShape;
import dev.hybridlabs.aquatic.effect.HybridAquaticMobEffects;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class ClarityFogModifier implements FogRenderer.MobEffectFogFunction {
    @Override
    public Holder<MobEffect> getMobEffect() {
        return HybridAquaticMobEffects.INSTANCE.getCLARITY().asHolder();
    }

    @Override
    public void setupFog(FogRenderer.FogData fogData, @NotNull LivingEntity entity, MobEffectInstance effect,
                         float viewDistance, float tickDelta) {

        float fogDistanceMultiplier = 0.5f;

        float f = effect.isInfiniteDuration() ? 5.0F : Mth.lerp(Math.min(1.0F, (float) effect.getDuration() / 20.0F),
                viewDistance, 5.0F);
        fogData.start = f * 32.0F * fogDistanceMultiplier;
        fogData.end = f * 64 * fogDistanceMultiplier;
        fogData.shape = FogShape.SPHERE;
    }
}
