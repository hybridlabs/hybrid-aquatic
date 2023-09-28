package dev.hybridlabs.aquatic.data.server.loot

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.loot.HybridAquaticLootTables
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.condition.MatchToolLootCondition
import net.minecraft.loot.entry.AlternativeEntry
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.entry.LootTableEntry
import net.minecraft.predicate.item.ItemPredicate
import net.minecraft.registry.Registries
import net.minecraft.registry.tag.ItemTags

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

        // crate
        addDrop(HybridAquaticBlocks.CRATE) { block ->
            LootTable.builder().pool(
                LootPool.builder().with(
                    AlternativeEntry.builder(
                        LootTableEntry.builder(HybridAquaticLootTables.CRATE_TREASURE_ID)
                            .conditionally(
                                MatchToolLootCondition.builder(
                                    ItemPredicate.Builder.create()
                                        .tag(ItemTags.AXES)
                                )
                            ),
                        ItemEntry.builder(block),
                    )
                ))
        }

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
