package dev.hybridlabs.aquatic.client.model.item

import net.minecraft.client.model.Model
import net.minecraft.client.model.ModelData
import net.minecraft.client.model.ModelPart
import net.minecraft.client.model.ModelPartBuilder
import net.minecraft.client.model.ModelTransform
import net.minecraft.client.model.TexturedModelData
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.util.math.MatrixStack

class FishingNetItemModel(
    val root: ModelPart
) : Model(RenderLayer::getEntitySolid) {
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
        this.root.render(matrices, vertices, light, overlay, red, green, blue, alpha)
    }

    companion object {
        fun createModelData(): TexturedModelData {
            return TexturedModelData.of(
                ModelData().apply {
                    root.addChild(
                        "fishing_net",
                        ModelPartBuilder.create()
                            .uv(0, 0)
                            .cuboid(0.5f, -8.0f, 1.0f, 1.0f, 1.0f, 16.0f)
                            .uv(0, 2)
                            .cuboid(-2.0f, -8.0f, 0.0f, 6.0f, 1.0f, 1.0f)
                            .uv(20, 22)
                            .cuboid(4.0f, -8.0f, -7.0f, 1.0f, 1.0f, 8.0f)
                            .uv(18, 0)
                            .cuboid(-3.0f, -8.0f, -7.0f, 1.0f, 1.0f, 8.0f)
                            .uv(0, 0)
                            .cuboid(-2.0f, -8.0f, -7.0f, 6.0f, 1.0f, 1.0f)
                            .uv(0, 17)
                            .cuboid(-2.5f, -7.5f, -6.5f, 7.0f, 6.0f, 7.0f),
                        ModelTransform.pivot(-1.0f, 23.0f, -9.0f)
                    )
                },
                64,
                64
            )
        }
    }
}
