package dev.hybridlabs.aquatic.world.gen.feature

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier
import net.minecraft.world.gen.feature.ConfiguredFeature

/**
 * A registry of configured features for Hybrid Aquatic.
 */
object HybridAquaticConfiguredFeatures {
    /**
     * A patch of anemones.
     */
    val ANEMONE_PATCH = register("anemone_patch")

    /**
     * A patch of giant clams.
     */
    val GIANT_CLAM_PATCH = register("giant_clam_patch")

    /**
     * A patch of living sponges.
     */
    val TUBE_SPONGE_PATCH = register("tube_sponge_patch")

    /**
     * A patch of vents.
     */
    val HYDROTHERMAL_VENTS= register("hydrothermal_vents")

    /**
     * A message in a bottle.
     */
    val MESSAGE_IN_A_BOTTLE = register("message_in_a_bottle")

    private fun register(id: String): RegistryKey<ConfiguredFeature<*, *>> {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier(HybridAquatic.MOD_ID, id))
    }
}
