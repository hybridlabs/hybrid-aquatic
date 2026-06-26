package dev.hybridlabs.aquatic.data.server.loot

import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.item.HybridAquaticPlatformItems
import dev.hybridlabs.aquatic.loot.HybridAquaticLootTables
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Items
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator
import java.util.function.BiConsumer

class GenericLootTableProvider(output: FabricDataOutput) :
    SimpleFabricLootTableProvider(output, LootContextParamSets.ALL_PARAMS) {
    override fun generate(exporter: BiConsumer<ResourceLocation, LootTable.Builder>) {
        fun universalCratePool(): LootPool.Builder {
            return LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0f))
                .add(LootItem.lootTableItem(HybridAquaticItems.BARBED_HOOK.get()))
                .add(LootItem.lootTableItem(HybridAquaticItems.GLOWING_HOOK.get()))
                .add(LootItem.lootTableItem(HybridAquaticItems.MAGNETIC_HOOK.get()))
                .add(LootItem.lootTableItem(Items.IRON_INGOT)
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(3f, 9f))))
                .add(LootItem.lootTableItem(Items.GOLD_INGOT)
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(3f, 9f))))
                .add(LootItem.lootTableItem(Items.DIAMOND)
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
                .add(LootItem.lootTableItem(Items.EMERALD)
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f))))
                .add(LootItem.lootTableItem(Items.SKULL_BANNER_PATTERN))
                .add(LootItem.lootTableItem(Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE)
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 4f))))
        }

        fun driftwoodPool(): LootPool.Builder {
            return LootPool.lootPool()
                .`when`(LootItemRandomChanceCondition.randomChance(0.5f))
                .add(LootItem.lootTableItem(HybridAquaticPlatformItems.DRIFTWOOD_LOG.get())
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(16f, 32f))))
        }

        fun plushiePool(): LootPool.Builder {
            return LootPool.lootPool()
                .`when`(LootItemRandomChanceCondition.randomChance(0.25f))
                .add(LootItem.lootTableItem(HybridAquaticItems.GREAT_WHITE_SHARK_PLUSHIE.get()))
                .add(LootItem.lootTableItem(HybridAquaticItems.WHALE_SHARK_PLUSHIE.get()))
                .add(LootItem.lootTableItem(HybridAquaticItems.TIGER_SHARK_PLUSHIE.get()))
                .add(LootItem.lootTableItem(HybridAquaticItems.HAMMERHEAD_SHARK_PLUSHIE.get()))
                .add(LootItem.lootTableItem(HybridAquaticItems.FRILLED_SHARK_PLUSHIE.get()))
                .add(LootItem.lootTableItem(HybridAquaticItems.BASKING_SHARK_PLUSHIE.get()))
                .add(LootItem.lootTableItem(HybridAquaticItems.THRESHER_SHARK_PLUSHIE.get()))
        }

        exporter.accept(
            HybridAquaticLootTables.CRAB_POT_TREASURE_ID,
            LootTable.lootTable()
                .setRandomSequence(HybridAquaticLootTables.CRAB_POT_TREASURE_ID)
                .withPool(
                    LootPool.lootPool()
                        .add(
                            LootItem.lootTableItem(HybridAquaticItems.COCONUT_CRAB_CLAW.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(HybridAquaticItems.GHOST_CRAB_CLAW.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(HybridAquaticItems.YETI_CRAB_CLAW.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(HybridAquaticItems.SPIDER_CRAB_CLAW.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(HybridAquaticItems.VAMPIRE_CRAB_CLAW.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(HybridAquaticItems.DUNGENESS_CRAB_CLAW.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(HybridAquaticItems.LIGHTFOOT_CRAB_CLAW.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(HybridAquaticItems.FIDDLER_CRAB_CLAW.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(HybridAquaticItems.FLOWER_CRAB_CLAW.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(HybridAquaticItems.RAW_SHRIMP.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(HybridAquaticItems.LOBSTER_CLAW.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(HybridAquaticItems.RAW_LOBSTER_TAIL.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.HYBRID_CRATE_TREASURE_ID,
            LootTable.lootTable()
                .setRandomSequence(HybridAquaticLootTables.HYBRID_CRATE_TREASURE_ID)
                .withPool(
                    LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0f))
                        .setBonusRolls(UniformGenerator.between(0.0f, 1.0f))
                        .add(LootItem.lootTableItem(HybridAquaticItems.MANGLERFISH_FIN.get()))
                        .add(LootItem.lootTableItem(HybridAquaticItems.MANGLERFISH_LURE.get()))
                        .add(LootItem.lootTableItem(HybridAquaticItems.EEL_SCARF.get()))
                        .add(LootItem.lootTableItem(HybridAquaticItems.MOON_JELLYFISH_HAT.get()))
                        .add(LootItem.lootTableItem(HybridAquaticItems.GREAT_WHITE_SHARK_PLUSHIE.get()))
                        .add(LootItem.lootTableItem(HybridAquaticItems.WHALE_SHARK_PLUSHIE.get()))
                        .add(LootItem.lootTableItem(HybridAquaticItems.TIGER_SHARK_PLUSHIE.get()))
                        .add(LootItem.lootTableItem(HybridAquaticItems.HAMMERHEAD_SHARK_PLUSHIE.get()))
                        .add(LootItem.lootTableItem(HybridAquaticItems.FRILLED_SHARK_PLUSHIE.get()))
                        .add(LootItem.lootTableItem(HybridAquaticItems.BASKING_SHARK_PLUSHIE.get()))
                        .add(
                            LootItem.lootTableItem(HybridAquaticItems.THRESHER_SHARK_PLUSHIE.get())
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.OAK_CRATE_TREASURE_ID,
            LootTable.lootTable()
                .setRandomSequence(HybridAquaticLootTables.OAK_CRATE_TREASURE_ID)
                .withPool(universalCratePool())
                .withPool(driftwoodPool())
                .withPool(plushiePool())
                .withPool(
                    LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0f))
                        .add(
                            LootItem.lootTableItem(Items.OAK_LOG)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(8.0f, 32.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(Items.OAK_SAPLING)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 4.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(Items.APPLE)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0f, 9.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.SPRUCE_CRATE_TREASURE_ID,
            LootTable.lootTable()
                .setRandomSequence(HybridAquaticLootTables.SPRUCE_CRATE_TREASURE_ID)
                .withPool(universalCratePool())
                .withPool(driftwoodPool())
                .withPool(plushiePool())
                .withPool(
                    LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0f))
                        .add(
                            LootItem.lootTableItem(Items.SPRUCE_LOG)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(8.0f, 32.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(Items.SPRUCE_SAPLING)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 4.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(Items.SWEET_BERRIES)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(8.0f, 16.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.BIRCH_CRATE_TREASURE_ID,
            LootTable.lootTable()
                .setRandomSequence(HybridAquaticLootTables.BIRCH_CRATE_TREASURE_ID)
                .withPool(universalCratePool())
                .withPool(driftwoodPool())
                .withPool(plushiePool())
                .withPool(
                    LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0f))
                        .add(
                            LootItem.lootTableItem(Items.BIRCH_LOG)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(8.0f, 32.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(Items.BIRCH_SAPLING)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 4.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(Items.PUMPKIN)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.ACACIA_CRATE_TREASURE_ID,
            LootTable.lootTable()
                .setRandomSequence(HybridAquaticLootTables.ACACIA_CRATE_TREASURE_ID)
                .withPool(universalCratePool())
                .withPool(driftwoodPool())
                .withPool(plushiePool())
                .withPool(
                    LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0f))
                        .add(
                            LootItem.lootTableItem(Items.ACACIA_LOG)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(8.0f, 32.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(Items.ACACIA_SAPLING)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 4.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(Items.BEETROOT)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0f, 9.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.DARK_OAK_CRATE_TREASURE_ID,
            LootTable.lootTable()
                .setRandomSequence(HybridAquaticLootTables.DARK_OAK_CRATE_TREASURE_ID)
                .withPool(universalCratePool())
                .withPool(driftwoodPool())
                .withPool(plushiePool())
                .withPool(
                    LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0f))
                        .add(
                            LootItem.lootTableItem(Items.DARK_OAK_LOG)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(8.0f, 32.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(Items.DARK_OAK_SAPLING)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0f, 8.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(Items.APPLE)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0f, 12.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.MANGROVE_CRATE_TREASURE_ID,
            LootTable.lootTable()
                .setRandomSequence(HybridAquaticLootTables.MANGROVE_CRATE_TREASURE_ID)
                .withPool(universalCratePool())
                .withPool(driftwoodPool())
                .withPool(plushiePool())
                .withPool(
                    LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0f))
                        .add(
                            LootItem.lootTableItem(Items.MANGROVE_LOG)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(8.0f, 32.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(Items.MANGROVE_PROPAGULE)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 4.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(Items.FROGSPAWN)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.CHERRY_CRATE_TREASURE_ID,
            LootTable.lootTable()
                .setRandomSequence(HybridAquaticLootTables.CHERRY_CRATE_TREASURE_ID)
                .withPool(universalCratePool())
                .withPool(driftwoodPool())
                .withPool(plushiePool())
                .withPool(
                    LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0f))
                        .add(
                            LootItem.lootTableItem(Items.CHERRY_LOG)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(8.0f, 32.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(Items.CHERRY_SAPLING)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 4.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(Items.PINK_PETALS)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0f, 12.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.JUNGLE_CRATE_TREASURE_ID,
            LootTable.lootTable()
                .setRandomSequence(HybridAquaticLootTables.JUNGLE_CRATE_TREASURE_ID)
                .withPool(universalCratePool())
                .withPool(driftwoodPool())
                .withPool(plushiePool())
                .withPool(
                    LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0f))
                        .add(
                            LootItem.lootTableItem(Items.JUNGLE_LOG)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(8.0f, 32.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(Items.JUNGLE_SAPLING)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 4.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(Items.MELON)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0f, 9.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(Items.COCOA_BEANS)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0f, 9.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.BAMBOO_CRATE_TREASURE_ID,
            LootTable.lootTable()
                .setRandomSequence(HybridAquaticLootTables.BAMBOO_CRATE_TREASURE_ID)
                .withPool(universalCratePool())
                .withPool(driftwoodPool())
                .withPool(plushiePool())
                .withPool(
                    LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0f))
                        .add(
                            LootItem.lootTableItem(Items.BAMBOO)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0f, 32.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(Items.SPYGLASS)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(Items.SNIFFER_EGG)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(Items.TORCHFLOWER_SEEDS)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 4.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(Items.EXPERIENCE_BOTTLE)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0f, 12.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.VENT_LOOT_ID,
            LootTable.lootTable()
                .setRandomSequence(HybridAquaticLootTables.VENT_LOOT_ID)
                .withPool(
                    LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1F))
                        .add(
                            LootItem.lootTableItem(HybridAquaticItems.SULFUR.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 2.0f)))
                        )
                )
                .withPool(
                    LootPool.lootPool()
                        .`when`(LootItemRandomChanceCondition.randomChance(0.5f))
                        .add(LootItem.lootTableItem(Items.RAW_GOLD).setWeight(1))
                        .add(LootItem.lootTableItem(Items.RAW_IRON).setWeight(3))
                        .add(
                            LootItem.lootTableItem(Items.RAW_COPPER).setWeight(5)
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.BLUE_SPOTTED_STINGRAY,
            LootTable.lootTable()
                .withPool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(HybridAquaticItems.BLUE_SPOTTED_STINGRAY.get()))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.SPOTTED_EAGLE_RAY,
            LootTable.lootTable()
                .withPool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(HybridAquaticItems.SPOTTED_EAGLE_RAY.get()))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.CLAWED_LOBSTER,
            LootTable.lootTable()
                .withPool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(HybridAquaticItems.LOBSTER_CLAW.get()))
                )
                .withPool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(HybridAquaticItems.RAW_LOBSTER_TAIL.get()))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.CLAWLESS_LOBSTER,
            LootTable.lootTable()
                .withPool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(HybridAquaticItems.RAW_LOBSTER_TAIL.get()))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.HERMIT_CRAB_SKULL,
            LootTable.lootTable()
                .withPool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.SKELETON_SKULL))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.HERMIT_CRAB_SHELL,
            LootTable.lootTable()
                .withPool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.NAUTILUS_SHELL))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.DECORATOR_FIRE,
            LootTable.lootTable()
                .withPool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.FIRE_CORAL))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.DECORATOR_BRAIN,
            LootTable.lootTable()
                .withPool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.BRAIN_CORAL))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.DECORATOR_TUBE,
            LootTable.lootTable()
                .withPool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.TUBE_CORAL))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.DECORATOR_BUBBLE,
            LootTable.lootTable()
                .withPool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.BUBBLE_CORAL))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.DECORATOR_HORN,
            LootTable.lootTable()
                .withPool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.HORN_CORAL))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.DECORATOR_THORN,
            LootTable.lootTable()
                .withPool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(HybridAquaticItems.THORN_CORAL.get()))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.DECORATOR_LOPHELIA,
            LootTable.lootTable()
                .withPool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(HybridAquaticItems.LOPHELIA_CORAL.get()))
                )
        )
    }
}
