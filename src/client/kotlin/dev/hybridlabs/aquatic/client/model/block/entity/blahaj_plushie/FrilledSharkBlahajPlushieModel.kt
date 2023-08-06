package dev.hybridlabs.aquatic.client.model.block.entity.blahaj_plushie

import dev.hybridlabs.aquatic.block.BlahajPlushieBlock
import net.minecraft.client.model.ModelData
import net.minecraft.client.model.ModelPart
import net.minecraft.client.model.ModelPartBuilder
import net.minecraft.client.model.ModelTransform
import net.minecraft.client.model.TexturedModelData
import net.minecraft.client.render.entity.model.EntityModelPartNames.BODY
import net.minecraft.client.render.entity.model.EntityModelPartNames.LEFT_FIN
import net.minecraft.client.render.entity.model.EntityModelPartNames.RIGHT_FIN
import net.minecraft.client.render.entity.model.EntityModelPartNames.TAIL_FIN

/**
 * The model for the Frilled Shark Blahaj Plushie.
 * @see BlahajPlushieBlock.Variant.FRILLED_SHARK
 */
class FrilledSharkBlahajPlushieModel(override val root: ModelPart) : BlahajPlushieModel() {
    companion object {
        fun createModelData(): TexturedModelData {
            val modelData = ModelData()
            val rootPart = modelData.root

            val bodyPart = rootPart.addChild(
                BODY,
                ModelPartBuilder.create()
                    .uv(0, 6).cuboid(-1.5F, -2.0F, 0.5F, 3.0F, 2.0F, 4.0F)
                    .uv(0, 0).cuboid(-2.0F, -2.0F, -3.5F, 4.0F, 2.0F, 4.0F)
                    .uv(0, 2).cuboid(-2.5F, -1.5F, -5.0F, 1.0F, 1.0F, 1.0F)
                    .uv(6, 12).cuboid(-0.5F, -3.0F, 1.0F, 1.0F, 1.0F, 2.0F)
                    .uv(12, 0).cuboid(2.0F, -1.0F, -3.5F, 2.0F, 1.0F, 2.0F)
                    .uv(10, 6).cuboid(-4.0F, -1.0F, -3.5F, 2.0F, 1.0F, 2.0F)
                    .uv(11, 9).cuboid(-2.0F, -2.0F, -6.5F, 4.0F, 2.0F, 3.0F)
                    .uv(0, 0).cuboid(1.5F, -1.5F, -5.0F, 1.0F, 1.0F, 1.0F),
                ModelTransform.NONE
            )

            bodyPart.addChild(
                RIGHT_FIN,
                ModelPartBuilder.create().uv(0, 6).cuboid(-1.0f, -1.0f, 0.0f, 1.0f, 2.0f, 0.0f),
                ModelTransform.of(-2.0f, -1.0f, -4.0f, 0.0f, 0.3927f, 0.0f)
            )

            bodyPart.addChild(
                LEFT_FIN,
                ModelPartBuilder.create().uv(2, 6).cuboid(0.0f, -1.0f, 0.0f, 1.0f, 2.0f, 0.0f),
                ModelTransform.of(2.0f, -1.0f, -4.0f, 0.0f, -0.3927f, 0.0f)
            )

            bodyPart.addChild(
                TAIL_FIN,
                ModelPartBuilder.create().uv(0, 12).cuboid(-0.5f, -3.5f, -0.5f, 1.0f, 5.0f, 2.0f),
                ModelTransform.of(0.0f, -1.5f, 4.0f, -0.3927f, 0.0f, 0.0f)
            )


            return TexturedModelData.of(modelData, 32, 32)
        }
    }
}
