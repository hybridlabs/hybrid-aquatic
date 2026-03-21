package dev.hybridlabs.aquatic.forge

import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.block.PlushieBlock
import dev.hybridlabs.aquatic.block.SeaMessage
import dev.hybridlabs.aquatic.block.entity.HybridAquaticBlockEntityTypes
import dev.hybridlabs.aquatic.client.model.HybridAquaticEntityModelLayers.BASKING_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HybridAquaticEntityModelLayers.BULL_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HybridAquaticEntityModelLayers.FRILLED_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HybridAquaticEntityModelLayers.GREAT_WHITE_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HybridAquaticEntityModelLayers.HAMMERHEAD_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HybridAquaticEntityModelLayers.THRESHER_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HybridAquaticEntityModelLayers.TIGER_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HybridAquaticEntityModelLayers.WHALE_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.block.entity.plushie.BaskingSharkPlushieModel
import dev.hybridlabs.aquatic.client.model.block.entity.plushie.BullSharkPlushieModel
import dev.hybridlabs.aquatic.client.model.block.entity.plushie.FrilledSharkPlushieModel
import dev.hybridlabs.aquatic.client.model.block.entity.plushie.GreatWhiteSharkPlushieModel
import dev.hybridlabs.aquatic.client.model.block.entity.plushie.HammerheadSharkPlushieModel
import dev.hybridlabs.aquatic.client.model.block.entity.plushie.ThresherSharkPlushieModel
import dev.hybridlabs.aquatic.client.model.block.entity.plushie.TigerSharkPlushieModel
import dev.hybridlabs.aquatic.client.model.block.entity.plushie.WhaleSharkPlushieModel
import dev.hybridlabs.aquatic.client.render.block.HybridAquaticBlockRenderers
import dev.hybridlabs.aquatic.client.render.block.entity.AnemoneBlockEntityRenderer
import dev.hybridlabs.aquatic.client.render.block.entity.BuoyBlockEntityRenderer
import dev.hybridlabs.aquatic.client.render.block.entity.GiantGreenAnemoneBlockEntityRenderer
import dev.hybridlabs.aquatic.client.render.block.entity.MessageInABottleBlockEntityRenderer
import dev.hybridlabs.aquatic.client.render.block.entity.StrawberryAnemoneBlockEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.HybridAquaticEntityRenderers
import dev.hybridlabs.aquatic.entity.SpawnRestrictionRegistry
import dev.hybridlabs.aquatic.potions.HybridAquaticPotions
import dev.hybridlabs.aquatic.registry.HybridAquaticRegistryKeys
import dev.hybridlabs.aquatic.world.gen.biome.HybridAquaticBiomes
import net.minecraftforge.client.event.EntityRenderersEvent
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent
import net.minecraftforge.registries.DataPackRegistryEvent
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.forge.runForDist

object HybridAquaticModBusEvents {
    init {
        MOD_BUS.addListener(::loadSeaMessages)
        MOD_BUS.addListener(::registerPotionsRecipes)
        MOD_BUS.addListener(::registerSpawnPlacements)

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
            })
    }

    private fun loadSeaMessages(event: DataPackRegistryEvent.NewRegistry) {
        event.dataPackRegistry(
            HybridAquaticRegistryKeys.SEA_MESSAGE,
            SeaMessage.CODEC,
            SeaMessage.CODEC,
        )
    }

    private fun registerPotionsRecipes(event: FMLCommonSetupEvent) {
        event.enqueueWork {
            HybridAquaticPotions.registerPotionRecipes()
        }
    }

    private fun registerSpawnPlacements(event: SpawnPlacementRegisterEvent) {
        SpawnRestrictionRegistry.registerSpawnRestrictions()
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
        event.registerBlockEntityRenderer(HybridAquaticBlockEntityTypes.ANEMONE.get(), ::AnemoneBlockEntityRenderer)
        event.registerBlockEntityRenderer(
            HybridAquaticBlockEntityTypes.STRAWBERRY_ANEMONE.get(),
            ::StrawberryAnemoneBlockEntityRenderer
        )
        event.registerBlockEntityRenderer(
            HybridAquaticBlockEntityTypes.GIANT_GREEN_ANEMONE.get(),
            ::GiantGreenAnemoneBlockEntityRenderer
        )
        event.registerBlockEntityRenderer(
            HybridAquaticBlockEntityTypes.MESSAGE_IN_A_BOTTLE.get(),
            ::MessageInABottleBlockEntityRenderer
        )
        event.registerBlockEntityRenderer(HybridAquaticBlockEntityTypes.BUOY.get(), ::BuoyBlockEntityRenderer)
        HybridAquaticBlockRenderers.registerRenderShapes()
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
    }

    private fun onServerSetup(event: FMLDedicatedServerSetupEvent) {
        Constants.LOG.info("Server starting...")
        HybridAquaticBiomes.addBiomes()
    }
}