package dev.hybridlabs.aquatic.block.entity

import dev.hybridlabs.aquatic.block.ThermalVentBlock
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState

class ThermalVentBlockEntity(
    pos: BlockPos,
    state: BlockState
) : BlockEntity(HABlockEntityTypes.THERMAL_VENT.get(), pos, state) {

    companion object {

        fun particleTick(
            level: Level,
            pos: BlockPos,
            state: BlockState,
            blockEntity: ThermalVentBlockEntity
        ) {
            val random = level.random

            if (state.getValue(ThermalVentBlock.THICKNESS) == ThermalVentBlock.ThermalVentPosition.TIP) {

                if (random.nextFloat() < 0.3f) {
                    for (i in 0 until random.nextInt(1) + 1) {

                        ThermalVentBlock.makeParticles(
                            level,
                            pos
                        )
                    }
                }
            }
        }
    }
}