package dev.hybridlabs.aquatic.world.gen.feature.corals

import com.mojang.serialization.MapCodec
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.block.state.BlockState

class BleachedCoralTableFeature : BleachedCoralFeature() {
    override fun codec(): MapCodec<out Feature> = CODEC


    override fun placeFeature(
        level: LevelAccessor,
        random: RandomSource,
        pos: BlockPos,
        state: BlockState,
    ): Boolean {

        val mutable = pos.mutable()

        placeBleachedCoralBlock(level, random, mutable, state)

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
                            placeBleachedCoralBlock(level, random, mutable, state)
                        }
                    }
                }
            }
        }

        return true
    }

    companion object {
        val CODEC: MapCodec<BleachedCoralTableFeature> = MapCodec.unit(BleachedCoralTableFeature())
    }
}
