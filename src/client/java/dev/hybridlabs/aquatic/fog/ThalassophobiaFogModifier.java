package dev.hybridlabs.aquatic.fog;

import dev.hybridlabs.aquatic.effect.HybridAquaticStatusEffects;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.math.MathHelper;

public class ThalassophobiaFogModifier implements BackgroundRenderer.StatusEffectFogModifier {
    @Override
    public StatusEffect getStatusEffect() {
        return HybridAquaticStatusEffects.INSTANCE.getTHALASSOPHOBIA();
    }

    @Override
    public void applyStartEndModifier(BackgroundRenderer.FogData fogData, LivingEntity entity, StatusEffectInstance effect, float viewDistance, float tickDelta) {
        float f = effect.isInfinite() ? 5.0F : MathHelper.lerp(Math.min(1.0F, (float)effect.getDuration() / 20.0F), viewDistance, 5.0F);
        if (fogData.fogType == BackgroundRenderer.FogType.FOG_SKY) {
            fogData.fogStart = 0.0F;
            fogData.fogEnd = f * 0.8F;
        } else {
            fogData.fogStart = f * 0.25F;
            fogData.fogEnd = f;
        }
    }
}
