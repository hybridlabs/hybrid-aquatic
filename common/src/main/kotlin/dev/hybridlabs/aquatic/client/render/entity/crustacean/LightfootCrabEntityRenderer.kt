package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.LightfootCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.LightfootCrabEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCrustaceanEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class LightfootCrabEntityRenderer(context: Context) :
    BaseCrustaceanEntityRenderer<LightfootCrabEntity>(context, LightfootCrabEntityModel(), true, false)