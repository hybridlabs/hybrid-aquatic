package dev.hybridlabs.aquatic.forge.compat

import com.mojang.blaze3d.vertex.PoseStack
import dev.hybridlabs.aquatic.item.HAItems
import net.minecraft.client.Minecraft
import net.minecraft.client.model.EntityModel
import net.minecraft.client.model.HumanoidModel
import net.minecraft.client.player.AbstractClientPlayer
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.RenderLayerParent
import net.minecraft.client.renderer.entity.state.HumanoidRenderState
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import software.bernie.geckolib.animatable.GeoItem
import software.bernie.geckolib.animatable.client.GeoRenderProvider
import software.bernie.geckolib.constant.DataTickets
import software.bernie.geckolib.renderer.GeoArmorRenderer
import software.bernie.geckolib.renderer.base.GeoRenderer
import top.theillusivec4.curios.api.SlotContext
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
        ICurioRenderer.register(item) { HACurioRenderer(equipmentSlot) }
    }

    private class HACurioRenderer(private val equipmentSlot: EquipmentSlot) : ICurioRenderer {
        override fun <S : LivingEntityRenderState, M : EntityModel<in S>> render(
            itemStack: ItemStack,
            slotContext: SlotContext,
            poseStack: PoseStack,
            submitNodeCollector: SubmitNodeCollector,
            packedLight: Int,
            renderState: S,
            renderLayerParent: RenderLayerParent<S, M>,
            context: EntityRendererProvider.Context,
            yRotation: Float,
            xRotation: Float
        ) {
            val player = slotContext.entity as? AbstractClientPlayer ?: return
            val humanoidState = renderState as? HumanoidRenderState ?: return
            val baseModel = renderLayerParent.model as? HumanoidModel<*> ?: return
            val armorRenderer = GeoRenderProvider.of(itemStack).getGeoArmorRenderer(itemStack, equipmentSlot)
                ?: return

            submitGeoArmor(
                armorRenderer,
                itemStack,
                player,
                equipmentSlot,
                baseModel,
                humanoidState,
                poseStack,
                submitNodeCollector,
                packedLight
            )
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun submitGeoArmor(
        armorRenderer: GeoArmorRenderer<*, *>,
        itemStack: ItemStack,
        entity: AbstractClientPlayer,
        equipmentSlot: EquipmentSlot,
        baseModel: HumanoidModel<*>,
        renderState: HumanoidRenderState,
        poseStack: PoseStack,
        submitNodeCollector: SubmitNodeCollector,
        packedLight: Int
    ) {
        val renderer =
            armorRenderer as GeoRenderer<GeoItem, GeoArmorRenderer.RenderData, HumanoidRenderState>
        val renderData = GeoArmorRenderer.RenderData(itemStack, equipmentSlot, entity, baseModel)
        val partialTick = Minecraft.getInstance().deltaTracker.getGameTimeDeltaPartialTick(false)

        renderState.addGeckolibData(DataTickets.PACKED_LIGHT, packedLight)
        renderer.fillRenderState(itemStack.item as GeoItem, renderData, renderState, partialTick)
        renderer.performRenderPass(
            renderState,
            poseStack,
            submitNodeCollector,
            Minecraft.getInstance().gameRenderer.levelRenderState.cameraRenderState
        )
    }
}
