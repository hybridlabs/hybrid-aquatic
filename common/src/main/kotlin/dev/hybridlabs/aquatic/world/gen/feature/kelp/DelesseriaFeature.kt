package dev.hybridlabs.aquatic.world.gen.feature.kelp

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import dev.hybridlabs.aquatic.block.HABlocks
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.KelpBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.util.RandomSource
import net.minecraft.world.level.WorldGenLevel
import net.minecraft.world.level.chunk.ChunkGenerator
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider

data class DelesseriaFeature(val toPlace: BlockStateProvider) : Feature {
    override fun codec(): MapCodec<DelesseriaFeature> = CODEC

    override fun place(
        structureLevelAccessor: WorldGenLevel,
        chunkGenerator: ChunkGenerator,
        random: RandomSource,
        blockPos: BlockPos,
    ): Boolean {
        var i = 0
        val j = structureLevelAccessor.getHeight(Heightmap.Types.OCEAN_FLOOR, blockPos.x, blockPos.z)
        var blockPos2 = BlockPos(blockPos.x, j, blockPos.z)
        if (structureLevelAccessor.getBlockState(blockPos2).`is`(Blocks.WATER)) {
            val blockState = HABlocks.DELESSERIA.get().defaultBlockState()
            val blockState2 = HABlocks.DELESSERIA_PLANT.get().defaultBlockState()
            val k = 1 + random.nextInt(10)

            for (l in 0..k) {
                if (structureLevelAccessor.getBlockState(blockPos2)
                        .`is`(Blocks.WATER) && structureLevelAccessor.getBlockState(blockPos2.above())
                        .`is`(Blocks.WATER) && blockState2.canSurvive(structureLevelAccessor, blockPos2)
                ) {
                    if (l == k) {
                        structureLevelAccessor.setBlock(
                            blockPos2,
                            blockState.setValue(KelpBlock.AGE, random.nextInt(4) + 20) as BlockState,
                            2
                        )
                        ++i
                    } else {
                        structureLevelAccessor.setBlock(blockPos2, blockState2, 2)
                    }
                } else if (l > 0) {
                    val blockPos3 = blockPos2.below()
                    if (blockState.canSurvive(structureLevelAccessor, blockPos3) && !(structureLevelAccessor.getBlockState(
                            blockPos3.below()
                        ).`is`(HABlocks.DELESSERIA.get()))
                    ) {
                        structureLevelAccessor.setBlock(
                            blockPos3,
                            blockState.setValue(KelpBlock.AGE, random.nextInt(4) + 20) as BlockState,
                            2
                        )
                        ++i
                    }
                    break
                }

                blockPos2 = blockPos2.above()
            }
        }

        return i > 0
    }

    companion object {
        val CODEC: MapCodec<DelesseriaFeature> = RecordCodecBuilder.mapCodec { instance ->
            instance.group(
                BlockStateProvider.DIRECT_CODEC.fieldOf("to_place")
                    .forGetter(DelesseriaFeature::toPlace)
            ).apply(instance, ::DelesseriaFeature)
        }
    }
}
