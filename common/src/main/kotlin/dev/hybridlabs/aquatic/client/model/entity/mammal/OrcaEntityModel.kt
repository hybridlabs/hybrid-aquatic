package dev.hybridlabs.aquatic.client.model.entity.mammal

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.entity.mammal.OrcaEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseDolphinEntityModel
import net.minecraft.resources.ResourceLocation

class OrcaEntityModel : BaseDolphinEntityModel<OrcaEntity>("hybrid_aquatic", "orca") {

    companion object {
        private val BLACK_ORCA_TEXTURE = ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/mammal/orca/black_orca.png")
        private val BABY_BLACK_ORCA_TEXTURE = ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/mammal/orca/baby_black_orca.png")

        private val NAVY_ORCA_TEXTURE = ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/mammal/orca/navy_orca.png")
        private val BABY_NAVY_ORCA_TEXTURE = ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/mammal/orca/baby_navy_orca.png")

        private val GRAY_ORCA_TEXTURE = ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/mammal/orca/gray_orca.png")
        private val BABY_GRAY_ORCA_TEXTURE = ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/mammal/orca/baby_gray_orca.png")

        private val PURPLE_ORCA_TEXTURE = ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/mammal/orca/purple_orca.png")
        private val BABY_PURPLE_ORCA_TEXTURE = ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/mammal/orca/baby_purple_orca.png")

        private val TAN_ORCA_TEXTURE = ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/mammal/orca/tan_orca.png")
        private val BABY_TAN_ORCA_TEXTURE = ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/mammal/orca/baby_tan_orca.png")

        private val BROWN_ORCA_TEXTURE = ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/mammal/orca/brown_orca.png")
        private val BABY_BROWN_ORCA_TEXTURE = ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/mammal/orca/baby_brown_orca.png")
    }

    override fun getTextureResource(animatable: OrcaEntity): ResourceLocation {
        return when (animatable.variant) {
            OrcaEntity.Companion.Type.BLACK ->
                if (animatable.isBaby) BABY_BLACK_ORCA_TEXTURE else BLACK_ORCA_TEXTURE

            OrcaEntity.Companion.Type.NAVY ->
                if (animatable.isBaby) BABY_NAVY_ORCA_TEXTURE else NAVY_ORCA_TEXTURE

            OrcaEntity.Companion.Type.GRAY ->
                if (animatable.isBaby) BABY_GRAY_ORCA_TEXTURE else GRAY_ORCA_TEXTURE

            OrcaEntity.Companion.Type.PURPLE ->
                if (animatable.isBaby) BABY_PURPLE_ORCA_TEXTURE else PURPLE_ORCA_TEXTURE

            OrcaEntity.Companion.Type.TAN ->
                if (animatable.isBaby) BABY_TAN_ORCA_TEXTURE else TAN_ORCA_TEXTURE

            OrcaEntity.Companion.Type.BROWN ->
                if (animatable.isBaby) BABY_BROWN_ORCA_TEXTURE else BROWN_ORCA_TEXTURE
        }
    }

    fun getSaddleTextureResource(animatable: OrcaEntity, layer: String): ResourceLocation {
        return if (animatable.isBaby) {
            CommonClass.locate("textures/entity/mammal/orca/saddle/baby_orca_$layer.png")
        } else {
            CommonClass.locate("textures/entity/mammal/orca/saddle/orca_$layer.png")
        }
    }

    fun getEyeSpotTextureResource(animatable: OrcaEntity, layer: String): ResourceLocation {
        return if (animatable.isBaby) {
            CommonClass.locate("textures/entity/mammal/orca/eye/baby_orca_$layer.png")
        } else {
            CommonClass.locate("textures/entity/mammal/orca/eye/orca_$layer.png")
        }
    }
}
