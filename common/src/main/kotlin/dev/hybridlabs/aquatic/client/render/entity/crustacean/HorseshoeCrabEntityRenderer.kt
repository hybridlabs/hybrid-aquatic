package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.HorseshoeCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.HorseshoeCrabEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCrustaceanEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class HorseshoeCrabEntityRenderer(context: Context) :
    BaseCrustaceanEntityRenderer<HorseshoeCrabEntity, LivingEntityRenderState>(context, HorseshoeCrabEntityModel(), true, false)