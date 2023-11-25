package dev.hybridlabs.aquatic

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.entity.HybridAquaticBlockEntityTypes
import dev.hybridlabs.aquatic.client.item.tooltip.FishingNetTooltip
import dev.hybridlabs.aquatic.client.model.HybridAquaticEntityModelLayers
import dev.hybridlabs.aquatic.client.network.HybridAquaticClientNetworking
import dev.hybridlabs.aquatic.client.render.block.entity.*
import dev.hybridlabs.aquatic.client.render.entity.HybridAquaticEntityRenderers
import dev.hybridlabs.aquatic.client.render.hud.FishingNetHUDRenderer
import dev.hybridlabs.aquatic.client.render.item.*
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory

object HybridAquaticClient : ClientModInitializer {
    override fun onInitializeClient() {
        HybridAquaticEntityModelLayers
        HybridAquaticClientNetworking

        registerBlockRenderLayers()
        registerBlockEntityRenderers()
        registerBuiltinItemRenderers()
        registerEntityRenderers()
        registerWeatherRenderers()
        registerTooltips()
        registerHudAddons()
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
