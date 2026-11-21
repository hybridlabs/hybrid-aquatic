package dev.hybridlabs.aquatic.datagen.server

import dev.hybridlabs.aquatic.block.HAPlatformBlocks
import java.util.concurrent.CompletableFuture
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.DataMapProvider
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps
import net.neoforged.neoforge.registries.datamaps.builtin.Transformable

/**
 * Generates the block transform data maps, which NeoForge uses for stripping and the other tool actions.
 */
class HADataMapProvider(
    output: PackOutput,
    lookupProvider: CompletableFuture<HolderLookup.Provider>,
) : DataMapProvider(output, lookupProvider) {
    override fun gather(provider: HolderLookup.Provider) {
        val transformables = builder(NeoForgeDataMaps.TRANSFORMABLES)

        transformables.add(
            HAPlatformBlocks.DRIFTWOOD_LOG.get().builtInRegistryHolder(),
            Transformable.stripping(
                HAPlatformBlocks.DRIFTWOOD_LOG.get(),
                HAPlatformBlocks.STRIPPED_DRIFTWOOD_LOG.get()
            ),
            false
        )

        transformables.add(
            HAPlatformBlocks.DRIFTWOOD_WOOD.get().builtInRegistryHolder(),
            Transformable.stripping(
                HAPlatformBlocks.DRIFTWOOD_WOOD.get(),
                HAPlatformBlocks.STRIPPED_DRIFTWOOD_WOOD.get()
            ),
            false
        )
    }
}
