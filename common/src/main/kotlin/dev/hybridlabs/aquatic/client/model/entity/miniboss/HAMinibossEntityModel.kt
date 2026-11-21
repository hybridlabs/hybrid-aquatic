package dev.hybridlabs.aquatic.client.model.entity.miniboss

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import com.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.entity.base.HAMinibossEntity
import net.minecraft.resources.Identifier
import com.geckolib.model.GeoModel

@Suppress("OVERRIDE_DEPRECATION")
abstract class HAMinibossEntityModel<T : HAMinibossEntity>(private val id: String) :
    GeoModel<T>() {

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return CommonClass.locate("miniboss/${id}/$id")
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return CommonClass.locate("textures/entity/miniboss/${id}/$id.png")
    }

    override fun getAnimationResource(animatable: T): Identifier {
        return CommonClass.locate("entity/miniboss/${id}/$id")
    }
}