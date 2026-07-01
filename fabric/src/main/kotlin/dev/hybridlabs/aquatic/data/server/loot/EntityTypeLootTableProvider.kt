package dev.hybridlabs.aquatic.data.server.loot

import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.aquatic.item.HAItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider
import net.minecraft.advancements.critereon.EntityFlagsPredicate
import net.minecraft.advancements.critereon.EntityPredicate
import net.minecraft.core.HolderLookup
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.Items
import net.minecraft.world.level.storage.loot.LootContext
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator
import java.util.concurrent.CompletableFuture
import java.util.function.BiConsumer

/**
 * Generates entity loot tables.
 */
class EntityTypeLootTableProvider(exporter: FabricDataOutput, val lookupProvider: CompletableFuture<HolderLookup.Provider>) :
    SimpleFabricLootTableProvider(exporter, lookupProvider, LootContextParamSets.ENTITY) {
    override fun generate(exporter: BiConsumer<ResourceKey<LootTable>, LootTable.Builder>) {
        val lookup = lookupProvider.join()
        // nautilus
        // sharks
        export(exporter, HAEntityTypes.SLEEPER_SHARK.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_FISH_STEAK.get())
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 3.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.SIXGILL_SHARK.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_FISH_STEAK.get())
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 3.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.GOBLIN_SHARK.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_FISH_STEAK.get())
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 3.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.HAMMERHEAD_SHARK.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_FISH_STEAK.get())
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 3.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.BULL_SHARK.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_FISH_STEAK.get())
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 3.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.GREAT_WHITE_SHARK.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_FISH_STEAK.get())
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 3.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.THRESHER_SHARK.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_FISH_STEAK.get())
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 3.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.WHALE_SHARK.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_FISH_STEAK.get())
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 3.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.BASKING_SHARK.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_FISH_STEAK.get())
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 3.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.HOUND_SHARK.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_FISH_MEAT.get())
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 3.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.SAND_TIGER_SHARK.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_FISH_STEAK.get())
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 3.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.FRILLED_SHARK.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_FISH_STEAK.get())
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 3.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.LANTERN_SHARK.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_FISH_MEAT.get())
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 3.0F)))
                    ).build()
            )
        }
        
        export(exporter, HAEntityTypes.NAUTILUS.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.NAUTILUS_SHELL)
                    ).build()
            )
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_TENTACLE.get())
                            .apply(
                                SmeltItemFunction.smelted().`when`(
                                    LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.THIS,
                                        NEEDS_ENTITY_ON_FIRE
                                    )
                                )
                            )
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.MANATEE.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.SIRENIAN_BEEF.get())
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                    ).build()
            )
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.LEATHER)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 3.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.STARFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.STARFISH.get())
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.DUGONG.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.SIRENIAN_BEEF.get())
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                    ).build()
            )
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.LEATHER)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 3.0F)))
                    ).build()
            )
        }

        //#region Minibosses
        export(exporter, HAEntityTypes.KARKINOS.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_CRAB.get())
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(8.0F, 16.0F)))
                    ).build()
            ).pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.KARKINOS_CLAW.get())
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.SHELL_BEAST.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_TENTACLE.get())
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(8.0F, 16.0F)))
                    ).build()
            ).pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.COMICALLY_LARGE_NAUTILUS_SHELL.get())
                    ).build()
            )
        }
        //#endregion

        //#region Crustaceans
        export(exporter, HAEntityTypes.DECORATOR_CRAB.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_CRAB.get())
                            .apply(
                                SmeltItemFunction.smelted().`when`(
                                    LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.THIS,
                                        NEEDS_ENTITY_ON_FIRE
                                    )
                                )
                            )
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.HORSESHOE_CRAB.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_CRAB.get())
                            .apply(
                                SmeltItemFunction.smelted().`when`(
                                    LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.THIS,
                                        NEEDS_ENTITY_ON_FIRE
                                    )
                                )
                            )
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.DUNGENESS_CRAB.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.DUNGENESS_CRAB_CLAW.get())
                            .apply(
                                SmeltItemFunction.smelted().`when`(
                                    LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.THIS,
                                        NEEDS_ENTITY_ON_FIRE
                                    )
                                )
                            )
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.FIDDLER_CRAB.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.FIDDLER_CRAB_CLAW.get())
                            .apply(
                                SmeltItemFunction.smelted().`when`(
                                    LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.THIS,
                                        NEEDS_ENTITY_ON_FIRE
                                    )
                                )
                            )
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.SPIDER_CRAB.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.SPIDER_CRAB_CLAW.get())
                            .apply(
                                SmeltItemFunction.smelted().`when`(
                                    LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.THIS,
                                        NEEDS_ENTITY_ON_FIRE
                                    )
                                )
                            )
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.GHOST_CRAB.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.GHOST_CRAB_CLAW.get())
                            .apply(
                                SmeltItemFunction.smelted().`when`(
                                    LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.THIS,
                                        NEEDS_ENTITY_ON_FIRE
                                    )
                                )
                            )
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.VAMPIRE_CRAB.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.VAMPIRE_CRAB_CLAW.get())
                            .apply(
                                SmeltItemFunction.smelted().`when`(
                                    LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.THIS,
                                        NEEDS_ENTITY_ON_FIRE
                                    )
                                )
                            )
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.FLOWER_CRAB.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.FLOWER_CRAB_CLAW.get())
                            .apply(
                                SmeltItemFunction.smelted().`when`(
                                    LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.THIS,
                                        NEEDS_ENTITY_ON_FIRE
                                    )
                                )
                            )
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.COCONUT_CRAB.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.COCONUT_CRAB_CLAW.get())
                            .apply(
                                SmeltItemFunction.smelted().`when`(
                                    LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.THIS,
                                        NEEDS_ENTITY_ON_FIRE
                                    )
                                )
                            )
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.YETI_CRAB.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.YETI_CRAB_CLAW.get())
                            .apply(
                                SmeltItemFunction.smelted().`when`(
                                    LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.THIS,
                                        NEEDS_ENTITY_ON_FIRE
                                    )
                                )
                            )
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.LIGHTFOOT_CRAB.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.LIGHTFOOT_CRAB_CLAW.get())
                            .apply(
                                SmeltItemFunction.smelted().`when`(
                                    LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.THIS,
                                        NEEDS_ENTITY_ON_FIRE
                                    )
                                )
                            )
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.LOBSTER.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.LOBSTER_CLAW.get())
                            .apply(
                                SmeltItemFunction.smelted().`when`(
                                    LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.THIS,
                                        NEEDS_ENTITY_ON_FIRE
                                    )
                                )
                            )
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                    ).build()
            )
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_LOBSTER_TAIL.get())
                            .apply(
                                SmeltItemFunction.smelted().`when`(
                                    LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.THIS,
                                        NEEDS_ENTITY_ON_FIRE
                                    )
                                )
                            )
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.CRAYFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_CRAYFISH.get())
                            .apply(
                                SmeltItemFunction.smelted().`when`(
                                    LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.THIS,
                                        NEEDS_ENTITY_ON_FIRE
                                    )
                                )
                            )
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.SHRIMP.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_SHRIMP.get())
                            .apply(
                                SmeltItemFunction.smelted().`when`(
                                    LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.THIS,
                                        NEEDS_ENTITY_ON_FIRE
                                    )
                                )
                            )
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        // cephalopods
        export(exporter, HAEntityTypes.VAMPIRE_SQUID.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_TENTACLE.get())
                            .apply(
                                SmeltItemFunction.smelted().`when`(
                                    LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.THIS,
                                        NEEDS_ENTITY_ON_FIRE
                                    )
                                )
                            )
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                    ).build()
            )
        }
        export(exporter, HAEntityTypes.CUTTLEFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.INK_SAC)
                    ).build()
            ).pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.CUTTLEBONE.get())
                    ).build()
            )
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_TENTACLE.get())
                            .apply(
                                SmeltItemFunction.smelted().`when`(
                                    LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.THIS,
                                        NEEDS_ENTITY_ON_FIRE
                                    )
                                )
                            )
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.ARROW_SQUID.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.INK_SAC)
                    ).build()
            )
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_TENTACLE.get())
                            .apply(
                                SmeltItemFunction.smelted().`when`(
                                    LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.THIS,
                                        NEEDS_ENTITY_ON_FIRE
                                    )
                                )
                            )
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.GIANT_SQUID.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.INK_SAC)
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                    ).build()
            )
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_TENTACLE.get())
                            .apply(
                                SmeltItemFunction.smelted().`when`(
                                    LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.THIS,
                                        NEEDS_ENTITY_ON_FIRE
                                    )
                                )
                            )
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.COLOSSAL_SQUID.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.INK_SAC)
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                    ).build()
            )
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_TENTACLE.get())
                            .apply(
                                SmeltItemFunction.smelted().`when`(
                                    LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.THIS,
                                        NEEDS_ENTITY_ON_FIRE
                                    )
                                )
                            )
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.FIREFLY_SQUID.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.GLOW_INK_SAC)
                    ).build()
            )
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_TENTACLE.get())
                            .apply(
                                SmeltItemFunction.smelted().`when`(
                                    LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.THIS,
                                        NEEDS_ENTITY_ON_FIRE
                                    )
                                )
                            )
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.SEA_CUCUMBER.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.SLIME_BALL)
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.SCALYFOOT_SNAIL.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.IRON_NUGGET)
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 2.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.SEA_SLUG.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.SLIME_BALL)
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.SEA_URCHIN.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.SEA_URCHIN_SPINE.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.UNI.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 3.0F)))
                    ).build()
            )
        }

        // end region

        export(exporter, HAEntityTypes.OCTOPUS.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_TENTACLE.get())
                            .apply(
                                SmeltItemFunction.smelted().`when`(
                                    LootItemEntityPropertyCondition.hasProperties(
                                        LootContext.EntityTarget.THIS,
                                        NEEDS_ENTITY_ON_FIRE
                                    )
                                )
                            )
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                    ).build()
            )
        }

        // jellyfish
        export(exporter, HAEntityTypes.MOON_JELLYFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.SLIME_BALL)
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.SEA_NETTLE.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.SLIME_BALL)
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.NOMURA_JELLYFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.SLIME_BALL)
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.LIONS_MANE_JELLYFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.SLIME_BALL)
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.BLUE_JELLYFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.SLIME_BALL)
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }


        export(exporter, HAEntityTypes.BARREL_JELLYFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.SLIME_BALL)
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.MAUVE_STINGER.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.SLIME_BALL)
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.CEPHEIDAE_JELLYFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.SLIME_BALL)
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.CROWN_JELLYFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.GLOWSLIME.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.ANGLERFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.ANGLERFISH.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.DRAGONFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.DRAGONFISH.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.BLOBFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.BLOBFISH.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.HAGFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.HAGFISH.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.ROCKFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.ROCKFISH.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.SEA_BASS.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.SEA_BASS.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.CLOWNFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.CLOWNFISH.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.DAMSELFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.DAMSELFISH.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.JOHN_DORY.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.JOHN_DORY.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.SURGEONFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.SURGEONFISH.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.BOXFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.BOXFISH.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.LIONFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.LIONFISH.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.BARRELEYE.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.GLOWSLIME.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.BARRELEYE.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.STINGRAY.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.STINGRAY.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.GOURAMI.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.GOURAMI.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.BETTA.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.BETTA.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.PEARLFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.PEARLFISH.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.SNAILFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.SNAILFISH.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.DANIO.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.DANIO.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.DISCUS.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.DISCUS.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.BLOWFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.BLOWFISH.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.STONEFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.STONEFISH.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.CARP.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.CARP.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.GOLDFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.GOLDFISH.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.PLECO.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.PLECO.get())
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.SHINER.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_FISH_MEAT.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(1.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.FANGTOOTH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_FISH_MEAT.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(1.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.HATCHETFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_FISH_MEAT.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(1.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.VIPERFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_FISH_MEAT.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(1.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.SUNFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.SUNFISH.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(1.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.TROUT.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.TROUT.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(1.0F, 2.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.PARROTFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.PARROTFISH.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.WRASSE.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.SHEEPSHEAD_WRASSE.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.FLASHLIGHT_FISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.FLASHLIGHT_FISH.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.RATFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RATFISH.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.PIRANHA.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.PIRANHA.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.MAHI.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.MAHI.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.OPAH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.OPAH.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.NEEDLEFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.NEEDLEFISH.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.MACKEREL.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.MACKEREL.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.HERRING.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.HERRING.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.TUNA.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.TUNA.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.FLYING_FISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.FLYING_FISH.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.TRIGGERFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.TRIGGERFISH.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }


        export(exporter, HAEntityTypes.TREVALLY.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.TREVALLY.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.SEAHORSE.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.SEAHORSE.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.OCEAN_SUNFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.OCEAN_SUNFISH.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.COELACANTH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.COELACANTH.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.GOLDEN_DORADO.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.GOLDEN_DORADO.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.OARFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.OARFISH.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.CICHLID.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.CICHLID.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.TIGER_BARB.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.TIGER_BARB.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.MORAY_EEL.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.MORAY_EEL.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.SQUIRRELFISH.get().defaultLootTable){
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.SQUIRRELFISH.get())
                            .apply(EnchantedCountIncreaseFunction.lootingMultiplier(lookup,UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }
    }

    /**
     * Exports a loot table for [entityType] to [exporter] using its loot table id.
     */
    private fun export(
        exporter: BiConsumer<ResourceKey<LootTable>, LootTable.Builder>,
        entityTable: ResourceKey<LootTable>,
        builder: LootTable.Builder.() -> Unit
    ) {
        exporter.accept(entityTable, LootTable.lootTable().apply(builder))
    }

    companion object {
        private val NEEDS_ENTITY_ON_FIRE: EntityPredicate.Builder =
            EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true))
    }
}
