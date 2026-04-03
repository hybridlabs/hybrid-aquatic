package dev.hybridlabs.aquatic.client.render.entity.miniboss

import dev.hybridlabs.aquatic.client.model.entity.miniboss.ShellBeastEntityModel
import dev.hybridlabs.aquatic.entity.miniboss.ShellBeastEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context

class ShellBeastEntityRenderer(context: Context) :
    HAMinibossEntityRenderer<ShellBeastEntity>(context, ShellBeastEntityModel()) {
    override fun getMotionAnimThreshold(animatable: ShellBeastEntity): Float {
        return 0.0025f
    }

    override fun getDeathMaxRotation(animatable: ShellBeastEntity): Float {
        return 0f
    }

    init {
        this.shadowRadius = 1.5f
    }
}