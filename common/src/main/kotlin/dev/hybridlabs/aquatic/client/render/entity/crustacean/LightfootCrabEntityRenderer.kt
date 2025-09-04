package dev.hybridlabs.aquatic.client.renderer.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.LightfootCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.LightfootCrabEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class LightfootCrabEntityRenderer(context: Context) :
    HybridAquaticCrustaceanEntityRenderer<LightfootCrabEntity>(context, LightfootCrabEntityModel(), true, false)