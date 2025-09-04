package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.world.gen.feature.FeatureConfig
import net.minecraft.world.gen.stateprovider.BlockStateProvider

data class BullKelpFeatureConfig(val toPlace: BlockStateProvider) : FeatureConfig {
    companion object {
        val CODEC: Codec<BullKelpFeatureConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                BlockStateProvider.TYPE_CODEC.fieldOf("to_place")
                    .forGetter(BullKelpFeatureConfig::toPlace)
            ).apply(instance, ::BullKelpFeatureConfig)
        }
    }
}