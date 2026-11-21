package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.LobsterEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.LobsterEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCrustaceanEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class LobsterEntityRenderer(context: Context) :
    BaseCrustaceanEntityRenderer<LobsterEntity, LivingEntityRenderState>(context, LobsterEntityModel(), true, false)