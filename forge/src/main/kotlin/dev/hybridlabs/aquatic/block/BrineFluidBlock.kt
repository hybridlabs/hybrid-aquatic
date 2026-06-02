package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.particle.HAParticleTypes
import net.minecraft.core.BlockPos
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.tags.FluidTags
import net.minecraft.util.RandomSource
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.LiquidBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.material.FlowingFluid
import java.util.*

class BrineFluidBlock(fluid: FlowingFluid, properties: Properties) : LiquidBlock(fluid, properties) {

    override fun animateTick(state: BlockState, level: Level, pos: BlockPos, random: RandomSource) {
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

        if (!state.fluidState.isSource && !state.getValue(FlowingFluid.FALLING)) {
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

    override fun getExplosionResistance(): Float {
        return 100.0f
    }

    override fun getPickupSound(): Optional<SoundEvent> {
        return Optional.of(SoundEvents.BUCKET_FILL)
    }
}