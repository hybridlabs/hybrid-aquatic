@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.aquatic.data.server.worldgen

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.TubeWormBlock
import dev.hybridlabs.aquatic.tag.HybridAquaticBlockTags
import dev.hybridlabs.aquatic.world.gen.feature.*
import dev.hybridlabs.aquatic.world.gen.feature.kelp.BullKelpFeatureConfig
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.core.Direction
import net.minecraft.core.HolderLookup
import net.minecraft.core.HolderSet
import net.minecraft.core.Vec3i
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.tags.BlockTags
import net.minecraft.util.random.SimpleWeightedRandomList
import net.minecraft.util.valueproviders.BiasedToBottomInt
import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED
import net.minecraft.world.level.levelgen.GeodeBlockSettings
import net.minecraft.world.level.levelgen.GeodeCrackSettings
import net.minecraft.world.level.levelgen.GeodeLayerSettings
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.Feature.GEODE
import net.minecraft.world.level.levelgen.feature.Feature.WATERLOGGED_VEGETATION_PATCH
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature
import net.minecraft.world.level.levelgen.feature.configurations.*
import net.minecraft.world.level.levelgen.feature.stateproviders.*
import net.minecraft.world.level.levelgen.placement.CaveSurface
import net.minecraft.world.level.levelgen.placement.CountPlacement
import net.minecraft.world.level.levelgen.placement.HeightmapPlacement
import net.minecraft.world.level.levelgen.placement.PlacementModifier
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest
import net.minecraft.world.level.levelgen.synth.NormalNoise
import java.util.concurrent.CompletableFuture

