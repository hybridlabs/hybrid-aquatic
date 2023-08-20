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
