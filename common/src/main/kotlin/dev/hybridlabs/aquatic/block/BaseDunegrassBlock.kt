package dev.hybridlabs.aquatic.block

import net.minecraft.world.level.block.BonemealSource
import com.mojang.serialization.MapCodec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.tags.BlockTags
import net.minecraft.util.RandomSource
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.ScheduledTickAccess
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.BonemealableBlock
import net.minecraft.world.level.block.BushBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

open class BaseDunegrassBlock(settings: Properties) : BushBlock(settings), BonemealableBlock {
    override fun getShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return SHAPE
    }

    override fun isValidBonemealTarget(
        p0: LevelReader,
        p1: BlockPos,
        p2: BlockState, source: BonemealSource): Boolean {
        return true
    }

    override fun isBonemealSuccess(world: Level, random: RandomSource, pos: BlockPos, state: BlockState, source: BonemealSource): Boolean {
        return true
    }

    override fun performBonemeal(
        level: ServerLevel,
        random: RandomSource,
        pos: BlockPos,
        state: BlockState, source: BonemealSource) {
    }

    override fun mayPlaceOn(state: BlockState, level: BlockGetter, pos: BlockPos): Boolean {
        return state.`is`(BlockTags.SAND)
    }

    override fun updateShape(
        state: BlockState,
        level: LevelReader,
        scheduledTickAccess: ScheduledTickAccess,
        currentPos: BlockPos,
        facing: Direction,
        facingPos: BlockPos,
        facingState: BlockState,
        random: RandomSource
    ): BlockState {
        return if (!state.canSurvive(level, currentPos)) Blocks.AIR.defaultBlockState() else super.updateShape(state, level, scheduledTickAccess, currentPos, facing, facingPos, facingState, random)
    }

    override fun canSurvive(state: BlockState, level: LevelReader, pos: BlockPos): Boolean {
        val blockPos = pos.below()
        return this.mayPlaceOn(level.getBlockState(blockPos), level, blockPos)
    }


    companion object {
        private val SHAPE: VoxelShape = box(2.0, 0.0, 2.0, 14.0, 16.0, 14.0)
    }
}