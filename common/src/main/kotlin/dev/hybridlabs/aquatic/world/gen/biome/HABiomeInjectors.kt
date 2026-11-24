package dev.hybridlabs.aquatic.world.gen.biome

import com.mojang.serialization.MapCodec
import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.platform.registration.RegistrationProvider
import dev.worldgen.lithostitched.api.predicate.LoadPredicate
import dev.worldgen.lithostitched.api.registry.LithostitchedBuiltInRegistries
import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector.ClimateParameter
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.ParameterBuilder
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.Level
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.Biomes
import net.minecraft.world.level.levelgen.densityfunction.DensityFunction
import net.minecraft.world.level.levelgen.densityfunction.DensityFunctions
import net.minecraft.world.level.levelgen.synth.NormalNoise

/**
 * Biome placements done through Lithostitched biome injectors instead of Biolith.
 *
 * The injectors are generated as `lithostitched/biome_injector` data, so datapacks can override them. Injectors
 * only test noise values at the sampled position, so Biolith's neighbour and edge criteria are approximated with
 * climate ranges.
 */
object HABiomeInjectors {
    private val LOAD_PREDICATE_TYPES =
        RegistrationProvider.get(LithostitchedBuiltInRegistries.LOAD_PREDICATE_TYPE, Constants.MOD_ID)
    val BIOME_ENABLED = LOAD_PREDICATE_TYPES.register("biome_enabled") { BiomeEnabledPredicate.CODEC }

    val REEF_SELECTOR_NOISE: ResourceKey<NormalNoise> =
        ResourceKey.create(Registries.NOISE, CommonClass.locate("reef_selector"))
    val REEF_SELECTOR: ResourceKey<DensityFunction> =
        ResourceKey.create(Registries.DENSITY_FUNCTION, CommonClass.locate("biome/reef_selector"))

