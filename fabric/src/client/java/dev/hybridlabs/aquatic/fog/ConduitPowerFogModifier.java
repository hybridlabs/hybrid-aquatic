package dev.hybridlabs.aquatic.fog;

import dev.hybridlabs.aquatic.effect.HybridAquaticStatusEffects;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.FogShape;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.MathHelper;

public class ConduitPowerFogModifier implements BackgroundRenderer.StatusEffectFogModifier {
    @Override
    public StatusEffect getStatusEffect() {
        return StatusEffects.CONDUIT_POWER;
    }

    @Override
    public void applyStartEndModifier(BackgroundRenderer.FogData fogData, LivingEntity entity, StatusEffectInstance effect, float viewDistance, float tickDelta) {

        float fogDistanceMultiplier = 0.5f;

        float f = effect.isInfinite() ? 5.0F : MathHelper.lerp(Math.min(1.0F, (float) effect.getDuration() / 20.0F), viewDistance, 5.0F);
        fogData.fogStart = f * 16.0F * fogDistanceMultiplier;
        fogData.fogEnd = f * 24.0F * fogDistanceMultiplier;
        fogData.fogShape = FogShape.SPHERE;
    }
}