class ConfiguredFeatureProvider(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<HolderLookup.Provider>,
) : FabricDynamicRegistryProvider(output, registriesFuture) {
    override fun configure(registries: HolderLookup.Provider, entries: Entries) {

        val ANEMONES = entries.add(
            HybridAquaticConfiguredFeatures.ANEMONES,
            ConfiguredFeature(
                Feature.NO_BONEMEAL_FLOWER,
                RandomPatchConfiguration(
                    4, 2, 2,
                    PlacementUtils.filtered(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockConfiguration(
                            WeightedStateProvider(
                                SimpleWeightedRandomList.builder<BlockState>()
                                    .add(
                                        HybridAquaticBlocks.ANEMONE.get().defaultBlockState()
                                            .setValue(WATERLOGGED, true), 1
                                    ).add(
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
                        ANEMONES
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

        entries.add(
            HybridAquaticConfiguredFeatures.DEEP_OCEAN_VEGETATION,
            ConfiguredFeature(
                Feature.SIMPLE_RANDOM_SELECTOR,
                SimpleRandomFeatureConfiguration(
                    HolderSet.direct(
                        PlacementUtils.inlinePlaced(
                            HybridAquaticFeatures.DEEP_CORAL_TREE.get(),
                            FeatureConfiguration.NONE,
                            *arrayOfNulls<PlacementModifier>(0)
                        ),
                        PlacementUtils.inlinePlaced(
                            HybridAquaticFeatures.DEEP_CORAL_CLAW.get(),
                            FeatureConfiguration.NONE,
                            *arrayOfNulls<PlacementModifier>(0)
                        ),
                        PlacementUtils.inlinePlaced(
                            HybridAquaticFeatures.DEEP_CORAL_MUSHROOM.get(),
                            FeatureConfiguration.NONE,
                            *arrayOfNulls<PlacementModifier>(0)
                        ),
                        PlacementUtils.inlinePlaced(
                            HybridAquaticFeatures.DEEP_CORAL_TABLE.get(),
                            FeatureConfiguration.NONE,
                            *arrayOfNulls<PlacementModifier>(0)
                        )
                    )
                )
            )
        )

        entries.add(
            HybridAquaticConfiguredFeatures.BLEACHED_REEF_VEGETATION,
            ConfiguredFeature(
                Feature.SIMPLE_RANDOM_SELECTOR,
                SimpleRandomFeatureConfiguration(
                    HolderSet.direct(
                        PlacementUtils.inlinePlaced(
                            HybridAquaticFeatures.BLEACHED_CORAL_TREE.get(),
                            FeatureConfiguration.NONE,
                            *arrayOfNulls<PlacementModifier>(0)
                        ),
                        PlacementUtils.inlinePlaced(
                            HybridAquaticFeatures.BLEACHED_CORAL_CLAW.get(),
                            FeatureConfiguration.NONE,
                            *arrayOfNulls<PlacementModifier>(0)
                        ),
                        PlacementUtils.inlinePlaced(
                            HybridAquaticFeatures.BLEACHED_CORAL_MUSHROOM.get(),
                            FeatureConfiguration.NONE,
                            *arrayOfNulls<PlacementModifier>(0)
                        ),
                        PlacementUtils.inlinePlaced(
                            HybridAquaticFeatures.BLEACHED_CORAL_TABLE.get(),
                            FeatureConfiguration.NONE,
                            *arrayOfNulls<PlacementModifier>(0)
                        )
                    )
                )
            )
        )

        entries.add(
            HybridAquaticConfiguredFeatures.CORAL_REEF_VEGETATION,
            ConfiguredFeature(
                Feature.SIMPLE_RANDOM_SELECTOR,
                SimpleRandomFeatureConfiguration(
                    HolderSet.direct(
                        PlacementUtils.inlinePlaced(
                            HybridAquaticFeatures.REEF_CORAL_TREE.get(),
                            FeatureConfiguration.NONE,
                            *arrayOfNulls<PlacementModifier>(0)
                        ),
                        PlacementUtils.inlinePlaced(
                            HybridAquaticFeatures.REEF_CORAL_CLAW.get(),
                            FeatureConfiguration.NONE,
                            *arrayOfNulls<PlacementModifier>(0)
                        ),
                        PlacementUtils.inlinePlaced(
                            HybridAquaticFeatures.REEF_CORAL_MUSHROOM.get(),
                            FeatureConfiguration.NONE,
                            *arrayOfNulls<PlacementModifier>(0)
                        ),
                        PlacementUtils.inlinePlaced(
                            HybridAquaticFeatures.REEF_CORAL_TABLE.get(),
                            FeatureConfiguration.NONE,
                            *arrayOfNulls<PlacementModifier>(0)
                        )
                    )
                )
            )
        )

        entries.add(
            HybridAquaticConfiguredFeatures.RED_MEADOW_VEGETATION,
            ConfiguredFeature(
                HybridAquaticFeatures.RED_ALGAE_PATCH.get(), ProbabilityFeatureConfiguration(
                    0.66f
                )
            )
        )

        entries.add(
            HybridAquaticConfiguredFeatures.AERATED_SAND_CIRCLE,
            ConfiguredFeature(
                Feature.DISK,
                DiskConfiguration(
                    RuleBasedBlockStateProvider.simple(HybridAquaticBlocks.AERATED_SAND.get()),
                    BlockPredicate.matchesBlocks(listOf<Block>(HybridAquaticBlocks.GRASSY_SAND.get())),
                    UniformInt.of(1, 3),
                    1
                )
            )
        )

        entries.add(
            HybridAquaticConfiguredFeatures.SAND_CIRCLE,
            ConfiguredFeature(
                Feature.DISK,
                DiskConfiguration(
                    RuleBasedBlockStateProvider.simple(Blocks.SAND),
                    BlockPredicate.matchesBlocks(listOf<Block>(HybridAquaticBlocks.GRASSY_SAND.get())),
                    UniformInt.of(3, 8),
                    1
                )
            )
        )

        entries.add(
            HybridAquaticConfiguredFeatures.SULFUR_DEPOSIT,
            ConfiguredFeature(
                Feature.ORE,
                OreConfiguration(
                    TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES),
                    HybridAquaticBlocks.CRYSTALLINE_SULFUR.get().defaultBlockState(),
                    20,
                    0.0f
                )
            )
        )

        entries.add(
            HybridAquaticConfiguredFeatures.SUSPICIOUS_SAND_DISK,
            ConfiguredFeature(
                HybridAquaticFeatures.SUSPICIOUS_SAND_DISK.get(),
                DiskConfiguration(
                    RuleBasedBlockStateProvider.simple(Blocks.SUSPICIOUS_SAND),
                    BlockPredicate.matchesBlocks(listOf<Block>(Blocks.SAND)),
                    UniformInt.of(1, 2),
                    1
                )
            )
        )

        entries.add(
            HybridAquaticConfiguredFeatures.SUSPICIOUS_RED_SAND_DISK,
            ConfiguredFeature(
                HybridAquaticFeatures.SUSPICIOUS_RED_SAND_DISK.get(),
                DiskConfiguration(
                    RuleBasedBlockStateProvider.simple(HybridAquaticBlocks.SUSPICIOUS_RED_SAND.get()),
                    BlockPredicate.matchesBlocks(listOf<Block>(Blocks.RED_SAND, Blocks.CLAY)),
                    UniformInt.of(3, 5),
                    1
                )
            )
        )

        entries.add(
            HybridAquaticConfiguredFeatures.DUNEGRASS_PATCH,
            ConfiguredFeature(
                HybridAquatic.DUNEGRASS_PATCH.get(), ProbabilityFeatureConfiguration(
                    0.5f
                )
            )
        )

        //#region Sargassum
        entries.add(
            HybridAquaticConfiguredFeatures.SARGASSUM,
            ConfiguredFeature(
                HybridAquaticFeatures.SARGASSUM.get(),
                _root_ide_package_.dev.hybridlabs.aquatic.world.gen.feature.kelp.SargassumFeatureConfig(
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
                    15, 5, 5,
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
            HybridAquaticConfiguredFeatures.WATER_HYACINTH,
            ConfiguredFeature(
                Feature.RANDOM_PATCH, RandomPatchConfiguration(
                    15, 5, 5,
                    PlacementUtils.filtered(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockConfiguration(
                            SimpleStateProvider.simple(HybridAquaticBlocks.WATER_HYACINTH.get())
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
                    10, 5, 5,
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

        entries.add(
            HybridAquaticConfiguredFeatures.GLASS_SPONGE_PATCH,
            ConfiguredFeature(
                Feature.FLOWER,
                RandomPatchConfiguration(
                    4, 4, 2,
                    PlacementUtils.filtered(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockConfiguration(
                            BlockStateProvider.simple(
                                HybridAquaticBlocks.GLASS_SPONGE.get().defaultBlockState().setValue(WATERLOGGED, true)
                            )
                        ),
                        BlockPredicate.matchesBlocks(Blocks.WATER)
                    )
                )
            )
        )

        entries.add(
            HybridAquaticConfiguredFeatures.HARP_SPONGE_PATCH,
            ConfiguredFeature(
                Feature.FLOWER,
                RandomPatchConfiguration(
                    4, 4, 2,
                    PlacementUtils.filtered(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockConfiguration(
                            BlockStateProvider.simple(
                                HybridAquaticBlocks.HARP_SPONGE.get().defaultBlockState().setValue(WATERLOGGED, true)
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

        // giant clam patch
        entries.add(
            HybridAquaticConfiguredFeatures.OYSTER_BED,
            ConfiguredFeature(
                Feature.NO_BONEMEAL_FLOWER, RandomPatchConfiguration(
                    2, 2, 2,
                    PlacementUtils.filtered(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockConfiguration(
                            WeightedStateProvider(
                                SimpleWeightedRandomList.builder<BlockState>()
                                    .add(
                                        HybridAquaticBlocks.OYSTER.get().defaultBlockState()
                                            .setValue(WATERLOGGED, true)
                                            .setValue(HorizontalDirectionalBlock.FACING, Direction.NORTH), 1
                                    )
                                    .add(
                                        HybridAquaticBlocks.OYSTER.get().defaultBlockState()
                                            .setValue(WATERLOGGED, true)
                                            .setValue(HorizontalDirectionalBlock.FACING, Direction.SOUTH), 1
                                    )
                                    .add(
                                        HybridAquaticBlocks.OYSTER.get().defaultBlockState()
                                            .setValue(WATERLOGGED, true)
                                            .setValue(HorizontalDirectionalBlock.FACING, Direction.EAST), 1
                                    )
                                    .add(
                                        HybridAquaticBlocks.OYSTER.get().defaultBlockState()
                                            .setValue(WATERLOGGED, true)
                                            .setValue(HorizontalDirectionalBlock.FACING, Direction.WEST), 1
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
                    UniformInt.of(3, 5),
                    ConstantInt.of(2),
                    UniformInt.of(1, 3),
                    ConstantInt.of(4),
                    UniformInt.of(TubeWormBlock.WORMS.min, TubeWormBlock.WORMS.max),
                )
            )
        )

        // coral mound base
        entries.add(
            HybridAquaticConfiguredFeatures.CORAL_MOUND, ConfiguredFeature(
                Feature.RANDOM_PATCH, RandomPatchConfiguration(
                    6, 7, 0, PlacementUtils.inlinePlaced(
                        Feature.DISK, DiskConfiguration(
                            RuleBasedBlockStateProvider(
                                SimpleStateProvider.simple(HybridAquaticBlocks.CORALSTONE.get()), listOf(
                                    RuleBasedBlockStateProvider.Rule(
                                        BlockPredicate.not(
                                            BlockPredicate.matchesBlocks(
                                                Vec3i(0, -1, 0),
                                                listOf(Blocks.SAND, HybridAquaticBlocks.CORALSTONE.get())
                                            )
                                        ),
                                        SimpleStateProvider.simple(Blocks.WATER),
                                    )
                                )
                            ), BlockPredicate.matchesBlocks(
                                Vec3i(0, -1, 0), listOf(Blocks.SAND, HybridAquaticBlocks.CORALSTONE.get())
                            ), UniformInt.of(3, 5), 1
                        ),
                        CountPlacement.of(1), HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR)
                    )
                )
            )
        )

        // coral mound coral layers
        entries.add(
            HybridAquaticConfiguredFeatures.CORAL_LAYER,
            ConfiguredFeature(
                Feature.RANDOM_SELECTOR,
                RandomFeatureConfiguration(
                    listOf(
                        Blocks.BRAIN_CORAL_BLOCK,
                        Blocks.HORN_CORAL_BLOCK,
                        Blocks.BUBBLE_CORAL_BLOCK,
                        Blocks.FIRE_CORAL_BLOCK,
                        Blocks.TUBE_CORAL_BLOCK,
                        HybridAquaticBlocks.SUN_CORAL_BLOCK.get(),
                        HybridAquaticBlocks.LEAF_CORAL_BLOCK.get(),
                        HybridAquaticBlocks.ROSE_CORAL_BLOCK.get(),
                        HybridAquaticBlocks.BUTTON_CORAL_BLOCK.get()
                    ).map { block ->
                        WeightedPlacedFeature(
                            PlacementUtils.inlinePlaced(
                                Feature.DISK, DiskConfiguration(
                                    RuleBasedBlockStateProvider(
                                        SimpleStateProvider.simple(HybridAquaticBlocks.CORALSTONE.get()),
                                        listOf(
                                            RuleBasedBlockStateProvider.Rule(
                                                BlockPredicate.allOf(
                                                    BlockPredicate.matchesTag(
                                                        Vec3i(0, 1, 0),
                                                        HybridAquaticBlockTags.CORAL_MOUND_BASE_BLOCKS,
                                                    ),
                                                    BlockPredicate.matchesTag(
                                                        Vec3i(0, -1, 0),
                                                        HybridAquaticBlockTags.CORAL_MOUND_BASE_BLOCKS,
                                                    ),
                                                ),
                                                WeightedStateProvider(
                                                    SimpleWeightedRandomList.builder<BlockState>().add(
                                                        HybridAquaticBlocks.CORALSTONE.get().defaultBlockState(),
                                                        2
                                                    ).add(
                                                        block.defaultBlockState(),
                                                        1

                                                    )
                                                )
                                            ),
                                            RuleBasedBlockStateProvider.Rule(
                                                BlockPredicate.allOf(
                                                    BlockPredicate.anyOf(
                                                        BlockPredicate.matchesBlocks(
                                                            Vec3i(0, 1, 0),
                                                            Blocks.WATER
                                                        ),
                                                        BlockPredicate.matchesBlocks(
                                                            Vec3i(0, 2, 0),
                                                            Blocks.WATER
                                                        ),
                                                    ),
                                                    BlockPredicate.allOf(
                                                        BlockPredicate.matchesTag(
                                                            Vec3i(0, -1, 0),
                                                            HybridAquaticBlockTags.CORAL_MOUND_BASE_BLOCKS,
                                                        ),
                                                        BlockPredicate.matchesTag(
                                                            Vec3i(0, -2, 0),
                                                            HybridAquaticBlockTags.CORAL_MOUND_BASE_BLOCKS,
                                                        ),
                                                    ),
                                                ),
                                                SimpleStateProvider.simple(block),
                                            )
                                        )
                                    ),
                                    BlockPredicate.matchesTag(
                                        Vec3i(0, -1, 0),
                                        HybridAquaticBlockTags.CORAL_MOUND_BLOCKS,
                                    ),
                                    ConstantInt.of(8),
                                    4
                                )
                            ),
                            0.2f
                        )
                    },
                    PlacementUtils.inlinePlaced(Feature.NO_OP, NoneFeatureConfiguration())
                )
            )
        )

        // mound base
        entries.add(
            HybridAquaticConfiguredFeatures.MOUND, ConfiguredFeature(
                Feature.RANDOM_PATCH, RandomPatchConfiguration(
                    6, 7, 0, PlacementUtils.inlinePlaced(
                        Feature.DISK, DiskConfiguration(
                            RuleBasedBlockStateProvider(
                                WeightedStateProvider(
                                    SimpleWeightedRandomList.builder<BlockState>()
                                        .add(Blocks.DEAD_HORN_CORAL_BLOCK.defaultBlockState(), 1)
                                        .add(Blocks.DEAD_FIRE_CORAL_BLOCK.defaultBlockState(), 1)
                                        .add(Blocks.DEAD_BUBBLE_CORAL_BLOCK.defaultBlockState(), 1)
                                        .add(Blocks.DEAD_BRAIN_CORAL_BLOCK.defaultBlockState(), 1)
                                        .add(Blocks.DEAD_TUBE_CORAL_BLOCK.defaultBlockState(), 1)
                                        .add(Blocks.STONE.defaultBlockState(), 2)
                                ), listOf(
                                    RuleBasedBlockStateProvider.Rule(
                                        BlockPredicate.not(
                                            BlockPredicate.matchesBlocks(
                                                Vec3i(0, -1, 0),
                                                listOf(
                                                    Blocks.SAND,
                                                    Blocks.DEAD_HORN_CORAL_BLOCK,
                                                    Blocks.DEAD_FIRE_CORAL_BLOCK,
                                                    Blocks.DEAD_BRAIN_CORAL_BLOCK,
                                                    Blocks.DEAD_BUBBLE_CORAL_BLOCK,
                                                    Blocks.DEAD_TUBE_CORAL_BLOCK,
                                                    HybridAquaticBlocks.GRASSY_SAND.get(),
                                                )
                                            )
                                        ),
                                        SimpleStateProvider.simple(Blocks.WATER),
                                    )
                                )
                            ), BlockPredicate.matchesBlocks(
                                Vec3i(0, -1, 0), listOf(
                                    Blocks.SAND,
                                    Blocks.DEAD_HORN_CORAL_BLOCK,
                                    Blocks.DEAD_FIRE_CORAL_BLOCK,
                                    Blocks.DEAD_BRAIN_CORAL_BLOCK,
                                    Blocks.DEAD_BUBBLE_CORAL_BLOCK,
                                    Blocks.DEAD_TUBE_CORAL_BLOCK,
                                    HybridAquaticBlocks.GRASSY_SAND.get())
                            ), UniformInt.of(3, 5), 1
                        ),
                        CountPlacement.of(1), HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR)
                    )
                )
            )
        )

        // white mound
        entries.add(
            HybridAquaticConfiguredFeatures.WHITE_MOUND, ConfiguredFeature(
                Feature.RANDOM_PATCH, RandomPatchConfiguration(
                    6, 7, 0, PlacementUtils.inlinePlaced(
                        Feature.DISK, DiskConfiguration(
                            RuleBasedBlockStateProvider(
                                WeightedStateProvider(
                                    SimpleWeightedRandomList.builder<BlockState>()
                                        .add(Blocks.CALCITE.defaultBlockState(), 5)
                                        .add(Blocks.DIORITE.defaultBlockState(), 3)
                                        .add(HybridAquaticBlocks.WHITE_SANDSTONE.get().defaultBlockState(), 1)
                                ), listOf(
                                    RuleBasedBlockStateProvider.Rule(
                                        BlockPredicate.not(
                                            BlockPredicate.matchesBlocks(
                                                Vec3i(0, -1, 0),
                                                listOf(
                                                    Blocks.CALCITE,
                                                    Blocks.DIORITE,
                                                    HybridAquaticBlocks.WHITE_SAND.get(),
                                                    HybridAquaticBlocks.WHITE_SANDSTONE.get(),
                                                )
                                            )
                                        ),
                                        SimpleStateProvider.simple(Blocks.WATER),
                                    )
                                )
                            ), BlockPredicate.matchesBlocks(
                                Vec3i(0, -1, 0), listOf(
                                    Blocks.CALCITE,
                                    Blocks.DIORITE,
                                    HybridAquaticBlocks.WHITE_SAND.get(),
                                    HybridAquaticBlocks.WHITE_SANDSTONE.get())
                            ), UniformInt.of(3, 5), 1
                        ),
                        CountPlacement.of(1), HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR)
                    )
                )
            )
        )
    }

    override fun getName(): String {
        return "Configured Features"
    }
}
