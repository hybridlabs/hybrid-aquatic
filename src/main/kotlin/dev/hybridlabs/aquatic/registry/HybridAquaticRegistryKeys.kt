package dev.hybridlabs.aquatic.registry

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.SeaMessage
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryKey

/**
 * Registry keys for Hybrid Aquatic.
 */
object HybridAquaticRegistryKeys {

    val SEA_MESSAGE: RegistryKey<Registry<SeaMessage>> = RegistryKey.ofRegistry(Identifier(HybridAquatic.MOD_ID, "sea_message"))
}
