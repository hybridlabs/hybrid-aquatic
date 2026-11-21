package dev.hybridlabs.aquatic.client.model.entity.misc

import dev.hybridlabs.aquatic.client.model.HADataTickets
import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import software.bernie.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.entity.misc.ArgonautEntity
import net.minecraft.client.model.geom.PartNames
import net.minecraft.client.renderer.rendertype.RenderType
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.resources.Identifier
import net.minecraft.util.Mth
import software.bernie.geckolib.animation.state.AnimationTest
import software.bernie.geckolib.model.GeoModel

@Suppress("OVERRIDE_DEPRECATION")
class ArgonautEntityModel<T : ArgonautEntity>() :
    GeoModel<T>() {
    override fun addAdditionalStateData(
        animatable: T,
        relatedObject: Any?,
        renderState: GeoRenderState
    ) {
        renderState.addGeckolibData(HADataTickets.GLOWING, animatable.isGlowing())
    }

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return CommonClass.locate("misc/argonaut")
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return if ((renderState.getGeckolibData(HADataTickets.GLOWING) == true)) {
            CommonClass.locate("textures/entity/misc/argonaut/glowing_argonaut.png")
        } else {
            CommonClass.locate("textures/entity/misc/argonaut/argonaut.png")
        }
    }

    fun getVisorTextureResource(): Identifier {
        return CommonClass.locate("textures/entity/misc/argonaut/argonaut_visor.png")
    }

    fun getShellTextureResource(animatable: T): Identifier {
        val textureName = when (val color = animatable.getShellColor()) {
            ArgonautEntity.ShellColor.NONE -> "argonaut_shell"
            else -> color.name.lowercase() + "_argonaut_shell"
        }
        return CommonClass.locate("textures/entity/misc/argonaut/shell/$textureName.png")
    }

    fun getSailTextureResource(animatable: T): Identifier {
        val textureName = when (val color = animatable.getSailColor()) {
            ArgonautEntity.SailColor.NONE -> "argonaut_sail"
            else -> color.name.lowercase() + "_argonaut_sail"
        }
        return CommonClass.locate("textures/entity/misc/argonaut/sail/$textureName.png")
    }

    override fun getAnimationResource(animatable: T): Identifier {
        return CommonClass.locate("entity/misc/argonaut")
    }

}