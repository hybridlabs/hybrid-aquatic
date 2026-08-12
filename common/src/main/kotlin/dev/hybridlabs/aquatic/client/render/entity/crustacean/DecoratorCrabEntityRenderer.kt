package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.DecoratorCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.DecoratorCrabEntity
import dev.hybridlabs.hapi.client.render.entity.BaseCrustaceanEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class DecoratorCrabEntityRenderer(context: Context) :
    BaseCrustaceanEntityRenderer<DecoratorCrabEntity>(context, DecoratorCrabEntityModel(), true, false)