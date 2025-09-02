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
 * The model for the Hammerhead Shark Blahaj Plushie.
 * @see PlushieBlock.Variant.HAMMERHEAD_SHARK
 */
class HammerheadSharkPlushieModel(override val root: ModelPart) : PlushieModel() {
    override val jaw: ModelPart = root.getChild(JAW)

    companion object {
        fun createModelData(): LayerDefinition {
            val modelData = MeshDefinition()
            val rootPart = modelData.root

            rootPart.addOrReplaceChild(
                BODY,
                CubeListBuilder.create()
                    .texOffs(15, 0).addBox(-1.5F, -3.0F, 2.5F, 3.0F, 3.0F, 1.0F)
                    .texOffs(0, 17).addBox(-0.5F, -4.5F, 3.0F, 1.0F, 5.0F, 2.0F)
                    .texOffs(0, 0).addBox(-2.5F, -4.0F, -2.5F, 5.0F, 4.0F, 5.0F)
                    .texOffs(0, 2).addBox(-3.5F, -2.5F, -4.5F, 1.0F, 1.0F, 1.0F)
                    .texOffs(9, 14).addBox(-0.5F, -6.0F, -1.5F, 1.0F, 2.0F, 3.0F)
                    .texOffs(15, 9).addBox(2.5F, -1.0F, -2.5F, 2.0F, 1.0F, 2.0F)
                    .texOffs(14, 14).addBox(-4.5F, -1.0F, -2.5F, 2.0F, 1.0F, 2.0F)
                    .texOffs(0, 9).addBox(-3.0F, -3.0F, -5.5F, 6.0F, 2.0F, 3.0F)
                    .texOffs(0, 0).addBox(2.5F, -2.5F, -4.5F, 1.0F, 1.0F, 1.0F),
                PartPose.ZERO
            )

            rootPart.addOrReplaceChild(
                JAW,
                CubeListBuilder.create()
                    .texOffs(0, 14).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 1.0f, 2.0f, CubeDeformation(-0.01F)),
                PartPose.offset(0.0F, -1.0F, -2.5F)
            )

            return LayerDefinition.create(modelData, 32, 32)
        }
    }
}
