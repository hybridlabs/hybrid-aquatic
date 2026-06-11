package dev.hybridlabs.aquatic.client.render.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.cephalopod.FireflySquidEntityModel
import dev.hybridlabs.aquatic.entity.cephalopod.FireflySquidEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class FireflySquidEntityRenderer(context: Context) :
    HACephalopodEntityRenderer<FireflySquidEntity>(context, FireflySquidEntityModel(), true, true)