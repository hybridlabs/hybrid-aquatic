package dev.hybridlabs.aquatic.loot

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.loot.LootTable
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier

object HybridAquaticLootTables {
    val FISHING_REEF_FISH_ID = create("gameplay/fishing/reef_fish")
    val FISHING_DEEP_SEA_FISH_ID = create("gameplay/fishing/deep_sea_fish")
    val FISHING_OPEN_OCEAN_FISH_ID = create("gameplay/fishing/open_ocean_fish")
    val FISHING_TROPICAL_FRESHWATER_FISH_ID = create("gameplay/fishing/tropical_freshwater_fish")
    val FISHING_TREASURE_ID = create("gameplay/fishing/treasure")
    val CRAB_POT_TREASURE_ID = create("gameplay/crab_pot_treasure")
    val HYBRID_CRATE_TREASURE_ID = create("gameplay/hybrid_crate_treasure")
    val OAK_CRATE_TREASURE_ID = create("gameplay/oak_crate_treasure")
    val SPRUCE_CRATE_TREASURE_ID = create("gameplay/spruce_crate_treasure")
    val BIRCH_CRATE_TREASURE_ID = create("gameplay/birch_crate_treasure")
    val JUNGLE_CRATE_TREASURE_ID = create("gameplay/jungle_crate_treasure")
    val ACACIA_CRATE_TREASURE_ID = create("gameplay/acacia_crate_treasure")
    val DARK_OAK_CRATE_TREASURE_ID = create("gameplay/dark_oak_crate_treasure")
    val MANGROVE_CRATE_TREASURE_ID = create("gameplay/mangrove_crate_treasure")
    val CHERRY_CRATE_TREASURE_ID = create("gameplay/cherry_crate_treasure")
    val VENT_LOOT_ID = create("gameplay/vent_drops")

    val SURGEONFISH_UNICORNFISH = create("gameplay/surgeonfish_unicornfish")
    val SURGEONFISH_SOHAL = create("gameplay/surgeonfish_sohal")
    val SURGEONFISH_ORANGESHOULDER = create("gameplay/surgeonfish_orangeshoulder")
    val SURGEONFISH_LINED = create("gameplay/surgeonfish_lined")
    val SURGEONFISH_YELLOW_TANG = create("gameplay/surgeonfish_yellow_tang")
    val SURGEONFISH_BLUE_TANG = create("gameplay/surgeonfish_blue_tang")
    val SURGEONFISH_POWDER_BLUE_TANG = create("gameplay/surgeonfish_powder_blue_tang")

    val KOI = create("gameplay/koi")
    val CARP = create("gameplay/carp")

    val CLAWED_LOBSTER = create("gameplay/clawed_lobster")
    val CLAWLESS_LOBSTER = create("gameplay/clawless_lobster")
    val HERMIT_CRAB_SKULL = create("gameplay/hermit_crab_skull")
    val HERMIT_CRAB_SHELL = create("gameplay/hermit_crab_shell")
    val DECORATOR_FIRE = create("gameplay/decorator_fire")
    val DECORATOR_BRAIN = create("gameplay/decorator_brain")
    val DECORATOR_TUBE = create("gameplay/decorator_tube")
    val DECORATOR_BUBBLE = create("gameplay/decorator_bubble")
    val DECORATOR_HORN = create("gameplay/decorator_horn")
    val DECORATOR_THORN = create("gameplay/decorator_thorn")
    val DECORATOR_LOPHELIA = create("gameplay/decorator_lophelia")

    val BLUE_SPOTTED_STINGRAY = create("gameplay/blue_spotted_stingray")
    val SPOTTED_EAGLE_RAY = create("gameplay/spotted_eagle_ray")

    val YELLOWFIN = create("gameplay/yellowfin")
    val BLUEFIN = create("gameplay/bluefin")

    val CRAB_DIGGING_TREASURE_ID = create("gameplay/crab_digging_treasure")

    fun create(id: String): RegistryKey<LootTable> = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(HybridAquatic.MOD_ID, id))
}
