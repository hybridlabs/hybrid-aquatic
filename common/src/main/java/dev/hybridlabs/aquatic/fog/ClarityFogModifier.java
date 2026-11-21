package dev.hybridlabs.aquatic.fog;

import dev.hybridlabs.aquatic.effect.HAMobEffects;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.environment.MobEffectFogEnvironment;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class ClarityFogModifier extends MobEffectFogEnvironment {
    private static final float FOG_DISTANCE_MULTIPLIER = 0.5F;

    @Override
    public @NotNull Holder<MobEffect> getMobEffect() {
        return HAMobEffects.INSTANCE.getCLARITY().asHolder();
    }

    @Override
    public void setupFog(FogData fogData, Camera camera, ClientLevel level, float viewDistance, DeltaTracker deltaTracker) {
        if (!(camera.entity() instanceof LivingEntity entity)) return;

        MobEffectInstance effect = entity.getEffect(this.getMobEffect());
        if (effect == null) return;

        float f = effect.isInfiniteDuration()
                ? 5.0F
                : Mth.lerp(Math.min(1.0F, (float) effect.getDuration() / 20.0F), viewDistance, 5.0F);

        fogData.environmentalStart = f * 24.0F * FOG_DISTANCE_MULTIPLIER;
        fogData.environmentalEnd = f * 48.0F * FOG_DISTANCE_MULTIPLIER;
        fogData.skyEnd = fogData.environmentalEnd;
        fogData.cloudEnd = fogData.environmentalEnd;
    }
}
