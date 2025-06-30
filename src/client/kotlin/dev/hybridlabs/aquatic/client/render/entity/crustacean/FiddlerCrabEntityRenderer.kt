package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.FiddlerCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.FiddlerCrabEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class FiddlerCrabEntityRenderer(context: Context) :
    HybridAquaticCrustaceanEntityRenderer<FiddlerCrabEntity>(context, FiddlerCrabEntityModel(), true, false)