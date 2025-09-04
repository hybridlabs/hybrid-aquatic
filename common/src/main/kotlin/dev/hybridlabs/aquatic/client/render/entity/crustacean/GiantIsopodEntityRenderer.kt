package dev.hybridlabs.aquatic.client.renderer.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.GiantIsopodEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.GiantIsopodEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class GiantIsopodEntityRenderer(context: Context) :
    HybridAquaticCrustaceanEntityRenderer<GiantIsopodEntity>(context, GiantIsopodEntityModel(), true, false)