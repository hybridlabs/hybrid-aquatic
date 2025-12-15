package dev.hybridlabs.aquatic.datagen

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.loot.HAGlobalLootModifier
import dev.hybridlabs.aquatic.tag.HybridAquaticItemTags
import net.minecraft.data.PackOutput
import net.minecraft.tags.ItemTags
import net.minecraft.util.random.WeightedEntry
import net.minecraft.util.random.WeightedRandomList
import net.minecraft.world.level.storage.loot.BuiltInLootTables
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition
import net.minecraftforge.common.data.GlobalLootModifierProvider
import net.minecraftforge.common.loot.IGlobalLootModifier


class HAGlobalLootModifierProvider
    (output: PackOutput) :
    GlobalLootModifierProvider(output, Constants.MOD_ID) {
    override fun start() {
        this.add<IGlobalLootModifier>(
            "fishing_fish",
            HAGlobalLootModifier(
                emptyArray<LootItemCondition>(),
                BuiltInLootTables.FISHING,
                ItemTags.FISHES,
                0.5f,
                WeightedRandomList.create(
                    WeightedEntry.wrap(CommonClass.locate("gameplay/fishing/ha_large_fish"), 1),
                    WeightedEntry.wrap(CommonClass.locate("gameplay/fishing/ha_medium_fish"), 2),
                    WeightedEntry.wrap(CommonClass.locate("gameplay/fishing/ha_small_fish"), 4),
                )
            )
        )
        this.add<IGlobalLootModifier>(
            "fishing_treasure",
            HAGlobalLootModifier(
                emptyArray<LootItemCondition>(),
                BuiltInLootTables.FISHING,
                HybridAquaticItemTags.FISHING_TREASURE,
                0.25f,
                WeightedRandomList.create(
                    WeightedEntry.wrap(CommonClass.locate("gameplay/fishing/ha_crates"), 1),
                )
            )
        )
    }
}
