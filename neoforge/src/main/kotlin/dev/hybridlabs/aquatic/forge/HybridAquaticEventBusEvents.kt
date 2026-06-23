package dev.hybridlabs.aquatic.forge

import dev.hybridlabs.aquatic.block.HABlocks
import net.minecraft.core.BlockPos
import net.minecraft.tags.FluidTags
import net.neoforged.neoforge.common.NeoForge.EVENT_BUS
import net.neoforged.neoforge.event.entity.living.LivingBreatheEvent

object HybridAquaticEventBusEvents {
    init {
        EVENT_BUS.addListener(::makeBlocksBreatheable)
    }

    private fun makeBlocksBreatheable(event: LivingBreatheEvent) {
        val entity = event.entity
        val world = entity.level()

        if(entity.isEyeInFluid(FluidTags.WATER) &&
            world.getBlockState(BlockPos.containing(entity.x, entity.eyeY, entity.z))
                .`is`(HABlocks.DECORATIVE_BUBBLE_COLUMN.get())) {
            event.setCanBreathe(true)
        }
    }
}