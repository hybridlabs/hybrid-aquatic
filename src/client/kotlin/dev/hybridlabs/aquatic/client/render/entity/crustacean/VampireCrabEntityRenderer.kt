package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.VampireCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.VampireCrabEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class VampireCrabEntityRenderer(context: Context) :
    HybridAquaticCrustaceanEntityRenderer<VampireCrabEntity>(context, VampireCrabEntityModel(), true, false)