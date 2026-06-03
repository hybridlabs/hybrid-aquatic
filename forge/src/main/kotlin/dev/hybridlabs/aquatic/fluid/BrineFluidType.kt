package dev.hybridlabs.aquatic.fluid

import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions
import net.minecraftforge.fluids.FluidType
import java.util.function.Consumer

class BrineFluidType(properties: Properties) : FluidType(properties) {

    override fun initializeClient(consumer: Consumer<IClientFluidTypeExtensions?>?) {
        super.initializeClient(consumer)
    }


}