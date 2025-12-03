package dev.hybridlabs.aquatic.config

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.platform.Services
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.minecraft.tags.BiomeTags
import net.minecraft.tags.TagKey
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.biome.Biome

/**
 * Applies biome modifications for entities when initialised.
 */
@Suppress("SameParameterValue")
class EntitySpawnConfigGenerator {
    private val list: MutableList<EntitySpawnConfig> = mutableListOf()

    fun finalizeSpawn() {
        //#region Fish
        addFish(HybridAquaticEntityTypes.AFRICAN_BUTTERFLYFISH.get(), listOf(HybridAquaticBiomeTags.JUNGLE, HybridAquaticBiomeTags.MARSHES, HybridAquaticBiomeTags.MANGROVES), 2, 1, 1)
        addFish(HybridAquaticEntityTypes.GOLDEN_DORADO.get(), listOf(HybridAquaticBiomeTags.TROPICAL_RIVERS, HybridAquaticBiomeTags.RIVERS, HybridAquaticBiomeTags.JUNGLE), 1, 1, 1)
        addFish(HybridAquaticEntityTypes.TETRA.get(), listOf(HybridAquaticBiomeTags.JUNGLE, HybridAquaticBiomeTags.MARSHES, HybridAquaticBiomeTags.MANGROVES, HybridAquaticBiomeTags.TROPICAL_RIVERS, HybridAquaticBiomeTags.CAVES), 3, 2, 3)
        addFish(HybridAquaticEntityTypes.PUPFISH.get(), listOf(BiomeTags.IS_BADLANDS), 3, 1, 1)
        addFish(HybridAquaticEntityTypes.TIGER_BARB.get(), listOf(HybridAquaticBiomeTags.JUNGLE, HybridAquaticBiomeTags.SWAMP, HybridAquaticBiomeTags.MANGROVES, HybridAquaticBiomeTags.MARSHES, HybridAquaticBiomeTags.TROPICAL_RIVERS), 3, 2, 3)
        addFish(HybridAquaticEntityTypes.GOURAMI.get(), listOf(HybridAquaticBiomeTags.JUNGLE, HybridAquaticBiomeTags.MANGROVES, HybridAquaticBiomeTags.MARSHES, HybridAquaticBiomeTags.TROPICAL_RIVERS), 3, 1, 1)
        addFish(HybridAquaticEntityTypes.DISCUS.get(), listOf(HybridAquaticBiomeTags.JUNGLE, HybridAquaticBiomeTags.MANGROVES, HybridAquaticBiomeTags.TROPICAL_RIVERS), 3, 1, 1)
        addFish(HybridAquaticEntityTypes.PIRANHA.get(), listOf(HybridAquaticBiomeTags.JUNGLE, HybridAquaticBiomeTags.TROPICAL_RIVERS), 3, 2, 5)
        addFish(HybridAquaticEntityTypes.OSCAR.get(), listOf(HybridAquaticBiomeTags.JUNGLE, HybridAquaticBiomeTags.MANGROVES, HybridAquaticBiomeTags.TROPICAL_RIVERS), 1, 1, 1)
        addFish(HybridAquaticEntityTypes.DANIO.get(), listOf(HybridAquaticBiomeTags.JUNGLE, HybridAquaticBiomeTags.MANGROVES, HybridAquaticBiomeTags.MARSHES, HybridAquaticBiomeTags.TROPICAL_RIVERS), 3, 2, 3)
        addFish(HybridAquaticEntityTypes.BETTA.get(), listOf(HybridAquaticBiomeTags.SWAMP, HybridAquaticBiomeTags.MANGROVES, HybridAquaticBiomeTags.MARSHES), 1, 1, 1)
        addFish(HybridAquaticEntityTypes.GOLDFISH.get(), listOf(HybridAquaticBiomeTags.CHERRY), 5, 1, 2)
        addFish(HybridAquaticEntityTypes.CARP.get(), listOf(HybridAquaticBiomeTags.CHERRY, HybridAquaticBiomeTags.RIVERS), 5, 1, 2)
        addFish(HybridAquaticEntityTypes.MANTA_RAY.get(), listOf(HybridAquaticBiomeTags.DEEP_TEMPERATE_OCEANS, HybridAquaticBiomeTags.DEEP_TROPICAL_OCEANS), 1, 1, 1)
        addFish(HybridAquaticEntityTypes.FLASHLIGHT_FISH.get(), listOf(HybridAquaticBiomeTags.TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF), 5, 3, 5)
        addFish(HybridAquaticEntityTypes.SQUIRRELFISH.get(), listOf(HybridAquaticBiomeTags.TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF), 5, 3, 5)
        addFish(HybridAquaticEntityTypes.MAHI.get(), listOf(HybridAquaticBiomeTags.DEEP_TROPICAL_OCEANS), 3, 1, 3)
        addFish(HybridAquaticEntityTypes.FLYING_FISH.get(), listOf(HybridAquaticBiomeTags.TROPICAL_OCEANS), 5, 2, 6)
        addFish(HybridAquaticEntityTypes.NEEDLEFISH.get(), listOf(HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS, HybridAquaticBiomeTags.TROPICAL_OCEANS), 3, 1, 3)
        addFish(HybridAquaticEntityTypes.BARRACUDA.get(), listOf(HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS, HybridAquaticBiomeTags.TROPICAL_OCEANS), 2, 1, 1)
        addFish(HybridAquaticEntityTypes.DAMSELFISH.get(), listOf(HybridAquaticBiomeTags.REEF, HybridAquaticBiomeTags.TROPICAL_OCEANS), 5, 3, 5)
        addFish(HybridAquaticEntityTypes.MACKEREL.get(), listOf(HybridAquaticBiomeTags.COLD_OCEANS, HybridAquaticBiomeTags.TEMPERATE_OCEANS), 5, 3, 5)
        addFish(HybridAquaticEntityTypes.HERRING.get(), listOf(HybridAquaticBiomeTags.SHALLOW_COLD_OCEANS, HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS), 5, 3, 5)
        addFish(HybridAquaticEntityTypes.OPAH.get(), listOf(HybridAquaticBiomeTags.DEEP_TEMPERATE_OCEANS, HybridAquaticBiomeTags.DEEP_TROPICAL_OCEANS), 3, 1, 1)
        addFish(HybridAquaticEntityTypes.ROCKFISH.get(), listOf(HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS, HybridAquaticBiomeTags.TROPICAL_OCEANS), 3, 1, 3)
        addFish(HybridAquaticEntityTypes.SEA_BASS.get(), listOf(HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS, HybridAquaticBiomeTags.TROPICAL_OCEANS), 3, 1, 3)
        addFish(HybridAquaticEntityTypes.WRASSE.get(), listOf(HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS), 3, 1, 1)
        addFish(HybridAquaticEntityTypes.SEAHORSE.get(), listOf(HybridAquaticBiomeTags.REEF), 1, 1, 2)
        addFish(HybridAquaticEntityTypes.SEADRAGON.get(), listOf(HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS, HybridAquaticBiomeTags.DEEP_TROPICAL_OCEANS), 1, 1, 2)
        addFish(HybridAquaticEntityTypes.LIONFISH.get(), listOf(HybridAquaticBiomeTags.REEF), 3, 1, 2)
        addFish(HybridAquaticEntityTypes.STONEFISH.get(), listOf(HybridAquaticBiomeTags.REEF), 1, 1, 1)
        addFish(HybridAquaticEntityTypes.STINGRAY.get(), listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF), 3, 1, 2)
        addFish(HybridAquaticEntityTypes.BLOWFISH.get(), listOf(HybridAquaticBiomeTags.REEF), 1, 1, 1)
        addFish(HybridAquaticEntityTypes.SURGEONFISH.get(), listOf(HybridAquaticBiomeTags.REEF), 5, 1, 3)
        addFish(HybridAquaticEntityTypes.CLOWNFISH.get(), listOf(HybridAquaticBiomeTags.REEF), 3, 1, 2)
        addFish(HybridAquaticEntityTypes.BOXFISH.get(), listOf(HybridAquaticBiomeTags.REEF), 1, 1, 1)
        addFish(HybridAquaticEntityTypes.TRIGGERFISH.get(), listOf(HybridAquaticBiomeTags.REEF), 3, 1, 1)
        addFish(HybridAquaticEntityTypes.PARROTFISH.get(), listOf(HybridAquaticBiomeTags.REEF), 5, 1, 2)
        addFish(HybridAquaticEntityTypes.MORAY_EEL.get(), listOf(HybridAquaticBiomeTags.REEF), 1, 1, 1)
        addFish(HybridAquaticEntityTypes.SUNFISH.get(), listOf(HybridAquaticBiomeTags.DEEP_TEMPERATE_OCEANS, HybridAquaticBiomeTags.DEEP_TROPICAL_OCEANS), 1, 1, 1)
        addFish(HybridAquaticEntityTypes.TUNA.get(), listOf(HybridAquaticBiomeTags.DEEP_TEMPERATE_OCEANS, HybridAquaticBiomeTags.DEEP_TROPICAL_OCEANS), 3, 1, 3)

