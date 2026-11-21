package dev.hybridlabs.aquatic.datagen.server

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.loot.HAGlobalLootModifier
import dev.hybridlabs.aquatic.tag.HAItemTags
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.tags.ItemTags
import net.minecraft.util.random.Weighted
import net.minecraft.util.random.WeightedList
import net.minecraft.world.level.storage.loot.BuiltInLootTables
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider
import java.util.concurrent.CompletableFuture

class HAGlobalLootModifierProvider
    (output: PackOutput, registries: CompletableFuture<HolderLookup.Provider>) :
    GlobalLootModifierProvider(output, registries, Constants.MOD_ID) {
    override fun start() {
        this.add(
            "fishing_fish",
            HAGlobalLootModifier(
                emptyArray<LootItemCondition>(),
                0,
                BuiltInLootTables.FISHING.identifier(),
                ItemTags.FISHES,
                0.5f,
                WeightedList.of(
                    Weighted(CommonClass.locate("gameplay/fishing/ha_large_fish"), 1),
                    Weighted(CommonClass.locate("gameplay/fishing/ha_medium_fish"), 2),
                    Weighted(CommonClass.locate("gameplay/fishing/ha_small_fish"), 4),
                )
            )
        )
        this.add(
            "fishing_treasure",
            HAGlobalLootModifier(
                emptyArray<LootItemCondition>(),
                0,
                BuiltInLootTables.FISHING.identifier(),
                HAItemTags.FISHING_TREASURE,
                0.25f,
                WeightedList.of(
                    Weighted(CommonClass.locate("gameplay/fishing/ha_crates"), 1),
                )
            )
        )
    }
}