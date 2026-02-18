package dev.hybridlabs.aquatic.client.model.entity.mammal

import dev.hybridlabs.aquatic.entity.fish.TunaEntity
import dev.hybridlabs.aquatic.entity.mammal.OrcaEntity
import dev.hybridlabs.aquatic.entity.mammal.OtterEntity
import net.minecraft.resources.ResourceLocation

class OrcaEntityModel : HybridAquaticDolphinEntityModel<OrcaEntity>("orca") {

    private val BLACK_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/mammal/orca/black_orca.png")
    private val BABY_BLACK_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/mammal/orca/baby_black_orca.png")
    private val GRAY_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/mammal/orca/gray_orca.png")
    private val BABY_GRAY_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/mammal/orca/baby_gray_orca.png")
    private val TAN_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/mammal/orca/tan_orca.png")
    private val BABY_TAN_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/mammal/orca/baby_tan_orca.png")

    override fun getTextureResource(animatable: OrcaEntity): ResourceLocation {
        return if (animatable.isBaby) {
            when (animatable.variant) {
                OrcaEntity.Companion.Type.BLACK -> BABY_BLACK_TEXTURE
                OrcaEntity.Companion.Type.GRAY -> BABY_GRAY_TEXTURE
                OrcaEntity.Companion.Type.TAN -> BABY_TAN_TEXTURE
            }
        } else {
            when (animatable.variant) {
                OrcaEntity.Companion.Type.BLACK -> BLACK_TEXTURE
                OrcaEntity.Companion.Type.GRAY -> GRAY_TEXTURE
                OrcaEntity.Companion.Type.TAN -> TAN_TEXTURE
            }
        }
    }
}