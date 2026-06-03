package dev.hybridlabs.aquatic.fluid

import dev.hybridlabs.aquatic.particle.HAParticleTypes
import dev.hybridlabs.aquatic.tag.HAFluidTags
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
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.material.FlowingFluid
import net.minecraft.world.level.material.Fluid
import net.minecraft.world.level.material.FluidState
import java.util.*

abstract class CommonBrineFluid : FlowingFluid() {

    override fun getFlowing(): Fluid {
        throw AssertionError("Override getFlowing() in forge/fabric")
    }

    override fun getSource(): Fluid {
        throw AssertionError("Override getSource() in forge/fabric")
    }

    override fun getBucket(): Item {
        throw AssertionError("Override getBucket() in forge/fabric")
    }

    public override fun animateTick(level: Level, pos: BlockPos, state: FluidState, random: RandomSource) {
        val blockpos = pos.above()
        if (level.getBlockState(blockpos).fluidState.`is`(FluidTags.WATER) && !level.getBlockState(blockpos).isSolidRender(level, blockpos)) {
            if (random.nextInt(100) == 0) {
                val d0 = pos.x.toDouble() + random.nextDouble()
                val d1 = pos.y.toDouble() + 1.0
                val d2 = pos.z.toDouble() + random.nextDouble()
                level.addParticle(HAParticleTypes.BRINE_BUBBLE.get(), d0, d1, d2, 0.0, 0.0, 0.0)
                level.playLocalSound(
                    d0,
                    d1,
                    d2,
                    SoundEvents.BUBBLE_COLUMN_BUBBLE_POP,
                    SoundSource.BLOCKS,
                    0.2f + random.nextFloat() * 0.2f,
                    0.9f + random.nextFloat() * 0.15f,
                    false
                )
            }
        }

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
        return true
    }

    override fun beforeDestroyingBlock(level: LevelAccessor, pos: BlockPos, state: BlockState) {
        val blockEntity = if (state.hasBlockEntity()) level.getBlockEntity(pos) else null
        Block.dropResources(state, level, pos, blockEntity)
    }

    public override fun getSlopeFindDistance(level: LevelReader): Int {
        return 4
    }

    public override fun createLegacyBlock(state: FluidState): BlockState {
        throw AssertionError("Override createLegacyBlock() in forge/fabric")
    }

    override fun isSame(fluid: Fluid): Boolean {
        return fluid === source || fluid === flowing
    }

    public override fun getDropOff(level: LevelReader): Int {
        return 1
    }

    override fun getTickDelay(level: LevelReader): Int {
        return 5
    }

    override fun canBeReplacedWith(
        fluidState: FluidState,
        blockReader: BlockGetter,
        pos: BlockPos,
        fluid: Fluid,
        direction: Direction,
    ): Boolean {
        return fluid.`is`(HAFluidTags.BRINE)
    }

    override fun getExplosionResistance(): Float {
        return 100.0f
    }

    override fun getPickupSound(): Optional<SoundEvent> {
        return Optional.of(SoundEvents.BUCKET_FILL)
    }
}