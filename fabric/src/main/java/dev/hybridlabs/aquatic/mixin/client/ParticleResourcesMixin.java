package dev.hybridlabs.aquatic.mixin.client;

import dev.hybridlabs.aquatic.client.render.particle.HAParticleRendererRegistry;
import net.minecraft.client.particle.ParticleResources;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Vanilla registers its particle providers in a private method with no registry to hook into, so
 * the aquatic providers are appended to the same method.
 */
@Mixin(ParticleResources.class)
public class ParticleResourcesMixin {

    @Inject(method = "registerProviders", at = @At("TAIL"))
    private void hybrid$registerAquaticParticles(CallbackInfo ci) {
        HAParticleRendererRegistry.INSTANCE.register((ParticleResources) (Object) this);
    }
}
