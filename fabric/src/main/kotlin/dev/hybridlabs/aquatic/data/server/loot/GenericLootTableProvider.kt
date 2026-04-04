package dev.hybridlabs.aquatic.data.server.loot

import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.aquatic.item.HAPlatformItems
import dev.hybridlabs.aquatic.loot.HALootTables
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

        //#region Universal Crate Loot
        fun universalCratePool(): LootPool.Builder {
            return LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0f))
                .add(LootItem.lootTableItem(HAItems.BARBED_HOOK.get()))
                .add(LootItem.lootTableItem(HAItems.GLOWING_HOOK.get()))
                .add(LootItem.lootTableItem(HAItems.MAGNETIC_HOOK.get()))
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
                .add(LootItem.lootTableItem(HAPlatformItems.DRIFTWOOD_LOG.get())
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(16f, 32f))))
        }

        fun plushiePool(): LootPool.Builder {
            return LootPool.lootPool()
                .`when`(LootItemRandomChanceCondition.randomChance(0.25f))
                .add(LootItem.lootTableItem(HAItems.GREAT_WHITE_SHARK_PLUSHIE.get()))
                .add(LootItem.lootTableItem(HAItems.WHALE_SHARK_PLUSHIE.get()))
                .add(LootItem.lootTableItem(HAItems.TIGER_SHARK_PLUSHIE.get()))
                .add(LootItem.lootTableItem(HAItems.HAMMERHEAD_SHARK_PLUSHIE.get()))
                .add(LootItem.lootTableItem(HAItems.FRILLED_SHARK_PLUSHIE.get()))
                .add(LootItem.lootTableItem(HAItems.BASKING_SHARK_PLUSHIE.get()))
                .add(LootItem.lootTableItem(HAItems.THRESHER_SHARK_PLUSHIE.get()))
        }
        //#endregion

        //#region Crate Loot Tables
        exporter.accept(
            HALootTables.CRAB_POT_TREASURE_ID,
            LootTable.lootTable()
                .setRandomSequence(HALootTables.CRAB_POT_TREASURE_ID)
                .pool(
                    LootPool.lootPool()
                        .add(
                            LootItem.lootTableItem(HAItems.COCONUT_CRAB_CLAW.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(HAItems.GHOST_CRAB_CLAW.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(HAItems.YETI_CRAB_CLAW.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(HAItems.SPIDER_CRAB_CLAW.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(HAItems.VAMPIRE_CRAB_CLAW.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(HAItems.DUNGENESS_CRAB_CLAW.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(HAItems.LIGHTFOOT_CRAB_CLAW.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(HAItems.FIDDLER_CRAB_CLAW.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(HAItems.FLOWER_CRAB_CLAW.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(HAItems.RAW_SHRIMP.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(HAItems.LOBSTER_CLAW.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f)))
                        )
                        .add(
                            LootItem.lootTableItem(HAItems.RAW_LOBSTER_TAIL.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f)))
                        ).build()
                )
        )

        exporter.accept(
            HALootTables.HYBRID_CRATE_TREASURE_ID,
            LootTable.lootTable()
                .setRandomSequence(HALootTables.HYBRID_CRATE_TREASURE_ID)
                .pool(
                    LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0f))
                        .setBonusRolls(UniformGenerator.between(0.0f, 1.0f))
                        .add(LootItem.lootTableItem(HAItems.MANGLERFISH_FIN.get()))
                        .add(LootItem.lootTableItem(HAItems.MANGLERFISH_LURE.get()))
                        .add(LootItem.lootTableItem(HAItems.EEL_SCARF.get()))
                        .add(LootItem.lootTableItem(HAItems.PINK_HATXOLOTL.get()))
                        .add(LootItem.lootTableItem(HAItems.GOLD_HATXOLOTL.get()))
                        .add(LootItem.lootTableItem(HAItems.BROWN_HATXOLOTL.get()))
                        .add(LootItem.lootTableItem(HAItems.BLUE_HATXOLOTL.get()))
                        .add(LootItem.lootTableItem(HAItems.CYAN_HATXOLOTL.get()))
                        .add(LootItem.lootTableItem(HAItems.MOON_JELLYFISH_HAT.get()))
                        .add(LootItem.lootTableItem(HAItems.GREAT_WHITE_SHARK_PLUSHIE.get()))
                        .add(LootItem.lootTableItem(HAItems.WHALE_SHARK_PLUSHIE.get()))
                        .add(LootItem.lootTableItem(HAItems.TIGER_SHARK_PLUSHIE.get()))
                        .add(LootItem.lootTableItem(HAItems.HAMMERHEAD_SHARK_PLUSHIE.get()))
                        .add(LootItem.lootTableItem(HAItems.FRILLED_SHARK_PLUSHIE.get()))
                        .add(LootItem.lootTableItem(HAItems.BASKING_SHARK_PLUSHIE.get()))
                        .add(
                            LootItem.lootTableItem(HAItems.THRESHER_SHARK_PLUSHIE.get())
                        ).build()
                )
        )

        exporter.accept(
            HALootTables.OAK_CRATE_TREASURE_ID,
            LootTable.lootTable()
                .setRandomSequence(HALootTables.OAK_CRATE_TREASURE_ID)
                .pool(universalCratePool().build())
                .pool(driftwoodPool().build())
                .pool(plushiePool().build())
                .pool(
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
                        ).build()
                )
        )

        exporter.accept(
            HALootTables.SPRUCE_CRATE_TREASURE_ID,
            LootTable.lootTable()
                .setRandomSequence(HALootTables.SPRUCE_CRATE_TREASURE_ID)
                .pool(universalCratePool().build())
                .pool(driftwoodPool().build())
                .pool(plushiePool().build())
                .pool(
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
                        ).build()
                )
        )

        exporter.accept(
            HALootTables.BIRCH_CRATE_TREASURE_ID,
            LootTable.lootTable()
                .setRandomSequence(HALootTables.BIRCH_CRATE_TREASURE_ID)
                .pool(universalCratePool().build())
                .pool(driftwoodPool().build())
                .pool(plushiePool().build())
                .pool(
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
                        ).build()
                )
        )

        exporter.accept(
            HALootTables.ACACIA_CRATE_TREASURE_ID,
            LootTable.lootTable()
                .setRandomSequence(HALootTables.ACACIA_CRATE_TREASURE_ID)
                .pool(universalCratePool().build())
                .pool(driftwoodPool().build())
                .pool(plushiePool().build())
                .pool(
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
                        ).build()
                )
        )

        exporter.accept(
            HALootTables.DARK_OAK_CRATE_TREASURE_ID,
            LootTable.lootTable()
                .setRandomSequence(HALootTables.DARK_OAK_CRATE_TREASURE_ID)
                .pool(universalCratePool().build())
                .pool(driftwoodPool().build())
                .pool(plushiePool().build())
                .pool(
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
                        ).build()
                )
        )

        exporter.accept(
            HALootTables.MANGROVE_CRATE_TREASURE_ID,
            LootTable.lootTable()
                .setRandomSequence(HALootTables.MANGROVE_CRATE_TREASURE_ID)
                .pool(universalCratePool().build())
                .pool(driftwoodPool().build())
                .pool(plushiePool().build())
                .pool(
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
                        ).build()
                )
        )

        exporter.accept(
            HALootTables.CHERRY_CRATE_TREASURE_ID,
            LootTable.lootTable()
                .setRandomSequence(HALootTables.CHERRY_CRATE_TREASURE_ID)
                .pool(universalCratePool().build())
                .pool(driftwoodPool().build())
                .pool(plushiePool().build())
                .pool(
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
                        ).build()
                )
        )

        exporter.accept(
            HALootTables.JUNGLE_CRATE_TREASURE_ID,
            LootTable.lootTable()
                .setRandomSequence(HALootTables.JUNGLE_CRATE_TREASURE_ID)
                .pool(universalCratePool().build())
                .pool(driftwoodPool().build())
                .pool(plushiePool().build())
                .pool(
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
                        ).build()
                )
        )

        exporter.accept(
            HALootTables.BAMBOO_CRATE_TREASURE_ID,
            LootTable.lootTable()
                .setRandomSequence(HALootTables.BAMBOO_CRATE_TREASURE_ID)
                .pool(universalCratePool().build())
                .pool(driftwoodPool().build())
                .pool(plushiePool().build())
                .pool(
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
                        ).build()
                )
        )
        //#endregion

        //#region Extra Entity Loot
        exporter.accept(
            HALootTables.BLUE_SPOTTED_STINGRAY,
            LootTable.lootTable()
                .pool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(HAItems.BLUE_SPOTTED_STINGRAY.get())).build()
                )
        )

        exporter.accept(
            HALootTables.SPOTTED_EAGLE_RAY,
            LootTable.lootTable()
                .pool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(HAItems.SPOTTED_EAGLE_RAY.get())).build()
                )
        )

        exporter.accept(
            HALootTables.CLAWED_LOBSTER,
            LootTable.lootTable()
                .pool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(HAItems.LOBSTER_CLAW.get())).build()
                )
                .pool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(HAItems.RAW_LOBSTER_TAIL.get())).build()
                )
        )

        exporter.accept(
            HALootTables.CLAWLESS_LOBSTER,
            LootTable.lootTable()
                .pool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(HAItems.RAW_LOBSTER_TAIL.get())).build()
                )
        )

        //#region Decorator Crab Loot
        exporter.accept(
            HALootTables.DECORATOR_FIRE,
            LootTable.lootTable()
                .pool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.FIRE_CORAL)).build()
                )
        )

        exporter.accept(
            HALootTables.DECORATOR_BRAIN,
            LootTable.lootTable()
                .pool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.BRAIN_CORAL)).build()
                )
        )

        exporter.accept(
            HALootTables.DECORATOR_TUBE,
            LootTable.lootTable()
                .pool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.TUBE_CORAL)).build()
                )
        )

        exporter.accept(
            HALootTables.DECORATOR_BUBBLE,
            LootTable.lootTable()
                .pool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.BUBBLE_CORAL)).build()
                )
        )

        exporter.accept(
            HALootTables.DECORATOR_HORN,
            LootTable.lootTable()
                .pool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.HORN_CORAL)).build()
                )
        )

        exporter.accept(
            HALootTables.DECORATOR_THORN,
            LootTable.lootTable()
                .pool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(HAItems.THORN_CORAL.get())).build()
                )
        )

        exporter.accept(
            HALootTables.DECORATOR_LOPHELIA,
            LootTable.lootTable()
                .pool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(HAItems.LOPHELIA_CORAL.get())).build()
                )
        )
        //#endregion
        //#endregion

        exporter.accept(
            HALootTables.VENT_LOOT_ID,
            LootTable.lootTable()
                .setRandomSequence(HALootTables.VENT_LOOT_ID)
                .pool(
                    LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1F))
                        .add(
                            LootItem.lootTableItem(HAItems.SULFUR.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 2.0f))))
                        .build()
                )
                .pool(
                    LootPool.lootPool()
                        .`when`(LootItemRandomChanceCondition.randomChance(0.5f))
                        .add(LootItem.lootTableItem(Items.RAW_GOLD).setWeight(1))
                        .add(LootItem.lootTableItem(Items.RAW_IRON).setWeight(3))
                        .add(LootItem.lootTableItem(Items.RAW_COPPER).setWeight(5))
                        .build()
                )
        )

        //#region Archaeology Loot
        exporter.accept(
                HALootTables.BEACH_ARCHAEOLOGY_ID,
        LootTable.lootTable()
            .setRandomSequence(HALootTables.BEACH_ARCHAEOLOGY_ID)
            .pool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(Items.STICK).setWeight(10))
                    .add(LootItem.lootTableItem(HAItems.SHARK_TOOTH.get()).setWeight(8))
                    .add(LootItem.lootTableItem(HAItems.SEA_URCHIN_SPINE.get()).setWeight(8))
                    .add(LootItem.lootTableItem(HAItems.BULL_KELP.get()).setWeight(8))
                    .add(LootItem.lootTableItem(HAItems.SARGASSUM.get()).setWeight(8))
                    .add(LootItem.lootTableItem(HAItems.CUTTLEBONE.get()).setWeight(8))
                    .add(LootItem.lootTableItem(HAItems.CORAL_CHUNK.get()).setWeight(6))
                    .add(LootItem.lootTableItem(Items.PRISMARINE_SHARD).setWeight(6))
                    .add(LootItem.lootTableItem(Items.PRISMARINE_CRYSTALS).setWeight(6))
                    .add(LootItem.lootTableItem(HAItems.COCONUT_CRAB_CLAW.get()).setWeight(5))
                    .add(LootItem.lootTableItem(HAItems.GHOST_CRAB_CLAW.get()).setWeight(5))
                    .add(LootItem.lootTableItem(HAItems.DUNGENESS_CRAB_CLAW.get()).setWeight(5))
                    .add(LootItem.lootTableItem(HAItems.FLOWER_CRAB_CLAW.get()).setWeight(5))
                    .add(LootItem.lootTableItem(HAItems.LIGHTFOOT_CRAB_CLAW.get()).setWeight(5))
                    .add(LootItem.lootTableItem(HAItems.HYBRID_CRATE.get()).setWeight(3))
                    .add(LootItem.lootTableItem(HAItems.OAK_CRATE.get()).setWeight(3))
                    .add(LootItem.lootTableItem(HAItems.DARK_OAK_CRATE.get()).setWeight(3))
                    .add(LootItem.lootTableItem(HAItems.SPRUCE_CRATE.get()).setWeight(3))
                    .add(LootItem.lootTableItem(HAItems.BIRCH_CRATE.get()).setWeight(3))
                    .add(LootItem.lootTableItem(HAItems.ACACIA_CRATE.get()).setWeight(3))
                    .add(LootItem.lootTableItem(HAItems.JUNGLE_CRATE.get()).setWeight(3))
                    .add(LootItem.lootTableItem(HAItems.MANGROVE_CRATE.get()).setWeight(3))
                    .add(LootItem.lootTableItem(HAItems.CHERRY_CRATE.get()).setWeight(3))
                    .add(LootItem.lootTableItem(HAItems.BAMBOO_CRATE.get()).setWeight(3))
                    .add(LootItem.lootTableItem(Items.NAUTILUS_SHELL).setWeight(4))
                    .add(LootItem.lootTableItem(Items.SCUTE).setWeight(4))
                    .add(LootItem.lootTableItem(Items.TURTLE_EGG).setWeight(2))
                    .add(LootItem.lootTableItem(Items.HEART_OF_THE_SEA).setWeight(1))
                    .add(LootItem.lootTableItem(HAItems.DIVING_ARMOR_UPGRADE_TEMPLATE.get()).setWeight(1))
                    .build()
            )
        )

        exporter.accept(
            HALootTables.PLACER_RIVER_ARCHAEOLOGY_ID,
            LootTable.lootTable()
                .setRandomSequence(HALootTables.PLACER_RIVER_ARCHAEOLOGY_ID)
                .pool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.GOLD_NUGGET).setWeight(10))
                        .add(LootItem.lootTableItem(Items.RAW_GOLD).setWeight(8))
                        .add(LootItem.lootTableItem(Items.FLINT).setWeight(6))
                        .add(LootItem.lootTableItem(Items.STICK).setWeight(6))
                        .add(LootItem.lootTableItem(Items.BONE).setWeight(4))
                        .add(LootItem.lootTableItem(Items.BURN_POTTERY_SHERD).setWeight(1))
                        .add(LootItem.lootTableItem(Items.DANGER_POTTERY_SHERD).setWeight(1))
                        .add(LootItem.lootTableItem(Items.FRIEND_POTTERY_SHERD).setWeight(1))
                        .add(LootItem.lootTableItem(Items.HEART_POTTERY_SHERD).setWeight(1))
                        .add(LootItem.lootTableItem(Items.HEARTBREAK_POTTERY_SHERD).setWeight(1))
                        .add(LootItem.lootTableItem(Items.HOWL_POTTERY_SHERD).setWeight(1))
                        .add(LootItem.lootTableItem(Items.SHEAF_POTTERY_SHERD).setWeight(1))
                        .build()
                )
        )
        //#endregion
    }
}