        //#endregion

        //#region Deep Fish
        addUndergroundFish(HybridAquaticEntityTypes.OARFISH.get(), listOf(HybridAquaticBiomeTags.DEEP_TROPICAL_OCEANS, HybridAquaticBiomeTags.DEEP_TEMPERATE_OCEANS), 1, 1, 1)
        addUndergroundFish(HybridAquaticEntityTypes.ANGLERFISH.get(), listOf(BiomeTags.IS_DEEP_OCEAN), 1, 1, 2)
        addUndergroundFish(HybridAquaticEntityTypes.JOHN_DORY.get(), listOf(HybridAquaticBiomeTags.DEEP_TROPICAL_OCEANS, HybridAquaticBiomeTags.DEEP_TEMPERATE_OCEANS), 2, 1, 3)
        addUndergroundFish(HybridAquaticEntityTypes.BARRELEYE.get(), listOf(HybridAquaticBiomeTags.DEEP_TROPICAL_OCEANS, HybridAquaticBiomeTags.DEEP_TEMPERATE_OCEANS), 1, 1, 1)
        addUndergroundFish(HybridAquaticEntityTypes.DRAGONFISH.get(), listOf(HybridAquaticBiomeTags.DEEP_TROPICAL_OCEANS, HybridAquaticBiomeTags.DEEP_TEMPERATE_OCEANS), 1, 1, 2)
        addUndergroundFish(HybridAquaticEntityTypes.RATFISH.get(), listOf(BiomeTags.IS_DEEP_OCEAN), 1, 1, 3)
        addUndergroundFish(HybridAquaticEntityTypes.SEA_ANGEL.get(), listOf(HybridAquaticBiomeTags.ARCTIC_OCEANS, HybridAquaticBiomeTags.DEEP_COLD_OCEANS), 1, 1, 1)
        addUndergroundFish(HybridAquaticEntityTypes.COELACANTH.get(), listOf(HybridAquaticBiomeTags.DEEP_TROPICAL_OCEANS, HybridAquaticBiomeTags.DEEP_TEMPERATE_OCEANS), 1, 1, 2)
        addUndergroundFish(HybridAquaticEntityTypes.SNAILFISH.get(), listOf(BiomeTags.IS_DEEP_OCEAN), 3, 1, 5)
        //#endregion

