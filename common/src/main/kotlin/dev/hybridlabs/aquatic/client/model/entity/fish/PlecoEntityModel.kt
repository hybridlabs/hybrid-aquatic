package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.PlecoEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class PlecoEntityModel : HAFishEntityModel<PlecoEntity>("pleco") {
    override fun getRenderType(animatable: PlecoEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }

    override fun getTextureResource(animatable: PlecoEntity): ResourceLocation {
        return when (animatable.variant) {
            PlecoEntity.Companion.Type.COMMON -> COMMON_PLECO_TEXTURE
            PlecoEntity.Companion.Type.BRISTLENOSE -> BRISTLENOSE_PLECO_TEXTURE
        }
    }

    override fun getModelResource(animatable: PlecoEntity): ResourceLocation {
        return when (animatable.variant) {
            PlecoEntity.Companion.Type.COMMON -> COMMON_PLECO_MODEL
            PlecoEntity.Companion.Type.BRISTLENOSE -> BRISTLENOSE_PLECO_MODEL
        }
    }

    companion object {
        private val COMMON_PLECO_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/pleco/common_pleco.png")
        private val BRISTLENOSE_PLECO_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/pleco/bristlenose_pleco.png")

        private val COMMON_PLECO_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/fish/pleco/common_pleco.geo.json")
        private val BRISTLENOSE_PLECO_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/fish/pleco/bristlenose_pleco.geo.json")
    }
}
