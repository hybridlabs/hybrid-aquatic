package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.FlowerCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.FlowerCrabEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class FlowerCrabEntityRenderer(context: Context) :
    HACrustaceanEntityRenderer<FlowerCrabEntity>(context, FlowerCrabEntityModel(), true, false)