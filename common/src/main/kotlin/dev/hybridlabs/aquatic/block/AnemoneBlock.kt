package dev.hybridlabs.aquatic.block

import com.mojang.serialization.MapCodec
import dev.hybridlabs.aquatic.block.entity.AnemoneBlockEntity
import dev.hybridlabs.aquatic.entity.fish.ClownfishEntity
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.tags.FluidTags
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.GameRules
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

@Suppress("DEPRECATION", "OVERRIDE_DEPRECATION")
class AnemoneBlock(settings: Properties) : BushBlock(settings), EntityBlock, SimpleWaterloggedBlock {
    init {
        this.registerDefaultState(stateDefinition.any().setValue(WATERLOGGED, true))
    }

    override fun entityInside(state: BlockState, level: Level, pos: BlockPos, entity: Entity) {
        if (entity is LivingEntity) {
            if (entity is ClownfishEntity) {
                if (!level.isClientSide) {
                    tryHideClownfish(entity, level, pos)
                }
            }
        }
    }

    private fun tryHideClownfish(entity: ClownfishEntity, world: LevelAccessor, pos: BlockPos) {
        if (entity.isBaby || !entity.navigation.isDone) {
            return
        }

        val blockEntity = world.getBlockEntity(pos)
        if (blockEntity is AnemoneBlockEntity) {
            if (blockEntity.hideClownfish(entity)) {
                entity.discard()
            }
        }
    }

    override fun playerWillDestroy(world: Level, pos: BlockPos, state: BlockState, player: Player): BlockState {
        if (!world.isClientSide && player.isCreative && world.gameRules.getBoolean(GameRules.RULE_DOBLOCKDROPS)) {
            val blockEntity = world.getBlockEntity(pos)
            if (blockEntity is AnemoneBlockEntity) {
                blockEntity.emergencyReleaseHiddenClownfish()
            }
        }

        return super.playerWillDestroy(world, pos, state, player)
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

    override fun <T : BlockEntity?> getTicker(
        level: Level,
        state: BlockState,
        type: BlockEntityType<T>
    ): BlockEntityTicker<T>? {
        return super.getTicker(level, state, type)
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
        return if (state.hasProperty(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(state)
    }

    override fun getRenderShape(state: BlockState): RenderShape {
        return RenderShape.ENTITYBLOCK_ANIMATED
    }

    override fun newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return AnemoneBlockEntity(pos, state)
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(WATERLOGGED)
    }

    override fun isPathfindable(state: BlockState, type: PathComputationType): Boolean {
        return false
    }

    override fun codec(): MapCodec<out BushBlock> {
        return CODEC
    }

    companion object {
        val CODEC: MapCodec<AnemoneBlock> = simpleCodec(::AnemoneBlock)
        private val SHAPE = box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0)
        private val COLLISION_SHAPE = box(1.0, 0.0, 1.0, 15.0, 8.0, 15.0)
    }
}
