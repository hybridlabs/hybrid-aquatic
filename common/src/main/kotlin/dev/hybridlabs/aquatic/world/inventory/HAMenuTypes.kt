package dev.hybridlabs.aquatic.world.inventory

import dev.hybridlabs.aquatic.CommonClass
import net.minecraft.world.flag.FeatureFlags
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.MenuType
import java.util.function.Supplier

object HAMenuTypes {

    val ARGONAUT_MENU_3ROW = register("argonaut_3row", ArgonautMenu::threeRows)
    val ARGONAUT_MENU_2ROW = register("argonaut_2row", ArgonautMenu::twoRows)

    fun <T: AbstractContainerMenu> register(id: String, menuType: MenuType.MenuSupplier<T>): Supplier<MenuType<T>> {
        return CommonClass.MENU_TYPE.register(id) {
            MenuType<T>(menuType, FeatureFlags.VANILLA_SET)
        }
    }
}