        //#region Cephalopods
        addCephalopod(HybridAquaticEntityTypes.ARROW_SQUID.get(), listOf(HybridAquaticBiomeTags.COLD_OCEANS, HybridAquaticBiomeTags.ARCTIC_OCEANS), 5, 1, 3)
        addCephalopod(HybridAquaticEntityTypes.FIREFLY_SQUID.get(), listOf(HybridAquaticBiomeTags.TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF), 5, 3, 5)
        addCephalopod(HybridAquaticEntityTypes.CUTTLEFISH.get(), listOf(
            HybridAquaticBiomeTags.REEF,
            HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS,
        ), 1, 1, 2)
        addCephalopod(HybridAquaticEntityTypes.OCTOPUS.get(), listOf(HybridAquaticBiomeTags.REEF, HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS), 1, 1, 2)
        //#endregion

        //#region Deep Cephalopods
        addUndergroundCephalopod(HybridAquaticEntityTypes.VAMPIRE_SQUID.get(), listOf(HybridAquaticBiomeTags.TROPICAL_OCEANS), 2, 1, 1)
        addUndergroundCephalopod(HybridAquaticEntityTypes.NAUTILUS.get(), listOf(HybridAquaticBiomeTags.TROPICAL_OCEANS), 3, 1, 2)
        addUndergroundCephalopod(HybridAquaticEntityTypes.UMBRELLA_OCTOPUS.get(), listOf(HybridAquaticBiomeTags.TROPICAL_OCEANS), 1, 1, 1)
        //#endregion

