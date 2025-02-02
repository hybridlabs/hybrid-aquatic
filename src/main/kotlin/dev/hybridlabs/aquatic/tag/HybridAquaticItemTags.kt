package dev.hybridlabs.aquatic.tag

import dev.hybridlabs.aquatic.HybridAquatic
import net.fabricmc.fabric.impl.tag.convention.v2.TagRegistration
import net.minecraft.item.Item
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier

object HybridAquaticItemTags {
    val LURE_ITEMS = create("lure_items")
    val PLUSHIES = create("plushies")
    val HAT = create("hat")
    val SCARF = create("scarf")
    val BACK_FIN = create("back_fin")
    val SMALL_FISH = create("small_fish")
    val MEDIUM_FISH = create("medium_fish")
    val LARGE_FISH = create("large_fish")
    val CRAB_CLAW = create("claw")

    val DRIFTWOOD_LOG_WOOD = create("driftwood_log_wood")

    val RAW_FISH = createConventional("foods/raw_fish")
    val RAW_FISHES = createConventional("foods/raw_fishes")
    val COOKED_FISH = createConventional("foods/cooked_fish")
    val COOKED_FISHES = createConventional("foods/cooked_fishes")
    val FOOD_POISONING = createConventional("foods/food_poisoning")
    val ARMORS = createConventional("armors")

    val REPAIRS_DIVING_HELMET = create("repairs_diving_helmet")
    val REPAIRS_NAUTILUS_ARMOR = create("repairs_nautilus_armor")
    val REPAIRS_MANGLERFISH_ARMOR = create("repairs_manglerfish_armor")
    val REPAIRS_EEL_SCARF = create("repairs_eel_scarf")
    val REPAIRS_MOON_JELLYFISH_HAT = create("repairs_moon_jellyfish_hat")

    val SEASHELL_TOOL_MATERIALS = create("seashell_tool_materials")
    val CORAL_TOOL_MATERIALS = create("coral_tool_materials")

    private fun create(id: String): TagKey<Item> {
        return TagKey.of(RegistryKeys.ITEM, Identifier(HybridAquatic.MOD_ID, id))
    }

    @Suppress("UnstableApiUsage")
    private fun createConventional(id: String): TagKey<Item> {
        return TagRegistration.ITEM_TAG.registerC(id)
    }
}
