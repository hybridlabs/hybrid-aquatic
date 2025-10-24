@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.aquatic.data.server.worldgen

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.TubeWormBlock
import dev.hybridlabs.aquatic.world.gen.feature.*
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.core.Direction
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.features.FeatureUtils
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.util.random.SimpleWeightedRandomList
import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider
import net.minecraft.world.level.levelgen.feature.stateproviders.NoiseProvider
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider
import net.minecraft.world.level.levelgen.synth.NormalNoise
import java.util.concurrent.CompletableFuture

class ConfiguredFeatureProvider(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<HolderLookup.Provider>,
) : FabricDynamicRegistryProvider(output, registriesFuture) {
    companion object {
        fun bootstrapConfiguredFeatures(bootstrap: BootstrapContext<ConfiguredFeature<*, *>>) {

            FeatureUtils.register(
                bootstrap,
                HybridAquaticConfiguredFeatures.ANEMONE_PATCH, Feature.NO_BONEMEAL_FLOWER,
                RandomPatchConfiguration(
                    3, 3, 3,
                    PlacementUtils.filtered(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockConfiguration(
                            WeightedStateProvider(
                                SimpleWeightedRandomList.builder<BlockState>()
                                    .add(
                                        HybridAquaticBlocks.ANEMONE.get().defaultBlockState()
                                            .setValue(WATERLOGGED, true), 1
                                    )
                                    .add(
                                        HybridAquaticBlocks.STRAWBERRY_ANEMONE.get().defaultBlockState().setValue(
                                            WATERLOGGED,
                                            true
                                        ), 3
                                    )
                                    .add(
                                        HybridAquaticBlocks.GIANT_GREEN_ANEMONE.get().defaultBlockState().setValue(
                                            WATERLOGGED,
                                            true
                                        ), 1
                                    )
                                    .build()
                            )
                        ),
                        BlockPredicate.matchesBlocks(Blocks.WATER)
                    )
                )
            )


            FeatureUtils.register(
                bootstrap,
                HybridAquaticConfiguredFeatures.RED_ALGAE_PATCH,
                HybridAquaticFeatures.RED_ALGAE_PATCH.get(), ProbabilityFeatureConfiguration(
                    0.33f
                )
            )
            FeatureUtils.register(
                bootstrap,
                HybridAquaticConfiguredFeatures.DUNEGRASS_PATCH,
                HybridAquatic.DUNEGRASS_PATCH.get(), ProbabilityFeatureConfiguration(
                    0.5f
                )
            )

            FeatureUtils.register(
                bootstrap,
                HybridAquaticConfiguredFeatures.SARGASSUM,
                HybridAquaticFeatures.SARGASSUM.get(), SargassumFeatureConfig(
                    SimpleStateProvider.simple(HybridAquaticBlocks.SARGASSUM.get())
                )
            )

            FeatureUtils.register(
                bootstrap,
                HybridAquaticConfiguredFeatures.BULL_KELP,
                HybridAquaticFeatures.BULL_KELP.get(), BullKelpFeatureConfig(
                    SimpleStateProvider.simple(HybridAquaticBlocks.BULL_KELP.get())
                )
            )

            FeatureUtils.register(
                bootstrap,
                HybridAquaticConfiguredFeatures.FLOATING_SARGASSUM,
                Feature.RANDOM_PATCH, RandomPatchConfiguration(
                    100, 10, 10,
                    PlacementUtils.filtered(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockConfiguration(
                            NoiseProvider(
                                237L,
                                NormalNoise.NoiseParameters(-5, 5.0, *DoubleArray(0)),
                                1.0f,
                                listOf<BlockState>(
                                    HybridAquaticBlocks.FLOATING_SARGASSUM.get().defaultBlockState()
                                )
                            )
                        ),
                        BlockPredicate.matchesBlocks(Blocks.WATER)
                    )
                )
            )

            FeatureUtils.register(
                bootstrap,
                HybridAquaticConfiguredFeatures.WATER_LETTUCE,
                Feature.RANDOM_PATCH, RandomPatchConfiguration(
                    5, 3, 3,
                    PlacementUtils.filtered(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockConfiguration(
                            SimpleStateProvider.simple(HybridAquaticBlocks.WATER_LETTUCE.get())
                        ),
                        BlockPredicate.matchesBlocks(Blocks.WATER)
                    )
                )
            )

            FeatureUtils.register(
                bootstrap,
                HybridAquaticConfiguredFeatures.JUNGLE_LILY_PAD,
                Feature.RANDOM_PATCH, RandomPatchConfiguration(
                    5, 3, 3,
                    PlacementUtils.filtered(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockConfiguration(
                            SimpleStateProvider.simple(HybridAquaticBlocks.JUNGLE_LILY_PAD.get())
                        ),
                        BlockPredicate.matchesBlocks(Blocks.WATER)
                    )
                )
            )

            FeatureUtils.register(
                bootstrap,
                HybridAquaticConfiguredFeatures.SEA_LETTUCE_PATCH,
                HybridAquaticFeatures.SEA_LETTUCE_PATCH.get(), ProbabilityFeatureConfiguration(
                    0.33f
                )
            )

            FeatureUtils.register(
                bootstrap,
                HybridAquaticConfiguredFeatures.TUBE_SPONGE_PATCH,
                Feature.FLOWER,
                RandomPatchConfiguration(
                    4, 2, 2,
                    PlacementUtils.filtered(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockConfiguration(
                            BlockStateProvider.simple(
                                HybridAquaticBlocks.TUBE_SPONGE.get().defaultBlockState().setValue(WATERLOGGED, true)
                            )
                        ),
                        BlockPredicate.matchesBlocks(Blocks.WATER)
                    )
                )
            )

            FeatureUtils.register(
                bootstrap,
                HybridAquaticConfiguredFeatures.GIANT_CLAM_PATCH,
                Feature.NO_BONEMEAL_FLOWER, RandomPatchConfiguration(
                    2, 2, 2,
                    PlacementUtils.filtered(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockConfiguration(
                            WeightedStateProvider(
                                SimpleWeightedRandomList.builder<BlockState>()
                                    .add(
                                        HybridAquaticBlocks.GIANT_CLAM.get().defaultBlockState()
                                            .setValue(WATERLOGGED, true)
                                            .setValue(HorizontalDirectionalBlock.FACING, Direction.EAST), 1
                                    )
                                    .add(
                                        HybridAquaticBlocks.GIANT_CLAM.get().defaultBlockState()
                                            .setValue(WATERLOGGED, true)
                                            .setValue(HorizontalDirectionalBlock.FACING, Direction.NORTH), 1
                                    )
                                    .build()
                            )
                        ),
                        BlockPredicate.matchesBlocks(Blocks.WATER)
                    )
                )
            )

            FeatureUtils.register(
                bootstrap,
                HybridAquaticConfiguredFeatures.MESSAGE_IN_A_BOTTLE,
                HybridAquaticFeatures.MESSAGE_IN_A_BOTTLE.get(), MessageInABottleFeatureConfig(
                    SimpleStateProvider.simple(HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE.get())
                )
            )

            FeatureUtils.register(
                bootstrap,
                HybridAquaticConfiguredFeatures.THERMAL_VENT_PATCH,
                HybridAquaticFeatures.VENT_PATCH.get(), VentPatchFeatureConfig(
                    SimpleStateProvider.simple(Blocks.TUFF),
                    SimpleStateProvider.simple(HybridAquaticBlocks.THERMAL_VENT.get()),
                    SimpleStateProvider.simple(HybridAquaticBlocks.TUBE_WORM.get()),
                    UniformInt.of(3, 5),
                    ConstantInt.of(2),
                    UniformInt.of(1, 3),
                    ConstantInt.of(4),
                    UniformInt.of(TubeWormBlock.WORMS.min, TubeWormBlock.WORMS.max),
                )
            )
        }
    }

    override fun configure(registries: HolderLookup.Provider, entries: Entries) {
        val reg = registries.lookup(Registries.CONFIGURED_FEATURE).get()

        entries.add(reg.getOrThrow(HybridAquaticConfiguredFeatures.ANEMONE_PATCH))
        entries.add(reg.getOrThrow(HybridAquaticConfiguredFeatures.BULL_KELP))
        entries.add(reg.getOrThrow(HybridAquaticConfiguredFeatures.DUNEGRASS_PATCH))
        entries.add(reg.getOrThrow(HybridAquaticConfiguredFeatures.FLOATING_SARGASSUM))
        entries.add(reg.getOrThrow(HybridAquaticConfiguredFeatures.GIANT_CLAM_PATCH))
        entries.add(reg.getOrThrow(HybridAquaticConfiguredFeatures.JUNGLE_LILY_PAD))
        entries.add(reg.getOrThrow(HybridAquaticConfiguredFeatures.MESSAGE_IN_A_BOTTLE))
        entries.add(reg.getOrThrow(HybridAquaticConfiguredFeatures.RED_ALGAE_PATCH))
        entries.add(reg.getOrThrow(HybridAquaticConfiguredFeatures.SARGASSUM))
        entries.add(reg.getOrThrow(HybridAquaticConfiguredFeatures.SEA_LETTUCE_PATCH))
        entries.add(reg.getOrThrow(HybridAquaticConfiguredFeatures.THERMAL_VENT_PATCH))
        entries.add(reg.getOrThrow(HybridAquaticConfiguredFeatures.TUBE_SPONGE_PATCH))
        entries.add(reg.getOrThrow(HybridAquaticConfiguredFeatures.WATER_LETTUCE))

    }

    override fun getName(): String {
        return "Configured Features"
    }
}
