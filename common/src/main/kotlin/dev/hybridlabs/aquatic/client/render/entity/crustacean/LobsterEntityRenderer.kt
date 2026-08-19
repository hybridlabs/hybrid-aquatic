package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.LobsterEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.LobsterEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCrustaceanEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class LobsterEntityRenderer(context: Context) :
    BaseCrustaceanEntityRenderer<LobsterEntity>(context, LobsterEntityModel(), true, false)