package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider

data class SargassumFeatureConfig(val toPlace: BlockStateProvider) : FeatureConfiguration uration uration {
    companion object {
        val CODEC: Codec<SargassumFeatureConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                BlockStateProvider.CODEC.fieldOf("to_place")
                    .forGetter(SargassumFeatureConfig::toPlace)
            ).apply(instance, ::SargassumFeatureConfig)
        }
    }
}