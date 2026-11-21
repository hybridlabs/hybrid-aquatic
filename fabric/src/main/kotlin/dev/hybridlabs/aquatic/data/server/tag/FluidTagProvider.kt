package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.fluid.HAPlatformFluids
import dev.hybridlabs.aquatic.tag.HAFluidTags
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider
import net.minecraft.core.HolderLookup
import net.minecraft.tags.FluidTags
import java.util.concurrent.CompletableFuture

class FluidTagProvider(output: FabricPackOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>):
    FabricTagsProvider.FluidTagsProvider(output, registriesFuture) {

    override fun addTags(p0: HolderLookup.Provider) {
        valueLookupBuilder(HAFluidTags.BRINE)
            .add(HAPlatformFluids.BRINE_STILL.get())
            .add(HAPlatformFluids.BRINE_FLOWING.get())


        valueLookupBuilder(FluidTags.WATER)
            .add(HAPlatformFluids.BRINE_STILL.get())
            .add(HAPlatformFluids.BRINE_FLOWING.get())
    }
}