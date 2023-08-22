package dev.hybridlabs.aquatic.features

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.features.config.SeaGenerationFeatureConfig
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.registry.Registries
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.random.Random
import net.minecraft.world.StructureWorldAccess
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.util.FeatureContext

class SeaGenerationFeature(configCodec: Codec<SeaGenerationFeatureConfig>?) : Feature<SeaGenerationFeatureConfig>(configCodec) {
    override fun generate(context: FeatureContext<SeaGenerationFeatureConfig>?): Boolean {
        if(context == null) return false

        val world: StructureWorldAccess = context.world
        val pos: BlockPos = context.origin
        val random: Random = context.random
        val config: SeaGenerationFeatureConfig = context.config

        val number: Int = config.number
        val blockId: Identifier = config.blockId
        val blockState: BlockState? = Registries.BLOCK.get(blockId).defaultState

        val generateOn: TagKey<Block> = config.generateOn

        if(blockState == null) {
            val e: IllegalStateException = IllegalStateException(String.format("%s could not be parsed to a valid block identifier!", blockId))
            HybridAquatic.LOGGER.error("Exception in SeaGenerationFeature: ", e)
            return false
        }

        var testPos = BlockPos(pos)
        for (y in world.bottomY..world.seaLevel) {
            testPos = testPos.up()
            if(world.getBlockState(testPos).isIn(generateOn)) {
                if(world.getBlockState(testPos.up()).isLiquid) {
                    world.setBlockState(testPos, blockState, Block.NOTIFY_NEIGHBORS)
                    testPos.up()

                    if(testPos.y >= world.topY) break;
                }
                return true
            }
        }
        return false
    }
}