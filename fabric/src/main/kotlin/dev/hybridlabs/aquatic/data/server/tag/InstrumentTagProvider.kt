package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.item.instrument.HAInstruments
import dev.hybridlabs.aquatic.tag.HAInstrumentTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.Instrument
import java.util.concurrent.CompletableFuture

class InstrumentTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider<Instrument>(output, Registries.INSTRUMENT ,registriesFuture) {

    override fun addTags(arg: HolderLookup.Provider) {
        getOrCreateTagBuilder(HAInstrumentTags.OMINOUS_CONCH)
            .add(HAInstruments.OMINOUS_CONCH_HORN.get())
    }
}