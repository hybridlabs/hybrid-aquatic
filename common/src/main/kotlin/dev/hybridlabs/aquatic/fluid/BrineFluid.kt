package dev.hybridlabs.aquatic.fluid

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.tags.FluidTags
import net.minecraft.util.RandomSource
import net.minecraft.world.item.Item
import net.minecraft.world.level.*
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.LiquidBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.material.FlowingFluid
import net.minecraft.world.level.material.Fluid
import net.minecraft.world.level.material.FluidState
import java.util.*

@Suppress("DEPRECATION")
abstract class BrineFluid : FlowingFluid() {
    override fun getFlowing(): Fluid {
        return HybridAquaticFluids.FLOWING_BRINE.get()
    }

    override fun getSource(): Fluid {
        return HybridAquaticFluids.BRINE.get()
    }

    override fun getBucket(): Item {
        return HybridAquaticItems.BRINE_BUCKET.get()
    }

    public override fun animateTick(level: Level, pos: BlockPos, state: FluidState, random: RandomSource) {
        if (!state.isSource && !state.getValue(FALLING)) {
            if (random.nextInt(64) == 0) {
                level.playLocalSound(
                    pos.x.toDouble() + 0.5,
                    pos.y.toDouble() + 0.5,
                    pos.z.toDouble() + 0.5,
                    SoundEvents.WATER_AMBIENT,
                    SoundSource.BLOCKS,
                    random.nextFloat() * 0.25f + 0.75f,
                    random.nextFloat() + 0.5f,
                    false
                )
            }
        } else if (random.nextInt(10) == 0) {
            level.addParticle(
                ParticleTypes.UNDERWATER,
                pos.x.toDouble() + random.nextDouble(),
                pos.y.toDouble() + random.nextDouble(),
                pos.z.toDouble() + random.nextDouble(),
                0.0,
                0.0,
                0.0
            )
        }
    }

    public override fun getDripParticle(): ParticleOptions? {
        return ParticleTypes.DRIPPING_WATER
    }

    override fun canConvertToSource(level: Level): Boolean {
        return false
    }

    override fun beforeDestroyingBlock(level: LevelAccessor, pos: BlockPos, state: BlockState) {
        val blockEntity = if (state.hasBlockEntity()) level.getBlockEntity(pos) else null
        Block.dropResources(state, level, pos, blockEntity)
    }

    public override fun getSlopeFindDistance(level: LevelReader): Int {
        return 4
    }

    public override fun createLegacyBlock(state: FluidState): BlockState {
        return HybridAquaticBlocks.BRINE.get().defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(state))
    }

    override fun isSame(fluid: Fluid): Boolean {
        return fluid == HybridAquaticFluids.BRINE || fluid == HybridAquaticFluids.FLOWING_BRINE
    }

    public override fun getDropOff(level: LevelReader): Int {
        return 1
    }

    override fun getTickDelay(level: LevelReader): Int {
        return 5
    }

    override fun getAmount(state: FluidState): Int {
        return 8
    }

    override fun isSource(state: FluidState): Boolean {
        return false
    }

    public override fun canBeReplacedWith(
        fluidState: FluidState,
        blockReader: BlockGetter,
        pos: BlockPos,
        fluid: Fluid,
        direction: Direction,
    ): Boolean {
        return direction == Direction.DOWN && !fluid.`is`(FluidTags.WATER)
    }

    override fun getExplosionResistance(): Float {
        return 100.0f
    }

    override fun getPickupSound(): Optional<SoundEvent> {
        return Optional.of(SoundEvents.BUCKET_FILL)
    }

    class Flowing : BrineFluid() {
        override fun createFluidStateDefinition(builder: StateDefinition.Builder<Fluid?, FluidState?>) {
            super.createFluidStateDefinition(builder)
            builder.add(LEVEL)
        }

        override fun getAmount(state: FluidState): Int {
            return state.getValue(LEVEL)
        }

        override fun isSource(state: FluidState): Boolean {
            return false
        }
    }

    class Source : BrineFluid() {
        override fun getAmount(state: FluidState): Int {
            return 8
        }

        override fun isSource(state: FluidState): Boolean {
            return true
        }
    }
}