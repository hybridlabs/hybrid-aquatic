package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.ClownfishEntity
import net.minecraft.util.Identifier

class ClownfishEntityModel : HybridAquaticFishEntityModel<ClownfishEntity>("clownfish") {

    private val OCELLARIS_TEXTURE = Identifier.of("hybrid-aquatic", "textures/entity/fish/clownfish/clownfish_ocellaris.png")
    private val CLARKII_TEXTURE = Identifier.of("hybrid-aquatic", "textures/entity/fish/clownfish/clownfish_clarkii.png")
    private val TOMATO_TEXTURE = Identifier.of("hybrid-aquatic", "textures/entity/fish/clownfish/clownfish_tomato.png")
    private val CINNAMON_TEXTURE = Identifier.of("hybrid-aquatic", "textures/entity/fish/clownfish/clownfish_cinnamon.png")
    private val WHITEBAND_TEXTURE = Identifier.of("hybrid-aquatic", "textures/entity/fish/clownfish/clownfish_whiteband.png")
    private val PERCULA_TEXTURE = Identifier.of("hybrid-aquatic", "textures/entity/fish/clownfish/clownfish_percula.png")
    private val PINK_SKUNK_TEXTURE = Identifier.of("hybrid-aquatic", "textures/entity/fish/clownfish/clownfish_pink_skunk.png")
    private val ORANGE_SKUNK_TEXTURE = Identifier.of("hybrid-aquatic", "textures/entity/fish/clownfish/clownfish_orange_skunk.png")

    private val SMALL_CLOWNFISH_MODEL = Identifier.of("hybrid-aquatic", "geo/fish/clownfish/clownfish_small.geo.json")
    private val BIG_CLOWNFISH_MODEL = Identifier.of("hybrid-aquatic", "geo/fish/clownfish/clownfish_big.geo.json")

    override fun getTextureResource(animatable: ClownfishEntity): Identifier {
        return when (animatable.variant) {
            ClownfishEntity.Companion.Type.OCELLARIS -> OCELLARIS_TEXTURE
            ClownfishEntity.Companion.Type.CLARKII -> CLARKII_TEXTURE
            ClownfishEntity.Companion.Type.TOMATO -> TOMATO_TEXTURE
            ClownfishEntity.Companion.Type.CINNAMON -> CINNAMON_TEXTURE
            ClownfishEntity.Companion.Type.PINK_SKUNK -> PINK_SKUNK_TEXTURE
            ClownfishEntity.Companion.Type.ORANGE_SKUNK -> ORANGE_SKUNK_TEXTURE
            ClownfishEntity.Companion.Type.WHITEBAND -> WHITEBAND_TEXTURE
            ClownfishEntity.Companion.Type.PERCULA -> PERCULA_TEXTURE
        }
    }

    override fun getModelResource(animatable: ClownfishEntity): Identifier {
        return when (animatable.variant) {
            ClownfishEntity.Companion.Type.OCELLARIS -> BIG_CLOWNFISH_MODEL
            ClownfishEntity.Companion.Type.PERCULA -> BIG_CLOWNFISH_MODEL
            ClownfishEntity.Companion.Type.WHITEBAND -> BIG_CLOWNFISH_MODEL
            else -> SMALL_CLOWNFISH_MODEL
        }
    }
}
