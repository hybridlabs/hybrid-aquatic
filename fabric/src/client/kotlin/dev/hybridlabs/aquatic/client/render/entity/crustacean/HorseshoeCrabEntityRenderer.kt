package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.HorseshoeCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.HybridAquaticCrustaceanEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class HorseshoeCrabEntityRenderer(context: Context) :
    HybridAquaticCrustaceanEntityRenderer<HybridAquaticCrustaceanEntity>(context, HorseshoeCrabEntityModel(), true, false)