package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.GiantIsopodEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.GiantIsopodEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCrustaceanEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class GiantIsopodEntityRenderer(context: Context) :
    BaseCrustaceanEntityRenderer<GiantIsopodEntity, LivingEntityRenderState>(context, GiantIsopodEntityModel(), true, false)