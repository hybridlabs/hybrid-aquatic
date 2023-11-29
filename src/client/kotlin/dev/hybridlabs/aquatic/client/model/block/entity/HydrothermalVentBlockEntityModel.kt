package dev.hybridlabs.aquatic.client.model.block.entity

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.entity.HydrothermalVentBlockEntity
import net.minecraft.util.Identifier
import software.bernie.geckolib.model.GeoModel

class HydrothermalVentBlockEntityModel: GeoModel<HydrothermalVentBlockEntity>() {
    override fun getAnimationResource(entity: HydrothermalVentBlockEntity): Identifier {
        return ANIMATION_LOCATION
    }

    override fun getModelResource(animatable: HydrothermalVentBlockEntity): Identifier {
        return MODEL_LOCATION
    }

    override fun getTextureResource(entity: HydrothermalVentBlockEntity): Identifier {
        return TEXTURE_LOCATION
    }

    companion object {
        val ANIMATION_LOCATION = Identifier(HybridAquatic.MOD_ID, "animations/hydrothermal_vent.animation.json")
        val MODEL_LOCATION = Identifier(HybridAquatic.MOD_ID, "geo/hydrothermal_vent.geo.json")
        val TEXTURE_LOCATION = Identifier(HybridAquatic.MOD_ID, "textures/block/hydrothermal_vent.png")
    }
}