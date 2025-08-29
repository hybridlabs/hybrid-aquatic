package dev.hybridlabs.aquatic.utils;

import net.minecraft.world.InteractionHand;

public class HandUtils {
    public static InteractionHand getOpposingHand(InteractionHand hand) {
        return hand.equals(InteractionHand.MAIN_HAND) ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
    }
}
