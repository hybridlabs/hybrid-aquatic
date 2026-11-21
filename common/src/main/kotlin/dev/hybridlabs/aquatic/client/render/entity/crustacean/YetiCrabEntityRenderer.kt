package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.YetiCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.YetiCrabEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCrustaceanEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class YetiCrabEntityRenderer(context: Context) :
    BaseCrustaceanEntityRenderer<YetiCrabEntity, LivingEntityRenderState>(context, YetiCrabEntityModel(), true, false)