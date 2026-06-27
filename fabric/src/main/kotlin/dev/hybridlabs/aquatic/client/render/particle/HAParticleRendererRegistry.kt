package dev.hybridlabs.aquatic.client.render.particle

import dev.hybridlabs.aquatic.particle.BrineBubbleParticle
import dev.hybridlabs.aquatic.particle.BrineBubblePopParticle
import dev.hybridlabs.aquatic.particle.HAParticleTypes
import dev.hybridlabs.aquatic.particle.SargassumParticle
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry

object HAParticleRendererRegistry {
    private val particleFactoryRegistry = ParticleFactoryRegistry.getInstance()

    init {
        particleFactoryRegistry.register(HAParticleTypes.SARGASSUM.get()) { sprites ->
            SargassumParticle.Companion.Provider(sprites)
        }
        particleFactoryRegistry.register(HAParticleTypes.BRINE_BUBBLE.get()) { sprites ->
            BrineBubbleParticle.Companion.Provider(sprites)
        }
        particleFactoryRegistry.register(HAParticleTypes.BRINE_BUBBLE_POP.get()) { sprites ->
            BrineBubblePopParticle.Companion.Provider(sprites)
        }
    }
}