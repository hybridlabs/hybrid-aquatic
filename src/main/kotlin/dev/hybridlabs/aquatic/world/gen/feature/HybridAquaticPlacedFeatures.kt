package dev.hybridlabs.aquatic.world.gen.feature

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryKey
import net.minecraft.world.gen.feature.PlacedFeature

/**
 * A registry of placed features for Hybrid Aquatic.
 */
object HybridAquaticPlacedFeatures {
    val ANEMONE_PATCH = register("anemone_patch")

    val SARGASSUM = register("sargassum")
    val SARGASSUM_FOREST = register("sargassum_forest")
    val FLOATING_SARGASSUM = register("floating_sargassum")

    val WATER_LETTUCE = register("water_lettuce")

    val JUNGLE_LILY_PAD = register("jungle_lily_pad")

    val GLOWING_PLANKTON = register("glowing_plankton")

    val RED_ALGAE_PATCH = register("red_algae_patch")
    val RED_ALGAE_MEADOW = register("red_algae_meadow")

    val SEA_LETTUCE_PATCH = register("sea_lettuce_patch")

    val GIANT_CLAM_PATCH = register("giant_clam_patch")

    val THERMAL_VENT_PATCH = register("thermal_vent_patch")

    val TUBE_SPONGE_PATCH = register("sponge_patch")
    val MESSAGE_IN_A_BOTTLE = register("message_in_a_bottle")

    private fun register(id: String): RegistryKey<PlacedFeature> {
        return RegistryKey.of(Registry.PLACED_FEATURE_KEY, Identifier(HybridAquatic.MOD_ID, id))
    }
}
