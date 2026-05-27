package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import net.minecraft.world.level.material.FlowingFluid
import java.util.function.Supplier

object HAFluids {
    val BRINE_FLOWING = register("flowing_acid") { BrineFluid.Flowing() }
    val BRINE_STILL = register("acid") { BrineFluid.Source() }

    private fun <T: FlowingFluid> register(id: String, fluid: Supplier<T>): RegistryObject<T> {
        return CommonClass.FLUIDS.register(id, fluid)
    }

    fun initialize() {
    }
}