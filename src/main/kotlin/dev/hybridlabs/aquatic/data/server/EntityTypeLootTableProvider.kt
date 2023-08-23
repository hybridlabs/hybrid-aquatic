package dev.hybridlabs.aquatic.data.server

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
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                    )
            )
        }
        //crustaceans
        export(exporter, HybridAquaticEntityTypes.CRAB) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.CRAB_CLAW)
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
                        ItemEntry.builder(HybridAquaticItems.CRAB_CLAW)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                    )
            )
        }

        // cephalopods
        export(exporter, HybridAquaticEntityTypes.VAMPIRE_SQUID) {
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
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.NUDIBRANCH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(Items.SLIME_BALL)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 1.0F)))
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
        export(exporter, HybridAquaticEntityTypes.MOON_JELLY) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(Items.SLIME_BALL)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 1.0F)))
                    )
            )
        }

        // small fish
        export(exporter, HybridAquaticEntityTypes.ANGLERFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.GLOW_SLIME)
                    )
            )
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_FISH_MEAT)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.DRAGONFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.GLOW_SLIME)
                    )
            )
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_FISH_MEAT)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.ROCKFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_FISH_MEAT)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.CLOWNFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_FISH_MEAT)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.BLUE_TANG) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_FISH_MEAT)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.COWFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_FISH_MEAT)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.LIONFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_FISH_MEAT)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.BARRELEYE) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.GLOW_SLIME)
                    )
            )
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_FISH_MEAT)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.BLUE_SPOTTED_STINGRAY) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_FISH_MEAT)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.FLASHLIGHT_FISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_FISH_MEAT)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.RATFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_FISH_MEAT)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.UNICORN_FISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_FISH_MEAT)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(1.0F, 1.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.PIRANHA) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_FISH_MEAT)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(1.0F, 1.0F)))
                    )
            )
        }

        // medium fish
        export(exporter, HybridAquaticEntityTypes.YELLOWFIN_TUNA) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_FISH_MEAT)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.MAHIMAHI) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_FISH_MEAT)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.OPAH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_FISH_MEAT)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.NEEDLEFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_FISH_MEAT)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.TRIGGERFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_FISH_MEAT)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                    )
            )
        }

        // large fish
        export(exporter, HybridAquaticEntityTypes.SUNFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_FISH_MEAT)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.OARFISH) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_FISH_MEAT)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F)))
                    )
            )
        }

        // sharks
        export(exporter, HybridAquaticEntityTypes.GREAT_WHITE_SHARK) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.SHARK_TOOTH)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F)))
                    )
            )
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_FISH_MEAT)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.TIGER_SHARK) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.SHARK_TOOTH)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
                    )
            )
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_FISH_MEAT)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.HAMMERHEAD_SHARK) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.SHARK_TOOTH)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
                    )
            )
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_FISH_MEAT)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.THRESHER_SHARK) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.SHARK_TOOTH)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
                    )
            )
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_FISH_MEAT)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.BULL_SHARK) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.SHARK_TOOTH)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
                    )
            )
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_FISH_MEAT)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.FRILLED_SHARK) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.SHARK_TOOTH)
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
                    )
            )
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_FISH_MEAT)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F)))
                    )
            )
        }

        export(exporter, HybridAquaticEntityTypes.BASKING_SHARK) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_FISH_MEAT)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F)))
                    )
            )
        }
        export(exporter, HybridAquaticEntityTypes.WHALE_SHARK) {
            pool(
                LootPool.builder()
                    .with(
                        ItemEntry.builder(HybridAquaticItems.RAW_FISH_MEAT)
                            .apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, NEEDS_ENTITY_ON_FIRE)))
                            .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F)))
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
