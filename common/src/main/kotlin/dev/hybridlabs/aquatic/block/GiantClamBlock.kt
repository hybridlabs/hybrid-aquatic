package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.block.entity.GiantClamBlockEntity
import dev.hybridlabs.aquatic.block.entity.HABlockEntityTypes
import dev.hybridlabs.aquatic.item.HAItems
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.util.RandomSource
import net.minecraft.util.StringRepresentable
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.BaseEntityBlock.createTickerHelper
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraft.world.level.block.state.properties.EnumProperty
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

@Suppress("OVERRIDE_DEPRECATION", "DEPRECATION")
class GiantClamBlock(private val emitsParticles: Boolean, settings: Properties) : Block(settings),
    EntityBlock, SimpleWaterloggedBlock {

    override fun isPathfindable(state: BlockState, type: PathComputationType): Boolean {
        return false
    }

    override fun setPlacedBy(
        world: Level,
        pos: BlockPos,
        state: BlockState,
        placer: LivingEntity?,
        stack: ItemStack
    ) {
        val giantClam = world.getBlockEntity(pos) as? GiantClamBlockEntity ?: return

        giantClam.pearlTimer = GiantClamBlockEntity.PEARL_TIMER
    }

    override fun newBlockEntity(blockPos: BlockPos, blockState: BlockState): BlockEntity {
        return GiantClamBlockEntity(blockPos, blockState)
    }

    override fun canSurvive(state: BlockState, world: LevelReader, pos: BlockPos): Boolean {
        val supportingPos = pos.below()
        val supportingState = world.getBlockState(supportingPos)
        return supportingState.isFaceSturdy(world, supportingPos, Direction.UP)
    }

    override fun getCollisionShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext,
    ): VoxelShape = COLLISION_SHAPE

    override fun getShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext,
    ): VoxelShape = SHAPE

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        val waterlogged = ctx.level.getFluidState(ctx.clickedPos).`is`(Fluids.WATER)
        return defaultBlockState()
            .setValue(WATERLOGGED, waterlogged)
            .setValue(STATE, if (waterlogged) GiantClamState.CLOSED else GiantClamState.DEAD)
            .setValue(FACING, ctx.horizontalDirection.clockWise)
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(state)
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(STATE, WATERLOGGED, FACING)
    }

    override fun useWithoutItem(
        state: BlockState,
        world: Level,
        pos: BlockPos,
        player: Player,
        hit: BlockHitResult
    ): InteractionResult {

        if (world.isClientSide) return InteractionResult.SUCCESS

        val be = world.getBlockEntity(pos) as? GiantClamBlockEntity
            ?: return InteractionResult.PASS

        if (state.getValue(STATE) == GiantClamState.OPEN) {

            val randomValue = world.random.nextFloat()
            val itemToDrop = when {
                randomValue < 0.70 -> ItemStack(HAItems.PEARL.get())
                randomValue < 0.95 -> ItemStack(HAItems.BLACK_PEARL.get())
                else -> ItemStack(Items.ENDER_PEARL)
            }

            popResource(world, pos, itemToDrop)

            be.closeAndStartCooldown()
            return InteractionResult.SUCCESS
        }

        return InteractionResult.PASS
    }

    override fun stepOn(world: Level, pos: BlockPos, state: BlockState, entity: Entity) {
        if (world.isClientSide) return

        val be = world.getBlockEntity(pos) as? GiantClamBlockEntity ?: return

        if (state.getValue(STATE) == GiantClamState.OPEN) {
            be.closeAndStartCooldown()

            if (!entity.isSteppingCarefully && entity is LivingEntity) {
                entity.hurt(world.damageSources().inWall(), 4.0f)
            }
        }

        super.stepOn(world, pos, state, entity)
    }

    override fun <T : BlockEntity?> getTicker(
        level: Level,
        state: BlockState,
        blockEntityType: BlockEntityType<T>,
    ): BlockEntityTicker<T>? {
        return createTickerHelper(
            blockEntityType,
            HABlockEntityTypes.GIANT_CLAM.get(),
            GiantClamBlockEntity::tick
        )
    }

    override fun animateTick(state: BlockState, world: Level, pos: BlockPos, random: RandomSource) {
        if (state.getValue(STATE) == GiantClamState.OPEN && emitsParticles && random.nextInt(5) == 0) {
            for (i in 0..random.nextInt(1)) {
                world.addParticle(
                    ParticleTypes.BUBBLE_COLUMN_UP,
                    pos.x + 0.5, pos.y + 0.5, pos.z + 0.5,
                    random.nextFloat() / 2.0, 0.0, random.nextFloat() / 2.0
                )
            }
        }
    }

    override fun rotate(state: BlockState, rotation: Rotation): BlockState {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING) as Direction)) as BlockState
    }

    companion object {
        val FACING: DirectionProperty = HorizontalDirectionalBlock.FACING
        val STATE: EnumProperty<GiantClamState> = EnumProperty.create(
            "state",
            GiantClamState::class.java,
            GiantClamState.OPEN,
            GiantClamState.CLOSED,
            GiantClamState.DEAD
        )

        val WATERLOGGED: BooleanProperty = BlockStateProperties.WATERLOGGED
        private val SHAPE: VoxelShape = box(2.0, 0.0, 2.0, 14.0, 8.0, 14.0)
        private val COLLISION_SHAPE: VoxelShape = box(2.0, 0.0, 2.0, 14.0, 8.0, 14.0)
    }

    enum class GiantClamState : StringRepresentable {
        OPEN, CLOSED, DEAD;

        override fun getSerializedName(): String {
            return name.lowercase()
        }
    }

    init {
        this.registerDefaultState(
            stateDefinition.any().setValue(BlockStateProperties.WATERLOGGED, true)
                .setValue(STATE, GiantClamState.OPEN)
        )
    }
}