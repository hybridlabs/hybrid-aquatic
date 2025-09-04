package dev.hybridlabs.aquatic.client.renderer.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.fish.MorayEelEntityModel
import dev.hybridlabs.aquatic.entity.fish.MorayEelEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class MorayEelEntityRenderer(context: Context) :
    HybridAquaticFishEntityRenderer<MorayEelEntity>(context, MorayEelEntityModel(), true, false)