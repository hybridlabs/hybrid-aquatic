package dev.hybridlabs.aquatic.client.model.block.entity.plushie

import dev.hybridlabs.aquatic.block.PlushieBlock
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartNames.BODY
import net.minecraft.client.model.geom.PartNames.TAIL_FIN
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.CubeListBuilder
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.model.geom.builders.MeshDefinition

/**
 * The model for the Thresher Shark Plushie.
 * @see PlushieBlock.Variant.THRESHER_SHARK
 */
class ThresherSharkPlushieModel(override val root: ModelPart) : PlushieModel() {
    companion object {
        fun createModelData(): LayerDefinition {
            val modelData = MeshDefinition()
            val rootPart = modelData.root

            val bodyPart = rootPart.addOrReplaceChild(
                BODY,
                CubeListBuilder.create()
                    .texOffs(11, 4).addBox(-1.0f, -3.0f, 1.0627f, 2.0f, 2.0f, 3.0f)
                    .texOffs(0, 0).addBox(-1.5f, -3.0f, -2.9373f, 3.0f, 3.0f, 4.0f)
                    .texOffs(0, 2).addBox(-2.0f, -2.5f, -4.4373f, 1.0f, 1.0f, 1.0f)
                    .texOffs(0, 12).addBox(1.5f, -1.0f, -2.9373f, 2.0f, 1.0f, 2.0f)
                    .texOffs(10, 0).addBox(-3.5f, -1.0f, -2.9373f, 2.0f, 1.0f, 2.0f)
                    .texOffs(0, 7).addBox(-1.5f, -3.0f, -5.9373f, 3.0f, 2.0f, 3.0f)
                    .texOffs(0, 0).addBox(1.0f, -2.5f, -4.4373f, 1.0f, 1.0f, 1.0f)
                    .texOffs(0, 15).addBox(-0.5f, -5.0f, -2.4373f, 1.0f, 2.0f, 2.0f),
                PartPose.ZERO
            )

            bodyPart.addOrReplaceChild(
                TAIL_FIN,
                CubeListBuilder.create()
                    .texOffs(10, 10).addBox(-0.5f, -4.5f, -1.55f, 1.0f, 7.0f, 2.0f),
                PartPose.offsetAndRotation(0.0f, -2.5f, 4.5627f, -0.3927f, 0.0f, 0.0f)
            )


            return LayerDefinition.create(modelData, 32, 32)
        }
    }
}
