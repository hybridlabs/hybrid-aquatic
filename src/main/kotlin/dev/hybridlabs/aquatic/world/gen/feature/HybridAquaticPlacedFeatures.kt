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

    private fun register(id: String): RegistryKey<PlacedFeature> {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier(HybridAquatic.MOD_ID, id))
    }
}
