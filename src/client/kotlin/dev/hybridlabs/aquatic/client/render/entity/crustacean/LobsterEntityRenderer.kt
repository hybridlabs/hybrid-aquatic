package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.LobsterEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.HybridAquaticCrustaceanEntity
import dev.hybridlabs.aquatic.entity.crustacean.LobsterEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class LobsterEntityRenderer(context: Context) :
    HybridAquaticCrustaceanEntityRenderer<LobsterEntity>(context, LobsterEntityModel(), true, false)