package dev.hybridlabs.aquatic.registry

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.SeaMessage
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.util.Identifier

/**
 * Registry keys for Hybrid Aquatic.
 */
object HybridAquaticRegistryKeys {
    /**
     * The registry key for the sea messages' registry.
     */
    val SEA_MESSAGE: RegistryKey<Registry<SeaMessage>> = RegistryKey.ofRegistry(Identifier(HybridAquatic.MOD_ID, "sea_message"))
}
