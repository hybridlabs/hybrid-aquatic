package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.LightfootCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.HybridAquaticCrustaceanEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class LightfootCrabEntityRenderer(context: Context) :
    HybridAquaticCrustaceanEntityRenderer<HybridAquaticCrustaceanEntity>(context, LightfootCrabEntityModel(), true, false)