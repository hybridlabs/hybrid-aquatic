package dev.hybridlabs.aquatic.client.render.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.cephalopod.UmbrellaOctopusEntityModel
import dev.hybridlabs.aquatic.entity.cephalopod.UmbrellaOctopusEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseOctopusEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider

class UmbrellaOctopusEntityRenderer(context: EntityRendererProvider.Context) :
    BaseOctopusEntityRenderer<UmbrellaOctopusEntity>(
        context,
        UmbrellaOctopusEntityModel(),
        true,
        false
    )