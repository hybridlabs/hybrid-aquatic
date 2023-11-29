package dev.hybridlabs.aquatic.world.gen.feature

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier
import net.minecraft.world.gen.feature.PlacedFeature

/**
 * A registry of placed features for Hybrid Aquatic.
 */
object HybridAquaticPlacedFeatures {
    /**
     * A patch of anemones.
     */
    val ANEMONE_PATCH = register("anemone_patch")

    /**
     * A cluster of thermal vents.
     */
    val HYDROTHERMAL_VENTS = register("hydrothermal_vents")

    /**
     * A patch of sea sponges
     */
    val TUBE_SPONGE_PATCH = register("sponge_patch")

    /**
     * A message in a bottle.
     */
    val MESSAGE_IN_A_BOTTLE = register("message_in_a_bottle")

    private fun register(id: String): RegistryKey<PlacedFeature> {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier(HybridAquatic.MOD_ID, id))
    }
}
