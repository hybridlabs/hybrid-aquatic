package dev.hybridlabs.aquatic.tag

import dev.hybridlabs.aquatic.CommonClass
import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.world.level.biome.Biome

object HybridAquaticBiomeTags {

    //#region Arctic Ocean Tags
    val SHALLOW_ARCTIC_OCEANS = create("shallow_arctic_oceans")
    val ARCTIC_OCEANS = create("arctic_oceans")
    val DEEP_ARCTIC_OCEANS = create("deep_arctic_oceans")
    val ARCTIC_TRENCH = create("arctic_trench")

    //#region Cold Ocean Tags
    val SHALLOW_COLD_OCEANS = create("shallow_cold_oceans")
    val COLD_OCEANS = create("cold_oceans")
    val DEEP_COLD_OCEANS = create("deep_cold_oceans")
    val COLD_TRENCH = create("cold_trench")

    //#region Temperate Ocean Tags
    val SHALLOW_TEMPERATE_OCEANS = create("shallow_temperate_oceans")
    val TEMPERATE_OCEANS = create("temperate_oceans")
    val DEEP_TEMPERATE_OCEANS = create("deep_temperate_oceans")
    val TEMPERATE_TRENCH = create("temperate_trench")

    //#region Tropical Ocean Tags
    val SHALLOW_TROPICAL_OCEANS = create("shallow_tropical_oceans")
    val TROPICAL_OCEANS = create("tropical_oceans")
    val DEEP_TROPICAL_OCEANS = create("deep_tropical_oceans")
    val TROPICAL_TRENCH = create("tropical_trench")

    //#region Warm Ocean Tags
    val WARM_OCEAN = create("warm_ocean")
    val REEF = create("reef")
    val SEAGRASS_BED = create("seagrass_bed")
    val RED_MEADOW = create("red_meadow")

    //#region Misc Deep Sea Tags
    val ALL_TRENCHES = create("all_trenches")
    val DEEP_REEF = create("deep_reef")
    val HAS_THERMAL_VENTS = create("has_thermal_vents")
    val SULFURIC_CAVE = create("sulfuric_cave")

    //#region Beach Tags
    val SANDY_BEACHES = create("sandy_beaches")
    val ROCKY_BEACHES = create("rocky_beaches")
    val TIDE_POOLS = create("tide_pools")

    //#region River Tags
    val RIVERS = create("rivers")
    val PLACER_RIVERS = create("placer_rivers")
    val TROPICAL_RIVERS = create("tropical_rivers")

    //#region Misc Biome Tags
    val JUNGLE = create("jungle")
    val CHERRY = create("cherry")
    val CAVES = create("caves")
    val SWAMP = create("swamp")
    val MARSHES = create("marshes")
    val MANGROVES = create("mangroves")

    //#region Misc Tags
    val BOTTLE_SPAWN_BIOMES = create("bottle_spawn_biomes")

    private fun create(id: String): TagKey<Biome> {
        return TagKey.create(Registries.BIOME, CommonClass.locate(id))
    }
}