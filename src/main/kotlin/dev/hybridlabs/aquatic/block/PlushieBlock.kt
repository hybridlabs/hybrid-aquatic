package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks.addBlocks
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.SkullBlock
import net.minecraft.block.Waterloggable
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.Identifier
import net.minecraft.util.StringIdentifiable
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView

/**
 * Represents any Plushie block.
 */
class PlushieBlock(variant: Variant, val particleBlock: Block, settings: Settings) : SkullBlock(variant, settings), Waterloggable {
    init {
        defaultState = stateManager.defaultState.with(WATERLOGGED, false)
        BlockEntityType.SKULL.addBlocks(this)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(builder)
        builder.add(WATERLOGGED)
    }

    override fun getFluidState(state: BlockState): FluidState =
        if (state.get(WATERLOGGED)) Fluids.WATER.getStill(false) else super.getFluidState(state)

    override fun getStateForNeighborUpdate(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: WorldAccess,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world))
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean {
        val fluidState = world.getFluidState(pos)
        return fluidState.fluid == Fluids.EMPTY || fluidState.fluid == Fluids.WATER
    }

    override fun getPlacementState(context: ItemPlacementContext): BlockState? {
        val fluidState = context.world.getFluidState(context.blockPos)
        return super.getPlacementState(context)?.with(WATERLOGGED, fluidState.fluid == Fluids.WATER)
    }

    enum class Variant(val id: String) : SkullType, StringIdentifiable {
        BASKING_SHARK("basking_shark"),
        BULL_SHARK("bull_shark"),
        FRILLED_SHARK("frilled_shark"),
        GREAT_WHITE_SHARK("great_white_shark"),
        HAMMERHEAD_SHARK("hammerhead_shark"),
        THRESHER_SHARK("thresher_shark"),
        TIGER_SHARK("tiger_shark"),
        WHALE_SHARK("whale_shark");

        val textureLocation: Identifier by lazy { Identifier(HybridAquatic.MOD_ID, "textures/entity/block/plushie/${id}_plushie.png") }

        override fun asString(): String = id
    }

    companion object {
        val WATERLOGGED: BooleanProperty = Properties.WATERLOGGED
    }
}
