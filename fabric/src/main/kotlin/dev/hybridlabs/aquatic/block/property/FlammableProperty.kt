package dev.hybridlabs.aquatic.block.property

import dev.hybridlabs.aquatic.block.HAPlatformBlocks
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry

object FlammableProperty {
    val registry: FlammableBlockRegistry = FlammableBlockRegistry.getDefaultInstance()

    init {
        registry.add(HAPlatformBlocks.DUNEGRASS.get(), 60, 100)
        registry.add(HAPlatformBlocks.TALL_DUNEGRASS.get(), 60, 100)
        registry.add(HAPlatformBlocks.CATTAIL.get(), 60, 100)
        // same as vanilla logs
        registry.add(HAPlatformBlocks.DRIFTWOOD_LOG.get(), 5, 5)
        registry.add(HAPlatformBlocks.STRIPPED_DRIFTWOOD_LOG.get(), 5, 5)
        registry.add(HAPlatformBlocks.DRIFTWOOD_WOOD.get(), 5, 5)
        registry.add(HAPlatformBlocks.STRIPPED_DRIFTWOOD_WOOD.get(), 5, 5)
        // same as vanilla cut wood
        registry.add(HAPlatformBlocks.DRIFTWOOD_PLANKS.get(), 5, 20)
        registry.add(HAPlatformBlocks.DRIFTWOOD_SLAB.get(), 5, 20)
        registry.add(HAPlatformBlocks.DRIFTWOOD_FENCE.get(), 5, 20)
        registry.add(HAPlatformBlocks.DRIFTWOOD_FENCE_GATE.get(), 5, 20)
    }
}