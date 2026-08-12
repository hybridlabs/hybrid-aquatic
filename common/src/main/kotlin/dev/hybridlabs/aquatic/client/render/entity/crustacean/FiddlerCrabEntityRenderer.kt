package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.FiddlerCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.FiddlerCrabEntity
import dev.hybridlabs.hapi.client.render.entity.BaseCrustaceanEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class FiddlerCrabEntityRenderer(context: Context) :
    BaseCrustaceanEntityRenderer<FiddlerCrabEntity>(context, FiddlerCrabEntityModel(), true, false)