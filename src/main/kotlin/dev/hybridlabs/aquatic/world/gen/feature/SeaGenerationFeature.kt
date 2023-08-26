@file:Suppress("DEPRECATION")

package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.world.gen.feature.config.SeaGenerationFeatureConfig
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.registry.tag.TagKey
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.util.FeatureContext

class SeaGenerationFeature(configCodec: Codec<SeaGenerationFeatureConfig>) : Feature<SeaGenerationFeatureConfig>(configCodec) {
    override fun generate(context: FeatureContext<SeaGenerationFeatureConfig>): Boolean {
        val world = context.world
        val pos = context.origin
        val (blockState: BlockState, generateOn: TagKey<Block>) = context.config

        val testPos = pos.mutableCopy()
        val yValues = world.bottomY..world.seaLevel
        return yValues.none { y ->
            testPos.setY(y)

            if (world.getBlockState(testPos).isIn(generateOn)) {
                if (!world.getFluidState(testPos.up()).isEmpty) {
                    world.setBlockState(testPos, blockState, Block.NOTIFY_NEIGHBORS)
                    return@none testPos.y >= world.topY
                }
            }

            false
        }
    }
}
