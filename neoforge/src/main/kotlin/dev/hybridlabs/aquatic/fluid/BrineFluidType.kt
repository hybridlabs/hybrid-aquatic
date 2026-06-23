package dev.hybridlabs.aquatic.fluid

import dev.hybridlabs.aquatic.CommonClass
import net.minecraft.resources.ResourceLocation
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions
import net.neoforged.neoforge.fluids.FluidType

class BrineFluidType(properties: Properties) : FluidType(properties), IClientFluidTypeExtensions {

    override fun getStillTexture(): ResourceLocation {
        return CommonClass.locate("block/brine_still")
    }

    override fun getFlowingTexture(): ResourceLocation {
        return CommonClass.locate("block/brine_flowing")
    }

    override fun getOverlayTexture(): ResourceLocation {
        return CommonClass.locate("block/brine_overlay")
    }
}