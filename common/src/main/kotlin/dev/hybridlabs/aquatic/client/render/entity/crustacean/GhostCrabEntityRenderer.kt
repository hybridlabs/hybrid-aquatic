package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.GhostCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.GhostCrabEntity
import dev.hybridlabs.hapi.client.render.entity.BaseCrustaceanEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class GhostCrabEntityRenderer(context: Context) :
    BaseCrustaceanEntityRenderer<GhostCrabEntity>(context, GhostCrabEntityModel(), true, false)