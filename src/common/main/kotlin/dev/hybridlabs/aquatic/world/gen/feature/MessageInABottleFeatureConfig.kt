package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider

data class MessageInABottleFeatureConfig(val toPlace: BlockStateProvider) : FeatureConfiguration {
    companion object {
        /**
         * The codec for this class.
         */
        val CODEC: Codec<MessageInABottleFeatureConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                BlockStateProvider.CODEC.fieldOf("to_place")
                    .forGetter(MessageInABottleFeatureConfig::toPlace)
            ).apply(instance, ::MessageInABottleFeatureConfig)
        }
    }
}