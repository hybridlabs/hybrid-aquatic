package dev.hybridlabs.aquatic.client.render.entity.misc

import dev.hybridlabs.aquatic.client.model.entity.misc.StarfishProjectileEntityModel
import dev.hybridlabs.aquatic.entity.projectile.StarfishProjectileEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import software.bernie.geckolib.renderer.GeoEntityRenderer

class StarfishProjectileRenderer(
    context: EntityRendererProvider.Context
) : GeoEntityRenderer<StarfishProjectileEntity, LivingEntityRenderState>(
    context,
    StarfishProjectileEntityModel()
)