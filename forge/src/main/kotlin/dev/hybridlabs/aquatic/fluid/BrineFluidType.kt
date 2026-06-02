package dev.hybridlabs.aquatic.fluid

import dev.hybridlabs.aquatic.Constants
import net.minecraft.resources.ResourceLocation
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions
import net.minecraftforge.fluids.FluidType
import java.util.function.Consumer

class BrineFluidType(properties: Properties) : FluidType(properties) {

    companion object {
        val FLUID_STILL: ResourceLocation =
            ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "block/brine_still")
        val FLUID_FLOWING: ResourceLocation =
            ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "block/brine_flowing")
    }

    override fun initializeClient(consumer: Consumer<IClientFluidTypeExtensions>) {
        consumer.accept(object : IClientFluidTypeExtensions {

            override fun getStillTexture(): ResourceLocation {
                return FLUID_STILL
            }

            override fun getFlowingTexture(): ResourceLocation {
                return FLUID_FLOWING
            }
        })
    }
}