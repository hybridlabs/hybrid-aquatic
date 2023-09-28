@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.aquatic.data.server

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.tag.HybridAquaticBlockTags
import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticConfiguredFeatures
import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticFeatures
import dev.hybridlabs.aquatic.world.gen.feature.MessageInABottleFeatureConfig
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.registry.RegistryWrapper
import net.minecraft.state.property.Properties
import net.minecraft.world.gen.blockpredicate.BlockPredicate
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.PlacedFeatures
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig
import net.minecraft.world.gen.stateprovider.BlockStateProvider
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider
import java.util.concurrent.CompletableFuture

class ConfiguredFeatureProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) : FabricDynamicRegistryProvider(output, registriesFuture) {
    override fun configure(registries: RegistryWrapper.WrapperLookup, entries: Entries) {
        // anemone patch
        entries.add(
            HybridAquaticConfiguredFeatures.ANEMONE_PATCH,
            ConfiguredFeature(
                Feature.RANDOM_PATCH, RandomPatchFeatureConfig(
                    6, 2, 2,
                    PlacedFeatures.createEntry(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockFeatureConfig(
                            BlockStateProvider.of(HybridAquaticBlocks.ANEMONE.defaultState.with(Properties.WATERLOGGED, true))
                        ),
                        BlockPredicate.matchingBlockTag(HybridAquaticBlockTags.ANEMONES_GENERATE_IN)
                    )
                )
            )
        )

        // message in a bottle
        entries.add(
            HybridAquaticConfiguredFeatures.MESSAGE_IN_A_BOTTLE,
            ConfiguredFeature(
                HybridAquaticFeatures.MESSAGE_IN_A_BOTTLE, MessageInABottleFeatureConfig(
                    SimpleBlockStateProvider.of(HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE)
                )
            )
        )
    }

    override fun getName(): String {
        return "Configured Features"
    }
}
