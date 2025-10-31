package dev.hybridlabs.aquatic.loot

import net.minecraft.advancements.critereon.EntityPredicate
import net.minecraft.advancements.critereon.FishingHookPredicate
import net.minecraft.world.item.Items
import net.minecraft.world.level.storage.loot.BuiltInLootTables
import net.minecraft.world.level.storage.loot.LootContext
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.entries.LootTableReference
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition
import net.minecraftforge.event.LootTableLoadEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
object LootTableModifications {

    @SubscribeEvent
    fun modifyLootTables(event: LootTableLoadEvent) {
        when (event.table.lootTableId) {
            BuiltInLootTables.FISHING_FISH -> {
                event.table = LootTable.lootTable().withPool(
                    LootPool.lootPool().add(LootItem.lootTableItem(Items.COD).setWeight(60))
                        .add(LootItem.lootTableItem(Items.SALMON).setWeight(25))
                        .add(LootItem.lootTableItem(Items.TROPICAL_FISH).setWeight(2))
                        .add(LootItem.lootTableItem(Items.PUFFERFISH).setWeight(13))
                        .add(
                            LootTableReference.lootTableReference(HybridAquaticLootTables.HA_CRATES)
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