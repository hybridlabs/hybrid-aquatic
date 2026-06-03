package dev.hybridlabs.aquatic.mixin;

import dev.hybridlabs.aquatic.entity.misc.ArgonautEntity;
import net.minecraft.network.protocol.game.ServerboundPaddleBoatPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerGamePacketListenerImpl.class)
public class ServerGamePacketListenerImplMixin {
	@Shadow
	public ServerPlayer player;
	
	@Inject(
			method = "handlePaddleBoat",
			at = @At("TAIL")
	)
	private void test(ServerboundPaddleBoatPacket packet, CallbackInfo ci) {
		if (player.getControlledVehicle() instanceof ArgonautEntity argonaut) {
			argonaut.setPropellerState(packet.getLeft(), packet.getRight());
		}
	}
}
