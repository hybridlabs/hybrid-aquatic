package dev.hybridlabs.aquatic.client.model.block.entity.plushie

import dev.hybridlabs.aquatic.block.PlushieBlock
import net.minecraft.client.model.*
import net.minecraft.client.render.entity.model.EntityModelPartNames.BODY
import net.minecraft.client.render.entity.model.EntityModelPartNames.JAW

/**
 * The model for the Bull Shark Blahaj Plushie.
 * @see PlushieBlock.Variant.BULL_SHARK
 */
class BullSharkPlushieModel(override val root: ModelPart) : PlushieModel() {
    override val jaw: ModelPart = root.getChild(JAW)

    companion object {
        fun createModelData(): TexturedModelData {
            val modelData = ModelData()
            val rootPart = modelData.root

            rootPart.addChild(
                BODY,
                ModelPartBuilder.create()
                    .uv(0, 12).cuboid(-1.5f, -3.0f, 1.5f, 3.0f, 3.0f, 2.0f)
                    .uv(16, 16).cuboid(-0.5f, -4.5f, 3.0f, 1.0f, 5.0f, 2.0f)
                    .uv(0, 0).cuboid(-2.5f, -3.0f, -2.5f, 5.0f, 3.0f, 4.0f)
                    .uv(0, 2).cuboid(-2.5f, -2.5f, -4.5f, 1.0f, 1.0f, 1.0f)
                    .uv(10, 13).cuboid(-0.5f, -5.0f, -2.5f, 1.0f, 2.0f, 3.0f)
                    .uv(15, 13).cuboid(2.5f, -1.0f, -2.5f, 2.0f, 1.0f, 2.0f)
                    .uv(14, 0).cuboid(-4.5f, -1.0f, -2.5f, 2.0f, 1.0f, 2.0f)
                    .uv(0, 7).cuboid(-2.0f, -3.0f, -5.5f, 4.0f, 2.0f, 3.0f)
                    .uv(0, 0).cuboid(1.5f, -2.5f, -4.5f, 1.0f, 1.0f, 1.0f),
                ModelTransform.NONE
            )

            rootPart.addChild(
                JAW,
                ModelPartBuilder.create()
                    .uv(11, 9).cuboid(-2.0f, 0.0f, -3.0f, 4.0f, 1.0f, 3.0f, Dilation(-0.01F)),
                ModelTransform.pivot(0.0F, -1.0F, -2.5F)
            )

            return TexturedModelData.of(modelData, 32, 32)
        }
    }
}
