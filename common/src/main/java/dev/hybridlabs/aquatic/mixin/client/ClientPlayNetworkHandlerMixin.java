package dev.hybridlabs.aquatic.mixin.client;

import dev.hybridlabs.aquatic.block.SeaMessage;
import dev.hybridlabs.aquatic.client.gui.SeaMessageBookContents;
import dev.hybridlabs.aquatic.item.HybridAquaticItems;
import dev.hybridlabs.aquatic.item.SeaMessageBookItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.BookViewScreen;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.protocol.game.ClientboundOpenBookPacket;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ClientPacketListener.class)
public abstract class ClientPlayNetworkHandlerMixin {

    @Shadow
    public abstract RegistryAccess.Frozen registryAccess();

    /**
     * Allows usage of Sea Message books to open a book screen.
     */
    @Inject(method = "handleOpenBook", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILHARD, remap = false)
    private void onOpenWrittenBook(ClientboundOpenBookPacket packet, CallbackInfo ci, ItemStack stack) {
        if (stack.is(HybridAquaticItems.INSTANCE.getSEA_MESSAGE_BOOK().get())) {
            SeaMessage message = SeaMessageBookItem.Companion.getSeaMessage(stack, this.registryAccess());
            if (message != null) {
                //this.minecraft.setScreen(new BookViewScreen(new SeaMessageBookContents(message)));
            }
        }
    }
}
