package dev.hybridlabs.aquatic.block

import net.minecraft.block.*
import net.minecraft.item.FlintAndSteelItem
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.Properties.*
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockRenderView
import net.minecraft.world.BlockView
import net.minecraft.world.WorldAccess

@SuppressWarnings("DEPRECATION")
class FishTankBlock(settings: Settings?) : Block(settings) {
    // TODO: 7/14/2023 Convert this to a proper class to handle hitboxes
    companion object {
        // region Hitbox Chunks
        private val FLOOR: VoxelShape = createCuboidShape(0.0, 0.0, 0.0, 16.0, 1.0, 16.0)
        private val ROOF: VoxelShape = createCuboidShape(0.0, 0.0, 0.0, 0.0, 0.0, 0.0)

        private val WALL_EAST: VoxelShape = createCuboidShape(0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
        private val WALL_WEST: VoxelShape = createCuboidShape(0.0, 0.0, 0.0, 0.0, 0.0, 0.0)

        private val WALL_NORTH: VoxelShape = createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 1.0)
        private val WALL_SOUTH: VoxelShape = createCuboidShape(16.0, 0.0, 0.0, 16.0, 16.0, 1.0)
        // endregion
    }

    init {
        defaultState = stateManager.defaultState
            .with(NORTH, true)
            .with(SOUTH, true)
            .with(EAST, true)
            .with(WEST, true)
            .with(UP, false)
            .with(DOWN, true)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>?) {
        builder?.add(NORTH, SOUTH, EAST, WEST, UP, DOWN)
    }

    override fun getAppearance(state: BlockState?, renderView: BlockRenderView?, pos: BlockPos?, side: Direction?, sourceState: BlockState?, sourcePos: BlockPos?): BlockState {
        return Blocks.AMETHYST_BLOCK.defaultState
    }

//    override fun getStateForNeighborUpdate(state: BlockState?, direction: Direction?, neighborState: BlockState?, world: WorldAccess?, pos: BlockPos?, neighborPos: BlockPos?): BlockState {
//        val hasTankUp = world?.getBlockState(pos?.up())?.isOf(HybridAquaticBlocks.FISH_TANK)
//        val hasTankDown = world?.getBlockState(pos?.down())?.isOf(HybridAquaticBlocks.FISH_TANK)
//        val hasTankNorth = world?.getBlockState(pos?.north())?.isOf(HybridAquaticBlocks.FISH_TANK)
//        val hasTankSouth = world?.getBlockState(pos?.south())?.isOf(HybridAquaticBlocks.FISH_TANK)
//        val hasTankEast = world?.getBlockState(pos?.east())?.isOf(HybridAquaticBlocks.FISH_TANK)
//        val hasTankWest = world?.getBlockState(pos?.west())?.isOf(HybridAquaticBlocks.FISH_TANK)
//
//        state?.with(NORTH, hasTankNorth!!)
//
//        return state!!
//    }

    override fun getCollisionShape(state: BlockState?, world: BlockView?, pos: BlockPos?, context: ShapeContext?): VoxelShape {
        return getShapeFromState(state)
    }

    override fun getOutlineShape(state: BlockState?, world: BlockView?, pos: BlockPos?, context: ShapeContext?): VoxelShape {
        return getShapeFromState(state)
    }

    private fun getShapeFromState(state: BlockState?) : VoxelShape {
        val result = createCuboidShape(0.0, 0.0, 0.0, 0.16, 0.1, 0.16)
        if (state!!.get(NORTH)) {
            VoxelShapes.union(result, WALL_NORTH)
        }
        if (state.get(SOUTH)) {
            VoxelShapes.union(result, WALL_SOUTH)
        }
        if (state.get(EAST)) {
            VoxelShapes.union(result, WALL_EAST)
        }
        if (state.get(WEST)) {
            VoxelShapes.union(result, WALL_WEST)
        }
        if (state.get(UP)) {
            VoxelShapes.union(result, ROOF)
        }
        if (state.get(DOWN)) {
            VoxelShapes.union(result, FLOOR)
        }
        return result
    }

}