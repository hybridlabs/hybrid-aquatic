package dev.hybridlabs.aquatic.mixin.client;

import dev.hybridlabs.aquatic.block.SeaMessage;
import dev.hybridlabs.aquatic.client.gui.SeaMessageBookContents;
import dev.hybridlabs.aquatic.item.HybridAquaticItems;
import dev.hybridlabs.aquatic.item.SeaMessageBookItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.BookScreen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.OpenWrittenBookS2CPacket;
import net.minecraft.registry.DynamicRegistryManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixin {
    @Shadow @Final private MinecraftClient client;

    @Shadow public abstract DynamicRegistryManager getRegistryManager();

    /**
     * Allows usage of Sea Message books to open a book screen.
     */
    @Inject(method = "onOpenWrittenBook", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void onOpenWrittenBook(OpenWrittenBookS2CPacket packet, CallbackInfo ci, ItemStack stack) {
        if (stack.isOf(HybridAquaticItems.INSTANCE.getSEA_MESSAGE_BOOK())) {
            SeaMessage message = SeaMessageBookItem.Companion.getSeaMessage(stack, this.getRegistryManager());
            if (message != null) {
                this.client.setScreen(new BookScreen(new SeaMessageBookContents(message)));
            }
        }
    }
}
