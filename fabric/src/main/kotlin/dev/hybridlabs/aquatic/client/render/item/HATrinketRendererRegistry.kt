package dev.hybridlabs.aquatic.client.render.item

import dev.emi.trinkets.api.client.TrinketRendererRegistry
import dev.hybridlabs.aquatic.item.HAItems
import net.minecraft.client.model.HumanoidModel
import net.minecraft.client.player.AbstractClientPlayer
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.entity.ItemRenderer
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.Item
import software.bernie.geckolib.animatable.GeoItem
import software.bernie.geckolib.animatable.client.RenderProvider

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
}