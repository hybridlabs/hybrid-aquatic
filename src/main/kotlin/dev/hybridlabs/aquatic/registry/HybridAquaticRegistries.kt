package dev.hybridlabs.aquatic.registry

import dev.hybridlabs.aquatic.block.seamessage.SeaMessage
import dev.hybridlabs.aquatic.block.seamessage.SeaMessages
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry

object HybridAquaticRegistries {
    val SEA_MESSAGE: Registry<SeaMessage> = Registries.create(HybridAquaticRegistryKeys.SEA_MESSAGE) { SeaMessages.THE_CREEPERS_CODE }
}
