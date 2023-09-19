package dev.hybridlabs.aquatic.utils;

import net.minecraft.util.Hand;

public class HandUtils {
  public static Hand getOpposingHand(Hand hand) {
    return hand.equals(Hand.MAIN_HAND) ? Hand.OFF_HAND : Hand.MAIN_HAND;
  }
}
