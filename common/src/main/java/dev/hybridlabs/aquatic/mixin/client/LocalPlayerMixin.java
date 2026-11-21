package dev.hybridlabs.aquatic.mixin.client;

import dev.hybridlabs.aquatic.entity.misc.ArgonautEntity;
import net.minecraft.client.player.ClientInput;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Input;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin {

    @Shadow
    public ClientInput input;

    @Inject(method = "rideTick", at = @At("TAIL"))
    private void handleArgonautInput(CallbackInfo ci) {
        LocalPlayer player = (LocalPlayer) (Object) this;

        Entity vehicle = player.getControlledVehicle();

        if (vehicle instanceof ArgonautEntity argonaut) {
            Input keys = this.input.keyPresses;

            argonaut.setInput(
                    keys.left(),
                    keys.right(),
                    keys.forward(),
                    keys.backward(),
                    keys.jump(),
                    keys.sprint()
            );
        }
    }
}
