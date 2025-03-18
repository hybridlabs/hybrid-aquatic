package dev.hybridlabs.aquatic.mixin;

import dev.hybridlabs.aquatic.item.HybridAquaticItems;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.OpenWrittenBookS2CPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {
    @Shadow public ServerPlayNetworkHandler networkHandler;

    /**
     * Allows usage of Sea Message books to open a book screen.
     */
    @Inject(method = "useBook", at = @At("TAIL"))
    private void onUseBook(ItemStack book, Hand hand, CallbackInfo ci) {
        if (book.isOf(HybridAquaticItems.INSTANCE.getSEA_MESSAGE_BOOK())) {
            this.networkHandler.sendPacket(new OpenWrittenBookS2CPacket(hand));
        }
    }
}
