package dev.hybridlabs.aquatic.world.gen.feature.kelp

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider

data class DelesseriaFeatureConfig(val toPlace: BlockStateProvider) : FeatureConfiguration {
    companion object {
        val CODEC: Codec<DelesseriaFeatureConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                BlockStateProvider.CODEC.fieldOf("to_place")
                    .forGetter(DelesseriaFeatureConfig::toPlace)
            ).apply(instance, ::DelesseriaFeatureConfig)
        }
    }
}