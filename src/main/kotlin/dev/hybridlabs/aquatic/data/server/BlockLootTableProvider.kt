package dev.hybridlabs.aquatic.data.server

import dev.hybridlabs.aquatic.HybridAquatic
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.registry.Registries

class BlockLootTableProvider(output: FabricDataOutput) : FabricBlockLootTableProvider(output) {
    override fun generate() {
        Registries.BLOCK
            .filter { block ->
                val id = Registries.BLOCK.getId(block)
                id.namespace == HybridAquatic.MOD_ID
            }
            .forEach(::addDrop)
    }
}
