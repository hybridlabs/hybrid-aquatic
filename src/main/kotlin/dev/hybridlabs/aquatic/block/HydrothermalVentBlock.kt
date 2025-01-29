package dev.hybridlabs.aquatic.block

import com.mojang.serialization.MapCodec
import net.minecraft.block.AbstractPlantStemBlock
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.FluidFillable
import net.minecraft.block.ShapeContext
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.particle.ParticleTypes
import net.minecraft.registry.tag.FluidTags
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.random.Random
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView

class HydrothermalVentBlock(settings: Settings) :
    AbstractPlantStemBlock(settings, Direction.UP, SHAPE, true, 0.0),
    FluidFillable {
    override fun chooseStemState(state: BlockState): Boolean {
        return state.isOf(Blocks.WATER)
    }

    override fun getPlant(): Block {
        return HybridAquaticBlocks.HYDROTHERMAL_VENT_SHAFT
    }

    override fun isFertilizable(world: WorldView, pos: BlockPos, state: BlockState): Boolean {
        return false
    }

    @Deprecated("Deprecated in Java")
    override fun getCollisionShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return COLLISION_SHAPE
    }

    override fun canFillWithFluid(
        player: PlayerEntity?,
        world: BlockView,
        pos: BlockPos,
        state: BlockState,
        fluid: Fluid
    ): Boolean {
        return false
    }

    override fun tryFillWithFluid(
        world: WorldAccess,
        pos: BlockPos,
        state: BlockState,
        fluidState: FluidState
    ): Boolean {
        return false
    }


    override fun randomDisplayTick(state: BlockState?, world: World?, pos: BlockPos?, random: Random?) {
        super.randomDisplayTick(state, world, pos, random)
        if (world != null && pos != null) {
            spawnSmokeParticle(world, pos)
        }
    }

    private fun spawnSmokeParticle(world: World, pos: BlockPos) {
        val random = world.getRandom()
        world.addParticle(
            ParticleTypes.CAMPFIRE_SIGNAL_SMOKE,
            pos.x.toDouble() + 0.5 + random.nextDouble() / 4.0 * (if (random.nextBoolean()) 1 else -1).toDouble(),
            pos.y.toDouble() + 0.4,
            pos.z.toDouble() + 0.5 + random.nextDouble() / 4.0 * (if (random.nextBoolean()) 1 else -1).toDouble(),
            0.0,
            0.01,
            0.0
        )
    }

    override fun getGrowthLength(random: Random): Int {
        return 1
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        val fluidState = ctx.world.getFluidState(ctx.blockPos)
        return if (fluidState.isIn(FluidTags.WATER) && fluidState.level == 8) super.getPlacementState(ctx) else null
    }

    @Deprecated("Deprecated in Java", ReplaceWith("Fluids.WATER.getStill(false)", "net.minecraft.fluid.Fluids"))
    override fun getFluidState(state: BlockState): FluidState {
        return Fluids.WATER.getStill(false)
    }

    override fun getCodec(): MapCodec<out AbstractPlantStemBlock> {
        return CODEC
    }

    companion object {
        val CODEC: MapCodec<HydrothermalVentBlock> = createCodec(::HydrothermalVentBlock)
        private val SHAPE = createCuboidShape(4.0, 0.0, 4.0, 12.0, 6.0, 12.0)
        private val COLLISION_SHAPE = createCuboidShape(4.0, 0.0, 4.0, 12.0, 8.0, 12.0)
    }
}
