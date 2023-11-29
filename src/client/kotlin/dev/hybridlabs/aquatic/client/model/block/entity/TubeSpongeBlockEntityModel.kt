package dev.hybridlabs.aquatic.client.model.block.entity

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.entity.TubeSpongeBlockEntity
import net.minecraft.util.Identifier
import software.bernie.geckolib.model.GeoModel

class TubeSpongeBlockEntityModel : GeoModel<TubeSpongeBlockEntity>() {
    override fun getAnimationResource(entity: TubeSpongeBlockEntity): Identifier {
        return ANIMATION_LOCATION
    }

    override fun getModelResource(animatable: TubeSpongeBlockEntity): Identifier {
        return MODEL_LOCATION
    }

    override fun getTextureResource(entity: TubeSpongeBlockEntity): Identifier {
        return TEXTURE_LOCATION
    }

    companion object {
        val ANIMATION_LOCATION = Identifier(HybridAquatic.MOD_ID, "animations/tube_sponge.animation.json")
        val MODEL_LOCATION = Identifier(HybridAquatic.MOD_ID, "geo/tube_sponge.geo.json")
        val TEXTURE_LOCATION = Identifier(HybridAquatic.MOD_ID, "textures/block/tube_sponge.png")
    }
}
