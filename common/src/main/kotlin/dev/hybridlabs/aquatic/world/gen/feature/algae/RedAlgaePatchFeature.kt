package dev.hybridlabs.aquatic.world.gen.feature.algae

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import dev.hybridlabs.aquatic.block.HABlocks
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.TallSeagrassBlock
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.util.RandomSource
import net.minecraft.world.level.WorldGenLevel
import net.minecraft.world.level.chunk.ChunkGenerator
import net.minecraft.world.level.levelgen.feature.Feature
import kotlin.math.sqrt

data class RedAlgaePatchFeature(val probability: Float) : Feature {
    override fun codec(): MapCodec<RedAlgaePatchFeature> = CODEC

    override fun place(
        level: WorldGenLevel,
        chunkGenerator: ChunkGenerator,
        random: RandomSource,
        origin: BlockPos,
    ): Boolean {

        val radius = 4 + random.nextInt(6)
        val attempts = 3 + random.nextInt(2)

        var placedAny = false

        repeat(attempts) {
            val dx = random.nextInt(radius) - random.nextInt(radius)
            val dz = random.nextInt(radius) - random.nextInt(radius)

            val dist = sqrt((dx * dx + dz * dz).toDouble()) / radius.toDouble()

            val radialChance = 1.0 - dist * 0.9
            if (random.nextDouble() > radialChance) return@repeat

            val x = origin.x + dx
            val z = origin.z + dz
            val y = level.getHeight(Heightmap.Types.OCEAN_FLOOR, x, z)
            val pos = BlockPos(x, y, z)

            if (!level.getBlockState(pos).`is`(Blocks.WATER)) return@repeat

            if (random.nextDouble() > this.probability) return@repeat

            val state = when {
                dist < 0.30 && random.nextFloat() < 0.75f ->
                    HABlocks.TALL_RED_ALGAE.get().defaultBlockState()
                dist < 0.65 ->
                    HABlocks.RED_ALGAE.get().defaultBlockState()
                else ->
                    HABlocks.SHORT_RED_ALGAE.get().defaultBlockState()
            }

            if (!state.canSurvive(level, pos)) return@repeat

            if (state.`is`(HABlocks.TALL_RED_ALGAE.get())) {
                val above = pos.above()
                if (level.getBlockState(above).`is`(Blocks.WATER)) {
                    level.setBlock(pos, state, 2)
                    level.setBlock(
                        above,
                        state.setValue(TallSeagrassBlock.HALF, DoubleBlockHalf.UPPER),
                        2
                    )
                    placedAny = true
                }
            } else {
                level.setBlock(pos, state, 2)
                placedAny = true
            }
        }

        return placedAny
    }

    companion object {
        val CODEC: MapCodec<RedAlgaePatchFeature> = RecordCodecBuilder.mapCodec { instance ->
            instance.group(
                com.mojang.serialization.Codec.floatRange(0.0f, 1.0f).fieldOf("probability")
                    .forGetter(RedAlgaePatchFeature::probability)
            ).apply(instance, ::RedAlgaePatchFeature)
        }
    }
}
