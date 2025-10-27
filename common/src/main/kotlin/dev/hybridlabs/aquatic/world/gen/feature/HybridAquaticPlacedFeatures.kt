package dev.hybridlabs.aquatic.world.gen.feature

import dev.hybridlabs.aquatic.CommonClass
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.levelgen.placement.PlacedFeature

/**
 * A registry of placed features for Hybrid Aquatic.
 */
@Suppress("UNUSED_PARAMETER")
object HybridAquaticPlacedFeatures {
    val ANEMONES = register("anemones")

    val SARGASSUM = register("sargassum")
    val FLOATING_SARGASSUM = register("floating_sargassum")
    val BULL_KELP = register("bull_kelp")

    val WATER_LETTUCE = register("water_lettuce")

    val JUNGLE_LILY_PAD = register("jungle_lily_pad")

    val RED_ALGAE_PATCH = register("red_algae_patch")
    val RED_ALGAE_MEADOW = register("red_algae_meadow")

    val SEA_LETTUCE_PATCH = register("sea_lettuce_patch")

    val DUNEGRASS_PATCH = register("dunegrass_patch")

    val GIANT_CLAM_PATCH = register("giant_clam_patch")

    val THERMAL_VENT_PATCH = register("thermal_vent_patch")

    val TUBE_SPONGE_PATCH = register("sponge_patch")
    val MESSAGE_IN_A_BOTTLE = register("message_in_a_bottle")

    private fun register(id: String): ResourceKey<PlacedFeature> {
        return ResourceKey.create(Registries.PLACED_FEATURE, CommonClass.locate(id))
    }
}
