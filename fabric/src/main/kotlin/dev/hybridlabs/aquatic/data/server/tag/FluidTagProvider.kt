package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.fluid.HAPlatformFluids
import dev.hybridlabs.aquatic.tag.HAFluidTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.tags.FluidTags
import java.util.concurrent.CompletableFuture

class FluidTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>):
    FabricTagProvider.FluidTagProvider(output, registriesFuture) {

    override fun addTags(p0: HolderLookup.Provider) {
        getOrCreateTagBuilder(HAFluidTags.BRINE)
            .add(HAPlatformFluids.BRINE_STILL.get())
            .add(HAPlatformFluids.BRINE_FLOWING.get())


        getOrCreateTagBuilder(FluidTags.WATER)
            .add(HAPlatformFluids.BRINE_STILL.get())
            .add(HAPlatformFluids.BRINE_FLOWING.get())
    }
}