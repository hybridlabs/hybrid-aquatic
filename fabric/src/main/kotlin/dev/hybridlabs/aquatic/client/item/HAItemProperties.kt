package dev.hybridlabs.aquatic.client.item

import dev.hybridlabs.aquatic.item.HAItems
import net.fabricmc.fabric.api.`object`.builder.v1.client.model.FabricModelPredicateProviderRegistry
import net.minecraft.resources.ResourceLocation

object HAItemProperties {
    init {
        FabricModelPredicateProviderRegistry.register(
            HAItems.OMINOUS_CONCH.get(),
            ResourceLocation("tooting")
        ) { stack, _, entity, _ ->
            if (entity != null && entity.isUsingItem && entity.useItem == stack) 1.0f else 0.0f
        }
    }
}