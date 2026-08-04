@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.aquatic.data.server.worldgen

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.block.TubeWormBlock
import dev.hybridlabs.aquatic.fluid.HAPlatformFluids
import dev.hybridlabs.aquatic.tag.HABlockTags
import dev.hybridlabs.aquatic.world.gen.feature.*
import dev.hybridlabs.aquatic.world.gen.feature.kelp.BullKelpFeatureConfig
import dev.hybridlabs.aquatic.world.gen.feature.kelp.DelesseriaFeatureConfig
import dev.hybridlabs.aquatic.world.gen.feature.kelp.SargassumFeatureConfig
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.core.Direction
import net.minecraft.core.HolderLookup
import net.minecraft.core.HolderSet
import net.minecraft.core.Vec3i
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.features.FeatureUtils
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
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockStateMatchTest
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest
import net.minecraft.world.level.levelgen.synth.NormalNoise
import java.util.concurrent.CompletableFuture

class ConfiguredFeatureProvider(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<HolderLookup.Provider>,
) : FabricDynamicRegistryProvider(output, registriesFuture) {
    companion object {
        fun bootstrapConfiguredFeatures(bootstrap: BootstrapContext<ConfiguredFeature<*, *>>) {

            val ANEMONES = FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.ANEMONES,

                Feature.NO_BONEMEAL_FLOWER,
                RandomPatchConfiguration(
                    4, 2, 2,
                    PlacementUtils.filtered(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockConfiguration(
                            WeightedStateProvider(
                                SimpleWeightedRandomList.builder<BlockState>()
                                    .add(
                                        HABlocks.ANEMONE.get().defaultBlockState()
                                            .setValue(WATERLOGGED, true), 1
                                    ).add(
                                        HABlocks.GIANT_GREEN_ANEMONE.get().defaultBlockState()
                                            .setValue(WATERLOGGED, true), 1
                                    )
                                    .add(
                                        HABlocks.STRAWBERRY_ANEMONE.get().defaultBlockState().setValue(
                                            WATERLOGGED,
                                            true
                                        ), 3
                                    )
                                    .build()
                            )
                        ),
                        BlockPredicate.allOf(
                            BlockPredicate.matchesBlocks(Blocks.WATER),
                            BlockPredicate.hasSturdyFace(Vec3i(0, -1, 0), Direction.UP)
                        )
                    )
                )
            )

            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.RED_BRINE_POOL,
                HAFeatures.BRINE_LAKE.get(),
                BrineLakeFeatureConfig(
                    BlockStateProvider.simple(
                        HABlocks.RED_BRINESTONE.get()
                    ),
                    BlockStateProvider.simple(
                        HAPlatformFluids.BRINE_STILL.get()
                            .defaultFluidState()
                            .createLegacyBlock()
                    )
                )
            )

            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.ORANGE_BRINE_POOL,
                HAFeatures.BRINE_LAKE.get(),
                BrineLakeFeatureConfig(
                    BlockStateProvider.simple(
                        HABlocks.ORANGE_BRINESTONE.get()
                    ),
                    BlockStateProvider.simple(
                        HAPlatformFluids.BRINE_STILL.get()
                            .defaultFluidState()
                            .createLegacyBlock()
                    )
                )
            )

            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.YELLOW_BRINE_POOL,
                HAFeatures.BRINE_LAKE.get(),
                BrineLakeFeatureConfig(
                    BlockStateProvider.simple(
                        HABlocks.YELLOW_BRINESTONE.get()
                    ),
                    BlockStateProvider.simple(
                        HAPlatformFluids.BRINE_STILL.get()
                            .defaultFluidState()
                            .createLegacyBlock()
                    )
                )
            )

            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.BRINE_POOL,
                Feature.SIMPLE_RANDOM_SELECTOR,
                SimpleRandomFeatureConfiguration(
                    HolderSet.direct(
                        bootstrap.lookup(Registries.PLACED_FEATURE).get(HAPlacedFeatures.RED_BRINE_POOL).get(),
                        bootstrap.lookup(Registries.PLACED_FEATURE).get(HAPlacedFeatures.YELLOW_BRINE_POOL).get(),
                        bootstrap.lookup(Registries.PLACED_FEATURE).get(HAPlacedFeatures.ORANGE_BRINE_POOL).get()
                    )
                )
            )

            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.TIDE_POOLS,

                WATERLOGGED_VEGETATION_PATCH, VegetationPatchConfiguration(
                    HABlockTags.TIDE_POOL_REPLACEABLE,
                    WeightedStateProvider(
                        SimpleWeightedRandomList.builder<BlockState>()
                            .add(HABlocks.SHORESTONE.get().defaultBlockState(), 3)
                            .add(HABlocks.BARNACLE_SHORESTONE.get().defaultBlockState(), 1)
                            .build()
                    ),
                    PlacementUtils.inlinePlaced(
                        HAFeatures.ANEMONES.get(),
                        FeatureConfiguration.NONE,
                        *arrayOfNulls<PlacementModifier>(0)
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

            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.BOULDER,

                GEODE, GeodeConfiguration(
                    GeodeBlockSettings(
                        BlockStateProvider.simple(Blocks.STONE),
                        BlockStateProvider.simple(Blocks.STONE),
                        BlockStateProvider.simple(Blocks.STONE),
                        BlockStateProvider.simple(Blocks.STONE),
                        BlockStateProvider.simple(HABlocks.SHORESTONE.get()),
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

            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.DEEP_OCEAN_VEGETATION,

                Feature.SIMPLE_RANDOM_SELECTOR,
                SimpleRandomFeatureConfiguration(
                    HolderSet.direct(
                        PlacementUtils.inlinePlaced(
                            HAFeatures.DEEP_CORAL_TREE.get(),
                            FeatureConfiguration.NONE,
                            *arrayOfNulls<PlacementModifier>(0)
                        ),
                        PlacementUtils.inlinePlaced(
                            HAFeatures.DEEP_CORAL_CLAW.get(),
                            FeatureConfiguration.NONE,
                            *arrayOfNulls<PlacementModifier>(0)
                        ),
                        PlacementUtils.inlinePlaced(
                            HAFeatures.DEEP_CORAL_MUSHROOM.get(),
                            FeatureConfiguration.NONE,
                            *arrayOfNulls<PlacementModifier>(0)
                        ),
                        PlacementUtils.inlinePlaced(
                            HAFeatures.DEEP_CORAL_TABLE.get(),
                            FeatureConfiguration.NONE,
                            *arrayOfNulls<PlacementModifier>(0)
                        )
                    )
                )
            )

            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.BLEACHED_REEF_VEGETATION,

                Feature.SIMPLE_RANDOM_SELECTOR,
                SimpleRandomFeatureConfiguration(
                    HolderSet.direct(
                        PlacementUtils.inlinePlaced(
                            HAFeatures.BLEACHED_CORAL_TREE.get(),
                            FeatureConfiguration.NONE,
                            *arrayOfNulls<PlacementModifier>(0)
                        ),
                        PlacementUtils.inlinePlaced(
                            HAFeatures.BLEACHED_CORAL_CLAW.get(),
                            FeatureConfiguration.NONE,
                            *arrayOfNulls<PlacementModifier>(0)
                        ),
                        PlacementUtils.inlinePlaced(
                            HAFeatures.BLEACHED_CORAL_MUSHROOM.get(),
                            FeatureConfiguration.NONE,
                            *arrayOfNulls<PlacementModifier>(0)
                        ),
                        PlacementUtils.inlinePlaced(
                            HAFeatures.BLEACHED_CORAL_TABLE.get(),
                            FeatureConfiguration.NONE,
                            *arrayOfNulls<PlacementModifier>(0)
                        )
                    )
                )
            )

            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.CORAL_REEF_VEGETATION,

                Feature.SIMPLE_RANDOM_SELECTOR,
                SimpleRandomFeatureConfiguration(
                    HolderSet.direct(
                        PlacementUtils.inlinePlaced(
                            HAFeatures.REEF_CORAL_TREE.get(),
                            FeatureConfiguration.NONE,
                            *arrayOfNulls<PlacementModifier>(0)
                        ),
                        PlacementUtils.inlinePlaced(
                            HAFeatures.REEF_CORAL_CLAW.get(),
                            FeatureConfiguration.NONE,
                            *arrayOfNulls<PlacementModifier>(0)
                        ),
                        PlacementUtils.inlinePlaced(
                            HAFeatures.REEF_CORAL_MUSHROOM.get(),
                            FeatureConfiguration.NONE,
                            *arrayOfNulls<PlacementModifier>(0)
                        ),
                        PlacementUtils.inlinePlaced(
                            HAFeatures.REEF_CORAL_TABLE.get(),
                            FeatureConfiguration.NONE,
                            *arrayOfNulls<PlacementModifier>(0)
                        )
                    )
                )
            )

            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.RED_ALGAE_PATCH,

                HAFeatures.RED_ALGAE_PATCH.get(), ProbabilityFeatureConfiguration(
                    0.66f
                )
            )

            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.AERATED_SAND_CIRCLE,

                Feature.DISK,
                DiskConfiguration(
                    RuleBasedBlockStateProvider.simple(HABlocks.AERATED_SAND.get()),
                    BlockPredicate.matchesBlocks(
                        listOf<Block>(
                            HABlocks.GRASSY_SAND.get(),
                            HABlocks.SHORESTONE.get(),
                            HABlocks.CORALSTONE.get(),
                            Blocks.SAND
                        )
                    ),
                    UniformInt.of(1, 3),
                    1
                )
            )

            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.SAND_CIRCLE,

                Feature.DISK,
                DiskConfiguration(
                    RuleBasedBlockStateProvider.simple(Blocks.SAND),
                    BlockPredicate.matchesBlocks(listOf<Block>(HABlocks.GRASSY_SAND.get())),
                    UniformInt.of(3, 8),
                    1
                )
            )

            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.SULFUR_DEPOSIT,

                Feature.ORE,
                OreConfiguration(
                    TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES),
                    HABlocks.CRYSTALLINE_SULFUR.get().defaultBlockState(),
                    20,
                    0.0f
                )
            )

            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.SUSPICIOUS_SAND_DISK,

                HAFeatures.SUSPICIOUS_SAND_DISK.get(),
                DiskConfiguration(
                    RuleBasedBlockStateProvider.simple(Blocks.SUSPICIOUS_SAND),
                    BlockPredicate.matchesBlocks(listOf<Block>(Blocks.SAND)),
                    UniformInt.of(1, 2),
                    1
                )
            )

            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.DUNEGRASS_PATCH,

                HybridAquatic.DUNEGRASS_PATCH.get(), ProbabilityFeatureConfiguration(
                    0.5f
                )
            )

            //#region Sargassum
            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.SARGASSUM,
                HAFeatures.SARGASSUM.get(), SargassumFeatureConfig(
                    SimpleStateProvider.simple(HABlocks.SARGASSUM.get())
                )
            )

            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.BULL_KELP,

                HAFeatures.BULL_KELP.get(), BullKelpFeatureConfig(
                    SimpleStateProvider.simple(HABlocks.BULL_KELP.get())
                )
            )

            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.DELESSERIA,

                HAFeatures.DELESSERIA.get(), DelesseriaFeatureConfig(
                    SimpleStateProvider.simple(HABlocks.DELESSERIA.get())
                )
            )

            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.FLOATING_SARGASSUM,

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
                                    HABlocks.FLOATING_SARGASSUM.get().defaultBlockState()
                                )
                            )
                        ),
                        BlockPredicate.matchesBlocks(Blocks.WATER)
                    )
                )
            )

            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.WATER_LETTUCE,

                Feature.RANDOM_PATCH, RandomPatchConfiguration(
                    30, 5, 5,
                    PlacementUtils.filtered(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockConfiguration(
                            SimpleStateProvider.simple(HABlocks.WATER_LETTUCE.get())
                        ),
                        BlockPredicate.matchesBlocks(Blocks.WATER)
                    )
                )
            )

            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.WATER_HYACINTH,

                Feature.RANDOM_PATCH, RandomPatchConfiguration(
                    30, 5, 5,
                    PlacementUtils.filtered(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockConfiguration(
                            SimpleStateProvider.simple(HABlocks.WATER_HYACINTH.get())
                        ),
                        BlockPredicate.matchesBlocks(Blocks.WATER)
                    )
                )
            )

            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.JUNGLE_LILY_PAD,

                Feature.RANDOM_PATCH, RandomPatchConfiguration(
                    10, 5, 5,
                    PlacementUtils.filtered(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockConfiguration(
                            SimpleStateProvider.simple(HABlocks.JUNGLE_LILY_PAD.get())
                        ),
                        BlockPredicate.matchesBlocks(Blocks.WATER)
                    )
                )
            )

            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.SEA_LETTUCE_PATCH,

                HAFeatures.SEA_LETTUCE_PATCH.get(), ProbabilityFeatureConfiguration(
                    0.33f
                )
            )

            // mussel patch
            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.TIDE_POOL_MUSSEL_PATCH,

                Feature.FLOWER,
                RandomPatchConfiguration(
                    16, 4, 4,
                    PlacementUtils.filtered(
                        Feature.REPLACE_SINGLE_BLOCK,
                        ReplaceBlockConfiguration(
                            listOf(
                                OreConfiguration.target(
                                    BlockStateMatchTest(Blocks.WATER.defaultBlockState()),
                                    HABlocks.WILD_MUSSELS.get().defaultBlockState()
                                        .setValue(WATERLOGGED, true)
                                ),
                                OreConfiguration.target(
                                    BlockStateMatchTest(Blocks.AIR.defaultBlockState()),
                                    HABlocks.WILD_MUSSELS.get().defaultBlockState()
                                        .setValue(WATERLOGGED, false)
                                )
                            )
                        ),
                        BlockPredicate.hasSturdyFace(Vec3i(0, -1, 0), Direction.UP)
                    )
                )
            )

            // tube sponge patch
            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.TUBE_SPONGE_PATCH,

                Feature.FLOWER,
                RandomPatchConfiguration(
                    4, 2, 2,
                    PlacementUtils.filtered(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockConfiguration(
                            BlockStateProvider.simple(
                                HABlocks.TUBE_SPONGE.get().defaultBlockState().setValue(WATERLOGGED, true)
                            )
                        ),
                        BlockPredicate.matchesBlocks(Blocks.WATER)
                    )
                )
            )

            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.GLASS_SPONGE_PATCH,

                Feature.FLOWER,
                RandomPatchConfiguration(
                    4, 4, 2,
                    PlacementUtils.filtered(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockConfiguration(
                            BlockStateProvider.simple(
                                HABlocks.GLASS_SPONGE.get().defaultBlockState().setValue(WATERLOGGED, true)
                            )
                        ),
                        BlockPredicate.matchesBlocks(Blocks.WATER)
                    )
                )
            )

            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.HARP_SPONGE_PATCH,

                Feature.FLOWER,
                RandomPatchConfiguration(
                    4, 4, 2,
                    PlacementUtils.filtered(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockConfiguration(
                            BlockStateProvider.simple(
                                HABlocks.HARP_SPONGE.get().defaultBlockState().setValue(WATERLOGGED, true)
                            )
                        ),
                        BlockPredicate.matchesBlocks(Blocks.WATER)
                    )
                )
            )

            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.PING_PONG_SPONGE_PATCH,

                Feature.FLOWER,
                RandomPatchConfiguration(
                    4, 4, 2,
                    PlacementUtils.filtered(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockConfiguration(
                            BlockStateProvider.simple(
                                HABlocks.PING_PONG_SPONGE.get().defaultBlockState().setValue(WATERLOGGED, true)
                            )
                        ),
                        BlockPredicate.matchesBlocks(Blocks.WATER)
                    )
                )
            )

            // giant clam patch
            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.GIANT_CLAM_PATCH,

                Feature.NO_BONEMEAL_FLOWER, RandomPatchConfiguration(
                    2, 2, 2,
                    PlacementUtils.filtered(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockConfiguration(
                            WeightedStateProvider(
                                SimpleWeightedRandomList.builder<BlockState>()
                                    .add(
                                        HABlocks.GIANT_CLAM.get().defaultBlockState()
                                            .setValue(WATERLOGGED, true)
                                            .setValue(HorizontalDirectionalBlock.FACING, Direction.EAST), 1
                                    )
                                    .add(
                                        HABlocks.GIANT_CLAM.get().defaultBlockState()
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

            // giant clam patch
            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.OYSTER_BED,

                Feature.NO_BONEMEAL_FLOWER, RandomPatchConfiguration(
                    2, 2, 2,
                    PlacementUtils.filtered(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockConfiguration(
                            WeightedStateProvider(
                                SimpleWeightedRandomList.builder<BlockState>()
                                    .add(
                                        HABlocks.OYSTER.get().defaultBlockState()
                                            .setValue(WATERLOGGED, true)
                                            .setValue(HorizontalDirectionalBlock.FACING, Direction.NORTH), 1
                                    )
                                    .add(
                                        HABlocks.OYSTER.get().defaultBlockState()
                                            .setValue(WATERLOGGED, true)
                                            .setValue(HorizontalDirectionalBlock.FACING, Direction.SOUTH), 1
                                    )
                                    .add(
                                        HABlocks.OYSTER.get().defaultBlockState()
                                            .setValue(WATERLOGGED, true)
                                            .setValue(HorizontalDirectionalBlock.FACING, Direction.EAST), 1
                                    )
                                    .add(
                                        HABlocks.OYSTER.get().defaultBlockState()
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

            // message in a bottle
            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.MESSAGE_IN_A_BOTTLE,

                HAFeatures.MESSAGE_IN_A_BOTTLE.get(), MessageInABottleFeatureConfig(
                    SimpleStateProvider.simple(HABlocks.MESSAGE_IN_A_BOTTLE.get())
                )
            )

            // thermal vents
            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.THERMAL_VENT_PATCH,

                HAFeatures.VENT_PATCH.get(), VentPatchFeatureConfig(
                    5,
                    SimpleStateProvider.simple(HABlocks.CHIMNEYSTONE.get()),
                    SimpleStateProvider.simple(HABlocks.THERMAL_VENT.get()),
                    SimpleStateProvider.simple(HABlocks.GIANT_THERMAL_VENT.get()),
                    SimpleStateProvider.simple(HABlocks.TUBE_WORM.get()),
                    UniformInt.of(3, 5),
                    ConstantInt.of(2),
                    UniformInt.of(1, 3),
                    ConstantInt.of(4),
                    UniformInt.of(TubeWormBlock.WORMS.min, TubeWormBlock.WORMS.max),
                )
            )

            // coral mound base
            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.CORAL_MOUND,
                Feature.RANDOM_PATCH, RandomPatchConfiguration(
                    6, 7, 0, PlacementUtils.inlinePlaced(
                        Feature.DISK, DiskConfiguration(
                            RuleBasedBlockStateProvider(
                                SimpleStateProvider.simple(HABlocks.CORALSTONE.get()), listOf(
                                    RuleBasedBlockStateProvider.Rule(
                                        BlockPredicate.not(
                                            BlockPredicate.matchesBlocks(
                                                Vec3i(0, -1, 0),
                                                listOf(Blocks.SAND, HABlocks.CORALSTONE.get())
                                            )
                                        ),
                                        SimpleStateProvider.simple(Blocks.WATER),
                                    )
                                )
                            ), BlockPredicate.matchesBlocks(
                                Vec3i(0, -1, 0), listOf(Blocks.SAND, HABlocks.CORALSTONE.get())
                            ), UniformInt.of(3, 5), 1
                        ),
                        CountPlacement.of(1), HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR)
                    )
                )
            )

            // coral mound coral layers
            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.CORAL_LAYER,

                Feature.RANDOM_SELECTOR,
                RandomFeatureConfiguration(
                    listOf(
                        Blocks.BRAIN_CORAL_BLOCK,
                        Blocks.HORN_CORAL_BLOCK,
                        Blocks.BUBBLE_CORAL_BLOCK,
                        Blocks.FIRE_CORAL_BLOCK,
                        Blocks.TUBE_CORAL_BLOCK,
                        HABlocks.SUN_CORAL_BLOCK.get(),
                        HABlocks.LEAF_CORAL_BLOCK.get(),
                        HABlocks.ROSE_CORAL_BLOCK.get(),
                        HABlocks.BUTTON_CORAL_BLOCK.get()
                    ).map { block ->
                        WeightedPlacedFeature(
                            PlacementUtils.inlinePlaced(
                                Feature.DISK, DiskConfiguration(
                                    RuleBasedBlockStateProvider(
                                        SimpleStateProvider.simple(HABlocks.CORALSTONE.get()),
                                        listOf(
                                            RuleBasedBlockStateProvider.Rule(
                                                BlockPredicate.allOf(
                                                    BlockPredicate.matchesTag(
                                                        Vec3i(0, 1, 0),
                                                        HABlockTags.CORAL_MOUND_BASE_BLOCKS,
                                                    ),
                                                    BlockPredicate.matchesTag(
                                                        Vec3i(0, -1, 0),
                                                        HABlockTags.CORAL_MOUND_BASE_BLOCKS,
                                                    ),
                                                ),
                                                WeightedStateProvider(
                                                    SimpleWeightedRandomList.builder<BlockState>().add(
                                                        HABlocks.CORALSTONE.get().defaultBlockState(),
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
                                                            HABlockTags.CORAL_MOUND_BASE_BLOCKS,
                                                        ),
                                                        BlockPredicate.matchesTag(
                                                            Vec3i(0, -2, 0),
                                                            HABlockTags.CORAL_MOUND_BASE_BLOCKS,
                                                        ),
                                                    ),
                                                ),
                                                SimpleStateProvider.simple(block),
                                            )
                                        )
                                    ),
                                    BlockPredicate.matchesTag(
                                        Vec3i(0, -1, 0),
                                        HABlockTags.CORAL_MOUND_BLOCKS,
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

            // mound base
            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.MOUND,
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
                                                    Blocks.GRAVEL,
                                                    Blocks.SAND,
                                                    Blocks.DEAD_HORN_CORAL_BLOCK,
                                                    Blocks.DEAD_FIRE_CORAL_BLOCK,
                                                    Blocks.DEAD_BRAIN_CORAL_BLOCK,
                                                    Blocks.DEAD_BUBBLE_CORAL_BLOCK,
                                                    Blocks.DEAD_TUBE_CORAL_BLOCK,
                                                    HABlocks.GRASSY_SAND.get(),
                                                )
                                            )
                                        ),
                                        SimpleStateProvider.simple(Blocks.WATER),
                                    )
                                )
                            ), BlockPredicate.matchesBlocks(
                                Vec3i(0, -1, 0), listOf(
                                    Blocks.GRAVEL,
                                    Blocks.SAND,
                                    Blocks.DEAD_HORN_CORAL_BLOCK,
                                    Blocks.DEAD_FIRE_CORAL_BLOCK,
                                    Blocks.DEAD_BRAIN_CORAL_BLOCK,
                                    Blocks.DEAD_BUBBLE_CORAL_BLOCK,
                                    Blocks.DEAD_TUBE_CORAL_BLOCK,
                                    HABlocks.GRASSY_SAND.get()
                                )
                            ), UniformInt.of(3, 5), 1
                        ),
                        CountPlacement.of(1), HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR)
                    )
                )
            )

            // white mound
            FeatureUtils.register(
                bootstrap,
                HAConfiguredFeatures.WHITE_MOUND,
                Feature.RANDOM_PATCH, RandomPatchConfiguration(
                    6, 7, 0, PlacementUtils.inlinePlaced(
                        Feature.DISK, DiskConfiguration(
                            RuleBasedBlockStateProvider(
                                WeightedStateProvider(
                                    SimpleWeightedRandomList.builder<BlockState>()
                                        .add(Blocks.CALCITE.defaultBlockState(), 5)
                                        .add(Blocks.DIORITE.defaultBlockState(), 3)
                                        .add(HABlocks.WHITE_SANDSTONE.get().defaultBlockState(), 1)
                                ), listOf(
                                    RuleBasedBlockStateProvider.Rule(
                                        BlockPredicate.not(
                                            BlockPredicate.matchesBlocks(
                                                Vec3i(0, -1, 0),
                                                listOf(
                                                    Blocks.CALCITE,
                                                    Blocks.DIORITE,
                                                    HABlocks.WHITE_SAND.get(),
                                                    HABlocks.WHITE_SANDSTONE.get(),
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
                                    HABlocks.WHITE_SAND.get(),
                                    HABlocks.WHITE_SANDSTONE.get()
                                )
                            ), UniformInt.of(3, 5), 1
                        ),
                        CountPlacement.of(1), HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR)
                    )
                )
            )
        }
    }

    override fun configure(registries: HolderLookup.Provider, entries: Entries) {
        val reg = registries.lookup(Registries.CONFIGURED_FEATURE).get()

        entries.add(reg.getOrThrow(HAConfiguredFeatures.ANEMONES))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.DEEP_OCEAN_VEGETATION))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.BLEACHED_REEF_VEGETATION))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.CORAL_REEF_VEGETATION))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.RED_ALGAE_PATCH))

        entries.add(reg.getOrThrow(HAConfiguredFeatures.DUNEGRASS_PATCH))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.SARGASSUM))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.FLOATING_SARGASSUM))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.BULL_KELP))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.DELESSERIA))

        entries.add(reg.getOrThrow(HAConfiguredFeatures.WATER_LETTUCE))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.WATER_HYACINTH))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.JUNGLE_LILY_PAD))

        entries.add(reg.getOrThrow(HAConfiguredFeatures.AERATED_SAND_CIRCLE))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.SAND_CIRCLE))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.SULFUR_DEPOSIT))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.SUSPICIOUS_SAND_DISK))

        entries.add(reg.getOrThrow(HAConfiguredFeatures.SEA_LETTUCE_PATCH))

        entries.add(reg.getOrThrow(HAConfiguredFeatures.GIANT_CLAM_PATCH))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.OYSTER_BED))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.TIDE_POOL_MUSSEL_PATCH))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.TUBE_SPONGE_PATCH))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.GLASS_SPONGE_PATCH))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.HARP_SPONGE_PATCH))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.PING_PONG_SPONGE_PATCH))

        entries.add(reg.getOrThrow(HAConfiguredFeatures.THERMAL_VENT_PATCH))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.MESSAGE_IN_A_BOTTLE))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.TIDE_POOLS))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.BOULDER))

        entries.add(reg.getOrThrow(HAConfiguredFeatures.BRINE_POOL))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.RED_BRINE_POOL))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.ORANGE_BRINE_POOL))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.YELLOW_BRINE_POOL))

        entries.add(reg.getOrThrow(HAConfiguredFeatures.CORAL_MOUND))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.CORAL_LAYER))

        entries.add(reg.getOrThrow(HAConfiguredFeatures.MOUND))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.WHITE_MOUND))
    }

    override fun getName(): String {
        return "Configured Features"
    }
}
