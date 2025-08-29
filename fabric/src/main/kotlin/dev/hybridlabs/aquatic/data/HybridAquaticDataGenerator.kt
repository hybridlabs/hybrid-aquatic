package dev.hybridlabs.aquatic.data

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.SeaMessage
import dev.hybridlabs.aquatic.data.client.LanguageProvider
import dev.hybridlabs.aquatic.data.client.ModelProvider
import dev.hybridlabs.aquatic.data.server.AdvancementProvider
import dev.hybridlabs.aquatic.data.server.ConfiguredFeatureProvider
import dev.hybridlabs.aquatic.data.server.PlacedFeatureProvider
import dev.hybridlabs.aquatic.data.server.RecipeProvider
import dev.hybridlabs.aquatic.data.server.loot.BlockLootTableProvider
import dev.hybridlabs.aquatic.data.server.loot.EntityTypeLootTableProvider
import dev.hybridlabs.aquatic.data.server.loot.FishingLootTableProvider
import dev.hybridlabs.aquatic.data.server.loot.GenericLootTableProvider
import dev.hybridlabs.aquatic.data.server.seamessage.SeaMessageProvider
import dev.hybridlabs.aquatic.data.server.tag.BiomeTagProvider
import dev.hybridlabs.aquatic.data.server.tag.BlockTagProvider
import dev.hybridlabs.aquatic.data.server.tag.EntityTypeTagProvider
import dev.hybridlabs.aquatic.data.server.tag.ItemTagProvider
import dev.hybridlabs.aquatic.registry.HybridAquaticRegistryKeys
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.minecraft.core.Registry
import net.minecraft.core.RegistrySetBuilder

object HybridAquaticDataGenerator : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(generator: FabricDataGenerator) {
        val pack = generator.createPack()
        pack.addProvider(::LanguageProvider)
        pack.addProvider(::ModelProvider)
        pack.addProvider(::BlockLootTableProvider)
        pack.addProvider(::EntityTypeLootTableProvider)
        pack.addProvider(::FishingLootTableProvider)
        pack.addProvider(::GenericLootTableProvider)
        pack.addProvider(::AdvancementProvider)
        pack.addProvider(::BiomeTagProvider)
        pack.addProvider(::BlockTagProvider)
        pack.addProvider(::ItemTagProvider)
        pack.addProvider(::EntityTypeTagProvider)
        pack.addProvider(::ConfiguredFeatureProvider)
        pack.addProvider(::PlacedFeatureProvider)
        pack.addProvider(::RecipeProvider)
        pack.addProvider(::SeaMessageProvider)
    }

    override fun buildRegistry(registryBuilder: RegistrySetBuilder) {
        registryBuilder.add<SeaMessage>(HybridAquaticRegistryKeys.SEA_MESSAGE) {}
    }

    fun <T> filterHybridAquatic(registry: Registry<T>): (T & Any) -> Boolean {
        return { o ->
            val id = registry.getKey(o)
            id!!.namespace == HybridAquatic.MOD_ID
        }
    }
}
