package dev.hybridlabs.aquatic.client.model.entity.misc

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.entity.projectile.StarfishProjectileEntity
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.model.GeoModel

class StarfishProjectileEntityModel :
    GeoModel<StarfishProjectileEntity>() {

    override fun getModelResource(entity: StarfishProjectileEntity): ResourceLocation {
        return CommonClass.locate("geo/misc/starfish_projectile.geo.json")
    }

    override fun getTextureResource(entity: StarfishProjectileEntity): ResourceLocation {
        return CommonClass.locate("textures/entity/misc/starfish_projectile/starfish_projectile.png")
    }

    override fun getAnimationResource(entity: StarfishProjectileEntity): ResourceLocation {
        return CommonClass.locate("animations/entity/misc/starfish_projectile.animation.json")
    }
}