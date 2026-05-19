package dev.hybridlabs.aquatic.data.server.loot

import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.aquatic.item.HAItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider
import net.minecraft.advancements.critereon.EntityFlagsPredicate
import net.minecraft.advancements.critereon.EntityPredicate
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.EntityType
import net.minecraft.world.item.Items
import net.minecraft.world.level.storage.loot.LootContext
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator
import java.util.function.BiConsumer

/**
 * Generates entity loot tables.
 */
class EntityTypeLootTableProvider(output: FabricDataOutput) :
    SimpleFabricLootTableProvider(output, LootContextParamSets.ENTITY) {
    override fun generate(exporter: BiConsumer<ResourceLocation, LootTable.Builder>) {
        // nautilus
        export(exporter, HAEntityTypes.NAUTILUS.get()) {
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

        //#region Minibosses
        export(exporter, HAEntityTypes.KARKINOS.get()) {
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

        export(exporter, HAEntityTypes.SHELL_BEAST.get()) {
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
        export(exporter, HAEntityTypes.DECORATOR_CRAB.get()) {
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
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.HORSESHOE_CRAB.get()) {
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
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.DUNGENESS_CRAB.get()) {
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
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.FIDDLER_CRAB.get()) {
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
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.SPIDER_CRAB.get()) {
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
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.GHOST_CRAB.get()) {
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
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.VAMPIRE_CRAB.get()) {
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
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.FLOWER_CRAB.get()) {
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
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.COCONUT_CRAB.get()) {
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
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.YETI_CRAB.get()) {
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
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.LIGHTFOOT_CRAB.get()) {
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
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.LOBSTER.get()) {
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
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
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
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.CRAYFISH.get()) {
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
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.SHRIMP.get()) {
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
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        // cephalopods
        export(exporter, HAEntityTypes.VAMPIRE_SQUID.get()) {
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
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                    ).build()
            )
        }
        export(exporter, HAEntityTypes.CUTTLEFISH.get()) {
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
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.ARROW_SQUID.get()) {
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
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.GIANT_SQUID.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.INK_SAC)
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
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
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.COLOSSAL_SQUID.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.INK_SAC)
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
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
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.FIREFLY_SQUID.get()) {
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
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.SEA_CUCUMBER.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.SLIME_BALL)
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.SCALYFOOT_SNAIL.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.IRON_NUGGET)
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 2.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.SEA_SLUG.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.SLIME_BALL)
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.SEA_URCHIN.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.SEA_URCHIN_SPINE.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.UNI.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 3.0F)))
                    ).build()
            )
        }

        // end region

        export(exporter, HAEntityTypes.OCTOPUS.get()) {
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
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                    ).build()
            )
        }

        // jellyfish
        export(exporter, HAEntityTypes.MOON_JELLYFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.SLIME_BALL)
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.SEA_NETTLE.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.SLIME_BALL)
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.NOMURA_JELLYFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.SLIME_BALL)
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.LIONS_MANE_JELLYFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.SLIME_BALL)
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.BLUE_JELLYFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.SLIME_BALL)
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }


        export(exporter, HAEntityTypes.BARREL_JELLYFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.SLIME_BALL)
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.MAUVE_STINGER.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.SLIME_BALL)
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.CEPHEIDAE_JELLYFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.SLIME_BALL)
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.CROWN_JELLYFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.GLOWSLIME.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.ANGLERFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.ANGLERFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.DRAGONFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.DRAGONFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.HAGFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.HAGFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.ROCKFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.ROCKFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.SEA_BASS.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.SEA_BASS.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.CLOWNFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.CLOWNFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.DAMSELFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.DAMSELFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.JOHN_DORY.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.JOHN_DORY.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.SURGEONFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.SURGEONFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.BOXFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.BOXFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.LIONFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.LIONFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.BARRELEYE.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.GLOWSLIME.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.BARRELEYE.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.STINGRAY.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.BLUE_SPOTTED_STINGRAY.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.GOURAMI.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.GOURAMI.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.BETTA.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.BETTA.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.PEARLFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.PEARLFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.SNAILFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.SNAILFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.DANIO.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.DANIO.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.DISCUS.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.DISCUS.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.BLOWFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.BLOWFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.STONEFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.STONEFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.CARP.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.CARP.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.PLECO.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.PLECO.get())
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                            .apply(LootingEnchantFunction.lootingMultiplier(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.SHINER.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_FISH_MEAT.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.FANGTOOTH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_FISH_MEAT.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.HATCHETFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_FISH_MEAT.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.VIPERFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RAW_FISH_MEAT.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.SUNFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.SUNFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.TROUT.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.TROUT.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.PARROTFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.PARROTFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.WRASSE.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.SHEEPSHEAD_WRASSE.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.FLASHLIGHT_FISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.FLASHLIGHT_FISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.RATFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.RATFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.PIRANHA.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.PIRANHA.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.MAHI.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.MAHI.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.OPAH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.OPAH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.NEEDLEFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.NEEDLEFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.MACKEREL.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.MACKEREL.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.HERRING.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.HERRING.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.TUNA.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.TUNA.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.FLYING_FISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.FLYING_FISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.TRIGGERFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.TRIGGERFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }


        export(exporter, HAEntityTypes.TREVALLY.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.TREVALLY.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.SEAHORSE.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.SEAHORSE.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.OCEAN_SUNFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.OCEAN_SUNFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.COELACANTH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.COELACANTH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.GOLDEN_DORADO.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.GOLDEN_DORADO.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.OARFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.OARFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.OSCAR.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.OSCAR.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.TIGER_BARB.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.TIGER_BARB.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.MORAY_EEL.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.MORAY_EEL.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HAEntityTypes.SQUIRRELFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HAItems.SQUIRRELFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }
    }

    /**
     * Exports a loot table for [entityType] to [exporter] using its loot table id.
     */
    private fun export(
        exporter: BiConsumer<ResourceLocation, LootTable.Builder>,
        entityType: EntityType<*>,
        builder: LootTable.Builder.() -> Unit
    ) {
        exporter.accept(entityType.defaultLootTable, LootTable.lootTable().apply(builder))
    }

    companion object {
        private val NEEDS_ENTITY_ON_FIRE: EntityPredicate.Builder =
            EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true).build())
    }
}
