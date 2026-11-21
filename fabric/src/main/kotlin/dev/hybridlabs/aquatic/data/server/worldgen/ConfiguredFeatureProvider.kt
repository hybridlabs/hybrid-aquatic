@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.aquatic.data.server.worldgen

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.FloatingPetalsBlock
import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.block.IncrementableBlock
import dev.hybridlabs.aquatic.block.TubeWormBlock
import dev.hybridlabs.aquatic.fluid.HAPlatformFluids
import dev.hybridlabs.aquatic.tag.HABlockTags
import dev.hybridlabs.aquatic.world.gen.feature.*
import dev.hybridlabs.aquatic.world.gen.feature.algae.*
import dev.hybridlabs.aquatic.world.gen.feature.corals.*
import dev.hybridlabs.aquatic.world.gen.feature.kelp.*
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.core.Direction
import net.minecraft.core.Holder
import net.minecraft.core.HolderLookup
import net.minecraft.core.HolderSet
import net.minecraft.core.Vec3i
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.tags.BlockTags
import net.minecraft.util.random.WeightedList
import net.minecraft.util.valueproviders.BiasedToBottomInt
import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED
import net.minecraft.world.level.levelgen.GeodeBlockSettings
import net.minecraft.world.level.levelgen.GeodeCrackSettings
import net.minecraft.world.level.levelgen.GeodeLayerSettings
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate
import net.minecraft.world.level.levelgen.feature.BlockReplacement
import net.minecraft.world.level.levelgen.feature.DiskFeature
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.GeodeFeature
import net.minecraft.world.level.levelgen.feature.NoOpFeature
import net.minecraft.world.level.levelgen.feature.OreFeature
import net.minecraft.world.level.levelgen.feature.RandomSelectorFeature
import net.minecraft.world.level.levelgen.feature.ReplaceBlockFeature
import net.minecraft.world.level.levelgen.feature.SimpleBlockFeature
import net.minecraft.world.level.levelgen.feature.SimpleRandomSelectorFeature
import net.minecraft.world.level.levelgen.feature.WaterloggedVegetationPatchFeature
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature
import net.minecraft.world.level.levelgen.feature.stateproviders.*
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter
import net.minecraft.world.level.levelgen.placement.CaveSurface
import net.minecraft.world.level.levelgen.placement.CountPlacement
import net.minecraft.world.level.levelgen.placement.HeightmapPlacement
import net.minecraft.world.level.levelgen.placement.PlacementModifier
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockStateMatchTest
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest
import net.minecraft.world.level.levelgen.synth.NormalNoise
import java.util.concurrent.CompletableFuture

