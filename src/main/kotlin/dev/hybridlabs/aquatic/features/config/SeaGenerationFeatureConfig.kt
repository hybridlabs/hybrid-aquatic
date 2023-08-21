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
    private val number: Int,
    private val blockId: Identifier,
    private val generateOn: TagKey<Block>
): FeatureConfig {
    companion object {
        val CODEC: Codec<SeaGenerationFeatureConfig> = RecordCodecBuilder.create { instance ->
            instance
                .group(Codecs.POSITIVE_INT.fieldOf("number").forGetter(SeaGenerationFeatureConfig::getNumber),
                    Identifier.CODEC.fieldOf("blockId").forGetter(SeaGenerationFeatureConfig::getBlockId),
                    TagKey.codec(RegistryKeys.BLOCK).fieldOf("generateOn").forGetter(SeaGenerationFeatureConfig::getGenerateOn))
                .apply(instance, ::SeaGenerationFeatureConfig)
        }
    }

    fun getNumber(): Int {
        return number
    }
    fun getBlockId(): Identifier {
        return blockId
    }
    fun getGenerateOn(): TagKey<Block> {
        return generateOn
    }
}