package dev.hybridlabs.aquatic.data.server.loot

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.item.HybridAquaticItems
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
        export(exporter, HybridAquaticEntityTypes.NAUTILUS.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.NAUTILUS_SHELL)
                    ).build()
            )
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.RAW_TENTACLE.get())
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
        //crustaceans

        export(exporter, HybridAquaticEntityTypes.KARKINOS.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.RAW_CRAB.get())
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(8.0F, 16.0F)))
                    ).build()
            ).pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.KARKINOS_CLAW.get())
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.DECORATOR_CRAB.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.RAW_CRAB.get())
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

        export(exporter, HybridAquaticEntityTypes.HORSESHOE_CRAB.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.RAW_CRAB.get())
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

        export(exporter, HybridAquaticEntityTypes.DUNGENESS_CRAB.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.DUNGENESS_CRAB_CLAW.get())
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

        export(exporter, HybridAquaticEntityTypes.FIDDLER_CRAB.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.FIDDLER_CRAB_CLAW.get())
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

        export(exporter, HybridAquaticEntityTypes.SPIDER_CRAB.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.SPIDER_CRAB_CLAW.get())
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

        export(exporter, HybridAquaticEntityTypes.GHOST_CRAB.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.GHOST_CRAB_CLAW.get())
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

        export(exporter, HybridAquaticEntityTypes.VAMPIRE_CRAB.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.VAMPIRE_CRAB_CLAW.get())
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

        export(exporter, HybridAquaticEntityTypes.FLOWER_CRAB.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.FLOWER_CRAB_CLAW.get())
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

        export(exporter, HybridAquaticEntityTypes.COCONUT_CRAB.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.COCONUT_CRAB_CLAW.get())
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

        export(exporter, HybridAquaticEntityTypes.YETI_CRAB.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.YETI_CRAB_CLAW.get())
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

        export(exporter, HybridAquaticEntityTypes.LIGHTFOOT_CRAB.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.LIGHTFOOT_CRAB_CLAW.get())
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

        export(exporter, HybridAquaticEntityTypes.LOBSTER.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.LOBSTER_CLAW.get())
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
                        LootItem.lootTableItem(HybridAquaticItems.RAW_LOBSTER_TAIL.get())
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

        export(exporter, HybridAquaticEntityTypes.CRAYFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.RAW_CRAYFISH.get())
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

        export(exporter, HybridAquaticEntityTypes.SHRIMP.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.RAW_SHRIMP.get())
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
        export(exporter, HybridAquaticEntityTypes.VAMPIRE_SQUID.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.RAW_TENTACLE.get())
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
        export(exporter, HybridAquaticEntityTypes.CUTTLEFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.INK_SAC)
                    ).build()
            ).pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.CUTTLEBONE.get())
                    ).build()
            )
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.RAW_TENTACLE.get())
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

        export(exporter, HybridAquaticEntityTypes.ARROW_SQUID.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.INK_SAC)
                    ).build()
            )
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.RAW_TENTACLE.get())
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

        export(exporter, HybridAquaticEntityTypes.FIREFLY_SQUID.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.GLOW_INK_SAC)
                    ).build()
            )
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.RAW_TENTACLE.get())
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

        export(exporter, HybridAquaticEntityTypes.SEA_CUCUMBER.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.SLIME_BALL)
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.SEA_SLUG.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.SLIME_BALL)
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.SEA_URCHIN.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.SEA_URCHIN_SPINE.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.UNI.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 3.0F)))
                    ).build()
            )
        }

        // end region

        export(exporter, HybridAquaticEntityTypes.GLOWING_SUCKER_OCTOPUS.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.RAW_TENTACLE.get())
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
        export(exporter, HybridAquaticEntityTypes.MOON_JELLYFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.SLIME_BALL)
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.SEA_NETTLE.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.SLIME_BALL)
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.NOMURA_JELLYFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.SLIME_BALL)
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.LIONS_MANE_JELLYFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.SLIME_BALL)
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.BLUE_JELLYFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.SLIME_BALL)
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }


        export(exporter, HybridAquaticEntityTypes.BARREL_JELLYFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.SLIME_BALL)
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.MAUVE_STINGER.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.SLIME_BALL)
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.CEPHEIDAE_JELLYFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(Items.SLIME_BALL)
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.ATOLLA_JELLYFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.GLOW_SLIME.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.ANGLERFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.ANGLERFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.DRAGONFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.DRAGONFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.ROCKFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.ROCKFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.SEA_BASS.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.SEA_BASS.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.CLOWNFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.CLOWNFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.DAMSELFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.DAMSELFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.JOHN_DORY.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.JOHN_DORY.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.SURGEONFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.SURGEONFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.BOXFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.BOXFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.LIONFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.LIONFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.BARRELEYE.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.GLOW_SLIME.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.BARRELEYE.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.STINGRAY.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.BLUE_SPOTTED_STINGRAY.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.GOURAMI.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.GOURAMI.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.BETTA.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.BETTA.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.PEARLFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.PEARLFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.SNAILFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.SNAILFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.DANIO.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.DANIO.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.DISCUS.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.DISCUS.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.BLOWFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.BLOWFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.STONEFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.STONEFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.CARP.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.CARP.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.GOLDFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.GOLDFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.PARROTFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.PARROTFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.FLASHLIGHT_FISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.FLASHLIGHT_FISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.RATFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.RATFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.PIRANHA.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.PIRANHA.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.MAHI.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.MAHI.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.OPAH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.OPAH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.NEEDLEFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.NEEDLEFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.MACKEREL.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.MACKEREL.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.TUNA.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.TUNA.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.FLYING_FISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.FLYING_FISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.TRIGGERFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.TRIGGERFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.SEAHORSE.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.SEAHORSE.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.SUNFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.SUNFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.COELACANTH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.COELACANTH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.GOLDEN_DORADO.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.GOLDEN_DORADO.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.OARFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.OARFISH.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.OSCAR.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.OSCAR.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.TIGER_BARB.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.TIGER_BARB.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.MORAY_EEL.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.MORAY_EEL.get())
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                    ).build()
            )
        }

        export(exporter, HybridAquaticEntityTypes.SQUIRRELFISH.get()) {
            pool(
                LootPool.lootPool()
                    .add(
                        LootItem.lootTableItem(HybridAquaticItems.SQUIRRELFISH.get())
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
