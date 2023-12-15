package dev.hybridlabs.aquatic

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.entity.HybridAquaticBlockEntityTypes
import dev.hybridlabs.aquatic.client.item.tooltip.FishingNetTooltip
import dev.hybridlabs.aquatic.client.model.HybridAquaticEntityModelLayers
import dev.hybridlabs.aquatic.client.network.HybridAquaticClientNetworking
import dev.hybridlabs.aquatic.client.render.block.entity.AnemoneBlockEntityRenderer
import dev.hybridlabs.aquatic.client.render.block.entity.BuoyBlockEntityRenderer
import dev.hybridlabs.aquatic.client.render.block.entity.CrabPotBlockEntityRenderer
import dev.hybridlabs.aquatic.client.render.block.entity.FishingPlaqueBlockEntityRenderer
import dev.hybridlabs.aquatic.client.render.block.entity.HydrothermalVentBlockEntityRenderer
import dev.hybridlabs.aquatic.client.render.block.entity.MessageInABottleBlockEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.HybridAquaticEntityRenderers
import dev.hybridlabs.aquatic.client.render.hud.FishingNetHUDRenderer
import dev.hybridlabs.aquatic.client.render.item.AnemoneBlockItemRenderer
import dev.hybridlabs.aquatic.client.render.item.BuoyBlockItemRenderer
import dev.hybridlabs.aquatic.client.render.item.CrabPotBlockItemRenderer
import dev.hybridlabs.aquatic.client.render.item.FishingNetItemRenderer
import dev.hybridlabs.aquatic.client.render.item.HydrothermalVentBlockItemRenderer
import dev.hybridlabs.aquatic.client.render.item.MessageInABottleBlockItemRenderer
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory
import net.minecraft.resource.ResourceType

object HybridAquaticClient : ClientModInitializer {
    override fun onInitializeClient() {
        HybridAquaticEntityModelLayers
        HybridAquaticClientNetworking

        registerClientResourceListeners()
        registerBlockRenderLayers()
        registerBlockEntityRenderers()
        registerBuiltinItemRenderers()
        registerEntityRenderers()
        registerWeatherRenderers()
        registerTooltips()
        registerHudAddons()

        ModelLoadingPlugin.register { context -> context.addModels(FishingNetItemRenderer.INVENTORY_MODEL_ID.withPrefixedPath("models/")) }
    }

    private fun registerWeatherRenderers() {
        // TODO: hook up renderer to make this thing easier
    }

    private fun registerHudAddons() {
        HudRenderCallback.EVENT.register(FishingNetHUDRenderer())
    }

    private fun registerTooltips() {
        ItemTooltipCallback.EVENT.register(FishingNetTooltip())
    }

    private fun registerBlockRenderLayers(registry: BlockRenderLayerMap = BlockRenderLayerMap.INSTANCE) {
        registry.putBlocks(RenderLayer.getTranslucent(),
            HybridAquaticBlocks.ANEMONE,
            HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE
        )
    }

    private fun registerClientResourceListeners(registry: ResourceManagerHelper = ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES)) {
        registry.registerReloadListener(FishingNetItemRenderer)
    }

    private fun registerBlockEntityRenderers() {
        BlockEntityRendererFactories.register(HybridAquaticBlockEntityTypes.ANEMONE, ::AnemoneBlockEntityRenderer)
        BlockEntityRendererFactories.register(HybridAquaticBlockEntityTypes.MESSAGE_IN_A_BOTTLE, ::MessageInABottleBlockEntityRenderer)
        BlockEntityRendererFactories.register(HybridAquaticBlockEntityTypes.FISHING_PLAQUE, ::FishingPlaqueBlockEntityRenderer)
        BlockEntityRendererFactories.register(HybridAquaticBlockEntityTypes.BUOY, ::BuoyBlockEntityRenderer)
        BlockEntityRendererFactories.register(HybridAquaticBlockEntityTypes.HYDROTHERMAL_VENT, ::HydrothermalVentBlockEntityRenderer)
        BlockEntityRendererFactories.register(HybridAquaticBlockEntityTypes.CRAB_POT, ::CrabPotBlockEntityRenderer)
    }

    private fun registerEntityRenderers() {
        HybridAquaticEntityRenderers
    }

    private fun registerBuiltinItemRenderers(registry: BuiltinItemRendererRegistry = BuiltinItemRendererRegistry.INSTANCE) {
        registry.register(HybridAquaticItems.ANEMONE, AnemoneBlockItemRenderer())
        registry.register(HybridAquaticItems.BUOY, BuoyBlockItemRenderer())
        registry.register(HybridAquaticItems.CRAB_POT, CrabPotBlockItemRenderer())
        registry.register(HybridAquaticItems.MESSAGE_IN_A_BOTTLE, MessageInABottleBlockItemRenderer())
        registry.register(HybridAquaticItems.HYDROTHERMAL_VENT, HydrothermalVentBlockItemRenderer())
        registry.register(HybridAquaticItems.FISHING_NET, FishingNetItemRenderer)
    }

    fun createBlockEntityRendererFactoryContext(): BlockEntityRendererFactory.Context {
        val client = MinecraftClient.getInstance()
        return BlockEntityRendererFactory.Context(
            client.blockEntityRenderDispatcher,
            client.blockRenderManager,
            client.itemRenderer,
            client.entityRenderDispatcher,
            client.entityModelLoader,
            client.textRenderer
        )
    }
}
