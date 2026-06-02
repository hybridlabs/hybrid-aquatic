package dev.hybridlabs.aquatic.fluid

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.block.HAPlatformBlocks
import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.level.material.FlowingFluid
import net.minecraft.world.level.pathfinder.BlockPathTypes
import net.minecraftforge.common.SoundActions
import net.minecraftforge.fluids.FluidType
import net.minecraftforge.fluids.ForgeFlowingFluid
import java.util.function.Supplier

object HAForgeFluids {
    private fun brineProperties(): ForgeFlowingFluid.Properties {
        return ForgeFlowingFluid.Properties(BRINE_FLUID_TYPE, BRINE_STILL, BRINE_FLOWING)
            .bucket(HAItems.BRINE_BUCKET)
            .block(HAPlatformBlocks.BRINE.get())
    }

    val BRINE_FLUID_TYPE: RegistryObject<FluidType> = registerFluidType(
        "brine"
    ) {
        BrineFluidType(
            FluidType.Properties.create()
                .density(1024)
                .viscosity(1024)
                .pathType(BlockPathTypes.WATER)
                .adjacentPathType(BlockPathTypes.WATER)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
        )
    }

    val BRINE_STILL: RegistryObject<FlowingFluid> =
        registerFluid("brine_still") { ForgeFlowingFluid.Source(brineProperties()) }

    val BRINE_FLOWING: RegistryObject<FlowingFluid> =
        registerFluid("brine_flowing") { ForgeFlowingFluid.Flowing(brineProperties()) }

    private fun <T: FlowingFluid> registerFluid(id: String, fluid: Supplier<T>): RegistryObject<T> {
        return CommonClass.FLUIDS.register(id, fluid)
    }

    private fun <T: FluidType> registerFluidType(id: String, fluid: Supplier<T>): RegistryObject<T> {
        return CommonClass.FLUID_TYPES.register(id, fluid)
    }
}