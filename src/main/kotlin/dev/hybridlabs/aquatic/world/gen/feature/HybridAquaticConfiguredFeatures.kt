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
    val ANEMONE_PATCH = register("anemone_patch")
    val GREEN_ANEMONE_PATCH = register("green_anemone_patch")
    val SARGASSUM = register("sargassum")
    val FLOATING_SARGASSUM = register("floating_sargassum")
    val BULL_KELP = register("bull_kelp")
    val DELESSERIA = register("delesseria")
    val GLOWING_PLANKTON = register("glowing_plankton")

    val WATER_LETTUCE = register("water_lettuce")

    val JUNGLE_LILY_PAD = register("jungle_lily_pad")

    val RED_ALGAE_PATCH = register("red_algae_patch")
    val SEA_LETTUCE_PATCH = register("sea_lettuce_patch")

    val GIANT_CLAM_PATCH = register("giant_clam_patch")
    val TUBE_SPONGE_PATCH = register("tube_sponge_patch")
    val THERMAL_VENT_PATCH = register("thermal_vent_patch")
    val MESSAGE_IN_A_BOTTLE = register("message_in_a_bottle")

    private fun register(id: String): RegistryKey<ConfiguredFeature<*, *>> {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier(HybridAquatic.MOD_ID, id))
    }
}