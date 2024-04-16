package dev.hybridlabs.aquatic

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.entity.HybridAquaticBlockEntityTypes
import dev.hybridlabs.aquatic.client.GeoRenderProviderStorage
import dev.hybridlabs.aquatic.client.item.tooltip.FishingNetTooltip
import dev.hybridlabs.aquatic.client.model.HybridAquaticEntityModelLayers
import dev.hybridlabs.aquatic.client.network.HybridAquaticClientNetworking
import dev.hybridlabs.aquatic.client.render.armor.*
import dev.hybridlabs.aquatic.client.render.block.entity.*
import dev.hybridlabs.aquatic.client.render.entity.HybridAquaticEntityRenderers
import dev.hybridlabs.aquatic.client.render.item.*
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import software.bernie.geckolib.animatable.client.RenderProvider
import software.bernie.geckolib.renderer.GeoArmorRenderer

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
        registerGeoRenderers()
    }

    private fun registerGeoRenderers() {
        GeoRenderProviderStorage.divingArmorRenderProvider = createBasicRenderProvider(::DivingArmorRenderer)
        GeoRenderProviderStorage.manglerfishArmorRenderProvider = createBasicRenderProvider(::ManglerfishArmorRenderer)
        GeoRenderProviderStorage.turtleArmorRenderProvider = createBasicRenderProvider(::TurtleArmorRenderer)
        GeoRenderProviderStorage.eelArmorRenderProvider = createBasicRenderProvider(::EelArmorRenderer)
        GeoRenderProviderStorage.moonjellyfishArmorRenderProvider = createBasicRenderProvider(::MoonJellyfishArmorRenderer)
    }

    private fun createBasicRenderProvider(rendererProvider: () -> GeoArmorRenderer<*>): () -> RenderProvider {
        return {
            object : RenderProvider {
                private val renderer: GeoArmorRenderer<*> by lazy(rendererProvider)

                override fun getHumanoidArmorModel(
                    livingEntity: LivingEntity,
                    itemStack: ItemStack,
                    equipmentSlot: EquipmentSlot,
                    original: BipedEntityModel<LivingEntity>
                ): BipedEntityModel<LivingEntity> {
                    renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original)
                    return renderer
                }
            }
        }
    }

    private fun registerWeatherRenderers() {
        // TODO: hook up renderer to make this thing easier
    }

    private fun registerTooltips() {
        ItemTooltipCallback.EVENT.register(FishingNetTooltip())
    }

    private fun registerBlockRenderLayers(registry: BlockRenderLayerMap = BlockRenderLayerMap.INSTANCE) {
        registry.putBlocks(
            RenderLayer.getTranslucent(),
            HybridAquaticBlocks.ANEMONE,
            HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE,
        )
        registry.putBlocks(
            RenderLayer.getCutout(),
            HybridAquaticBlocks.LOPHELIA_CORAL,
            HybridAquaticBlocks.LOPHELIA_CORAL_FAN,
            HybridAquaticBlocks.LOPHELIA_CORAL_WALL_FAN,
            HybridAquaticBlocks.DEAD_LOPHELIA_CORAL,
            HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_FAN,
            HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_WALL_FAN,
            HybridAquaticBlocks.THORN_CORAL,
            HybridAquaticBlocks.THORN_CORAL_FAN,
            HybridAquaticBlocks.THORN_CORAL_WALL_FAN,
            HybridAquaticBlocks.DEAD_THORN_CORAL,
            HybridAquaticBlocks.DEAD_THORN_CORAL_FAN,
            HybridAquaticBlocks.DEAD_THORN_CORAL_WALL_FAN,
            HybridAquaticBlocks.DRIFTWOOD_DOOR,
            HybridAquaticBlocks.DRIFTWOOD_TRAPDOOR,
            HybridAquaticBlocks.GLOWSTICK,
            HybridAquaticBlocks.WALL_GLOWSTICK,
        )
    }

    private fun registerBlockEntityRenderers() {
        BlockEntityRendererFactories.register(HybridAquaticBlockEntityTypes.ANEMONE, ::AnemoneBlockEntityRenderer)
        BlockEntityRendererFactories.register(HybridAquaticBlockEntityTypes.GIANT_CLAM, ::GiantClamBlockEntityRenderer)
        BlockEntityRendererFactories.register(HybridAquaticBlockEntityTypes.MESSAGE_IN_A_BOTTLE, ::MessageInABottleBlockEntityRenderer)
        BlockEntityRendererFactories.register(HybridAquaticBlockEntityTypes.BUOY, ::BuoyBlockEntityRenderer)
        BlockEntityRendererFactories.register(HybridAquaticBlockEntityTypes.HYDROTHERMAL_VENT, ::HydrothermalVentBlockEntityRenderer)
        BlockEntityRendererFactories.register(HybridAquaticBlockEntityTypes.TUBE_SPONGE, ::TubeSpongeBlockEntityRenderer)
    }

    private fun registerEntityRenderers() {
        HybridAquaticEntityRenderers
    }

    private fun registerBuiltinItemRenderers(registry: BuiltinItemRendererRegistry = BuiltinItemRendererRegistry.INSTANCE) {
        registry.register(HybridAquaticItems.ANEMONE, AnemoneBlockItemRenderer())
        registry.register(HybridAquaticItems.GIANT_CLAM, GiantClamBlockItemRenderer())
        registry.register(HybridAquaticItems.TUBE_SPONGE, TubeSpongeBlockItemRenderer())
        registry.register(HybridAquaticItems.BUOY, BuoyBlockItemRenderer())
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
