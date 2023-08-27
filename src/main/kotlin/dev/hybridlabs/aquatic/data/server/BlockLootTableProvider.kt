package dev.hybridlabs.aquatic.data.server

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.registry.Registries

class BlockLootTableProvider(output: FabricDataOutput) : FabricBlockLootTableProvider(output) {
    override fun generate() {
        // anemone
        addDrop(HybridAquaticBlocks.ANEMONE) { block ->
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(block))
                        .conditionally(WITH_SILK_TOUCH_OR_SHEARS)
                )
        }

        // message in a bottle
        addDrop(HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE, LootTable.builder())

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
