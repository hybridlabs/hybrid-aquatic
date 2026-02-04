package dev.hybridlabs.aquatic.world.gen.feature

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration

/**
 * A registry of world generation features for Hybrid Aquatic.
 */
object HybridAquaticFeatures {
    val MESSAGE_IN_A_BOTTLE = register("message_in_a_bottle", MessageInABottleFeature(MessageInABottleFeatureConfig.CODEC))
    val ANEMONES = register("anemones", AnemoneFeature())
    val BULL_KELP = register("bull_kelp", BullKelpFeature(BullKelpFeatureConfig.CODEC))
    val VENT_PATCH = register("vent_patch", VentPatchFeature(VentPatchFeatureConfig.CODEC))
    val SARGASSUM = register("sargassum", SargassumFeature(SargassumFeatureConfig.CODEC))
    val SEA_LETTUCE_PATCH = register("sea_lettuce_patch", SeaLettuceFeature(ProbabilityFeatureConfiguration.CODEC))
    val SHORT_RED_ALGAE_PATCH = register("short_red_algae_patch", ShortRedAlgaePatchFeature(ProbabilityFeatureConfiguration.CODEC))
    val RED_ALGAE_PATCH = register("red_algae_patch", RedAlgaePatchFeature(ProbabilityFeatureConfiguration.CODEC))
    val TALL_RED_ALGAE_PATCH = register("tall_red_algae_patch", TallRedAlgaePatchFeature(ProbabilityFeatureConfiguration.CODEC))
    val SUSPICIOUS_SAND_DISK = register("suspicious_sand_disk", SuspiciousSandDiskFeature(DiskConfiguration.CODEC))
    val SUSPICIOUS_RED_SAND_DISK = register("suspicious_red_sand_disk", SuspiciousRedSandDiskFeature(DiskConfiguration.CODEC))

    val DEEP_CORAL_CLAW = register("deep_coral_claw", DeepCoralClawFeature(NoneFeatureConfiguration.CODEC))
    val DEEP_CORAL_TREE = register("deep_coral_tree", DeepCoralTreeFeature(NoneFeatureConfiguration.CODEC))
    val DEEP_CORAL_MUSHROOM = register("deep_coral_mushroom", DeepCoralMushroomFeature(NoneFeatureConfiguration.CODEC))
    val DEEP_CORAL_TABLE = register("deep_coral_table", DeepCoralTableFeature(NoneFeatureConfiguration.CODEC))

    val REEF_CORAL_CLAW = register("reef_coral_claw", ReefCoralClawFeature(NoneFeatureConfiguration.CODEC))
    val REEF_CORAL_TREE = register("reef_coral_tree", ReefCoralTreeFeature(NoneFeatureConfiguration.CODEC))
    val REEF_CORAL_MUSHROOM = register("reef_coral_mushroom", ReefCoralMushroomFeature(NoneFeatureConfiguration.CODEC))
    val REEF_CORAL_TABLE = register("reef_coral_table", ReefCoralTableFeature(NoneFeatureConfiguration.CODEC))

    fun <F : Feature<*>> register(
        id: String,
        feature: F
    ): RegistryObject<Feature<FeatureConfiguration>> {
        @Suppress("UNCHECKED_CAST")
        return CommonClass.FEATURE.register(id) { feature as Feature<FeatureConfiguration> }
    }
}
