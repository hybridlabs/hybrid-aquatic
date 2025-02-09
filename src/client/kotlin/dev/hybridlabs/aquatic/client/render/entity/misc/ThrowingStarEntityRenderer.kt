package dev.hybridlabs.aquatic.client.render.entity.misc

import com.mojang.blaze3d.systems.RenderSystem
import dev.hybridlabs.aquatic.entity.miscellaneous.ThrowingStarEntity
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.RenderLayers
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRenderer
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.LivingEntityRenderer
import net.minecraft.client.render.item.ItemRenderer
import net.minecraft.client.render.model.json.ModelTransformationMode
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.Identifier
import net.minecraft.util.math.RotationAxis
import net.minecraft.util.math.Vec3d
import kotlin.math.absoluteValue
import kotlin.math.sqrt

class ThrowingStarEntityRenderer(renderManager: EntityRendererFactory.Context?) : EntityRenderer<ThrowingStarEntity>(renderManager) {

    private val itemRenderer: ItemRenderer = renderManager!!.itemRenderer;

    // TODO 1: implement a wobbling thing as it flies through the sky
    // TODO 2: Finish impl of fadeout
    override fun render(entity: ThrowingStarEntity?, yaw: Float, tickDelta: Float, matrices: MatrixStack?, vertexConsumers: VertexConsumerProvider?, light: Int) {
        matrices!!.push();
        val stack = entity!!.displayItem;

        val worldTime = entity.world.time;
//        val lerpedRotationSpeed = MathHelper.lerp(1 / normalizeXZ(entity.velocity), 0.0f, MAX_SPEED)

        matrices.multiply(RotationAxis.NEGATIVE_X.rotationDegrees(90f));
        matrices.multiply(RotationAxis.NEGATIVE_Y.rotationDegrees(entity.pitch))

        if(!entity.inGround) {
            matrices.multiply(RotationAxis.NEGATIVE_Z.rotationDegrees((worldTime * tickDelta) * 5))
        } else {

        }



        val bakedModel = itemRenderer.getModel(stack, entity.world, null, 0)
        itemRenderer.renderItem(stack, ModelTransformationMode.FIXED, false, matrices, vertexConsumers, light, OverlayTexture.DEFAULT_UV, bakedModel);
        matrices.pop()
    }

    override fun getTexture(entity: ThrowingStarEntity?): Identifier? {
        return null;
    }

    companion object {

        val MAX_SPEED = 5.0f;

        /**
         * Combine X and Z motion into one vector
         */
        fun normalizeXZ(pos : Vec3d) : Float {
            val a = pos.x.toFloat();
            val b = pos.z.toFloat();

            return sqrt((a * a) + (b * b)).absoluteValue;
        }
    }
}