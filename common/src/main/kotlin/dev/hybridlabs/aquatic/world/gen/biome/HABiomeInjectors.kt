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

    val TROPICAL_RIVER: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("tropical_river"))

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

        // Runs after Biolith has taken its seagrass bed and red meadow shares, so
        // the reef keeps roughly the share it had with Biolith
        context.register(
            CORAL_REEF,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.CORAL_REEF)).replacePartially(
                biomes.getOrThrow(Biomes.WARM_OCEAN),
                biomes.getOrThrow(HABiomes.CORAL_REEF),
                ParameterBuilder.create()
                    .densityFunctionMin(densityFunctions.getOrThrow(REEF_SELECTOR), 0.2)
            )
        )

        //
        context.register(
            DEEP_WARM_OCEAN,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.DEEP_WARM_OCEAN)).replacePartially(
                biomes.getOrThrow(Biomes.WARM_OCEAN),
                biomes.getOrThrow(HABiomes.DEEP_WARM_OCEAN),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.CONTINENTALNESS, -1.05, -0.455)
            )
        )

        // Add trench biomes to the appropriate deep ocean types
        context.register(
            WARM_TRENCH,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.WARM_TRENCH)).replacePartially(
                biomes.getOrThrow(HABiomes.DEEP_WARM_OCEAN),
                biomes.getOrThrow(HABiomes.WARM_TRENCH),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.CONTINENTALNESS, -0.71, -0.63)
            )
        )

        context.register(
            LUKEWARM_TRENCH,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.LUKEWARM_TRENCH)).replacePartially(
                biomes.getOrThrow(Biomes.DEEP_LUKEWARM_OCEAN),
                biomes.getOrThrow(HABiomes.LUKEWARM_TRENCH),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.CONTINENTALNESS, -0.71, -0.63)
            )
        )

        context.register(
            TRENCH,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.TRENCH)).replacePartially(
                biomes.getOrThrow(Biomes.DEEP_OCEAN),
                biomes.getOrThrow(HABiomes.TRENCH),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.CONTINENTALNESS, -0.71, -0.63)
            )
        )

        context.register(
            COLD_TRENCH,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.COLD_TRENCH)).replacePartially(
                biomes.getOrThrow(Biomes.DEEP_COLD_OCEAN),
                biomes.getOrThrow(HABiomes.COLD_TRENCH),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.CONTINENTALNESS, -0.71, -0.63)
            )
        )

        context.register(
            FROZEN_TRENCH,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.FROZEN_TRENCH)).replacePartially(
                biomes.getOrThrow(Biomes.DEEP_FROZEN_OCEAN),
                biomes.getOrThrow(HABiomes.FROZEN_TRENCH),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.CONTINENTALNESS, -0.71, -0.63)
            )
        )

        context.register(
            TROPICAL_RIVER,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.TROPICAL_RIVER)).replacePartially(
                biomes.getOrThrow(Biomes.RIVER),
                biomes.getOrThrow(HABiomes.TROPICAL_RIVER),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.TEMPERATURE, 0.2, 1.0)
                    .climateRange(ClimateParameter.HUMIDITY, 0.1, 1.0)
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
