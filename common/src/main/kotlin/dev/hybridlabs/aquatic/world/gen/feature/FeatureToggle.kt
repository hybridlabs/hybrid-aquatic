package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.config.FeatureConfig

/**
 * Runtime feature switch for a [BiomeFeatureAddition].
 *
 * The same switch is evaluated by Fabric's biome modifications and by the NeoForge biome modifier. Keeping the
 * decision here means a config edit takes effect on both loaders without regenerating the mod jar.
 */
enum class FeatureToggle {
    ALWAYS,
    BRINE_POOLS,
    RIVER_PLANTS,
    SARGASSUM,
    FLOATING_SARGASSUM,
    BULL_KELP,
    DELESSERIA,
    THERMAL_VENTS,
    MOUNDS;

    fun isEnabled(config: FeatureConfig): Boolean = when (this) {
        ALWAYS -> true
        BRINE_POOLS -> config.generateBrinePools
        RIVER_PLANTS -> config.generateRiverPlants
        SARGASSUM -> config.generateSargassum
        FLOATING_SARGASSUM -> config.generateFloatingSargassum
        BULL_KELP -> config.generateBullKelp
        DELESSERIA -> config.generateDelesseria
        THERMAL_VENTS -> config.generateThermalVents
        MOUNDS -> config.generateMounds
    }

    companion object {
        val CODEC: Codec<FeatureToggle> = Codec.STRING.xmap(
            { name -> entries.first { it.name.equals(name, ignoreCase = true) } },
            { toggle -> toggle.name.lowercase() }
        )
    }
}
