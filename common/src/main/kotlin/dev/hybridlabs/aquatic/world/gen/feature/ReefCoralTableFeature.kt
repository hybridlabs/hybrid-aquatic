package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration

class ReefCoralTableFeature(codec: Codec<NoneFeatureConfiguration>) : ReefCoralFeature(codec) {

    override fun placeFeature(
        level: LevelAccessor,
        random: RandomSource,
        pos: BlockPos,
        state: BlockState,
    ): Boolean {

        val mutable = pos.mutable()

        placeCoralBlock(level, random, mutable, state)

        val topRadius = random.nextInt(3) + 1
        val layers = 2

        for (layer in 1..layers) {
            val radius = layer * topRadius / layers
            val y = pos.y + layer

            for (dx in -radius..radius) {
                for (dz in -radius..radius) {
                    val distSq = dx * dx + dz * dz
                    if (distSq <= radius * radius) {

                        val edge = distSq >= (radius - 1) * (radius - 1)
                        val place = !edge || random.nextFloat() < 0.95f

                        if (place) {
                            mutable.set(pos.x + dx, y, pos.z + dz)
                            placeCoralBlock(level, random, mutable, state)
                        }
                    }
                }
            }
        }

        return true
    }
}