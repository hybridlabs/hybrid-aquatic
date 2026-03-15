package dev.hybridlabs.aquatic.fluid

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import net.minecraft.world.level.material.Fluid
import java.util.function.Supplier

object HybridAquaticFluids {
    val FLOWING_BRINE = register("flowing_brine") { BrineFluid.Flowing() }
    val BRINE_SOURCE = register("brine_source") { BrineFluid.Source() }

    private fun <T: Fluid> register(id: String, fluid: Supplier<T>): RegistryObject<T> {
        return CommonClass.FLUIDS.register(id, fluid)
    }
}