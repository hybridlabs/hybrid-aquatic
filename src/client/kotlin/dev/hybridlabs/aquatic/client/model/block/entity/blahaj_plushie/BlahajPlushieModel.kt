package dev.hybridlabs.aquatic.client.model.block.entity.blahaj_plushie

import net.minecraft.client.model.ModelPart
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.block.entity.SkullBlockEntityModel
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.math.MathHelper

/**
 * Represents the model of a Blahaj Plushie.
 */
abstract class BlahajPlushieModel : SkullBlockEntityModel() {
    /**
     * The root model part.
     */
    abstract val root: ModelPart

    /**
     * The head model part.
     */
    abstract val head: ModelPart

    override fun setHeadRotation(animationProgress: Float, yaw: Float, pitch: Float) {
        head.yaw = yaw * MathHelper.RADIANS_PER_DEGREE
        head.pitch = pitch * MathHelper.RADIANS_PER_DEGREE
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
