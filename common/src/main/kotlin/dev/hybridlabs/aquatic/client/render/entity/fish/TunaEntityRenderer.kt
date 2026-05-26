package dev.hybridlabs.aquatic.client.render.entity.fish

import com.mojang.blaze3d.vertex.PoseStack
import dev.hybridlabs.aquatic.client.model.entity.fish.TunaEntityModel
import dev.hybridlabs.aquatic.entity.fish.TunaEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import net.minecraft.world.item.ItemStack
import software.bernie.geckolib.cache.`object`.GeoBone

class TunaEntityRenderer(context: Context) :
    HAFishEntityRenderer<TunaEntity>(context, TunaEntityModel(), true, false) {

    override fun rotateItem(
        poseStack: PoseStack,
        bone: GeoBone,
        stack: ItemStack,
        animatable: TunaEntity
    ) {}
}