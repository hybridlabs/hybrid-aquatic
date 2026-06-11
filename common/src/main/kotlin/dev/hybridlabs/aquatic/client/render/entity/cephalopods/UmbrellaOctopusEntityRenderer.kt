package dev.hybridlabs.aquatic.client.render.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.cephalopod.UmbrellaOctopusEntityModel
import dev.hybridlabs.aquatic.entity.cephalopod.UmbrellaOctopusEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider

class UmbrellaOctopusEntityRenderer(context: EntityRendererProvider.Context) :
    HAOctopusEntityRenderer<UmbrellaOctopusEntity>(
        context,
        UmbrellaOctopusEntityModel(),
        true,
        false
    )