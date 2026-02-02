package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration

class DeepCoralMushroomFeature(codec: Codec<NoneFeatureConfiguration?>) : DeepCoralFeature(codec) {
    override fun placeFeature(
        level: LevelAccessor,
        random: RandomSource,
        pos: BlockPos,
        state: BlockState,
    ): Boolean {
        val i = random.nextInt(3) + 3
        val j = random.nextInt(3) + 3
        val k = random.nextInt(3) + 3
        val l = random.nextInt(3) + 1
        val mutableBlockPos = pos.mutable()

        for (i1 in 0..j) {
            for (j1 in 0..i) {
                for (k1 in 0..k) {
                    mutableBlockPos.set(i1 + pos.x, j1 + pos.y, k1 + pos.z)
                    mutableBlockPos.move(Direction.DOWN, l)
                    if (
                        (i1 != 0 && i1 != j || j1 != 0 && j1 != i) &&
                        (k1 != 0 && k1 != k || j1 != 0 && j1 != i) &&
                        (i1 != 0 && i1 != j || k1 != 0 && k1 != k) &&
                        (i1 == 0 || i1 == j || j1 == 0 || j1 == i || k1 == 0 || k1 == k) &&
                        random.nextFloat() >= 0.1f
                    ) {
                        this.placeDeepCoralBlock(
                            level,
                            random,
                            mutableBlockPos,
                            state
                        )
                    }
                }
            }
        }

        return true
    }
}