package dev.hybridlabs.aquatic.world.gen.feature

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.TubeWormBlock
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.HorizontalFacingBlock
import net.minecraft.registry.Registerable
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.state.property.Properties
import net.minecraft.util.Identifier
import net.minecraft.util.collection.DataPool
import net.minecraft.util.math.Direction
import net.minecraft.util.math.intprovider.ConstantIntProvider
import net.minecraft.util.math.intprovider.UniformIntProvider
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler
import net.minecraft.world.gen.ProbabilityConfig
import net.minecraft.world.gen.blockpredicate.BlockPredicate
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.PlacedFeatures
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig
import net.minecraft.world.gen.stateprovider.BlockStateProvider
import net.minecraft.world.gen.stateprovider.NoiseBlockStateProvider
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider

/**
 * A registry of configured features for Hybrid Aquatic.
 */
object HybridAquaticConfiguredFeatures {
    val ANEMONE_PATCH = register("anemone_patch")
    val SARGASSUM = register("sargassum")
    val FLOATING_SARGASSUM = register("floating_sargassum")
    val GLOWING_PLANKTON = register("glowing_plankton")

    val WATER_LETTUCE = register("water_lettuce")

    val JUNGLE_LILY_PAD = register("jungle_lily_pad")

    val RED_ALGAE_PATCH = register("red_algae_patch")
    val SEA_LETTUCE_PATCH = register("sea_lettuce_patch")

    val GIANT_CLAM_PATCH = register("giant_clam_patch")
    val TUBE_SPONGE_PATCH = register("tube_sponge_patch")
    val THERMAL_VENT_PATCH = register("thermal_vent_patch")
    val MESSAGE_IN_A_BOTTLE = register("message_in_a_bottle")

//    val BRINE_LAKE = register("brine_lake")
//    val DEEP_CORAL_TREE = register("deep_coral_tree")
//    val DEEP_CORAL_CLAW = register("deep_coral_claw")
//    val DEEP_CORAL_MUSHROOM = register("deep_coral_mushroom")
//    val DEEP_OCEAN_VEGETATION = register("deep_ocean_vegetation")

