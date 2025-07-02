package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.GiantIsopodEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.GiantIsopodEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class GiantIsopodEntityRenderer(context: Context) :
    HybridAquaticCrustaceanEntityRenderer<GiantIsopodEntity>(context, GiantIsopodEntityModel(), true, false)