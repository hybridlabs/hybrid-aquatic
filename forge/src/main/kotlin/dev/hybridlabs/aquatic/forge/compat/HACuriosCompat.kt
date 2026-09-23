package dev.hybridlabs.aquatic.forge.compat

import com.mojang.blaze3d.vertex.PoseStack
import dev.hybridlabs.aquatic.item.HAItems
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
import net.minecraftforge.client.extensions.common.IClientItemExtensions
import top.theillusivec4.curios.api.SlotContext
import top.theillusivec4.curios.api.client.CuriosRendererRegistry
import top.theillusivec4.curios.api.client.ICurioRenderer

/**
 * Curios is optional, so every reference to its API lives here and this object is only touched once
 * Curios is known to be loaded.
 */
object HACuriosCompat {
    fun registerRenderers() {
        registerCurioRenderer(HAItems.MOON_JELLYFISH_HAT.get(), EquipmentSlot.HEAD)
        registerCurioRenderer(HAItems.EEL_SCARF.get(), EquipmentSlot.CHEST)
        registerCurioRenderer(HAItems.STRIPED_EEL_SCARF.get(), EquipmentSlot.CHEST)
        registerCurioRenderer(HAItems.MANGLERFISH_FIN.get(), EquipmentSlot.CHEST)
        registerCurioRenderer(HAItems.MANGLERFISH_LURE.get(), EquipmentSlot.HEAD)
        registerCurioRenderer(HAItems.BROWN_HATXOLOTL.get(), EquipmentSlot.HEAD)
        registerCurioRenderer(HAItems.BLUE_HATXOLOTL.get(), EquipmentSlot.HEAD)
        registerCurioRenderer(HAItems.CYAN_HATXOLOTL.get(), EquipmentSlot.HEAD)
        registerCurioRenderer(HAItems.GOLD_HATXOLOTL.get(), EquipmentSlot.HEAD)
        registerCurioRenderer(HAItems.PINK_HATXOLOTL.get(), EquipmentSlot.HEAD)
    }

    private fun registerCurioRenderer(item: Item, equipmentSlot: EquipmentSlot) {
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
