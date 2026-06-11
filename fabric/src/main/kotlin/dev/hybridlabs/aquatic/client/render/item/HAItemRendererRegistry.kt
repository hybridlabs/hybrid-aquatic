package dev.hybridlabs.aquatic.client.render.item

import dev.hybridlabs.aquatic.client.render.item.renderer.AnemoneBlockItemRenderer
import dev.hybridlabs.aquatic.client.render.item.renderer.GiantGreenAnemoneBlockItemRenderer
import dev.hybridlabs.aquatic.client.render.item.renderer.MessageInABottleBlockItemRenderer
import dev.hybridlabs.aquatic.client.render.item.renderer.StrawberryAnemoneBlockItemRenderer
import dev.hybridlabs.aquatic.item.HAItems
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry

object HAItemRendererRegistry {
    val registry: BuiltinItemRendererRegistry = BuiltinItemRendererRegistry.INSTANCE

    init {
        registry.register(HAItems.ANEMONE.get(), AnemoneBlockItemRenderer())
        registry.register(HAItems.GIANT_GREEN_ANEMONE.get(), GiantGreenAnemoneBlockItemRenderer())
        registry.register(HAItems.STRAWBERRY_ANEMONE.get(), StrawberryAnemoneBlockItemRenderer())
        registry.register(HAItems.MESSAGE_IN_A_BOTTLE.get(), MessageInABottleBlockItemRenderer())
    }
}