package dev.hybridlabs.aquatic.client.renderer.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.CoconutCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.CoconutCrabEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class CoconutCrabEntityRenderer(context: Context) :
    HybridAquaticCrustaceanEntityRenderer<CoconutCrabEntity>(context, CoconutCrabEntityModel(), true, false)