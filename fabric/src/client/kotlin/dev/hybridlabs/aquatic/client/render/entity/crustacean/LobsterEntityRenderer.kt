package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.LobsterEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.HybridAquaticCrustaceanEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class LobsterEntityRenderer(context: Context) :
    HybridAquaticCrustaceanEntityRenderer<HybridAquaticCrustaceanEntity>(context, LobsterEntityModel(), true, false)