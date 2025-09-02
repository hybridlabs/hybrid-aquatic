package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks.addBlocks
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.StringRepresentable
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SimpleWaterloggedBlock
import net.minecraft.world.level.block.SkullBlock
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.Property
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids

/**
 * Represents any Plushie block.
 */
@Suppress("OVERRIDE_DEPRECATION", "DEPRECATION")
class PlushieBlock(variant: Variant, val particleBlock: Block, settings: Properties) :
    SkullBlock(variant, settings), SimpleWaterloggedBlock {
    init {
        this.registerDefaultState(
            stateDefinition.any().setValue(BlockStateProperties.WATERLOGGED, true).setValue(ROTATION, 0)
        )
        BlockEntityType.SKULL.addBlocks(this)
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block?, BlockState?>) {
        builder.add(WATERLOGGED)
        builder.add(*arrayOf<Property<*>>(ROTATION))
    }

    override fun getFluidState(state: BlockState): FluidState =
        if (state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(state)

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
        return super.updateShape(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun canSurvive(state: BlockState, world: LevelReader, pos: BlockPos): Boolean {
        val fluidState = world.getFluidState(pos)
        return fluidState.`is`(Fluids.EMPTY) || fluidState.`is`(Fluids.WATER)
    }


    override fun getStateForPlacement(context: BlockPlaceContext): BlockState? {
        val fluidState = context.level.getFluidState(context.clickedPos)
        return super.getStateForPlacement(context)?.setValue(WATERLOGGED, fluidState.`is`(Fluids.WATER))
    }

    enum class Variant(val id: String) : Type, StringRepresentable {
        BASKING_SHARK("basking_shark"),
        BULL_SHARK("bull_shark"),
        FRILLED_SHARK("frilled_shark"),
        GREAT_WHITE_SHARK("great_white_shark"),
        HAMMERHEAD_SHARK("hammerhead_shark"),
        THRESHER_SHARK("thresher_shark"),
        TIGER_SHARK("tiger_shark"),
        WHALE_SHARK("whale_shark");

        val textureLocation: ResourceLocation by lazy {
            ResourceLocation(
                Constants.MOD_ID,
                "textures/entity/block/plushie/${id}_plushie.png"
            )
        }

        override fun getSerializedName(): String = id
    }

    companion object {
        val WATERLOGGED: BooleanProperty = BlockStateProperties.WATERLOGGED
    }
}
