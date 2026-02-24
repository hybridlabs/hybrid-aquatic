package dev.hybridlabs.aquatic.world

import net.minecraft.core.BlockPos
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Explosion
import net.minecraft.world.level.ExplosionDamageCalculator
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.material.FluidState
import java.util.Optional

class UnderwaterExplosionDamageCalculator : ExplosionDamageCalculator() {
    override fun getBlockExplosionResistance(
        explosion: Explosion,
        reader: BlockGetter,
        pos: BlockPos,
        state: BlockState,
        fluid: FluidState,
    ): Optional<Float> {

        if (!fluid.isEmpty) {
            return Optional.of(state.block.explosionResistance)
        }

        return super.getBlockExplosionResistance(explosion, reader, pos, state, fluid)
    }
}