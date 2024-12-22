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
    val SARGASSUM = register("sargassum")
    val FLOATING_SARGASSUM = register("floating_sargassum")
    val GLOWING_PLANKTON = register("glowing_plankton")

    val WATER_LETTUCE = register("water_lettuce")

    val RED_ALGAE_PATCH = register("red_algae_patch")
    val SEA_LETTUCE_PATCH = register("sea_lettuce_patch")

    val GIANT_CLAM_PATCH = register("giant_clam_patch")
    val TUBE_SPONGE_PATCH = register("tube_sponge_patch")
    val THERMAL_VENT_PATCH = register("thermal_vent_patch")
    val MESSAGE_IN_A_BOTTLE = register("message_in_a_bottle")

//    val BRINE_LAKE = register("brine_lake")
//    val DEEP_CORAL_TREE = register("deep_coral_tree")
//    val DEEP_CORAL_CLAW = register("deep_coral_claw")
//    val DEEP_CORAL_MUSHROOM = register("deep_coral_mushroom")
//    val DEEP_OCEAN_VEGETATION = register("deep_ocean_vegetation")

    private fun register(id: String): RegistryKey<ConfiguredFeature<*, *>> {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier(HybridAquatic.MOD_ID, id))
    }
}