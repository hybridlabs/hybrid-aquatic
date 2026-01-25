package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.tags.BlockTags
import net.minecraft.tags.FluidTags
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider

@Suppress("DEPRECATION")
class BigLakeFeature(codec: Codec<Configuration>) : Feature<BigLakeFeature.Configuration>(codec) {

    private val lakeWidth = 32
    private val lakeDepth = 12
    private val waterLevel = 10

    override fun place(context: FeaturePlaceContext<Configuration>): Boolean {
        var pos = context.origin()
        val level = context.level()
        val random = context.random()
        val config = context.config()

        if (pos.y <= level.minBuildHeight + 4) return false

        pos = pos.below(4)
        val shape = BooleanArray(lakeWidth * lakeWidth * lakeDepth)
        val blobs = random.nextInt(4) + 8 // 8-11 blobs

        repeat(blobs) {
            val dx = random.nextDouble() * 16.0 + 3.0
            val dy = random.nextDouble() * 6.0 + 4.0
            val dz = random.nextDouble() * 16.0 + 3.0

            val cx = random.nextDouble() * (lakeWidth - dx - 2.0) + 1.0 + dx / 2.0
            val cy = random.nextDouble() * (lakeDepth - dy - 2.0) + 1.0 + dy / 2.0
            val cz = random.nextDouble() * (lakeWidth - dz - 2.0) + 1.0 + dz / 2.0

            for (x in 1 until lakeWidth - 1) {
                for (z in 1 until lakeWidth - 1) {
                    for (y in 1 until lakeDepth - 1) {
                        val nx = (x - cx) / (dx / 2.0)
                        val ny = (y - cy) / (dy / 2.0)
                        val nz = (z - cz) / (dz / 2.0)
                        if (nx * nx + ny * ny + nz * nz < 1.0) {
                            shape[(x * lakeWidth + z) * lakeDepth + y.toInt()] = true
                        }
                    }
                }
            }
        }

        val fluidState = config.fluid.getState(random, pos)

        for (x in 0 until lakeWidth) {
            for (z in 0 until lakeWidth) {
                for (y in 0 until lakeDepth) {
                    val edge =
                        !shape[(x * lakeWidth + z) * lakeDepth + y] && (
                                (x < lakeWidth - 1 && shape[((x + 1) * lakeWidth + z) * lakeDepth + y]) ||
                                        (x > 0 && shape[((x - 1) * lakeWidth + z) * lakeDepth + y]) ||
                                        (z < lakeWidth - 1 && shape[(x * lakeWidth + z + 1) * lakeDepth + y]) ||
                                        (z > 0 && shape[(x * lakeWidth + z - 1) * lakeDepth + y]) ||
                                        (y < lakeDepth - 1 && shape[(x * lakeWidth + z) * lakeDepth + y + 1]) ||
                                        (y > 0 && shape[(x * lakeWidth + z) * lakeDepth + y - 1])
                                )

                    if (edge) {
                        val state = level.getBlockState(pos.offset(x, y, z))
                        if (y >= waterLevel && state.liquid()) return false
                        if (y < waterLevel && !state.isSolid && state != fluidState) return false
                    }
                }
            }
        }

        // Carve the lake
        for (x in 0 until lakeWidth) {
            for (z in 0 until lakeWidth) {
                for (y in 0 until lakeDepth) {
                    if (shape[(x * lakeWidth + z) * lakeDepth + y]) {
                        val target = pos.offset(x, y, z)
                        if (canReplaceBlock(level.getBlockState(target))) {
                            val isAir = y >= waterLevel
                            level.setBlock(target, if (isAir) AIR else fluidState, 2)
                            if (isAir) {
                                level.scheduleTick(target, AIR.block, 0)
                                markAboveForPostProcessing(level, target)
                            }
                        }
                    }
                }
            }
        }

        val barrierState = config.barrier.getState(random, pos)
        if (!barrierState.isAir) {
            for (x in 0 until lakeWidth) {
                for (z in 0 until lakeWidth) {
                    for (y in 0 until lakeDepth) {
                        val edge =
                            !shape[(x * lakeWidth + z) * lakeDepth + y] && (
                                    (x < lakeWidth - 1 && shape[((x + 1) * lakeWidth + z) * lakeDepth + y]) ||
                                            (x > 0 && shape[((x - 1) * lakeWidth + z) * lakeDepth + y]) ||
                                            (z < lakeWidth - 1 && shape[(x * lakeWidth + z + 1) * lakeDepth + y]) ||
                                            (z > 0 && shape[(x * lakeWidth + z - 1) * lakeDepth + y]) ||
                                            (y < lakeDepth - 1 && shape[(x * lakeWidth + z) * lakeDepth + y + 1]) ||
                                            (y > 0 && shape[(x * lakeWidth + z) * lakeDepth + y - 1])
                                    )

                        if (edge && (y < waterLevel || random.nextInt(2) != 0)) {
                            val state = level.getBlockState(pos.offset(x, y, z))
                            if (state.isSolid && !state.`is`(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE)) {
                                val target = pos.offset(x, y, z)
                                level.setBlock(target, barrierState, 2)
                                markAboveForPostProcessing(level, target)
                            }
                        }
                    }
                }
            }
        }

        // Freeze top if water
        if (fluidState.fluidState.`is`(FluidTags.WATER)) {
            for (x in 0 until lakeWidth) {
                for (z in 0 until lakeWidth) {
                    val top = pos.offset(x, waterLevel - 1, z)
                    if (level.getBiome(top).value().shouldFreeze(level, top, false)
                        && canReplaceBlock(level.getBlockState(top))
                    ) {
                        level.setBlock(top, Blocks.ICE.defaultBlockState(), 2)
                    }
                }
            }
        }

        return true
    }

    private fun canReplaceBlock(state: BlockState): Boolean =
        !state.`is`(BlockTags.FEATURES_CANNOT_REPLACE)

    companion object {
        private val AIR: BlockState = Blocks.CAVE_AIR.defaultBlockState()
    }

    data class Configuration(
        val fluid: BlockStateProvider,
        val barrier: BlockStateProvider
    ) : FeatureConfiguration {
        companion object {
            val CODEC: Codec<Configuration> = RecordCodecBuilder.create { instance ->
                instance.group(
                    BlockStateProvider.CODEC.fieldOf("fluid").forGetter(Configuration::fluid),
                    BlockStateProvider.CODEC.fieldOf("barrier").forGetter(Configuration::barrier)
                ).apply(instance, ::Configuration)
            }
        }
    }
}