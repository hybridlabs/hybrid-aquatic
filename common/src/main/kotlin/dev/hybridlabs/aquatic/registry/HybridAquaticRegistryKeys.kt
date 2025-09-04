package dev.hybridlabs.aquatic.registry

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.SeaMessage
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.resources.ResourceLocation

/**
 * Registry keys for Hybrid Aquatic.
 */
object HybridAquaticRegistryKeys {

    val SEA_MESSAGE: RegistryKey<Registry<SeaMessage>> = RegistryKey.ofRegistry(ResourceLocation(Constants.MOD_ID, "sea_message"))
}