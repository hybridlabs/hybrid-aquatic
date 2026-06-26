package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider

data class BullKelpFeatureConfig(val toPlace: BlockStateProvider) : FeatureConfiguration {
    companion object {
        val CODEC: Codec<BullKelpFeatureConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                BlockStateProvider.CODEC.fieldOf("to_place")
                    .forGetter(BullKelpFeatureConfig::toPlace)
            ).apply(instance, ::BullKelpFeatureConfig)
        }
    }
}