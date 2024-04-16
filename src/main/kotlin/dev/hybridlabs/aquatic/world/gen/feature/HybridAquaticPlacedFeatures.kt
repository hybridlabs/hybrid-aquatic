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

    val ANEMONE_PATCH = register("anemone_patch")

    val GIANT_CLAM_PATCH = register("giant_clam_patch")

    val HYDROTHERMAL_VENTS = register("hydrothermal_vents")

    val TUBE_SPONGE_PATCH = register("sponge_patch")

    val MESSAGE_IN_A_BOTTLE = register("message_in_a_bottle")

    val LOPHELIA_CORAL = register("lophelia_coral")

    private fun register(id: String): RegistryKey<PlacedFeature> {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier(HybridAquatic.MOD_ID, id))
    }
}
