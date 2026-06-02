package dev.hybridlabs.aquatic.client.render.fluid

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.fluid.HAPlatformFluids
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler
import net.minecraft.client.renderer.RenderType
import net.minecraft.world.level.material.FlowingFluid

class HAFluidRenderer {
    init {
        registerFluid("brine", HAPlatformFluids.BRINE_STILL.get(), HAPlatformFluids.BRINE_FLOWING.get())
    }

    fun registerFluid(id: String, still: FlowingFluid, flowing: FlowingFluid) {
        FluidRenderHandlerRegistry.INSTANCE.register(
            still, flowing, SimpleFluidRenderHandler(
                CommonClass.locate("block/${id}_still"),
                CommonClass.locate("block/${id}_flowing"),
                0xFFFFFF
            )
        )
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.solid(), still, flowing)
    }
}