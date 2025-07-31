package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.MahiEntity
import net.minecraft.util.Identifier

class MahiEntityModel : HybridAquaticFishEntityModel<MahiEntity>("mahi") {

    private val MAHI_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/mahi/mahi_mahi.png")
    private val POMPANO_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/mahi/mahi_pompano.png")

    private val MAHI_MODEL = Identifier("hybrid-aquatic", "geo/fish/mahi/mahi_mahi.geo.json")
    private val POMPANO_MODEL = Identifier("hybrid-aquatic", "geo/fish/mahi/mahi_pompano.geo.json")

    override fun getTextureResource(animatable: MahiEntity): Identifier {
        return when (animatable.variant) {
            MahiEntity.Companion.Type.MAHI -> MAHI_TEXTURE
            MahiEntity.Companion.Type.POMPANO -> POMPANO_TEXTURE
        }
    }

    override fun getModelResource(animatable: MahiEntity): Identifier {
        return when (animatable.variant) {
            MahiEntity.Companion.Type.MAHI -> MAHI_MODEL
            MahiEntity.Companion.Type.POMPANO -> POMPANO_MODEL
            else -> MAHI_MODEL
        }
    }
}