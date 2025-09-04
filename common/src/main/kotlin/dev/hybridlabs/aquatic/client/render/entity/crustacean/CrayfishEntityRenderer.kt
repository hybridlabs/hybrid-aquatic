package dev.hybridlabs.aquatic.client.renderer.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.CrayfishEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.CrayfishEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class CrayfishEntityRenderer(context: Context) :
    HybridAquaticCrustaceanEntityRenderer<CrayfishEntity>(context, CrayfishEntityModel(), true, false)