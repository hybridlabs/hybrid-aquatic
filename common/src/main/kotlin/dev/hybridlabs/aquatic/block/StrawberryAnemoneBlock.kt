package dev.hybridlabs.aquatic.block

import com.mojang.serialization.MapCodec
import dev.hybridlabs.aquatic.block.entity.StrawberryAnemoneBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.tags.FluidTags
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.BushBlock
import net.minecraft.world.level.block.EntityBlock
import net.minecraft.world.level.block.RenderShape
import net.minecraft.world.level.block.SimpleWaterloggedBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

@Suppress("DEPRECATION", "OVERRIDE_DEPRECATION")
class StrawberryAnemoneBlock(settings: Properties) : BushBlock(settings), EntityBlock, SimpleWaterloggedBlock {
    override fun codec(): MapCodec<out BushBlock?> {
        TODO("Not yet implemented")
    }

    init {
        this.registerDefaultState(stateDefinition.any().setValue(WATERLOGGED, true))
    }

    override fun mayPlaceOn(floor: BlockState, world: BlockGetter, pos: BlockPos): Boolean {
        return !floor.getCollisionShape(world, pos).getFaceShape(Direction.UP).isEmpty || floor.isFaceSturdy(
            world,
            pos,
            Direction.UP
        )
    }

    override fun updateShape(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: LevelAccessor,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        if (state.getValue(WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world))
        }

        return if (!canSurvive(state, world, pos)) {
            Blocks.AIR.defaultBlockState()
        } else super.updateShape(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun getCollisionShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return COLLISION_SHAPE
    }

    override fun getShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return SHAPE
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        val fluidState = ctx.level.getFluidState(ctx.clickedPos)
        return if (fluidState.`is`(FluidTags.WATER)) defaultBlockState().setValue(
            WATERLOGGED,
            ctx.level.getFluidState(ctx.clickedPos).`is`(Fluids.WATER)
        ) else null
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(state)
    }

    override fun getRenderShape(state: BlockState): RenderShape {
        return RenderShape.ENTITYBLOCK_ANIMATED
    }

    override fun newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return StrawberryAnemoneBlockEntity(pos, state)
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(WATERLOGGED)
    }

    override fun isPathfindable(state: BlockState, type: PathComputationType): Boolean {
        return false
    }

    companion object {
        private val SHAPE = box(4.0, 0.0, 4.0, 12.0, 9.0, 12.0)
        private val COLLISION_SHAPE = box(4.0, 0.0, 4.0, 12.0, 8.0, 12.0)
    }
}
