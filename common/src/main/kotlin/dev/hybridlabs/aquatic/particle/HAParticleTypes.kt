package dev.hybridlabs.aquatic.particle

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import net.minecraft.core.particles.ParticleType
import net.minecraft.core.particles.SimpleParticleType
import java.util.function.Supplier

object HAParticleTypes {
    val WATER_UP_WHIRL = register("water_up_whirl") { SimpleParticleType(true) }
    val SARGASSUM = register("sargassum") { SimpleParticleType(true) }

    fun <T: ParticleType<SimpleParticleType>> register(id: String, particle: Supplier<T>): RegistryObject<T> {
        return CommonClass.PARTICLE_TYPE.register(id,  particle)
    }
}