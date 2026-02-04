package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.TallSeagrassBlock
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration

class TallRedAlgaePatchFeature(codec: Codec<ProbabilityFeatureConfiguration>) :
    Feature<ProbabilityFeatureConfiguration>(codec) {

    override fun place(context: FeaturePlaceContext<ProbabilityFeatureConfiguration>): Boolean {
        val level = context.level()
        val origin = context.origin()
        val radius = 4
        var placedAny = false
        val random = context.random()

        for (dx in -radius..radius) {
            for (dz in -radius..radius) {
                if (random.nextFloat() > 0.66f) continue

                val x = origin.x + dx
                val z = origin.z + dz
                val y = level.getHeight(Heightmap.Types.OCEAN_FLOOR, x, z)
                val pos = BlockPos(x, y, z)

                if (!level.getBlockState(pos).`is`(Blocks.WATER)) continue

                val baseState = HybridAquaticBlocks.TALL_RED_ALGAE.get().defaultBlockState()
                if (!baseState.canSurvive(level, pos)) continue

                val above = pos.above()
                if (!level.getBlockState(above).`is`(Blocks.WATER)) continue

                val upperState = baseState.setValue(TallSeagrassBlock.HALF, DoubleBlockHalf.UPPER)
                level.setBlock(pos, baseState, 2)
                level.setBlock(above, upperState, 2)
                placedAny = true
            }
        }

        return placedAny
    }
}