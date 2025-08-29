package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.DecoratorCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.DecoratorCrabEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class DecoratorCrabEntityRenderer(context: Context) :
    HybridAquaticCrustaceanEntityRenderer<DecoratorCrabEntity>(context, DecoratorCrabEntityModel(), true, false)