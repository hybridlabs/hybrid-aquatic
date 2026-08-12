package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.SpiderCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.SpiderCrabEntity
import dev.hybridlabs.hapi.client.render.entity.BaseCrustaceanEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SpiderCrabEntityRenderer(context: Context) :
    BaseCrustaceanEntityRenderer<SpiderCrabEntity>(context, SpiderCrabEntityModel(), true, false)