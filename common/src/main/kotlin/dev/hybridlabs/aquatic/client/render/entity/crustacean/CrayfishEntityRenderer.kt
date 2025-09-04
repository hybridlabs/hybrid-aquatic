package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.CrayfishEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.CrayfishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class CrayfishEntityRenderer(context: Context) :
    HybridAquaticCrustaceanEntityRenderer<CrayfishEntity>(context, CrayfishEntityModel(), true, false)