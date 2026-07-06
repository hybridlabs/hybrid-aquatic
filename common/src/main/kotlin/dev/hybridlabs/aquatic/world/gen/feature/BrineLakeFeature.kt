package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.block.WildMusselBlock
import net.minecraft.core.BlockPos
import net.minecraft.tags.BlockTags
import net.minecraft.world.level.ChunkPos
import net.minecraft.world.level.WorldGenLevel
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext

class BrineLakeFeature(codec: Codec<BrineLakeFeatureConfig>) : Feature<BrineLakeFeatureConfig>(codec) {

    private fun checkChunkDistance(
        level: WorldGenLevel,
        originChunk: ChunkPos,
        pos: BlockPos
    ): Boolean {
        val chunk = level.getChunk(pos).pos
        return kotlin.math.abs(originChunk.x - chunk.x) <= 1 &&
                kotlin.math.abs(originChunk.z - chunk.z) <= 1
    }

    override fun place(context: FeaturePlaceContext<BrineLakeFeatureConfig>): Boolean {
        var blockPos = context.origin()
        val worldGenLevel = context.level()
        val randomSource = context.random()
        val configuration = context.config()
        if (blockPos.y <= worldGenLevel.minBuildHeight + 4) {
            return false
        } else {
            blockPos = blockPos.below(4)
            val originChunk = worldGenLevel.getChunk(context.origin()).pos
            val bls = BooleanArray(2048)
            val i = randomSource.nextInt(4) + 4

            for (j in 0..<i) {
                val d = randomSource.nextDouble() * 6.0 + 3.0
                val e = randomSource.nextDouble() * 4.0 + 2.0
                val f = randomSource.nextDouble() * 6.0 + 3.0
                val g = randomSource.nextDouble() * (10.0 - d - 2.0) + 1.0 + d / 2.0
                val h = randomSource.nextDouble() * (8.0 - e - 4.0) + 2.0 + e / 2.0
                val k = randomSource.nextDouble() * (10.0 - f - 2.0) + 1.0 + f / 2.0

                for (l in 1..8) {
                    for (m in 1..8) {
                        for (n in 1..6) {
                            val o = (l.toDouble() - g) / (d / 2.0)
                            val p = (n.toDouble() - h) / (e / 2.0)
                            val q = (m.toDouble() - k) / (f / 2.0)
                            val r = o * o + p * p + q * q
                            if (r < 1.0) {
                                bls[(l * 16 + m) * 8 + n] = true
                            }
                        }
                    }
                }
            }

            val blockState = configuration.fluidProvider.getState(randomSource, blockPos)

            for (s in 0..9) {
                for (t in 0..9) {
                    for (u in 0..7) {
                        val bl =
                            !bls[(s * 16 + t) * 8 + u] && (s < 9 && bls[((s + 1) * 16 + t) * 8 + u] || s > 0 && bls[((s - 1) * 16 + t) * 8 + u] || t < 9 && bls[(s * 16 + t + 1) * 8 + u] || t > 0 && bls[(s * 16 + (t - 1)) * 8 + u] || u < 7 && bls[(s * 16 + t) * 8 + u + 1] || u > 0 && bls[(s * 16 + t) * 8 + (u - 1)])
                        if (bl) {
                            val blockState2 = worldGenLevel.getBlockState(blockPos.offset(s, u, t))

                            if (u < 4 && !blockState2.isSolid && worldGenLevel.getBlockState(
                                    blockPos.offset(
                                        s,
                                        u,
                                        t
                                    )
                                ) !== blockState
                            ) {
                                return false
                            }
                        }
                    }
                }
            }

            for (x in 0..9) {
                for (z in 0..9) {
                    for (y in 0..7) {
                        if (bls[(x * 16 + z) * 8 + y]) {
                            val pos = blockPos.offset(x, y, z)
                            if (!checkChunkDistance(worldGenLevel, originChunk, pos)) {
                                return false
                            }
                        }
                    }
                }
            }

            for (x in 0..9) {
                for (z in 0..9) {
                    for (y in 0..7) {
                        val boundary =
                            !bls[(x * 16 + z) * 8 + y] &&
                                    (
                                            (x < 9 && bls[((x + 1) * 16 + z) * 8 + y]) ||
                                                    (x > 0 && bls[((x - 1) * 16 + z) * 8 + y]) ||
                                                    (z < 9 && bls[(x * 16 + (z + 1)) * 8 + y]) ||
                                                    (z > 0 && bls[(x * 16 + (z - 1)) * 8 + y]) ||
                                                    (y < 7 && bls[(x * 16 + z) * 8 + (y + 1)]) ||
                                                    (y > 0 && bls[(x * 16 + z) * 8 + (y - 1)])
                                            )

                        if (boundary) {
                            val pos = blockPos.offset(x, y, z)
                            if (!checkChunkDistance(worldGenLevel, originChunk, pos)) {
                                return false
                            }
                        }
                    }
                }
            }

            for (s in 0..9) {
                for (t in 0..9) {
                    for (u in 0..7) {
                        if (bls[(s * 16 + t) * 8 + u]) {
                            val blockPos2 = blockPos.offset(s, u, t)
                            if (this.canReplaceBlock(worldGenLevel.getBlockState(blockPos2))) {
                                val bl2 = u >= 4
                                worldGenLevel.setBlock(blockPos2, if (bl2) WATER else blockState, 2)
                                if (bl2) {
                                    worldGenLevel.scheduleTick(blockPos2, WATER.block, 0)
                                    this.markAboveForPostProcessing(worldGenLevel, blockPos2)
                                }
                            }
                        }
                    }
                }
            }

            val blockState3 = configuration.barrierProvider.getState(randomSource, blockPos)
            if (!blockState3.isAir) {
                for (t in 0..9) {
                    for (u in 0..9) {
                        for (v in 0..7) {
                            val bl2 =
                                !bls[(t * 16 + u) * 8 + v] && (t < 9 && bls[((t + 1) * 16 + u) * 8 + v] || t > 0 && bls[((t - 1) * 16 + u) * 8 + v] || u < 9 && bls[(t * 16 + u + 1) * 8 + v] || u > 0 && bls[(t * 16 + (u - 1)) * 8 + v] || v < 7 && bls[(t * 16 + u) * 8 + v + 1] || v > 0 && bls[(t * 16 + u) * 8 + (v - 1)])
                            if (bl2 && (v < 4 || randomSource.nextInt(2) != 0)) {
                                val blockState4 = worldGenLevel.getBlockState(blockPos.offset(t, v, u))
                                if (blockState4.isSolid && !blockState4.`is`(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE)) {
                                    val blockPos3 = blockPos.offset(t, v, u)

                                    worldGenLevel.setBlock(blockPos3, blockState3, 2)

                                    val abovePos = blockPos3.above()
                                    val aboveState = worldGenLevel.getBlockState(abovePos)

                                    if (aboveState.`is`(Blocks.WATER) && randomSource.nextInt(3) == 0) {
                                        worldGenLevel.setBlock(
                                            abovePos,
                                            HABlocks.WILD_MUSSELS.get()
                                                .defaultBlockState()
                                                .setValue(WildMusselBlock.WATERLOGGED, true),
                                            2
                                        )
                                    }

                                    this.markAboveForPostProcessing(worldGenLevel, blockPos3)
                                }
                            }
                        }
                    }
                }
            }

            return true
        }
    }

    private fun canReplaceBlock(state: BlockState): Boolean {
        return !state.`is`(BlockTags.FEATURES_CANNOT_REPLACE)
    }

    companion object {
        private val WATER: BlockState = Blocks.WATER.defaultBlockState()
    }
}