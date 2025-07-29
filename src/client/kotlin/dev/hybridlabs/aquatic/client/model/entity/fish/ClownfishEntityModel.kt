package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.ClownfishEntity
import net.minecraft.util.Identifier

class ClownfishEntityModel : HybridAquaticFishEntityModel<ClownfishEntity>("clownfish") {

    private val OCELLARIS_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/clownfish/clownfish_ocellaris.png")
    private val CLARKII_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/clownfish/clownfish_clarkii.png")
    private val TOMATO_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/clownfish/clownfish_tomato.png")
    private val CINNAMON_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/clownfish/clownfish_cinnamon.png")
    private val WHITEBAND_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/clownfish/clownfish_whiteband.png")
    private val PERCULA_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/clownfish/clownfish_percula.png")
    private val PINK_SKUNK_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/clownfish/clownfish_pink_skunk.png")
    private val ORANGE_SKUNK_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/clownfish/clownfish_orange_skunk.png")

    private val SMALL_CLOWNFISH_MODEL = Identifier("hybrid-aquatic", "geo/fish/clownfish/clownfish_small.geo.json")
    private val BIG_CLOWNFISH_MODEL = Identifier("hybrid-aquatic", "geo/fish/clownfish/clownfish_big.geo.json")

    override fun getTextureResource(animatable: ClownfishEntity): Identifier {
        return when (animatable.variant) {
            ClownfishEntity.Type.OCELLARIS -> OCELLARIS_TEXTURE
            ClownfishEntity.Type.CLARKII -> CLARKII_TEXTURE
            ClownfishEntity.Type.TOMATO -> TOMATO_TEXTURE
            ClownfishEntity.Type.CINNAMON -> CINNAMON_TEXTURE
            ClownfishEntity.Type.PINK_SKUNK -> PINK_SKUNK_TEXTURE
            ClownfishEntity.Type.ORANGE_SKUNK -> ORANGE_SKUNK_TEXTURE
            ClownfishEntity.Type.WHITEBAND -> WHITEBAND_TEXTURE
            ClownfishEntity.Type.PERCULA -> PERCULA_TEXTURE
        }
    }

    override fun getModelResource(animatable: ClownfishEntity): Identifier {
        return when (animatable.variant) {
            ClownfishEntity.Type.OCELLARIS -> BIG_CLOWNFISH_MODEL
            ClownfishEntity.Type.PERCULA -> BIG_CLOWNFISH_MODEL
            ClownfishEntity.Type.WHITEBAND -> BIG_CLOWNFISH_MODEL
            else -> SMALL_CLOWNFISH_MODEL
        }
    }
}
