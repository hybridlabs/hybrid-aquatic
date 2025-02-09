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
import net.minecraft.util.math.Direction
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.RotationAxis
import net.minecraft.util.math.Vec3d
import software.bernie.geckolib.renderer.GeoEntityRenderer
import java.lang.Math.pow
import kotlin.math.absoluteValue
import kotlin.math.sin
import kotlin.math.sqrt

class ThrowingStarEntityRenderer(renderManager: EntityRendererFactory.Context?) : EntityRenderer<ThrowingStarEntity>(renderManager) {

    private val itemRenderer: ItemRenderer = renderManager!!.itemRenderer;

    override fun render(entity: ThrowingStarEntity?, yaw: Float, tickDelta: Float, matrices: MatrixStack?, vertexConsumers: VertexConsumerProvider?, light: Int) {
        val stack = entity!!.displayItem;

        val worldTime = entity.world.time;
//        val lerpedRotationSpeed = MathHelper.lerp(1 / normalizeXZ(entity.velocity), 0.0f, MAX_SPEED)

        matrices!!.multiply(RotationAxis.NEGATIVE_X.rotationDegrees(90f));
        matrices.multiply(RotationAxis.NEGATIVE_Z.rotationDegrees((worldTime * tickDelta) * 5))


        val bakedModel = itemRenderer.getModel(stack, entity.world, null, 0)
        itemRenderer.renderItem(stack, ModelTransformationMode.FIXED, false, matrices, vertexConsumers, light, OverlayTexture.DEFAULT_UV, bakedModel);
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