        //#region Jellyfish
        addJelly(HybridAquaticEntityTypes.BARREL_JELLYFISH.get(), listOf(HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS, HybridAquaticBiomeTags.TROPICAL_OCEANS), 5, 1, 2)
        addJelly(HybridAquaticEntityTypes.MOON_JELLYFISH.get(), listOf(HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS, HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF, HybridAquaticBiomeTags.SANDY_BEACHES), 5, 3, 5)
        addJelly(HybridAquaticEntityTypes.CEPHEIDAE_JELLYFISH.get(), listOf(HybridAquaticBiomeTags.TROPICAL_OCEANS, HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS), 5, 1, 3)
        addJelly(HybridAquaticEntityTypes.BLUE_JELLYFISH.get(), listOf(HybridAquaticBiomeTags.COLD_OCEANS, HybridAquaticBiomeTags.TEMPERATE_OCEANS), 3, 1, 2)
        addJelly(HybridAquaticEntityTypes.SEA_NETTLE.get(), listOf(HybridAquaticBiomeTags.TROPICAL_OCEANS, HybridAquaticBiomeTags.TEMPERATE_OCEANS), 5, 3, 5)
        addJelly(HybridAquaticEntityTypes.NOMURA_JELLYFISH.get(), listOf(HybridAquaticBiomeTags.ARCTIC_OCEANS, HybridAquaticBiomeTags.COLD_OCEANS), 1, 1, 2)
        addJelly(HybridAquaticEntityTypes.LIONS_MANE_JELLYFISH.get(), listOf(HybridAquaticBiomeTags.ARCTIC_OCEANS, HybridAquaticBiomeTags.COLD_OCEANS), 1, 1, 2)
        addJelly(HybridAquaticEntityTypes.BOX_JELLYFISH.get(), listOf(HybridAquaticBiomeTags.MANGROVES, HybridAquaticBiomeTags.REEF, HybridAquaticBiomeTags.TROPICAL_OCEANS, HybridAquaticBiomeTags.SANDY_BEACHES), 1, 1, 1)

        addUndergroundJelly(HybridAquaticEntityTypes.CROWN_JELLYFISH.get(), listOf(BiomeTags.IS_DEEP_OCEAN), 1, 1, 1)
        addUndergroundJelly(HybridAquaticEntityTypes.BIG_RED_JELLYFISH.get(), listOf(BiomeTags.IS_DEEP_OCEAN), 1, 1, 1)
        addUndergroundJelly(HybridAquaticEntityTypes.COSMIC_JELLYFISH.get(), listOf(BiomeTags.IS_DEEP_OCEAN), 2, 1, 1)
        addUndergroundJelly(HybridAquaticEntityTypes.FIREWORK_JELLYFISH.get(), listOf(BiomeTags.IS_DEEP_OCEAN), 2, 1, 1)
        addUndergroundJelly(HybridAquaticEntityTypes.MAUVE_STINGER.get(), listOf(BiomeTags.IS_DEEP_OCEAN), 3, 1, 1)
        //#endregion

        //#region Sharks
        addShark(HybridAquaticEntityTypes.GREAT_WHITE_SHARK.get(), listOf(HybridAquaticBiomeTags.DEEP_TEMPERATE_OCEANS, HybridAquaticBiomeTags.DEEP_TROPICAL_OCEANS), 1, 1, 1)
        addShark(HybridAquaticEntityTypes.TIGER_SHARK.get(), listOf(HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS, HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS), 2, 1, 2)
        addShark(HybridAquaticEntityTypes.HAMMERHEAD_SHARK.get(), listOf(HybridAquaticBiomeTags.TEMPERATE_OCEANS, HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS), 3, 1, 3)
        addShark(HybridAquaticEntityTypes.HOUND_SHARK.get(), listOf(HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS, HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS), 3, 1, 3)
        addShark(HybridAquaticEntityTypes.THRESHER_SHARK.get(), listOf(HybridAquaticBiomeTags.DEEP_TEMPERATE_OCEANS, HybridAquaticBiomeTags.DEEP_TROPICAL_OCEANS), 3, 1, 1)
        addShark(HybridAquaticEntityTypes.BULL_SHARK.get(), listOf(HybridAquaticBiomeTags.DEEP_TROPICAL_OCEANS), 2, 1, 1)
        addShark(HybridAquaticEntityTypes.WHALE_SHARK.get(), listOf(HybridAquaticBiomeTags.DEEP_TROPICAL_OCEANS), 1, 1, 1)
        addShark(HybridAquaticEntityTypes.BASKING_SHARK.get(), listOf(HybridAquaticBiomeTags.DEEP_TEMPERATE_OCEANS, HybridAquaticBiomeTags.COLD_OCEANS, HybridAquaticBiomeTags.ARCTIC_OCEANS), 1, 1, 1)
        //#endregion

