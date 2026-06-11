package dev.hybridlabs.aquatic.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.BucketPickup
import net.minecraft.world.level.block.RenderShape
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import java.util.*

@Suppress("OVERRIDE_DEPRECATION", "DEPRECATION")
class DecorativeBubbleColumnBlock(settings: Properties): Block(settings), BucketPickup {
    init {
        this.registerDefaultState(stateDefinition.any())
    }

    override fun tick(state: BlockState, level: ServerLevel, pos: BlockPos, random: RandomSource) {
        updateColumn(level, pos, state, level.getBlockState(pos.below()))
    }

    override fun getFluidState(state: BlockState): FluidState {
        return Fluids.WATER.getSource(false)
    }

    override fun animateTick(state: BlockState, level: Level, pos: BlockPos, random: RandomSource) {
        val x = pos.x.toDouble()
        val y = pos.y.toDouble()
        val z = pos.z.toDouble()

        level.addAlwaysVisibleParticle(ParticleTypes.BUBBLE_COLUMN_UP, x + 0.5, y, z + 0.5, 0.0, 0.04, 0.0)
        level.addAlwaysVisibleParticle(ParticleTypes.BUBBLE_COLUMN_UP, x + random.nextDouble(), y + random.nextDouble(), z + random.nextDouble(), 0.0, 0.04, 0.0)

        if (random.nextInt(200) == 0) {
            level.playLocalSound(
                x, y, z,
                SoundEvents.BUBBLE_COLUMN_UPWARDS_AMBIENT,
                SoundSource.BLOCKS,
                0.2F + random.nextFloat() * 0.2F,
                0.9F + random.nextFloat() * 0.15F,
                false
            )
        }
    }

    override fun updateShape(
        state: BlockState,
        facing: Direction,
        facingState: BlockState,
        level: LevelAccessor,
        currentPos: BlockPos,
        facingPos: BlockPos
    ): BlockState {
        level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level))
        if (!state.canSurvive(level, currentPos) || facing == Direction.DOWN || facing == Direction.UP && !facingState.`is`(HABlocks.DECORATIVE_BUBBLE_COLUMN.get()) && canExistIn(facingState)) {
            level.scheduleTick(currentPos, this, CHECK_PERIOD)
        }

        return super.updateShape(state, facing, facingState, level, currentPos, facingPos)
    }

    override fun canSurvive(state: BlockState, level: LevelReader, pos: BlockPos): Boolean {
        val blockstate = level.getBlockState(pos.below())
        return blockstate.`is`(HABlocks.DECORATIVE_BUBBLE_COLUMN.get()) || blockstate.`is`(HABlocks.AERATED_SAND.get())
    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, context: CollisionContext): VoxelShape {
        return Shapes.empty()
    }

    override fun getRenderShape(state: BlockState): RenderShape {
        return RenderShape.INVISIBLE
    }

    override fun pickupBlock(p0: Player?, level: LevelAccessor, pos: BlockPos, state: BlockState): ItemStack {
        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11)
        return ItemStack(Items.WATER_BUCKET)
    }

    override fun getPickupSound(): Optional<SoundEvent?> {
        return Fluids.WATER.pickupSound
    }

    companion object {
        const val CHECK_PERIOD = 5

        fun updateColumn(level: LevelAccessor, origin: BlockPos, belowState: BlockState) {
            updateColumn(level, origin, level.getBlockState(origin), belowState)
        }

        fun updateColumn(level: LevelAccessor, origin: BlockPos, originState: BlockState, belowState: BlockState) {
            if (canExistIn(originState)) {
                val columnState = getColumnState(belowState)
                level.setBlock(origin, columnState, 2)
                val pos = origin.mutable().move(Direction.UP)

                while (canExistIn(level.getBlockState(pos))) {
                    if (!level.setBlock(pos, columnState, 2)) return
                    pos.move(Direction.UP)
                }
            }
        }

        fun canExistIn(state: BlockState): Boolean {
            return state.`is`(HABlocks.DECORATIVE_BUBBLE_COLUMN.get()) || state.`is`(Blocks.WATER) && state.fluidState.amount >= 8 && state.fluidState.isSource
        }

        fun getColumnState(belowState: BlockState): BlockState {
            return when {
                belowState.`is`(HABlocks.DECORATIVE_BUBBLE_COLUMN.get()) -> belowState
                belowState.`is`(HABlocks.AERATED_SAND.get()) -> HABlocks.DECORATIVE_BUBBLE_COLUMN.get().defaultBlockState()
                else -> Blocks.WATER.defaultBlockState()
            }
        }
    }
}