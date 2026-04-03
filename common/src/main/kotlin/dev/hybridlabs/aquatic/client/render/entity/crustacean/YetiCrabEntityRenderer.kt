package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.YetiCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.YetiCrabEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class YetiCrabEntityRenderer(context: Context) :
    HACrustaceanEntityRenderer<YetiCrabEntity>(context, YetiCrabEntityModel(), true, false)