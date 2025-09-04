package dev.hybridlabs.aquatic.client.renderer.armor

import com.mojang.blaze3d.vertex.PoseStack
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer
import net.minecraft.client.model.HumanoidModel
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack

/**
 * Renders an armor layer over an entity with the given texture.
 *
 * To implement, call in a client initializer:
 * `ArmorRenderer.register(TexturedArmorRenderer(ResourceLocation("path/to/texture")), MY_HELMET, MY_CHESTPLATE, ...)`
 */
class TexturedArmorRenderer(
    /**
     * The texture id to render.
     */
    private val textureId: ResourceLocation
) : ArmorRenderer {
    override fun render(
        matrices: PoseStack,
        vertexConsumers: MultiBufferSource,
        stack: ItemStack,
        entity: LivingEntity,
        slot: EquipmentSlot,
        light: Int,
        contextModel: HumanoidModel<LivingEntity>
    ) {
        ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, contextModel, textureId)
    }
}
