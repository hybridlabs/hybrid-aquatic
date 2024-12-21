package dev.hybridlabs.aquatic.world.gen.feature

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.minecraft.world.gen.ProbabilityConfig
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.FeatureConfig

/**
 * A registry of world generation features for Hybrid Aquatic.
 */
object HybridAquaticFeatures {
    val MESSAGE_IN_A_BOTTLE = register("message_in_a_bottle", MessageInABottleFeature(MessageInABottleFeatureConfig.CODEC))
    val VENT_PATCH = register("vent_patch", VentPatchFeature(VentPatchFeatureConfig.CODEC))
    val SARGASSUM = register("sargassum", SargassumFeature(SargassumFeatureConfig.CODEC))
    val SEA_LETTUCE_PATCH = register("sea_lettuce_patch", SeaLettuceFeature(ProbabilityConfig.CODEC))
//    val DEEP_CORAL_TREE = register("deep_coral_tree", DeepCoralTreeFeature(DefaultFeatureConfig.CODEC))
//    val DEEP_CORAL_CLAW = register("deep_coral_claw", DeepCoralClawFeature(DefaultFeatureConfig.CODEC))
//    val DEEP_CORAL_MUSHROOM = register("deep_coral_mushroom", DeepCoralMushroomFeature(DefaultFeatureConfig.CODEC))
//    val BRINE_LAKE_FEATURE = register("brine_lake", BrineLakeFeature(BrineLakeFeatureConfig.CODEC))

    private fun <FC : FeatureConfig, F : Feature<FC>> register(id: String, feature: F): Feature<FC> {
        return Registry.register(Registries.FEATURE, Identifier(HybridAquatic.MOD_ID, id), feature)
    }
}
