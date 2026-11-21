package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.MapCodec
import dev.hybridlabs.aquatic.tag.HABlockTags
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.WorldGenLevel
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.DirectionalBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.chunk.ChunkGenerator
import net.minecraft.world.level.levelgen.feature.Feature

class AnemoneFeature : Feature {
    override fun codec(): MapCodec<out Feature> = CODEC

    override fun place(
        level: WorldGenLevel,
        chunkGenerator: ChunkGenerator,
        random: RandomSource,
        origin: BlockPos,
    ): Boolean {
        var placed = 0
        repeat(16) {
            val pos = origin.offset(
                random.nextInt(6) - 3,
                random.nextInt(6) - 3,
                random.nextInt(6) - 3
            )

            if (placeAnemoneAt(level, pos, random)) {
                placed++
            }
        }

        return placed > 0
    }

    private fun placeAnemoneAt(level: WorldGenLevel, pos: BlockPos, random: RandomSource): Boolean {
        val anemoneState = getRandomAnemoneState(level, random) ?: return false

        for (direction in Direction.entries) {
            val attachPos = pos.relative(direction)
            val attachState = level.getBlockState(attachPos)

            if (!attachState.isAir && attachState.isFaceSturdy(level, attachPos, direction.opposite)) {
                val finalState = anemoneState.setValue(DirectionalBlock.FACING, direction.opposite)

                if (level.getFluidState(pos).isSource) {
                    level.setBlock(pos, finalState, Block.UPDATE_CLIENTS)
                    return true
                }
            }
        }

        return false
    }

    private fun getRandomAnemoneState(level: LevelAccessor, random: RandomSource): BlockState? {
        val possibleBlocks = level.registryAccess()
            .lookupOrThrow(net.minecraft.core.registries.Registries.BLOCK)
            .get(HABlockTags.ANEMONES).orElse(null)
            ?.toList()
            ?: return null

        if (possibleBlocks.isEmpty()) return null

        val randomEntry = possibleBlocks[random.nextInt(possibleBlocks.size)].value()
        return randomEntry.defaultBlockState()
    }

    companion object {
        val CODEC: MapCodec<AnemoneFeature> = MapCodec.unit(AnemoneFeature())
    }
}
