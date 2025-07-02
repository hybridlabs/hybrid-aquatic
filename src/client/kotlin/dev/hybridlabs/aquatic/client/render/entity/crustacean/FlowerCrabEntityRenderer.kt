package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.FlowerCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.FlowerCrabEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class FlowerCrabEntityRenderer(context: Context) :
    HybridAquaticCrustaceanEntityRenderer<FlowerCrabEntity>(context, FlowerCrabEntityModel(), true, false)