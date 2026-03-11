package dev.hybridlabs.aquatic.mixin.client;

import dev.hybridlabs.aquatic.entity.misc.ArgonautEntity;
import net.minecraft.client.player.Input;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin {

    @Shadow
    public Input input;

    @Inject(method = "rideTick", at = @At("TAIL"))
    private void handleArgonautInput(CallbackInfo ci) {
        LocalPlayer player = (LocalPlayer)(Object)this;

        Entity vehicle = player.getControlledVehicle();

        if (vehicle instanceof ArgonautEntity argonaut) {
            argonaut.setInput(
                    this.input.left,
                    this.input.right,
                    this.input.up,
                    this.input.down,
                    this.input.jumping
            );
        }
    }
}