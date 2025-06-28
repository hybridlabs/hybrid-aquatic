package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.ClownfishEntity
import net.minecraft.util.Identifier

class ClownfishEntityModel : HybridAquaticFishEntityModel<ClownfishEntity>("clownfish") {

    private val OCELLARIS_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/clownfish/clownfish_ocellaris.png")
    private val CLARKII_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/clownfish/clownfish_clarkii.png")
    private val TOMATO_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/clownfish/clownfish_tomato.png")
    private val CINNAMON_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/clownfish/clownfish_cinnamon.png")

    private val SMALL_CLOWNFISH_MODEL = Identifier("hybrid-aquatic", "geo/fish/clownfish/clownfish_small.geo.json")
    private val BIG_CLOWNFISH_MODEL = Identifier("hybrid-aquatic", "geo/fish/clownfish/clownfish_big.geo.json")

    override fun getTextureResource(animatable: ClownfishEntity): Identifier {
        return when (animatable.variant) {
            ClownfishEntity.Type.OCELLARIS -> OCELLARIS_TEXTURE
            ClownfishEntity.Type.CLARKII -> CLARKII_TEXTURE
            ClownfishEntity.Type.TOMATO -> TOMATO_TEXTURE
            ClownfishEntity.Type.CINNAMON -> CINNAMON_TEXTURE
        }
    }

    override fun getModelResource(animatable: ClownfishEntity): Identifier {
        return when (animatable.variant) {
            ClownfishEntity.Type.OCELLARIS -> BIG_CLOWNFISH_MODEL
            else -> SMALL_CLOWNFISH_MODEL
        }
    }
}
