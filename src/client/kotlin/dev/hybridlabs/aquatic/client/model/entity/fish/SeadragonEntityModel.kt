package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.SeadragonEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class SeadragonEntityModel : HybridAquaticFishEntityModel<SeadragonEntity>("seadragon") {
    override fun getRenderType(animatable: SeadragonEntity, texture: Identifier): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }

    private val LEAFY_TEXTURE = Identifier("hybrid-aquatic", "textures/entity/fish/seadragon/leafy_seadragon.png")

    override fun getTextureResource(animatable: SeadragonEntity): Identifier {
        return when (animatable.variant) {
            SeadragonEntity.Type.LEAFY -> LEAFY_TEXTURE
        }
    }
}