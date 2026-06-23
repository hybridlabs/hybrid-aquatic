@file:Suppress("UNUSED_PARAMETER")

package dev.hybridlabs.aquatic

import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.block.HAPlatformBlocks
import dev.hybridlabs.aquatic.block.entity.HABlockEntityTypes
import dev.hybridlabs.aquatic.client.data.HypnoticEntities
import dev.hybridlabs.aquatic.client.gui.screen.HAMenuScreens
import dev.hybridlabs.aquatic.client.item.HAItemProperties
import dev.hybridlabs.aquatic.client.item.tooltip.FishingNetTooltip
import dev.hybridlabs.aquatic.client.model.HAEntityModelLayers
import dev.hybridlabs.aquatic.client.model.HAEntityModelLayers.BASKING_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HAEntityModelLayers.BULL_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HAEntityModelLayers.FRILLED_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HAEntityModelLayers.GREAT_WHITE_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HAEntityModelLayers.HAMMERHEAD_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HAEntityModelLayers.THRESHER_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HAEntityModelLayers.TIGER_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HAEntityModelLayers.WHALE_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.block.entity.plushie.*
import dev.hybridlabs.aquatic.client.network.HybridAquaticClientNetworking
import dev.hybridlabs.aquatic.client.render.block.HABlockRendererRegistry
import dev.hybridlabs.aquatic.client.render.block.HAModelLayerRegistry
import dev.hybridlabs.aquatic.client.render.block.entity.*
import dev.hybridlabs.aquatic.client.render.entity.HybridAquaticEntityRenderers
import dev.hybridlabs.aquatic.client.render.fluid.HAFluidRenderer
import dev.hybridlabs.aquatic.client.render.item.HAItemRendererRegistry
import dev.hybridlabs.aquatic.client.render.item.HATrinketRendererRegistry
import dev.hybridlabs.aquatic.platform.ClientServices
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry.registerModelLayer
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers

@Suppress("UnusedExpression", "DEPRECATION")
object HybridAquaticClient : ClientModInitializer {
    override fun onInitializeClient() {
        HAEntityModelLayers
        HybridAquaticClientNetworking

        HABlockRendererRegistry
        HAItemRendererRegistry
        HybridAquaticEntityRenderers
        registerWeatherRenderers()
        HATrinketRendererRegistry
        HAModelLayerRegistry
        HAItemProperties
        HAFluidRenderer()

        HAMenuScreens

        ClientTickEvents.END_CLIENT_TICK.register { client ->
            HypnoticEntities.mobs.clear()
        }
    }

    private fun registerWeatherRenderers() {
        // TODO: hook up renderer to make this thing easier
    }

    private fun registerTooltips() {
        ItemTooltipCallback.EVENT.register(FishingNetTooltip())
    }

