package dev.hybridlabs.aquatic.block

import net.minecraft.block.BlockState
import net.minecraft.block.IceBlock
import net.minecraft.block.PlantBlock
import net.minecraft.block.ShapeContext
import net.minecraft.entity.Entity
import net.minecraft.entity.vehicle.BoatEntity
import net.minecraft.fluid.Fluids
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.World

open class JungleLilyPadBlock(settings: Settings?) : PlantBlock(settings) {
    override fun onEntityCollision(state: BlockState, world: World, pos: BlockPos, entity: Entity) {
        super.onEntityCollision(state, world, pos, entity)
        if (world is ServerWorld && entity is BoatEntity) {
            world.breakBlock(BlockPos(pos), true, entity)
        }
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return SHAPE
    }

    override fun canPlantOnTop(floor: BlockState, world: BlockView, pos: BlockPos): Boolean {
        val fluidState = world.getFluidState(pos)
        val fluidState2 = world.getFluidState(pos.up())
        return (fluidState.fluid === Fluids.WATER || floor.block is IceBlock) && fluidState2.fluid === Fluids.EMPTY
    }

    companion object {
        protected val SHAPE: VoxelShape = createCuboidShape(1.0, 0.0, 1.0, 15.0, 1.5, 15.0)
    }
}