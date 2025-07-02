package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.DungenessCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.DungenessCrabEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class DungenessCrabEntityRenderer(context: Context) :
    HybridAquaticCrustaceanEntityRenderer<DungenessCrabEntity>(context, DungenessCrabEntityModel(), true, false)