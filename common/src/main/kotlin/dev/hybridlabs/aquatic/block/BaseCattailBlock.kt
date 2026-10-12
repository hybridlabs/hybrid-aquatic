package dev.hybridlabs.aquatic.block

import net.minecraft.core.BlockPos
import net.minecraft.tags.BlockTags
import net.minecraft.tags.FluidTags
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.DoublePlantBlock
import net.minecraft.world.level.block.SimpleWaterloggedBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf
import net.minecraft.world.level.block.state.properties.EnumProperty
import net.minecraft.world.level.material.Fluid
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

@Suppress("OVERRIDE_DEPRECATION")
open class BaseCattailBlock(settings: Properties) : DoublePlantBlock(settings), SimpleWaterloggedBlock {

    init {
        registerDefaultState(
            stateDefinition.any()
                .setValue(HALF, DoubleBlockHalf.LOWER)
                .setValue(WATERLOGGED, false)
        )
    }

    override fun getShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext,
    ): VoxelShape {
        return SHAPE
    }

    override fun mayPlaceOn(floor: BlockState, world: BlockGetter, pos: BlockPos): Boolean {
        return floor.`is`(BlockTags.DIRT) || floor.`is`(Blocks.MUD)
    }

    override fun canSurvive(state: BlockState, world: LevelReader, pos: BlockPos): Boolean {
        val half = state.getValue(HALF)

        if (half == DoubleBlockHalf.UPPER) {
            val below = world.getBlockState(pos.below())

            return below.`is`(this) &&
                below.getValue(HALF) == DoubleBlockHalf.LOWER
        }

        val belowPos = pos.below()
        val belowState = world.getBlockState(belowPos)

        if (!mayPlaceOn(belowState, world, belowPos)) {
            return false
        }

        val fluidState = world.getFluidState(pos)
        val hasWater = fluidState.`is`(FluidTags.WATER) && fluidState.amount == 8

        val fluidAbove = world.getFluidState(pos.above())
        if (fluidAbove.`is`(FluidTags.WATER)) {
            return false
        }

        return hasWater || !fluidState.`is`(FluidTags.WATER)
    }

    override fun getStateForPlacement(context: BlockPlaceContext): BlockState? {
        val level = context.level
        val pos = context.clickedPos
        val abovePos = pos.above()

        if (!level.getBlockState(abovePos).canBeReplaced(context)) {
            return null
        }

        val fluidState = level.getFluidState(pos)
        val waterlogged = fluidState.`is`(FluidTags.WATER)

        return defaultBlockState()
            .setValue(HALF, DoubleBlockHalf.LOWER)
            .setValue(WATERLOGGED, waterlogged)
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(state)
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
        builder.add(WATERLOGGED)
    }

    override fun canPlaceLiquid(
        p0: Player?,
        world: BlockGetter,
        pos: BlockPos,
        state: BlockState,
        fluid: Fluid
    ): Boolean {
        return false
    }

    override fun placeLiquid(
        world: LevelAccessor,
        pos: BlockPos,
        state: BlockState,
        fluidState: FluidState,
    ): Boolean {
        return false
    }

    companion object {
        val HALF: EnumProperty<DoubleBlockHalf> = DoublePlantBlock.HALF
        private val SHAPE: VoxelShape = box(2.0, 0.0, 2.0, 14.0, 16.0, 14.0)
    }
}
