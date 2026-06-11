package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.SpiderCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.SpiderCrabEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SpiderCrabEntityRenderer(context: Context) :
    HACrustaceanEntityRenderer<SpiderCrabEntity>(context, SpiderCrabEntityModel(), true, false)