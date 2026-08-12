package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.HermitCrabEntityModel
import dev.hybridlabs.aquatic.client.render.entity.crustacean.layer.HybridAquaticShellLayer
import dev.hybridlabs.aquatic.entity.crustacean.HermitCrabEntity
import dev.hybridlabs.hapi.client.render.entity.BaseCrustaceanEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.world.item.Items

class HermitCrabEntityRenderer(context: Context) :
    BaseCrustaceanEntityRenderer<HermitCrabEntity>(context, HermitCrabEntityModel(), true, false) {

    init {
        addRenderLayer(HybridAquaticShellLayer(this, Items.NAUTILUS_SHELL))
    }
}