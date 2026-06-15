package dev.hybridlabs.aquatic.mixin.client;

import dev.hybridlabs.aquatic.particle.*;
import dev.hybridlabs.aquatic.particle.BrineBubbleParticle;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ParticleEngine.class)
public abstract class ParticleEngineMixin {
	
	@Shadow
	protected abstract <T extends ParticleOptions> void register(ParticleType<T> particleType, ParticleProvider<T> particleFactory);
	
	@Shadow
	protected abstract <T extends ParticleOptions> void register(ParticleType<T> particleType, ParticleEngine.SpriteParticleRegistration<T> particleMetaFactory);
	
	@Shadow
	protected abstract <T extends ParticleOptions> void register(ParticleType<T> particleType, ParticleProvider.Sprite<T> sprite);
	
	@Inject(
			method = "registerProviders",
			at = @At("TAIL"))
	private void registerHAProviders(CallbackInfo ci) {
		register(HAParticleTypes.INSTANCE.getSARGASSUM().get(), spriteSets -> new SargassumParticle.Companion.Provider(spriteSets));
		register(HAParticleTypes.INSTANCE.getBRINE_BUBBLE().get(), spriteSets -> new BrineBubbleParticle.Companion.Provider(spriteSets));
		register(HAParticleTypes.INSTANCE.getBRINE_BUBBLE_POP().get(), spriteSets -> new BrineBubblePopParticle.Companion.Provider(spriteSets));
	}
}
