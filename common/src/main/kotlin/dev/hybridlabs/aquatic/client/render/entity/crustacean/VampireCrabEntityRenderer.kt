package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.VampireCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.VampireCrabEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCrustaceanEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class VampireCrabEntityRenderer(context: Context) :
    BaseCrustaceanEntityRenderer<VampireCrabEntity, LivingEntityRenderState>(context, VampireCrabEntityModel(), true, false)