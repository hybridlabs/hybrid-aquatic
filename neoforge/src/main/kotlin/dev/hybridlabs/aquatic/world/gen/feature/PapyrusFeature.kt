package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import dev.hybridlabs.aquatic.block.TallDunegrassBlock
import dev.hybridlabs.aquatic.block.HAPlatformBlocks
import dev.hybridlabs.aquatic.block.PapyrusBlock
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.util.RandomSource
import net.minecraft.world.level.WorldGenLevel
import net.minecraft.world.level.chunk.ChunkGenerator
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.material.Fluids

@Suppress("DEPRECATION")
data class PapyrusFeature(val probability: Float) : Feature {
    override fun codec(): MapCodec<PapyrusFeature> = CODEC

    override fun place(
        world: WorldGenLevel,
        chunkGenerator: ChunkGenerator,
        random: RandomSource,
        origin: BlockPos,
    ): Boolean {
        var placed = false

        val dx = random.nextInt(8) - random.nextInt(8)
        val dz = random.nextInt(8) - random.nextInt(8)
        val posY = world.getHeight(Heightmap.Types.WORLD_SURFACE, origin.x + dx, origin.z + dz)
        val pos = BlockPos(origin.x + dx, posY, origin.z + dz)
        val seaLevel = world.level.chunkSource.generator.seaLevel

        val isNearWater = BlockPos.betweenClosedStream(
            pos.offset(-3, -2, -3),
            pos.offset(3, 2, 3)
        ).anyMatch { checkPos ->
            world.getBlockState(checkPos).`is`(Blocks.WATER)
        }

        if (posY >= (seaLevel - 1) &&
            (world.isEmptyBlock(pos) || world.getBlockState(pos).`is`(Blocks.WATER)) &&
            isNearWater
        ) {
            val blTall = random.nextDouble() < this.probability

            if (blTall) {
                val lower = HAPlatformBlocks.PAPYRUS.get().defaultBlockState()
                    .setValue(PapyrusBlock.HALF, DoubleBlockHalf.LOWER)
                val upper = HAPlatformBlocks.PAPYRUS.get().defaultBlockState()
                    .setValue(PapyrusBlock.HALF, DoubleBlockHalf.UPPER)

                if (pos.y < world.maxY &&
                    world.isEmptyBlock(pos.above()) &&
                    lower.canSurvive(world, pos)
                ) {
                    world.setBlock(pos, lower, 2)
                    world.setBlock(pos.above(), upper, 2)
                    placed = true
                }
            }
        }

        return placed
    }

    companion object {
        val CODEC: MapCodec<PapyrusFeature> = RecordCodecBuilder.mapCodec { instance ->
            instance.group(
                com.mojang.serialization.Codec.floatRange(0.0f, 1.0f).fieldOf("probability")
                    .forGetter(PapyrusFeature::probability)
            ).apply(instance, ::PapyrusFeature)
        }
    }
}
