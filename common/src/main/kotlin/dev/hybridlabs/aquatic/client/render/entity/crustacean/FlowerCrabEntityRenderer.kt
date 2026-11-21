package dev.hybridlabs.aquatic.client.render.entity.crustacean

import dev.hybridlabs.aquatic.client.model.entity.crustacean.FlowerCrabEntityModel
import dev.hybridlabs.aquatic.entity.crustacean.FlowerCrabEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCrustaceanEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class FlowerCrabEntityRenderer(context: Context) :
    BaseCrustaceanEntityRenderer<FlowerCrabEntity, LivingEntityRenderState>(context, FlowerCrabEntityModel(), true, false)