package dev.hybridlabs.aquatic.data.server

import dev.hybridlabs.aquatic.HybridAquatic
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.registry.Registries

class BlockLootTableProvider(output: FabricDataOutput) : FabricBlockLootTableProvider(output) {
    override fun generate() {
        // message in a bottle
        // <--

        // generate remaining drops
        Registries.BLOCK
            .filter { block ->
                block.lootTableId !in lootTables && let {
                    val id = Registries.BLOCK.getId(block)
                    id.namespace == HybridAquatic.MOD_ID
                }
            }
            .forEach(::addDrop)
    }
}