    private fun register(id: String): RegistryKey<ConfiguredFeature<*, *>> {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier(HybridAquatic.MOD_ID, id))
    }

    fun bootstrap(registry: Registerable<ConfiguredFeature<*, *>>) {
        // anemone patch
        registry.register(
            ANEMONE_PATCH,
            ConfiguredFeature(
                Feature.NO_BONEMEAL_FLOWER, RandomPatchFeatureConfig(
                    3, 3, 3,
                    PlacedFeatures.createEntry(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockFeatureConfig(
                            WeightedBlockStateProvider(
                                DataPool.builder<BlockState>()
                                    .add(HybridAquaticBlocks.ANEMONE.defaultState.with(Properties.WATERLOGGED, true), 1)
                                    .add(
                                        HybridAquaticBlocks.STRAWBERRY_ANEMONE.defaultState.with(
                                            Properties.WATERLOGGED,
                                            true
                                        ), 3
                                    )
                                    .build()
                            )
                        ),
                        BlockPredicate.matchingBlocks(Blocks.WATER)
                    )
                )
            )
        )

        registry.register(
            RED_ALGAE_PATCH,
            ConfiguredFeature(
                HybridAquaticFeatures.RED_ALGAE_PATCH, ProbabilityConfig(
                    0.33f
                )
            )
        )

        registry.register(
            SARGASSUM,
            ConfiguredFeature(
                HybridAquaticFeatures.SARGASSUM, SargassumFeatureConfig(
                    SimpleBlockStateProvider.of(HybridAquaticBlocks.SARGASSUM)
                )
            )
        )

        registry.register(
            FLOATING_SARGASSUM,
            ConfiguredFeature(
                Feature.RANDOM_PATCH, RandomPatchFeatureConfig(
                    100, 10, 10,
                    PlacedFeatures.createEntry(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockFeatureConfig(
                            NoiseBlockStateProvider(
                                237L,
                                DoublePerlinNoiseSampler.NoiseParameters(-5, 5.0, *DoubleArray(0)),
                                1.0f,
                                listOf<BlockState>(
                                    HybridAquaticBlocks.FLOATING_SARGASSUM.defaultState
                                )
                            )
                        ),
                        BlockPredicate.matchingBlocks(Blocks.WATER)
                    )
                )
            )
        )

        registry.register(
            WATER_LETTUCE,
            ConfiguredFeature(
                Feature.RANDOM_PATCH, RandomPatchFeatureConfig(
                    5, 3, 3,
                    PlacedFeatures.createEntry(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockFeatureConfig(
                            SimpleBlockStateProvider.of(HybridAquaticBlocks.WATER_LETTUCE)
                        ),
                        BlockPredicate.matchingBlocks(Blocks.WATER)
                    )
                )
            )
        )

        registry.register(
            JUNGLE_LILY_PAD,
            ConfiguredFeature(
                Feature.RANDOM_PATCH, RandomPatchFeatureConfig(
                    5, 3, 3,
                    PlacedFeatures.createEntry(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockFeatureConfig(
                            SimpleBlockStateProvider.of(HybridAquaticBlocks.JUNGLE_LILY_PAD)
                        ),
                        BlockPredicate.matchingBlocks(Blocks.WATER)
                    )
                )
            )
        )

        registry.register(
            GLOWING_PLANKTON,
            ConfiguredFeature(
                Feature.RANDOM_PATCH, RandomPatchFeatureConfig(
                    100, 12, 12,
                    PlacedFeatures.createEntry(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockFeatureConfig(
                            BlockStateProvider.of(HybridAquaticBlocks.GLOWING_PLANKTON.defaultState)
                        ),
                        BlockPredicate.matchingBlocks(Blocks.WATER)
                    )
                )
            )
        )

        registry.register(
            SEA_LETTUCE_PATCH,
            ConfiguredFeature(
                HybridAquaticFeatures.SEA_LETTUCE_PATCH, ProbabilityConfig(
                    0.33f
                )
            )
        )

        // tube sponge patch
        registry.register(
            TUBE_SPONGE_PATCH,
            ConfiguredFeature(
                Feature.FLOWER,
                RandomPatchFeatureConfig(
                    4, 2, 2,
                    PlacedFeatures.createEntry(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockFeatureConfig(
                            BlockStateProvider.of(
                                HybridAquaticBlocks.TUBE_SPONGE.defaultState.with(
                                    Properties.WATERLOGGED,
                                    true
                                )
                            )
                        ),
                        BlockPredicate.matchingBlocks(Blocks.WATER)
                    )
                )
            )
        )

        // giant clam patch
        registry.register(
            GIANT_CLAM_PATCH,
            ConfiguredFeature(
                Feature.NO_BONEMEAL_FLOWER, RandomPatchFeatureConfig(
                    2, 2, 2,
                    PlacedFeatures.createEntry(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockFeatureConfig(
                            WeightedBlockStateProvider(
                                DataPool.builder<BlockState>()
                                    .add(
                                        HybridAquaticBlocks.GIANT_CLAM.defaultState.with(Properties.WATERLOGGED, true)
                                            .with(HorizontalFacingBlock.FACING, Direction.EAST), 1
                                    )
                                    .add(
                                        HybridAquaticBlocks.GIANT_CLAM.defaultState.with(Properties.WATERLOGGED, true)
                                            .with(HorizontalFacingBlock.FACING, Direction.NORTH), 1
                                    )
                                    .build()
                            )
                        ),
                        BlockPredicate.matchingBlocks(Blocks.WATER)
                    )
                )
            )
        )

        // message in a bottle
        registry.register(
            MESSAGE_IN_A_BOTTLE,
            ConfiguredFeature(
                HybridAquaticFeatures.MESSAGE_IN_A_BOTTLE, MessageInABottleFeatureConfig(
                    SimpleBlockStateProvider.of(HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE)
                )
            )
        )

        // thermal vents
        registry.register(
            THERMAL_VENT_PATCH,
            ConfiguredFeature(
                HybridAquaticFeatures.VENT_PATCH, VentPatchFeatureConfig(
                    SimpleBlockStateProvider.of(Blocks.TUFF),
                    SimpleBlockStateProvider.of(HybridAquaticBlocks.THERMAL_VENT),
                    SimpleBlockStateProvider.of(HybridAquaticBlocks.TUBE_WORM),
                    UniformIntProvider.create(2, 3),
                    ConstantIntProvider.create(3),
                    UniformIntProvider.create(1, 3),
                    ConstantIntProvider.create(4),
                    UniformIntProvider.create(TubeWormBlock.WORMS.min, TubeWormBlock.WORMS.max),
                )
            )
        )

//        // brine lake
//        registry.register(
//            HybridAquaticConfiguredFeatures.BRINE_LAKE,
//            ConfiguredFeature(
//                HybridAquaticFeatures.BRINE_LAKE_FEATURE, BrineLakeFeatureConfig(
//                    SimpleBlockStateProvider.of(Blocks.TUFF),
//                    SimpleBlockStateProvider.of(HybridAquaticBlocks.BRINE),
//                )
//            )
//        )

//        // deep coral
//        registry.register(
//            HybridAquaticConfiguredFeatures.DEEP_CORAL_TREE,
//            ConfiguredFeature(
//                HybridAquaticFeatures.DEEP_CORAL_TREE, DefaultFeatureConfig()
//            )
//        )
//
//        registry.register(
//            HybridAquaticConfiguredFeatures.DEEP_CORAL_CLAW,
//            ConfiguredFeature(
//                HybridAquaticFeatures.DEEP_CORAL_CLAW, DefaultFeatureConfig()
//            )
//        )
//
//        registry.register(
//            HybridAquaticConfiguredFeatures.DEEP_CORAL_MUSHROOM,
//            ConfiguredFeature(
//                HybridAquaticFeatures.DEEP_CORAL_MUSHROOM, DefaultFeatureConfig()
//            )
//        )
//
//        registry.register(
//            HybridAquaticConfiguredFeatures.DEEP_OCEAN_VEGETATION,
//            ConfiguredFeature(
//                Feature.SIMPLE_RANDOM_SELECTOR,
//                SimpleRandomFeatureConfig(
//                    RegistryEntryList.of(
//                        entries.ref(HybridAquaticPlacedFeatures.DEEP_CORAL_TREE),
//                        entries.ref(HybridAquaticPlacedFeatures.DEEP_CORAL_CLAW),
//                        entries.ref(HybridAquaticPlacedFeatures.DEEP_CORAL_MUSHROOM)
//                    )
//                )
//            )
//        )
    }
}
