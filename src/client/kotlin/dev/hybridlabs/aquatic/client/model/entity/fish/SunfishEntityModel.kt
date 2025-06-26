package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.SunfishEntity
import net.minecraft.util.Identifier

class SunfishEntityModel : HybridAquaticFishEntityModel<SunfishEntity>("sunfish") {

    private val OCEAN_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/sunfish/sunfish_ocean.png")
    private val HOODWINKER_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/sunfish/sunfish_hoodwinker.png")
    private val SHARPTAIL_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/sunfish/sunfish_sharptail.png")
    private val GIANT_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/sunfish/sunfish_giant.png")

    private val OCEAN_MODEL = Identifier("hybrid-aquatic", "geo/fish/sunfish/sunfish_ocean.geo.json")
    private val HOODWINKER_MODEL = Identifier("hybrid-aquatic", "geo/fish/sunfish/sunfish_hoodwinker.geo.json")
    private val SHARPTAIL_MODEL = Identifier("hybrid-aquatic", "geo/fish/sunfish/sunfish_sharptail.geo.json")
    private val GIANT_MODEL = Identifier("hybrid-aquatic", "geo/fish/sunfish/sunfish_giant.geo.json")

    override fun getTextureResource(animatable: SunfishEntity): Identifier {
        return when (animatable.variant) {
            SunfishEntity.Type.OCEAN -> OCEAN_TEXTURE
            SunfishEntity.Type.HOODWINKER -> HOODWINKER_TEXTURE
            SunfishEntity.Type.SHARPTAIL -> SHARPTAIL_TEXTURE
            SunfishEntity.Type.GIANT -> GIANT_TEXTURE
        }
    }

    override fun getModelResource(animatable: SunfishEntity): Identifier {
        return when (animatable.variant) {
            SunfishEntity.Type.OCEAN -> OCEAN_MODEL
            SunfishEntity.Type.HOODWINKER -> HOODWINKER_MODEL
            SunfishEntity.Type.SHARPTAIL -> SHARPTAIL_MODEL
            SunfishEntity.Type.GIANT -> GIANT_MODEL
            else -> OCEAN_MODEL
        }
    }
}
