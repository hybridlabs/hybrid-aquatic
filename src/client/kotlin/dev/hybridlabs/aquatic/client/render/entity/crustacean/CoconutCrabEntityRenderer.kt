package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.CoconutCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.CoconutCrabEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class CoconutCrabEntityRenderer(context: Context) :
    HybridAquaticCrustaceanEntityRenderer<CoconutCrabEntity>(context, CoconutCrabEntityModel(), true, false)