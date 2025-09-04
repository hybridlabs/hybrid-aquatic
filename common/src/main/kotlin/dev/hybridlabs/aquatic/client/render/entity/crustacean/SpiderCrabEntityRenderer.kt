package dev.hybridlabs.aquatic.client.renderer.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.SpiderCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.SpiderCrabEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class SpiderCrabEntityRenderer(context: Context) :
    HybridAquaticCrustaceanEntityRenderer<SpiderCrabEntity>(context, SpiderCrabEntityModel(), true, false)