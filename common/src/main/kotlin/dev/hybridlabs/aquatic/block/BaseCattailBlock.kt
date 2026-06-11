package dev.hybridlabs.aquatic.block

import net.minecraft.core.BlockPos
import net.minecraft.tags.BlockTags
import net.minecraft.tags.FluidTags
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.DoublePlantBlock
import net.minecraft.world.level.block.LiquidBlockContainer
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf
import net.minecraft.world.level.block.state.properties.EnumProperty
import net.minecraft.world.level.material.Fluid
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

@Suppress("OVERRIDE_DEPRECATION")
open class BaseCattailBlock(settings: Properties) : DoublePlantBlock(settings), LiquidBlockContainer {
    override fun getShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext,
    ): VoxelShape {
        return SHAPE
    }

    override fun mayPlaceOn(floor: BlockState, world: BlockGetter, pos: BlockPos): Boolean {
        return floor.`is`(BlockTags.DIRT) || floor.`is`(Blocks.CLAY) || floor.`is`(BlockTags.SAND)
    }

    override fun canSurvive(state: BlockState, level: LevelReader, pos: BlockPos): Boolean {
        return if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
            val belowState = level.getBlockState(pos.below())
            belowState.`is`(this) && belowState.getValue(HALF) == DoubleBlockHalf.LOWER
        } else {
            val fluidHere = level.getFluidState(pos)
            val blockBelow = level.getBlockState(pos.below())
            fluidHere.`is`(FluidTags.WATER) && fluidHere.amount == 8 && mayPlaceOn(blockBelow, level, pos.below())
        }
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        val world = ctx.level
        val pos = ctx.clickedPos

        val fluidHere = world.getFluidState(pos)
        val blockAbove = world.getBlockState(pos.above())

        return if (fluidHere.`is`(FluidTags.WATER) && fluidHere.amount == 8 && blockAbove.isAir) {
            defaultBlockState()
        } else {
            null
        }
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
            Fluids.WATER.getSource(false)
        } else {
            Fluids.EMPTY.defaultFluidState()
        }
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