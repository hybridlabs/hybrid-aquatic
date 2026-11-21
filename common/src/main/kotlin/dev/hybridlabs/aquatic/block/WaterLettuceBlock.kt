package dev.hybridlabs.aquatic.block

import net.minecraft.world.entity.InsideBlockEffectApplier

import net.minecraft.util.RandomSource

import com.mojang.serialization.MapCodec
import dev.hybridlabs.hapi.tag.HAPIEntityTags
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.Entity
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.ScheduledTickAccess
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.BushBlock
import net.minecraft.world.level.block.SimpleWaterloggedBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

class WaterLettuceBlock(settings: Properties) : BushBlock(settings), SimpleWaterloggedBlock {

    init {
        this.registerDefaultState(stateDefinition.any().setValue(WATERLOGGED, true))
    }

    override fun canSurvive(state: BlockState, world: LevelReader, pos: BlockPos): Boolean {
        val fluidStateAbove = world.getFluidState(pos.above())
        if (!fluidStateAbove.`is`( Fluids.EMPTY)) {
            return false
        }

        val stateBelow = world.getBlockState(pos.below())
        if (stateBelow.block == this) {
            return false
        }

        val fluidState = world.getFluidState(pos)
        return fluidState.`is`(Fluids.WATER) || canSupportCenter(world, pos.below(), Direction.UP)
    }

    override fun entityInside(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        entity: Entity,
        applier: InsideBlockEffectApplier,
        intersects: Boolean
    ) {
        super.entityInside(state, level, pos, entity, applier, intersects)
        if (level is ServerLevel && entity.type.builtInRegistryHolder().`is`(HAPIEntityTags.BOATS)) {
            level.destroyBlock(BlockPos(pos), true, entity)
        }
    }

    override fun getStateForPlacement(context: BlockPlaceContext): BlockState? {
        val world = context.level
        val pos = context.clickedPos
        val fluidState = world.getFluidState(pos)
        return if (fluidState.`is`(Fluids.WATER)) {
            super.getStateForPlacement(context)?.setValue(WATERLOGGED, true)
        } else {
            null
        }
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
        if (state.getValue(WATERLOGGED)) {
            scheduledTickAccess.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world))
        }

        if (!canSurvive(state, world, pos)) {
            return Blocks.AIR.defaultBlockState()
        }

        return super.updateShape(state, world, scheduledTickAccess, pos, direction, neighborPos, neighborState, random)
    }

    override fun getShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext,
    ): VoxelShape {
        return SHAPE
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(state)
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(WATERLOGGED)
    }

    override fun codec(): MapCodec<BushBlock> {
        return CODEC as MapCodec<BushBlock>
    }

    companion object {
        val CODEC: MapCodec<WaterLettuceBlock> = simpleCodec(::WaterLettuceBlock)
        private val SHAPE: VoxelShape = box(3.0, 14.0, 3.0, 13.0, 16.0, 13.0)
    }
}
