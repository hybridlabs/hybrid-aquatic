package dev.hybridlabs.aquatic.client.model.block.entity.blahaj_plushie

import dev.hybridlabs.aquatic.block.BlahajPlushieBlock
import net.minecraft.client.model.ModelData
import net.minecraft.client.model.ModelPart
import net.minecraft.client.model.ModelPartBuilder
import net.minecraft.client.model.ModelTransform
import net.minecraft.client.model.TexturedModelData
import net.minecraft.client.render.entity.model.EntityModelPartNames.BODY

/**
 * The model for the Whale Shark Blahaj Plushie.
 * @see BlahajPlushieBlock.Variant.WHALE_SHARK
 */
class WhaleSharkBlahajPlushieModel(override val root: ModelPart) : BlahajPlushieModel() {
    companion object {
        fun createModelData(): TexturedModelData {
            val modelData = ModelData()
            val rootPart = modelData.root

            rootPart.addChild(
                BODY,
                ModelPartBuilder.create()
                    .uv(14, 13).cuboid(-1.5F, -3.0F, 2.5F, 3.0F, 3.0F, 2.0F)
                    .uv(0, 15).cuboid(-0.5F, -4.5F, 4.0F, 1.0F, 5.0F, 2.0F)
                    .uv(0, 0).cuboid(-2.5F, -4.0F, -2.5F, 5.0F, 4.0F, 5.0F)
                    .uv(0, 2).cuboid(-3.0F, -2.0F, -4.5F, 1.0F, 1.0F, 1.0F)
                    .uv(15, 0).cuboid(-0.5F, -5.0F, -0.5F, 1.0F, 1.0F, 3.0F)
                    .uv(6, 15).cuboid(2.5F, -1.0F, -2.5F, 2.0F, 1.0F, 2.0F)
                    .uv(13, 9).cuboid(-4.5F, -1.0F, -2.5F, 2.0F, 1.0F, 2.0F)
                    .uv(0, 9).cuboid(-2.5F, -3.0F, -5.5F, 5.0F, 3.0F, 3.0F)
                    .uv(0, 0).cuboid(2.0F, -2.0F, -4.5F, 1.0F, 1.0F, 1.0F),
                ModelTransform.NONE
            )

            return TexturedModelData.of(modelData, 32, 32)
        }
    }
}