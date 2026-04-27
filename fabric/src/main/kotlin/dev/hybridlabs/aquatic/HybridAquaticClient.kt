@file:Suppress("UNUSED_PARAMETER")

package dev.hybridlabs.aquatic

import dev.emi.trinkets.api.client.TrinketRendererRegistry
import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.block.HAPlatformBlocks
import dev.hybridlabs.aquatic.block.entity.HABlockEntityTypes
import dev.hybridlabs.aquatic.client.gui.screen.HAMenuScreens
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
import dev.hybridlabs.aquatic.client.particle.ClientParticleRegistry
import dev.hybridlabs.aquatic.client.render.GeoRenderProviderStorage
import dev.hybridlabs.aquatic.client.render.armor.*
import dev.hybridlabs.aquatic.client.render.block.entity.*
import dev.hybridlabs.aquatic.client.render.entity.HybridAquaticEntityRenderers
import dev.hybridlabs.aquatic.client.render.item.AnemoneBlockItemRenderer
import dev.hybridlabs.aquatic.client.render.item.GiantGreenAnemoneBlockItemRenderer
import dev.hybridlabs.aquatic.client.render.item.MessageInABottleBlockItemRenderer
import dev.hybridlabs.aquatic.client.render.item.StrawberryAnemoneBlockItemRenderer
import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.aquatic.platform.ClientServices
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry.registerModelLayer
import net.fabricmc.fabric.api.`object`.builder.v1.client.model.FabricModelPredicateProviderRegistry
import net.minecraft.client.model.HumanoidModel
import net.minecraft.client.player.AbstractClientPlayer
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers
import net.minecraft.client.renderer.entity.ItemRenderer
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import software.bernie.geckolib.animatable.GeoItem
import software.bernie.geckolib.animatable.client.RenderProvider
import software.bernie.geckolib.renderer.GeoArmorRenderer

@Suppress("UnusedExpression", "DEPRECATION")
object HybridAquaticClient : ClientModInitializer {
    override fun onInitializeClient() {
        HAEntityModelLayers
        HybridAquaticClientNetworking

        registerRenderShapes()
        registerBlockEntityRenderers()
        registerBuiltinItemRenderers()
        registerEntityRenderers()
        registerWeatherRenderers()
        registerGeoRenderers()
        registerTrinketRenderers()
        registerModelLayers()
        registerItemProperties()

        ClientParticleRegistry()

        HAMenuScreens.register()
    }

    private fun registerItemProperties() {
        FabricModelPredicateProviderRegistry.register(
            HAItems.OMINOUS_CONCH.get(),
            ResourceLocation("tooting")
        ) { stack, _, entity, _ ->
            if (entity != null && entity.isUsingItem && entity.useItem == stack) 1.0f else 0.0f
        }
    }

    private fun registerGeoRenderers() {
        GeoRenderProviderStorage.divingArmorRenderProvider = createBasicRenderProvider(::DivingArmorRenderer)
        GeoRenderProviderStorage.reinforcedDivingArmorRenderProvider =
            createBasicRenderProvider(::ReinforcedDivingArmorRenderer)
        GeoRenderProviderStorage.glowingDivingArmorRenderProvider =
            createBasicRenderProvider(::GlowingDivingArmorRenderer)
        GeoRenderProviderStorage.seashellArmorRenderProvider = createBasicRenderProvider(::SeashellArmorRenderer)
        GeoRenderProviderStorage.turtleArmorRenderProvider = createBasicRenderProvider(::TurtleArmorRenderer)

        //Cosmetics
        GeoRenderProviderStorage.manglerfishArmorRenderProvider = createBasicRenderProvider(::ManglerfishArmorRenderer)
        GeoRenderProviderStorage.eelArmorRenderProvider = createBasicRenderProvider(::EelArmorRenderer)
        GeoRenderProviderStorage.pinkHatxolotlArmorRenderProvider =
            createBasicRenderProvider(::PinkHatxolotlArmorRenderer)
        GeoRenderProviderStorage.goldHatxolotlArmorRenderProvider =
            createBasicRenderProvider(::GoldHatxolotlArmorRenderer)
        GeoRenderProviderStorage.brownHatxolotlArmorRenderProvider =
            createBasicRenderProvider(::BrownHatxolotlArmorRenderer)
        GeoRenderProviderStorage.cyanHatxolotlArmorRenderProvider =
            createBasicRenderProvider(::CyanHatxolotlArmorRenderer)
        GeoRenderProviderStorage.blueHatxolotlArmorRenderProvider =
            createBasicRenderProvider(::BlueHatxolotlArmorRenderer)
        GeoRenderProviderStorage.moonjellyfishArmorRenderProvider =
            createBasicRenderProvider(::MoonJellyfishArmorRenderer)
    }

