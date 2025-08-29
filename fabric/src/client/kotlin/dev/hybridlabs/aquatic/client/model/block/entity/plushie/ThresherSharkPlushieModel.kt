package dev.hybridlabs.aquatic.client.model.block.entity.plushie

import dev.hybridlabs.aquatic.block.PlushieBlock
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.EntityModelPartNames.BODY
import net.minecraft.client.render.entity.model.EntityModelPartNames.TAIL_FIN

/**
 * The model for the Thresher Shark Plushie.
 * @see PlushieBlock.Variant.THRESHER_SHARK
 */
class ThresherSharkPlushieModel(override val root: ModelPart) : PlushieModel() {
    companion object {
        fun createModelData(): TexturedModelData {
            val modelData = ModelData()
            val rootPart = modelData.root

            val bodyPart = rootPart.addChild(
                BODY,
                ModelPartBuilder.create()
                    .uv(11, 4).cuboid(-1.0f, -3.0f, 1.0627f, 2.0f, 2.0f, 3.0f)
                    .uv(0, 0).cuboid(-1.5f, -3.0f, -2.9373f, 3.0f, 3.0f, 4.0f)
                    .uv(0, 2).cuboid(-2.0f, -2.5f, -4.4373f, 1.0f, 1.0f, 1.0f)
                    .uv(0, 12).cuboid(1.5f, -1.0f, -2.9373f, 2.0f, 1.0f, 2.0f)
                    .uv(10, 0).cuboid(-3.5f, -1.0f, -2.9373f, 2.0f, 1.0f, 2.0f)
                    .uv(0, 7).cuboid(-1.5f, -3.0f, -5.9373f, 3.0f, 2.0f, 3.0f)
                    .uv(0, 0).cuboid(1.0f, -2.5f, -4.4373f, 1.0f, 1.0f, 1.0f)
                    .uv(0, 15).cuboid(-0.5f, -5.0f, -2.4373f, 1.0f, 2.0f, 2.0f),
                ModelTransform.NONE
            )

            bodyPart.addChild(
                TAIL_FIN,
                ModelPartBuilder.create()
                    .uv(10, 10).cuboid(-0.5f, -4.5f, -1.55f, 1.0f, 7.0f, 2.0f),
                ModelTransform.of(0.0f, -2.5f, 4.5627f, -0.3927f, 0.0f, 0.0f)
            )


            return TexturedModelData.of(modelData, 32, 32)
        }
    }
}
