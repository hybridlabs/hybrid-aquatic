package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.Holder
import net.minecraft.util.ExtraCodecs
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import net.minecraft.world.level.levelgen.placement.PlacedFeature

/**
 * Minecraft 26.1 removed the built-in random patch feature in favour of placement modifiers, which
 * cannot express a nested feature. This mirrors the old configuration so existing worldgen data
 * keeps its behaviour.
 */
data class HARandomPatchConfiguration(
    val tries: Int = 128,
    val xzSpread: Int = 7,
    val ySpread: Int = 3,
    val feature: Holder<PlacedFeature>,
) : FeatureConfiguration {
    companion object {
        val CODEC: Codec<HARandomPatchConfiguration> = RecordCodecBuilder.create { instance ->
            instance.group(
                ExtraCodecs.POSITIVE_INT.fieldOf("tries").orElse(128).forGetter(HARandomPatchConfiguration::tries),
                ExtraCodecs.NON_NEGATIVE_INT.fieldOf("xz_spread").orElse(7)
                    .forGetter(HARandomPatchConfiguration::xzSpread),
                ExtraCodecs.NON_NEGATIVE_INT.fieldOf("y_spread").orElse(3)
                    .forGetter(HARandomPatchConfiguration::ySpread),
                PlacedFeature.CODEC.fieldOf("feature").forGetter(HARandomPatchConfiguration::feature)
            ).apply(instance, ::HARandomPatchConfiguration)
        }
    }
}
