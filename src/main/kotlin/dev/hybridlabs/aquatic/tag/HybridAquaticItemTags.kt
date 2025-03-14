@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.aquatic.tag

import dev.hybridlabs.aquatic.HybridAquatic
import net.fabricmc.fabric.impl.tag.convention.TagRegistration
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

    val COOLING = create("foods/cooling")

    val DRIFTWOOD_LOG_WOOD = create("driftwood_log_wood")

    val RAW_FISH = createConventional("foods/raw_fish")
    val RAW_FISHES = createConventional("foods/raw_fishes")
    val COOKED_FISH = createConventional("foods/cooked_fish")
    val COOKED_FISHES = createConventional("foods/cooked_fishes")
    val FOOD_POISONING = createConventional("foods/food_poisoning")
    val ARMORS = createConventional("armors")

    private fun create(id: String): TagKey<Item> {
        return TagKey.of(RegistryKeys.ITEM, Identifier(HybridAquatic.MOD_ID, id))
    }

    private fun createConventional(id: String): TagKey<Item> {
        return TagRegistration.ITEM_TAG_REGISTRATION.registerCommon(id)
    }
}
