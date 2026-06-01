package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider

data class BrineLakeFeatureConfig(
    val barrierProvider: BlockStateProvider,
    val fluidProvider: BlockStateProvider,
) : FeatureConfiguration {
    companion object {
        val CODEC: Codec<BrineLakeFeatureConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                BlockStateProvider.CODEC.fieldOf("base_block").forGetter(BrineLakeFeatureConfig::barrierProvider),
                BlockStateProvider.CODEC.fieldOf("vent_block").forGetter(BrineLakeFeatureConfig::fluidProvider),
            ).apply(instance, ::BrineLakeFeatureConfig)
        }
    }
}