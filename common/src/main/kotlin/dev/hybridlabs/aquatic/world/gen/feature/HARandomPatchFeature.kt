package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.BlockPos
import net.minecraft.core.Holder
import net.minecraft.util.ExtraCodecs
import net.minecraft.util.RandomSource
import net.minecraft.world.level.WorldGenLevel
import net.minecraft.world.level.chunk.ChunkGenerator
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.placement.PlacedFeature

/**
 * Replacement for vanilla's removed random patch feature, seeding a nested placed feature around
 * the origin. Minecraft 26.1 removed the built-in feature in favour of placement modifiers, which
 * cannot express a nested feature.
 */
data class HARandomPatchFeature(
    val tries: Int = 128,
    val xzSpread: Int = 7,
    val ySpread: Int = 3,
    val feature: Holder<PlacedFeature>,
) : Feature {
    override fun codec(): MapCodec<HARandomPatchFeature> = CODEC

    override fun place(
        level: WorldGenLevel,
        chunkGenerator: ChunkGenerator,
        random: RandomSource,
        origin: BlockPos,
    ): Boolean {
        val mutablePos = BlockPos.MutableBlockPos()
        val xzSpread = this.xzSpread + 1
        val ySpread = this.ySpread + 1
        var placed = 0

        for (attempt in 0 until this.tries) {
            mutablePos.setWithOffset(
                origin,
                random.nextInt(xzSpread) - random.nextInt(xzSpread),
                random.nextInt(ySpread) - random.nextInt(ySpread),
                random.nextInt(xzSpread) - random.nextInt(xzSpread)
            )
            if (this.feature.value().place(level, chunkGenerator, random, mutablePos)) {
                placed++
            }
        }

        return placed > 0
    }

    companion object {
        val CODEC: MapCodec<HARandomPatchFeature> = RecordCodecBuilder.mapCodec { instance ->
            instance.group(
                ExtraCodecs.POSITIVE_INT.fieldOf("tries").orElse(128).forGetter(HARandomPatchFeature::tries),
                ExtraCodecs.NON_NEGATIVE_INT.fieldOf("xz_spread").orElse(7)
                    .forGetter(HARandomPatchFeature::xzSpread),
                ExtraCodecs.NON_NEGATIVE_INT.fieldOf("y_spread").orElse(3)
                    .forGetter(HARandomPatchFeature::ySpread),
                PlacedFeature.CODEC.fieldOf("feature").forGetter(HARandomPatchFeature::feature)
            ).apply(instance, ::HARandomPatchFeature)
        }
    }
}
