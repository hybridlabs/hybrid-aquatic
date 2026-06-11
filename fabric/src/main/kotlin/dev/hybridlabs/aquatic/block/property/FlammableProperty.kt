package dev.hybridlabs.aquatic.block.property

import dev.hybridlabs.aquatic.block.HAPlatformBlocks
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.minecraft.world.level.block.Block

object FlammableProperty {
    val registry: FlammableBlockRegistry = FlammableBlockRegistry.getDefaultInstance()

    init {
        registerGrass(HAPlatformBlocks.DUNEGRASS.get())
        registerGrass(HAPlatformBlocks.TALL_DUNEGRASS.get())
        registerGrass(HAPlatformBlocks.CATTAIL.get())

        registerLogs(HAPlatformBlocks.DRIFTWOOD_LOG.get())
        registerLogs(HAPlatformBlocks.STRIPPED_DRIFTWOOD_LOG.get())
        registerLogs(HAPlatformBlocks.DRIFTWOOD_WOOD.get())
        registerLogs(HAPlatformBlocks.STRIPPED_DRIFTWOOD_WOOD.get())

        registerCutWood(HAPlatformBlocks.DRIFTWOOD_PLANKS.get())
        registerCutWood(HAPlatformBlocks.DRIFTWOOD_SLAB.get())
        registerCutWood(HAPlatformBlocks.DRIFTWOOD_FENCE.get())
        registerCutWood(HAPlatformBlocks.DRIFTWOOD_FENCE_GATE.get())
    }

    fun registerGrass(input: Block) {
        registry.add(input, 60, 100)
    }

    fun registerLogs(input: Block) {
        registry.add(input, 5, 5)
    }

    fun registerCutWood(input: Block) {
        registry.add(input, 5, 20)
    }
}