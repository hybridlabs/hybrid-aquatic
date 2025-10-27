package dev.hybridlabs.aquatic.world.gen.feature

import dev.hybridlabs.aquatic.tag.HybridAquaticBlockTags
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.WorldGenLevel
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.AttachFace
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration

class AnemoneFeature : Feature<NoneFeatureConfiguration>(NoneFeatureConfiguration.CODEC) {
    override fun place(context: FeaturePlaceContext<NoneFeatureConfiguration>): Boolean {
        val level = context.level()
        val random = context.random()
        val origin = context.origin()

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

        val horizontals = arrayOf(Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST)

        for (direction in Direction.entries) {
            val attachPos = pos.relative(direction)
            val attachState = level.getBlockState(attachPos)

            if (!attachState.isAir && attachState.isFaceSturdy(level, attachPos, direction.opposite)) {
                val face = when (direction) {
                    Direction.DOWN -> AttachFace.FLOOR
                    Direction.UP -> AttachFace.CEILING
                    else -> AttachFace.WALL
                }

                val facing = if (face == AttachFace.WALL) {
                    direction.opposite
                } else {
                    horizontals[random.nextInt(horizontals.size)]
                }

                val finalState = anemoneState
                    .setValue(FaceAttachedHorizontalDirectionalBlock.FACE, face)
                    .setValue(FaceAttachedHorizontalDirectionalBlock.FACING, facing)

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
            .registryOrThrow(net.minecraft.core.registries.Registries.BLOCK)
            .getTag(HybridAquaticBlockTags.ANEMONES)
            .orElse(null)
            ?.toList()
            ?: return null

        if (possibleBlocks.isEmpty()) return null

        val randomEntry = possibleBlocks[random.nextInt(possibleBlocks.size)].value()
        return randomEntry.defaultBlockState()
    }
}