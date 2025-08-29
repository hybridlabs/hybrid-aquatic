package dev.hybridlabs.aquatic.client.render.armor

import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier

/**
 * Renders an armor layer over an entity with the given texture.
 *
 * To implement, call in a client initializer:
 * `ArmorRenderer.register(TexturedArmorRenderer(Identifier("path/to/texture")), MY_HELMET, MY_CHESTPLATE, ...)`
 */
class TexturedArmorRenderer(
    /**
     * The texture id to render.
     */
    private val textureId: Identifier
) : ArmorRenderer {
    override fun render(
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        stack: ItemStack,
        entity: LivingEntity,
        slot: EquipmentSlot,
        light: Int,
        contextModel: BipedEntityModel<LivingEntity>
    ) {
        ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, contextModel, textureId)
    }
}
