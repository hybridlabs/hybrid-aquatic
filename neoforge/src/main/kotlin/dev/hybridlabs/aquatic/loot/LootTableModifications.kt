package dev.hybridlabs.aquatic.loot

import net.minecraft.advancements.critereon.EntityPredicate
import net.minecraft.advancements.critereon.FishingHookPredicate
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.Items
import net.minecraft.world.level.storage.loot.BuiltInLootTables
import net.minecraft.world.level.storage.loot.LootContext
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.entries.NestedLootTable.lootTableReference
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.event.LootTableLoadEvent

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
object LootTableModifications {

    @SubscribeEvent
    fun modifyLootTables(event: LootTableLoadEvent) {
        when (event.table.lootTableId) {
            BuiltInLootTables.FISHING_FISH -> {
                event.table = LootTable.lootTable().withPool(
                    LootPool.lootPool().add(LootItem.lootTableItem(Items.COD).setWeight(60))
                        .add(LootItem.lootTableItem(Items.SALMON).setWeight(25))
                        .add(LootItem.lootTableItem(Items.TROPICAL_FISH).setWeight(2))
                        .add(LootItem.lootTableItem(Items.PUFFERFISH).setWeight(13)).add(
                            lootTableReference(
                                ResourceKey.create(
                                    Registries.LOOT_TABLE,
                                    HybridAquaticLootTables.FISHING_DEEP_SEA_FISH_ID
                                )
                            )
                                .setWeight(30).setQuality(1)
                        ).add(
                            lootTableReference(
                                ResourceKey.create(
                                    Registries.LOOT_TABLE,
                                    HybridAquaticLootTables.FISHING_OPEN_OCEAN_FISH_ID
                                )
                            )
                                .setWeight(40).setQuality(1)
                        ).add(
                            lootTableReference(
                                ResourceKey.create(
                                    Registries.LOOT_TABLE,
                                    HybridAquaticLootTables.FISHING_REEF_FISH_ID
                                )
                            )
                                .setWeight(50).setQuality(-1)
                        ).add(
                            lootTableReference(
                                ResourceKey.create(
                                    Registries.LOOT_TABLE,
                                    HybridAquaticLootTables.FISHING_TROPICAL_FRESHWATER_FISH_ID
                                )
                            )
                                .setWeight(30).setQuality(1)

                        ).add(
                            lootTableReference(
                                ResourceKey.create(
                                    Registries.LOOT_TABLE,
                                    HybridAquaticLootTables.FISHING_TREASURE_ID
                                )
                            )
                                .setWeight(10).setQuality(2).`when`(
                                    LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.THIS,
                                        EntityPredicate.Builder.entity()
                                            .subPredicate(FishingHookPredicate.inOpenWater(true))
                                    )
                                )
                        )
                ).build()
            }
        }
    }
}