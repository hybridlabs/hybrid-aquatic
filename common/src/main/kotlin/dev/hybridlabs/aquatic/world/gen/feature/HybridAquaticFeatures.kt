package dev.hybridlabs.aquatic.world.gen.feature

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration

/**
 * A registry of world generation features for Hybrid Aquatic.
 */
object HybridAquaticFeatures {
    val MESSAGE_IN_A_BOTTLE = register("message_in_a_bottle", MessageInABottleFeature(MessageInABottleFeatureConfig.CODEC))
    val BULL_KELP = register("bull_kelp", BullKelpFeature(BullKelpFeatureConfig.CODEC))
    val VENT_PATCH = register("vent_patch", VentPatchFeature(VentPatchFeatureConfig.CODEC))
    val SARGASSUM = register("sargassum", SargassumFeature(SargassumFeatureConfig.CODEC))
    val SEA_LETTUCE_PATCH = register("sea_lettuce_patch", SeaLettuceFeature(ProbabilityFeatureConfiguration.CODEC))
    val RED_ALGAE_PATCH = register("red_algae_patch", RedAlgaeFeature(ProbabilityFeatureConfiguration.CODEC))

    fun <F : Feature<*>> register(
        id: String,
        feature: F
    ): RegistryObject<Feature<FeatureConfiguration>> {
        @Suppress("UNCHECKED_CAST")
        return CommonClass.FEATURE.register(id) { feature as Feature<FeatureConfiguration> }
    }
}