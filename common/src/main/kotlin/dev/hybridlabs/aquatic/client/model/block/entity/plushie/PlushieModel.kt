package dev.hybridlabs.aquatic.client.model.block.entity.plushie

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import net.minecraft.client.model.SkullModelBase
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.util.Mth
import kotlin.math.sin

/**
 * Represents the model of a Blahaj Plushie.
 */
abstract class PlushieModel : SkullModelBase() {
    /**
     * The root model part.
     */
    abstract val root: ModelPart

    /**
     * The jaw model part.
     */
    open val jaw: ModelPart? = null

    override fun setupAnim(animationProgress: Float, yRot: Float, xRot: Float) {
        root.yRot = yRot * Mth.DEG_TO_RAD
        root.xRot = xRot * Mth.DEG_TO_RAD

        jaw?.xRot = (sin((animationProgress * 3.1415927f * 0.2f).toDouble()) + 1.0).toFloat() * 0.2f
    }

    override fun renderToBuffer(
        matrices: PoseStack,
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
