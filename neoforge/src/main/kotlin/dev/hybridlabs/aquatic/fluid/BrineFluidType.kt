package dev.hybridlabs.aquatic.fluid

import dev.hybridlabs.aquatic.CommonClass
import net.minecraft.resources.Identifier
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions
import net.neoforged.neoforge.fluids.FluidType

class BrineFluidType(properties: Properties) : FluidType(properties), IClientFluidTypeExtensions {

    override fun getStillTexture(): Identifier {
        return CommonClass.locate("block/brine_still")
    }

    override fun getFlowingTexture(): Identifier {
        return CommonClass.locate("block/brine_flowing")
    }

    override fun getOverlayTexture(): Identifier {
        return CommonClass.locate("block/brine_overlay")
    }
}