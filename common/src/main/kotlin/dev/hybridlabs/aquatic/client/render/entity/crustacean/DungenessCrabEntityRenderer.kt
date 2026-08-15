package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.DungenessCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.DungenessCrabEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCrustaceanEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class DungenessCrabEntityRenderer(context: Context) :
    BaseCrustaceanEntityRenderer<DungenessCrabEntity>(context, DungenessCrabEntityModel(), true, false)