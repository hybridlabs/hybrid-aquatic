package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.CrayfishEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.CrayfishEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCrustaceanEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class CrayfishEntityRenderer(context: Context) :
    BaseCrustaceanEntityRenderer<CrayfishEntity>(context, CrayfishEntityModel(), true, false)