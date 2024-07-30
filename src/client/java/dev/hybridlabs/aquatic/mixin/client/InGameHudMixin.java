package dev.hybridlabs.aquatic.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.hybridlabs.aquatic.item.HybridAquaticItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.util.Window;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Unique
    private static final Identifier DIVING_HELMET_OVERLAY = Identifier.of("hybrid-aquatic", "textures/misc/diving_helmet_overlay.png");

    @Inject(method = "render", at = @At("HEAD"))
    private void onRender(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        PlayerEntity player = client.player;

        if (player != null && client.options.getPerspective().isFirstPerson()) {
            ItemStack helmet = player.getInventory().getArmorStack(3);
            if (helmet.getItem() == HybridAquaticItems.INSTANCE.getDIVING_HELMET()) {
                renderDivingHelmetOverlay(context);
            }
        }
    }

    @Unique
    private void renderDivingHelmetOverlay(DrawContext context) {
        MinecraftClient client = MinecraftClient.getInstance();
        Window window = client.getWindow();
        int scaledWidth = window.getScaledWidth();
        int scaledHeight = window.getScaledHeight();

        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderTexture(0, DIVING_HELMET_OVERLAY);
        context.drawTexture(DIVING_HELMET_OVERLAY, 0, 0, scaledWidth, scaledHeight, 0.0F, 0.0F, 512, 256, 512, 256);
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
    }
}
