package dev.hybridlabs.aquatic.client.render.block

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.wood.HybridAquaticPlatformBlocks
import dev.hybridlabs.aquatic.platform.ClientServices
import net.minecraft.client.renderer.RenderType

object HybridAquaticBlockRenderers {
    fun registerRenderShapes() {
        ClientServices.PLATFORM.registerBlockRenderers(
            RenderType.cutout(),
            HybridAquaticPlatformBlocks.DUNEGRASS.get(),
            HybridAquaticPlatformBlocks.TALL_DUNEGRASS.get(),
            HybridAquaticPlatformBlocks.CATTAIL.get(),
            HybridAquaticBlocks.GLOWSLIME_BLOCK.get(),

            HybridAquaticBlocks.SHORT_RED_ALGAE.get(),
            HybridAquaticBlocks.RED_ALGAE.get(),
            HybridAquaticBlocks.TALL_RED_ALGAE.get(),

            HybridAquaticBlocks.BULL_KELP.get(),
            HybridAquaticBlocks.BULL_KELP_PLANT.get(),

            HybridAquaticBlocks.HARP_SPONGE.get(),
            HybridAquaticBlocks.GLASS_SPONGE.get(),

            HybridAquaticBlocks.SARGASSUM.get(),
            HybridAquaticBlocks.SARGASSUM_PLANT.get(),
            HybridAquaticBlocks.FLOATING_SARGASSUM.get(),

            HybridAquaticBlocks.WATER_LETTUCE.get(),
            HybridAquaticBlocks.WATER_HYACINTH.get(),
            HybridAquaticBlocks.JUNGLE_LILY_PAD.get(),

            HybridAquaticBlocks.GLOWING_PLANKTON.get(),

            HybridAquaticBlocks.SEA_LETTUCE.get(),
            HybridAquaticBlocks.TALL_SEA_LETTUCE.get(),

            HybridAquaticBlocks.CRAB_POT.get(),
            HybridAquaticBlocks.GIANT_CLAM.get(),
            HybridAquaticBlocks.OYSTER.get(),
            HybridAquaticBlocks.TUBE_WORM.get(),

            HybridAquaticBlocks.LOPHELIA_CORAL.get(),
            HybridAquaticBlocks.LOPHELIA_CORAL_FAN.get(),
            HybridAquaticBlocks.LOPHELIA_CORAL_WALL_FAN.get(),
            HybridAquaticBlocks.DEAD_LOPHELIA_CORAL.get(),
            HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_FAN.get(),
            HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_WALL_FAN.get(),
            HybridAquaticBlocks.BLEACHED_LOPHELIA_CORAL.get(),
            HybridAquaticBlocks.BLEACHED_LOPHELIA_CORAL_FAN.get(),
            HybridAquaticBlocks.BLEACHED_LOPHELIA_CORAL_WALL_FAN.get(),

            HybridAquaticBlocks.LEAF_CORAL.get(),
            HybridAquaticBlocks.LEAF_CORAL_FAN.get(),
            HybridAquaticBlocks.LEAF_CORAL_WALL_FAN.get(),
            HybridAquaticBlocks.DEAD_LEAF_CORAL.get(),
            HybridAquaticBlocks.DEAD_LEAF_CORAL_FAN.get(),
            HybridAquaticBlocks.DEAD_LEAF_CORAL_WALL_FAN.get(),

            HybridAquaticBlocks.ROSE_CORAL.get(),
            HybridAquaticBlocks.ROSE_CORAL_FAN.get(),
            HybridAquaticBlocks.ROSE_CORAL_WALL_FAN.get(),
            HybridAquaticBlocks.DEAD_ROSE_CORAL.get(),
            HybridAquaticBlocks.DEAD_ROSE_CORAL_FAN.get(),
            HybridAquaticBlocks.DEAD_ROSE_CORAL_WALL_FAN.get(),
            HybridAquaticBlocks.BLEACHED_ROSE_CORAL.get(),
            HybridAquaticBlocks.BLEACHED_ROSE_CORAL_FAN.get(),
            HybridAquaticBlocks.BLEACHED_ROSE_CORAL_WALL_FAN.get(),

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

            HybridAquaticBlocks.BLEACHED_FIRE_CORAL.get(),
            HybridAquaticBlocks.BLEACHED_FIRE_CORAL_FAN.get(),
            HybridAquaticBlocks.BLEACHED_FIRE_CORAL_WALL_FAN.get(),

            HybridAquaticBlocks.BLEACHED_TUBE_CORAL.get(),
            HybridAquaticBlocks.BLEACHED_TUBE_CORAL_FAN.get(),
            HybridAquaticBlocks.BLEACHED_TUBE_CORAL_WALL_FAN.get(),

            HybridAquaticBlocks.BLEACHED_HORN_CORAL.get(),
            HybridAquaticBlocks.BLEACHED_HORN_CORAL_FAN.get(),
            HybridAquaticBlocks.BLEACHED_HORN_CORAL_WALL_FAN.get(),

            HybridAquaticBlocks.BLEACHED_BUBBLE_CORAL.get(),
            HybridAquaticBlocks.BLEACHED_BUBBLE_CORAL_FAN.get(),
            HybridAquaticBlocks.BLEACHED_BUBBLE_CORAL_WALL_FAN.get(),

            HybridAquaticBlocks.BLEACHED_BRAIN_CORAL.get(),
            HybridAquaticBlocks.BLEACHED_BRAIN_CORAL_FAN.get(),
            HybridAquaticBlocks.BLEACHED_BRAIN_CORAL_WALL_FAN.get(),

            HybridAquaticPlatformBlocks.DRIFTWOOD_DOOR.get(),
            HybridAquaticPlatformBlocks.DRIFTWOOD_TRAPDOOR.get(),

            HybridAquaticBlocks.GLOWSTICK.get(),
            HybridAquaticBlocks.WALL_GLOWSTICK.get(),
        )
    }
}