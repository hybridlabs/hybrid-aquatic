package dev.hybridlabs.aquatic.client.model.entity.mammal

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.entity.mammal.OrcaEntity
import net.minecraft.resources.ResourceLocation

class OrcaEntityModel : HybridAquaticDolphinEntityModel<OrcaEntity>("orca") {

    override fun getTextureResource(animatable: OrcaEntity): ResourceLocation {
        return if (animatable.isBaby) {
            when (animatable.variant) {
                OrcaEntity.Companion.Type.BLACK -> CommonClass.locate("textures/entity/mammal/orca/baby_black_orca.png")
                OrcaEntity.Companion.Type.NAVY ->  CommonClass.locate("textures/entity/mammal/orca/baby_navy_orca.png")
                OrcaEntity.Companion.Type.GRAY ->  CommonClass.locate("textures/entity/mammal/orca/baby_gray_orca.png")
                OrcaEntity.Companion.Type.PURPLE ->  CommonClass.locate("textures/entity/mammal/orca/baby_purple_orca.png")
                OrcaEntity.Companion.Type.TAN ->  CommonClass.locate("textures/entity/mammal/orca/baby_tan_orca.png")
                OrcaEntity.Companion.Type.BROWN ->  CommonClass.locate("textures/entity/mammal/orca/baby_brown_orca.png")
            }
        } else {
            when (animatable.variant) {
                OrcaEntity.Companion.Type.BLACK -> CommonClass.locate("textures/entity/mammal/orca/black_orca.png")
                OrcaEntity.Companion.Type.NAVY ->  CommonClass.locate("textures/entity/mammal/orca/navy_orca.png")
                OrcaEntity.Companion.Type.GRAY ->  CommonClass.locate("textures/entity/mammal/orca/gray_orca.png")
                OrcaEntity.Companion.Type.PURPLE ->  CommonClass.locate("textures/entity/mammal/orca/purple_orca.png")
                OrcaEntity.Companion.Type.TAN ->  CommonClass.locate("textures/entity/mammal/orca/tan_orca.png")
                OrcaEntity.Companion.Type.BROWN ->  CommonClass.locate("textures/entity/mammal/orca/brown_orca.png")
            }
        }
    }

    fun getSaddleTextureResource(animatable: OrcaEntity, layer: String): ResourceLocation {
        return if (animatable.isBaby) {
            CommonClass.locate("textures/entity/mammal/orca/saddle/baby_orca_$layer.png")
        } else {
            CommonClass.locate("textures/entity/mammal/orca/saddle/orca_$layer.png")
        }
    }

    fun getEyeTextureResource(animatable: OrcaEntity, layer: String): ResourceLocation {
        return if (animatable.isBaby) {
            CommonClass.locate("textures/entity/mammal/orca/eye/baby_orca_$layer.png")
        } else {
            CommonClass.locate("textures/entity/mammal/orca/eye/orca_$layer.png")
        }
    }
}