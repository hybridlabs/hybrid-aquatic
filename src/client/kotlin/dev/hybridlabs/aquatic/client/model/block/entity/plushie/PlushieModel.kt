package dev.hybridlabs.aquatic.client.model.block.entity.plushie

import net.minecraft.client.model.ModelPart
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.block.entity.SkullBlockEntityModel
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.math.MathHelper
import kotlin.math.sin

/**
 * Represents the model of a Blahaj Plushie.
 */
abstract class PlushieModel : SkullBlockEntityModel() {
    /**
     * The root model part.
     */
    abstract val root: ModelPart

    /**
     * The jaw model part.
     */
    open val jaw: ModelPart? = null

    override fun setHeadRotation(animationProgress: Float, yaw: Float, pitch: Float) {
        root.yaw = yaw * MathHelper.RADIANS_PER_DEGREE
        root.pitch = pitch * MathHelper.RADIANS_PER_DEGREE

        jaw?.pitch = (sin((animationProgress * 3.1415927f * 0.2f).toDouble()) + 1.0).toFloat() * 0.2f
    }

    override fun render(
        matrices: MatrixStack,
        vertices: VertexConsumer,
        light: Int,
        overlay: Int,
        red: Float,
        green: Float,
        blue: Float,
        alpha: Float
    ) {
        root.render(matrices, vertices, light, overlay, red, green, blue, alpha)
    }
}
