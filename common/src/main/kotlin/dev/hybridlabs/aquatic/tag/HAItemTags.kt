@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.aquatic.tag

import dev.hybridlabs.aquatic.CommonClass
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item

object HAItemTags {
    val LURE_ITEMS = create("lure_items")
    val PLUSHIES = create("plushies")

    val CORAL_SET = create("coral_set")
    val SEASHELL_SET = create("seashell_set")
    val TURTLE_SET = create("turtle_set")

    //#region Meat Tags
    val SMALL_FISH = create("small_fish")
    val MEDIUM_FISH = create("medium_fish")
    val LARGE_FISH = create("large_fish")
    val LOBSTER_MEAT = create("lobster_meat")
    val CRUSTACEAN_MEAT = create("crustacean_meat")

    val CRAB_CLAW = create("crab_claw")
    val KELPS = create("kelps")

    val DIVING_ARMOR = create("diving_armor")
    val DIVING_HELMET = create("diving_helmet")
    val DIVING_SUIT = create("diving_suit")
    val DIVING_LEGGINGS = create("diving_leggings")
    val DIVING_BOOTS = create("diving_boots")

    val RESISTS_CORROSION = create("resists_corrosion")

    val HAT = create("hat")
    val SCARF = create("scarf")
    val BACK_FIN = create("back_fin")

    val DRIFTWOOD_LOG_WOOD = create("driftwood_log_wood")

    val FISHING_TREASURE = create("fishing_treasure")

    val CRAB_WEARABLES = create("crab_wearables")
    val CRAB_TRADEABLES = create("crab_tradeables")
    val REDSTONE_COMPONENTS = create("redstone_components")

    val RAW_FISH = createConventional("foods/raw_fish")
    val RAW_FISHES = createConventional("foods/raw_fishes")
    val COOKED_FISH = createConventional("foods/cooked_fish")
    val COOKED_FISHES = createConventional("foods/cooked_fishes")
    val FOOD_POISONING = createConventional("foods/food_poisoning")
    val ARMORS = createConventional("armors")

    val STRIPPED_WOODS = createConventional("stripped_woods")
    val STRIPPED_LOGS = createConventional("stripped_logs")

    val STONES = createConventional("stones")
    val SANDS = createConventional("sands")

    private fun create(id: String): TagKey<Item> {
        return TagKey.create(Registries.ITEM, CommonClass.locate(id))
    }

    private fun createConventional(id: String): TagKey<Item> {
		return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", id))
    }
}
