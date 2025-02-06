package dev.hybridlabs.aquatic.client.render.entity.misc

import dev.hybridlabs.aquatic.entity.miscellaneous.ThrowingStarEntity
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRenderer
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.ItemEntityRenderer
import net.minecraft.client.render.entity.ProjectileEntityRenderer
import net.minecraft.client.render.item.ItemRenderer
import net.minecraft.client.render.model.json.ModelTransformationMode
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.item.Items
import net.minecraft.util.Identifier
import software.bernie.geckolib.renderer.GeoEntityRenderer

class ThrowingStarEntityRenderer(renderManager: EntityRendererFactory.Context?) : EntityRenderer<ThrowingStarEntity>(renderManager) {

    private val itemRenderer: ItemRenderer = renderManager!!.itemRenderer;

    override fun render(entity: ThrowingStarEntity?, yaw: Float, tickDelta: Float, matrices: MatrixStack?, vertexConsumers: VertexConsumerProvider?, light: Int) {
        val stack = Items.STONE.defaultStack;

        val bakedModel = itemRenderer.getModel(stack, entity!!.world, null, 0)
        itemRenderer.renderItem(stack, ModelTransformationMode.FIXED, false, matrices, vertexConsumers, light, OverlayTexture.DEFAULT_UV, bakedModel);
    }

    override fun getTexture(entity: ThrowingStarEntity?): Identifier? {
        return null;
    }
}