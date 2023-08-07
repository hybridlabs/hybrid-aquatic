@file:Suppress("OVERRIDE_DEPRECATION", "DEPRECATION")

package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity
import net.minecraft.block.Block
import net.minecraft.block.BlockRenderType
import net.minecraft.block.BlockState
import net.minecraft.block.BlockWithEntity
import net.minecraft.block.Blocks
import net.minecraft.block.ShapeContext
import net.minecraft.block.Waterloggable
import net.minecraft.block.entity.BlockEntity
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.Properties.WATERLOGGED
import net.minecraft.util.StringIdentifiable
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView

/**
 * Represents the Message in a Bottle block.
 * @see MessageInABottleBlockEntity
 */
class MessageInABottleBlock(settings: Settings) : BlockWithEntity(settings), Waterloggable {
    init {
        // add waterlogged to default state
        defaultState = defaultState.with(WATERLOGGED, false)
    }

    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean {
        // cannot place below water
        val fluidStateAbove = world.getFluidState(pos.up())
        if (fluidStateAbove.fluid != Fluids.EMPTY) {
            return false
        }

        // cannot stack
        val stateBelow = world.getBlockState(pos.down())
        if (stateBelow.block == this) {
            return false
        }

        // check valid placement
        val fluidState = world.getFluidState(pos)
        return fluidState.fluid == Fluids.WATER || sideCoversSmallSquare(world, pos.down(), Direction.UP)
    }

    override fun getPlacementState(context: ItemPlacementContext): BlockState? {
        // place in water as waterlogged
        val world = context.world
        val pos = context.blockPos
        val fluidState = world.getFluidState(pos)
        return super.getPlacementState(context)?.with(WATERLOGGED, fluidState.fluid == Fluids.WATER)
    }

    override fun getStateForNeighborUpdate(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: WorldAccess,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        // tick fluid when waterlogged
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world))
        }

        // update placement validity
        if (!canPlaceAt(state, world, pos)) {
            return Blocks.AIR.defaultState
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.get(WATERLOGGED)) Fluids.WATER.getStill(false) else super.getFluidState(state)
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return if (state.get(WATERLOGGED)) WATER_SHAPE else SHAPE
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(
            // append waterlogged
            builder.add(WATERLOGGED)
        )
    }

    override fun getRenderType(state: BlockState): BlockRenderType {
        return BlockRenderType.ENTITYBLOCK_ANIMATED
    }

    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return MessageInABottleBlockEntity(pos, state)
    }

    /**
     * Represents the variants of a Message in a Bottle.
     */
    enum class Variant(val id: String) : StringIdentifiable {
        /**
         * The default bottle variant.
         */
        BOTTLE("bottle"),

        /**
         * The jar variant.
         */
        JAR("jar"),

        /**
         * The longneck variant.
         */
        LONGNECK("longneck");

        override fun asString(): String {
            return id
        }

        companion object {
            private val BY_ID = values().associateBy(Variant::id)

            /**
             * Retrieves a variant based on [id].
             */
            fun byId(id: String): Variant {
                return BY_ID[id] ?: BOTTLE
            }
        }
    }

    companion object {
        /**
         * The default shape of a Message in a Bottle block.
         */
        val SHAPE: VoxelShape = Block.createCuboidShape(2.0, 0.0, 2.0, 13.0, 6.0, 14.0)

        /**
         * The shape of a Message in a Bottle block in water.
         */
        val WATER_SHAPE: VoxelShape = Block.createCuboidShape(1.0, 13.0, 1.0, 15.0, 16.0, 15.0)
    }
}
