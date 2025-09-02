package dev.hybridlabs.aquatic.client.model.block.entity.plushie

import dev.hybridlabs.aquatic.block.PlushieBlock
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartNames.BODY
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.CubeListBuilder
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.model.geom.builders.MeshDefinition

/**
 * The model for the Basking Shark Blahaj Plushie.
 * @see PlushieBlock.Variant.BASKING_SHARK
 */
class BaskingSharkPlushieModel(override val root: ModelPart) : PlushieModel() {
    companion object {
        fun createModelData(): LayerDefinition {
            val modelData = MeshDefinition()
            val rootPart = modelData.root

            rootPart.addOrReplaceChild(
                BODY,
                CubeListBuilder.create()
                    .texOffs(12, 12).addBox(-1.5f, -3.0f, 2.5f, 3.0f, 3.0f, 2.0f)
                    .texOffs(0, 14).addBox(-0.5f, -4.5f, 4.0f, 1.0f, 5.0f, 2.0f)
                    .texOffs(0, 0).addBox(-2.5f, -4.0f, -3.5f, 5.0f, 4.0f, 6.0f)
                    .texOffs(0, 2).addBox(-3.0f, -3.5f, -5.0f, 1.0f, 1.0f, 1.0f)
                    .texOffs(6, 14).addBox(-0.5f, -5.0f, -0.5f, 1.0f, 1.0f, 3.0f)
                    .texOffs(16, 3).addBox(2.5f, -1.0f, -1.5f, 2.0f, 1.0f, 2.0f)
                    .texOffs(16, 0).addBox(-4.5f, -1.0f, -1.5f, 2.0f, 1.0f, 2.0f)
                    .texOffs(0, 10).addBox(-2.5f, -4.0f, -5.5f, 5.0f, 2.0f, 2.0f)
                    .texOffs(0, 0).addBox(2.0f, -3.5f, -5.0f, 1.0f, 1.0f, 1.0f),
                PartPose.ZERO
            )

            return LayerDefinition.create(modelData, 32, 32)
        }
    }
}
