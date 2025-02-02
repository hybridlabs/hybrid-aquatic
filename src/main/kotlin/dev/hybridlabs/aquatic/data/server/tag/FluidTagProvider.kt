package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.fluid.HybridAquaticFluids
import dev.hybridlabs.aquatic.tag.HybridAquaticFluidTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.FluidTags
import java.util.concurrent.CompletableFuture

class FluidTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) : FabricTagProvider.FluidTagProvider(output, registriesFuture) {
    override fun configure(arg: RegistryWrapper.WrapperLookup) {
        setOf(
            HybridAquaticFluids.BRINE,
            HybridAquaticFluids.FLOWING_BRINE,
        ).forEach { fluids ->
            getOrCreateTagBuilder(HybridAquaticFluidTags.BRINE).add(fluids)
        }

        setOf(
            HybridAquaticFluids.BRINE,
            HybridAquaticFluids.FLOWING_BRINE,
        ).forEach { fluids ->
            getOrCreateTagBuilder(FluidTags.WATER).add(fluids)
        }
    }
}
