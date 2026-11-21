package dev.hybridlabs.aquatic.client.render.particle

import dev.hybridlabs.aquatic.particle.BrineBubbleParticle
import dev.hybridlabs.aquatic.particle.BrineBubblePopParticle
import dev.hybridlabs.aquatic.particle.HAParticleTypes
import dev.hybridlabs.aquatic.particle.SargassumParticle
import dev.hybridlabs.aquatic.particle.VentSmokeParticle
import net.minecraft.client.particle.ParticleResources

/**
 * Registers the particle providers. Vanilla 26.1 hardcodes its providers in `ParticleResources`
 * instead of using a registry, and Fabric no longer ships a particle provider API, so the mixin
 * passes the resources instance in.
 */
object HAParticleRendererRegistry {
    fun register(resources: ParticleResources) {
        resources.register(HAParticleTypes.SARGASSUM.get()) { sprites -> SargassumParticle.Companion.Provider(sprites) }
        resources.register(HAParticleTypes.BRINE_BUBBLE.get()) { sprites -> BrineBubbleParticle.Companion.Provider(sprites) }
        resources.register(HAParticleTypes.BRINE_BUBBLE_POP.get()) { sprites -> BrineBubblePopParticle.Companion.Provider(sprites) }
        resources.register(HAParticleTypes.VENT_SMOKE.get()) { sprites -> VentSmokeParticle.Companion.Provider(sprites) }
    }
}
