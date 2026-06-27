package dev.hybridlabs.aquatic.world.gen.feature

import dev.hybridlabs.aquatic.CommonClass
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature

/**
 * A registry of configured features for Hybrid Aquatic.
 */
object HAConfiguredFeatures {
    val ANEMONES = register("anemones")
    val DEEP_OCEAN_VEGETATION = register("deep_ocean_vegetation")
    val BLEACHED_REEF_VEGETATION = register("bleached_reef_vegetation")
    val CORAL_REEF_VEGETATION = register("coral_reef_vegetation")
    val RED_MEADOW_VEGETATION = register("red_meadow_vegetation")
    val RED_ALGAE_PATCH = register("red_algae_patch")

    val DUNEGRASS_PATCH = register("dunegrass_patch")
    val SARGASSUM = register("sargassum")
    val FLOATING_SARGASSUM = register("floating_sargassum")
    val BULL_KELP = register("bull_kelp")
    val DELESSERIA = register("delesseria")

    val WATER_LETTUCE = register("water_lettuce")
    val WATER_HYACINTH = register("water_hyacinth")

    val JUNGLE_LILY_PAD = register("jungle_lily_pad")

    val AERATED_SAND_CIRCLE = register("aerated_sand_circle")
    val SAND_CIRCLE = register("sand_circle")
    val SULFUR_DEPOSIT = register("sulfur_deposit")
    val SUSPICIOUS_SAND_DISK = register("disk_suspicious_sand")

    val SEA_LETTUCE_PATCH = register("sea_lettuce_patch")

    val GIANT_CLAM_PATCH = register("giant_clam_patch")
    val OYSTER_BED = register("oyster_bed")
    val TIDE_POOL_MUSSEL_PATCH = register("tide_pool_mussel_patch")
    val TUBE_SPONGE_PATCH = register("tube_sponge_patch")
    val GLASS_SPONGE_PATCH = register("glass_sponge_patch")
    val HARP_SPONGE_PATCH = register("harp_sponge_patch")
    val PING_PONG_SPONGE_PATCH = register("ping_pong_sponge_patch")
    val THERMAL_VENT_PATCH = register("thermal_vent_patch")
    val MESSAGE_IN_A_BOTTLE = register("message_in_a_bottle")
    val TIDE_POOLS = register("tide_pools")
    val BOULDER = register("boulder")
    val BRINE_POOL = register("brine_pool")
    val RED_BRINE_POOL = register("red_brine_pool")
    val ORANGE_BRINE_POOL = register("orange_brine_pool")
    val YELLOW_BRINE_POOL = register("yellow_brine_pool")

    val CORAL_MOUND = register("coral_mound")
    val CORAL_LAYER = register("coral_layer")

    val MOUND = register("mound")
    val WHITE_MOUND = register("white_mound")

    fun register(id: String): ResourceKey<ConfiguredFeature<*, *>> {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, CommonClass.locate(id))
    }
}