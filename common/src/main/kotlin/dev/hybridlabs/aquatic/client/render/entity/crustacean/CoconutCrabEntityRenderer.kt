package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.CoconutCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.CoconutCrabEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCrustaceanEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class CoconutCrabEntityRenderer(context: Context) :
    BaseCrustaceanEntityRenderer<CoconutCrabEntity, LivingEntityRenderState>(context, CoconutCrabEntityModel(), true, false)