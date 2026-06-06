package dev.hybridlabs.aquatic.config

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder

data class FeatureConfig(
    val generateBottles: Boolean = true,
    val generateFloatingSargassum: Boolean = true,
    val generateSargassum: Boolean = true,
    val generateBullKelp: Boolean = true,
    val generateDelesseria: Boolean = true,
    val generateRiverPlants: Boolean = true,
    val generateThermalVents: Boolean = true,
    val generateBrinePools: Boolean = true,
    val generateMounds: Boolean = true,
) {
    companion object {
        val CODEC: Codec<FeatureConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.BOOL.fieldOf("generateBottles").forGetter(FeatureConfig::generateBottles),
                Codec.BOOL.fieldOf("generateFloatingSargassum").forGetter(FeatureConfig::generateFloatingSargassum),
                Codec.BOOL.fieldOf("generateSargassum").forGetter(FeatureConfig::generateSargassum),
                Codec.BOOL.fieldOf("generateBullKelp").forGetter(FeatureConfig::generateBullKelp),
                Codec.BOOL.fieldOf("generateDelesseria").forGetter(FeatureConfig::generateDelesseria),
                Codec.BOOL.fieldOf("generateRiverPlants").forGetter(FeatureConfig::generateRiverPlants),
                Codec.BOOL.fieldOf("generateThermalVents").forGetter(FeatureConfig::generateThermalVents),
                Codec.BOOL.fieldOf("generateBrinePools").forGetter(FeatureConfig::generateBrinePools),
                Codec.BOOL.fieldOf("generateMounds").forGetter(FeatureConfig::generateMounds),
            ).apply(instance, ::FeatureConfig)
        }
    }
}