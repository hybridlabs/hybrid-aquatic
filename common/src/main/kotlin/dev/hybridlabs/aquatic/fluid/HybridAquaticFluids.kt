package dev.hybridlabs.aquatic.fluid

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import net.minecraft.world.level.material.Fluid
import java.util.function.Supplier

object HybridAquaticFluids {
    val FLOWING_BRINE = register("flowing_brine") { BrineFluid.Flowing() }
    val BRINE = register("brine") { BrineFluid.Source() }

    private fun register(id: String, fluid: Supplier<Fluid>): RegistryObject<Fluid> {
        return CommonClass.FLUIDS.register(id, fluid)
    }
}