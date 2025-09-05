package dev.hybridlabs.aquatic.registry

import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.block.SeaMessage
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation

/**
 * Registry keys for Hybrid Aquatic.
 */
object HybridAquaticRegistryKeys {

    val SEA_MESSAGE: ResourceKey<Registry<SeaMessage>> = ResourceKey.createRegistryKey(ResourceLocation(Constants.MOD_ID, "sea_message"))
}