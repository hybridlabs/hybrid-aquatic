package dev.hybridlabs.aquatic.client.renderer.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.DecoratorCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.DecoratorCrabEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class DecoratorCrabEntityRenderer(context: Context) :
    HybridAquaticCrustaceanEntityRenderer<DecoratorCrabEntity>(context, DecoratorCrabEntityModel(), true, false)