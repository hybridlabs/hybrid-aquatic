package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.block.entity.GiantClamBlockEntity
import dev.hybridlabs.aquatic.block.entity.HybridAquaticBlockEntityTypes
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.item.ItemStack
import net.minecraft.registry.tag.FluidTags
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.Properties.WATERLOGGED
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.WorldAccess

@Suppress("OVERRIDE_DEPRECATION")
class GiantClamBlock(settings: Settings) : PlantBlock(settings), BlockEntityProvider, Waterloggable {
    init {
        defaultState = stateManager.defaultState
            .with(WATERLOGGED, false)
            .with(CLAM_HAS_PEARL, true)
    }

    override fun canPlantOnTop(floor: BlockState, world: BlockView, pos: BlockPos): Boolean {
        return !floor.getCollisionShape(world, pos).getFace(Direction.UP).isEmpty || floor.isSideSolidFullSquare(world, pos, Direction.UP)
    }

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

        return if (!canPlaceAt(state, world, pos)) {
            Blocks.AIR.defaultState
        } else super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun getCollisionShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return COLLISION_SHAPE
    }

    override fun getOutlineShape(state: BlockState, world: BlockView, pos: BlockPos, context: ShapeContext): VoxelShape {
        return SHAPE
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        val fluidState = ctx.world.getFluidState(ctx.blockPos)
        return if (fluidState.isIn(FluidTags.WATER)) defaultState.with(WATERLOGGED, ctx.world.getFluidState(ctx.blockPos).isOf(Fluids.WATER)) else null
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.get(WATERLOGGED)) Fluids.WATER.getStill(false) else super.getFluidState(state)
    }

    override fun getRenderType(state: BlockState): BlockRenderType {
        return BlockRenderType.ENTITYBLOCK_ANIMATED
    }

    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return GiantClamBlockEntity(pos, state)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder
            .add(WATERLOGGED)
            .add(CLAM_HAS_PEARL)
    }

    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos?,
        player: PlayerEntity,
        hand: Hand?,
        hit: BlockHitResult?
    ): ActionResult? {
        if (hand == Hand.MAIN_HAND) {
            val blockEntity = world.getBlockEntity(pos)
            if (blockEntity is GiantClamBlockEntity && blockEntity.pearlCooldown == 0) {
                blockEntity.pearlCooldown = world.random.nextBetween(1200, 6000)

                dropStack(world, pos, ItemStack(HybridAquaticItems.PEARL, 1))
                world.playSound(
                    null,
                    pos,
                    SoundEvents.ENTITY_SHULKER_CLOSE,
                    SoundCategory.BLOCKS,
                    1.0f,
                    0.8f + world.random.nextFloat() * 0.4f
                )
            }
        }
        return super.onUse(state, world, pos, player, hand, hit)
    }

    override fun <T : BlockEntity> getTicker(
        world: World,
        state: BlockState,
        type: BlockEntityType<T>
    ): BlockEntityTicker<T>? {
        return if(world.isClient) {
            null
        } else {
            BlockWithEntity.checkType(type, HybridAquaticBlockEntityTypes.GIANT_CLAM, GiantClamBlockEntity::tick)
        }
    }

    companion object {
        val CLAM_HAS_PEARL: BooleanProperty = BooleanProperty.of("clam_has_pearl")
        private val SHAPE = createCuboidShape(2.0, 0.0, 2.0, 14.0, 8.0, 14.0)
        private val COLLISION_SHAPE = createCuboidShape(2.0, 0.0, 2.0, 14.0, 8.0, 14.0)
    }
}