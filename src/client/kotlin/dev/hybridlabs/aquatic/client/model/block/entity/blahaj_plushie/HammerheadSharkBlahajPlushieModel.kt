package dev.hybridlabs.aquatic.client.model.block.entity.blahaj_plushie

import dev.hybridlabs.aquatic.block.BlahajPlushieBlock
import net.minecraft.client.model.Dilation
import net.minecraft.client.model.ModelData
import net.minecraft.client.model.ModelPart
import net.minecraft.client.model.ModelPartBuilder
import net.minecraft.client.model.ModelTransform
import net.minecraft.client.model.TexturedModelData
import net.minecraft.client.render.entity.model.EntityModelPartNames.BODY
import net.minecraft.client.render.entity.model.EntityModelPartNames.JAW

/**
 * The model for the Hammerhead Shark Blahaj Plushie.
 * @see BlahajPlushieBlock.Variant.HAMMERHEAD_SHARK
 */
class HammerheadSharkBlahajPlushieModel(override val root: ModelPart) : BlahajPlushieModel() {
    override val jaw: ModelPart = root.getChild(JAW)

    companion object {
        fun createModelData(): TexturedModelData {
            val modelData = ModelData()
            val rootPart = modelData.root

            rootPart.addChild(
                BODY,
                ModelPartBuilder.create()
                    .uv(15, 0).cuboid(-1.5F, -3.0F, 2.5F, 3.0F, 3.0F, 1.0F)
                    .uv(0, 17).cuboid(-0.5F, -4.5F, 3.0F, 1.0F, 5.0F, 2.0F)
                    .uv(0, 0).cuboid(-2.5F, -4.0F, -2.5F, 5.0F, 4.0F, 5.0F)
                    .uv(0, 2).cuboid(-3.5F, -2.5F, -4.5F, 1.0F, 1.0F, 1.0F)
                    .uv(9, 14).cuboid(-0.5F, -6.0F, -1.5F, 1.0F, 2.0F, 3.0F)
                    .uv(15, 9).cuboid(2.5F, -1.0F, -2.5F, 2.0F, 1.0F, 2.0F)
                    .uv(14, 14).cuboid(-4.5F, -1.0F, -2.5F, 2.0F, 1.0F, 2.0F)
                    .uv(0, 9).cuboid(-3.0F, -3.0F, -5.5F, 6.0F, 2.0F, 3.0F)
                    .uv(0, 0).cuboid(2.5F, -2.5F, -4.5F, 1.0F, 1.0F, 1.0F),
                ModelTransform.NONE
            )

            rootPart.addChild(
                JAW,
                ModelPartBuilder.create()
                    .uv(0, 14).cuboid(-2.0f, 0.0f, -2.0f, 4.0f, 1.0f, 2.0f, Dilation(-0.01F)),
                ModelTransform.pivot(0.0F, -1.0F, -2.5F)
            )

            return TexturedModelData.of(modelData, 32, 32)
        }
    }
}
