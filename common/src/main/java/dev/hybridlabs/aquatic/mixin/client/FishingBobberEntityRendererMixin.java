package dev.hybridlabs.aquatic.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.hybridlabs.aquatic.CommonClass;
import dev.hybridlabs.aquatic.access.CustomFishingBobberEntityData;
import dev.hybridlabs.aquatic.item.HybridAquaticItems;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.FishingHookRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FishingHookRenderer.class)
public abstract class FishingBobberEntityRendererMixin {
    @Unique
    private static final RenderType BARBED_HOOK_LAYER =
            getRenderType(CommonClass.locate("textures/entity/bobber" + "/barbed_bobber.png"));

    @Unique
    private static final RenderType GLOWING_HOOK_LAYER =
            getRenderType(CommonClass.locate("textures/entity/bobber" + "/glowing_bobber.png"));

    @Unique
    private static final RenderType MAGNETIC_HOOK_LAYER =
            getRenderType(CommonClass.locate("textures/entity/bobber" + "/magnetic_bobber.png"));

    @Unique
    private static final RenderType CREEPERMAGNET_HOOK_LAYER =
            getRenderType(
                    CommonClass.locate("textures/entity" + "/bobber/creepermagnet_bobber.png"));

    @Unique
    private static final RenderType OMINOUS_HOOK_LAYER =
            getRenderType(CommonClass.locate("textures/entity/bobber" + "/ominous_bobber.png"));

    @Unique FishingHook entity;

    @Unique
    private static RenderType getRenderType(ResourceLocation textureLocation) {
        return RenderType.entityCutout(textureLocation);
    }

    @Inject(
            method =
                    "render(Lnet/minecraft/world/entity/projectile/FishingHook;"
                            + "FFLcom/mojang/blaze3d/vertex"
                            + "/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At(value = "HEAD"),
            remap = false)
    private void objectGetter(
            FishingHook fishingBobberEntity,
            float f,
            float g,
            PoseStack matrixStack,
            MultiBufferSource vertexConsumerProvider,
            int i,
            CallbackInfo ci) {
        entity = fishingBobberEntity;
    }

    @Redirect(
            method =
                    "render(Lnet/minecraft/world/entity/projectile/FishingHook;"
                            + "FFLcom/mojang/blaze3d/vertex"
                            + "/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lnet/minecraft/client/renderer/MultiBufferSource;getBuffer"
                                            + "(Lnet/minecraft/client/renderer"
                                            + "/RenderType;)Lcom/mojang/blaze3d/vertex/VertexConsumer;",
                            ordinal = 0),
            remap = false)
    private VertexConsumer changeRenderType(MultiBufferSource instance, RenderType renderLayer) {
        RenderType currentRenderType = renderLayer;

        ItemStack currentStack =
                ((CustomFishingBobberEntityData) entity).hybrid_aquatic$getLureItem();
        if (currentStack.getItem().equals(HybridAquaticItems.INSTANCE.getBARBED_HOOK().get()))
            currentRenderType = BARBED_HOOK_LAYER;
        else if (currentStack.getItem().equals(HybridAquaticItems.INSTANCE.getGLOWING_HOOK().get()))
            currentRenderType = GLOWING_HOOK_LAYER;
        else if (currentStack
                .getItem()
                .equals(HybridAquaticItems.INSTANCE.getMAGNETIC_HOOK().get()))
            currentRenderType = MAGNETIC_HOOK_LAYER;
        else if (currentStack
                .getItem()
                .equals(HybridAquaticItems.INSTANCE.getCREEPERMAGNET_HOOK().get()))
            currentRenderType = CREEPERMAGNET_HOOK_LAYER;
        else if (currentStack.getItem().equals(HybridAquaticItems.INSTANCE.getOMINOUS_HOOK().get()))
            currentRenderType = OMINOUS_HOOK_LAYER;

        return instance.getBuffer(currentRenderType);
    }
}