    private fun registerRenderShapes() {
        ClientServices.PLATFORM.registerBlockRenderers(
            RenderType.translucent(),
            HABlocks.ANEMONE.get(),
            HABlocks.GIANT_GREEN_ANEMONE.get(),
            HABlocks.STRAWBERRY_ANEMONE.get(),
            HABlocks.MESSAGE_IN_A_BOTTLE.get(),
            HAPlatformBlocks.GLOWSLIME_BLOCK.get(),
        )
        ClientServices.PLATFORM.registerBlockRenderers(
            RenderType.cutout(),
            HAPlatformBlocks.DUNEGRASS.get(),
            HAPlatformBlocks.TALL_DUNEGRASS.get(),

            HAPlatformBlocks.CATTAIL.get(),

            HABlocks.RED_ALGAE.get(),
            HABlocks.TALL_RED_ALGAE.get(),

            HABlocks.BULL_KELP.get(),
            HABlocks.BULL_KELP_PLANT.get(),

            HABlocks.SARGASSUM.get(),
            HABlocks.SARGASSUM_PLANT.get(),
            HABlocks.FLOATING_SARGASSUM.get(),

            HABlocks.WATER_LETTUCE.get(),
            HABlocks.JUNGLE_LILY_PAD.get(),

            HABlocks.GLOWING_PLANKTON.get(),

            HABlocks.SEA_LETTUCE.get(),
            HABlocks.TALL_SEA_LETTUCE.get(),

            HABlocks.CRAB_POT.get(),
            HABlocks.GIANT_CLAM.get(),
            HABlocks.TUBE_WORM.get(),

            HABlocks.LOPHELIA_CORAL.get(),
            HABlocks.LOPHELIA_CORAL_FAN.get(),
            HABlocks.LOPHELIA_CORAL_WALL_FAN.get(),
            HABlocks.DEAD_LOPHELIA_CORAL.get(),
            HABlocks.DEAD_LOPHELIA_CORAL_FAN.get(),
            HABlocks.DEAD_LOPHELIA_CORAL_WALL_FAN.get(),

            HABlocks.SUN_CORAL.get(),
            HABlocks.SUN_CORAL_FAN.get(),
            HABlocks.SUN_CORAL_WALL_FAN.get(),
            HABlocks.DEAD_SUN_CORAL.get(),
            HABlocks.DEAD_SUN_CORAL_FAN.get(),
            HABlocks.DEAD_SUN_CORAL_WALL_FAN.get(),

            HABlocks.LEAF_CORAL.get(),
            HABlocks.LEAF_CORAL_FAN.get(),
            HABlocks.LEAF_CORAL_WALL_FAN.get(),
            HABlocks.DEAD_LEAF_CORAL.get(),
            HABlocks.DEAD_LEAF_CORAL_FAN.get(),
            HABlocks.DEAD_LEAF_CORAL_WALL_FAN.get(),

            HABlocks.ROSE_CORAL.get(),
            HABlocks.ROSE_CORAL_FAN.get(),
            HABlocks.ROSE_CORAL_WALL_FAN.get(),
            HABlocks.DEAD_ROSE_CORAL.get(),
            HABlocks.DEAD_ROSE_CORAL_FAN.get(),
            HABlocks.DEAD_ROSE_CORAL_WALL_FAN.get(),

            HABlocks.BUTTON_CORAL.get(),
            HABlocks.BUTTON_CORAL_FAN.get(),
            HABlocks.BUTTON_CORAL_WALL_FAN.get(),
            HABlocks.DEAD_BUTTON_CORAL.get(),
            HABlocks.DEAD_BUTTON_CORAL_FAN.get(),
            HABlocks.DEAD_BUTTON_CORAL_WALL_FAN.get(),

            HABlocks.THORN_CORAL.get(),
            HABlocks.THORN_CORAL_FAN.get(),
            HABlocks.THORN_CORAL_WALL_FAN.get(),
            HABlocks.DEAD_THORN_CORAL.get(),
            HABlocks.DEAD_THORN_CORAL_FAN.get(),
            HABlocks.DEAD_THORN_CORAL_WALL_FAN.get(),

            HAPlatformBlocks.DRIFTWOOD_DOOR.get(),
            HAPlatformBlocks.DRIFTWOOD_TRAPDOOR.get(),
            HABlocks.GLOWSTICK.get(),
            HABlocks.WALL_GLOWSTICK.get(),
        )
    }

    private fun registerBlockEntityRenderers() {
        BlockEntityRenderers.register(HABlockEntityTypes.ANEMONE.get(), ::AnemoneBlockEntityRenderer)
        BlockEntityRenderers.register(
            HABlockEntityTypes.GIANT_GREEN_ANEMONE.get(),
            ::GiantGreenAnemoneBlockEntityRenderer
        )
        BlockEntityRenderers.register(
            HABlockEntityTypes.STRAWBERRY_ANEMONE.get(),
            ::StrawberryAnemoneBlockEntityRenderer
        )
        BlockEntityRenderers.register(
            HABlockEntityTypes.MESSAGE_IN_A_BOTTLE.get(),
            ::MessageInABottleBlockEntityRenderer
        )
        BlockEntityRenderers.register(HABlockEntityTypes.BUOY.get(), ::BuoyBlockEntityRenderer)
    }

    private fun registerEntityRenderers() {
        HybridAquaticEntityRenderers
    }

    private fun registerModelLayers() {
        registerModelLayer(BASKING_SHARK_PLUSHIE, BaskingSharkPlushieModel.Companion::createModelData)
        registerModelLayer(BULL_SHARK_PLUSHIE, BullSharkPlushieModel.Companion::createModelData)
        registerModelLayer(FRILLED_SHARK_PLUSHIE, FrilledSharkPlushieModel.Companion::createModelData)
        registerModelLayer(GREAT_WHITE_SHARK_PLUSHIE, GreatWhiteSharkPlushieModel.Companion::createModelData)
        registerModelLayer(HAMMERHEAD_SHARK_PLUSHIE, HammerheadSharkPlushieModel.Companion::createModelData)
        registerModelLayer(THRESHER_SHARK_PLUSHIE, ThresherSharkPlushieModel.Companion::createModelData)
        registerModelLayer(TIGER_SHARK_PLUSHIE, TigerSharkPlushieModel.Companion::createModelData)
        registerModelLayer(WHALE_SHARK_PLUSHIE, WhaleSharkPlushieModel.Companion::createModelData)

    }
}
