package dev.hybridlabs.aquatic.registry

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.block.SeaMessage
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey

/**
 * Registry keys for Hybrid Aquatic.
 */
object HybridAquaticRegistryKeys {

    val SEA_MESSAGE: ResourceKey<Registry<SeaMessage>> =
        ResourceKey.createRegistryKey(CommonClass.locate("sea_message"))
}