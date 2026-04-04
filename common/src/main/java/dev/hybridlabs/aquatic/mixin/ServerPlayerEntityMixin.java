package dev.hybridlabs.aquatic.mixin;

import dev.hybridlabs.aquatic.item.HAItems;
import net.minecraft.network.protocol.game.ClientboundOpenBookPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class ServerPlayerEntityMixin {
    @Shadow
    public ServerGamePacketListenerImpl connection;

    /**
     * Allows usage of Sea Message books to open a book screen.
     */
    @Inject(method = "openItemGui", at = @At("TAIL"))
    private void onUseBook(ItemStack book, InteractionHand hand, CallbackInfo ci) {
        if (book.is(HAItems.INSTANCE.getSEA_MESSAGE_BOOK().get())) {
            this.connection.send(new ClientboundOpenBookPacket(hand));
        }
    }
}
