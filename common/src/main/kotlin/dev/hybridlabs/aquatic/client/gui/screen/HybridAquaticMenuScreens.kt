package dev.hybridlabs.aquatic.client.gui.screen

import dev.hybridlabs.aquatic.world.inventory.HybridAquaticMenuTypes
import net.minecraft.client.gui.screens.MenuScreens

object HybridAquaticMenuScreens {
    fun register() {
        MenuScreens.register(HybridAquaticMenuTypes.ARGONAUT_MENU_3ROW.get(), ::ArgonautScreen)
    }
}