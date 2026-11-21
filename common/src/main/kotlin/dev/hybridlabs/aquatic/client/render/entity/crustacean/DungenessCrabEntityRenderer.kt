package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.DungenessCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.DungenessCrabEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCrustaceanEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class DungenessCrabEntityRenderer(context: Context) :
    BaseCrustaceanEntityRenderer<DungenessCrabEntity, LivingEntityRenderState>(context, DungenessCrabEntityModel(), true, false)