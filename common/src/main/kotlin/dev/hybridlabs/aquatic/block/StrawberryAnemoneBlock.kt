package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.block.entity.StrawberryAnemoneBlockEntity
import dev.hybridlabs.aquatic.entity.fish.ClownfishEntity
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.tags.FluidTags
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.*
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
    init {
        this.registerDefaultState(
            stateDefinition.any()
                .setValue(WATERLOGGED, true)
        )
    }

    override fun entityInside(state: BlockState, world: Level, pos: BlockPos, entity: Entity) {
        if (entity is LivingEntity && entity !is ClownfishEntity) {
            entity.addEffect(MobEffectInstance(MobEffects.POISON, 3 * 20, 0))
        }
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

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block?, BlockState?>) {
        builder.add(WATERLOGGED)
    }

    override fun isPathfindable(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        type: PathComputationType
    ): Boolean {
        return false
    }

    companion object {
        private val SHAPE = box(4.0, 0.0, 4.0, 12.0, 9.0, 12.0)
        private val COLLISION_SHAPE = box(4.0, 0.0, 4.0, 12.0, 8.0, 12.0)
    }
}
