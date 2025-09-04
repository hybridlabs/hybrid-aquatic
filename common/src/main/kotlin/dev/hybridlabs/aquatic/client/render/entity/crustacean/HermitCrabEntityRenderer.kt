package dev.hybridlabs.aquatic.client.renderer.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.HermitCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.HermitCrabEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class HermitCrabEntityRenderer(context: Context) :
    HybridAquaticCrustaceanEntityRenderer<HermitCrabEntity>(context, HermitCrabEntityModel(), true, false)