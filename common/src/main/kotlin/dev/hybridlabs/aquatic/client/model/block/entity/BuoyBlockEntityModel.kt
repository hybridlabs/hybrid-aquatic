package dev.hybridlabs.aquatic.client.model.block.entity

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import software.bernie.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.block.entity.BuoyBlockEntity
import net.minecraft.resources.Identifier
import software.bernie.geckolib.model.GeoModel

@Suppress("OVERRIDE_DEPRECATION")
class BuoyBlockEntityModel : GeoModel<BuoyBlockEntity>() {
    override fun getAnimationResource(entity: BuoyBlockEntity): Identifier {
        return ANIMATION_LOCATION
    }

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return MODEL_LOCATION
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return TEXTURE_LOCATION
    }

    companion object {
        val ANIMATION_LOCATION: Identifier = CommonClass.locate("buoy")
        val MODEL_LOCATION: Identifier = CommonClass.locate("buoy")
        val TEXTURE_LOCATION: Identifier = CommonClass.locate("textures/block/buoy.png")
    }
}