package dev.hybridlabs.aquatic.forge

import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.block.PlushieBlock
import dev.hybridlabs.aquatic.block.entity.HABlockEntityTypes
import dev.hybridlabs.aquatic.config.HAConfigHolder
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
import net.minecraft.client.renderer.BiomeColors
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent
import net.neoforged.fml.event.lifecycle.FMLDedicatedServerSetupEvent
import net.neoforged.neoforge.client.event.EntityRenderersEvent
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent
import net.neoforged.neoforge.client.network.event.RegisterClientPayloadHandlersEvent
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent
import net.neoforged.neoforge.fluids.FluidType
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.neoforge.forge.runForDist

object HybridAquaticModBusEvents {
    init {
        MOD_BUS.addListener(::registerSpawnPlacements)
        MOD_BUS.addListener(::registerNetworking)
        MOD_BUS.addListener(::onCommonSetup)

        runForDist(
            clientTarget = {
                MOD_BUS.addListener(::onClientSetup)
                MOD_BUS.addListener(::registerParticleProviders)
                MOD_BUS.addListener(::registerModelLayers)
                MOD_BUS.addListener(::registerSkullModels)
                MOD_BUS.addListener(::registerBlockEntityRenderers)
                MOD_BUS.addListener(::registerFluidTypes)
                MOD_BUS.addListener(::registerBlockColors)
                MOD_BUS.addListener(::registerClientPayloadHandlers)
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
        event.registerSkullModel(
            PlushieBlock.Variant.BASKING_SHARK,
            { modelSet -> BaskingSharkPlushieModel(modelSet.bakeLayer(BASKING_SHARK_PLUSHIE)) },
            null
        )
        event.registerSkullModel(
            PlushieBlock.Variant.BULL_SHARK,
            { modelSet -> BullSharkPlushieModel(modelSet.bakeLayer(BULL_SHARK_PLUSHIE)) },
            null
        )
        event.registerSkullModel(
            PlushieBlock.Variant.FRILLED_SHARK,
            { modelSet -> BullSharkPlushieModel(modelSet.bakeLayer(FRILLED_SHARK_PLUSHIE)) },
            null
        )
        event.registerSkullModel(
            PlushieBlock.Variant.GREAT_WHITE_SHARK,
            { modelSet -> BullSharkPlushieModel(modelSet.bakeLayer(GREAT_WHITE_SHARK_PLUSHIE)) },
            null
        )
        event.registerSkullModel(
            PlushieBlock.Variant.HAMMERHEAD_SHARK,
            { modelSet -> BullSharkPlushieModel(modelSet.bakeLayer(HAMMERHEAD_SHARK_PLUSHIE)) },
            null
        )
        event.registerSkullModel(
            PlushieBlock.Variant.THRESHER_SHARK,
            { modelSet -> BullSharkPlushieModel(modelSet.bakeLayer(THRESHER_SHARK_PLUSHIE)) },
            null
        )
        event.registerSkullModel(
            PlushieBlock.Variant.TIGER_SHARK,
            { modelSet -> BullSharkPlushieModel(modelSet.bakeLayer(TIGER_SHARK_PLUSHIE)) },
            null
        )
        event.registerSkullModel(
            PlushieBlock.Variant.WHALE_SHARK,
            { modelSet -> BullSharkPlushieModel(modelSet.bakeLayer(WHALE_SHARK_PLUSHIE)) },
            null
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

    private fun onCommonSetup(event: FMLCommonSetupEvent) {
        // Deferred registries are bound by this point, so generating the default spawn config is safe.
        event.enqueueWork { HAConfigHolder.handler }
    }

    private fun onClientSetup(event: FMLClientSetupEvent) {
        Constants.LOGGER.info("Initializing client...")
        if (Services.PLATFORM.isModLoaded("curios")) HACuriosCompat.registerRenderers()

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
            ServerPayloadHandler::handleDataOnMain
        )
    }

    private fun registerClientPayloadHandlers(event: RegisterClientPayloadHandlersEvent) {
        event.register(FishingBobberPayload.type, ClientPayloadHandler::handleDataOnMain)
    }

    fun registerBlockColors(event: RegisterColorHandlersEvent.Block) {
        event.register(
            { _, level, pos, _ ->
                if (level != null && pos != null) {
                    BiomeColors.getAverageFoliageColor(level, pos)
                } else {
                    -1
                }
            },
            HABlocks.FLOATING_LEAVES.get()
        )
    }
}
