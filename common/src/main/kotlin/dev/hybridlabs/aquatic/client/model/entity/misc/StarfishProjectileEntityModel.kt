package dev.hybridlabs.aquatic.client.model.entity.misc

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import com.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.entity.projectile.StarfishProjectileEntity
import net.minecraft.resources.Identifier
import com.geckolib.model.GeoModel

class StarfishProjectileEntityModel :
    GeoModel<StarfishProjectileEntity>() {

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return CommonClass.locate("misc/starfish_projectile")
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return CommonClass.locate("textures/entity/misc/starfish_projectile/starfish_projectile.png")
    }

    override fun getAnimationResource(entity: StarfishProjectileEntity): Identifier {
        return CommonClass.locate("entity/misc/starfish_projectile")
    }
}