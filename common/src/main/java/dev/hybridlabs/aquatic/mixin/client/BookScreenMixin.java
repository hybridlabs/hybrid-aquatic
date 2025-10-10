package dev.hybridlabs.aquatic.mixin.client;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import dev.hybridlabs.aquatic.client.gui.SeaMessageBookContents;
import net.minecraft.client.gui.screens.inventory.BookViewScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BookViewScreen.class)
public class BookScreenMixin {
    @Shadow
    private BookViewScreen.BookAccess bookAccess;

    /*
    @WrapWithCondition(method = "render", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screens" +
            "/inventory/BookViewScreen;pageMsg:Lnet/minecraft/network/chat/Component;", ordinal = 0))
    private boolean onRenderPageCount(BookViewScreen instance, Component value) {
        return !(this.bookAccess instanceof SeaMessageBookContents seaContents && seaContents.getMessage().getInfinite());
    }
     */
}
