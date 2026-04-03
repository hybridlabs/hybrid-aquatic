package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.LobsterEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.LobsterEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class LobsterEntityRenderer(context: Context) :
    HACrustaceanEntityRenderer<LobsterEntity>(context, LobsterEntityModel(), true, false)