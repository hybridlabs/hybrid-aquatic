package dev.hybridlabs.aquatic.fluid

import dev.hybridlabs.aquatic.item.HAPlatformItems
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.LiquidBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.material.Fluid
import net.minecraft.world.level.material.FluidState
import net.minecraftforge.fluids.FluidType

abstract class BrineFluid : CommonBrineFluid() {

    override fun getFlowing(): Fluid {
        return HAPlatformFluids.BRINE_FLOWING.get()
    }

    override fun getSource(): Fluid {
        return HAPlatformFluids.BRINE_STILL.get()
    }

    override fun getBucket(): Item {
        return HAPlatformItems.BRINE_BUCKET.get()
    }

    override fun createLegacyBlock(state: FluidState): BlockState {
        return HAPlatformFluids.BRINE.get().defaultBlockState()
            .setValue(LiquidBlock.LEVEL, getLegacyLevel(state))
    }

    override fun getFluidType(): FluidType {
        return HAPlatformFluids.BRINE_FLUIDTYPE.get()
    }

    class Flowing : BrineFluid() {
        override fun createFluidStateDefinition(builder: StateDefinition.Builder<Fluid?, FluidState?>) {
            super.createFluidStateDefinition(builder)
            builder.add(LEVEL)
        }

        override fun getAmount(state: FluidState): Int {
            return state.getValue(LEVEL)
        }

        override fun isSource(state: FluidState): Boolean {
            return false
        }
    }

    class Source : BrineFluid() {
        override fun getAmount(state: FluidState): Int {
            return 8
        }

        override fun isSource(state: FluidState): Boolean {
            return true
        }
    }
}