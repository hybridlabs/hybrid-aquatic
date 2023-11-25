package dev.hybridlabs.aquatic.client.model.block.entity

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.entity.BuoyBlockEntity
import dev.hybridlabs.aquatic.block.entity.CrabPotBlockEntity
import net.minecraft.util.Identifier
import software.bernie.geckolib.model.GeoModel

class CrabPotBlockEntityModel: GeoModel<CrabPotBlockEntity>() {
    override fun getAnimationResource(entity: CrabPotBlockEntity): Identifier {
        return ANIMATION_LOCATION
    }

    override fun getModelResource(animatable: CrabPotBlockEntity): Identifier {
        return MODEL_LOCATION
    }

    override fun getTextureResource(entity: CrabPotBlockEntity): Identifier {
        return TEXTURE_LOCATION
    }

    companion object {
        val ANIMATION_LOCATION = Identifier(HybridAquatic.MOD_ID, "animations/crab_pot.animation.json")
        val MODEL_LOCATION = Identifier(HybridAquatic.MOD_ID, "geo/entity/block/crab_pot.geo.json")
        val TEXTURE_LOCATION = Identifier(HybridAquatic.MOD_ID, "textures/block/crab_pot.png")
    }
}