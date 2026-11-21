package dev.hybridlabs.aquatic.client.render.fluid

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.fluid.HAPlatformFluids
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderingRegistry
import net.minecraft.client.renderer.block.FluidModel
import net.minecraft.client.resources.model.sprite.Material
import net.minecraft.world.level.material.FlowingFluid

class HAFluidRenderer {
    init {
        registerFluid("brine", HAPlatformFluids.BRINE_STILL.get(), HAPlatformFluids.BRINE_FLOWING.get())
    }

    fun registerFluid(id: String, still: FlowingFluid, flowing: FlowingFluid) {
        FluidRenderingRegistry.register(
            still,
            flowing,
            FluidModel.Unbaked(
                Material(CommonClass.locate("block/${id}_still")),
                Material(CommonClass.locate("block/${id}_flowing")),
                Material(CommonClass.locate("block/${id}_overlay")),
                null
            )
        )
    }
}
