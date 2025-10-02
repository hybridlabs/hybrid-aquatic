@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.aquatic.data.server.worldgen

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.TubeWormBlock
import dev.hybridlabs.aquatic.tag.HybridAquaticBlockTags
import dev.hybridlabs.aquatic.world.gen.feature.*
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.core.Direction
import net.minecraft.core.HolderLookup
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.tags.BlockTags
import net.minecraft.util.random.SimpleWeightedRandomList
import net.minecraft.util.valueproviders.BiasedToBottomInt
import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED
import net.minecraft.world.level.levelgen.GeodeBlockSettings
import net.minecraft.world.level.levelgen.GeodeCrackSettings
import net.minecraft.world.level.levelgen.GeodeLayerSettings
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.Feature.GEODE
import net.minecraft.world.level.levelgen.feature.Feature.WATERLOGGED_VEGETATION_PATCH
import net.minecraft.world.level.levelgen.feature.configurations.*
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider
import net.minecraft.world.level.levelgen.feature.stateproviders.NoiseProvider
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider
import net.minecraft.world.level.levelgen.placement.CaveSurface
import net.minecraft.world.level.levelgen.synth.NormalNoise
import java.util.concurrent.CompletableFuture

class ConfiguredFeatureProvider(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<HolderLookup.Provider>,
) : FabricDynamicRegistryProvider(output, registriesFuture) {
    override fun configure(registries: HolderLookup.Provider, entries: Entries) {
        // anemone patch
        entries.add(
            HybridAquaticConfiguredFeatures.ANEMONE_PATCH,
            ConfiguredFeature(
                Feature.NO_BONEMEAL_FLOWER,
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
        )

        val GREEN = entries.add(
            HybridAquaticConfiguredFeatures.GREEN_ANEMONE_PATCH,
            ConfiguredFeature(
                Feature.NO_BONEMEAL_FLOWER,
                RandomPatchConfiguration(
                    3, 3, 3,
                    PlacementUtils.filtered(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockConfiguration(
                            WeightedStateProvider(
                                SimpleWeightedRandomList.builder<BlockState>()
                                    .add(
                                        HybridAquaticBlocks.GIANT_GREEN_ANEMONE.get().defaultBlockState()
                                            .setValue(WATERLOGGED, true), 1
                                    )
                                    .add(
                                        HybridAquaticBlocks.STRAWBERRY_ANEMONE.get().defaultBlockState().setValue(
                                            WATERLOGGED,
                                            true
                                        ), 3
                                    )
                                    .build()
                            )
                        ),
                        BlockPredicate.matchesBlocks(Blocks.WATER)
                    )
                )
            )
        )

        entries.add(
            HybridAquaticConfiguredFeatures.RED_ALGAE_PATCH,
            ConfiguredFeature(
                HybridAquaticFeatures.RED_ALGAE_PATCH.get(), ProbabilityFeatureConfiguration(
                    0.33f
                )
            )
        )

        entries.add(
            HybridAquaticConfiguredFeatures.DUNEGRASS_PATCH,
            ConfiguredFeature(
                HybridAquaticFeatures.DUNEGRASS_PATCH.get(), ProbabilityFeatureConfiguration(
                    0.5f
                )
            )
        )

        //#region Sargassum

        entries.add(
            HybridAquaticConfiguredFeatures.SARGASSUM,
            ConfiguredFeature(
                HybridAquaticFeatures.SARGASSUM.get(), SargassumFeatureConfig(
                    SimpleStateProvider.simple(HybridAquaticBlocks.SARGASSUM.get())
                )
            )
        )

        entries.add(
            HybridAquaticConfiguredFeatures.BULL_KELP,
            ConfiguredFeature(
                HybridAquaticFeatures.BULL_KELP.get(), BullKelpFeatureConfig(
                    SimpleStateProvider.simple(HybridAquaticBlocks.BULL_KELP.get())
                )
            )
        )

        entries.add(
            HybridAquaticConfiguredFeatures.FLOATING_SARGASSUM,
            ConfiguredFeature(
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
        )

        entries.add(
            HybridAquaticConfiguredFeatures.WATER_LETTUCE,
            ConfiguredFeature(
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
        )

        entries.add(
            HybridAquaticConfiguredFeatures.JUNGLE_LILY_PAD,
            ConfiguredFeature(
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
        )

        entries.add(
            HybridAquaticConfiguredFeatures.GLOWING_PLANKTON,
            ConfiguredFeature(
                Feature.RANDOM_PATCH, RandomPatchConfiguration(
                    100, 12, 12,
                    PlacementUtils.filtered(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockConfiguration(
                            BlockStateProvider.simple(HybridAquaticBlocks.GLOWING_PLANKTON.get().defaultBlockState())
                        ),
                        BlockPredicate.matchesBlocks(Blocks.WATER)
                    )
                )
            )
        )

        entries.add(
            HybridAquaticConfiguredFeatures.SEA_LETTUCE_PATCH,
            ConfiguredFeature(
                HybridAquaticFeatures.SEA_LETTUCE_PATCH.get(), ProbabilityFeatureConfiguration(
                    0.33f
                )
            )
        )

        // tube sponge patch
        entries.add(
            HybridAquaticConfiguredFeatures.TUBE_SPONGE_PATCH,
            ConfiguredFeature(
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
        )

        // giant clam patch
        entries.add(
            HybridAquaticConfiguredFeatures.GIANT_CLAM_PATCH,
            ConfiguredFeature(
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
        )

        // message in a bottle
        entries.add(
            HybridAquaticConfiguredFeatures.MESSAGE_IN_A_BOTTLE,
            ConfiguredFeature(
                HybridAquaticFeatures.MESSAGE_IN_A_BOTTLE.get(), MessageInABottleFeatureConfig(
                    SimpleStateProvider.simple(HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE.get())
                )
            )
        )

        // thermal vents
        entries.add(
            HybridAquaticConfiguredFeatures.THERMAL_VENT_PATCH,
            ConfiguredFeature(
                HybridAquaticFeatures.VENT_PATCH.get(), VentPatchFeatureConfig(
                    SimpleStateProvider.simple(Blocks.TUFF),
                    SimpleStateProvider.simple(HybridAquaticBlocks.THERMAL_VENT.get()),
                    SimpleStateProvider.simple(HybridAquaticBlocks.TUBE_WORM.get()),
                    UniformInt.of(2, 3),
                    ConstantInt.of(3),
                    UniformInt.of(1, 3),
                    ConstantInt.of(4),
                    UniformInt.of(TubeWormBlock.WORMS.min, TubeWormBlock.WORMS.max),
                )
            )
        )

        entries.add(
            HybridAquaticConfiguredFeatures.TIDE_POOLS,
            ConfiguredFeature(
                WATERLOGGED_VEGETATION_PATCH, VegetationPatchConfiguration(
                    HybridAquaticBlockTags.TIDE_POOL_REPLACEABLE,
                    WeightedStateProvider(
                        SimpleWeightedRandomList.builder<BlockState>()
                            .add(HybridAquaticBlocks.SHORESTONE.get().defaultBlockState(), 3)
                            .add(HybridAquaticBlocks.BARNACLE_SHORESTONE.get().defaultBlockState(), 1)
                            .build()
                    ),
                    PlacementUtils.inlinePlaced(
                        GREEN
                    ),
                    CaveSurface.FLOOR,
                    BiasedToBottomInt.of(1, 7),
                    0.0f,
                    5,
                    0.2f,
                    BiasedToBottomInt.of(3, 4),
                    0.5f
                )
            )
        )

        entries.add(
            HybridAquaticConfiguredFeatures.BOULDER,
            ConfiguredFeature(
                GEODE, GeodeConfiguration(
                    GeodeBlockSettings(
                        BlockStateProvider.simple(Blocks.STONE),
                        BlockStateProvider.simple(Blocks.STONE),
                        BlockStateProvider.simple(Blocks.STONE),
                        BlockStateProvider.simple(Blocks.STONE),
                        BlockStateProvider.simple(HybridAquaticBlocks.SHORESTONE.get()),
                        mutableListOf(Blocks.STONE.defaultBlockState()),
                        BlockTags.FEATURES_CANNOT_REPLACE,
                        BlockTags.GEODE_INVALID_BLOCKS
                    ),
                    GeodeLayerSettings(0.5, 1.0, 1.25, 1.75),
                    GeodeCrackSettings(0.0, 0.0, 0),
                    0.0,
                    0.0,
                    false,
                    ConstantInt.of(5),
                    UniformInt.of(3, 4),
                    BiasedToBottomInt.of(1, 2),
                    -16,
                    16,
                    0.07,
                    50
                )
            )
        )
    }

    override fun getName(): String {
        return "Configured Features"
    }
}
