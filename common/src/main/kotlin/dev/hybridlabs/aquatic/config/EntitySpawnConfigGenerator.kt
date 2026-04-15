package dev.hybridlabs.aquatic.config

import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.aquatic.platform.Services
import dev.hybridlabs.aquatic.tag.HABiomeTags
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
        //#region River Fish
        addRiverFish(
            HAEntityTypes.AFRICAN_BUTTERFLYFISH.get(),
            listOf(
                HABiomeTags.TROPICAL_RIVERS,
                HABiomeTags.JUNGLE,
                HABiomeTags.MARSHES,
                HABiomeTags.MANGROVES),
            2, 1, 1
        )

        addRiverFish(
            HAEntityTypes.GOLDEN_DORADO.get(),
            listOf(
                HABiomeTags.TROPICAL_RIVERS,
                HABiomeTags.JUNGLE),
            1, 1, 1
        )

        addRiverFish(
            HAEntityTypes.TETRA.get(),
            listOf(
                HABiomeTags.JUNGLE,
                HABiomeTags.MARSHES,
                HABiomeTags.MANGROVES,
                HABiomeTags.TROPICAL_RIVERS,
                HABiomeTags.CAVES),
            3, 2, 3
        )

        addRiverFish(
            HAEntityTypes.PUPFISH.get(),
            listOf(BiomeTags.IS_BADLANDS),
            1, 0, 1)

        addRiverFish(
            HAEntityTypes.TIGER_BARB.get(),
            listOf(
                HABiomeTags.JUNGLE,
                HABiomeTags.SWAMP,
                HABiomeTags.MANGROVES,
                HABiomeTags.MARSHES,
                HABiomeTags.TROPICAL_RIVERS),
            3, 2, 3
        )

        addRiverFish(
            HAEntityTypes.GOURAMI.get(),
            listOf(
                HABiomeTags.JUNGLE,
                HABiomeTags.MANGROVES,
                HABiomeTags.MARSHES,
                HABiomeTags.TROPICAL_RIVERS),
            1, 1, 1
        )

        addRiverFish(
            HAEntityTypes.PLECO.get(),
            listOf(
                HABiomeTags.SWAMP,
                HABiomeTags.MANGROVES,
                HABiomeTags.JUNGLE,
                HABiomeTags.TROPICAL_RIVERS,
                HABiomeTags.MARSHES),
            1, 1, 2
        )

        addRiverFish(
            HAEntityTypes.DISCUS.get(),
            listOf(
                HABiomeTags.JUNGLE,
                HABiomeTags.MANGROVES,
                HABiomeTags.TROPICAL_RIVERS),
            1, 1, 1
        )

        addRiverFish(
            HAEntityTypes.CORYDORA.get(),
            listOf(
                HABiomeTags.JUNGLE,
                HABiomeTags.TROPICAL_RIVERS),
            2, 1, 3
        )

        addRiverFish(
            HAEntityTypes.PIRANHA.get(),
            listOf(
                HABiomeTags.JUNGLE,
                HABiomeTags.TROPICAL_RIVERS),
            2, 4, 8
        )

        addRiverFish(
            HAEntityTypes.OSCAR.get(),
            listOf(
                HABiomeTags.JUNGLE,
                HABiomeTags.MANGROVES,
                HABiomeTags.TROPICAL_RIVERS),
            1, 1, 1
        )

        addRiverFish(
            HAEntityTypes.DANIO.get(),
            listOf(
                HABiomeTags.JUNGLE,
                HABiomeTags.MANGROVES,
                HABiomeTags.MARSHES,
                HABiomeTags.TROPICAL_RIVERS),
            3, 2, 3
        )

        addRiverFish(
            HAEntityTypes.BETTA.get(),
            listOf(
                HABiomeTags.SWAMP,
                HABiomeTags.MANGROVES,
                HABiomeTags.MARSHES),
            1, 1, 1
        )

        addRiverFish(
            HAEntityTypes.CARP.get(),
            listOf(
                HABiomeTags.RIVERS),
            3, 0, 2
        )

        addRiverFish(
            HAEntityTypes.SUNFISH.get(),
            listOf(
                HABiomeTags.RIVERS),
            3, 1, 2
        )

        addRiverFish(
            HAEntityTypes.TROUT.get(),
            listOf(
                HABiomeTags.COLD_RIVERS,
                BiomeTags.IS_RIVER),
            2, 0, 1
        )

        addRiverFish(
            HAEntityTypes.SHINER.get(),
            listOf(
                HABiomeTags.COLD_RIVERS,
                BiomeTags.IS_RIVER),
            3, 1, 2
        )
        //#endregion

        //#region Marine Fish
        addFish(
            HAEntityTypes.MANTA_RAY.get(),
            listOf(
                HABiomeTags.DEEP_TEMPERATE_OCEANS,
                HABiomeTags.DEEP_LUKEWARM_OCEANS),
            1, 1, 2
        )

        addFish(
            HAEntityTypes.FLASHLIGHT_FISH.get(),
            listOf(
                HABiomeTags.LUKEWARM_OCEANS,
                HABiomeTags.SHALLOW_WARM_OCEANS,
                HABiomeTags.DEEP_REEF,
                HABiomeTags.CORAL_REEF),
            5, 4, 12
        )

        addFish(
            HAEntityTypes.SQUIRRELFISH.get(),
            listOf(
                HABiomeTags.LUKEWARM_OCEANS,
                HABiomeTags.SHALLOW_WARM_OCEANS,
                HABiomeTags.DEEP_REEF,
                HABiomeTags.RED_MEADOW,
                HABiomeTags.CORAL_REEF),
            5, 4, 12
        )

        addFish(
            HAEntityTypes.FLYING_FISH.get(),
            listOf(
                HABiomeTags.SHALLOW_WARM_OCEANS,
                HABiomeTags.LUKEWARM_OCEANS,),
            5, 4, 12
        )

        addFish(
            HAEntityTypes.DAMSELFISH.get(),
            listOf(
                HABiomeTags.SHALLOW_WARM_OCEANS,
                HABiomeTags.CORAL_REEF,
                HABiomeTags.LUKEWARM_OCEANS),
            5, 4, 12
        )

        addFish(
            HAEntityTypes.MACKEREL.get(),
            listOf(
                HABiomeTags.COLD_OCEANS,
                HABiomeTags.TEMPERATE_OCEANS),
            5, 4, 12
        )

        addFish(
            HAEntityTypes.HERRING.get(),
            listOf(
                HABiomeTags.SHALLOW_COLD_OCEANS,
                HABiomeTags.SHALLOW_TEMPERATE_OCEANS),
            5, 4, 12
        )

        addFish(
            HAEntityTypes.NEEDLEFISH.get(),
            listOf(
                HABiomeTags.SHALLOW_TEMPERATE_OCEANS,
                HABiomeTags.LUKEWARM_OCEANS,
                HABiomeTags.SEAGRASS_BED),
            4, 1, 4
        )

        addFish(
            HAEntityTypes.MAHI.get(),
            listOf(
                HABiomeTags.DEEP_LUKEWARM_OCEANS),
            3, 1, 4
        )

        addFish(
            HAEntityTypes.TUNA.get(),
            listOf(
                HABiomeTags.DEEP_TEMPERATE_OCEANS,
                HABiomeTags.DEEP_LUKEWARM_OCEANS),
            3, 1, 4
        )

        addFish(
            HAEntityTypes.ROCKFISH.get(),
            listOf(
                HABiomeTags.SHALLOW_TEMPERATE_OCEANS,
                HABiomeTags.DEEP_REEF,
                HABiomeTags.LUKEWARM_OCEANS),
            3, 1, 4
        )

        addFish(
            HAEntityTypes.SEA_BASS.get(),
            listOf(
                HABiomeTags.SHALLOW_TEMPERATE_OCEANS,
                HABiomeTags.DEEP_REEF,
                HABiomeTags.LUKEWARM_OCEANS),
            3, 1, 3
        )

        addFish(
            HAEntityTypes.BARRACUDA.get(),
            listOf(
                HABiomeTags.SHALLOW_TEMPERATE_OCEANS,
                HABiomeTags.SHALLOW_WARM_OCEANS,
                HABiomeTags.LUKEWARM_OCEANS),
            3, 1, 1
        )

        addFish(
            HAEntityTypes.GARDEN_EEL.get(),
            listOf(
                HABiomeTags.SHALLOW_LUKEWARM_OCEANS,
                HABiomeTags.SHALLOW_WARM_OCEANS,
                HABiomeTags.SEAGRASS_BED),
            5, 3, 8
        )

        addFish(
            HAEntityTypes.OPAH.get(),
            listOf(
                HABiomeTags.DEEP_TEMPERATE_OCEANS,
                HABiomeTags.DEEP_LUKEWARM_OCEANS),
            2, 1, 2
        )

        addFish(
            HAEntityTypes.WRASSE.get(),
            listOf(
                HABiomeTags.SHALLOW_TEMPERATE_OCEANS),
            3, 1, 2
        )

        addFish(
            HAEntityTypes.SEAHORSE.get(),
            listOf(
                HABiomeTags.CORAL_REEF,
                HABiomeTags.SEAGRASS_BED),
            1, 1, 2
        )

        addFish(
            HAEntityTypes.SEADRAGON.get(),
            listOf(
                HABiomeTags.SHALLOW_TEMPERATE_OCEANS,
                HABiomeTags.DEEP_LUKEWARM_OCEANS,
                HABiomeTags.SEAGRASS_BED),
            1, 1, 2
        )

        addFish(
            HAEntityTypes.LIONFISH.get(),
            listOf(
                HABiomeTags.RED_MEADOW,
                HABiomeTags.CORAL_REEF),
            3, 1, 2
        )

        addFish(
            HAEntityTypes.STONEFISH.get(),
            listOf(
                HABiomeTags.CORAL_REEF,
                HABiomeTags.SEAGRASS_BED),
            3, 1, 2
        )

        addFish(
            HAEntityTypes.STINGRAY.get(),
            listOf(
                HABiomeTags.SHALLOW_LUKEWARM_OCEANS,
                HABiomeTags.SHALLOW_WARM_OCEANS,
                HABiomeTags.CORAL_REEF,
                HABiomeTags.RED_MEADOW,
                HABiomeTags.SEAGRASS_BED),
            2, 1, 2
        )

        addFish(
            HAEntityTypes.BLOWFISH.get(),
            listOf(
                HABiomeTags.SHALLOW_WARM_OCEANS,
                HABiomeTags.CORAL_REEF,
                HABiomeTags.SEAGRASS_BED),
            3, 1, 2
        )

        addFish(
            HAEntityTypes.SURGEONFISH.get(),
            listOf(
                HABiomeTags.SEAGRASS_BED,
                HABiomeTags.CORAL_REEF),
            4, 1, 4
        )

        addFish(
            HAEntityTypes.CLOWNFISH.get(),
            listOf(
                HABiomeTags.CORAL_REEF),
            3, 1, 2
        )

        addFish(
            HAEntityTypes.BOXFISH.get(),
            listOf(
                HABiomeTags.SHALLOW_WARM_OCEANS,
                HABiomeTags.CORAL_REEF),
            2, 1, 1
        )

        addFish(
            HAEntityTypes.TRIGGERFISH.get(),
            listOf(
                HABiomeTags.SHALLOW_WARM_OCEANS,
                HABiomeTags.CORAL_REEF,
                HABiomeTags.SEAGRASS_BED),
            3, 1, 1
        )

        addFish(
            HAEntityTypes.TREVALLY.get(),
            listOf(
                HABiomeTags.SHALLOW_WARM_OCEANS,
                HABiomeTags.LUKEWARM_OCEANS,
                HABiomeTags.CORAL_REEF,
                HABiomeTags.SEAGRASS_BED),
            3, 1, 1
        )

        addFish(
            HAEntityTypes.PARROTFISH.get(),
            listOf(
                HABiomeTags.SHALLOW_WARM_OCEANS,
                HABiomeTags.CORAL_REEF),
            4, 1, 2
        )

        addFish(
            HAEntityTypes.MORAY_EEL.get(),
            listOf(
                HABiomeTags.CORAL_REEF,
                HABiomeTags.SEAGRASS_BED),
            3, 1, 2
        )

        addFish(
            HAEntityTypes.OCEAN_SUNFISH.get(),
            listOf(
                HABiomeTags.DEEP_TEMPERATE_OCEANS,
                HABiomeTags.DEEP_LUKEWARM_OCEANS),
            1, 1, 2
        )

        addFish(HAEntityTypes.RATFISH.get(),
            listOf(
                HABiomeTags.DEEP_REEF,
                HABiomeTags.ALL_TRENCHES),
            5, 1, 3
        )

        addFish(
            HAEntityTypes.SNAILFISH.get(),
            listOf(
                HABiomeTags.ALL_TRENCHES),
            5, 1, 3
        )

        addFish(
            HAEntityTypes.OARFISH.get(),
            listOf(
                HABiomeTags.DEEP_LUKEWARM_OCEANS,
                HABiomeTags.LUKEWARM_TRENCH,
                HABiomeTags.WARM_TRENCH,
                HABiomeTags.TEMPERATE_TRENCH,
                HABiomeTags.DEEP_TEMPERATE_OCEANS),
            1, 1, 2
        )

        addFish(
            HAEntityTypes.ANGLERFISH.get(),
            listOf(
                HABiomeTags.ALL_TRENCHES),
            3, 1, 2
        )

        addFish(
            HAEntityTypes.FANGTOOTH.get(),
            listOf(
                HABiomeTags.ALL_TRENCHES),
            3, 1, 2
        )

        addFish(
            HAEntityTypes.VIPERFISH.get(),
            listOf(
                HABiomeTags.ALL_TRENCHES),
            3, 1, 2
        )

        addFish(
            HAEntityTypes.HATCHETFISH.get(),
            listOf(
                HABiomeTags.ALL_TRENCHES),
            3, 1, 2
        )

        addFish(
            HAEntityTypes.TRIPOD_FISH.get(),
            listOf(
                HABiomeTags.ALL_TRENCHES),
            3, 1, 2
        )

        addFish(
            HAEntityTypes.JOHN_DORY.get(),
            listOf(
                HABiomeTags.DEEP_REEF,
                HABiomeTags.DEEP_WARM_OCEANS,
                HABiomeTags.WARM_TRENCH,
                HABiomeTags.LUKEWARM_TRENCH,
                HABiomeTags.DEEP_LUKEWARM_OCEANS,
                HABiomeTags.TEMPERATE_TRENCH,
                HABiomeTags.DEEP_TEMPERATE_OCEANS),
            4, 1, 2
        )

        addFish(
            HAEntityTypes.BARRELEYE.get(),
            listOf(
                HABiomeTags.LUKEWARM_TRENCH,
                HABiomeTags.WARM_TRENCH,
                HABiomeTags.TEMPERATE_TRENCH),
            1, 1, 2
        )

        addFish(
            HAEntityTypes.DRAGONFISH.get(),
            listOf(
                HABiomeTags.LUKEWARM_TRENCH,
                HABiomeTags.WARM_TRENCH,
                HABiomeTags.TEMPERATE_TRENCH),
            2, 1, 2
        )

        addFish(
            HAEntityTypes.SEA_ANGEL.get(),
            listOf(
                HABiomeTags.FROZEN_TRENCH,
                HABiomeTags.COLD_TRENCH,
                HABiomeTags.TEMPERATE_TRENCH),
            1, 1, 2
        )

        addFish(
            HAEntityTypes.COELACANTH.get(),
            listOf(
                HABiomeTags.DEEP_REEF,
                HABiomeTags.LUKEWARM_TRENCH,
                HABiomeTags.WARM_TRENCH,
                HABiomeTags.TEMPERATE_TRENCH),
            1, 1, 2
        )

        addFish(
            HAEntityTypes.SLICKHEAD.get(),
            listOf(
                HABiomeTags.COLD_TRENCH,
                HABiomeTags.TEMPERATE_TRENCH),
            1, 1, 1
        )
        //#endregion

        //#region Cephalopods
        addCephalopod(
            HAEntityTypes.ARROW_SQUID.get(),
            listOf(
                HABiomeTags.TEMPERATE_TRENCH,
                HABiomeTags.TEMPERATE_OCEANS),
            10, 1, 2
        )

        addCephalopod(
            HAEntityTypes.GIANT_SQUID.get(),
            listOf(
                HABiomeTags.COLD_TRENCH,
                HABiomeTags.WARM_TRENCH,
                HABiomeTags.LUKEWARM_TRENCH,
                HABiomeTags.TEMPERATE_TRENCH,),
            1, 1, 1
        )

        addCephalopod(
            HAEntityTypes.COLOSSAL_SQUID.get(),
            listOf(
                HABiomeTags.TEMPERATE_TRENCH,
                HABiomeTags.COLD_TRENCH),
            1, 1, 1
        )

        addCephalopod(
            HAEntityTypes.FIREFLY_SQUID.get(),
            listOf(
                HABiomeTags.LUKEWARM_OCEANS,
                HABiomeTags.LUKEWARM_TRENCH,
                HABiomeTags.SHALLOW_WARM_OCEANS,
                HABiomeTags.WARM_TRENCH,
                HABiomeTags.CORAL_REEF),
            10, 1, 2
        )

        addCephalopod(
            HAEntityTypes.CUTTLEFISH.get(),
            listOf(
                HABiomeTags.SHALLOW_WARM_OCEANS,
                HABiomeTags.CORAL_REEF,
                HABiomeTags.SHALLOW_TEMPERATE_OCEANS),
            10, 1, 1
        )

        addCephalopod(
            HAEntityTypes.OCTOPUS.get(),
            listOf(
                HABiomeTags.CORAL_REEF,
                HABiomeTags.RED_MEADOW,
                HABiomeTags.SHALLOW_LUKEWARM_OCEANS,
                HABiomeTags.SHALLOW_TEMPERATE_OCEANS
            ),
            10, 1, 1
        )
        //#endregion

        //#region Jellyfish
        addJelly(
            HAEntityTypes.BARREL_JELLYFISH.get(),
            listOf(
                HABiomeTags.SHALLOW_TEMPERATE_OCEANS,
                HABiomeTags.SHALLOW_WARM_OCEANS,
                HABiomeTags.RED_MEADOW,
                HABiomeTags.SEAGRASS_BED,
                HABiomeTags.LUKEWARM_OCEANS),
            3, 1, 2
        )

        addJelly(
            HAEntityTypes.MOON_JELLYFISH.get(),
            listOf(
                HABiomeTags.SHALLOW_TEMPERATE_OCEANS,
                HABiomeTags.SHALLOW_LUKEWARM_OCEANS,
                HABiomeTags.SHALLOW_WARM_OCEANS,
                HABiomeTags.CORAL_REEF,
                HABiomeTags.RED_MEADOW,
                HABiomeTags.SEAGRASS_BED,
                HABiomeTags.SANDY_BEACHES
            ),
            5, 2, 5
        )

        addJelly(
            HAEntityTypes.CEPHEIDAE_JELLYFISH.get(),
            listOf(
                HABiomeTags.LUKEWARM_OCEANS,
                HABiomeTags.SHALLOW_WARM_OCEANS,
                HABiomeTags.SEAGRASS_BED,
                HABiomeTags.SHALLOW_TEMPERATE_OCEANS),
            3, 1, 2
        )

        addJelly(
            HAEntityTypes.BLUE_JELLYFISH.get(),
            listOf(
                HABiomeTags.COLD_OCEANS,
                HABiomeTags.TEMPERATE_OCEANS),
            3, 1, 2
        )

        addJelly(
            HAEntityTypes.SEA_NETTLE.get(),
            listOf(
                HABiomeTags.LUKEWARM_OCEANS,
                HABiomeTags.SHALLOW_WARM_OCEANS,
                HABiomeTags.TEMPERATE_OCEANS),
            3, 1, 4
        )

        addJelly(
            HAEntityTypes.NOMURA_JELLYFISH.get(),
            listOf(
                HABiomeTags.FROZEN_OCEANS,
                HABiomeTags.COLD_OCEANS),
            1, 1, 1
        )

        addJelly(
            HAEntityTypes.LIONS_MANE_JELLYFISH.get(),
            listOf(
                HABiomeTags.FROZEN_OCEANS,
                HABiomeTags.COLD_OCEANS),
            1, 1, 1
        )

        addJelly(
            HAEntityTypes.BOX_JELLYFISH.get(),
            listOf(
                HABiomeTags.MANGROVES,
                HABiomeTags.CORAL_REEF,
                HABiomeTags.SHALLOW_WARM_OCEANS,
                HABiomeTags.LUKEWARM_OCEANS,
                HABiomeTags.SANDY_BEACHES
            ),
            1, 1, 1
        )

        addJelly(
            HAEntityTypes.CROWN_JELLYFISH.get(),
            listOf(
                HABiomeTags.TEMPERATE_TRENCH,
                HABiomeTags.LUKEWARM_TRENCH,
                HABiomeTags.WARM_TRENCH,
                HABiomeTags.COLD_TRENCH,
                HABiomeTags.FROZEN_TRENCH),
            1, 1, 1
        )

        addJelly(
            HAEntityTypes.BIG_RED_JELLYFISH.get(),
            listOf(
                HABiomeTags.TEMPERATE_TRENCH,
                HABiomeTags.LUKEWARM_TRENCH,
                HABiomeTags.WARM_TRENCH,
                HABiomeTags.COLD_TRENCH,
                HABiomeTags.FROZEN_TRENCH),
            1, 1, 1
        )

        addJelly(
            HAEntityTypes.COSMIC_JELLYFISH.get(),
            listOf(
                HABiomeTags.DEEP_REEF,
                HABiomeTags.TEMPERATE_TRENCH,
                HABiomeTags.LUKEWARM_TRENCH,
                HABiomeTags.WARM_TRENCH,
                HABiomeTags.COLD_TRENCH,
                HABiomeTags.FROZEN_TRENCH),
            1, 1, 1
        )

        addJelly(
            HAEntityTypes.FIREWORK_JELLYFISH.get(),
            listOf(
                HABiomeTags.DEEP_REEF,
                HABiomeTags.TEMPERATE_TRENCH,
                HABiomeTags.LUKEWARM_TRENCH,
                HABiomeTags.WARM_TRENCH,
                HABiomeTags.COLD_TRENCH,
                HABiomeTags.FROZEN_TRENCH),
            1, 1, 1
        )

        addJelly(
            HAEntityTypes.MAUVE_STINGER.get(),
            listOf(
                HABiomeTags.DEEP_REEF,
                HABiomeTags.TEMPERATE_TRENCH,
                HABiomeTags.LUKEWARM_TRENCH,
                HABiomeTags.WARM_TRENCH,
                HABiomeTags.COLD_TRENCH,
                HABiomeTags.FROZEN_TRENCH),
            2, 1, 3
        )
        //#endregion

        //#region Sharks
        addShark(
            HAEntityTypes.GREAT_WHITE_SHARK.get(),
            listOf(
                HABiomeTags.DEEP_TEMPERATE_OCEANS,
                HABiomeTags.DEEP_LUKEWARM_OCEANS),
            1, 1, 1
        )

        addShark(
            HAEntityTypes.SAND_TIGER_SHARK.get(),
            listOf(
                HABiomeTags.SHALLOW_TEMPERATE_OCEANS,
                HABiomeTags.SHALLOW_WARM_OCEANS,
                HABiomeTags.SEAGRASS_BED,
                HABiomeTags.SHALLOW_LUKEWARM_OCEANS),
            2, 1, 1
        )

        addShark(
            HAEntityTypes.HAMMERHEAD_SHARK.get(),
            listOf(
                HABiomeTags.TEMPERATE_OCEANS,
                HABiomeTags.SHALLOW_WARM_OCEANS,
                HABiomeTags.SEAGRASS_BED,
                HABiomeTags.SHALLOW_LUKEWARM_OCEANS),
            3, 1, 2
        )

        addShark(
            HAEntityTypes.HOUND_SHARK.get(),
            listOf(
                HABiomeTags.SHALLOW_TEMPERATE_OCEANS,
                HABiomeTags.SHALLOW_LUKEWARM_OCEANS),
            3, 1, 2
        )

        addShark(
            HAEntityTypes.THRESHER_SHARK.get(),
            listOf(
                HABiomeTags.DEEP_TEMPERATE_OCEANS,
                HABiomeTags.DEEP_LUKEWARM_OCEANS),
            2, 1, 1
        )

        addShark(
            HAEntityTypes.BULL_SHARK.get(),
            listOf(
                HABiomeTags.DEEP_LUKEWARM_OCEANS),
            2, 1, 2
        )

        addShark(
            HAEntityTypes.WHALE_SHARK.get(),
            listOf(
                HABiomeTags.DEEP_LUKEWARM_OCEANS),
            1, 1, 1
        )

        addShark(
            HAEntityTypes.BASKING_SHARK.get(),
            listOf(
                HABiomeTags.DEEP_TEMPERATE_OCEANS,
                HABiomeTags.COLD_OCEANS,
                HABiomeTags.FROZEN_OCEANS
            ),
            1, 1, 1
        )

        addShark(
            HAEntityTypes.FRILLED_SHARK.get(),
            listOf(
                HABiomeTags.DEEP_REEF,
                HABiomeTags.WARM_TRENCH,
                HABiomeTags.LUKEWARM_TRENCH),
            1, 1, 1
        )

        addShark(
            HAEntityTypes.SIXGILL_SHARK.get(),
            listOf(
                HABiomeTags.TEMPERATE_TRENCH,
                HABiomeTags.WARM_TRENCH,
                HABiomeTags.LUKEWARM_TRENCH),
            1, 1, 1
        )

        addShark(
            HAEntityTypes.SLEEPER_SHARK.get(),
            listOf(
                HABiomeTags.FROZEN_TRENCH,
                HABiomeTags.COLD_TRENCH),
            1, 1, 1
        )

        addShark(
            HAEntityTypes.LANTERN_SHARK.get(),
            listOf(
                HABiomeTags.DEEP_REEF,
                HABiomeTags.WARM_TRENCH,
                HABiomeTags.DEEP_TEMPERATE_OCEANS,
                HABiomeTags.TEMPERATE_TRENCH,
                HABiomeTags.DEEP_LUKEWARM_OCEANS,
                HABiomeTags.LUKEWARM_TRENCH),
            2, 1, 2
        )
        //#endregion

        //#region Mammal
        addMammal(
            HAEntityTypes.OTTER.get(),
            listOf(
                HABiomeTags.RIVERS,
                HABiomeTags.SANDY_BEACHES,
                HABiomeTags.ROCKY_BEACHES
            ),
            1, 1, 2
        )

        addSirenian(
            HAEntityTypes.DUGONG.get(),
            listOf(
                HABiomeTags.SEAGRASS_BED
            ),
            1, 1, 2
        )

        addSirenian(
            HAEntityTypes.MANATEE.get(),
            listOf(
                HABiomeTags.SEAGRASS_BED,
                HABiomeTags.MANGROVES,
                HABiomeTags.TROPICAL_RIVERS
            ),
            1, 1, 2
        )

        addDolphin(
            HAEntityTypes.ORCA.get(),
            listOf(
                HABiomeTags.FROZEN_OCEANS,
                HABiomeTags.COLD_OCEANS,
                HABiomeTags.TEMPERATE_OCEANS,
            ),
            1, 1, 3
        )
        //#endregion

        //#region Crustaceans
        addCrustacean(
            HAEntityTypes.DUNGENESS_CRAB.get(),
            listOf(
                HABiomeTags.SANDY_BEACHES),
            3, 1, 2
        )

        addCrustacean(
            HAEntityTypes.FIDDLER_CRAB.get(),
            listOf(
                HABiomeTags.SWAMP,
                HABiomeTags.MANGROVES,
                HABiomeTags.MARSHES),
            3, 1, 2
        )

        addCrustacean(
            HAEntityTypes.GHOST_CRAB.get(),
            listOf(
                HABiomeTags.SANDY_BEACHES),
            3, 1, 2
        )

        addCrustacean(
            HAEntityTypes.HORSESHOE_CRAB.get(),
            listOf(
                HABiomeTags.SHALLOW_WARM_OCEANS,
                HABiomeTags.RED_MEADOW,
                HABiomeTags.SEAGRASS_BED,
                HABiomeTags.SHALLOW_LUKEWARM_OCEANS,
                HABiomeTags.SANDY_BEACHES),
            3, 1, 2
        )

        addCrustacean(
            HAEntityTypes.LIGHTFOOT_CRAB.get(),
            listOf(
                HABiomeTags.ROCKY_BEACHES),
            3, 1, 2
        )

        addCrustacean(
            HAEntityTypes.FLOWER_CRAB.get(),
            listOf(
                HABiomeTags.SHALLOW_WARM_OCEANS,
                HABiomeTags.RED_MEADOW,
                HABiomeTags.SEAGRASS_BED,
                HABiomeTags.SHALLOW_LUKEWARM_OCEANS,
                HABiomeTags.MANGROVES,
                HABiomeTags.MARSHES),
            3, 1, 2
        )

        addCrustacean(
            HAEntityTypes.VAMPIRE_CRAB.get(),
            listOf(
                HABiomeTags.JUNGLE,
                HABiomeTags.TROPICAL_RIVERS),
            1, 1, 2
        )

        addCrustacean(
            HAEntityTypes.SHRIMP.get(),
            listOf(
                HABiomeTags.TROPICAL_RIVERS,
                HABiomeTags.DEEP_REEF,
                HABiomeTags.SHALLOW_WARM_OCEANS,
                HABiomeTags.RED_MEADOW,
                HABiomeTags.SEAGRASS_BED,
                HABiomeTags.CORAL_REEF),
            3, 2, 3
        )

        addCrustacean(
            HAEntityTypes.LOBSTER.get(),
            listOf(
                HABiomeTags.DEEP_REEF,
                HABiomeTags.SHALLOW_WARM_OCEANS,
                HABiomeTags.CORAL_REEF,
                HABiomeTags.RED_MEADOW,
                HABiomeTags.SEAGRASS_BED,
                HABiomeTags.SHALLOW_LUKEWARM_OCEANS),
            2, 1, 2
        )
        addCrustacean(
            HAEntityTypes.DECORATOR_CRAB.get(),
            listOf(
                HABiomeTags.CORAL_REEF,
                HABiomeTags.RED_MEADOW),
            2, 1, 2
        )

        addCrustacean(
            HAEntityTypes.CRAYFISH.get(),
            listOf(
                HABiomeTags.TROPICAL_RIVERS,
                HABiomeTags.COLD_RIVERS,
                HABiomeTags.RIVERS),
            2, 1, 2
        )

        addCrustacean(
            HAEntityTypes.COCONUT_CRAB.get(),
            listOf(
                HABiomeTags.SANDY_BEACHES),
            2, 1, 2
        )

        addCrustacean(
            HAEntityTypes.HERMIT_CRAB.get(),
            listOf(
                HABiomeTags.SANDY_BEACHES,
                HABiomeTags.RED_MEADOW,
                HABiomeTags.SEAGRASS_BED,
                HABiomeTags.SHALLOW_LUKEWARM_OCEANS),
            1, 1, 2
        )

        addCrustacean(
            HAEntityTypes.YETI_CRAB.get(),
            listOf(
                HABiomeTags.HAS_THERMAL_VENTS,
                HABiomeTags.FROZEN_TRENCH,
                HABiomeTags.COLD_TRENCH),
            2, 1, 2
        )

        addCrustacean(
            HAEntityTypes.GIANT_ISOPOD.get(),
            listOf(
                HABiomeTags.DEEP_REEF,
                HABiomeTags.WARM_TRENCH,
                HABiomeTags.TEMPERATE_OCEANS,
                HABiomeTags.TEMPERATE_TRENCH,
                HABiomeTags.COLD_TRENCH,
                HABiomeTags.COLD_OCEANS),
            1, 1, 2
        )

        addCrustacean(
            HAEntityTypes.SPIDER_CRAB.get(),
            listOf(
                HABiomeTags.DEEP_REEF,
                HABiomeTags.WARM_TRENCH,
                HABiomeTags.TEMPERATE_OCEANS,
                HABiomeTags.TEMPERATE_TRENCH,
                HABiomeTags.COLD_TRENCH,
                HABiomeTags.COLD_OCEANS),
            2, 1, 2
        )
        //#endregion

        //#region Critters
        addCritter(
            HAEntityTypes.SEA_SLUG.get(),
            listOf(
                HABiomeTags.SHALLOW_COLD_OCEANS,
                HABiomeTags.SHALLOW_TEMPERATE_OCEANS,
                HABiomeTags.LUKEWARM_OCEANS,
                HABiomeTags.SHALLOW_WARM_OCEANS,
                HABiomeTags.SEAGRASS_BED,
                HABiomeTags.RED_MEADOW,
                HABiomeTags.CORAL_REEF),
            1, 0, 2
        )

        addCritter(
            HAEntityTypes.SCALYFOOT_SNAIL.get(),
            listOf(
                HABiomeTags.HAS_THERMAL_VENTS),
            1, 0, 2
        )

        addCritter(
            HAEntityTypes.STARFISH.get(),
            listOf(
                BiomeTags.IS_OCEAN,
                BiomeTags.IS_DEEP_OCEAN,
                HABiomeTags.TEMPERATE_TRENCH,
                HABiomeTags.LUKEWARM_TRENCH,
                HABiomeTags.WARM_TRENCH,
                HABiomeTags.COLD_TRENCH,
                HABiomeTags.FROZEN_TRENCH,
                HABiomeTags.SANDY_BEACHES),
            2, 0, 2
        )

        addCritter(
            HAEntityTypes.SEA_CUCUMBER.get(),
            listOf(
                BiomeTags.IS_OCEAN,
                BiomeTags.IS_DEEP_OCEAN,
                HABiomeTags.RED_MEADOW,
                HABiomeTags.TEMPERATE_TRENCH,
                HABiomeTags.LUKEWARM_TRENCH,
                HABiomeTags.WARM_TRENCH,
                HABiomeTags.COLD_TRENCH,
                HABiomeTags.FROZEN_TRENCH),
            3, 1, 2
        )

        addCritter(
            HAEntityTypes.SEA_URCHIN.get(),
            listOf(
                BiomeTags.IS_OCEAN,
                BiomeTags.IS_DEEP_OCEAN,
                HABiomeTags.RED_MEADOW,
                HABiomeTags.TEMPERATE_TRENCH,
                HABiomeTags.LUKEWARM_TRENCH,
                HABiomeTags.WARM_TRENCH,
                HABiomeTags.COLD_TRENCH,
                HABiomeTags.FROZEN_TRENCH),
            3, 0, 2
        )
        //#endregion
    }

    private fun addFish(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int,
    ) {
        add(
            entityType,
            spawnTags,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_FISH"),
            weight,
            minGroup,
            maxGroup
        )
    }

    private fun addRiverFish(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int,
    ) {
        add(
            entityType,
            spawnTags,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_RIVER_FISH"),
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
        maxGroup: Int,
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
        maxGroup: Int,
    ) {
        add(
            entityType, spawnTags,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_SHARK"),
            weight, minGroup, maxGroup
        )
    }

    private fun addMammal(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int,
    ) {
        add(
            entityType,
            spawnTags,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_MAMMAL"),
            weight,
            minGroup,
            maxGroup
        )
    }

    private fun addSirenian(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int,
    ) {
        add(
            entityType,
            spawnTags,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_MAMMAL"),
            weight,
            minGroup,
            maxGroup
        )
    }

    private fun addDolphin(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int,
    ) {
        add(
            entityType,
            spawnTags,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_MAMMAL"),
            weight,
            minGroup,
            maxGroup
        )
    }

    private fun addJelly(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int,
    ) {
        add(
            entityType, spawnTags,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_JELLY"),
            weight, minGroup, maxGroup
        )
    }

    private fun addCrustacean(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int,
    ) {
        add(
            entityType, spawnTags,
            Services.PLATFORM.getMobCategoryByName("HYBRID_AQUATIC_CRUSTACEAN"),
            weight, minGroup, maxGroup
        )
    }

    private fun addCritter(
        entityType: EntityType<*>,
        spawnTags: List<TagKey<Biome>>,
        weight: Int,
        minGroup: Int,
        maxGroup: Int,
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
        maxGroup: Int,
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