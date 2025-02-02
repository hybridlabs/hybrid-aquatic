package dev.hybridlabs.aquatic.mixin.client;

import net.minecraft.client.gui.screen.ingame.BookScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BookScreen.class)
public class BookScreenMixin {
    @Shadow private BookScreen.Contents contents;

    /*@WrapWithCondition(
            method = "render",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/gui/screen/ingame/BookScreen;pageIndexText:Lnet/minecraft/text/Text;",
                    ordinal = 0
            )
    )
    private boolean onRenderPageCount(BookScreen instance, Text value) {
        return !(this.contents instanceof SeaMessageBookContents seaContents && seaContents.getMessage().getInfinite());
    } TODO */
}
