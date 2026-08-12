package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.CoconutCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.CoconutCrabEntity
import dev.hybridlabs.hapi.client.render.entity.BaseCrustaceanEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class CoconutCrabEntityRenderer(context: Context) :
    BaseCrustaceanEntityRenderer<CoconutCrabEntity>(context, CoconutCrabEntityModel(), true, false)