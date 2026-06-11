package dev.hybridlabs.aquatic.block.entity

import dev.hybridlabs.aquatic.block.GiantThermalVentBlock
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState

class GiantThermalVentBlockEntity(
    pos: BlockPos,
    state: BlockState,
) : BlockEntity(HABlockEntityTypes.GIANT_THERMAL_VENT.get(), pos, state) {

    companion object {
        fun particleTick(
            level: Level,
            pos: BlockPos,
            state: BlockState,
            blockEntity: GiantThermalVentBlockEntity,
        ) {
            val random = level.random

            if (state.getValue(GiantThermalVentBlock.THICKNESS) ==
                GiantThermalVentBlock.GiantThermalVentPosition.TIP
            ) {

                if (random.nextFloat() < 0.15f) {
                    GiantThermalVentBlock.makeParticles(level, pos)
                }
            }
        }
    }
}