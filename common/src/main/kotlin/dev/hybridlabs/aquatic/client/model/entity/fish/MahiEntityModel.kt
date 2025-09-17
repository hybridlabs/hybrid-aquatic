package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.MahiEntity
import net.minecraft.resources.ResourceLocation

class MahiEntityModel : HybridAquaticFishEntityModel<MahiEntity>("mahi") {

    private val MAHI_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/mahi/mahi_mahi.png")
    private val POMPANO_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/mahi/mahi_pompano.png")

    private val MAHI_MODEL = ResourceLocation("hybrid-aquatic", "geo/fish/mahi/mahi_mahi.geo.json")
    private val POMPANO_MODEL = ResourceLocation("hybrid-aquatic", "geo/fish/mahi/mahi_pompano.geo.json")

    override fun getTextureResource(animatable: MahiEntity): ResourceLocation {
        return when (animatable.variant) {
            MahiEntity.Companion.Type.MAHI -> MAHI_TEXTURE
            MahiEntity.Companion.Type.POMPANO -> POMPANO_TEXTURE
        }
    }

    override fun getModelResource(animatable: MahiEntity): ResourceLocation {
        return when (animatable.variant) {
            MahiEntity.Companion.Type.MAHI -> MAHI_MODEL
            MahiEntity.Companion.Type.POMPANO -> POMPANO_MODEL
        }
    }
}