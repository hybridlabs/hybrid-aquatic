package dev.hybridlabs.aquatic.mixin.client;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.hybridlabs.aquatic.item.HybridAquaticItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ForgeGui.class)
public class InGameHudMixin {
    @Unique
    private static final ResourceLocation DIVING_HELMET_OVERLAY = new ResourceLocation("hybrid-aquatic",
            "textures" + "/misc/diving_helmet_overlay.png");

    @Inject(method = "render", at = @At("HEAD"))
    private void onRender(GuiGraphics context, float tickDelta, CallbackInfo ci) {
        Minecraft client = Minecraft.getInstance();
        Player player = client.player;

        if (player != null && client.options.getCameraType().isFirstPerson()) {
            ItemStack helmet = player.getInventory().getArmor(3);
            if (helmet.getItem() == HybridAquaticItems.INSTANCE.getDIVING_HELMET().get()) {
                renderDivingHelmetOverlay(context);
            }
        }

        if (player != null && client.options.getCameraType().isFirstPerson()) {
            ItemStack helmet = player.getInventory().getArmor(3);
            if (helmet.getItem() == HybridAquaticItems.INSTANCE.getREINFORCED_DIVING_HELMET().get()) {
                renderReinforcedDivingHelmetOverlay(context);
            }
        }
    }

    @Unique
    private void renderDivingHelmetOverlay(GuiGraphics context) {
        Minecraft client = Minecraft.getInstance();
        Window window = client.getWindow();
        int scaledWidth = window.getGuiScaledWidth();
        int scaledHeight = window.getGuiScaledHeight();

        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderTexture(0, DIVING_HELMET_OVERLAY);
        context.blit(DIVING_HELMET_OVERLAY, 0, 0, scaledWidth, scaledHeight, 0.0F, 0.0F, 512, 256, 512, 256);
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
    }

    @Unique
    private void renderReinforcedDivingHelmetOverlay(GuiGraphics context) {
        Minecraft client = Minecraft.getInstance();
        Window window = client.getWindow();
        int scaledWidth = window.getGuiScaledWidth();
        int scaledHeight = window.getGuiScaledHeight();

        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderTexture(0, DIVING_HELMET_OVERLAY);
        context.blit(DIVING_HELMET_OVERLAY, 0, 0, scaledWidth, scaledHeight, 0.0F, 0.0F, 512, 256, 512, 256);
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
    }
}