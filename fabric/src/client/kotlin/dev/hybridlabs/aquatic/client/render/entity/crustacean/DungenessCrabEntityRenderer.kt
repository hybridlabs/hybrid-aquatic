package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.DungenessCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.HybridAquaticCrustaceanEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class DungenessCrabEntityRenderer(context: Context) :
    HybridAquaticCrustaceanEntityRenderer<HybridAquaticCrustaceanEntity>(context, DungenessCrabEntityModel(), true, false)