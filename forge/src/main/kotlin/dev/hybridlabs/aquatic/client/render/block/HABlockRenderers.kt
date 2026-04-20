package dev.hybridlabs.aquatic.client.render.block

import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.block.wood.HAPlatformBlocks
import dev.hybridlabs.aquatic.platform.ClientServices
import net.minecraft.client.renderer.RenderType

object HABlockRenderers {
    fun registerRenderShapes() {
        ClientServices.PLATFORM.registerBlockRenderers(
            RenderType.cutout(),
            HAPlatformBlocks.DUNEGRASS.get(),
            HAPlatformBlocks.TALL_DUNEGRASS.get(),
            HAPlatformBlocks.CATTAIL.get(),
            HABlocks.GLOWSLIME_BLOCK.get(),

            HABlocks.SHORT_RED_ALGAE.get(),
            HABlocks.RED_ALGAE.get(),
            HABlocks.TALL_RED_ALGAE.get(),

            HABlocks.BULL_KELP.get(),
            HABlocks.BULL_KELP_PLANT.get(),

            HABlocks.HARP_SPONGE.get(),
            HABlocks.GLASS_SPONGE.get(),

            HABlocks.SARGASSUM.get(),
            HABlocks.SARGASSUM_PLANT.get(),
            HABlocks.FLOATING_SARGASSUM.get(),

            HABlocks.WATER_LETTUCE.get(),
            HABlocks.WATER_HYACINTH.get(),
            HABlocks.JUNGLE_LILY_PAD.get(),

            HABlocks.GLOWING_PLANKTON.get(),

            HABlocks.BONE_WORMS.get(),
            HABlocks.CLAMS.get(),
            HABlocks.SEA_LETTUCE.get(),
            HABlocks.TALL_SEA_LETTUCE.get(),

            HABlocks.CRAB_POT.get(),
            HABlocks.GIANT_CLAM.get(),
            HABlocks.OYSTER.get(),
            HABlocks.TUBE_WORM.get(),

            HABlocks.BLEACHED_FIRE_CORAL.get(),
            HABlocks.BLEACHED_FIRE_CORAL_FAN.get(),
            HABlocks.BLEACHED_FIRE_CORAL_WALL_FAN.get(),

            HABlocks.BLEACHED_TUBE_CORAL.get(),
            HABlocks.BLEACHED_TUBE_CORAL_FAN.get(),
            HABlocks.BLEACHED_TUBE_CORAL_WALL_FAN.get(),

            HABlocks.BLEACHED_HORN_CORAL.get(),
            HABlocks.BLEACHED_HORN_CORAL_FAN.get(),
            HABlocks.BLEACHED_HORN_CORAL_WALL_FAN.get(),

            HABlocks.BLEACHED_BUBBLE_CORAL.get(),
            HABlocks.BLEACHED_BUBBLE_CORAL_FAN.get(),
            HABlocks.BLEACHED_BUBBLE_CORAL_WALL_FAN.get(),

            HABlocks.BLEACHED_BRAIN_CORAL.get(),
            HABlocks.BLEACHED_BRAIN_CORAL_FAN.get(),
            HABlocks.BLEACHED_BRAIN_CORAL_WALL_FAN.get(),

            HABlocks.BLEACHED_LEAF_CORAL.get(),
            HABlocks.BLEACHED_LEAF_CORAL_FAN.get(),
            HABlocks.BLEACHED_LEAF_CORAL_WALL_FAN.get(),

            HABlocks.BLEACHED_ROSE_CORAL.get(),
            HABlocks.BLEACHED_ROSE_CORAL_FAN.get(),
            HABlocks.BLEACHED_ROSE_CORAL_WALL_FAN.get(),

            HABlocks.BLEACHED_SUN_CORAL.get(),
            HABlocks.BLEACHED_SUN_CORAL_FAN.get(),
            HABlocks.BLEACHED_SUN_CORAL_WALL_FAN.get(),

            HABlocks.BLEACHED_LOPHELIA_CORAL.get(),
            HABlocks.BLEACHED_LOPHELIA_CORAL_FAN.get(),
            HABlocks.BLEACHED_LOPHELIA_CORAL_WALL_FAN.get(),

            HABlocks.BLEACHED_THORN_CORAL.get(),
            HABlocks.BLEACHED_THORN_CORAL_FAN.get(),
            HABlocks.BLEACHED_THORN_CORAL_WALL_FAN.get(),

            HABlocks.BLEACHED_BUTTON_CORAL.get(),
            HABlocks.BLEACHED_BUTTON_CORAL_FAN.get(),
            HABlocks.BLEACHED_BUTTON_CORAL_WALL_FAN.get(),

            HABlocks.DEAD_LEAF_CORAL.get(),
            HABlocks.DEAD_LEAF_CORAL_FAN.get(),
            HABlocks.DEAD_LEAF_CORAL_WALL_FAN.get(),

            HABlocks.DEAD_ROSE_CORAL.get(),
            HABlocks.DEAD_ROSE_CORAL_FAN.get(),
            HABlocks.DEAD_ROSE_CORAL_WALL_FAN.get(),

            HABlocks.DEAD_SUN_CORAL.get(),
            HABlocks.DEAD_SUN_CORAL_FAN.get(),
            HABlocks.DEAD_SUN_CORAL_WALL_FAN.get(),

            HABlocks.DEAD_LOPHELIA_CORAL.get(),
            HABlocks.DEAD_LOPHELIA_CORAL_FAN.get(),
            HABlocks.DEAD_LOPHELIA_CORAL_WALL_FAN.get(),

            HABlocks.DEAD_THORN_CORAL.get(),
            HABlocks.DEAD_THORN_CORAL_FAN.get(),
            HABlocks.DEAD_THORN_CORAL_WALL_FAN.get(),

            HABlocks.DEAD_BUTTON_CORAL.get(),
            HABlocks.DEAD_BUTTON_CORAL_FAN.get(),
            HABlocks.DEAD_BUTTON_CORAL_WALL_FAN.get(),

            HABlocks.LEAF_CORAL.get(),
            HABlocks.LEAF_CORAL_FAN.get(),
            HABlocks.LEAF_CORAL_WALL_FAN.get(),

            HABlocks.ROSE_CORAL.get(),
            HABlocks.ROSE_CORAL_FAN.get(),
            HABlocks.ROSE_CORAL_WALL_FAN.get(),

            HABlocks.SUN_CORAL.get(),
            HABlocks.SUN_CORAL_FAN.get(),
            HABlocks.SUN_CORAL_WALL_FAN.get(),

            HABlocks.LOPHELIA_CORAL.get(),
            HABlocks.LOPHELIA_CORAL_FAN.get(),
            HABlocks.LOPHELIA_CORAL_WALL_FAN.get(),

            HABlocks.THORN_CORAL.get(),
            HABlocks.THORN_CORAL_FAN.get(),
            HABlocks.THORN_CORAL_WALL_FAN.get(),

            HABlocks.BUTTON_CORAL.get(),
            HABlocks.BUTTON_CORAL_FAN.get(),
            HABlocks.BUTTON_CORAL_WALL_FAN.get(),

            HAPlatformBlocks.DRIFTWOOD_DOOR.get(),
            HAPlatformBlocks.DRIFTWOOD_TRAPDOOR.get(),

            HABlocks.GLOWSTICK.get(),
            HABlocks.WALL_GLOWSTICK.get(),
        )
    }
}