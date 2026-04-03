package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.VampireCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.VampireCrabEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class VampireCrabEntityRenderer(context: Context) :
    HACrustaceanEntityRenderer<VampireCrabEntity>(context, VampireCrabEntityModel(), true, false)