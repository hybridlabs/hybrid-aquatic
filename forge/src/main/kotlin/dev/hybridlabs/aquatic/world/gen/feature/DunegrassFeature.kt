package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.block.TallDunegrassBlock
import dev.hybridlabs.aquatic.block.HAPlatformBlocks
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration

@Suppress("DEPRECATION")
class DunegrassFeature(codec: Codec<ProbabilityFeatureConfiguration>) :
    Feature<ProbabilityFeatureConfiguration>(codec) {

    override fun place(context: FeaturePlaceContext<ProbabilityFeatureConfiguration>): Boolean {
        var placed = false
        val random = context.random()
        val world = context.level()
        val origin = context.origin()
        val config = context.config()

        val dx = random.nextInt(8) - random.nextInt(8)
        val dz = random.nextInt(8) - random.nextInt(8)
        val posY = world.getHeight(Heightmap.Types.WORLD_SURFACE, origin.x + dx, origin.z + dz)
        val pos = BlockPos(origin.x + dx, posY, origin.z + dz)
        val seaLevel = world.level.chunkSource.generator.seaLevel

        if (posY > seaLevel + 2 && world.isEmptyBlock(pos)) {
            val blTall = random.nextDouble() < config.probability

            if (blTall) {
                val lower = HAPlatformBlocks.TALL_DUNEGRASS.get().defaultBlockState()
                    .setValue(TallDunegrassBlock.HALF, DoubleBlockHalf.LOWER)
                val upper = HAPlatformBlocks.TALL_DUNEGRASS.get().defaultBlockState()
                    .setValue(TallDunegrassBlock.HALF, DoubleBlockHalf.UPPER)

                if (pos.y < world.maxBuildHeight - 1 &&
                    world.isEmptyBlock(pos.above()) &&
                    lower.canSurvive(world, pos)
                ) {
                    world.setBlock(pos, lower, 2)
                    world.setBlock(pos.above(), upper, 2)
                    placed = true
                }
            } else {
                val blockState = HAPlatformBlocks.DUNEGRASS.get().defaultBlockState()
                if (blockState.canSurvive(world, pos)) {
                    world.setBlock(pos, blockState, 2)
                    placed = true
                }
            }
        }

        return placed
    }
}
