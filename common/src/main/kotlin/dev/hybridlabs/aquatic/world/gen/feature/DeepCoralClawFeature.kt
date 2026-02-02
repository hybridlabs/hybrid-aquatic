package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import net.minecraft.Util
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration
import java.util.stream.Stream

class DeepCoralClawFeature(codec: Codec<NoneFeatureConfiguration?>) : DeepCoralFeature(codec) {
    override fun placeFeature(
        level: LevelAccessor,
        random: RandomSource,
        pos: BlockPos,
        state: BlockState,
    ): Boolean {
        if (!this.placeDeepCoralBlock(level, random, pos, state)) {
            return false
        } else {
            val direction = Direction.Plane.HORIZONTAL.getRandomDirection(random)
            val i = random.nextInt(2) + 2
            val list = Util.toShuffledList(
                Stream.of(
                    direction,
                    direction.clockWise,
                    direction.counterClockWise
                ), random
            )

            for (direction1 in list.subList(0, i)) {
                val mutableBlockPos = pos.mutable()
                val j = random.nextInt(2) + 1
                mutableBlockPos.move(direction1)
                val k: Int
                val direction2: Direction?
                if (direction1 == direction) {
                    direction2 = direction
                    k = random.nextInt(3) + 2
                } else {
                    mutableBlockPos.move(Direction.UP)
                    val adirection: Array<Direction> = arrayOf(direction1, Direction.UP)
                    direction2 = Util.getRandom(adirection, random)
                    k = random.nextInt(3) + 3
                }

                var l = 0
                while (l < j && this.placeDeepCoralBlock(level, random, mutableBlockPos, state)) {
                    mutableBlockPos.move(direction2)
                    ++l
                }

                mutableBlockPos.move(direction2.opposite)
                mutableBlockPos.move(Direction.UP)

                for (i1 in 0..<k) {
                    mutableBlockPos.move(direction)
                    if (!this.placeDeepCoralBlock(level, random, mutableBlockPos, state)) {
                        break
                    }

                    if (random.nextFloat() < 0.25f) {
                        mutableBlockPos.move(Direction.UP)
                    }
                }
            }

            return true
        }
    }
}