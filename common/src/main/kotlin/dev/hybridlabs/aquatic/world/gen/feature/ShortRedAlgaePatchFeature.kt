package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration

class ShortRedAlgaePatchFeature(codec: Codec<ProbabilityFeatureConfiguration>) :
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

                val state = HybridAquaticBlocks.SHORT_RED_ALGAE.get().defaultBlockState()
                if (!state.canSurvive(level, pos)) continue

                level.setBlock(pos, state, 2)
                placedAny = true
            }
        }

        return placedAny
    }
}