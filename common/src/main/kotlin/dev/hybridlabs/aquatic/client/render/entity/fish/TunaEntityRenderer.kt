package dev.hybridlabs.aquatic.client.render.entity.fish

import com.mojang.blaze3d.vertex.PoseStack
import dev.hybridlabs.aquatic.client.model.entity.fish.TunaEntityModel
import dev.hybridlabs.aquatic.entity.fish.TunaEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.world.item.ItemStack
import software.bernie.geckolib.cache.model.GeoBone

class TunaEntityRenderer(context: Context) :
    BaseFishEntityRenderer<TunaEntity, LivingEntityRenderState>(context, TunaEntityModel(), true, false) {

    override fun rotateItem(
        poseStack: PoseStack,
        bone: GeoBone,
        stack: ItemStack,
        renderState: LivingEntityRenderState
    ) {}
}