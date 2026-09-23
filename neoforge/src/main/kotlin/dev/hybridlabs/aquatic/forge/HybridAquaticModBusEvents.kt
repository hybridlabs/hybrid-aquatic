package dev.hybridlabs.aquatic.forge

import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.block.PlushieBlock
import dev.hybridlabs.aquatic.block.entity.HABlockEntityTypes
import dev.hybridlabs.aquatic.client.gui.screen.HAMenuScreens
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
import dev.hybridlabs.aquatic.fluid.BrineFluidType
import dev.hybridlabs.aquatic.fluid.HAPlatformFluids
import dev.hybridlabs.aquatic.forge.compat.HACuriosCompat
import dev.hybridlabs.aquatic.network.FishingBobberPayload
import dev.hybridlabs.aquatic.network.HybridAquaticNetworkingForge.ClientPayloadHandler
import dev.hybridlabs.aquatic.network.HybridAquaticNetworkingForge.ServerPayloadHandler
import dev.hybridlabs.aquatic.particle.*
import dev.hybridlabs.aquatic.platform.Services
import dev.hybridlabs.aquatic.world.gen.biome.HABiomes
import net.minecraft.client.renderer.ItemBlockRenderTypes
import net.minecraft.client.renderer.RenderType
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent
import net.neoforged.fml.event.lifecycle.FMLDedicatedServerSetupEvent
import net.neoforged.neoforge.client.event.EntityRenderersEvent
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent
import net.neoforged.neoforge.fluids.FluidType
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler
import net.neoforged.neoforge.registries.DataPackRegistryEvent
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.neoforge.forge.runForDist

object HybridAquaticModBusEvents {
    init {
        MOD_BUS.addListener(::registerSpawnPlacements)
        MOD_BUS.addListener(::addBiomes)
        MOD_BUS.addListener(::registerNetworking)

        runForDist(
            clientTarget = {
                MOD_BUS.addListener(::onClientSetup)
                MOD_BUS.addListener(::registerParticleProviders)
                MOD_BUS.addListener(::registerModelLayers)
                MOD_BUS.addListener(::registerSkullModels)
                MOD_BUS.addListener(::registerBlockEntityRenderers)
                MOD_BUS.addListener(::registerFluidTypes)
                HybridAquaticEntityRenderers
            },
            serverTarget = {
                MOD_BUS.addListener(::onServerSetup)
            })

        HAPlatformFluids.FLUID_TYPES.register(MOD_BUS)
    }

    private fun registerSpawnPlacements(event: RegisterSpawnPlacementsEvent) {
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
        event.registerBlockEntityRenderer(HABlockEntityTypes.ANEMONE.get(),
            ::AnemoneBlockEntityRenderer)

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

        event.registerBlockEntityRenderer(HABlockEntityTypes.BUOY.get(),
            ::BuoyBlockEntityRenderer)

        event.registerBlockEntityRenderer(HABlockEntityTypes.BELL_BUOY.get(),
            ::BellBuoyBlockEntityRenderer)

        HABlockRenderers.registerRenderShapes()
    }

    private fun registerFluidTypes(event: RegisterClientExtensionsEvent) {
        event.registerFluidType(BrineFluidType(FluidType.Properties.create()), HAPlatformFluids.BRINE_FLUIDTYPE.get())
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

    private fun registerParticleProviders(event: RegisterParticleProvidersEvent) {
        event.registerSpriteSet(HAParticleTypes.SARGASSUM.get()) { sprites ->
            SargassumParticle.Companion.Provider(sprites)
        }
        event.registerSpriteSet(HAParticleTypes.BRINE_BUBBLE.get()) { sprites ->
            BrineBubbleParticle.Companion.Provider(sprites)
        }
        event.registerSpriteSet(HAParticleTypes.BRINE_BUBBLE_POP.get()) { sprites ->
            BrineBubblePopParticle.Companion.Provider(sprites)
        }
        event.registerSpriteSet(HAParticleTypes.VENT_SMOKE.get()) { sprites ->
            VentSmokeParticle.Companion.Provider(sprites)
        }
    }

    private fun onClientSetup(event: FMLClientSetupEvent) {
        Constants.LOGGER.info("Initializing client...")
        if (Services.PLATFORM.isModLoaded("curios")) HACuriosCompat.registerRenderers()

        listOf(
            HAPlatformFluids.BRINE_STILL,
            HAPlatformFluids.BRINE_FLOWING
        ).forEach { fluid ->
            ItemBlockRenderTypes.setRenderLayer(fluid.get(), RenderType.solid())
        }

        HAMenuScreens
    }

    private fun onServerSetup(event: FMLDedicatedServerSetupEvent) {
        Constants.LOGGER.info("Server starting...")
    }

    private fun registerNetworking(event: RegisterPayloadHandlersEvent) {
        val registrar = event.registrar("1")
        registrar.playBidirectional(
            FishingBobberPayload.type,
            FishingBobberPayload.CODEC,
            DirectionalPayloadHandler(
                ClientPayloadHandler::handleDataOnMain,
                ServerPayloadHandler::handleDataOnMain,
            )
        )
    }
}