        //#region Deep Sharks
        addUndergroundShark(HybridAquaticEntityTypes.FRILLED_SHARK.get(), listOf(HybridAquaticBiomeTags.DEEP_TROPICAL_OCEANS), 3, 1, 1)
        addUndergroundShark(HybridAquaticEntityTypes.LANTERN_SHARK.get(), listOf(HybridAquaticBiomeTags.DEEP_TEMPERATE_OCEANS, HybridAquaticBiomeTags.DEEP_TROPICAL_OCEANS), 3, 1, 2)
        //#endregion

        //#region Mammal
        addMammal(HybridAquaticEntityTypes.OTTER.get(), listOf(HybridAquaticBiomeTags.RIVERS, HybridAquaticBiomeTags.SANDY_BEACHES, HybridAquaticBiomeTags.ROCKY_BEACHES), 5, 1, 4)
        //#endregion

        //#region Crustaceans
        addCrustacean(HybridAquaticEntityTypes.DUNGENESS_CRAB.get(), listOf(HybridAquaticBiomeTags.SANDY_BEACHES), 5, 2, 3)
        addCrustacean(HybridAquaticEntityTypes.FIDDLER_CRAB.get(), listOf(HybridAquaticBiomeTags.SWAMP, HybridAquaticBiomeTags.MANGROVES, HybridAquaticBiomeTags.MARSHES), 5, 2, 3)
        addCrustacean(HybridAquaticEntityTypes.GHOST_CRAB.get(), listOf(HybridAquaticBiomeTags.SANDY_BEACHES), 5, 1, 3)
        addCrustacean(HybridAquaticEntityTypes.HORSESHOE_CRAB.get(), listOf(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.SANDY_BEACHES), 3, 1, 1)
        addCrustacean(HybridAquaticEntityTypes.LIGHTFOOT_CRAB.get(), listOf(HybridAquaticBiomeTags.ROCKY_BEACHES), 3, 1, 3)
        addCrustacean(HybridAquaticEntityTypes.FLOWER_CRAB.get(), listOf(HybridAquaticBiomeTags.REEF, HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS, HybridAquaticBiomeTags.MANGROVES, HybridAquaticBiomeTags.MARSHES), 3, 1, 2)
        addCrustacean(HybridAquaticEntityTypes.VAMPIRE_CRAB.get(), listOf(HybridAquaticBiomeTags.JUNGLE, HybridAquaticBiomeTags.TROPICAL_RIVERS), 3, 1, 2)
        addCrustacean(HybridAquaticEntityTypes.SHRIMP.get(), listOf(HybridAquaticBiomeTags.REEF), 3, 2, 3)
        addCrustacean(HybridAquaticEntityTypes.LOBSTER.get(), listOf(HybridAquaticBiomeTags.REEF,  HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS), 3, 1, 2)
        addCrustacean(HybridAquaticEntityTypes.DECORATOR_CRAB.get(), listOf(HybridAquaticBiomeTags.REEF), 1, 1, 2)
        addCrustacean(HybridAquaticEntityTypes.CRAYFISH.get(), listOf(HybridAquaticBiomeTags.RIVERS), 5, 2, 3)
        addCrustacean(HybridAquaticEntityTypes.COCONUT_CRAB.get(), listOf(HybridAquaticBiomeTags.SANDY_BEACHES), 3, 1, 2)
        addCrustacean(HybridAquaticEntityTypes.HERMIT_CRAB.get(), listOf(HybridAquaticBiomeTags.SANDY_BEACHES, HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS), 3, 1, 2)
        //#endregion

