package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.HorseshoeCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.HorseshoeCrabEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class HorseshoeCrabEntityRenderer(context: Context) :
    HybridAquaticCrustaceanEntityRenderer<HorseshoeCrabEntity>(context, HorseshoeCrabEntityModel(), true, false)