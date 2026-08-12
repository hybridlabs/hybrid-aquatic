package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.GiantIsopodEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.GiantIsopodEntity
import dev.hybridlabs.hapi.client.render.entity.BaseCrustaceanEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class GiantIsopodEntityRenderer(context: Context) :
    BaseCrustaceanEntityRenderer<GiantIsopodEntity>(context, GiantIsopodEntityModel(), true, false)