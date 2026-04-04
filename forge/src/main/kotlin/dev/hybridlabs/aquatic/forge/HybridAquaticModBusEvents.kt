package dev.hybridlabs.aquatic.forge

import com.mojang.blaze3d.vertex.PoseStack
import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.block.PlushieBlock
import dev.hybridlabs.aquatic.block.SeaMessage
import dev.hybridlabs.aquatic.block.entity.HABlockEntityTypes
import dev.hybridlabs.aquatic.client.model.HAEntityModelLayers.BASKING_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HAEntityModelLayers.BULL_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HAEntityModelLayers.FRILLED_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HAEntityModelLayers.GREAT_WHITE_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HAEntityModelLayers.HAMMERHEAD_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HAEntityModelLayers.THRESHER_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HAEntityModelLayers.TIGER_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HAEntityModelLayers.WHALE_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.block.entity.plushie.*
import dev.hybridlabs.aquatic.client.render.block.HABlockRenderers
import dev.hybridlabs.aquatic.client.render.block.entity.*
import dev.hybridlabs.aquatic.client.render.entity.HybridAquaticEntityRenderers
import dev.hybridlabs.aquatic.entity.SpawnRestrictionRegistry
import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.aquatic.potions.HAPotions
import dev.hybridlabs.aquatic.registry.HARegistryKeys
import dev.hybridlabs.aquatic.world.gen.biome.HABiomes
import net.minecraft.client.model.EntityModel
import net.minecraft.client.model.HumanoidModel
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.entity.ItemRenderer
import net.minecraft.client.renderer.entity.RenderLayerParent
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraftforge.client.event.EntityRenderersEvent
import net.minecraftforge.client.extensions.common.IClientItemExtensions
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent
import net.minecraftforge.registries.DataPackRegistryEvent
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.forge.runForDist
import top.theillusivec4.curios.api.SlotContext
import top.theillusivec4.curios.api.client.CuriosRendererRegistry
import top.theillusivec4.curios.api.client.ICurioRenderer

object HybridAquaticModBusEvents {
    init {
        MOD_BUS.addListener(::loadSeaMessages)
        MOD_BUS.addListener(::registerPotionsRecipes)
        MOD_BUS.addListener(::registerSpawnPlacements)
        MOD_BUS.addListener(::addBiomes)

        runForDist(
            clientTarget = {
                MOD_BUS.addListener(::onClientSetup)
                MOD_BUS.addListener(::registerModelLayers)
                MOD_BUS.addListener(::registerSkullModels)
                MOD_BUS.addListener(::registerBlockEntityRenderers)
                HybridAquaticEntityRenderers
            },
            serverTarget = {
                MOD_BUS.addListener(::onServerSetup)
                HABiomes.addBiomes()
            })
    }

    private fun loadSeaMessages(event: DataPackRegistryEvent.NewRegistry) {
        event.dataPackRegistry(
            HARegistryKeys.SEA_MESSAGE,
            SeaMessage.CODEC,
            SeaMessage.CODEC,
        )
    }

    private fun registerPotionsRecipes(event: FMLCommonSetupEvent) {
        event.enqueueWork {
            HAPotions.registerPotionRecipes()
        }
    }

    private fun registerSpawnPlacements(event: SpawnPlacementRegisterEvent) {
        SpawnRestrictionRegistry.registerSpawnRestrictions()
    }

    private fun addBiomes(event: FMLCommonSetupEvent) {
        HABiomes.addBiomes()
    }

    private fun registerModelLayers(event: EntityRenderersEvent.RegisterLayerDefinitions) {
        event.registerLayerDefinition(BASKING_SHARK_PLUSHIE, BaskingSharkPlushieModel.Companion::createModelData)
        event.registerLayerDefinition(BULL_SHARK_PLUSHIE, BullSharkPlushieModel.Companion::createModelData)
        event.registerLayerDefinition(FRILLED_SHARK_PLUSHIE, FrilledSharkPlushieModel.Companion::createModelData)
        event.registerLayerDefinition(GREAT_WHITE_SHARK_PLUSHIE, GreatWhiteSharkPlushieModel.Companion::createModelData)
        event.registerLayerDefinition(HAMMERHEAD_SHARK_PLUSHIE, HammerheadSharkPlushieModel.Companion::createModelData)
        event.registerLayerDefinition(THRESHER_SHARK_PLUSHIE, ThresherSharkPlushieModel.Companion::createModelData)
        event.registerLayerDefinition(TIGER_SHARK_PLUSHIE, TigerSharkPlushieModel.Companion::createModelData)
        event.registerLayerDefinition(WHALE_SHARK_PLUSHIE, WhaleSharkPlushieModel.Companion::createModelData)
    }

