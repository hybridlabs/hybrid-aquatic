package dev.hybridlabs.aquatic.data.server

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity.Companion.MESSAGE_KEY
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity.Companion.VARIANT_KEY
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.item.BlockItem.BLOCK_ENTITY_TAG_KEY
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.function.CopyNbtLootFunction
import net.minecraft.loot.provider.nbt.ContextLootNbtProvider
import net.minecraft.registry.Registries

class BlockLootTableProvider(output: FabricDataOutput) : FabricBlockLootTableProvider(output) {
    override fun generate() {
        // message in a bottle
        addDrop(HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE) { block ->
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(
                            ItemEntry.builder(block)
                                .apply(
                                    CopyNbtLootFunction.builder(ContextLootNbtProvider.BLOCK_ENTITY)
                                        .withOperation(MESSAGE_KEY, "$BLOCK_ENTITY_TAG_KEY.$MESSAGE_KEY")
                                        .withOperation(VARIANT_KEY, "$BLOCK_ENTITY_TAG_KEY.$VARIANT_KEY")
                                )
                        )
                )
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