class ConfiguredFeatureProvider(
    output: FabricPackOutput,
    registriesFuture: CompletableFuture<HolderLookup.Provider>,
) : FabricDynamicRegistryProvider(output, registriesFuture) {
    companion object {
        fun bootstrapConfiguredFeatures(bootstrap: BootstrapContext<Feature>) {

            val ANEMONES = bootstrap.register(HAConfiguredFeatures.ANEMONES, HARandomPatchFeature(
                    4, 2, 2,
                    PlacementUtils.inlinePlaced(SimpleBlockFeature(
                            WeightedStateProvider(
                                WeightedList.builder<BlockState>()
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
                        ), BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                            BlockPredicate.matchesBlocks(Blocks.WATER),
                            BlockPredicate.hasSturdyFace(Direction.DOWN, Direction.UP)
                        )))
                ))

            bootstrap.register(HAConfiguredFeatures.RED_BRINE_POOL, BrineLakeFeature(
                    BlockStateProvider.of(
                        HABlocks.RED_BRINESTONE.get()
                    ),
                    BlockStateProvider.of(
                        HAPlatformFluids.BRINE_STILL.get()
                            .defaultFluidState()
                            .createLegacyBlock()
                    )
                ))

            bootstrap.register(HAConfiguredFeatures.ORANGE_BRINE_POOL, BrineLakeFeature(
                    BlockStateProvider.of(
                        HABlocks.ORANGE_BRINESTONE.get()
                    ),
                    BlockStateProvider.of(
                        HAPlatformFluids.BRINE_STILL.get()
                            .defaultFluidState()
                            .createLegacyBlock()
                    )
                ))

            bootstrap.register(HAConfiguredFeatures.YELLOW_BRINE_POOL, BrineLakeFeature(
                    BlockStateProvider.of(
                        HABlocks.YELLOW_BRINESTONE.get()
                    ),
                    BlockStateProvider.of(
                        HAPlatformFluids.BRINE_STILL.get()
                            .defaultFluidState()
                            .createLegacyBlock()
                    )
                ))

            bootstrap.register(HAConfiguredFeatures.BRINE_POOL, SimpleRandomSelectorFeature(
                    HolderSet.direct(
                        bootstrap.lookup(Registries.PLACED_FEATURE).get(HAPlacedFeatures.RED_BRINE_POOL).get(),
                        bootstrap.lookup(Registries.PLACED_FEATURE).get(HAPlacedFeatures.YELLOW_BRINE_POOL).get(),
                        bootstrap.lookup(Registries.PLACED_FEATURE).get(HAPlacedFeatures.ORANGE_BRINE_POOL).get()
                    )
                ))

            bootstrap.register(HAConfiguredFeatures.TIDE_POOLS, WaterloggedVegetationPatchFeature(
                    bootstrap.lookup(Registries.BLOCK).getOrThrow(HABlockTags.TIDE_POOL_REPLACEABLE),
                    Holder.direct(
                        WeightedStateProvider(
                            WeightedList.builder<BlockState>()
                                .add(HABlocks.SHORESTONE.get().defaultBlockState(), 3)
                                .add(HABlocks.BARNACLE_SHORESTONE.get().defaultBlockState(), 1)
                                .build()
                        )
                    ),
                    PlacementUtils.inlinePlaced(AnemoneFeature(), *arrayOf<PlacementModifier>()),
                    CaveSurface.FLOOR,
                    BiasedToBottomInt.of(1, 7),
                    0.0f,
                    5,
                    0.2f,
                    BiasedToBottomInt.of(3, 4),
                    0.5f
                ))

            bootstrap.register(HAConfiguredFeatures.BOULDER, GeodeFeature(
                    GeodeBlockSettings(
                        Holder.direct(BlockStateProvider.of(Blocks.STONE)),
                        Holder.direct(BlockStateProvider.of(Blocks.STONE)),
                        Holder.direct(BlockStateProvider.of(Blocks.STONE)),
                        Holder.direct(BlockStateProvider.of(Blocks.STONE)),
                        Holder.direct(BlockStateProvider.of(HABlocks.SHORESTONE.get())),
                        mutableListOf(Blocks.STONE.defaultBlockState()),
                        bootstrap.lookup(Registries.BLOCK).getOrThrow(BlockTags.FEATURES_CANNOT_REPLACE),
                        bootstrap.lookup(Registries.BLOCK).getOrThrow(BlockTags.GEODE_INVALID_BLOCKS)
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
                ))

            bootstrap.register(HAConfiguredFeatures.DEEP_OCEAN_VEGETATION, SimpleRandomSelectorFeature(
                    HolderSet.direct(
                        PlacementUtils.inlinePlaced(DeepCoralTreeFeature(), *arrayOf<PlacementModifier>()),
                        PlacementUtils.inlinePlaced(DeepCoralClawFeature(), *arrayOf<PlacementModifier>()),
                        PlacementUtils.inlinePlaced(DeepCoralMushroomFeature(), *arrayOf<PlacementModifier>()),
                        PlacementUtils.inlinePlaced(DeepCoralTableFeature(), *arrayOf<PlacementModifier>())
                    )
                ))

            bootstrap.register(HAConfiguredFeatures.BLEACHED_REEF_VEGETATION, SimpleRandomSelectorFeature(
                    HolderSet.direct(
                        PlacementUtils.inlinePlaced(BleachedCoralTreeFeature(), *arrayOf<PlacementModifier>()),
                        PlacementUtils.inlinePlaced(BleachedCoralClawFeature(), *arrayOf<PlacementModifier>()),
                        PlacementUtils.inlinePlaced(BleachedCoralMushroomFeature(), *arrayOf<PlacementModifier>()),
                        PlacementUtils.inlinePlaced(BleachedCoralTableFeature(), *arrayOf<PlacementModifier>())
                    )
                ))

            bootstrap.register(HAConfiguredFeatures.CORAL_REEF_VEGETATION, SimpleRandomSelectorFeature(
                    HolderSet.direct(
                        PlacementUtils.inlinePlaced(ReefCoralTreeFeature(), *arrayOf<PlacementModifier>()),
                        PlacementUtils.inlinePlaced(ReefCoralClawFeature(), *arrayOf<PlacementModifier>()),
                        PlacementUtils.inlinePlaced(ReefCoralMushroomFeature(), *arrayOf<PlacementModifier>()),
                        PlacementUtils.inlinePlaced(ReefCoralTableFeature(), *arrayOf<PlacementModifier>())
                    )
                ))

            bootstrap.register(HAConfiguredFeatures.RED_ALGAE_PATCH, RedAlgaePatchFeature(
                    0.66f
                ))

            bootstrap.register(HAConfiguredFeatures.AERATED_SAND_CIRCLE, DiskFeature(Holder.direct(BlockStateProvider.of(HABlocks.AERATED_SAND.get())), BlockPredicate.matchesBlocks(Vec3i.ZERO, listOf<Block>(
                            HABlocks.GRASSY_SAND.get(),
                            HABlocks.SHORESTONE.get(),
                            HABlocks.CORALSTONE.get(),
                            Blocks.SAND
                        )), UniformInt.of(1, 3), 1))

            bootstrap.register(HAConfiguredFeatures.SAND_CIRCLE, DiskFeature(Holder.direct(BlockStateProvider.of(Blocks.SAND)), BlockPredicate.matchesBlocks(Vec3i.ZERO, listOf<Block>(HABlocks.GRASSY_SAND.get())), UniformInt.of(3, 8), 1))

            bootstrap.register(HAConfiguredFeatures.SULFUR_DEPOSIT, OreFeature(listOf(BlockReplacement(TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), HABlocks.CRYSTALLINE_SULFUR.get().defaultBlockState())), 20, 0.0f))

            bootstrap.register(HAConfiguredFeatures.SUSPICIOUS_SAND_DISK, SuspiciousSandDiskFeature(
                    BlockStateProvider.of(Blocks.SUSPICIOUS_SAND),
                    BlockPredicate.matchesBlocks(Vec3i.ZERO, listOf<Block>(Blocks.SAND)),
                    UniformInt.of(1, 2),
                    1
                ))

            bootstrap.register(HAConfiguredFeatures.DUNEGRASS_PATCH, DunegrassFeature(
                    0.5f
                ))

            bootstrap.register(HAConfiguredFeatures.PAPYRUS_PATCH, PapyrusFeature(
                    0.5f
                ))

            bootstrap.register(HAConfiguredFeatures.CATTAIL_PATCH, CattailFeature(
                    0.5f
                ))

            //#region Sargassum
            bootstrap.register(HAConfiguredFeatures.SARGASSUM, SargassumFeature(
                    BlockStateProvider.of(HABlocks.SARGASSUM.get())
                ))

            bootstrap.register(HAConfiguredFeatures.BULL_KELP, BullKelpFeature(
                    BlockStateProvider.of(HABlocks.BULL_KELP.get())
                ))

            bootstrap.register(HAConfiguredFeatures.DELESSERIA, DelesseriaFeature(
                    BlockStateProvider.of(HABlocks.DELESSERIA.get())
                ))

            bootstrap.register(HAConfiguredFeatures.FLOATING_SARGASSUM, HARandomPatchFeature(
                    100, 10, 10,
                    PlacementUtils.inlinePlaced(SimpleBlockFeature(
                            NoiseProvider(
                                237L,
                                NormalNoise.createParity(-5, 5.0),
                                1.0f,
                                listOf<BlockState>(
                                    HABlocks.FLOATING_SARGASSUM.get().defaultBlockState()
                                )
                            )
                        ), BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.WATER)))
                ))

            val floatingLeavesStates = buildList {
                for (amount in 1..4) {
                    for (direction in Direction.Plane.HORIZONTAL) {
                        add(
                            HABlocks.FLOATING_LEAVES.get().defaultBlockState()
                                .setValue(IncrementableBlock.AMOUNT, amount)
                                .setValue(IncrementableBlock.FACING, direction)
                                .setValue(WATERLOGGED, true)
                        )
                    }
                }
            }

            bootstrap.register(HAConfiguredFeatures.FLOATING_LEAVES, HARandomPatchFeature(
                    50,
                    5,
                    5,
                    PlacementUtils.inlinePlaced(SimpleBlockFeature(
                            WeightedStateProvider(
                                WeightedList.builder<BlockState>().apply {
                                    floatingLeavesStates.forEach { state ->
                                        add(state, 1)
                                    }
                                }.build()
                            )
                        ), BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.WATER)))
                ))

            val floatingLeafLitterStates = buildList {
                for (amount in 1..4) {
                    for (direction in Direction.Plane.HORIZONTAL) {
                        add(
                            HABlocks.FLOATING_LEAF_LITTER.get().defaultBlockState()
                                .setValue(IncrementableBlock.AMOUNT, amount)
                                .setValue(IncrementableBlock.FACING, direction)
                                .setValue(WATERLOGGED, true)
                        )
                    }
                }
            }

            bootstrap.register(HAConfiguredFeatures.FLOATING_LEAF_LITTER, HARandomPatchFeature(
                    50,
                    5,
                    5,
                    PlacementUtils.inlinePlaced(SimpleBlockFeature(
                            WeightedStateProvider(
                                WeightedList.builder<BlockState>().apply {
                                    floatingLeafLitterStates.forEach { state ->
                                        add(state, 1)
                                    }
                                }.build()
                            )
                        ), BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.WATER)))
                ))

            val duckweedStates = buildList {
                for (amount in 1..4) {
                    for (direction in Direction.Plane.HORIZONTAL) {
                        add(
                            HABlocks.DUCKWEED.get().defaultBlockState()
                                .setValue(IncrementableBlock.AMOUNT, amount)
                                .setValue(IncrementableBlock.FACING, direction)
                                .setValue(WATERLOGGED, true)
                        )
                    }
                }
            }

            bootstrap.register(HAConfiguredFeatures.DUCKWEED_PATCH, HARandomPatchFeature(
                    50,
                    5,
                    5,
                    PlacementUtils.inlinePlaced(SimpleBlockFeature(
                            WeightedStateProvider(
                                WeightedList.builder<BlockState>().apply {
                                    duckweedStates.forEach { state ->
                                        add(state, 1)
                                    }
                                }.build()
                            )
                        ), BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.WATER)))
                ))

            val floatingPetalsStates = buildList {
                for (amount in 1..4) {
                    for (direction in Direction.Plane.HORIZONTAL) {
                        add(
                            HABlocks.FLOATING_PETALS.get().defaultBlockState()
                                .setValue(IncrementableBlock.AMOUNT, amount)
                                .setValue(IncrementableBlock.FACING, direction)
                                .setValue(WATERLOGGED, true)
                        )
                    }
                }
            }

            bootstrap.register(HAConfiguredFeatures.FLOATING_PETALS, HARandomPatchFeature(
                    50,
                    5,
                    5,
                    PlacementUtils.inlinePlaced(SimpleBlockFeature(
                            WeightedStateProvider(
                                WeightedList.builder<BlockState>().apply {
                                    floatingPetalsStates.forEach { state ->
                                        add(state, 1)
                                    }
                                }.build()
                            )
                        ), BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.WATER)))
                ))

            bootstrap.register(HAConfiguredFeatures.WATER_LETTUCE, HARandomPatchFeature(
                    30, 5, 5,
                    PlacementUtils.inlinePlaced(SimpleBlockFeature(
                            BlockStateProvider.of(HABlocks.WATER_LETTUCE.get())
                        ), BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.WATER)))
                ))

            bootstrap.register(HAConfiguredFeatures.WATER_HYACINTH, HARandomPatchFeature(
                    30, 5, 5,
                    PlacementUtils.inlinePlaced(SimpleBlockFeature(
                            BlockStateProvider.of(HABlocks.WATER_HYACINTH.get())
                        ), BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.WATER)))
                ))

            bootstrap.register(HAConfiguredFeatures.JUNGLE_LILY_PAD, HARandomPatchFeature(
                    10, 5, 5,
                    PlacementUtils.inlinePlaced(SimpleBlockFeature(
                            BlockStateProvider.of(HABlocks.JUNGLE_LILY_PAD.get())
                        ), BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.WATER)))
                ))

            bootstrap.register(HAConfiguredFeatures.SEA_LETTUCE_PATCH, SeaLettuceFeature(
                    0.33f
                ))

            // mussel patch
            bootstrap.register(HAConfiguredFeatures.TIDE_POOL_MUSSEL_PATCH, HARandomPatchFeature(
                    16, 4, 4,
                    PlacementUtils.inlinePlaced(ReplaceBlockFeature(
                            listOf(
                                BlockReplacement(
                                    BlockStateMatchTest(Blocks.WATER.defaultBlockState()),
                                    HABlocks.WILD_MUSSELS.get().defaultBlockState()
                                        .setValue(WATERLOGGED, true)
                                ),
                                BlockReplacement(
                                    BlockStateMatchTest(Blocks.AIR.defaultBlockState()),
                                    HABlocks.WILD_MUSSELS.get().defaultBlockState()
                                        .setValue(WATERLOGGED, false)
                                )
                            )
                        ), BlockPredicateFilter.forPredicate(BlockPredicate.hasSturdyFace(Direction.DOWN, Direction.UP)))
                ))

            // tube sponge patch
            bootstrap.register(HAConfiguredFeatures.TUBE_SPONGE_PATCH, HARandomPatchFeature(
                    4, 2, 2,
                    PlacementUtils.inlinePlaced(SimpleBlockFeature(
                            BlockStateProvider.of(
                                HABlocks.TUBE_SPONGE.get().defaultBlockState().setValue(WATERLOGGED, true)
                            )
                        ), BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.WATER)))
                ))

            bootstrap.register(HAConfiguredFeatures.GLASS_SPONGE_PATCH, HARandomPatchFeature(
                    4, 4, 2,
                    PlacementUtils.inlinePlaced(SimpleBlockFeature(
                            BlockStateProvider.of(
                                HABlocks.GLASS_SPONGE.get().defaultBlockState().setValue(WATERLOGGED, true)
                            )
                        ), BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.WATER)))
                ))

            bootstrap.register(HAConfiguredFeatures.HARP_SPONGE_PATCH, HARandomPatchFeature(
                    4, 4, 2,
                    PlacementUtils.inlinePlaced(SimpleBlockFeature(
                            BlockStateProvider.of(
                                HABlocks.HARP_SPONGE.get().defaultBlockState().setValue(WATERLOGGED, true)
                            )
                        ), BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.WATER)))
                ))

            bootstrap.register(HAConfiguredFeatures.PING_PONG_SPONGE_PATCH, HARandomPatchFeature(
                    4, 4, 2,
                    PlacementUtils.inlinePlaced(SimpleBlockFeature(
                            BlockStateProvider.of(
                                HABlocks.PING_PONG_SPONGE.get().defaultBlockState().setValue(WATERLOGGED, true)
                            )
                        ), BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.WATER)))
                ))

            // giant clam patch
            bootstrap.register(HAConfiguredFeatures.GIANT_CLAM_PATCH, HARandomPatchFeature(
                    2, 2, 2,
                    PlacementUtils.inlinePlaced(SimpleBlockFeature(
                            WeightedStateProvider(
                                WeightedList.builder<BlockState>()
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
                        ), BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.WATER)))
                ))

            // giant clam patch
            bootstrap.register(HAConfiguredFeatures.OYSTER_BED, HARandomPatchFeature(
                    2, 2, 2,
                    PlacementUtils.inlinePlaced(SimpleBlockFeature(
                            WeightedStateProvider(
                                WeightedList.builder<BlockState>()
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
                        ), BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.WATER)))
                ))

            // message in a bottle
            bootstrap.register(HAConfiguredFeatures.MESSAGE_IN_A_BOTTLE, MessageInABottleFeature(
                    BlockStateProvider.of(HABlocks.MESSAGE_IN_A_BOTTLE.get())
                ))

            // thermal vents
            bootstrap.register(HAConfiguredFeatures.THERMAL_VENT_PATCH, VentPatchFeature(
                    5,
                    BlockStateProvider.of(HABlocks.CHIMNEYSTONE.get()),
                    BlockStateProvider.of(HABlocks.THERMAL_VENT.get()),
                    BlockStateProvider.of(HABlocks.GIANT_THERMAL_VENT.get()),
                    BlockStateProvider.of(HABlocks.TUBE_WORM.get()),
                    UniformInt.of(3, 5),
                    ConstantInt.of(2),
                    UniformInt.of(1, 3),
                    ConstantInt.of(4),
                    UniformInt.of(TubeWormBlock.WORMS.min, TubeWormBlock.WORMS.max),
                ))

            // coral mound base
            bootstrap.register(HAConfiguredFeatures.CORAL_MOUND, HARandomPatchFeature(
                    6, 7, 0, PlacementUtils.inlinePlaced(DiskFeature(Holder.direct(RuleBasedStateProvider(Holder.direct(BlockStateProvider.of(HABlocks.CORALSTONE.get())), listOf(
                                    RuleBasedStateProvider.Rule(BlockPredicate.not(
                                            BlockPredicate.matchesBlocks(
                                                Vec3i(0, -1, 0),
                                                listOf(Blocks.SAND, HABlocks.CORALSTONE.get())
                                            )
                                        ), Holder.direct(BlockStateProvider.of(Blocks.WATER)), )
                                ))), BlockPredicate.matchesBlocks(
                                Vec3i(0, -1, 0), listOf(Blocks.SAND, HABlocks.CORALSTONE.get())
                            ), UniformInt.of(3, 5), 1), CountPlacement.of(1), HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR))
                ))

            // coral mound coral layers
            bootstrap.register(HAConfiguredFeatures.CORAL_LAYER, RandomSelectorFeature(
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
                            PlacementUtils.inlinePlaced(DiskFeature(Holder.direct(RuleBasedStateProvider(Holder.direct(BlockStateProvider.of(HABlocks.CORALSTONE.get())), listOf(
                                            RuleBasedStateProvider.Rule(BlockPredicate.allOf(
                                                    BlockPredicate.matchesTag(
                                                        Vec3i(0, 1, 0),
                                                        HABlockTags.CORAL_MOUND_BASE_BLOCKS,
                                                    ),
                                                    BlockPredicate.matchesTag(
                                                        Vec3i(0, -1, 0),
                                                        HABlockTags.CORAL_MOUND_BASE_BLOCKS,
                                                    ),
                                                ), Holder.direct(WeightedStateProvider(
                                                    WeightedList.builder<BlockState>().add(
                                                        HABlocks.CORALSTONE.get().defaultBlockState(),
                                                        2
                                                    ).add(
                                                        block.defaultBlockState(),
                                                        1

                                                    )
                                                ))),
                                            RuleBasedStateProvider.Rule(BlockPredicate.allOf(
                                                    BlockPredicate.anyOf(
                                                        BlockPredicate.matchesBlocks(
                                                            Vec3i(0, 1, 0),
                                                            listOf(Blocks.WATER)
                                                        ),
                                                        BlockPredicate.matchesBlocks(
                                                            Vec3i(0, 2, 0),
                                                            listOf(Blocks.WATER)
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
                                                ), Holder.direct(BlockStateProvider.of(block)), )
                                        ))), BlockPredicate.matchesTag(
                                        Vec3i(0, -1, 0),
                                        HABlockTags.CORAL_MOUND_BLOCKS,
                                    ), ConstantInt.of(8), 4)),
                            0.2f
                        )
                    },
                    PlacementUtils.inlinePlaced(NoOpFeature())
                ))

            // mound base
            bootstrap.register(HAConfiguredFeatures.MOUND, HARandomPatchFeature(
                    6, 7, 0, PlacementUtils.inlinePlaced(DiskFeature(Holder.direct(RuleBasedStateProvider(Holder.direct(WeightedStateProvider(
                                    WeightedList.builder<BlockState>()
                                        .add(Blocks.DEAD_HORN_CORAL_BLOCK.defaultBlockState(), 1)
                                        .add(Blocks.DEAD_FIRE_CORAL_BLOCK.defaultBlockState(), 1)
                                        .add(Blocks.DEAD_BUBBLE_CORAL_BLOCK.defaultBlockState(), 1)
                                        .add(Blocks.DEAD_BRAIN_CORAL_BLOCK.defaultBlockState(), 1)
                                        .add(Blocks.DEAD_TUBE_CORAL_BLOCK.defaultBlockState(), 1)
                                        .add(Blocks.STONE.defaultBlockState(), 2)
                                )), listOf(
                                    RuleBasedStateProvider.Rule(BlockPredicate.not(
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
                                        ), Holder.direct(BlockStateProvider.of(Blocks.WATER)), )
                                ))), BlockPredicate.matchesBlocks(
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
                            ), UniformInt.of(3, 5), 1), CountPlacement.of(1), HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR))
                ))

            // white mound
            bootstrap.register(HAConfiguredFeatures.WHITE_MOUND, HARandomPatchFeature(
                    6, 7, 0, PlacementUtils.inlinePlaced(DiskFeature(Holder.direct(RuleBasedStateProvider(Holder.direct(WeightedStateProvider(
                                    WeightedList.builder<BlockState>()
                                        .add(Blocks.CALCITE.defaultBlockState(), 5)
                                        .add(Blocks.DIORITE.defaultBlockState(), 3)
                                        .add(HABlocks.WHITE_SANDSTONE.get().defaultBlockState(), 1)
                                )), listOf(
                                    RuleBasedStateProvider.Rule(BlockPredicate.not(
                                            BlockPredicate.matchesBlocks(
                                                Vec3i(0, -1, 0),
                                                listOf(
                                                    Blocks.CALCITE,
                                                    Blocks.DIORITE,
                                                    HABlocks.WHITE_SAND.get(),
                                                    HABlocks.WHITE_SANDSTONE.get(),
                                                )
                                            )
                                        ), Holder.direct(BlockStateProvider.of(Blocks.WATER)), )
                                ))), BlockPredicate.matchesBlocks(
                                Vec3i(0, -1, 0), listOf(
                                    Blocks.CALCITE,
                                    Blocks.DIORITE,
                                    HABlocks.WHITE_SAND.get(),
                                    HABlocks.WHITE_SANDSTONE.get()
                                )
                            ), UniformInt.of(3, 5), 1), CountPlacement.of(1), HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR))
                ))
        }
    }

    override fun configure(registries: HolderLookup.Provider, entries: Entries) {
        val reg = registries.lookup(Registries.FEATURE).get()

        val materialRules = registries.lookupOrThrow(Registries.MATERIAL_RULE)
        for (key in dev.hybridlabs.aquatic.world.gen.biome.HABiomes.surfaceRuleKeys()) {
            entries.add(materialRules.getOrThrow(key))
        }

        entries.add(reg.getOrThrow(HAConfiguredFeatures.ANEMONES))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.DEEP_OCEAN_VEGETATION))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.BLEACHED_REEF_VEGETATION))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.CORAL_REEF_VEGETATION))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.RED_ALGAE_PATCH))

        entries.add(reg.getOrThrow(HAConfiguredFeatures.DUNEGRASS_PATCH))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.CATTAIL_PATCH))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.PAPYRUS_PATCH))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.SARGASSUM))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.FLOATING_SARGASSUM))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.FLOATING_PETALS))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.FLOATING_LEAVES))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.FLOATING_LEAF_LITTER))
        entries.add(reg.getOrThrow(HAConfiguredFeatures.DUCKWEED_PATCH))
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
