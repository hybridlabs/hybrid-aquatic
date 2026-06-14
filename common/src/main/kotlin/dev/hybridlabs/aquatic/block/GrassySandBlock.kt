package dev.hybridlabs.aquatic.block

import com.mojang.serialization.MapCodec
import net.minecraft.core.BlockPos
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.placement.AquaticPlacements
import net.minecraft.server.level.ServerLevel
import net.minecraft.tags.FluidTags
import net.minecraft.util.RandomSource
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.BonemealableBlock
import net.minecraft.world.level.block.FallingBlock
import net.minecraft.world.level.block.state.BlockState

@Suppress("OVERRIDE_DEPRECATION")
class GrassySandBlock(properties: Properties) :
    FallingBlock(properties), BonemealableBlock {

    override fun codec(): MapCodec<out FallingBlock> {
        return CODEC
    }

    override fun isValidBonemealTarget(level: LevelReader, pos: BlockPos, state: BlockState): Boolean {
        return level.getBlockState(pos.above()).isAir
    }

    override fun isBonemealSuccess(
        level: Level,
        random: RandomSource,
        pos: BlockPos,
        state: BlockState,
    ): Boolean = true

    override fun performBonemeal(
        level: ServerLevel,
        random: RandomSource,
        pos: BlockPos,
        state: BlockState,
    ) {
        val startPos = pos.above()

        val optional = level.registryAccess()
            .registryOrThrow(Registries.PLACED_FEATURE)
            .getHolder(AquaticPlacements.SEAGRASS_WARM)

        if (!optional.isPresent) return

        repeat(128) {
            var targetPos = startPos

            repeat(it / 16) {
                targetPos = targetPos.offset(
                    random.nextInt(3) - 1,
                    (random.nextInt(3) - 1) * random.nextInt(3) / 2,
                    random.nextInt(3) - 1
                )

                if (
                    !level.getBlockState(targetPos.below()).`is`(this) ||
                    level.getBlockState(targetPos)
                        .isCollisionShapeFullBlock(level, targetPos)
                ) return@repeat
            }

            if (level.getBlockState(targetPos).isAir) {
                optional.get().value().place(
                    level,
                    level.chunkSource.generator,
                    random,
                    targetPos
                )
            }
        }
    }

    override fun randomTick(
        state: BlockState,
        level: ServerLevel,
        pos: BlockPos,
        random: RandomSource,
    ) {
        val abovePos = pos.above()
        val aboveState = level.getBlockState(abovePos)

        if (aboveState.isCollisionShapeFullBlock(level, abovePos)) {
            level.setBlockAndUpdate(pos, Blocks.SAND.defaultBlockState())
            return
        }

        if (!level.getFluidState(abovePos).`is`(FluidTags.WATER)) {
            level.setBlockAndUpdate(pos, Blocks.SAND.defaultBlockState())
        }
    }

    override fun isRandomlyTicking(state: BlockState): Boolean = true

    companion object {
        val CODEC: MapCodec<GrassySandBlock> = simpleCodec(::GrassySandBlock)
    }
}