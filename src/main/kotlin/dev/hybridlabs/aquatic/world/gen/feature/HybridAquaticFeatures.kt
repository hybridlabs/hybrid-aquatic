package dev.hybridlabs.aquatic.world.gen.feature

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.FeatureConfig

/**
 * A registry of world generation features for Hybrid Aquatic.
 */
object HybridAquaticFeatures {
    val MESSAGE_IN_A_BOTTLE = register("message_in_a_bottle", MessageInABottleFeature(MessageInABottleFeatureConfig.CODEC))

    private fun <FC : FeatureConfig, F : Feature<FC>> register(id: String, feature: F): Feature<FC> {
        return Registry.register(Registries.FEATURE, Identifier(HybridAquatic.MOD_ID, id), feature)
    }
}
