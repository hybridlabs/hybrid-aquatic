package dev.hybridlabs.aquatic.data.server.loot

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider
import net.minecraft.entity.EntityType
import net.minecraft.item.Items
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.condition.EntityPropertiesLootCondition
import net.minecraft.loot.context.LootContext
import net.minecraft.loot.context.LootContextTypes
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.function.FurnaceSmeltLootFunction
import net.minecraft.loot.function.LootingEnchantLootFunction
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.predicate.entity.EntityFlagsPredicate
import net.minecraft.predicate.entity.EntityPredicate
import net.minecraft.util.Identifier
import java.util.function.BiConsumer

/**
 * Generates entity loot tables.
 */
class EntityTypeLootTableProvider(output: FabricDataOutput) : SimpleFabricLootTableProvider(output, LootContextTypes.ENTITY) {
    override fun accept(exporter: BiConsumer<Identifier, LootTable.Builder>) {
        // nautilus
        export(exporter, HybridAquaticEntityTypes.NAUTILUS) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(Items.NAUTILUS_SHELL)
                    )
            )
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_TENTACLE)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }
        //crustaceans

        export(exporter, HybridAquaticEntityTypes.KARKINOS) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_CRAB)
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0F, 16.0F)))
                    )
            )
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.KARKINOS_CLAW)
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.DECORATOR_CRAB) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_CRAB)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.HORSESHOE_CRAB) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_CRAB)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.DUNGENESS_CRAB) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.DUNGENESS_CRAB_CLAW)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.FIDDLER_CRAB) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.FIDDLER_CRAB_CLAW)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.SPIDER_CRAB) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.SPIDER_CRAB_CLAW)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.GHOST_CRAB) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.GHOST_CRAB_CLAW)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.VAMPIRE_CRAB) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.VAMPIRE_CRAB_CLAW)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.FLOWER_CRAB) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.FLOWER_CRAB_CLAW)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.COCONUT_CRAB) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.COCONUT_CRAB_CLAW)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.YETI_CRAB) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.YETI_CRAB_CLAW)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.LIGHTFOOT_CRAB) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.LIGHTFOOT_CRAB_CLAW)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.LOBSTER) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.LOBSTER_CLAW)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                    )
            )
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_LOBSTER_TAIL)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.CRAYFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_CRAYFISH)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.SHRIMP) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_SHRIMP)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        // cephalopods
        export(exporter, HybridAquaticEntityTypes.VAMPIRE_SQUID) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_TENTACLE)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F)))
                    )
            )
        }
        export(exporter, HybridAquaticEntityTypes.CUTTLEFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(Items.INK_SAC)
                    )
            )
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.CUTTLEBONE)
                    )
            )
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_TENTACLE)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.ARROW_SQUID) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(Items.INK_SAC)
                    )
            )
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_TENTACLE)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.FIREFLY_SQUID) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(Items.GLOW_INK_SAC)
                    )
            )
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_TENTACLE)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.SEA_CUCUMBER) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(Items.SLIME_BALL)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.NUDIBRANCH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(Items.SLIME_BALL)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.SEA_URCHIN) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.SEA_URCHIN_SPINE)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.UNI)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 3.0F)))
                    )
            )
        }

        // end region

        export(exporter, HybridAquaticEntityTypes.GLOWING_SUCKER_OCTOPUS) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_TENTACLE)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F)))
                    )
            )
        }

        // jellyfish
        export(exporter, HybridAquaticEntityTypes.MOON_JELLYFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(Items.SLIME_BALL)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.SEA_NETTLE) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(Items.SLIME_BALL)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.NOMURA_JELLYFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(Items.SLIME_BALL)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.LIONS_MANE_JELLYFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(Items.SLIME_BALL)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.BLUE_JELLYFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(Items.SLIME_BALL)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.COMPASS_JELLYFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(Items.SLIME_BALL)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.BARREL_JELLYFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(Items.SLIME_BALL)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.MAUVE_STINGER) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(Items.SLIME_BALL)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.FRIED_EGG_JELLYFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(Items.SLIME_BALL)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.CAULIFLOWER_JELLYFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(Items.SLIME_BALL)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.ATOLLA_JELLYFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.GLOW_SLIME)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.ANGLERFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.ANGLERFISH)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.DRAGONFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.DRAGONFISH)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.ROCKFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.ROCKFISH)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.SEA_BASS) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.SEA_BASS)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.CLOWNFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.CLOWNFISH)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.SURGEONFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.BLUE_TANG)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.BOXFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.BOXFISH)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.LIONFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.LIONFISH)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.BARRELEYE) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.GLOW_SLIME)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.BARRELEYE)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.STINGRAY) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.BLUE_SPOTTED_STINGRAY)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.GOURAMI) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.GOURAMI)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.BETTA) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.BETTA)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.PEARLFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.PEARLFISH)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.SNAILFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.SNAILFISH)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.DANIO) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.DANIO)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.DISCUS) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.DISCUS)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.TOADFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.TOADFISH)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.STONEFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.STONEFISH)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.CARP) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.CARP)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.GOLDFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.GOLDFISH)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.PARROTFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.PARROTFISH)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.FLASHLIGHT_FISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.FLASHLIGHT_FISH)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.RATFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RATFISH)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.PIRANHA) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.PIRANHA)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.MAHI) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.MAHI)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.OPAH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.OPAH)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.NEEDLEFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.NEEDLEFISH)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.MACKEREL) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.MACKEREL)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.FLYING_FISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.FLYING_FISH)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.TRIGGERFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.TRIGGERFISH)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.SEAHORSE) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.SEAHORSE)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.SUNFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.SUNFISH)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.COELACANTH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.COELACANTH)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.GOLDEN_DORADO) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.GOLDEN_DORADO)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.OARFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.OARFISH)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.OSCAR) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.OSCAR)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.TIGER_BARB) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.TIGER_BARB)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.MORAY_EEL) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.MORAY_EEL)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.SQUIRRELFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.SQUIRRELFISH)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                    )
            )
        }
    }

    /**
     * Exports a loot table for [entityType] to [exporter] using its loot table id.
     */
    private fun export(exporter: BiConsumer<Identifier, LootTable.Builder>, entityType: EntityType<*>, builder: LootTable.Builder.() -> Unit) {
        exporter.accept(entityType.lootTableId, LootTable.builder().apply(builder))
    }

    companion object {
        private val NEEDS_ENTITY_ON_FIRE: EntityPredicate.Builder = EntityPredicate.Builder.create().flags(EntityFlagsPredicate.Builder.create().onFire(true).build())
    }
}
