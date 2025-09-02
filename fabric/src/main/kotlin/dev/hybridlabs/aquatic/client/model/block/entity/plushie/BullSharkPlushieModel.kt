package dev.hybridlabs.aquatic.client.model.block.entity.plushie

import dev.hybridlabs.aquatic.block.PlushieBlock
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartNames.BODY
import net.minecraft.client.model.geom.PartNames.JAW
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.CubeDeformation
import net.minecraft.client.model.geom.builders.CubeListBuilder
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.model.geom.builders.MeshDefinition

/**
 * The model for the Bull Shark Blahaj Plushie.
 * @see PlushieBlock.Variant.BULL_SHARK
 */
class BullSharkPlushieModel(override val root: ModelPart) : PlushieModel() {
    override val jaw: ModelPart = root.getChild(JAW)

    companion object {
        fun createModelData(): LayerDefinition {
            val modelData = MeshDefinition()
            val rootPart = modelData.root

            rootPart.addOrReplaceChild(
                BODY,
                CubeListBuilder.create()
                    .texOffs(0, 12).addBox(-1.5f, -3.0f, 1.5f, 3.0f, 3.0f, 2.0f)
                    .texOffs(16, 16).addBox(-0.5f, -4.5f, 3.0f, 1.0f, 5.0f, 2.0f)
                    .texOffs(0, 0).addBox(-2.5f, -3.0f, -2.5f, 5.0f, 3.0f, 4.0f)
                    .texOffs(0, 2).addBox(-2.5f, -2.5f, -4.5f, 1.0f, 1.0f, 1.0f)
                    .texOffs(10, 13).addBox(-0.5f, -5.0f, -2.5f, 1.0f, 2.0f, 3.0f)
                    .texOffs(15, 13).addBox(2.5f, -1.0f, -2.5f, 2.0f, 1.0f, 2.0f)
                    .texOffs(14, 0).addBox(-4.5f, -1.0f, -2.5f, 2.0f, 1.0f, 2.0f)
                    .texOffs(0, 7).addBox(-2.0f, -3.0f, -5.5f, 4.0f, 2.0f, 3.0f)
                    .texOffs(0, 0).addBox(1.5f, -2.5f, -4.5f, 1.0f, 1.0f, 1.0f),
                PartPose.ZERO
            )

            rootPart.addOrReplaceChild(
                JAW,
                CubeListBuilder.create()
                    .texOffs(11, 9).addBox(-2.0f, 0.0f, -3.0f, 4.0f, 1.0f, 3.0f, CubeDeformation(-0.01F)),
                PartPose.offset(0.0F, -1.0F, -2.5F)
            )

            return LayerDefinition.create(modelData, 32, 32)
        }
    }
}
