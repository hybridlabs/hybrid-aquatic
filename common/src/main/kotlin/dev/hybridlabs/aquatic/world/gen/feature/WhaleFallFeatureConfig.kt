package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration

class WhaleFallFeatureConfig(
    whaleFallStructures: MutableList<ResourceLocation>,
    maxEmptyCornersAllowed: Int,
) : FeatureConfiguration {
    val whaleFallStructures: MutableList<ResourceLocation>
    val maxEmptyCornersAllowed: Int

    init {
        require(!whaleFallStructures.isEmpty()) { "Whale fall structure lists need at least one entry" }
        this.whaleFallStructures = whaleFallStructures
        this.maxEmptyCornersAllowed = maxEmptyCornersAllowed
    }

    companion object {
        val CODEC: Codec<WhaleFallFeatureConfig> =
            RecordCodecBuilder.create { instance ->
                instance.group(
                    ResourceLocation.CODEC.listOf().fieldOf("whale_fall_structures")
                        .forGetter { it.whaleFallStructures },

                    Codec.intRange(0, 7).fieldOf("max_empty_corners_allowed")
                        .forGetter { it.maxEmptyCornersAllowed }
                ).apply(instance) { whaleFallStructures,
                                    maxEmptyCornersAllowed ->
                    WhaleFallFeatureConfig(
                        whaleFallStructures,
                        maxEmptyCornersAllowed
                    )
                }
            }
    }
}