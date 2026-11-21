package dev.hybridlabs.aquatic.client.render.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.cephalopod.FireflySquidEntityModel
import dev.hybridlabs.aquatic.entity.cephalopod.FireflySquidEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseCephalopodEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class FireflySquidEntityRenderer(context: Context) :
    BaseCephalopodEntityRenderer<FireflySquidEntity, LivingEntityRenderState>(context, FireflySquidEntityModel(), true, true)