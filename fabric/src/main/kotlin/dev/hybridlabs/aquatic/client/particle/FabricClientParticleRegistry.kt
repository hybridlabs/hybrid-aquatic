package dev.hybridlabs.aquatic.client.particle

import dev.hybridlabs.aquatic.particle.HAParticleTypes.WATER_UP_WHIRL
import dev.hybridlabs.aquatic.particle.WaterUpWhirlParticle
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry

object FabricClientParticleRegistry {

    init {
        ParticleFactoryRegistry.getInstance().register(WATER_UP_WHIRL.get()) { sprite -> WaterUpWhirlParticle.Companion.Provider(sprite) }
    }
}