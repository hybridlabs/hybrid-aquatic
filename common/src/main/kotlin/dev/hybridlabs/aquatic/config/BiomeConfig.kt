package dev.hybridlabs.aquatic.config

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder

data class BiomeConfig(
    val enableBiomes: Boolean = true,
    val generateDeepWarmOcean: Boolean = true,
    val generateDeepCoralReef: Boolean = true,
    val generateCoralReef: Boolean = true,
    val generateTropicalDeepCoralReef: Boolean = true,
    val generateRedMeadow: Boolean = true,
    val generateSeagrassBed: Boolean = true,
    val generateTropicalRiver: Boolean = true,
    val generateTidePools: Boolean = true,
    val generateSulfuricCave: Boolean = true,
    val generateTrench: Boolean = true,
    val generateWarmTrench: Boolean = true,
    val generateLukewarmTrench: Boolean = true,
    val generateColdTrench: Boolean = true,
    val generateFrozenTrench: Boolean = true,
) {
    companion object {
        val CODEC: Codec<BiomeConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.BOOL.fieldOf("enableBiomes").forGetter(BiomeConfig::enableBiomes),
                Codec.BOOL.fieldOf("generateDeepWarmOcean")
                    .forGetter(BiomeConfig::generateDeepWarmOcean),
                Codec.BOOL.fieldOf("generateDeepCoralReef")
                    .forGetter(BiomeConfig::generateDeepCoralReef),
                Codec.BOOL.fieldOf("generateCoralReef").forGetter(BiomeConfig::generateCoralReef),
                Codec.BOOL.fieldOf("generateTropicalDeepCoralReef")
                    .forGetter(BiomeConfig::generateTropicalDeepCoralReef),
                Codec.BOOL.fieldOf("generateRedMeadow").forGetter(BiomeConfig::generateRedMeadow),
                Codec.BOOL.fieldOf("generateSeagrassBed")
                    .forGetter(BiomeConfig::generateSeagrassBed),
                Codec.BOOL.fieldOf("generateTropicalRiver")
                    .forGetter(BiomeConfig::generateTropicalRiver),
                Codec.BOOL.fieldOf("generateTidePools").forGetter(BiomeConfig::generateTidePools),
                Codec.BOOL.fieldOf("generateSulfuricCave")
                    .forGetter(BiomeConfig::generateSulfuricCave),
                Codec.BOOL.fieldOf("generateTrench").forGetter(BiomeConfig::generateTrench),
                Codec.BOOL.fieldOf("generateWarmTrench").forGetter(BiomeConfig::generateWarmTrench),
                Codec.BOOL.fieldOf("generateLukewarmTrench")
                    .forGetter(BiomeConfig::generateLukewarmTrench),
                Codec.BOOL.fieldOf("generateColdTrench").forGetter(BiomeConfig::generateColdTrench),
                Codec.BOOL.fieldOf("generateFrozenTrench")
                    .forGetter(BiomeConfig::generateFrozenTrench),
            ).apply(instance, ::BiomeConfig)
        }
    }
}
