package dev.hybridlabs.aquatic.world.gen.biome

import dev.hybridlabs.aquatic.CommonClass
import dev.worldgen.lithostitched.api.event.AddBiomeInjectorsEvent
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector.ClimateParameter
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.ParameterBuilder
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.Level
import net.minecraft.world.level.biome.Biomes
import net.minecraft.world.level.levelgen.DensityFunction

/**
 * Biome placements done through Lithostitched biome injectors instead of Biolith.
 *
 * Injectors only test noise values at the sampled position, so Biolith's neighbour
 * and edge criteria are approximated with climate ranges.
 */
object HABiomeInjectors {
    private val REEF_SELECTOR: ResourceKey<DensityFunction> =
        ResourceKey.create(Registries.DENSITY_FUNCTION, CommonClass.locate("biome/reef_selector"))

    fun register() {
        AddBiomeInjectorsEvent.EVENT.register(AddBiomeInjectorsEvent { registries, add ->
            val biomeConfig = HABiomes.config.config.biomeConfig
            if (!biomeConfig.enableBiomes) return@AddBiomeInjectorsEvent

            val biomes = registries.lookupOrThrow(Registries.BIOME)
            val densityFunctions = registries.lookupOrThrow(Registries.DENSITY_FUNCTION)

            // Beaches bordering lukewarm oceans share their temperature band; the
            // seaward edge of the coast band stands in for Biolith's edge ratio
            if (biomeConfig.generateTidePools) {
                add.accept(
                    CommonClass.locate("tide_pools"),
                    BiomeInjector.builder(Level.OVERWORLD).replacePartially(
                        biomes.getOrThrow(Biomes.BEACH),
                        biomes.getOrThrow(HABiomes.TIDE_POOLS),
                        ParameterBuilder.create()
                            .climateRange(ClimateParameter.TEMPERATURE, 0.2, 0.55)
                            .climateRange(ClimateParameter.CONTINENTALNESS, -0.19, -0.17)
                    )
                )
            }

            // Runs after Biolith has taken its seagrass bed and red meadow shares, so
            // the reef keeps roughly the share it had with Biolith
            add.accept(
                CommonClass.locate("coral_reef"),
                BiomeInjector.builder(Level.OVERWORLD).replacePartially(
                    biomes.getOrThrow(Biomes.WARM_OCEAN),
                    biomes.getOrThrow(HABiomes.CORAL_REEF),
                    ParameterBuilder.create()
                        .densityFunctionMin(densityFunctions.getOrThrow(REEF_SELECTOR), 0.2)
                )
            )
        })
    }
}
