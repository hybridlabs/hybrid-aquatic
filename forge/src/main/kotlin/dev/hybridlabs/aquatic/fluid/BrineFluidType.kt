package dev.hybridlabs.aquatic.fluid

import dev.hybridlabs.aquatic.CommonClass
import net.minecraft.resources.ResourceLocation
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions
import net.minecraftforge.fluids.FluidType
import java.util.function.Consumer

class BrineFluidType(properties: Properties) : FluidType(properties) {

    override fun initializeClient(consumer: Consumer<IClientFluidTypeExtensions>) {
        consumer.accept(object : IClientFluidTypeExtensions {
            override fun getStillTexture(): ResourceLocation {
                return CommonClass.locate("brine_still")
            }

            override fun getFlowingTexture(): ResourceLocation {
                return CommonClass.locate("brine_flowing")
            }

            override fun getOverlayTexture(): ResourceLocation {
                return CommonClass.locate("brine_still")
            }
        })
    }
}