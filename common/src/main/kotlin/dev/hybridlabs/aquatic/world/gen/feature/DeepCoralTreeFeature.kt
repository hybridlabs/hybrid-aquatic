package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration

class DeepCoralTreeFeature(codec: Codec<NoneFeatureConfiguration?>) : DeepCoralFeature(codec) {
    override fun placeFeature(
        level: LevelAccessor,
        random: RandomSource,
        pos: BlockPos,
        state: BlockState,
    ): Boolean {
        val `blockpos$mutableblockpos` = pos.mutable()
        val i = random.nextInt(3) + 1

        for (j in 0..<i) {
            if (!this.placeDeepCoralBlock(level, random, `blockpos$mutableblockpos`, state)) {
                return true
            }

            `blockpos$mutableblockpos`.move(Direction.UP)
        }

        val blockpos = `blockpos$mutableblockpos`.immutable()
        val k = random.nextInt(3) + 2
        val list = Direction.Plane.HORIZONTAL.shuffledCopy(random)

        for (direction in list.subList(0, k)) {
            `blockpos$mutableblockpos`.set(blockpos)
            `blockpos$mutableblockpos`.move(direction)
            val l = random.nextInt(5) + 2
            var i1 = 0

            var j1 = 0
            while (j1 < l && this.placeDeepCoralBlock(level, random, `blockpos$mutableblockpos`, state)) {
                ++i1
                `blockpos$mutableblockpos`.move(Direction.UP)
                if (j1 == 0 || i1 >= 2 && random.nextFloat() < 0.25f) {
                    `blockpos$mutableblockpos`.move(direction)
                    i1 = 0
                }
                ++j1
            }
        }

        return true
    }
}
