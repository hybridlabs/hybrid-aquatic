package dev.hybridlabs.aquatic.loot

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.loot.LootTable
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier

object HybridAquaticLootTables {
    val FISHING_FISH_ID = create("gameplay/fishing/fish")
    val FISHING_TREASURE_ID = create("gameplay/fishing/treasure")

    val BLUE_TANG = create("gameplay/blue_tang")
    val SOHAL = create("gameplay/sohal")
    val ORANGESHOULDER = create("gameplay/orangeshoulder")
    val YELLOW_TANG = create("gameplay/yellow_tang")
    val POWDER_BLUE_TANG = create("gameplay/powder_blue_tang")
    val UNICORNFISH = create("gameplay/unicornfish")

    val PARROTFISH = create("gameplay/parrotfish")
    val SEAHORSE = create("gameplay/seahorse")

    val SUNFISH = create("gameplay/sunfish")

    val KOI = create("gameplay/koi")
    val GOLDFISH = create("gameplay/goldfish")

    val CLAWED_LOBSTER = create("gameplay/clawed_lobster")
    val CLAWLESS_LOBSTER = create("gameplay/clawless_lobster")
    val HERMIT_CRAB_SKULL = create("gameplay/hermit_crab_skull")
    val HERMIT_CRAB_SHELL = create("gameplay/hermit_crab_shell")

    val BLUE_SPOTTED_STINGRAY = create("gameplay/blue_spotted_stingray")
    val SPOTTED_EAGLE_RAY = create("gameplay/spotted_eagle_ray")

    val CRAB_DIGGING_TREASURE_ID = create("gameplay/crab_digging_treasure")

    private fun create(id: String): RegistryKey<LootTable> {
        return RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(HybridAquatic.MOD_ID, id))
    }
}
