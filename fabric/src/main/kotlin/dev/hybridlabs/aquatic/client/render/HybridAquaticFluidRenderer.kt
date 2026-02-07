package dev.hybridlabs.aquatic.client.render

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.fluid.HybridAquaticFluids
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler
import net.minecraft.client.renderer.RenderType
import net.minecraft.world.level.material.FlowingFluid
import net.minecraft.world.level.material.Fluid

class HybridAquaticFluidRenderer {
    init {
        registerFluid("brine", HybridAquaticFluids.BRINE.get(), HybridAquaticFluids.FLOWING_BRINE.get())
    }

    fun registerFluid(id: String, still: Fluid, flowing: Fluid) {
        FluidRenderHandlerRegistry.INSTANCE.register(still, flowing, SimpleFluidRenderHandler(
            CommonClass.locate("block/${id}_still"), CommonClass.locate("block/${id}_flowing"), 0xAADDFF
        ))
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), still, flowing)
    }
}