package dev.hybridlabs.aquatic.particle

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import net.minecraft.core.particles.ParticleType
import net.minecraft.core.particles.SimpleParticleType
import java.util.function.Supplier

object HAParticleTypes {
    val SARGASSUM = register("sargassum") { SimpleParticleType(true) }
    val BRINE_BUBBLE = register("brine_bubble") { SimpleParticleType(true) }
    val BRINE_BUBBLE_POP = register("brine_bubble_pop") { SimpleParticleType(true) }

    fun <T: ParticleType<SimpleParticleType>> register(id: String, particle: Supplier<T>): RegistryObject<T> {
        return CommonClass.PARTICLE_TYPE.register(id,  particle)
    }
}