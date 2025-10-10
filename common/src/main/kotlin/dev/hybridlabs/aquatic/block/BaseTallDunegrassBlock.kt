package dev.hybridlabs.aquatic.block

import net.minecraft.core.BlockPos
import net.minecraft.tags.BlockTags
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.DoublePlantBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf
import net.minecraft.world.level.block.state.properties.EnumProperty
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

open class BaseTallDunegrassBlock(settings: Properties) : DoublePlantBlock(settings) {
    override fun getShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return SHAPE
    }

    override fun mayPlaceOn(floor: BlockState, world: BlockGetter, pos: BlockPos): Boolean {
        return floor.`is`(BlockTags.SAND)
    }

    override fun canSurvive(state: BlockState, level: LevelReader, pos: BlockPos): Boolean {
        return if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
            val belowState = level.getBlockState(pos.below())
            belowState.`is`(this) && belowState.getValue(HALF) == DoubleBlockHalf.LOWER
        } else {
            val belowPos = pos.below()
            this.mayPlaceOn(level.getBlockState(belowPos), level, belowPos)
        }
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        val world = ctx.level
        val pos = ctx.clickedPos
        val belowPos = pos.below()
        val belowState = world.getBlockState(belowPos)

        return if (belowState.`is`(BlockTags.SAND)) {
            defaultBlockState()
        } else {
            null
        }
    }

    companion object {
        val HALF: EnumProperty<DoubleBlockHalf> = DoublePlantBlock.HALF
        private val SHAPE: VoxelShape = box(2.0, 0.0, 2.0, 14.0, 16.0, 14.0)
    }
}