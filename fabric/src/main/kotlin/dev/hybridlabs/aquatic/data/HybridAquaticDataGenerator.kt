package dev.hybridlabs.aquatic.data

import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.data.client.LanguageProvider
import dev.hybridlabs.aquatic.data.client.ModelProvider
import dev.hybridlabs.aquatic.data.client.SoundProvider
import dev.hybridlabs.aquatic.data.server.AdvancementProvider
import dev.hybridlabs.aquatic.data.server.RecipeProvider
import dev.hybridlabs.aquatic.data.server.loot.BlockLootTableProvider
import dev.hybridlabs.aquatic.data.server.loot.EntityTypeLootTableProvider
import dev.hybridlabs.aquatic.data.server.loot.FishingLootTableProvider
import dev.hybridlabs.aquatic.data.server.loot.GenericLootTableProvider
import dev.hybridlabs.aquatic.data.server.tag.BlockTagProvider
import dev.hybridlabs.aquatic.data.server.tag.EntityTypeTagProvider
import dev.hybridlabs.aquatic.data.server.tag.FluidTagProvider
import dev.hybridlabs.aquatic.data.server.tag.InstrumentTagProvider
import dev.hybridlabs.aquatic.data.server.tag.ItemTagProvider
import dev.hybridlabs.aquatic.data.server.tag.PaintingVariantTagProvider
import dev.hybridlabs.aquatic.data.server.worldgen.BiomeProvider
import dev.hybridlabs.aquatic.data.server.worldgen.ConfiguredFeatureProvider
import dev.hybridlabs.aquatic.data.server.worldgen.PlacedFeatureProvider
import dev.hybridlabs.aquatic.data.structure_spawn_modifier.StructureSpawnModifierProvider
import dev.hybridlabs.aquatic.registry.HARegistryKeys
import dev.hybridlabs.aquatic.world.gen.biome.HABiomes
import dev.hybridlabs.aquatic.world.gen.feature.HAConfiguredFeatures
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.minecraft.core.Registry
import net.minecraft.core.RegistrySetBuilder
import net.minecraft.core.registries.Registries

object HybridAquaticDataGenerator : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(generator: FabricDataGenerator) {
        val pack = generator.createPack()
        pack.addProvider(::LanguageProvider)
        pack.addProvider(::ModelProvider)
        pack.addProvider(::SoundProvider)
        pack.addProvider(::BlockLootTableProvider)
        pack.addProvider(::EntityTypeLootTableProvider)
        pack.addProvider(::FishingLootTableProvider)
        pack.addProvider(::GenericLootTableProvider)
        pack.addProvider(::AdvancementProvider)
        pack.addProvider(::BiomeProvider)
        pack.addProvider(::BlockTagProvider)
        pack.addProvider(::EntityTypeTagProvider)
        pack.addProvider(::PaintingVariantTagProvider)
        pack.addProvider(::ItemTagProvider)
        pack.addProvider(::InstrumentTagProvider)
        pack.addProvider(::ConfiguredFeatureProvider)
        pack.addProvider(::PlacedFeatureProvider)
        pack.addProvider(::RecipeProvider)
        pack.addProvider(::StructureSpawnModifierProvider)
        pack.addProvider(::FluidTagProvider)
    }

    override fun buildRegistry(registryBuilder: RegistrySetBuilder) {
        registryBuilder.add(HARegistryKeys.STRUCTURE_SPAWN_MODIFIER) {}
        registryBuilder.add(Registries.BIOME) {
            HABiomes
        }
        registryBuilder.add(Registries.CONFIGURED_FEATURE) {
            HAConfiguredFeatures
        }
    }

    fun <T> filterHybridAquatic(registry: Registry<T>): (T & Any) -> Boolean {
        return { o ->
            val id = registry.getKey(o)
            id!!.namespace == Constants.MOD_ID
        }
    }
}
