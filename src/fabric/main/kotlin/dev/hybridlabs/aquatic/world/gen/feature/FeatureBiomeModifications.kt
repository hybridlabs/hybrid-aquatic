package dev.hybridlabs.aquatic.world.gen.feature

import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors

/**
 * Applies biome modifications to features when initialised.
 */
object FeatureBiomeModifications {
    fun registerBiomeModifications() {
        for (addition in BiomeFeatureAddition.builtIn) {

            BiomeModifications.addFeature(
                BiomeSelectors.tag(addition.biomeTag),
                addition.step,
                addition.placedFeature
            )

        }
    }
}