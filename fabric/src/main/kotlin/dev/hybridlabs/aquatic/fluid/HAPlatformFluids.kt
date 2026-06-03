package dev.hybridlabs.aquatic.fluid

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.LiquidBlock
import net.minecraft.world.level.block.state.BlockBehaviour.Properties
import net.minecraft.world.level.material.FlowingFluid
import java.util.function.Supplier

object HAPlatformFluids {
    val BRINE_FLOWING = registerFluid("flowing_brine") { BrineFluid.Flowing() }
    val BRINE_STILL = registerFluid("brine") { BrineFluid.Source() }

    val BRINE = registerFluidBlock("brine") {
        LiquidBlock(
            BRINE_STILL.get(),
            Properties.copy(Blocks.WATER).liquid()
        )
    }


    private fun <T: FlowingFluid> registerFluid(id: String, fluid: Supplier<T>): RegistryObject<T> {
        return CommonClass.FLUIDS.register(id, fluid)
    }

    fun <T : Block> registerFluidBlock(id: String, block: Supplier<T>): RegistryObject<Block> {
        return CommonClass.BLOCKS.register(id, block)
    }
}