package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.TunaEntity
import net.minecraft.util.Identifier

class TunaEntityModel : HybridAquaticFishEntityModel<TunaEntity>("tuna") {

    private val YELLOWFIN_TEXTURE = Identifier.of("hybrid-aquatic", "textures/entity/fish/tuna/tuna_yellowfin.png")
    private val BLUEFIN_TEXTURE = Identifier.of("hybrid-aquatic", "textures/entity/fish/tuna/tuna_bluefin.png")

    private val YELLOWFIN_MODEL = Identifier.of("hybrid-aquatic", "geo/fish/tuna/tuna_yellowfin.geo.json")
    private val BLUEFIN_MODEL = Identifier.of("hybrid-aquatic", "geo/fish/tuna/tuna_bluefin.geo.json")

    override fun getTextureResource(animatable: TunaEntity): Identifier {
        return when (animatable.variant) {
            TunaEntity.Companion.Type.YELLOWFIN -> YELLOWFIN_TEXTURE
            TunaEntity.Companion.Type.BLUEFIN -> BLUEFIN_TEXTURE
        }
    }

    override fun getModelResource(animatable: TunaEntity): Identifier {
        return when (animatable.variant) {
            TunaEntity.Companion.Type.YELLOWFIN -> YELLOWFIN_MODEL
            TunaEntity.Companion.Type.BLUEFIN -> BLUEFIN_MODEL
            else -> YELLOWFIN_MODEL
        }
    }
}