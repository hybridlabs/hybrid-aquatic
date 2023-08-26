package dev.hybridlabs.aquatic.world.gen.feature.config

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.world.gen.feature.FeatureConfig

data class SeaGenerationFeatureConfig(
    /**
     * The block state to generate.
     */
    val blockState: BlockState,

    /**
     * A tag of the blocks that this feature can generate on.
     */
    val validBlocksTag: TagKey<Block>
): FeatureConfig {
    companion object {
        /**
         * The codec of this class.
         */
        val CODEC: Codec<SeaGenerationFeatureConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                    BlockState.CODEC.fieldOf("block_state").forGetter(SeaGenerationFeatureConfig::blockState),
                    TagKey.codec(RegistryKeys.BLOCK).fieldOf("valid_blocks").forGetter(SeaGenerationFeatureConfig::validBlocksTag)
            ).apply(instance, ::SeaGenerationFeatureConfig)
        }
    }
}