    val LUKEWARM_TIDE_POOLS: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("lukewarm_tide_pools"))
    val WARM_TIDE_POOLS: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("warm_tide_pools"))

    val CORAL_REEF: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("coral_reef"))

    val RED_MEADOW: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("red_meadow"))

    val SEAGRASS_BED: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("seagrass_bed"))

    val WARM_TROPICAL_DEEP_CORAL_REEF: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("warm_tropical_deep_coral_reef"))

    val LUKEWARM_TROPICAL_DEEP_CORAL_REEF: ResourceKey<BiomeInjector> =
        ResourceKey.create(
            LithostitchedRegistries.BIOME_INJECTOR,
            CommonClass.locate("lukewarm_tropical_deep_coral_reef")
        )

    val DEEP_CORAL_REEF: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("deep_coral_reef"))

    val COLD_DEEP_CORAL_REEF: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("cold_deep_coral_reef"))

    val FROZEN_DEEP_CORAL_REEF: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("frozen_deep_coral_reef"))

    val DEEP_WARM_OCEAN: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("deep_warm_ocean"))

    val WARM_TRENCH: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("warm_trench"))
    val LUKEWARM_TRENCH: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("lukewarm_trench"))
    val TRENCH: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("trench"))
    val COLD_TRENCH: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("cold_trench"))
    val FROZEN_TRENCH: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("frozen_trench"))

    val WARM_SULFURIC_CAVES: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("warm_sulfuric_caves"))
    val LUKEWARM_SULFURIC_CAVES: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("lukewarm_sulfuric_caves"))
    val SULFURIC_CAVES: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("sulfuric_caves"))
    val COLD_SULFURIC_CAVES: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("cold_sulfuric_caves"))
    val FROZEN_SULFURIC_CAVES: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("frozen_sulfuric_caves"))

    val TROPICAL_RIVER: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("tropical_river"))
    val COLD_RIVER: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("cold_river"))
    val BLACKWATER_RIVER: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("blackwater_river"))
    val FORESTED_RIVER: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("forested_river"))
    val FORESTED_RIVER_PLATEAU: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("forested_river_weird"))
    val FORESTED_RIVER_BIRCH: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("forested_river_birch"))
    val FORESTED_RIVER_BIRCH_PLATEAU: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("forested_river_birch_plateau"))
    val FLORAL_RIVER: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("floral_river"))
    val EXOTIC_RIVER: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("exotic_river"))
    val EXOTIC_DESERT_RIVER: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("exotic_desert_river"))
    val EXOTIC_DESERT_RIVER_SHATTERED: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("exotic_desert_river_shattered"))
    val EXOTIC_BADLANDS_RIVER: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("exotic_badlands_river"))
    val EXOTIC_BADLANDS_RIVER_SHATTERED: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("exotic_badlands_river_shattered"))

    val LUSH_DESERT: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("lush_desert"))
    val LUSH_DESERT_2: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("lush_desert_2"))
    val LUSH_DESERT_SHATTERED: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("lush_desert_shattered"))
    val LUSH_DESERT_SHATTERED_2: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("lush_desert_shattered_2"))

    val ESTUARY: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("estuary"))
    val TROPICAL_ESTUARY: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("tropical_estuary"))
    val FORESTED_ESTUARY: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("forested_estuary"))
    val FLORAL_ESTUARY: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("floral_estuary"))
    val BLACKWATER_ESTUARY: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("blackwater_estuary"))
    val COLD_ESTUARY: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("cold_estuary"))
    val EXOTIC_ESTUARY: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("exotic_estuary"))
    val EXOTIC_DESERT_ESTUARY: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("exotic_desert_estuary"))
    val EXOTIC_BADLANDS_ESTUARY: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("exotic_badlands_estuary"))

    // Lithostitched samples the base biome once and applies only the first matching
    // replace_partially injector, lowest priority first. Injectors cannot target a
    // biome another injector places, so everything carved out of warm ocean targets
    // it directly, and the ordering keeps specific biomes ahead of broad ones
    private const val TRENCH_PRIORITY = 900
    private const val SULFURIC_CAVES_PRIORITY = 950
    private const val DEEP_WARM_OCEAN_PRIORITY = 1100
    private const val SHALLOW_REEF_PRIORITY = 1200

    // Vanilla's deep ocean continentalness band; warm ocean has no deep variant there
    private const val DEEP_OCEAN_MIN_CONTINENTALNESS = -1.05f
    private const val DEEP_OCEAN_MAX_CONTINENTALNESS = -0.455f

    fun bootstrapNoises(context: BootstrapContext<NormalNoise>) {
        context.register(REEF_SELECTOR_NOISE, NormalNoise.createParity(-9, 1.0, 1.0))
    }

    fun bootstrapDensityFunctions(context: BootstrapContext<DensityFunction>) {
        val noises = context.lookup(Registries.NOISE)
        context.register(REEF_SELECTOR, DensityFunctions.noise(noises.getOrThrow(REEF_SELECTOR_NOISE), 1.0, 0.0))
    }

    fun bootstrap(context: BootstrapContext<BiomeInjector>) {
        val biomes = context.lookup(Registries.BIOME)
        val densityFunctions = context.lookup(Registries.DENSITY_FUNCTION)

        // Beaches bordering lukewarm oceans share their temperature band; the
        // seaward edge of the coast band stands in for Biolith's edge ratio
        context.register(
            LUKEWARM_TIDE_POOLS,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.TIDE_POOLS)).replacePartially(
                biomes.getOrThrow(Biomes.BEACH),
                biomes.getOrThrow(HABiomes.TIDE_POOLS),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.TEMPERATURE, 0.2f, 0.55f)
                    .climateRange(ClimateParameter.CONTINENTALNESS, -0.19f, -0.16f)
            )
        )

        context.register(
            WARM_TIDE_POOLS,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.TIDE_POOLS)).replacePartially(
                biomes.getOrThrow(Biomes.DESERT),
                biomes.getOrThrow(HABiomes.TIDE_POOLS),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.TEMPERATURE, 0.55f, 1.0f)
                    .climateRange(ClimateParameter.CONTINENTALNESS, -0.19f, -0.16f)
            )
        )

        // Reef selector values cluster around 0 (roughly normal, sd ~0.33f), so these
        // bands give coral reef and red meadow ~15% of warm ocean each and seagrass
        // bed ~28%, with plain warm ocean left in the gaps between them
        context.register(
            CORAL_REEF,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.CORAL_REEF))
                .priority(SHALLOW_REEF_PRIORITY)
                .replacePartially(
                    biomes.getOrThrow(Biomes.WARM_OCEAN),
                    biomes.getOrThrow(HABiomes.CORAL_REEF),
                    ParameterBuilder.create()
                        .densityFunctionRange(densityFunctions.getOrThrow(REEF_SELECTOR), -1.0f, -0.32f)
                )
        )

        context.register(
            SEAGRASS_BED,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.SEAGRASS_BED))
                .priority(SHALLOW_REEF_PRIORITY)
                .replacePartially(
                    biomes.getOrThrow(Biomes.WARM_OCEAN),
                    biomes.getOrThrow(HABiomes.SEAGRASS_BED),
                    ParameterBuilder.create()
                        .densityFunctionRange(densityFunctions.getOrThrow(REEF_SELECTOR), -0.12f, 0.12f)
                )
        )

        context.register(
            RED_MEADOW,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.RED_MEADOW))
                .priority(SHALLOW_REEF_PRIORITY)
                .replacePartially(
                    biomes.getOrThrow(Biomes.WARM_OCEAN),
                    biomes.getOrThrow(HABiomes.RED_MEADOW),
                    ParameterBuilder.create()
                        .densityFunctionRange(densityFunctions.getOrThrow(REEF_SELECTOR), 0.32f, 1.0f)
                )
        )

        context.register(
            WARM_TROPICAL_DEEP_CORAL_REEF,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.TROPICAL_DEEP_CORAL_REEF))
                .replacePartially(
                    biomes.getOrThrow(Biomes.WARM_OCEAN),
                    biomes.getOrThrow(HABiomes.TROPICAL_DEEP_CORAL_REEF),
                    ParameterBuilder.create()
                        .climateRange(
                            ClimateParameter.CONTINENTALNESS,
                            DEEP_OCEAN_MIN_CONTINENTALNESS,
                            DEEP_OCEAN_MAX_CONTINENTALNESS
                        )
                        .densityFunctionMin(densityFunctions.getOrThrow(REEF_SELECTOR), 0.2f)
                )
        )

        context.register(
            LUKEWARM_TROPICAL_DEEP_CORAL_REEF,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.TROPICAL_DEEP_CORAL_REEF))
                .replacePartially(
                    biomes.getOrThrow(Biomes.DEEP_LUKEWARM_OCEAN),
                    biomes.getOrThrow(HABiomes.TROPICAL_DEEP_CORAL_REEF),
                    ParameterBuilder.create()
                        .densityFunctionMin(densityFunctions.getOrThrow(REEF_SELECTOR), 0.2f)
                )
        )

        context.register(
            DEEP_CORAL_REEF,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.DEEP_CORAL_REEF)).replacePartially(
                biomes.getOrThrow(Biomes.DEEP_OCEAN),
                biomes.getOrThrow(HABiomes.DEEP_CORAL_REEF),
                ParameterBuilder.create()
                    .densityFunctionMin(densityFunctions.getOrThrow(REEF_SELECTOR), 0.2f)
            )
        )

        context.register(
            COLD_DEEP_CORAL_REEF,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.DEEP_CORAL_REEF)).replacePartially(
                biomes.getOrThrow(Biomes.DEEP_COLD_OCEAN),
                biomes.getOrThrow(HABiomes.DEEP_CORAL_REEF),
                ParameterBuilder.create()
                    .densityFunctionMin(densityFunctions.getOrThrow(REEF_SELECTOR), 0.2f)
            )
        )

        context.register(
            FROZEN_DEEP_CORAL_REEF,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.DEEP_CORAL_REEF)).replacePartially(
                biomes.getOrThrow(Biomes.DEEP_FROZEN_OCEAN),
                biomes.getOrThrow(HABiomes.DEEP_CORAL_REEF),
                ParameterBuilder.create()
                    .densityFunctionMin(densityFunctions.getOrThrow(REEF_SELECTOR), 0.2f)
            )
        )

        // Whatever deep warm ocean the trench, cave and deep reef injectors leave
        context.register(
            DEEP_WARM_OCEAN,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.DEEP_WARM_OCEAN))
                .priority(DEEP_WARM_OCEAN_PRIORITY)
                .replacePartially(
                    biomes.getOrThrow(Biomes.WARM_OCEAN),
                    biomes.getOrThrow(HABiomes.DEEP_WARM_OCEAN),
                    ParameterBuilder.create()
                        .climateRange(
                            ClimateParameter.CONTINENTALNESS,
                            DEEP_OCEAN_MIN_CONTINENTALNESS,
                            DEEP_OCEAN_MAX_CONTINENTALNESS
                        )
                )
        )

        // Add trench biomes to the appropriate deep ocean types
        context.register(
            WARM_TRENCH,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.WARM_TRENCH))
                .priority(TRENCH_PRIORITY)
                .replacePartially(
                    biomes.getOrThrow(Biomes.WARM_OCEAN),
                    biomes.getOrThrow(HABiomes.WARM_TRENCH),
                    ParameterBuilder.create()
                        .climateRange(ClimateParameter.CONTINENTALNESS, -0.71f, -0.63f)
                )
        )

        context.register(
            LUKEWARM_TRENCH,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.LUKEWARM_TRENCH))
                .priority(TRENCH_PRIORITY)
                .replacePartially(
                    biomes.getOrThrow(Biomes.DEEP_LUKEWARM_OCEAN),
                    biomes.getOrThrow(HABiomes.LUKEWARM_TRENCH),
                    ParameterBuilder.create()
                        .climateRange(ClimateParameter.CONTINENTALNESS, -0.71f, -0.63f)
                )
        )

        context.register(
            TRENCH,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.TRENCH))
                .priority(TRENCH_PRIORITY)
                .replacePartially(
                    biomes.getOrThrow(Biomes.DEEP_OCEAN),
                    biomes.getOrThrow(HABiomes.TRENCH),
                    ParameterBuilder.create()
                        .climateRange(ClimateParameter.CONTINENTALNESS, -0.71f, -0.63f)
                )
        )

        context.register(
            COLD_TRENCH,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.COLD_TRENCH))
                .priority(TRENCH_PRIORITY)
                .replacePartially(
                    biomes.getOrThrow(Biomes.DEEP_COLD_OCEAN),
                    biomes.getOrThrow(HABiomes.COLD_TRENCH),
                    ParameterBuilder.create()
                        .climateRange(ClimateParameter.CONTINENTALNESS, -0.71f, -0.63f)
                )
        )

        context.register(
            FROZEN_TRENCH,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.FROZEN_TRENCH))
                .priority(TRENCH_PRIORITY)
                .replacePartially(
                    biomes.getOrThrow(Biomes.DEEP_FROZEN_OCEAN),
                    biomes.getOrThrow(HABiomes.FROZEN_TRENCH),
                    ParameterBuilder.create()
                        .climateRange(ClimateParameter.CONTINENTALNESS, -0.71f, -0.63f)
                )
        )

        // Add sulfuric caves biomes to the appropriate deep ocean types
        context.register(
            WARM_SULFURIC_CAVES,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.SULFURIC_CAVES))
                .priority(SULFURIC_CAVES_PRIORITY)
                .replacePartially(
                    biomes.getOrThrow(Biomes.WARM_OCEAN),
                    biomes.getOrThrow(HABiomes.SULFURIC_CAVES),
                    ParameterBuilder.create()
                        .climateRange(
                            ClimateParameter.CONTINENTALNESS,
                            DEEP_OCEAN_MIN_CONTINENTALNESS,
                            DEEP_OCEAN_MAX_CONTINENTALNESS
                        )
                        .climateRange(ClimateParameter.DEPTH, 0.2f, 0.5f)
                )
        )

        context.register(
            LUKEWARM_SULFURIC_CAVES,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.SULFURIC_CAVES))
                .priority(SULFURIC_CAVES_PRIORITY)
                .replacePartially(
                    biomes.getOrThrow(Biomes.DEEP_LUKEWARM_OCEAN),
                    biomes.getOrThrow(HABiomes.SULFURIC_CAVES),
                    ParameterBuilder.create()
                        .climateRange(ClimateParameter.DEPTH, 0.2f, 0.5f)
                )
        )

        context.register(
            SULFURIC_CAVES,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.SULFURIC_CAVES))
                .priority(SULFURIC_CAVES_PRIORITY)
                .replacePartially(
                    biomes.getOrThrow(Biomes.DEEP_OCEAN),
                    biomes.getOrThrow(HABiomes.SULFURIC_CAVES),
                    ParameterBuilder.create()
                        .climateRange(ClimateParameter.DEPTH, 0.2f, 0.5f)
                )
        )

        context.register(
            COLD_SULFURIC_CAVES,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.SULFURIC_CAVES))
                .priority(SULFURIC_CAVES_PRIORITY)
                .replacePartially(
                    biomes.getOrThrow(Biomes.DEEP_COLD_OCEAN),
                    biomes.getOrThrow(HABiomes.SULFURIC_CAVES),
                    ParameterBuilder.create()
                        .climateRange(ClimateParameter.DEPTH, 0.2f, 0.5f)
                )
        )

        context.register(
            FROZEN_SULFURIC_CAVES,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.SULFURIC_CAVES))
                .priority(SULFURIC_CAVES_PRIORITY)
                .replacePartially(
                    biomes.getOrThrow(Biomes.DEEP_FROZEN_OCEAN),
                    biomes.getOrThrow(HABiomes.SULFURIC_CAVES),
                    ParameterBuilder.create()
                        .climateRange(ClimateParameter.DEPTH, 0.2f, 0.5f)
                )
        )

        context.register(
            TROPICAL_RIVER,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.TROPICAL_RIVER)).replacePartially(
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(HABiomes.TROPICAL_RIVER),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.TEMPERATURE, 0.2f, 0.55f)
                    .climateRange(ClimateParameter.HUMIDITY, 0.1f, 1.0f)
            )
        )

        context.register(
            COLD_RIVER,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.COLD_RIVER)).replacePartially(
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(HABiomes.COLD_RIVER),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.TEMPERATURE, -0.45f, -0.15f)
                    .climateRange(ClimateParameter.HUMIDITY, 0.1f, 1.0f)
            )
        )

        context.register(
            BLACKWATER_RIVER,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.BLACKWATER_RIVER)).replacePartially(
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(HABiomes.BLACKWATER_RIVER),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.TEMPERATURE, -0.15f, 0.2f)
                    .climateRange(ClimateParameter.HUMIDITY, 0.3f, 1.0f)
            )
        )

        context.register(
            FORESTED_RIVER,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.FORESTED_RIVER)).replacePartially(
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(HABiomes.FORESTED_RIVER),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.TEMPERATURE, -0.45f, 0.2f)
                    .climateRange(ClimateParameter.HUMIDITY, -0.1f, 0.1f)
            )
        )

        context.register(
            FORESTED_RIVER_BIRCH,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.FORESTED_RIVER)).replacePartially(
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(HABiomes.FORESTED_RIVER),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.TEMPERATURE, -0.15f, 0.2f)
                    .climateRange(ClimateParameter.HUMIDITY, 0.1f, 0.3f)
            )
        )

        context.register(
            FLORAL_RIVER,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.FLORAL_RIVER)).replacePartially(
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(HABiomes.FLORAL_RIVER),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.TEMPERATURE, -0.15f, 0.2f)
                    .climateMax(ClimateParameter.HUMIDITY, -0.35f)
                    .climateMax(ClimateParameter.WEIRDNESS, -0.01f)
            )
        )

        context.register(
            EXOTIC_RIVER,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.EXOTIC_RIVER)).replacePartially(
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(HABiomes.EXOTIC_RIVER),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.TEMPERATURE, 0.2f, 0.55f)
                    .climateRange(ClimateParameter.HUMIDITY, -1.0f, -0.1f)
            )
        )

        context.register(
            EXOTIC_DESERT_RIVER,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.EXOTIC_DESERT_RIVER)).replacePartially(
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(HABiomes.EXOTIC_DESERT_RIVER),
                ParameterBuilder.create()
                    .climateMax(ClimateParameter.CONTINENTALNESS, (0.03f))
                    .climateMin(ClimateParameter.EROSION, (-0.375f))
                    .climateMin(ClimateParameter.TEMPERATURE, (0.55f))
            )
        )

        context.register(
            EXOTIC_DESERT_RIVER_SHATTERED,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.EXOTIC_DESERT_RIVER)).replacePartially(
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(HABiomes.EXOTIC_DESERT_RIVER),
                ParameterBuilder.create()
                    .climateMin(ClimateParameter.CONTINENTALNESS, (0.03f))
                    .climateMin(ClimateParameter.EROSION, (0.05f))
                    .climateMin(ClimateParameter.TEMPERATURE, (0.55f))
            )
        )

        context.register(
            LUSH_DESERT,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.LUSH_DESERT)).replacePartially(
                biomes.getOrThrow(Biomes.DESERT),
                biomes.getOrThrow(HABiomes.LUSH_DESERT),
                ParameterBuilder.create()
                    .climateMax(ClimateParameter.CONTINENTALNESS, (0.03f))
                    .climateMin(ClimateParameter.EROSION, (-0.375f))
                    .climateMin(ClimateParameter.TEMPERATURE, (0.55f))
                    .climateRange(ClimateParameter.WEIRDNESS, 0.05f, 0.125f)
            )
        )

        context.register(
            LUSH_DESERT_2,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.LUSH_DESERT)).replacePartially(
                biomes.getOrThrow(Biomes.DESERT),
                biomes.getOrThrow(HABiomes.LUSH_DESERT),
                ParameterBuilder.create()
                    .climateMax(ClimateParameter.CONTINENTALNESS, (0.03f))
                    .climateMin(ClimateParameter.EROSION, (-0.375f))
                    .climateMin(ClimateParameter.TEMPERATURE, (0.55f))
                    .climateRange(ClimateParameter.WEIRDNESS, -0.125f, -0.05f)
            )
        )

        context.register(
            LUSH_DESERT_SHATTERED,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.LUSH_DESERT)).replacePartially(
                biomes.getOrThrow(Biomes.DESERT),
                biomes.getOrThrow(HABiomes.LUSH_DESERT),
                ParameterBuilder.create()
                    .climateMin(ClimateParameter.CONTINENTALNESS, (0.03f))
                    .climateMin(ClimateParameter.EROSION, (0.05f))
                    .climateMin(ClimateParameter.TEMPERATURE, (0.55f))
                    .climateRange(ClimateParameter.WEIRDNESS, 0.05f, 0.125f)
            )
        )

        context.register(
            LUSH_DESERT_SHATTERED_2,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.LUSH_DESERT)).replacePartially(
                biomes.getOrThrow(Biomes.DESERT),
                biomes.getOrThrow(HABiomes.LUSH_DESERT),
                ParameterBuilder.create()
                    .climateMin(ClimateParameter.CONTINENTALNESS, (0.03f))
                    .climateMin(ClimateParameter.EROSION, (0.05f))
                    .climateMin(ClimateParameter.TEMPERATURE, (0.55f))
                    .climateRange(ClimateParameter.WEIRDNESS, -0.125f, -0.05f)
            )
        )

        context.register(
            EXOTIC_BADLANDS_RIVER,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.EXOTIC_BADLANDS_RIVER)).replacePartially(
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(HABiomes.EXOTIC_BADLANDS_RIVER),
                ParameterBuilder.create()
                    .climateMin(ClimateParameter.CONTINENTALNESS, (0.03f))
                    .climateMax(ClimateParameter.EROSION, (0.05f))
                    .climateMin(ClimateParameter.TEMPERATURE, (0.55f))
            )
        )

        context.register(
            EXOTIC_BADLANDS_RIVER_SHATTERED,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.EXOTIC_BADLANDS_RIVER)).replacePartially(
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(HABiomes.EXOTIC_BADLANDS_RIVER),
                ParameterBuilder.create()
                    .climateMax(ClimateParameter.CONTINENTALNESS, (0.03f))
                    .climateMax(ClimateParameter.EROSION, (-0.0375f))
                    .climateMin(ClimateParameter.TEMPERATURE, (0.55f))
            )
        )

        context.register(
            ESTUARY,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.ESTUARY)).replacePartially(
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(HABiomes.ESTUARY),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.CONTINENTALNESS, -0.455f, -0.0975f)
            )
        )

        context.register(
            FORESTED_ESTUARY,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.ESTUARY)).replacePartially(
                biomes.getOrThrow(HABiomes.FORESTED_RIVER),
                biomes.getOrThrow(HABiomes.ESTUARY),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.CONTINENTALNESS, -0.455f, -0.0975f)
            )
        )

        context.register(
            FLORAL_ESTUARY,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.ESTUARY)).replacePartially(
                biomes.getOrThrow(HABiomes.FLORAL_RIVER),
                biomes.getOrThrow(HABiomes.ESTUARY),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.CONTINENTALNESS, -0.455f, -0.0975f)
            )
        )

        context.register(
            TROPICAL_ESTUARY,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.TROPICAL_ESTUARY)).replacePartially(
                biomes.getOrThrow(HABiomes.TROPICAL_RIVER),
                biomes.getOrThrow(HABiomes.TROPICAL_ESTUARY),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.CONTINENTALNESS, -0.455f, -0.0975f)
            )
        )

        context.register(
            BLACKWATER_ESTUARY,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.BLACKWATER_ESTUARY)).replacePartially(
                biomes.getOrThrow(HABiomes.BLACKWATER_RIVER),
                biomes.getOrThrow(HABiomes.BLACKWATER_ESTUARY),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.CONTINENTALNESS, -0.455f, -0.0975f)
            )
        )

        context.register(
            COLD_ESTUARY,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.COLD_ESTUARY)).replacePartially(
                biomes.getOrThrow(HABiomes.COLD_RIVER),
                biomes.getOrThrow(HABiomes.COLD_ESTUARY),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.CONTINENTALNESS, -0.455f, -0.0975f)
            )
        )

        context.register(
            EXOTIC_ESTUARY,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.EXOTIC_ESTUARY)).replacePartially(
                biomes.getOrThrow(HABiomes.EXOTIC_RIVER),
                biomes.getOrThrow(HABiomes.EXOTIC_ESTUARY),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.CONTINENTALNESS, -0.455f, -0.0975f)
            )
        )

        context.register(
            EXOTIC_DESERT_ESTUARY,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.EXOTIC_DESERT_ESTUARY)).replacePartially(
                biomes.getOrThrow(HABiomes.EXOTIC_DESERT_RIVER),
                biomes.getOrThrow(HABiomes.EXOTIC_DESERT_ESTUARY),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.CONTINENTALNESS, -0.455f, -0.0975f)
            )
        )

        context.register(
            EXOTIC_BADLANDS_ESTUARY,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.EXOTIC_BADLANDS_ESTUARY)).replacePartially(
                biomes.getOrThrow(HABiomes.EXOTIC_BADLANDS_RIVER),
                biomes.getOrThrow(HABiomes.EXOTIC_BADLANDS_ESTUARY),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.CONTINENTALNESS, -0.455f, -0.0975f)
            )
        )
    }

    /**
     * Loads an injector only when the biome config allows its biome to generate.
     */
    data class BiomeEnabledPredicate(val biome: ResourceKey<Biome>) : LoadPredicate {
        override fun test(): Boolean {
            val biomeConfig = HABiomes.config.config.biomeConfig
            return biomeConfig.enableBiomes && when (biome) {
                HABiomes.TIDE_POOLS -> biomeConfig.generateTidePools
                HABiomes.DEEP_WARM_OCEAN -> biomeConfig.generateDeepWarmOcean
                HABiomes.DEEP_CORAL_REEF -> biomeConfig.generateDeepCoralReef
                HABiomes.CORAL_REEF -> biomeConfig.generateCoralReef
                HABiomes.TROPICAL_DEEP_CORAL_REEF -> biomeConfig.generateTropicalDeepCoralReef
                HABiomes.RED_MEADOW -> biomeConfig.generateRedMeadow
                HABiomes.SEAGRASS_BED -> biomeConfig.generateSeagrassBed
                HABiomes.TROPICAL_RIVER -> biomeConfig.generateTropicalRiver
                HABiomes.SULFURIC_CAVES -> biomeConfig.generateSulfuricCave
                HABiomes.TRENCH -> biomeConfig.generateTrench
                HABiomes.WARM_TRENCH -> biomeConfig.generateWarmTrench
                HABiomes.LUKEWARM_TRENCH -> biomeConfig.generateLukewarmTrench
                HABiomes.COLD_TRENCH -> biomeConfig.generateColdTrench
                HABiomes.FROZEN_TRENCH -> biomeConfig.generateFrozenTrench
                else -> true
            }
        }

        override fun codec(): MapCodec<BiomeEnabledPredicate> = CODEC

        companion object {
            val CODEC: MapCodec<BiomeEnabledPredicate> = ResourceKey.codec(Registries.BIOME)
                .fieldOf("biome")
                .xmap(::BiomeEnabledPredicate, BiomeEnabledPredicate::biome)
        }
    }
}
