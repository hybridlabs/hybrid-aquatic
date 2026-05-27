@file:Suppress("UNUSED_PARAMETER")

package dev.hybridlabs.aquatic

import dev.hybridlabs.aquatic.client.data.HypnoticEntities
import dev.hybridlabs.aquatic.client.gui.screen.HAMenuScreens
import dev.hybridlabs.aquatic.client.item.HAItemProperties
import dev.hybridlabs.aquatic.client.model.HAEntityModelLayers
import dev.hybridlabs.aquatic.client.network.HybridAquaticClientNetworking
import dev.hybridlabs.aquatic.client.render.block.HABlockRendererRegistry
import dev.hybridlabs.aquatic.client.render.block.HAModelLayerRegistry
import dev.hybridlabs.aquatic.client.render.entity.HybridAquaticEntityRenderers
import dev.hybridlabs.aquatic.client.render.item.HAGeoRendererRegistry
import dev.hybridlabs.aquatic.client.render.item.HAItemRendererRegistry
import dev.hybridlabs.aquatic.client.render.item.HATrinketRendererRegistry
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents

@Suppress("UnusedExpression", "DEPRECATION")
object HybridAquaticClient : ClientModInitializer {
    override fun onInitializeClient() {
        HAEntityModelLayers
        HybridAquaticClientNetworking

        HABlockRendererRegistry
        HAItemRendererRegistry
        HybridAquaticEntityRenderers
        registerWeatherRenderers()
        HAGeoRendererRegistry
        HATrinketRendererRegistry
        HAModelLayerRegistry
        HAItemProperties

        HAMenuScreens

        ClientTickEvents.END_CLIENT_TICK.register { client ->
            HypnoticEntities.mobs.clear()
        }
    }

    private fun registerWeatherRenderers() {
        // TODO: hook up renderer to make this thing easier
    }
}
