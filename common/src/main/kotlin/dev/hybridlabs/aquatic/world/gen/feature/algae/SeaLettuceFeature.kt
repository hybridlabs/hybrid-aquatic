package dev.hybridlabs.aquatic.world.gen.feature.algae

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import dev.hybridlabs.aquatic.block.HABlocks
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.TallSeagrassBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.util.RandomSource
import net.minecraft.world.level.WorldGenLevel
import net.minecraft.world.level.chunk.ChunkGenerator
import net.minecraft.world.level.levelgen.feature.Feature

data class SeaLettuceFeature(val probability: Float) : Feature {
    override fun codec(): MapCodec<SeaLettuceFeature> = CODEC

    override fun place(
        structureLevelAccessor: WorldGenLevel,
        chunkGenerator: ChunkGenerator,
        random: RandomSource,
        blockPos: BlockPos,
    ): Boolean {
        var bl = false
        val i = random.nextInt(8) - random.nextInt(8)
        val j = random.nextInt(8) - random.nextInt(8)
        val k = structureLevelAccessor.getHeight(Heightmap.Types.OCEAN_FLOOR, blockPos.x + i, blockPos.z + j)
        val blockPos2 = BlockPos(blockPos.x + i, k, blockPos.z + j)
        if (structureLevelAccessor.getBlockState(blockPos2).`is`(Blocks.WATER)) {
            val bl2 = random.nextDouble() < this.probability.toDouble()
            val blockState = if (bl2) {
                HABlocks.TALL_SEA_LETTUCE.get().defaultBlockState()
            } else {
                HABlocks.SEA_LETTUCE.get().defaultBlockState()
            }
            if (blockState.canSurvive(structureLevelAccessor, blockPos2)) {
                if (bl2) {
                    val blockState2 = blockState.setValue(TallSeagrassBlock.HALF, DoubleBlockHalf.UPPER) as BlockState
                    val blockPos3 = blockPos2.above()
                    if (structureLevelAccessor.getBlockState(blockPos3).`is`(Blocks.WATER)) {
                        structureLevelAccessor.setBlock(blockPos2, blockState, 2)
                        structureLevelAccessor.setBlock(blockPos3, blockState2, 2)
                    }
                } else {
                    structureLevelAccessor.setBlock(blockPos2, blockState, 2)
                }

                bl = true
            }
        }

        return bl
    }

    companion object {
        val CODEC: MapCodec<SeaLettuceFeature> = RecordCodecBuilder.mapCodec { instance ->
            instance.group(
                com.mojang.serialization.Codec.floatRange(0.0f, 1.0f).fieldOf("probability")
                    .forGetter(SeaLettuceFeature::probability)
            ).apply(instance, ::SeaLettuceFeature)
        }
    }
}
