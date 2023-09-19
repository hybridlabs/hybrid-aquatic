package dev.hybridlabs.aquatic.loot_table

import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.fabricmc.fabric.api.loot.v2.LootTableEvents
import net.minecraft.loot.LootTables
import net.minecraft.loot.entry.ItemEntry

object LootTableModifications {
    init {
        LootTableEvents.MODIFY.register { resourceManager, lootManager, id, tableBuilder, source ->
            if (source.isBuiltin && id.equals(LootTables.FISHING_FISH_GAMEPLAY)) {
                // TODO: Replace everything with `.with(LootTableEntry.builder())` and
                //       make datagen with our fishes(should be in gameplay/fishing/fish or something) for this loot table
                tableBuilder.modifyPools { defaultPools ->
                    defaultPools
                        .with(ItemEntry.builder(HybridAquaticItems.LIONFISH).weight(5))
                        .with(ItemEntry.builder(HybridAquaticItems.MAHI_MAHI).weight(3))
                        .with(ItemEntry.builder(HybridAquaticItems.YELLOWFIN_TUNA).weight(3))
                        .with(ItemEntry.builder(HybridAquaticItems.OPAH).weight(3))
                        .with(ItemEntry.builder(HybridAquaticItems.ROCKFISH).weight(5))
                        .with(ItemEntry.builder(HybridAquaticItems.BLUE_SPOTTED_STINGRAY).weight(2))
                        .with(ItemEntry.builder(HybridAquaticItems.MORAY_EEL).weight(2))
                        .with(ItemEntry.builder(HybridAquaticItems.NEEDLEFISH).weight(4))
                        .with(ItemEntry.builder(HybridAquaticItems.PIRANHA).weight(2))
                        .with(ItemEntry.builder(HybridAquaticItems.ANGLERFISH).weight(2))
                        .with(ItemEntry.builder(HybridAquaticItems.BARRELEYE).weight(1))
                        .with(ItemEntry.builder(HybridAquaticItems.BLUE_TANG).weight(4))
                        .with(ItemEntry.builder(HybridAquaticItems.CLOWNFISH).weight(4))
                        .with(ItemEntry.builder(HybridAquaticItems.UNICORN_FISH).weight(4))
                        .with(ItemEntry.builder(HybridAquaticItems.COWFISH).weight(2))
                        .with(ItemEntry.builder(HybridAquaticItems.TRIGGERFISH).weight(3))
                        .with(ItemEntry.builder(HybridAquaticItems.TIGER_BARB).weight(2))
                        .with(ItemEntry.builder(HybridAquaticItems.OSCAR).weight(2))
                }
            }
        }
    }
}