    private fun registerTrinketRenderers() {
        registerTrinketRenderer(HAItems.EEL_SCARF.get(), EquipmentSlot.CHEST)
        registerTrinketRenderer(HAItems.MANGLERFISH_FIN.get(), EquipmentSlot.CHEST)
        registerTrinketRenderer(HAItems.MOON_JELLYFISH_HAT.get(), EquipmentSlot.HEAD)
        registerTrinketRenderer(HAItems.MANGLERFISH_LURE.get(), EquipmentSlot.HEAD)
        registerTrinketRenderer(HAItems.PINK_HATXOLOTL.get(), EquipmentSlot.HEAD)
        registerTrinketRenderer(HAItems.GOLD_HATXOLOTL.get(), EquipmentSlot.HEAD)
        registerTrinketRenderer(HAItems.BROWN_HATXOLOTL.get(), EquipmentSlot.HEAD)
        registerTrinketRenderer(HAItems.CYAN_HATXOLOTL.get(), EquipmentSlot.HEAD)
        registerTrinketRenderer(HAItems.BLUE_HATXOLOTL.get(), EquipmentSlot.HEAD)
    }

    private fun createBasicRenderProvider(rendererProvider: () -> GeoArmorRenderer<*>): () -> RenderProvider {
        return {
            object : RenderProvider {
                private val renderer: GeoArmorRenderer<*> by lazy(rendererProvider)

                override fun getHumanoidArmorModel(
                    livingEntity: LivingEntity,
                    itemStack: ItemStack,
                    equipmentSlot: EquipmentSlot,
                    original: HumanoidModel<LivingEntity>
                ): HumanoidModel<LivingEntity> {
                    renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original)
                    return renderer as HumanoidModel<LivingEntity>
                }
            }
        }
    }

    private fun registerTrinketRenderer(item: Item, equipmentSlot: EquipmentSlot) {
        TrinketRendererRegistry.registerRenderer(item) { itemStack, slotReference, contextModel, poseStack, bufferSource, light, entity, limbAngle, limbDistance, tickDelta, animationProgress, headYaw, headPitch ->
            if (entity is AbstractClientPlayer) {
                val renderer = (item as GeoItem).renderProvider.get() as RenderProvider
                val model = renderer.getGenericArmorModel(
                    entity, itemStack, equipmentSlot,
                    contextModel as HumanoidModel<LivingEntity>
                )
                val vertexConsumer =
                    ItemRenderer.getArmorFoilBuffer(bufferSource, RenderType.cutout(), false, false)
                model.renderToBuffer(
                    poseStack, vertexConsumer, light, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f
                )
            }
        }
    }

    private fun registerWeatherRenderers() {
        // TODO: hook up renderer to make this thing easier
    }

    private fun registerRenderShapes() {
        ClientServices.PLATFORM.registerBlockRenderers(
            RenderType.translucent(),
            HABlocks.ANEMONE.get(),
            HABlocks.GIANT_GREEN_ANEMONE.get(),
            HABlocks.STRAWBERRY_ANEMONE.get(),
            HABlocks.MESSAGE_IN_A_BOTTLE.get(),
            HAPlatformBlocks.GLOWSLIME_BLOCK.get(),
            HAPlatformBlocks.HAGSLIME_BLOCK.get(),
        )
        ClientServices.PLATFORM.registerBlockRenderers(
            RenderType.cutout(),
            HAPlatformBlocks.DUNEGRASS.get(),
            HAPlatformBlocks.TALL_DUNEGRASS.get(),

            HAPlatformBlocks.CATTAIL.get(),

            HABlocks.CLAMS.get(),
            HABlocks.BONE_WORMS.get(),

            HABlocks.SHORT_RED_ALGAE.get(),
            HABlocks.RED_ALGAE.get(),
            HABlocks.TALL_RED_ALGAE.get(),

            HABlocks.BULL_KELP.get(),
            HABlocks.BULL_KELP_PLANT.get(),

            HABlocks.SARGASSUM.get(),
            HABlocks.SARGASSUM_PLANT.get(),
            HABlocks.FLOATING_SARGASSUM.get(),

            HABlocks.HARP_SPONGE.get(),
            HABlocks.PING_PONG_SPONGE.get(),
            HABlocks.GLASS_SPONGE.get(),

            HABlocks.WATER_LETTUCE.get(),
            HABlocks.WATER_HYACINTH.get(),
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
            HABlocks.BLEACHED_LOPHELIA_CORAL.get(),
            HABlocks.BLEACHED_LOPHELIA_CORAL_FAN.get(),
            HABlocks.BLEACHED_LOPHELIA_CORAL_WALL_FAN.get(),

            HABlocks.BAMBOO_CORAL.get(),
            HABlocks.BAMBOO_CORAL_FAN.get(),
            HABlocks.BAMBOO_CORAL_WALL_FAN.get(),
            HABlocks.DEAD_BAMBOO_CORAL.get(),
            HABlocks.DEAD_BAMBOO_CORAL_FAN.get(),
            HABlocks.DEAD_BAMBOO_CORAL_WALL_FAN.get(),
            HABlocks.BLEACHED_BAMBOO_CORAL.get(),
            HABlocks.BLEACHED_BAMBOO_CORAL_FAN.get(),
            HABlocks.BLEACHED_BAMBOO_CORAL_WALL_FAN.get(),

            HABlocks.SUN_CORAL.get(),
            HABlocks.SUN_CORAL_FAN.get(),
            HABlocks.SUN_CORAL_WALL_FAN.get(),
            HABlocks.DEAD_SUN_CORAL.get(),
            HABlocks.DEAD_SUN_CORAL_FAN.get(),
            HABlocks.DEAD_SUN_CORAL_WALL_FAN.get(),
            HABlocks.BLEACHED_SUN_CORAL.get(),
            HABlocks.BLEACHED_SUN_CORAL_FAN.get(),
            HABlocks.BLEACHED_SUN_CORAL_WALL_FAN.get(),

            HABlocks.LEAF_CORAL.get(),
            HABlocks.LEAF_CORAL_FAN.get(),
            HABlocks.LEAF_CORAL_WALL_FAN.get(),
            HABlocks.DEAD_LEAF_CORAL.get(),
            HABlocks.DEAD_LEAF_CORAL_FAN.get(),
            HABlocks.DEAD_LEAF_CORAL_WALL_FAN.get(),
            HABlocks.BLEACHED_LEAF_CORAL.get(),
            HABlocks.BLEACHED_LEAF_CORAL_FAN.get(),
            HABlocks.BLEACHED_LEAF_CORAL_WALL_FAN.get(),

            HABlocks.ROSE_CORAL.get(),
            HABlocks.ROSE_CORAL_FAN.get(),
            HABlocks.ROSE_CORAL_WALL_FAN.get(),
            HABlocks.DEAD_ROSE_CORAL.get(),
            HABlocks.DEAD_ROSE_CORAL_FAN.get(),
            HABlocks.DEAD_ROSE_CORAL_WALL_FAN.get(),
            HABlocks.BLEACHED_ROSE_CORAL.get(),
            HABlocks.BLEACHED_ROSE_CORAL_FAN.get(),
            HABlocks.BLEACHED_ROSE_CORAL_WALL_FAN.get(),

            HABlocks.BUTTON_CORAL.get(),
            HABlocks.BUTTON_CORAL_FAN.get(),
            HABlocks.BUTTON_CORAL_WALL_FAN.get(),
            HABlocks.DEAD_BUTTON_CORAL.get(),
            HABlocks.DEAD_BUTTON_CORAL_FAN.get(),
            HABlocks.DEAD_BUTTON_CORAL_WALL_FAN.get(),
            HABlocks.BLEACHED_BUTTON_CORAL.get(),
            HABlocks.BLEACHED_BUTTON_CORAL_FAN.get(),
            HABlocks.BLEACHED_BUTTON_CORAL_WALL_FAN.get(),

            HABlocks.ZIGZAG_CORAL.get(),
            HABlocks.ZIGZAG_CORAL_FAN.get(),
            HABlocks.ZIGZAG_CORAL_WALL_FAN.get(),
            HABlocks.DEAD_ZIGZAG_CORAL.get(),
            HABlocks.DEAD_ZIGZAG_CORAL_FAN.get(),
            HABlocks.DEAD_ZIGZAG_CORAL_WALL_FAN.get(),
            HABlocks.BLEACHED_ZIGZAG_CORAL.get(),
            HABlocks.BLEACHED_ZIGZAG_CORAL_FAN.get(),
            HABlocks.BLEACHED_ZIGZAG_CORAL_WALL_FAN.get(),

            HABlocks.THORN_CORAL.get(),
            HABlocks.THORN_CORAL_FAN.get(),
            HABlocks.THORN_CORAL_WALL_FAN.get(),
            HABlocks.DEAD_THORN_CORAL.get(),
            HABlocks.DEAD_THORN_CORAL_FAN.get(),
            HABlocks.DEAD_THORN_CORAL_WALL_FAN.get(),
            HABlocks.BLEACHED_THORN_CORAL.get(),
            HABlocks.BLEACHED_THORN_CORAL_FAN.get(),
            HABlocks.BLEACHED_THORN_CORAL_WALL_FAN.get(),

            HABlocks.BLEACHED_FIRE_CORAL.get(),
            HABlocks.BLEACHED_FIRE_CORAL_FAN.get(),
            HABlocks.BLEACHED_FIRE_CORAL_WALL_FAN.get(),

            HABlocks.BLEACHED_TUBE_CORAL.get(),
            HABlocks.BLEACHED_TUBE_CORAL_FAN.get(),
            HABlocks.BLEACHED_TUBE_CORAL_WALL_FAN.get(),

            HABlocks.BLEACHED_HORN_CORAL.get(),
            HABlocks.BLEACHED_HORN_CORAL_FAN.get(),
            HABlocks.BLEACHED_HORN_CORAL_WALL_FAN.get(),

            HABlocks.BLEACHED_BUBBLE_CORAL.get(),
            HABlocks.BLEACHED_BUBBLE_CORAL_FAN.get(),
            HABlocks.BLEACHED_BUBBLE_CORAL_WALL_FAN.get(),

            HABlocks.BLEACHED_BRAIN_CORAL.get(),
            HABlocks.BLEACHED_BRAIN_CORAL_FAN.get(),
            HABlocks.BLEACHED_BRAIN_CORAL_WALL_FAN.get(),

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
        BlockEntityRenderers.register(HABlockEntityTypes.BUOY.get(),
            ::BuoyBlockEntityRenderer)
        BlockEntityRenderers.register(HABlockEntityTypes.BELL_BUOY.get(),
            ::BellBuoyBlockEntityRenderer)
    }

    private fun registerEntityRenderers() {
        HybridAquaticEntityRenderers
    }

    private fun registerBuiltinItemRenderers(registry: BuiltinItemRendererRegistry = BuiltinItemRendererRegistry.INSTANCE) {
        registry.register(HAItems.ANEMONE.get(), AnemoneBlockItemRenderer())
        registry.register(HAItems.GIANT_GREEN_ANEMONE.get(), GiantGreenAnemoneBlockItemRenderer())
        registry.register(HAItems.STRAWBERRY_ANEMONE.get(), StrawberryAnemoneBlockItemRenderer())
        registry.register(HAItems.MESSAGE_IN_A_BOTTLE.get(), MessageInABottleBlockItemRenderer())
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
