package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.SeahorseEntity
import net.minecraft.resources.ResourceLocation

class SeahorseEntityModel : HybridAquaticFishEntityModel<SeahorseEntity>("seahorse") {

    private val COMMON_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/seahorse/seahorse_common.png")
    private val PYGMY_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/seahorse/seahorse_pygmy.png")
    private val THORNY_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/seahorse/seahorse_thorny.png")
    private val BIG_BELLY_TEXTURE = ResourceLocation("hybrid-aquatic", "textures/entity/fish/seahorse/seahorse_big_belly.png")

    private val COMMON_MODEL = ResourceLocation("hybrid-aquatic", "geo/fish/seahorse/seahorse_common.geo.json")
    private val PYGMY_MODEL = ResourceLocation("hybrid-aquatic", "geo/fish/seahorse/seahorse_pygmy.geo.json")
    private val THORNY_MODEL = ResourceLocation("hybrid-aquatic", "geo/fish/seahorse/seahorse_thorny.geo.json")
    private val BIG_BELLY_MODEL = ResourceLocation("hybrid-aquatic", "geo/fish/seahorse/seahorse_big_belly.geo.json")

    override fun getTextureResource(animatable: SeahorseEntity): ResourceLocation {
        return when (animatable.variant) {
            SeahorseEntity.Companion.Type.COMMON -> COMMON_TEXTURE
            SeahorseEntity.Companion.Type.PYGMY -> PYGMY_TEXTURE
            SeahorseEntity.Companion.Type.THORNY -> THORNY_TEXTURE
            SeahorseEntity.Companion.Type.BIG_BELLY -> BIG_BELLY_TEXTURE
        }
    }

    override fun getModelResource(animatable: SeahorseEntity): ResourceLocation {
        return when (animatable.variant) {
            SeahorseEntity.Companion.Type.COMMON -> COMMON_MODEL
            SeahorseEntity.Companion.Type.PYGMY -> PYGMY_MODEL
            SeahorseEntity.Companion.Type.THORNY -> THORNY_MODEL
            SeahorseEntity.Companion.Type.BIG_BELLY -> BIG_BELLY_MODEL
        }
    }
}