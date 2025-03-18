package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.block.*
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.pathing.NavigationType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.world.ServerWorld
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.DirectionProperty
import net.minecraft.state.property.EnumProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.ActionResult
import net.minecraft.util.BlockRotation
import net.minecraft.util.Hand
import net.minecraft.util.StringIdentifiable
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.random.Random
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.WorldView

@Suppress("OVERRIDE_DEPRECATION", "DEPRECATION")
class GiantClamBlock(
    private val emitsParticles: Boolean,
    settings: Settings
) : Block(settings), Waterloggable {

    private var pearlTimer: Int = 6000

    init {
        defaultState = stateManager.defaultState
            .with(STATE, GiantClamState.OPEN)
            .with(WATERLOGGED, true)
    }

    override fun canPathfindThrough(state: BlockState, world: BlockView, pos: BlockPos, type: NavigationType): Boolean {
        return false
    }

    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean {
        val supportingPos = pos.down()
        val supportingState = world.getBlockState(supportingPos)
        return supportingState.isSideSolidFullSquare(world, supportingPos, Direction.UP)
    }

    override fun scheduledTick(state: BlockState, world: ServerWorld, pos: BlockPos, random: Random?) {
        val waterlogged = state.get(WATERLOGGED)
        val currentState = state.get(STATE)

        if (!waterlogged && currentState != GiantClamState.DEAD) {
            world.setBlockState(pos, state.with(STATE, GiantClamState.DEAD), 3)
            return
        }

        if (currentState != GiantClamState.DEAD && pearlTimer > 0) {
            pearlTimer--

            val newState = if (pearlTimer > 0) GiantClamState.CLOSED else GiantClamState.OPEN
            if (newState != currentState) {
                world.setBlockState(pos, state.with(STATE, newState), 2)
            }

            if (pearlTimer > 0) {
                world.scheduleBlockTick(pos, this, 20)
            }
        }
    }

    override fun getCollisionShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape = COLLISION_SHAPE

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape = SHAPE

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        val waterlogged = ctx.world.getFluidState(ctx.blockPos).fluid == Fluids.WATER
        return defaultState
            .with(WATERLOGGED, waterlogged)
            .with(STATE, if (waterlogged) GiantClamState.CLOSED else GiantClamState.DEAD)
            .with(FACING, ctx.horizontalPlayerFacing.rotateYClockwise())
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.get(WATERLOGGED)) Fluids.WATER.getStill(false) else super.getFluidState(state)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(STATE, WATERLOGGED, FACING)
    }

    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        hand: Hand,
        hit: BlockHitResult
    ): ActionResult {
        if (!world.isClient) {
            val currentState = state.get(STATE)
            if (currentState == GiantClamState.OPEN) {
                pearlTimer = 6000
                world.setBlockState(pos, state.with(STATE, GiantClamState.CLOSED), 3)

                val randomValue = world.random.nextFloat()
                val itemToDrop = when {
                    randomValue < 0.70 -> ItemStack(HybridAquaticItems.PEARL)
                    randomValue < 0.95 -> ItemStack(HybridAquaticItems.BLACK_PEARL)
                    else -> ItemStack(Items.ENDER_PEARL)
                }

                dropStack(world, pos, itemToDrop)
            } else {
                return ActionResult.PASS
            }
        }
        return ActionResult.SUCCESS
    }

    override fun onSteppedOn(world: World, pos: BlockPos?, state: BlockState?, entity: Entity) {
        if (world.isClient || pos == null || state == null) return

        val currentState = state.get(STATE)
        if (currentState == GiantClamState.OPEN) {
            world.setBlockState(pos, state.with(STATE, GiantClamState.CLOSED), 3)
            pearlTimer = 6000

            if (!entity.bypassesSteppingEffects() && entity is LivingEntity) {
                entity.damage(world.damageSources.inWall(), 4.0f)
            }
        }

        super.onSteppedOn(world, pos, state, entity)
    }

    override fun randomDisplayTick(state: BlockState, world: World, pos: BlockPos, random: Random) {
        if (state.get(STATE) == GiantClamState.OPEN && emitsParticles && random.nextInt(5) == 0) {
            for (i in 0..random.nextInt(1)) {
                world.addParticle(
                    ParticleTypes.BUBBLE_COLUMN_UP,
                    pos.x + 0.5, pos.y + 0.5, pos.z + 0.5,
                    random.nextFloat() / 2.0, 0.0, random.nextFloat() / 2.0
                )
            }
        }
    }

    override fun rotate(state: BlockState, rotation: BlockRotation): BlockState {
        return state.with(FACING, rotation.rotate(state.get(FACING) as Direction)) as BlockState
    }

    companion object {
        val FACING: DirectionProperty = HorizontalFacingBlock.FACING
        val STATE: EnumProperty<GiantClamState> = EnumProperty.of(
            "state",
            GiantClamState::class.java,
            GiantClamState.OPEN,
            GiantClamState.CLOSED,
            GiantClamState.DEAD
        )

        val WATERLOGGED: BooleanProperty = Properties.WATERLOGGED
        private val SHAPE: VoxelShape = createCuboidShape(2.0, 0.0, 2.0, 14.0, 8.0, 14.0)
        private val COLLISION_SHAPE: VoxelShape = createCuboidShape(2.0, 0.0, 2.0, 14.0, 8.0, 14.0)
    }

    enum class GiantClamState : StringIdentifiable {
        OPEN, CLOSED, DEAD;

        override fun asString(): String {
            return name.lowercase()
        }
    }
}