    private fun registerBlockEntityRenderers(event: EntityRenderersEvent.RegisterRenderers) {
        event.registerBlockEntityRenderer(HABlockEntityTypes.ANEMONE.get(), ::AnemoneBlockEntityRenderer)
        event.registerBlockEntityRenderer(
            HABlockEntityTypes.STRAWBERRY_ANEMONE.get(),
            ::StrawberryAnemoneBlockEntityRenderer
        )
        event.registerBlockEntityRenderer(
            HABlockEntityTypes.GIANT_GREEN_ANEMONE.get(),
            ::GiantGreenAnemoneBlockEntityRenderer
        )
        event.registerBlockEntityRenderer(
            HABlockEntityTypes.MESSAGE_IN_A_BOTTLE.get(),
            ::MessageInABottleBlockEntityRenderer
        )
        event.registerBlockEntityRenderer(HABlockEntityTypes.BUOY.get(), ::BuoyBlockEntityRenderer)
        HABlockRenderers.registerRenderShapes()
    }

    private fun registerSkullModels(event: EntityRenderersEvent.CreateSkullModels) {
        val modelLoader = event.entityModelSet

        event.registerSkullModel(
            PlushieBlock.Variant.BASKING_SHARK,
            BaskingSharkPlushieModel(modelLoader.bakeLayer(BASKING_SHARK_PLUSHIE))
        )
        event.registerSkullModel(
            PlushieBlock.Variant.BULL_SHARK,
            BullSharkPlushieModel(modelLoader.bakeLayer(BULL_SHARK_PLUSHIE))
        )
        event.registerSkullModel(
            PlushieBlock.Variant.FRILLED_SHARK,
            BullSharkPlushieModel(modelLoader.bakeLayer(FRILLED_SHARK_PLUSHIE))
        )
        event.registerSkullModel(
            PlushieBlock.Variant.GREAT_WHITE_SHARK,
            BullSharkPlushieModel(modelLoader.bakeLayer(GREAT_WHITE_SHARK_PLUSHIE))
        )
        event.registerSkullModel(
            PlushieBlock.Variant.HAMMERHEAD_SHARK,
            BullSharkPlushieModel(modelLoader.bakeLayer(HAMMERHEAD_SHARK_PLUSHIE))
        )
        event.registerSkullModel(
            PlushieBlock.Variant.THRESHER_SHARK,
            BullSharkPlushieModel(modelLoader.bakeLayer(THRESHER_SHARK_PLUSHIE))
        )
        event.registerSkullModel(
            PlushieBlock.Variant.TIGER_SHARK,
            BullSharkPlushieModel(modelLoader.bakeLayer(TIGER_SHARK_PLUSHIE))
        )
        event.registerSkullModel(
            PlushieBlock.Variant.WHALE_SHARK,
            BullSharkPlushieModel(modelLoader.bakeLayer(WHALE_SHARK_PLUSHIE))
        )
    }

    private fun onClientSetup(event: FMLClientSetupEvent) {
        Constants.LOG.info("Initializing client...")
        registerTrinketRenderer(
            HAItems.MOON_JELLYFISH_HAT.get(), EquipmentSlot.HEAD
        )
        registerTrinketRenderer(
            HAItems.EEL_SCARF.get(), EquipmentSlot.CHEST
        )
        registerTrinketRenderer(
            HAItems.MANGLERFISH_FIN.get(), EquipmentSlot.CHEST
        )
        registerTrinketRenderer(
            HAItems.MANGLERFISH_LURE.get(), EquipmentSlot.HEAD
        )
        registerTrinketRenderer(
            HAItems.BROWN_HATXOLOTL.get(), EquipmentSlot.HEAD
        )
        registerTrinketRenderer(
            HAItems.BLUE_HATXOLOTL.get(), EquipmentSlot.HEAD
        )
        registerTrinketRenderer(
            HAItems.CYAN_HATXOLOTL.get(), EquipmentSlot.HEAD
        )
        registerTrinketRenderer(
            HAItems.GOLD_HATXOLOTL.get(), EquipmentSlot.HEAD
        )
        registerTrinketRenderer(
            HAItems.PINK_HATXOLOTL.get(), EquipmentSlot.HEAD
        )
    }

    private fun onServerSetup(event: FMLDedicatedServerSetupEvent) {
        Constants.LOG.info("Server starting...")
    }

    private fun registerTrinketRenderer(item: Item, equipmentSlot: EquipmentSlot) {
        CuriosRendererRegistry.register(item) { HACurioRenderer(equipmentSlot) }
    }

    private class HACurioRenderer(val equipmentSlot: EquipmentSlot) : ICurioRenderer {
        override fun <T : LivingEntity?, M : EntityModel<T?>?> render(
            itemStack: ItemStack,
            slotContext: SlotContext,
            poseStack: PoseStack,
            renderLayerParent: RenderLayerParent<T?, M?>,
            bufferSource: MultiBufferSource,
            light: Int,
            limbSwing: Float,
            limbSwingAmount: Float,
            partialTicks: Float,
            ageInTicks: Float,
            netHeadYaw: Float,
            headPitch: Float
        ) {
            val itemExtension = IClientItemExtensions.of(itemStack.item)
            val model = itemExtension.getGenericArmorModel(
                slotContext.entity, itemStack, equipmentSlot,
                (renderLayerParent.model) as HumanoidModel<*>
            )
            val vertexConsumer =
                ItemRenderer.getArmorFoilBuffer(bufferSource, RenderType.cutout(), false, false)
            model.renderToBuffer(
                poseStack, vertexConsumer, light, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f
            )
        }
    }
}