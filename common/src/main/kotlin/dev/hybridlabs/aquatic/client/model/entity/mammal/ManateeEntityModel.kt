package dev.hybridlabs.aquatic.client.model.entity.mammal

import dev.hybridlabs.aquatic.entity.mammal.ManateeEntity
import net.minecraft.resources.ResourceLocation

class ManateeEntityModel : HASirenianEntityModel<ManateeEntity>("manatee") {

    override fun getTextureResource(animatable: ManateeEntity): ResourceLocation {
        return if (animatable.isBaby) {
            BABY_TEXTURE
        } else when (animatable.variant) {
            ManateeEntity.Companion.Type.PLAIN -> PLAIN_TEXTURE
            ManateeEntity.Companion.Type.MOSSY -> MOSSY_TEXTURE
        }
    }

    companion object {
        private val BABY_TEXTURE =
            ResourceLocation("hybrid_aquatic", "textures/entity/mammal/manatee/baby_manatee.png")

        private val PLAIN_TEXTURE =
            ResourceLocation("hybrid_aquatic", "textures/entity/mammal/manatee/manatee.png")
        private val MOSSY_TEXTURE =
            ResourceLocation("hybrid_aquatic", "textures/entity/mammal/manatee/mossy_manatee.png")
    }
}