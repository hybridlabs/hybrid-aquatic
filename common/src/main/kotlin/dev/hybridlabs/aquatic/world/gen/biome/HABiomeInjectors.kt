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

    val TIDE_POOLS: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("tide_pools"))
    val CORAL_REEF: ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, CommonClass.locate("coral_reef"))

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
            TIDE_POOLS,
            BiomeInjector.builder(Level.OVERWORLD, BiomeEnabledPredicate(HABiomes.TIDE_POOLS)).replacePartially(
                biomes.getOrThrow(Biomes.BEACH),
                biomes.getOrThrow(HABiomes.TIDE_POOLS),
                ParameterBuilder.create()
                    .climateRange(ClimateParameter.TEMPERATURE, 0.2, 0.55)
                    .climateRange(ClimateParameter.CONTINENTALNESS, -0.19, -0.17)
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
