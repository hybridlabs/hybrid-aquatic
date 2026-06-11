package dev.hybridlabs.aquatic.tag

import dev.hybridlabs.aquatic.CommonClass
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.level.biome.Biome

object HABiomeTags {
    //#region Compatibility Biome Tags
    // rainbow reef
    val RR_WARM_OCEANS = TagKey.create(Registries.BIOME, ResourceLocation("rainbowreef", "warm_oceans"))
    //fintastic
    val MOONY_BIOMES = TagKey.create(Registries.BIOME, ResourceLocation("fintastic", "moony_biomes"))
    val PLECO_BIOMES = TagKey.create(Registries.BIOME, ResourceLocation("fintastic", "pleco_biomes"))
    val CATFISH_BIOMES = TagKey.create(Registries.BIOME, ResourceLocation("fintastic", "catfish_biomes"))
    val ARAPAIMA_BIOMES = TagKey.create(Registries.BIOME, ResourceLocation("fintastic", "arapaima_biomes"))
    val GUPPY_BIOMES = TagKey.create(Registries.BIOME, ResourceLocation("fintastic", "guppy_biomes"))
    val FWSHARK_BIOMES = TagKey.create(Registries.BIOME, ResourceLocation("fintastic", "fwshark_biomes"))
    val MINNOW_SURFACE_BIOMES = TagKey.create(Registries.BIOME, ResourceLocation("fintastic", "minnow_surface_biomes"))

    //#region Frozen Ocean Tags
    val SHALLOW_FROZEN_OCEANS = create("shallow_frozen_oceans")
    val FROZEN_OCEANS = create("frozen_oceans")
    val DEEP_FROZEN_OCEANS = create("deep_frozen_oceans")
    val FROZEN_TRENCH = create("frozen_trench")

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

    //#region Lukewarm Ocean Tags
    val SHALLOW_LUKEWARM_OCEANS = create("shallow_lukewarm_oceans")
    val LUKEWARM_OCEANS = create("lukewarm_oceans")
    val DEEP_LUKEWARM_OCEANS = create("deep_lukewarm_oceans")
    val LUKEWARM_TRENCH = create("lukewarm_trench")

    //#region Warm Ocean Tags
    val SHALLOW_WARM_OCEANS = create("shallow_warm_oceans")
    val WARM_OCEANS = create("warm_oceans")
    val DEEP_WARM_OCEANS = create("deep_warm_oceans")
    val WARM_TRENCH = create("warm_trench")
    val CORAL_REEF = create("coral_reef")
    val SEAGRASS_BED = create("seagrass_bed")
    val RED_MEADOW = create("red_meadow")

    //#region Misc Deep Sea Tags
    val ALL_TRENCHES = create("all_trenches")
    val DEEP_REEF = create("deep_reef")
    val HAS_WHALE_FALL = create("has_whale_fall")
    val HAS_THERMAL_VENTS = create("has_thermal_vents")
    val SULFURIC_CAVE = create("sulfuric_cave")

    //#region Beach Tags
    val SANDY_BEACHES = create("sandy_beaches")
    val ROCKY_BEACHES = create("rocky_beaches")
    val TIDE_POOLS = create("tide_pools")

    //#region River Tags
    val RIVERS = create("rivers")
    val TROPICAL_RIVERS = create("tropical_rivers")
    val COLD_RIVERS = create("cold_rivers")

    //#region Misc Biome Tags
    val JUNGLE = create("jungle")
    val CHERRY = create("cherry")
    val CAVES = create("caves")
    val SWAMP = create("swamp")
    val MARSHES = create("marshes")
    val MANGROVES = create("mangroves")
    val HA_DEEP_OCEANS = create("ha_deep_oceans")

    //#region Misc Tags
    val BOTTLE_SPAWN_BIOMES = create("bottle_spawn_biomes")

    private fun create(id: String): TagKey<Biome> {
        return TagKey.create(Registries.BIOME, CommonClass.locate(id))
    }
}