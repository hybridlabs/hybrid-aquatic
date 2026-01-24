package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import net.minecraft.util.Mth
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration

class SpireFeature(codec: Codec<NoneFeatureConfiguration>) : Feature<NoneFeatureConfiguration>(codec) {
    override fun place(
        context: FeaturePlaceContext<NoneFeatureConfiguration?>,
    ): Boolean {
        val level: LevelAccessor = context.level()
        val random = context.random()
        val origin = context.origin()

        val height = random.nextInt(6) + 6
        val baseRadius = random.nextFloat() * 3f + 4f

        val minRadius = 2.0f

        val coralstone = HybridAquaticBlocks.CORALSTONE.get().defaultBlockState()

        for (y in 0 until height) {
            val progress = y.toFloat() / height

            var radius = baseRadius * Mth.cos(progress * (Math.PI.toFloat() / 2f))

            radius = maxOf(radius, minRadius)

            val r = Mth.ceil(radius)

            for (x in -r..r) {
                for (z in -r..r) {
                    if (x * x + z * z <= radius * radius) {
                        val pos = origin.offset(x, y, z)

                        if (level.isEmptyBlock(pos) || level.getBlockState(pos).`is`(Blocks.WATER)) {
                            level.setBlock(pos, coralstone, 2)
                        }
                    }
                }
            }
        }

        return true
    }
}