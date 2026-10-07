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
import net.minecraft.world.level.levelgen.DensityFunction
import net.minecraft.world.level.levelgen.DensityFunctions
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

    val REEF_SELECTOR_NOISE: ResourceKey<NormalNoise.NoiseParameters> =
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

    val WARM_OCEAN_RIVER_REPLACER: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("warm_ocean_river_replacer"))
    val DEEP_WARM_OCEAN_RIVER_REPLACER: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("deep_warm_ocean_river_replacer"))
    val LUKEWARM_OCEAN_RIVER_REPLACER: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("lukewarm_ocean_river_replacer"))
    val DEEP_LUKEWARM_OCEAN_RIVER_REPLACER: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("deep_lukewarm_ocean_river_replacer"))
    val OCEAN_RIVER_REPLACER: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("ocean_river_replacer"))
    val DEEP_OCEAN_RIVER_REPLACER: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("deep_ocean_river_replacer"))
    val COLD_OCEAN_RIVER_REPLACER: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("cold_ocean_river_replacer"))
    val DEEP_COLD_OCEAN_RIVER_REPLACER: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("deep_cold_ocean_river_replacer"))
    val FROZEN_OCEAN_RIVER_REPLACER: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("frozen_ocean_river_replacer"))
    val DEEP_FROZEN_OCEAN_RIVER_REPLACER: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("deep_frozen_ocean_river_replacer"))

    // Lithostitched samples the base biome once and applies only the first matching
    // replace_partially injector, lowest priority first. Injectors cannot target a
    // biome another injector places, so everything carved out of warm ocean targets
    // it directly, and the ordering keeps specific biomes ahead of broad ones
    private const val TRENCH_PRIORITY = 900
    private const val SULFURIC_CAVES_PRIORITY = 950
    private const val DEEP_WARM_OCEAN_PRIORITY = 1100
    private const val SHALLOW_REEF_PRIORITY = 1200

    // Vanilla's deep ocean continentalness band; warm ocean has no deep variant there
    private const val DEEP_OCEAN_MIN_CONTINENTALNESS = -1.05
    private const val DEEP_OCEAN_MAX_CONTINENTALNESS = -0.455

    fun bootstrapNoises(context: BootstrapContext<NormalNoise.NoiseParameters>) {
        context.register(REEF_SELECTOR_NOISE, NormalNoise.NoiseParameters(-9, 1.0, 1.0))
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
                    .climateRange(ClimateParameter.TEMPERATURE, 0.2, 0.55)
                    .climateRange(ClimateParameter.CONTINENTALNESS, -0.19, -0.16)
            )
        )

        context.register(
            WARM_TIDE_POOLS,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.TIDE_POOLS)).replacePartially(
                biomes.getOrThrow(Biomes.DESERT),
                biomes.getOrThrow(HABiomes.TIDE_POOLS),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.TEMPERATURE, 0.55, 1.0)
                    .climateRange(ClimateParameter.CONTINENTALNESS, -0.19, -0.16)
            )
        )

        // Reef selector values cluster around 0 (roughly normal, sd ~0.33), so these
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
                        .densityFunctionRange(densityFunctions.getOrThrow(REEF_SELECTOR), -1.0, -0.32)
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
                        .densityFunctionRange(densityFunctions.getOrThrow(REEF_SELECTOR), -0.12, 0.12)
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
                        .densityFunctionRange(densityFunctions.getOrThrow(REEF_SELECTOR), 0.32, 1.0)
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
                        .densityFunctionMin(densityFunctions.getOrThrow(REEF_SELECTOR), 0.2)
                )
        )

        context.register(
            LUKEWARM_TROPICAL_DEEP_CORAL_REEF,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.TROPICAL_DEEP_CORAL_REEF))
                .replacePartially(
                    biomes.getOrThrow(Biomes.DEEP_LUKEWARM_OCEAN),
                    biomes.getOrThrow(HABiomes.TROPICAL_DEEP_CORAL_REEF),
                    ParameterBuilder.create()
                        .densityFunctionMin(densityFunctions.getOrThrow(REEF_SELECTOR), 0.2)
                )
        )

        context.register(
            DEEP_CORAL_REEF,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.DEEP_CORAL_REEF)).replacePartially(
                biomes.getOrThrow(Biomes.DEEP_OCEAN),
                biomes.getOrThrow(HABiomes.DEEP_CORAL_REEF),
                ParameterBuilder.create()
                    .densityFunctionMin(densityFunctions.getOrThrow(REEF_SELECTOR), 0.2)
            )
        )

        context.register(
            COLD_DEEP_CORAL_REEF,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.DEEP_CORAL_REEF)).replacePartially(
                biomes.getOrThrow(Biomes.DEEP_COLD_OCEAN),
                biomes.getOrThrow(HABiomes.DEEP_CORAL_REEF),
                ParameterBuilder.create()
                    .densityFunctionMin(densityFunctions.getOrThrow(REEF_SELECTOR), 0.2)
            )
        )

        context.register(
            FROZEN_DEEP_CORAL_REEF,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.DEEP_CORAL_REEF)).replacePartially(
                biomes.getOrThrow(Biomes.DEEP_FROZEN_OCEAN),
                biomes.getOrThrow(HABiomes.DEEP_CORAL_REEF),
                ParameterBuilder.create()
                    .densityFunctionMin(densityFunctions.getOrThrow(REEF_SELECTOR), 0.2)
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
                        .climateRange(ClimateParameter.CONTINENTALNESS, -0.71, -0.63)
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
                        .climateRange(ClimateParameter.CONTINENTALNESS, -0.71, -0.63)
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
                        .climateRange(ClimateParameter.CONTINENTALNESS, -0.71, -0.63)
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
                        .climateRange(ClimateParameter.CONTINENTALNESS, -0.71, -0.63)
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
                        .climateRange(ClimateParameter.CONTINENTALNESS, -0.71, -0.63)
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
                        .climateRange(ClimateParameter.DEPTH, 0.2, 0.5)
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
                        .climateRange(ClimateParameter.DEPTH, 0.2, 0.5)
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
                        .climateRange(ClimateParameter.DEPTH, 0.2, 0.5)
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
                        .climateRange(ClimateParameter.DEPTH, 0.2, 0.5)
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
                        .climateRange(ClimateParameter.DEPTH, 0.2, 0.5)
                )
        )

        context.register(
            TROPICAL_RIVER,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.TROPICAL_RIVER)).replacePartially(
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(HABiomes.TROPICAL_RIVER),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.TEMPERATURE, 0.2, 0.55)
                    .climateRange(ClimateParameter.HUMIDITY, 0.1, 1.0)
            )
        )

        context.register(
            COLD_RIVER,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.COLD_RIVER)).replacePartially(
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(HABiomes.COLD_RIVER),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.TEMPERATURE, -0.45, -0.15)
                    .climateRange(ClimateParameter.HUMIDITY, 0.1, 1.0)
            )
        )

        context.register(
            BLACKWATER_RIVER,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.BLACKWATER_RIVER)).replacePartially(
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(HABiomes.BLACKWATER_RIVER),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.TEMPERATURE, -0.15, 0.2)
                    .climateRange(ClimateParameter.HUMIDITY, 0.3, 1.0)
            )
        )

        context.register(
            FORESTED_RIVER,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.FORESTED_RIVER)).replacePartially(
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(HABiomes.FORESTED_RIVER),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.TEMPERATURE, -0.45, 0.2)
                    .climateRange(ClimateParameter.HUMIDITY, -0.1, 0.1)
            )
        )

        context.register(
            FORESTED_RIVER_BIRCH,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.FORESTED_RIVER)).replacePartially(
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(HABiomes.FORESTED_RIVER),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.TEMPERATURE, -0.15, 0.2)
                    .climateRange(ClimateParameter.HUMIDITY, 0.1, 0.3)
            )
        )

        context.register(
            FLORAL_RIVER,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.FLORAL_RIVER)).replacePartially(
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(HABiomes.FLORAL_RIVER),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.TEMPERATURE, -0.15, 0.2)
                    .climateMax(ClimateParameter.HUMIDITY, -0.35)
                    .climateMax(ClimateParameter.WEIRDNESS, -0.01)
            )
        )

        context.register(
            EXOTIC_RIVER,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.EXOTIC_RIVER)).replacePartially(
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(HABiomes.EXOTIC_RIVER),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.TEMPERATURE, 0.2, 0.55)
                    .climateRange(ClimateParameter.HUMIDITY, -1.0, -0.1)
            )
        )

        context.register(
            EXOTIC_DESERT_RIVER,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.EXOTIC_DESERT_RIVER)).replacePartially(
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(HABiomes.EXOTIC_DESERT_RIVER),
                ParameterBuilder.create()
                    .climateMax(ClimateParameter.CONTINENTALNESS, (0.03))
                    .climateMin(ClimateParameter.EROSION, (-0.375))
                    .climateMin(ClimateParameter.TEMPERATURE, (0.55))
            )
        )

        context.register(
            EXOTIC_DESERT_RIVER_SHATTERED,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.EXOTIC_DESERT_RIVER)).replacePartially(
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(HABiomes.EXOTIC_DESERT_RIVER),
                ParameterBuilder.create()
                    .climateMin(ClimateParameter.CONTINENTALNESS, (0.03))
                    .climateMin(ClimateParameter.EROSION, (0.05))
                    .climateMin(ClimateParameter.TEMPERATURE, (0.55))
            )
        )

        context.register(
            EXOTIC_BADLANDS_RIVER,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.EXOTIC_BADLANDS_RIVER)).replacePartially(
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(HABiomes.EXOTIC_BADLANDS_RIVER),
                ParameterBuilder.create()
                    .climateMin(ClimateParameter.CONTINENTALNESS, (0.03))
                    .climateMax(ClimateParameter.EROSION, (0.05))
                    .climateMin(ClimateParameter.TEMPERATURE, (0.55))
            )
        )

        context.register(
            EXOTIC_BADLANDS_RIVER_SHATTERED,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.EXOTIC_BADLANDS_RIVER)).replacePartially(
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(HABiomes.EXOTIC_BADLANDS_RIVER),
                ParameterBuilder.create()
                    .climateMax(ClimateParameter.CONTINENTALNESS, (0.03))
                    .climateMax(ClimateParameter.EROSION, (-0.0375))
                    .climateMin(ClimateParameter.TEMPERATURE, (0.55))
            )
        )

        //#region River Fixes
        context.register(
            WARM_OCEAN_RIVER_REPLACER,
            BiomeInjector.builder(Level.OVERWORLD).replacePartially(
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(Biomes.WARM_OCEAN),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.CONTINENTALNESS, -0.455, -0.19)
                    .climateRange(ClimateParameter.TEMPERATURE, 0.55, 1.0)
            )
        )

        context.register(
            DEEP_WARM_OCEAN_RIVER_REPLACER,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.DEEP_WARM_OCEAN)).replacePartially(
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(HABiomes.DEEP_WARM_OCEAN),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.CONTINENTALNESS, -1.05, -0.455)
                    .climateRange(ClimateParameter.TEMPERATURE, 0.55, 1.0)
            )
        )

        context.register(
            LUKEWARM_OCEAN_RIVER_REPLACER,
            BiomeInjector.builder(Level.OVERWORLD).replacePartially(
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(Biomes.LUKEWARM_OCEAN),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.CONTINENTALNESS, -0.455, -0.19)
                    .climateRange(ClimateParameter.TEMPERATURE, 0.2, 0.55)
            )
        )

        context.register(
            DEEP_LUKEWARM_OCEAN_RIVER_REPLACER,
            BiomeInjector.builder(Level.OVERWORLD).replacePartially(
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(Biomes.DEEP_LUKEWARM_OCEAN),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.CONTINENTALNESS, -1.05, -0.19)
                    .climateRange(ClimateParameter.TEMPERATURE, 0.2, 0.55)
            )
        )

        context.register(
            OCEAN_RIVER_REPLACER,
            BiomeInjector.builder(Level.OVERWORLD).replacePartially(
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(Biomes.OCEAN),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.CONTINENTALNESS, -0.455, -0.19)
                    .climateRange(ClimateParameter.TEMPERATURE, -0.15, 0.2)
            )
        )

        context.register(
            DEEP_OCEAN_RIVER_REPLACER,
            BiomeInjector.builder(Level.OVERWORLD).replacePartially(
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(Biomes.DEEP_OCEAN),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.CONTINENTALNESS, -1.05, -0.19)
                    .climateRange(ClimateParameter.TEMPERATURE, -0.15, 0.2)
            )
        )

        context.register(
            COLD_OCEAN_RIVER_REPLACER,
            BiomeInjector.builder(Level.OVERWORLD).replacePartially(
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(Biomes.COLD_OCEAN),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.CONTINENTALNESS, -0.455, -0.19)
                    .climateRange(ClimateParameter.TEMPERATURE, -0.45, -0.15)
            )
        )

        context.register(
            DEEP_COLD_OCEAN_RIVER_REPLACER,
            BiomeInjector.builder(Level.OVERWORLD).replacePartially(
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(Biomes.DEEP_COLD_OCEAN),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.CONTINENTALNESS, -1.05, -0.19)
                    .climateRange(ClimateParameter.TEMPERATURE, -0.45, -0.15)
            )
        )

        context.register(
            FROZEN_OCEAN_RIVER_REPLACER,
            BiomeInjector.builder(Level.OVERWORLD).replacePartially(
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(Biomes.FROZEN_OCEAN),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.CONTINENTALNESS, -0.455, -0.19)
                    .climateRange(ClimateParameter.TEMPERATURE, -0.45, -0.15)
            )
        )

        context.register(
            DEEP_FROZEN_OCEAN_RIVER_REPLACER,
            BiomeInjector.builder(Level.OVERWORLD).replacePartially(
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(Biomes.DEEP_FROZEN_OCEAN),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.CONTINENTALNESS, -1.05, -0.19)
                    .climateRange(ClimateParameter.TEMPERATURE, -1.0, -0.45)
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
