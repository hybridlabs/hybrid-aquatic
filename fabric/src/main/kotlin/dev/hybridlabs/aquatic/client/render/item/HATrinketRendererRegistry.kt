package dev.hybridlabs.aquatic.client.render.item

import dev.emi.trinkets.api.client.TrinketRendererRegistry
import dev.hybridlabs.aquatic.item.HAItems
import net.minecraft.client.model.HumanoidModel
import net.minecraft.client.player.AbstractClientPlayer
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.Item
import software.bernie.geckolib.animatable.client.GeoRenderProvider
import software.bernie.geckolib.renderer.GeoArmorRenderer
import software.bernie.geckolib.util.Color

object HATrinketRendererRegistry {

    init {
        registerTrinketRenderer(HAItems.EEL_SCARF.get(), EquipmentSlot.CHEST)
        registerTrinketRenderer(HAItems.STRIPED_EEL_SCARF.get(), EquipmentSlot.CHEST)
        registerTrinketRenderer(HAItems.MANGLERFISH_FIN.get(), EquipmentSlot.CHEST)
        registerTrinketRenderer(HAItems.MOON_JELLYFISH_HAT.get(), EquipmentSlot.HEAD)
        registerTrinketRenderer(HAItems.MANGLERFISH_LURE.get(), EquipmentSlot.HEAD)
        registerTrinketRenderer(HAItems.PINK_HATXOLOTL.get(), EquipmentSlot.HEAD)
        registerTrinketRenderer(HAItems.GOLD_HATXOLOTL.get(), EquipmentSlot.HEAD)
        registerTrinketRenderer(HAItems.BROWN_HATXOLOTL.get(), EquipmentSlot.HEAD)
        registerTrinketRenderer(HAItems.CYAN_HATXOLOTL.get(), EquipmentSlot.HEAD)
        registerTrinketRenderer(HAItems.BLUE_HATXOLOTL.get(), EquipmentSlot.HEAD)
    }

    @Suppress("UNCHECKED_CAST")
    private fun registerTrinketRenderer(item: Item, equipmentSlot: EquipmentSlot) {
        TrinketRendererRegistry.registerRenderer(item) { itemStack, slotReference, contextModel, poseStack, bufferSource, light, entity, limbAngle, limbDistance, tickDelta, animationProgress, headYaw, headPitch ->
            if (entity is AbstractClientPlayer) {
                // InternalUtil.tryRenderGeoArmorPiece() is really useful!
                val renderer = GeoRenderProvider.of(item)
                val armorModel = renderer.getGeoArmorRenderer(
                    entity, itemStack, equipmentSlot,
                    contextModel as HumanoidModel<LivingEntity>
                ) as HumanoidModel<AbstractClientPlayer>? // entity IS AbstractClientPlayer, shouldn't worry about that
                if (armorModel == null) return@registerRenderer

                if (armorModel is GeoArmorRenderer<*>) armorModel.prepForRender(entity, itemStack, equipmentSlot, contextModel, bufferSource, tickDelta, limbAngle, limbDistance, headYaw, headPitch)
                (contextModel as HumanoidModel<AbstractClientPlayer>).copyPropertiesTo(armorModel)

                armorModel.renderToBuffer(poseStack, null, light, OverlayTexture.NO_OVERLAY, Color.WHITE.argbInt)
            }
        }
    }
}