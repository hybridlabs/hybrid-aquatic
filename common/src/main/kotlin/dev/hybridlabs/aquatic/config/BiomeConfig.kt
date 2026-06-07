package dev.hybridlabs.aquatic.config

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder

data class BiomeConfig(
    val enableBiomes: Boolean = true,
    val generateDeepWarmOcean: Boolean = true,
    val generateDeepCoralReef: Boolean = true,
    val generateRedMeadow: Boolean = true,
    val generateSeagrassBed: Boolean = true,
    val generateTropicalRiver: Boolean = true,
    val generateTidePools: Boolean = true,
    val generateSulfuricCave: Boolean = true,
) {
    companion object {
        val CODEC: Codec<BiomeConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.BOOL.fieldOf("enableBiomes").forGetter(BiomeConfig::enableBiomes),
                Codec.BOOL.fieldOf("generateDeepWarmOcaen").forGetter(BiomeConfig::generateDeepWarmOcean),
                Codec.BOOL.fieldOf("generateDeepCoralReef").forGetter(BiomeConfig::generateDeepCoralReef),
                Codec.BOOL.fieldOf("generateRedMeadow").forGetter(BiomeConfig::generateRedMeadow),
                Codec.BOOL.fieldOf("generateSeagrassBed").forGetter(BiomeConfig::generateSeagrassBed),
                Codec.BOOL.fieldOf("generateTropicalRiver").forGetter(BiomeConfig::generateTropicalRiver),
                Codec.BOOL.fieldOf("generateTidePools").forGetter(BiomeConfig::generateTidePools),
                Codec.BOOL.fieldOf("generateSulfuricCave").forGetter(BiomeConfig::generateSulfuricCave),
            ).apply(instance, ::BiomeConfig)
        }
    }
}