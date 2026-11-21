package dev.hybridlabs.aquatic.client.render.entity.misc.layer

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.client.model.entity.misc.ArgonautEntityModel
import dev.hybridlabs.aquatic.client.render.entity.misc.ArgonautEntityRenderer
import dev.hybridlabs.aquatic.entity.misc.ArgonautEntity
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.client.renderer.rendertype.RenderType
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.resources.Identifier
import software.bernie.geckolib.renderer.base.RenderPassInfo
import software.bernie.geckolib.renderer.layer.builtin.TextureLayerGeoLayer

class ArgonautVisorEntityLayer<T : ArgonautEntity>(
    renderer: ArgonautEntityRenderer<T>
) : TextureLayerGeoLayer<T, Void, LivingEntityRenderState>(renderer, VISOR_TEXTURE) {

    override fun getRenderType(renderState: LivingEntityRenderState): RenderType {
        return RenderTypes.entityCutoutNoCull(VISOR_TEXTURE)
    }

    companion object {
        private val VISOR_TEXTURE = CommonClass.locate("textures/entity/misc/argonaut/argonaut_visor.png")
    }
}
