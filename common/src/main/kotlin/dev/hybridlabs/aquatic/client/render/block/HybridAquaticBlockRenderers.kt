package dev.hybridlabs.aquatic.client.render.block

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.platform.ClientServices
import net.minecraft.client.renderer.RenderType

object HybridAquaticBlockRenderers {
    fun registerRenderShapes() {
        ClientServices.PLATFORM.registerBlockRenderers(
            RenderType.cutout(),
            HybridAquaticBlocks.RED_ALGAE.get(),
            HybridAquaticBlocks.TALL_RED_ALGAE.get(),

            HybridAquaticBlocks.BULL_KELP.get(),
            HybridAquaticBlocks.BULL_KELP_PLANT.get(),

            HybridAquaticBlocks.SARGASSUM.get(),
            HybridAquaticBlocks.SARGASSUM_PLANT.get(),
            HybridAquaticBlocks.FLOATING_SARGASSUM.get(),

            HybridAquaticBlocks.WATER_LETTUCE.get(),
            HybridAquaticBlocks.JUNGLE_LILY_PAD.get(),

            HybridAquaticBlocks.GLOWING_PLANKTON.get(),

            HybridAquaticBlocks.SEA_LETTUCE.get(),
            HybridAquaticBlocks.TALL_SEA_LETTUCE.get(),

            HybridAquaticBlocks.CRAB_POT.get(),
            HybridAquaticBlocks.GIANT_CLAM.get(),
            HybridAquaticBlocks.TUBE_WORM.get(),

            HybridAquaticBlocks.LOPHELIA_CORAL.get(),
            HybridAquaticBlocks.LOPHELIA_CORAL_FAN.get(),
            HybridAquaticBlocks.LOPHELIA_CORAL_WALL_FAN.get(),
            HybridAquaticBlocks.DEAD_LOPHELIA_CORAL.get(),
            HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_FAN.get(),
            HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_WALL_FAN.get(),

            HybridAquaticBlocks.SUN_CORAL.get(),
            HybridAquaticBlocks.SUN_CORAL_FAN.get(),
            HybridAquaticBlocks.SUN_CORAL_WALL_FAN.get(),
            HybridAquaticBlocks.DEAD_SUN_CORAL.get(),
            HybridAquaticBlocks.DEAD_SUN_CORAL_FAN.get(),
            HybridAquaticBlocks.DEAD_SUN_CORAL_WALL_FAN.get(),

            HybridAquaticBlocks.BUTTON_CORAL.get(),
            HybridAquaticBlocks.BUTTON_CORAL_FAN.get(),
            HybridAquaticBlocks.BUTTON_CORAL_WALL_FAN.get(),
            HybridAquaticBlocks.DEAD_BUTTON_CORAL.get(),
            HybridAquaticBlocks.DEAD_BUTTON_CORAL_FAN.get(),
            HybridAquaticBlocks.DEAD_BUTTON_CORAL_WALL_FAN.get(),

            HybridAquaticBlocks.THORN_CORAL.get(),
            HybridAquaticBlocks.THORN_CORAL_FAN.get(),
            HybridAquaticBlocks.THORN_CORAL_WALL_FAN.get(),
            HybridAquaticBlocks.DEAD_THORN_CORAL.get(),
            HybridAquaticBlocks.DEAD_THORN_CORAL_FAN.get(),
            HybridAquaticBlocks.DEAD_THORN_CORAL_WALL_FAN.get(),

            HybridAquaticBlocks.DRIFTWOOD_DOOR.get(),
            HybridAquaticBlocks.DRIFTWOOD_TRAPDOOR.get(),
            HybridAquaticBlocks.GLOWSTICK.get(),
            HybridAquaticBlocks.WALL_GLOWSTICK.get(),
        )
    }
}