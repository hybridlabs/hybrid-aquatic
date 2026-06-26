package dev.hybridlabs.aquatic.client.model.block.entity.plushie

import dev.hybridlabs.aquatic.block.PlushieBlock
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartNames.BODY
import net.minecraft.client.model.geom.PartNames.JAW
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.CubeListBuilder
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.model.geom.builders.MeshDefinition

/**
 * The model for the Whale Shark Blahaj Plushie.
 * @see PlushieBlock.Variant.WHALE_SHARK
 */
class WhaleSharkPlushieModel(override val root: ModelPart) : PlushieModel() {
    companion object {
        fun createModelData(): LayerDefinition {
            val modelData = MeshDefinition()
            val rootPart = modelData.root

            rootPart.addOrReplaceChild(
                BODY,
                CubeListBuilder.create()
                    .texOffs(14, 13).addBox(-1.5F, -3.0F, 2.5F, 3.0F, 3.0F, 2.0F)
                    .texOffs(0, 15).addBox(-0.5F, -4.5F, 4.0F, 1.0F, 5.0F, 2.0F)
                    .texOffs(0, 0).addBox(-2.5F, -4.0F, -2.5F, 5.0F, 4.0F, 5.0F)
                    .texOffs(0, 2).addBox(-3.0F, -2.0F, -4.5F, 1.0F, 1.0F, 1.0F)
                    .texOffs(15, 0).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 1.0F, 3.0F)
                    .texOffs(6, 15).addBox(2.5F, -1.0F, -2.5F, 2.0F, 1.0F, 2.0F)
                    .texOffs(13, 9).addBox(-4.5F, -1.0F, -2.5F, 2.0F, 1.0F, 2.0F)
                    .texOffs(0, 9).addBox(-2.5F, -3.0F, -5.5F, 5.0F, 3.0F, 3.0F)
                    .texOffs(0, 0).addBox(2.0F, -2.0F, -4.5F, 1.0F, 1.0F, 1.0F),
                PartPose.ZERO
            )

            rootPart.addOrReplaceChild(
                JAW,
                CubeListBuilder.create(),
                PartPose.ZERO
            )

            return LayerDefinition.create(modelData, 32, 32)
        }
    }
}
