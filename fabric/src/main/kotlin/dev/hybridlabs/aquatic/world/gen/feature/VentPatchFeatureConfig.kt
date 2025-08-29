package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import dev.hybridlabs.aquatic.block.TubeWormBlock
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider

data class VentPatchFeatureConfig(
    val baseProvider: BlockStateProvider,
    val ventProvider: BlockStateProvider,
    val wormProvider: BlockStateProvider,
    val count: IntProvider,
    val spreadRadius: IntProvider,
    val wormCount: IntProvider,
    val wormSpreadRadius: IntProvider,
    val wormCountPerBlock: IntProvider,
) : FeatureConfiguration {
    companion object {
        val CODEC: Codec<VentPatchFeatureConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                BlockStateProvider.CODEC.fieldOf("base_block").forGetter(VentPatchFeatureConfig::baseProvider),
                BlockStateProvider.CODEC.fieldOf("vent_block").forGetter(VentPatchFeatureConfig::ventProvider),
                BlockStateProvider.CODEC.fieldOf("worm_block").forGetter(VentPatchFeatureConfig::wormProvider),
                IntProvider.POSITIVE_CODEC.fieldOf("count").forGetter(VentPatchFeatureConfig::count),
                IntProvider.POSITIVE_CODEC.fieldOf("spread_radius").forGetter(VentPatchFeatureConfig::spreadRadius),
                IntProvider.POSITIVE_CODEC.fieldOf("worm_count").forGetter(VentPatchFeatureConfig::wormCount),
                IntProvider.POSITIVE_CODEC.fieldOf("worm_spread_radius")
                    .forGetter(VentPatchFeatureConfig::wormSpreadRadius),
                TubeWormBlock.WORM_COUNT_CODEC.fieldOf("worm_count_per_block")
                    .forGetter(VentPatchFeatureConfig::wormCountPerBlock),
            ).apply(instance, ::VentPatchFeatureConfig)
        }
    }
}