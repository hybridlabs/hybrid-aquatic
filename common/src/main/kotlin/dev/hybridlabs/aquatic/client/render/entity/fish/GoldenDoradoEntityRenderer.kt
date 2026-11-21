package dev.hybridlabs.aquatic.client.render.entity.fish

import com.mojang.blaze3d.vertex.PoseStack
import dev.hybridlabs.aquatic.client.model.entity.fish.GoldenDoradoEntityModel
import dev.hybridlabs.aquatic.entity.fish.GoldenDoradoEntity
import dev.hybridlabs.hapi.client.render.entity.aquatic.BaseFishEntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.world.item.ItemStack
import software.bernie.geckolib.cache.model.GeoBone

class GoldenDoradoEntityRenderer(context: Context) :
    BaseFishEntityRenderer<GoldenDoradoEntity, LivingEntityRenderState>(context, GoldenDoradoEntityModel(), true, false) {

    override fun rotateItem(
        poseStack: PoseStack,
        bone: GeoBone,
        stack: ItemStack,
        renderState: LivingEntityRenderState
    ) {}
}