package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.world.gen.feature.FeatureConfig
import net.minecraft.world.gen.stateprovider.BlockStateProvider

data class MessageInABottleFeatureConfig(val toPlace: BlockStateProvider) : FeatureConfig {
    companion object {
        /**
         * The codec for this class.
         */
        val CODEC: Codec<MessageInABottleFeatureConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                BlockStateProvider.TYPE_CODEC.fieldOf("to_place")
                    .forGetter(MessageInABottleFeatureConfig::toPlace)
            ).apply(instance, ::MessageInABottleFeatureConfig)
        }
    }
}
