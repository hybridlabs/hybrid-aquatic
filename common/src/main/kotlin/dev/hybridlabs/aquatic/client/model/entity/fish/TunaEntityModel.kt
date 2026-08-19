package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.TunaEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseFishEntityModel
import net.minecraft.resources.ResourceLocation

class TunaEntityModel : BaseFishEntityModel<TunaEntity>("hybrid_aquatic", "tuna") {

    override fun getTextureResource(animatable: TunaEntity): ResourceLocation {
        return when (animatable.variant) {
            TunaEntity.Companion.Type.YELLOWFIN -> YELLOWFIN_TEXTURE
            TunaEntity.Companion.Type.BLUEFIN -> BLUEFIN_TEXTURE
        }
    }

    override fun getModelResource(animatable: TunaEntity): ResourceLocation {
        return when (animatable.variant) {
            TunaEntity.Companion.Type.YELLOWFIN -> YELLOWFIN_MODEL
            TunaEntity.Companion.Type.BLUEFIN -> BLUEFIN_MODEL
        }
    }

    companion object {
        private val YELLOWFIN_TEXTURE =
            ResourceLocation("hybrid_aquatic", "textures/entity/fish/tuna/tuna_yellowfin.png")
        private val BLUEFIN_TEXTURE =
            ResourceLocation("hybrid_aquatic", "textures/entity/fish/tuna/tuna_bluefin.png")

        private val YELLOWFIN_MODEL =
            ResourceLocation("hybrid_aquatic", "geo/fish/tuna/tuna_yellowfin.geo.json")
        private val BLUEFIN_MODEL =
            ResourceLocation("hybrid_aquatic", "geo/fish/tuna/tuna_bluefin.geo.json")
    }
}
