package dev.hybridlabs.aquatic.client.model.entity

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.critter.HybridAquaticCritterEntity
import net.minecraft.util.Identifier
import software.bernie.geckolib.model.GeoModel

abstract class HybridAquaticCritterEntityModel<T: HybridAquaticCritterEntity> (private val id: String) : GeoModel<T>() {
    override fun getModelResource(animatable: T?): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "geo/$id.geo.json")
    }

    override fun getTextureResource(animatable: T?): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "textures/entity/$id.png")
    }

    fun getVariantTexture(variant: String): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "textures/entity/${id}_$variant.png")
    }

    override fun getAnimationResource(animatable: T?): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "animations/$id.animation.json")
    }
}