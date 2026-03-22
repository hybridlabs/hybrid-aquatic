package dev.hybridlabs.aquatic.config

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder

data class BiomeConfig(
    val enableBiomes: Boolean = true,
    val generateDeepWarmOcean: Boolean = true,
    val generateDeepReefs: Boolean = true,
    val generateRedMeadow: Boolean = true,
    val generateSeagrassBed: Boolean = true,
    val generateTropicalRiver: Boolean = true,
    val generateTidePools: Boolean = true,
    val generateBasaltBeach: Boolean = true,
    val generateSulfuricCave: Boolean = true,
) {
    companion object {
        val CODEC: Codec<BiomeConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.BOOL.fieldOf("enableBiomes").forGetter(BiomeConfig::enableBiomes),
                Codec.BOOL.fieldOf("generate_deep_warm_ocean").forGetter(BiomeConfig::generateDeepWarmOcean),
                Codec.BOOL.fieldOf("generate_deep_reef").forGetter(BiomeConfig::generateDeepReefs),
                Codec.BOOL.fieldOf("generate_red_meadow").forGetter(BiomeConfig::generateRedMeadow),
                Codec.BOOL.fieldOf("generate_seagrass_bed").forGetter(BiomeConfig::generateSeagrassBed),
                Codec.BOOL.fieldOf("generate_tropical_river").forGetter(BiomeConfig::generateTropicalRiver),
                Codec.BOOL.fieldOf("generate_tide_pools").forGetter(BiomeConfig::generateTidePools),
                Codec.BOOL.fieldOf("generate_basalt_beach").forGetter(BiomeConfig::generateBasaltBeach),
                Codec.BOOL.fieldOf("generate_sulfuric_cave").forGetter(BiomeConfig::generateSulfuricCave),
            ).apply(instance, ::BiomeConfig)
        }
    }
}