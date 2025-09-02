package dev.hybridlabs.aquatic.client.model.block.entity.plushie

import dev.hybridlabs.aquatic.block.PlushieBlock
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartNames.*
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.CubeListBuilder
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.model.geom.builders.MeshDefinition

/**
 * The model for the Frilled Shark Blahaj Plushie.
 * @see PlushieBlock.Variant.FRILLED_SHARK
 */
class FrilledSharkPlushieModel(override val root: ModelPart) : PlushieModel() {
    companion object {
        fun createModelData(): LayerDefinition {
            val modelData = MeshDefinition()
            val rootPart = modelData.root

            val bodyPart = rootPart.addOrReplaceChild(
                BODY,
                CubeListBuilder.create()
                    .texOffs(0, 6).addBox(-1.5F, -2.0F, 0.5F, 3.0F, 2.0F, 4.0F)
                    .texOffs(0, 0).addBox(-2.0F, -2.0F, -3.5F, 4.0F, 2.0F, 4.0F)
                    .texOffs(0, 2).addBox(-2.5F, -1.5F, -5.0F, 1.0F, 1.0F, 1.0F)
                    .texOffs(6, 12).addBox(-0.5F, -3.0F, 1.0F, 1.0F, 1.0F, 2.0F)
                    .texOffs(12, 0).addBox(2.0F, -1.0F, -3.5F, 2.0F, 1.0F, 2.0F)
                    .texOffs(10, 6).addBox(-4.0F, -1.0F, -3.5F, 2.0F, 1.0F, 2.0F)
                    .texOffs(11, 9).addBox(-2.0F, -2.0F, -6.5F, 4.0F, 2.0F, 3.0F)
                    .texOffs(0, 0).addBox(1.5F, -1.5F, -5.0F, 1.0F, 1.0F, 1.0F),
                PartPose.ZERO
            )

            bodyPart.addOrReplaceChild(
                RIGHT_FIN,
                CubeListBuilder.create().texOffs(0, 6).addBox(-1.0f, -1.0f, 0.0f, 1.0f, 2.0f, 0.0f),
                PartPose.offsetAndRotation(-2.0f, -1.0f, -4.0f, 0.0f, 0.3927f, 0.0f)
            )

            bodyPart.addOrReplaceChild(
                LEFT_FIN,
                CubeListBuilder.create().texOffs(2, 6).addBox(0.0f, -1.0f, 0.0f, 1.0f, 2.0f, 0.0f),
                PartPose.offsetAndRotation(2.0f, -1.0f, -4.0f, 0.0f, -0.3927f, 0.0f)
            )

            bodyPart.addOrReplaceChild(
                TAIL_FIN,
                CubeListBuilder.create().texOffs(0, 12).addBox(-0.5f, -3.5f, -0.5f, 1.0f, 5.0f, 2.0f),
                PartPose.offsetAndRotation(0.0f, -1.5f, 4.0f, -0.3927f, 0.0f, 0.0f)
            )


            return LayerDefinition.create(modelData, 32, 32)
        }
    }
}
