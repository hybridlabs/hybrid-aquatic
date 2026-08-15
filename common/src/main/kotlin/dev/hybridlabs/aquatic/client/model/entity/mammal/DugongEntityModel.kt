package dev.hybridlabs.aquatic.client.model.entity.mammal

import dev.hybridlabs.aquatic.entity.mammal.DugongEntity
import dev.hybridlabs.hapi.client.model.entity.aquatic.BaseSirenianEntityModel
import net.minecraft.resources.ResourceLocation

class DugongEntityModel : BaseSirenianEntityModel<DugongEntity>("hybrid_aquatic", "dugong") {

    override fun getTextureResource(animatable: DugongEntity): ResourceLocation {
        return if (animatable.isBaby) {
            BABY_TEXTURE
        } else when (animatable.variant) {
            DugongEntity.Companion.Type.PLAIN -> PLAIN_TEXTURE
            DugongEntity.Companion.Type.MOSSY -> MOSSY_TEXTURE
        }
    }

    companion object {
        private val BABY_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/mammal/dugong/baby_dugong.png")

        private val PLAIN_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/mammal/dugong/dugong.png")
        private val MOSSY_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/mammal/dugong/mossy_dugong.png")
    }
}