        //#region Deep Crustaceans
        addUndergroundCrustacean(HybridAquaticEntityTypes.YETI_CRAB.get(), listOf(HybridAquaticBiomeTags.DEEP_COLD_OCEANS, HybridAquaticBiomeTags.DEEP_ARCTIC_OCEANS), 3, 2, 2)
        addUndergroundCrustacean(HybridAquaticEntityTypes.GIANT_ISOPOD.get(), listOf(HybridAquaticBiomeTags.COLD_OCEANS, HybridAquaticBiomeTags.TEMPERATE_OCEANS), 3, 1, 3)
        addUndergroundCrustacean(HybridAquaticEntityTypes.SPIDER_CRAB.get(), listOf(HybridAquaticBiomeTags.TEMPERATE_OCEANS, HybridAquaticBiomeTags.COLD_OCEANS), 3, 1, 2)
        //#endregion

        //#region Critters
        addCritter(HybridAquaticEntityTypes.SEA_SLUG.get(), listOf(HybridAquaticBiomeTags.SHALLOW_COLD_OCEANS, HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS, HybridAquaticBiomeTags.TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF), 3, 1, 2)
        addCritter(HybridAquaticEntityTypes.STARFISH.get(), listOf(BiomeTags.IS_OCEAN, BiomeTags.IS_DEEP_OCEAN, HybridAquaticBiomeTags.SANDY_BEACHES), 5, 1, 3)
        addCritter(HybridAquaticEntityTypes.SEA_CUCUMBER.get(), listOf(BiomeTags.IS_OCEAN, BiomeTags.IS_DEEP_OCEAN), 3, 1, 3)
        addCritter(HybridAquaticEntityTypes.SEA_URCHIN.get(), listOf(BiomeTags.IS_OCEAN, BiomeTags.IS_DEEP_OCEAN), 5, 1, 3)
        //#endregion
    }

    private fun addFish(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int
    ) {
        add(entityType, spawnTags, Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"), weight, minGroup, maxGroup)
    }

    private fun addUndergroundFish(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int
    ) {
        add(
            entityType,
            spawnTags,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH_UNDERGROUND"),
            weight,
            minGroup,
            maxGroup
        )
    }

    private fun addCephalopod(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int
    ) {
        add(
            entityType, spawnTags,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
            weight, minGroup, maxGroup
        )
    }

    private fun addUndergroundCephalopod(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int
    ) {
        add(
            entityType, spawnTags,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CEPHALOPOD"),
            weight, minGroup, maxGroup
        )
    }

    private fun addShark(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int
    ) {
        add(
            entityType, spawnTags,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_SHARK"),
            weight, minGroup, maxGroup
        )
    }

    private fun addUndergroundShark(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int
    ) {
        add(
            entityType, spawnTags,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_SHARK_UNDERGROUND"),
            weight, minGroup, maxGroup
        )
    }

    private fun addMammal(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int
    ) {
        add(entityType, spawnTags, Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_MAMMAL"), weight, minGroup, maxGroup)
    }

    private fun addJelly(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int
    ) {
        add(
            entityType, spawnTags,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_JELLY"),
            weight, minGroup, maxGroup
        )
    }

    private fun addUndergroundJelly(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int
    ) {
        add(
            entityType, spawnTags,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_JELLY_UNDERGROUND"),
            weight, minGroup, maxGroup
        )
    }

    private fun addCrustacean(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int
    ) {
        add(
            entityType, spawnTags,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CRUSTACEAN"),
            weight, minGroup, maxGroup
        )
    }

    private fun addUndergroundCrustacean(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int
    ) {
        add(
            entityType, spawnTags,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CRUSTACEAN_UNDERGROUND"),
            weight, minGroup, maxGroup
        )
    }

    private fun addCritter(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int
    ) {
        add(
            entityType, spawnTags,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CRITTER"),
            weight, minGroup, maxGroup
        )
    }

    private fun add(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        spawnGroup: MobCategory,
        weight: Int,
        minGroup: Int,
        maxGroup: Int
    ) {
        spawnTags.forEach { spawnTag ->
            list.add(EntitySpawnConfig(entityType, spawnTag, spawnGroup, weight, minGroup, maxGroup))
        }
    }

    companion object {
        fun generate(): List<EntitySpawnConfig> {
            val generator = EntitySpawnConfigGenerator()
            generator.finalizeSpawn()
            return generator.list
        }
    }
}