package dev.hybridlabs.aquatic.world.gen.feature

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import dev.hybridlabs.aquatic.world.gen.feature.algae.RedAlgaePatchFeature
import dev.hybridlabs.aquatic.world.gen.feature.algae.SeaLettuceFeature
import dev.hybridlabs.aquatic.world.gen.feature.corals.*
import dev.hybridlabs.aquatic.world.gen.feature.kelp.BullKelpFeature
import dev.hybridlabs.aquatic.world.gen.feature.kelp.BullKelpFeatureConfig
import dev.hybridlabs.aquatic.world.gen.feature.kelp.SargassumFeature
import dev.hybridlabs.aquatic.world.gen.feature.kelp.SargassumFeatureConfig
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration

/**
 * A registry of world generation features for Hybrid Aquatic.
 */
object HAFeatures {
    val MESSAGE_IN_A_BOTTLE = register("message_in_a_bottle", MessageInABottleFeature(MessageInABottleFeatureConfig.CODEC))
    val ANEMONES = register("anemones", AnemoneFeature())
    val BULL_KELP = register("bull_kelp", BullKelpFeature(BullKelpFeatureConfig.CODEC))
    val VENT_PATCH = register("vent_patch", VentPatchFeature(VentPatchFeatureConfig.CODEC))
    val SARGASSUM = register("sargassum", SargassumFeature(SargassumFeatureConfig.CODEC))
    val SEA_LETTUCE_PATCH = register("sea_lettuce_patch", SeaLettuceFeature(ProbabilityFeatureConfiguration.CODEC))
    val RED_ALGAE_PATCH = register("red_algae_patch", RedAlgaePatchFeature(ProbabilityFeatureConfiguration.CODEC))
    val SUSPICIOUS_SAND_DISK = register("suspicious_sand_disk", SuspiciousSandDiskFeature(DiskConfiguration.CODEC))

    val DEEP_CORAL_CLAW = register("deep_coral_claw", DeepCoralClawFeature(NoneFeatureConfiguration.CODEC))
    val DEEP_CORAL_TREE = register("deep_coral_tree", DeepCoralTreeFeature(NoneFeatureConfiguration.CODEC))
    val DEEP_CORAL_MUSHROOM = register("deep_coral_mushroom", DeepCoralMushroomFeature(NoneFeatureConfiguration.CODEC))
    val DEEP_CORAL_TABLE = register("deep_coral_table", DeepCoralTableFeature(NoneFeatureConfiguration.CODEC))

    val BLEACHED_CORAL_CLAW = register("bleached_coral_claw", BleachedCoralClawFeature(NoneFeatureConfiguration.CODEC))
    val BLEACHED_CORAL_TREE = register("bleached_coral_tree", BleachedCoralTreeFeature(NoneFeatureConfiguration.CODEC))
    val BLEACHED_CORAL_MUSHROOM = register("bleached_coral_mushroom",
        BleachedCoralMushroomFeature(NoneFeatureConfiguration.CODEC)
    )
    val BLEACHED_CORAL_TABLE = register("bleached_coral_table",
        BleachedCoralTableFeature(NoneFeatureConfiguration.CODEC)
    )

    val REEF_CORAL_CLAW = register("reef_coral_claw", ReefCoralClawFeature(NoneFeatureConfiguration.CODEC))
    val REEF_CORAL_TREE = register("reef_coral_tree", ReefCoralTreeFeature(NoneFeatureConfiguration.CODEC))
    val REEF_CORAL_MUSHROOM = register("reef_coral_mushroom", ReefCoralMushroomFeature(NoneFeatureConfiguration.CODEC))
    val REEF_CORAL_TABLE = register("reef_coral_table", ReefCoralTableFeature(NoneFeatureConfiguration.CODEC))

    val WHALE_FALL = register("whale_fall", WhaleFallFeature(WhaleFallFeatureConfig.CODEC))

    fun <F : Feature<*>> register(
        id: String,
        feature: F,
    ): RegistryObject<Feature<FeatureConfiguration>> {
        @Suppress("UNCHECKED_CAST")
        return CommonClass.FEATURE.register(id) { feature as Feature<FeatureConfiguration> }
    }
}
