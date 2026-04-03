package dev.hybridlabs.aquatic.particle

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes
import net.minecraft.core.particles.ParticleType
import java.util.function.Supplier

object HAFabricParticleTypes {
    val WATER_UP_WHIRL = register("water_up_whirl") { FabricParticleTypes.simple(true) }

    fun <T: ParticleType<*>> register(id: String, particle: Supplier<T>): RegistryObject<T> {
        return CommonClass.PARTICLE_TYPE.register(id,  particle)
    }
}