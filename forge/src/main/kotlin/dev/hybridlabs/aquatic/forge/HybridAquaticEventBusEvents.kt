package dev.hybridlabs.aquatic.forge

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import net.minecraft.core.BlockPos
import net.minecraft.tags.FluidTags
import net.minecraftforge.common.MinecraftForge.EVENT_BUS
import net.minecraftforge.event.entity.living.LivingBreatheEvent

object HybridAquaticEventBusEvents {
    init {
        EVENT_BUS.addListener(::makeBlocksBreatheable)
    }

    private fun makeBlocksBreatheable(event: LivingBreatheEvent) {
        val entity = event.entity
        val world = entity.level()

        if(entity.isEyeInFluid(FluidTags.WATER) &&
            world.getBlockState(BlockPos.containing(entity.x, entity.eyeY, entity.z))
                .`is`(HybridAquaticBlocks.DECORATIVE_BUBBLE_COLUMN.get())) {
            event.setCanBreathe(true)
            event.setCanRefillAir(true)
        }
    }
}