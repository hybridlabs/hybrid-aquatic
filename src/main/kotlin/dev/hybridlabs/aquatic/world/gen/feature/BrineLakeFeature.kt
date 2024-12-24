package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.registry.tag.BlockTags
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.util.FeatureContext

@Suppress("DEPRECATION", "NAME_SHADOWING")
class BrineLakeFeature(codec: Codec<BrineLakeFeatureConfig>) : Feature<BrineLakeFeatureConfig>(codec) {
    override fun generate(context: FeatureContext<BrineLakeFeatureConfig>): Boolean {
        val random = context.random
        val structureWorldAccess = context.world
        val blockPos = context.origin
        val config = context.config

        val bls = BooleanArray(2048)
        val i = random.nextInt(4) + 4

        for (j in 0 until i) {
            val d = random.nextDouble() * 12.0 + 6.0
            val e = random.nextDouble() * 8.0 + 4.0
            val f = random.nextDouble() * 12.0 + 6.0
            val g = random.nextDouble() * (16.0 - d - 2.0) + 1.0 + d / 2.0
            val h = random.nextDouble() * (8.0 - e - 4.0) + 2.0 + e / 2.0
            val k = random.nextDouble() * (16.0 - f - 2.0) + 1.0 + f / 2.0

            for (l in 1..14) {
                for (m in 1..14) {
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

        val blockState = config.fluidProvider[random, blockPos]

        var t: Int
        var v: Boolean
        var u: Int
        var s = 0
        while (s < 16) {
            t = 0
            while (t < 16) {
                u = 0
                while (u < 8) {
                    v =
                        !bls[(s * 16 + t) * 8 + u] && ((s < 15 && bls[((s + 1) * 16 + t) * 8 + u] || s > 0 && bls[((s - 1) * 16 + t) * 8 + u] || t < 15 && bls[(s * 16 + t + 1) * 8 + u]) || (t > 0) && bls[(s * 16 + (t - 1)) * 8 + u] || (u < 7) && bls[(s * 16 + t) * 8 + u + 1] || (u > 0) && bls[(s * 16 + t) * 8 + (u - 1)])
                    if (v) {
                        val blockState2 = structureWorldAccess.getBlockState(blockPos.add(s, u, t))
                        if (u >= 4 && blockState2.isLiquid) {
                            return false
                        }

                        if (u < 4 && !blockState2.isSolid && structureWorldAccess.getBlockState(
                                blockPos.add(
                                    s,
                                    u,
                                    t
                                )
                            ) !== blockState
                        ) {
                            return false
                        }
                    }
                    ++u
                }
                ++t
            }
            ++s
        }

        var bl2: Boolean
        s = 0
        while (s < 16) {
            t = 0
            while (t < 16) {
                u = 0
                while (u < 8) {
                    if (bls[(s * 16 + t) * 8 + u]) {
                        val blockPos2 = blockPos.add(s, u, t)
                        if (this.canReplace(structureWorldAccess.getBlockState(blockPos2))) {
                            bl2 = u >= 4
                            structureWorldAccess.setBlockState(
                                blockPos2,
                                if (bl2) WATER else blockState,
                                2
                            )
                            if (bl2) {
                                structureWorldAccess.scheduleBlockTick(blockPos2, WATER.block, 0)
                                this.markBlocksAboveForPostProcessing(structureWorldAccess, blockPos2)
                            }
                        }
                    }
                    ++u
                }
                ++t
            }
            ++s
        }

        val blockState3 = config.barrierProvider[random, blockPos]
        if (!blockState3.isAir) {
            t = 0
            while (t < 16) {
                u = 0
                while (u < 16) {
                    for (v in 0..7) {
                        bl2 =
                            !bls[(t * 16 + u) * 8 + v] && ((t < 15 && bls[((t + 1) * 16 + u) * 8 + v] || t > 0 && bls[((t - 1) * 16 + u) * 8 + v] || u < 15 && bls[(t * 16 + u + 1) * 8 + v] || u > 0 && bls[(t * 16 + (u - 1)) * 8 + v]) || v < 7 && bls[(t * 16 + u) * 8 + v + 1] || v > 0 && bls[(t * 16 + u) * 8 + (v - 1)])
                        if (bl2 && (v < 4 || random.nextInt(2) != 0)) {
                            val blockState4 = structureWorldAccess.getBlockState(blockPos.add(t, v, u))
                            if (blockState4.isSolid && !blockState4.isIn(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE)) {
                                val blockPos3 = blockPos.add(t, v, u)
                                structureWorldAccess.setBlockState(blockPos3, blockState3, 2)
                                this.markBlocksAboveForPostProcessing(structureWorldAccess, blockPos3)
                            }
                        }
                    }
                    ++u
                }
                ++t
            }
        }

        return true
    }

    private fun canReplace(state: BlockState): Boolean {
        return !state.isIn(BlockTags.FEATURES_CANNOT_REPLACE)
    }

    companion object {
        private val WATER: BlockState = Blocks.WATER.defaultState
    }
}
