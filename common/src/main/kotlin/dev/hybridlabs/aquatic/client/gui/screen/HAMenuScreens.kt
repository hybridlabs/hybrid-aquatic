package dev.hybridlabs.aquatic.client.gui.screen

import dev.hybridlabs.aquatic.world.inventory.HAMenuTypes
import net.minecraft.client.gui.screens.MenuScreens

object HAMenuScreens {
    init {
        MenuScreens.register(HAMenuTypes.ARGONAUT_MENU_3ROW.get(), ::ArgonautScreen)
    }
}