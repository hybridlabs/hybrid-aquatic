package dev.hybridlabs.aquatic.world.gen.feature.corals

import com.mojang.serialization.Codec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration

class ReefCoralTreeFeature(codec: Codec<NoneFeatureConfiguration>) : ReefCoralFeature(codec) {
    override fun placeFeature(
        level: LevelAccessor,
        random: RandomSource,
        pos: BlockPos,
        state: BlockState,
    ): Boolean {
        val mutableBlockPos = pos.mutable()
        val i = random.nextInt(3) + 1

        for (j in 0..<i) {
            if (!this.placeCoralBlock(level, random, mutableBlockPos, state)) {
                return true
            }

            mutableBlockPos.move(Direction.UP)
        }

        val blockpos = mutableBlockPos.immutable()
        val k = random.nextInt(3) + 2
        val list = Direction.Plane.HORIZONTAL.shuffledCopy(random)

        for (direction in list.subList(0, k)) {
            mutableBlockPos.set(blockpos)
            mutableBlockPos.move(direction)
            val l = random.nextInt(5) + 2
            var i1 = 0

            var j1 = 0
            while (j1 < l && this.placeCoralBlock(level, random, mutableBlockPos, state)) {
                ++i1
                mutableBlockPos.move(Direction.UP)
                if (j1 == 0 || i1 >= 2 && random.nextFloat() < 0.25f) {
                    mutableBlockPos.move(direction)
                    i1 = 0
                }
                ++j1
            }
        }

        return true
    }
}
