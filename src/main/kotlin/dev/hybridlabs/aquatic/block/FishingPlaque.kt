package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.block.entity.FishingPlaqueBlockEntity
import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView

class FishingPlaque(settings: Settings?) : BlockWithEntity(settings) {

    init {
        defaultState = stateManager.defaultState
            .with(Properties.HORIZONTAL_FACING, Direction.NORTH)
    }

    override fun createBlockEntity(pos: BlockPos?, state: BlockState?): BlockEntity {
        return FishingPlaqueBlockEntity(pos, state)
    }

    override fun getRenderType(state: BlockState?): BlockRenderType {
        return BlockRenderType.MODEL
    }

    override fun getCollisionShape(
        state: BlockState?,
        world: BlockView?,
        pos: BlockPos?,
        context: ShapeContext?
    ): VoxelShape {
        return getDirectionalShape(state)
    }

    override fun getOutlineShape(
        state: BlockState?,
        world: BlockView?,
        pos: BlockPos?,
        context: ShapeContext?
    ): VoxelShape {
        return getDirectionalShape(state)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>?) {
        builder?.add(Properties.HORIZONTAL_FACING)
        AnemoneBlock
    }

    /*
    TODO: Fix this up, it will crash you because up and down are not acceptable inputs for this blockstate
     */
    override fun getPlacementState(ctx: ItemPlacementContext?): BlockState? {
        return defaultState.with(Properties.HORIZONTAL_FACING, ctx!!.playerLookDirection.opposite)
    }

    private fun getDirectionalShape(state: BlockState?) : VoxelShape {
        return when (state?.get(Properties.HORIZONTAL_FACING)) {
            Direction.NORTH -> SHAPE_NORTH
            Direction.SOUTH -> SHAPE_SOUTH
            Direction.WEST -> SHAPE_WEST
            Direction.EAST -> SHAPE_EAST
            else -> SHAPE_NORTH // That's right, fuck you. It defaults to north. btw luv you mystic.
                                // IDK when you'll read this but it'll probably be late because well...
                                // i have a tendency to drag my feet. But hey. Nice chat :)
        }
    }

    companion object {

        private val SHAPE_NORTH : VoxelShape = createCuboidShape(1.0,3.5, 15.0, 15.0, 12.5, 16.0)
        private val SHAPE_SOUTH : VoxelShape = createCuboidShape(1.0, 3.5, 0.0, 15.0, 12.5, 1.0)
        private val SHAPE_EAST : VoxelShape = createCuboidShape(0.0, 3.5, 1.0, 1.0, 12.5, 15.0)
        private val SHAPE_WEST : VoxelShape = createCuboidShape(15.0, 3.5, 1.0, 16.0, 12.5, 15.0)
    }
}