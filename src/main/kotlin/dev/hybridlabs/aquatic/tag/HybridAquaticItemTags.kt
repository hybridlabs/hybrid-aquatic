package dev.hybridlabs.aquatic.tag

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.item.Item
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier

object HybridAquaticItemTags {
    val LURE_ITEMS = create("lure_items")
    val PLUSHIES = create("plushies")
    val IRON_TOOLS = createConventional("iron_tools")
    val SMALL_FISH = create("small_fish")
    val MEDIUM_FISH = create("medium_fish")

    private fun create(id: String): TagKey<Item> {
        return TagKey.of(RegistryKeys.ITEM, Identifier(HybridAquatic.MOD_ID, id))
    }

    private fun createConventional(id: String): TagKey<Item> {
        return TagKey.of(RegistryKeys.ITEM, Identifier("c", id))
    }
}