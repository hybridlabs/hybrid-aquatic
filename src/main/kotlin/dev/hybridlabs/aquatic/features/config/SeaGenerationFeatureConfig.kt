package dev.hybridlabs.aquatic.features.config

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.block.Block
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier
import net.minecraft.util.dynamic.Codecs
import net.minecraft.world.gen.feature.FeatureConfig

data class SeaGenerationFeatureConfig(
    val number: Int,
    val blockId: Identifier,
    val generateOn: TagKey<Block>
): FeatureConfig {
    companion object {
        val CODEC: Codec<SeaGenerationFeatureConfig> = RecordCodecBuilder.create { instance ->
            instance
                .group(Codecs.POSITIVE_INT.fieldOf("number").forGetter(SeaGenerationFeatureConfig::number),
                    Identifier.CODEC.fieldOf("blockId").forGetter(SeaGenerationFeatureConfig::blockId),
                    TagKey.codec(RegistryKeys.BLOCK).fieldOf("generateOn").forGetter(SeaGenerationFeatureConfig::generateOn))
                .apply(instance, ::SeaGenerationFeatureConfig)
        }
    }
}