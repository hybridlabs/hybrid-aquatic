package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.level.WorldGenLevel
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext

/**
 * Replacement for vanilla's removed random patch feature, seeding a nested placed feature around
 * the origin.
 */
class HARandomPatchFeature(codec: Codec<HARandomPatchConfiguration>) : Feature<HARandomPatchConfiguration>(codec) {
    override fun place(context: FeaturePlaceContext<HARandomPatchConfiguration>): Boolean {
        val config = context.config()
        val random: RandomSource = context.random()
        val origin = context.origin()
        val level: WorldGenLevel = context.level()
        val mutablePos = BlockPos.MutableBlockPos()
        val xzSpread = config.xzSpread + 1
        val ySpread = config.ySpread + 1
        var placed = 0

        for (attempt in 0 until config.tries) {
            mutablePos.setWithOffset(
                origin,
                random.nextInt(xzSpread) - random.nextInt(xzSpread),
                random.nextInt(ySpread) - random.nextInt(ySpread),
                random.nextInt(xzSpread) - random.nextInt(xzSpread)
            )
            if (config.feature.value().place(level, context.chunkGenerator(), random, mutablePos)) {
                placed++
            }
        }

        return placed > 0
    }
}
