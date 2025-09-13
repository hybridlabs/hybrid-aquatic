@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.aquatic.tag

import dev.hybridlabs.aquatic.CommonClass
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item

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

    val CRUSTACEAN_MEAT = create("crustacean_meat")
    val TUNA = create("tuna")
    val RAY = create("ray")

    val RAW_FISH = createConventional("foods/raw_fish")
    val RAW_FISHES = createConventional("foods/raw_fishes")
    val COOKED_FISH = createConventional("foods/cooked_fish")
    val COOKED_FISHES = createConventional("foods/cooked_fishes")
    val FOOD_POISONING = createConventional("foods/food_poisoning")
    val ARMORS = createConventional("armors")

    private fun create(id: String): TagKey<Item> {
        return TagKey.create(Registries.ITEM, CommonClass.locate(id))
    }

    private fun createConventional(id: String): TagKey<Item> {
		return TagKey.create(Registries.ITEM, ResourceLocation("c", id));
    }
}
