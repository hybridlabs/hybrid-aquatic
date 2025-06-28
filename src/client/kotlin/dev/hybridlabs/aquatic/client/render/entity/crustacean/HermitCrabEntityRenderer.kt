package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.HermitCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.HermitCrabEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class HermitCrabEntityRenderer(context: Context) :
    HybridAquaticCrustaceanEntityRenderer<HermitCrabEntity>(context, HermitCrabEntityModel(), true, false)