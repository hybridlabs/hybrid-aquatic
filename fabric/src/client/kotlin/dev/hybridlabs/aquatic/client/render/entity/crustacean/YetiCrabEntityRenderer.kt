package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.YetiCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.HybridAquaticCrustaceanEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class YetiCrabEntityRenderer(context: Context) :
    HybridAquaticCrustaceanEntityRenderer<HybridAquaticCrustaceanEntity>(context, YetiCrabEntityModel(), true, false)