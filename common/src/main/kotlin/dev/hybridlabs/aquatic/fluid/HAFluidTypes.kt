package dev.hybridlabs.aquatic.fluid

import dev.hybridlabs.aquatic.CommonClass
import net.minecraftforge.fluids.FluidType

object HAFluidTypes {
    val BRINE = CommonClass.FLUID_TYPES.register("brine") {
        FluidType(
            FluidType.Properties.create()
        )
    }
}