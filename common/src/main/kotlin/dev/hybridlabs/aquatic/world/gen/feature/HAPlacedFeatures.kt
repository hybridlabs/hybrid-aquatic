package dev.hybridlabs.aquatic.world.gen.feature

import dev.hybridlabs.aquatic.CommonClass
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.levelgen.placement.PlacedFeature

/**
 * A registry of placed features for Hybrid Aquatic.
 */
@Suppress("UNUSED_PARAMETER")
object HAPlacedFeatures {
    val ANEMONES = register("anemones")
    val DEEP_OCEAN_VEGETATION = register("deep_ocean_vegetation")
    val BLEACHED_REEF_VEGETATION = register("bleached_reef_vegetation")
    val CORAL_REEF_VEGETATION = register("coral_reef_vegetation")
    val RED_MEADOW_VEGETATION = register("red_meadow_vegetation")
    val SARGASSUM = register("sargassum")
    val FLOATING_SARGASSUM = register("floating_sargassum")
    val BULL_KELP = register("bull_kelp")
    val WATER_LETTUCE = register("water_lettuce")
    val WATER_HYACINTH = register("water_hyacinth")
    val JUNGLE_LILY_PAD = register("jungle_lily_pad")
    val RED_ALGAE_PATCH = register("red_algae_patch")
    val SEA_LETTUCE_PATCH = register("sea_lettuce_patch")
    val DUNEGRASS_PATCH = register("dunegrass_patch")

    val AERATED_SAND_CIRCLE = register("aerated_sand_circle")
    val SAND_CIRCLE = register("sand_circle")
    val SULFUR_DEPOSIT = register("sulfur_deposit")
    val DISK_SUSPICIOUS_SAND = register("disk_suspicious_sand")

    val GIANT_CLAM_PATCH = register("giant_clam_patch")
    val OYSTER_BED = register("oyster_bed")

    val THERMAL_VENT_CAVES = register("thermal_vent_caves")
    val THERMAL_VENT_TRENCHES = register("thermal_vent_trenches")

    val TUBE_SPONGE_PATCH = register("sponge_patch")
    val GLASS_SPONGE_PATCH = register("glass_sponge_patch")
    val HARP_SPONGE_PATCH = register("harp_sponge_patch")

    val MESSAGE_IN_A_BOTTLE = register("message_in_a_bottle")

    val TIDE_POOLS = register("tide_pools")
    val BOULDERS = register("boulders")
    val CORAL_MOUND = register("coral_mound")
    val CORAL_LAYER = register("coral_layer")
    val MOUND = register("mound")
    val WHITE_MOUND = register("calcite_mound")

    val WHALE_FALL = register("whale_fall")

    private fun register(id: String): ResourceKey<PlacedFeature> {
        return ResourceKey.create(Registries.PLACED_FEATURE, CommonClass.locate(id))
    }
}
