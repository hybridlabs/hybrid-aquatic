package dev.hybridlabs.aquatic.mixin.client;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import dev.hybridlabs.aquatic.client.gui.SeaMessageBookContents;
import net.minecraft.client.gui.screen.ingame.BookScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BookScreen.class)
public class BookScreenMixin {
    @Shadow private BookScreen.Contents contents;

    @WrapWithCondition(
            method = "render",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/gui/screen/ingame/BookScreen;pageIndexText:Lnet/minecraft/text/Text;",
                    ordinal = 0
            )
    )
    private boolean onRenderPageCount(BookScreen instance, Text value) {
        return !(this.contents instanceof SeaMessageBookContents seaContents && seaContents.getMessage().getInfinite());
    }
}
