package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.CepheidaeJellyfishEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class CepheidaeJellyfishEntityModel : HybridAquaticJellyfishEntityModel<CepheidaeJellyfishEntity>("cepheidae_jellyfish") {
    override fun getRenderType(animatable: CepheidaeJellyfishEntity, texture: Identifier): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }

    private val CAULIFLOWER_TEXTURE = Identifier.of("hybrid-aquatic", "textures/entity/jellyfish/cepheidae_jellyfish/cauliflower_jellyfish.png")
    private val FRIED_EGG_TEXTURE = Identifier.of("hybrid-aquatic", "textures/entity/jellyfish/cepheidae_jellyfish/fried_egg_jellyfish.png")

    private val CAULIFLOWER_MODEL = Identifier.of("hybrid-aquatic", "geo/jellyfish/cepheidae_jellyfish/cauliflower_jellyfish.geo.json")
    private val FRIED_EGG_MODEL = Identifier.of("hybrid-aquatic", "geo/jellyfish/cepheidae_jellyfish/fried_egg_jellyfish.geo.json")

    override fun getTextureResource(animatable: CepheidaeJellyfishEntity): Identifier {
        return when (animatable.variant) {
            CepheidaeJellyfishEntity.Companion.Type.CAULIFLOWER -> CAULIFLOWER_TEXTURE
            CepheidaeJellyfishEntity.Companion.Type.FRIED_EGG -> FRIED_EGG_TEXTURE
        }
    }

    override fun getModelResource(animatable: CepheidaeJellyfishEntity): Identifier {
        return when (animatable.variant) {
            CepheidaeJellyfishEntity.Companion.Type.CAULIFLOWER -> CAULIFLOWER_MODEL
            CepheidaeJellyfishEntity.Companion.Type.FRIED_EGG -> FRIED_EGG_MODEL
        }
    }
}