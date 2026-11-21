package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.SpiderCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.SpiderCrabEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCrustaceanEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class SpiderCrabEntityRenderer(context: Context) :
    BaseCrustaceanEntityRenderer<SpiderCrabEntity, LivingEntityRenderState>(context, SpiderCrabEntityModel(), true, false)