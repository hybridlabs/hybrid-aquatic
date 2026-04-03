package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.GiantIsopodEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.GiantIsopodEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class GiantIsopodEntityRenderer(context: Context) :
    HACrustaceanEntityRenderer<GiantIsopodEntity>(context, GiantIsopodEntityModel(), true, false)