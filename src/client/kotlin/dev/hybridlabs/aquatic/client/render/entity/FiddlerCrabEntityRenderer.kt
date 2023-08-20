package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.FiddlerCrabEntityModel
import dev.hybridlabs.aquatic.entity.critter.FiddlerCrabEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer

class FiddlerCrabEntityRenderer(context: Context) : GeoEntityRenderer<FiddlerCrabEntity>(context, FiddlerCrabEntityModel())
