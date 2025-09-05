package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.util.StringRepresentable
import net.minecraft.world.InteractionHand
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
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.Rotation
import net.minecraft.world.level.block.SimpleWaterloggedBlock
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
    SimpleWaterloggedBlock {

    private var pearlTimer: Int = 6000

    override fun isPathfindable(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        type: PathComputationType,
    ): Boolean {
        return false
    }

    override fun canSurvive(state: BlockState, world: LevelReader, pos: BlockPos): Boolean {
        val supportingPos = pos.below()
        val supportingState = world.getBlockState(supportingPos)
        return supportingState.isFaceSturdy(world, supportingPos, Direction.UP)
    }

    override fun tick(state: BlockState, world: ServerLevel, pos: BlockPos, random: RandomSource) {
        val waterlogged = state.getValue(WATERLOGGED)
        val currentState = state.getValue(STATE)

        if (!waterlogged && currentState != GiantClamState.DEAD) {
            world.setBlock(pos, state.setValue(STATE, GiantClamState.DEAD), 3)
            return
        }

        if (currentState != GiantClamState.DEAD && pearlTimer > 0) {
            pearlTimer--

            val newState = if (pearlTimer > 0) GiantClamState.CLOSED else GiantClamState.OPEN
            if (newState != currentState) {
                world.setBlock(pos, state.setValue(STATE, newState), 2)
            }

            if (pearlTimer > 0) {
                world.scheduleTick(pos, this, 20)
            }
        }
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

    override fun use(
        state: BlockState,
        world: Level,
        pos: BlockPos,
        player: Player,
        hand: InteractionHand,
        hit: BlockHitResult,
    ): InteractionResult {
        if (!world.isClientSide) {
            val currentState = state.getValue(STATE)
            if (currentState == GiantClamState.OPEN) {
                pearlTimer = 6000
                world.setBlock(pos, state.setValue(STATE, GiantClamState.CLOSED), 3)

                val randomValue = world.random.nextFloat()
                val itemToDrop = when {
                    randomValue < 0.70 -> ItemStack(HybridAquaticItems.PEARL.get())
                    randomValue < 0.95 -> ItemStack(HybridAquaticItems.BLACK_PEARL.get())
                    else -> ItemStack(Items.ENDER_PEARL)
                }

                popResource(world, pos, itemToDrop)
            } else {
                return InteractionResult.PASS
            }
        }
        return InteractionResult.SUCCESS
    }

    override fun stepOn(world: Level, pos: BlockPos, state: BlockState, entity: Entity) {
        if (world.isClientSide) return

        val currentState = state.getValue(STATE)
        if (currentState == GiantClamState.OPEN) {
            world.setBlock(pos, state.setValue(STATE, GiantClamState.CLOSED), 3)
            pearlTimer = 6000

            if (!entity.isSteppingCarefully && entity is LivingEntity) {
                entity.hurt(world.damageSources().inWall(), 4.0f)
            }
        }

        super.stepOn(world, pos, state, entity)
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
