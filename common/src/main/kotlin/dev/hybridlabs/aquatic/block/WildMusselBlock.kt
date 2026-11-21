package dev.hybridlabs.aquatic.block

import net.minecraft.world.level.LevelReader

import net.minecraft.world.entity.LivingEntity

import net.minecraft.util.RandomSource

import com.mojang.serialization.MapCodec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.ScheduledTickAccess
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.BushBlock
import net.minecraft.world.level.block.SimpleWaterloggedBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.material.Fluid
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

@Suppress("OVERRIDE_DEPRECATION")
class WildMusselBlock(properties: Properties) :
    BushBlock(properties), SimpleWaterloggedBlock {
    override fun getShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext,
    ): VoxelShape {
        return SHAPE
    }

    override fun mayPlaceOn(floor: BlockState, world: BlockGetter, pos: BlockPos): Boolean {
        return floor.isFaceSturdy(world, pos, Direction.UP) && !floor.`is`(Blocks.MAGMA_BLOCK)
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        val world = ctx.level
        val pos = ctx.clickedPos
        return defaultBlockState()
            .setValue(WATERLOGGED, world.getFluidState(pos) == Fluids.WATER)
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(WATERLOGGED)) {
            Fluids.WATER.getSource(false)
        } else {
            super.getFluidState(state)
        }
    }

    override fun getOcclusionShape(state: BlockState): VoxelShape {
        return Shapes.empty()
    }

    override fun updateShape(
        state: BlockState,
        world: LevelReader,
        scheduledTickAccess: ScheduledTickAccess,
        pos: BlockPos,
        direction: Direction,
        neighborPos: BlockPos,
        neighborState: BlockState,
        random: RandomSource
    ): BlockState {
        val blockState = super.updateShape(state, world, scheduledTickAccess, pos, direction, neighborPos, neighborState, random)
        if (!blockState.isAir) {
            scheduledTickAccess.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world))
        }

        return blockState
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(WATERLOGGED)
    }

    override fun canPlaceLiquid(
        player: LivingEntity?,
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

    override fun codec(): MapCodec<BushBlock> {
        return CODEC as MapCodec<BushBlock>
    }

    companion object {
        val CODEC: MapCodec<WildMusselBlock> = simpleCodec(::WildMusselBlock)
        private val SHAPE: VoxelShape = box(2.0, 0.0, 2.0, 14.0, 12.0, 14.0)

        val WATERLOGGED: BooleanProperty = BlockStateProperties.WATERLOGGED
    }
}