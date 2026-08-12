package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.CepheidaeJellyfishEntity
import dev.hybridlabs.hapi.client.model.entity.BaseJellyfishEntityModel
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

class CepheidaeJellyfishEntityModel : BaseJellyfishEntityModel<CepheidaeJellyfishEntity>("hybrid_aquatic", "cepheidae_jellyfish") {
    override fun getRenderType(animatable: CepheidaeJellyfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }

    companion object {
        private val CAULIFLOWER_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/jellyfish/cepheidae_jellyfish/cauliflower_jellyfish.png")
        private val FRIED_EGG_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/jellyfish/cepheidae_jellyfish/fried_egg_jellyfish.png")

        private val CAULIFLOWER_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/jellyfish/cepheidae_jellyfish/cauliflower_jellyfish.geo.json")
        private val FRIED_EGG_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/jellyfish/cepheidae_jellyfish/fried_egg_jellyfish.geo.json")
    }

    override fun getTextureResource(animatable: CepheidaeJellyfishEntity): ResourceLocation {
        return when (animatable.variant) {
            CepheidaeJellyfishEntity.Companion.Type.CAULIFLOWER -> CAULIFLOWER_TEXTURE
            CepheidaeJellyfishEntity.Companion.Type.FRIED_EGG -> FRIED_EGG_TEXTURE
        }
    }

    override fun getModelResource(animatable: CepheidaeJellyfishEntity): ResourceLocation {
        return when (animatable.variant) {
            CepheidaeJellyfishEntity.Companion.Type.CAULIFLOWER -> CAULIFLOWER_MODEL
            CepheidaeJellyfishEntity.Companion.Type.FRIED_EGG -> FRIED_EGG_MODEL
        }
    }
}
