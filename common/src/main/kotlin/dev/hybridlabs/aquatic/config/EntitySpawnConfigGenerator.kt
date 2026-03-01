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
        addRiverFish(
            HybridAquaticEntityTypes.AFRICAN_BUTTERFLYFISH.get(),
            listOf(
                HybridAquaticBiomeTags.JUNGLE,
                HybridAquaticBiomeTags.MARSHES,
                HybridAquaticBiomeTags.MANGROVES),
            2, 1, 1
        )

        addRiverFish(
            HybridAquaticEntityTypes.GOLDEN_DORADO.get(),
            listOf(
                HybridAquaticBiomeTags.TROPICAL_RIVERS,
                HybridAquaticBiomeTags.JUNGLE),
            1, 1, 1
        )

        addRiverFish(
            HybridAquaticEntityTypes.TETRA.get(),
            listOf(
                HybridAquaticBiomeTags.JUNGLE,
                HybridAquaticBiomeTags.MARSHES,
                HybridAquaticBiomeTags.MANGROVES,
                HybridAquaticBiomeTags.TROPICAL_RIVERS,
                HybridAquaticBiomeTags.CAVES),
            3, 2, 3
        )

        addRiverFish(
            HybridAquaticEntityTypes.PUPFISH.get(),
            listOf(BiomeTags.IS_BADLANDS),
            1, 0, 1)

        addRiverFish(
            HybridAquaticEntityTypes.TIGER_BARB.get(),
            listOf(
                HybridAquaticBiomeTags.JUNGLE,
                HybridAquaticBiomeTags.SWAMP,
                HybridAquaticBiomeTags.MANGROVES,
                HybridAquaticBiomeTags.MARSHES,
                HybridAquaticBiomeTags.TROPICAL_RIVERS),
            3, 2, 3
        )

        addRiverFish(
            HybridAquaticEntityTypes.GOURAMI.get(),
            listOf(
                HybridAquaticBiomeTags.JUNGLE,
                HybridAquaticBiomeTags.MANGROVES,
                HybridAquaticBiomeTags.MARSHES,
                HybridAquaticBiomeTags.TROPICAL_RIVERS),
            1, 1, 1
        )

        addRiverFish(
            HybridAquaticEntityTypes.PLECO.get(),
            listOf(
                HybridAquaticBiomeTags.SWAMP,
                HybridAquaticBiomeTags.MANGROVES,
                HybridAquaticBiomeTags.JUNGLE,
                HybridAquaticBiomeTags.MARSHES),
            1, 1, 2
        )

        addRiverFish(
            HybridAquaticEntityTypes.DISCUS.get(),
            listOf(
                HybridAquaticBiomeTags.JUNGLE,
                HybridAquaticBiomeTags.MANGROVES,
                HybridAquaticBiomeTags.TROPICAL_RIVERS),
            1, 1, 1
        )

        addRiverFish(
            HybridAquaticEntityTypes.CORYDORA.get(),
            listOf(
                HybridAquaticBiomeTags.JUNGLE,
                HybridAquaticBiomeTags.TROPICAL_RIVERS),
            2, 1, 3
        )

        addRiverFish(
            HybridAquaticEntityTypes.PIRANHA.get(),
            listOf(
                HybridAquaticBiomeTags.JUNGLE,
                HybridAquaticBiomeTags.TROPICAL_RIVERS),
            2, 4, 8
        )

        addRiverFish(
            HybridAquaticEntityTypes.OSCAR.get(),
            listOf(
                HybridAquaticBiomeTags.JUNGLE,
                HybridAquaticBiomeTags.MANGROVES,
                HybridAquaticBiomeTags.TROPICAL_RIVERS),
            1, 1, 1
        )

        addRiverFish(
            HybridAquaticEntityTypes.DANIO.get(),
            listOf(
                HybridAquaticBiomeTags.JUNGLE,
                HybridAquaticBiomeTags.MANGROVES,
                HybridAquaticBiomeTags.MARSHES,
                HybridAquaticBiomeTags.TROPICAL_RIVERS),
            3, 2, 3
        )

        addRiverFish(
            HybridAquaticEntityTypes.BETTA.get(),
            listOf(
                HybridAquaticBiomeTags.SWAMP,
                HybridAquaticBiomeTags.MANGROVES,
                HybridAquaticBiomeTags.MARSHES),
            1, 1, 1
        )

        addRiverFish(
            HybridAquaticEntityTypes.GOLDFISH.get(),
            listOf(
                HybridAquaticBiomeTags.CHERRY),
            3, 1, 2)

        addRiverFish(
            HybridAquaticEntityTypes.CARP.get(),
            listOf(
                HybridAquaticBiomeTags.RIVERS),
            3, 0, 2
        )

        addRiverFish(
            HybridAquaticEntityTypes.SUNFISH.get(),
            listOf(
                HybridAquaticBiomeTags.RIVERS),
            3, 1, 2
        )

        addRiverFish(
            HybridAquaticEntityTypes.TROUT.get(),
            listOf(
                HybridAquaticBiomeTags.COLD_RIVERS,
                HybridAquaticBiomeTags.SEASONAL_RIVERS,
                HybridAquaticBiomeTags.PLACER_RIVERS),
            2, 0, 1
        )

        addRiverFish(
            HybridAquaticEntityTypes.SHINER.get(),
            listOf(
                HybridAquaticBiomeTags.COLD_RIVERS,
                HybridAquaticBiomeTags.SEASONAL_RIVERS,
                HybridAquaticBiomeTags.PLACER_RIVERS),
            3, 1, 2
        )

        addFish(
            HybridAquaticEntityTypes.MANTA_RAY.get(),
            listOf(
                HybridAquaticBiomeTags.DEEP_TEMPERATE_OCEANS,
                HybridAquaticBiomeTags.DEEP_LUKEWARM_OCEANS),
            1, 1, 2
        )

        addFish(
            HybridAquaticEntityTypes.FLASHLIGHT_FISH.get(),
            listOf(
                HybridAquaticBiomeTags.LUKEWARM_OCEANS,
                HybridAquaticBiomeTags.WARM_OCEAN,
                HybridAquaticBiomeTags.DEEP_REEF,
                HybridAquaticBiomeTags.CORAL_REEF),
            5, 4, 12
        )

        addFish(
            HybridAquaticEntityTypes.SQUIRRELFISH.get(),
            listOf(
                HybridAquaticBiomeTags.LUKEWARM_OCEANS,
                HybridAquaticBiomeTags.WARM_OCEAN,
                HybridAquaticBiomeTags.DEEP_REEF,
                HybridAquaticBiomeTags.RED_MEADOW,
                HybridAquaticBiomeTags.CORAL_REEF),
            5, 4, 12
        )

        addFish(
            HybridAquaticEntityTypes.FLYING_FISH.get(),
            listOf(
                HybridAquaticBiomeTags.WARM_OCEAN,
                HybridAquaticBiomeTags.LUKEWARM_OCEANS,),
            5, 4, 12
        )

        addFish(
            HybridAquaticEntityTypes.DAMSELFISH.get(),
            listOf(
                HybridAquaticBiomeTags.WARM_OCEAN,
                HybridAquaticBiomeTags.CORAL_REEF,
                HybridAquaticBiomeTags.LUKEWARM_OCEANS),
            5, 4, 12
        )

        addFish(
            HybridAquaticEntityTypes.MACKEREL.get(),
            listOf(
                HybridAquaticBiomeTags.COLD_OCEANS,
                HybridAquaticBiomeTags.TEMPERATE_OCEANS),
            5, 4, 12
        )

        addFish(
            HybridAquaticEntityTypes.HERRING.get(),
            listOf(
                HybridAquaticBiomeTags.SHALLOW_COLD_OCEANS,
                HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS),
            5, 4, 12
        )

        addFish(HybridAquaticEntityTypes.RATFISH.get(),
            listOf(
                HybridAquaticBiomeTags.TEMPERATE_TRENCH,
                HybridAquaticBiomeTags.DEEP_REEF,
                HybridAquaticBiomeTags.LUKEWARM_TRENCH,
                HybridAquaticBiomeTags.COLD_TRENCH,
                HybridAquaticBiomeTags.ARCTIC_TRENCH),
            5, 1, 3
        )

        addFish(
            HybridAquaticEntityTypes.SNAILFISH.get(),
            listOf(
                HybridAquaticBiomeTags.TEMPERATE_TRENCH,
                HybridAquaticBiomeTags.LUKEWARM_TRENCH,
                HybridAquaticBiomeTags.COLD_TRENCH,
                HybridAquaticBiomeTags.ARCTIC_TRENCH),
            5, 1, 3
        )

        addFish(
            HybridAquaticEntityTypes.NEEDLEFISH.get(),
            listOf(
                HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS,
                HybridAquaticBiomeTags.LUKEWARM_OCEANS,
                HybridAquaticBiomeTags.SEAGRASS_BED),
            4, 1, 4
        )

        addFish(
            HybridAquaticEntityTypes.MAHI.get(),
            listOf(
                HybridAquaticBiomeTags.DEEP_LUKEWARM_OCEANS),
            3, 1, 4
        )

        addFish(
            HybridAquaticEntityTypes.TUNA.get(),
            listOf(
                HybridAquaticBiomeTags.DEEP_TEMPERATE_OCEANS,
                HybridAquaticBiomeTags.DEEP_LUKEWARM_OCEANS),
            3, 1, 4
        )

        addFish(
            HybridAquaticEntityTypes.ROCKFISH.get(),
            listOf(
                HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS,
                HybridAquaticBiomeTags.DEEP_REEF,
                HybridAquaticBiomeTags.LUKEWARM_OCEANS),
            3, 1, 4
        )

        addFish(
            HybridAquaticEntityTypes.SEA_BASS.get(),
            listOf(
                HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS,
                HybridAquaticBiomeTags.DEEP_REEF,
                HybridAquaticBiomeTags.LUKEWARM_OCEANS),
            3, 1, 3
        )

        addFish(
            HybridAquaticEntityTypes.BARRACUDA.get(),
            listOf(
                HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS,
                HybridAquaticBiomeTags.WARM_OCEAN,
                HybridAquaticBiomeTags.LUKEWARM_OCEANS),
            3, 1, 1
        )

        addFish(
            HybridAquaticEntityTypes.OPAH.get(),
            listOf(
                HybridAquaticBiomeTags.DEEP_TEMPERATE_OCEANS,
                HybridAquaticBiomeTags.DEEP_LUKEWARM_OCEANS),
            2, 1, 2
        )

        addFish(
            HybridAquaticEntityTypes.WRASSE.get(),
            listOf(
                HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS),
            3, 1, 2
        )

        addFish(
            HybridAquaticEntityTypes.SEAHORSE.get(),
            listOf(
                HybridAquaticBiomeTags.CORAL_REEF,
                HybridAquaticBiomeTags.SEAGRASS_BED),
            1, 1, 2
        )

        addFish(
            HybridAquaticEntityTypes.SEADRAGON.get(),
            listOf(
                HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS,
                HybridAquaticBiomeTags.DEEP_LUKEWARM_OCEANS,
                HybridAquaticBiomeTags.SEAGRASS_BED),
            1, 1, 2
        )

        addFish(
            HybridAquaticEntityTypes.LIONFISH.get(),
            listOf(
                HybridAquaticBiomeTags.RED_MEADOW,
                HybridAquaticBiomeTags.CORAL_REEF),
            3, 1, 2
        )

        addFish(
            HybridAquaticEntityTypes.STONEFISH.get(),
            listOf(
                HybridAquaticBiomeTags.CORAL_REEF,
                HybridAquaticBiomeTags.SEAGRASS_BED),
            3, 1, 2
        )

        addFish(
            HybridAquaticEntityTypes.STINGRAY.get(),
            listOf(
                HybridAquaticBiomeTags.SHALLOW_LUKEWARM_OCEANS,
                HybridAquaticBiomeTags.WARM_OCEAN,
                HybridAquaticBiomeTags.CORAL_REEF,
                HybridAquaticBiomeTags.RED_MEADOW,
                HybridAquaticBiomeTags.SEAGRASS_BED),
            2, 1, 2
        )

        addFish(
            HybridAquaticEntityTypes.BLOWFISH.get(),
            listOf(
                HybridAquaticBiomeTags.WARM_OCEAN,
                HybridAquaticBiomeTags.CORAL_REEF,
                HybridAquaticBiomeTags.SEAGRASS_BED),
            3, 1, 2
        )

        addFish(
            HybridAquaticEntityTypes.SURGEONFISH.get(),
            listOf(
                HybridAquaticBiomeTags.SEAGRASS_BED,
                HybridAquaticBiomeTags.CORAL_REEF),
            4, 1, 4
        )

        addFish(
            HybridAquaticEntityTypes.CLOWNFISH.get(),
            listOf(
                HybridAquaticBiomeTags.CORAL_REEF),
            3, 1, 2
        )

        addFish(
            HybridAquaticEntityTypes.BOXFISH.get(),
            listOf(
                HybridAquaticBiomeTags.WARM_OCEAN,
                HybridAquaticBiomeTags.CORAL_REEF),
            2, 1, 1
        )

        addFish(
            HybridAquaticEntityTypes.TRIGGERFISH.get(),
            listOf(
                HybridAquaticBiomeTags.WARM_OCEAN,
                HybridAquaticBiomeTags.CORAL_REEF,
                HybridAquaticBiomeTags.SEAGRASS_BED),
            3, 1, 1
        )

        addFish(
            HybridAquaticEntityTypes.PARROTFISH.get(),
            listOf(
                HybridAquaticBiomeTags.WARM_OCEAN,
                HybridAquaticBiomeTags.CORAL_REEF),
            4, 1, 2
        )

        addFish(
            HybridAquaticEntityTypes.MORAY_EEL.get(),
            listOf(
                HybridAquaticBiomeTags.CORAL_REEF,
                HybridAquaticBiomeTags.SEAGRASS_BED),
            3, 1, 2
        )

        addFish(
            HybridAquaticEntityTypes.OCEAN_SUNFISH.get(),
            listOf(
                HybridAquaticBiomeTags.DEEP_TEMPERATE_OCEANS,
                HybridAquaticBiomeTags.DEEP_LUKEWARM_OCEANS),
            1, 1, 2
        )

        addFish(
            HybridAquaticEntityTypes.OARFISH.get(),
            listOf(
                HybridAquaticBiomeTags.DEEP_LUKEWARM_OCEANS,
                HybridAquaticBiomeTags.LUKEWARM_TRENCH,
                HybridAquaticBiomeTags.TEMPERATE_TRENCH,
                HybridAquaticBiomeTags.DEEP_TEMPERATE_OCEANS),
            1, 1, 2
        )

        addFish(
            HybridAquaticEntityTypes.ANGLERFISH.get(),
            listOf(
                HybridAquaticBiomeTags.TEMPERATE_TRENCH,
                HybridAquaticBiomeTags.LUKEWARM_TRENCH,
                HybridAquaticBiomeTags.COLD_TRENCH,
                HybridAquaticBiomeTags.ARCTIC_TRENCH),
            3, 1, 2
        )

        addFish(
            HybridAquaticEntityTypes.FANGTOOTH.get(),
            listOf(
                HybridAquaticBiomeTags.TEMPERATE_TRENCH,
                HybridAquaticBiomeTags.LUKEWARM_TRENCH,
                HybridAquaticBiomeTags.COLD_TRENCH,
                HybridAquaticBiomeTags.ARCTIC_TRENCH),
            3, 1, 2
        )

        addFish(
            HybridAquaticEntityTypes.VIPERFISH.get(),
            listOf(
                HybridAquaticBiomeTags.TEMPERATE_TRENCH,
                HybridAquaticBiomeTags.LUKEWARM_TRENCH,
                HybridAquaticBiomeTags.COLD_TRENCH,
                HybridAquaticBiomeTags.ARCTIC_TRENCH),
            3, 1, 2
        )

        addFish(
            HybridAquaticEntityTypes.HATCHETFISH.get(),
            listOf(
                HybridAquaticBiomeTags.TEMPERATE_TRENCH,
                HybridAquaticBiomeTags.LUKEWARM_TRENCH,
                HybridAquaticBiomeTags.COLD_TRENCH,
                HybridAquaticBiomeTags.ARCTIC_TRENCH),
            3, 1, 2
        )

        addFish(
            HybridAquaticEntityTypes.JOHN_DORY.get(),
            listOf(
                HybridAquaticBiomeTags.DEEP_REEF,
                HybridAquaticBiomeTags.DEEP_WARM_OCEAN,
                HybridAquaticBiomeTags.LUKEWARM_TRENCH,
                HybridAquaticBiomeTags.DEEP_LUKEWARM_OCEANS,
                HybridAquaticBiomeTags.TEMPERATE_TRENCH,
                HybridAquaticBiomeTags.DEEP_TEMPERATE_OCEANS),
            4, 1, 2
        )

        addFish(
            HybridAquaticEntityTypes.BARRELEYE.get(),
            listOf(
                HybridAquaticBiomeTags.LUKEWARM_TRENCH,
                HybridAquaticBiomeTags.TEMPERATE_TRENCH),
            1, 1, 2
        )

        addFish(
            HybridAquaticEntityTypes.DRAGONFISH.get(),
            listOf(
                HybridAquaticBiomeTags.LUKEWARM_TRENCH,
                HybridAquaticBiomeTags.TEMPERATE_TRENCH),
            2, 1, 2
        )

        addFish(
            HybridAquaticEntityTypes.SEA_ANGEL.get(),
            listOf(
                HybridAquaticBiomeTags.ARCTIC_OCEANS,
                HybridAquaticBiomeTags.COLD_TRENCH,
                HybridAquaticBiomeTags.TEMPERATE_TRENCH),
            1, 1, 2
        )

        addFish(
            HybridAquaticEntityTypes.COELACANTH.get(),
            listOf(
                HybridAquaticBiomeTags.DEEP_REEF,
                HybridAquaticBiomeTags.LUKEWARM_TRENCH,
                HybridAquaticBiomeTags.TEMPERATE_TRENCH),
            1, 1, 1
        )
        //#endregion

        //#region Cephalopods
        addCephalopod(
            HybridAquaticEntityTypes.ARROW_SQUID.get(),
            listOf(
                HybridAquaticBiomeTags.TEMPERATE_TRENCH,
                HybridAquaticBiomeTags.TEMPERATE_OCEANS),
            10, 1, 2
        )

        addCephalopod(
            HybridAquaticEntityTypes.FIREFLY_SQUID.get(),
            listOf(
                HybridAquaticBiomeTags.LUKEWARM_OCEANS,
                HybridAquaticBiomeTags.WARM_OCEAN,
                HybridAquaticBiomeTags.CORAL_REEF),
            10, 1, 2
        )

        addCephalopod(
            HybridAquaticEntityTypes.CUTTLEFISH.get(),
            listOf(
                HybridAquaticBiomeTags.WARM_OCEAN,
                HybridAquaticBiomeTags.CORAL_REEF,
                HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS),
            10, 1, 1
        )

        addCephalopod(
            HybridAquaticEntityTypes.OCTOPUS.get(),
            listOf(
                HybridAquaticBiomeTags.CORAL_REEF,
                HybridAquaticBiomeTags.RED_MEADOW,
                HybridAquaticBiomeTags.SHALLOW_LUKEWARM_OCEANS,
                HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS
            ),
            10, 1, 1
        )
        //#endregion

        //#region Jellyfish
        addJelly(
            HybridAquaticEntityTypes.BARREL_JELLYFISH.get(),
            listOf(
                HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS,
                HybridAquaticBiomeTags.WARM_OCEAN,
                HybridAquaticBiomeTags.RED_MEADOW,
                HybridAquaticBiomeTags.SEAGRASS_BED,
                HybridAquaticBiomeTags.LUKEWARM_OCEANS),
            3, 1, 2
        )

        addJelly(
            HybridAquaticEntityTypes.MOON_JELLYFISH.get(),
            listOf(
                HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS,
                HybridAquaticBiomeTags.SHALLOW_LUKEWARM_OCEANS,
                HybridAquaticBiomeTags.WARM_OCEAN,
                HybridAquaticBiomeTags.CORAL_REEF,
                HybridAquaticBiomeTags.RED_MEADOW,
                HybridAquaticBiomeTags.SEAGRASS_BED,
                HybridAquaticBiomeTags.SANDY_BEACHES
            ),
            5, 2, 5
        )

        addJelly(
            HybridAquaticEntityTypes.CEPHEIDAE_JELLYFISH.get(),
            listOf(
                HybridAquaticBiomeTags.LUKEWARM_OCEANS,
                HybridAquaticBiomeTags.WARM_OCEAN,
                HybridAquaticBiomeTags.SEAGRASS_BED,
                HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS),
            3, 1, 2
        )

        addJelly(
            HybridAquaticEntityTypes.BLUE_JELLYFISH.get(),
            listOf(
                HybridAquaticBiomeTags.COLD_OCEANS,
                HybridAquaticBiomeTags.TEMPERATE_OCEANS),
            3, 1, 2
        )

        addJelly(
            HybridAquaticEntityTypes.SEA_NETTLE.get(),
            listOf(
                HybridAquaticBiomeTags.LUKEWARM_OCEANS,
                HybridAquaticBiomeTags.WARM_OCEAN,
                HybridAquaticBiomeTags.TEMPERATE_OCEANS),
            3, 1, 4
        )

        addJelly(
            HybridAquaticEntityTypes.NOMURA_JELLYFISH.get(),
            listOf(
                HybridAquaticBiomeTags.ARCTIC_OCEANS,
                HybridAquaticBiomeTags.COLD_OCEANS),
            1, 1, 1
        )

        addJelly(
            HybridAquaticEntityTypes.LIONS_MANE_JELLYFISH.get(),
            listOf(
                HybridAquaticBiomeTags.ARCTIC_OCEANS,
                HybridAquaticBiomeTags.COLD_OCEANS),
            1, 1, 1
        )

        addJelly(
            HybridAquaticEntityTypes.BOX_JELLYFISH.get(),
            listOf(
                HybridAquaticBiomeTags.MANGROVES,
                HybridAquaticBiomeTags.CORAL_REEF,
                HybridAquaticBiomeTags.WARM_OCEAN,
                HybridAquaticBiomeTags.LUKEWARM_OCEANS,
                HybridAquaticBiomeTags.SANDY_BEACHES
            ),
            1, 1, 1
        )

        addJelly(
            HybridAquaticEntityTypes.CROWN_JELLYFISH.get(),
            listOf(
                HybridAquaticBiomeTags.TEMPERATE_TRENCH,
                HybridAquaticBiomeTags.LUKEWARM_TRENCH,
                HybridAquaticBiomeTags.COLD_TRENCH,
                HybridAquaticBiomeTags.ARCTIC_TRENCH),
            1, 1, 1
        )

        addJelly(
            HybridAquaticEntityTypes.BIG_RED_JELLYFISH.get(),
            listOf(
                HybridAquaticBiomeTags.TEMPERATE_TRENCH,
                HybridAquaticBiomeTags.LUKEWARM_TRENCH,
                HybridAquaticBiomeTags.COLD_TRENCH,
                HybridAquaticBiomeTags.ARCTIC_TRENCH),
            1, 1, 1
        )

        addJelly(
            HybridAquaticEntityTypes.COSMIC_JELLYFISH.get(),
            listOf(
                HybridAquaticBiomeTags.DEEP_REEF,
                HybridAquaticBiomeTags.TEMPERATE_TRENCH,
                HybridAquaticBiomeTags.LUKEWARM_TRENCH,
                HybridAquaticBiomeTags.COLD_TRENCH,
                HybridAquaticBiomeTags.ARCTIC_TRENCH),
            1, 1, 1
        )

        addJelly(
            HybridAquaticEntityTypes.FIREWORK_JELLYFISH.get(),
            listOf(
                HybridAquaticBiomeTags.DEEP_REEF,
                HybridAquaticBiomeTags.TEMPERATE_TRENCH,
                HybridAquaticBiomeTags.LUKEWARM_TRENCH,
                HybridAquaticBiomeTags.COLD_TRENCH,
                HybridAquaticBiomeTags.ARCTIC_TRENCH),
            1, 1, 1
        )

        addJelly(
            HybridAquaticEntityTypes.MAUVE_STINGER.get(),
            listOf(
                HybridAquaticBiomeTags.DEEP_REEF,
                HybridAquaticBiomeTags.TEMPERATE_TRENCH,
                HybridAquaticBiomeTags.LUKEWARM_TRENCH,
                HybridAquaticBiomeTags.COLD_TRENCH,
                HybridAquaticBiomeTags.ARCTIC_TRENCH),
            2, 1, 3
        )
        //#endregion

        //#region Sharks
        addShark(
            HybridAquaticEntityTypes.GREAT_WHITE_SHARK.get(),
            listOf(
                HybridAquaticBiomeTags.DEEP_TEMPERATE_OCEANS,
                HybridAquaticBiomeTags.DEEP_LUKEWARM_OCEANS),
            1, 1, 1
        )

        addShark(
            HybridAquaticEntityTypes.SAND_TIGER_SHARK.get(),
            listOf(
                HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS,
                HybridAquaticBiomeTags.WARM_OCEAN,
                HybridAquaticBiomeTags.SEAGRASS_BED,
                HybridAquaticBiomeTags.SHALLOW_LUKEWARM_OCEANS),
            2, 1, 1
        )

        addShark(
            HybridAquaticEntityTypes.HAMMERHEAD_SHARK.get(),
            listOf(
                HybridAquaticBiomeTags.TEMPERATE_OCEANS,
                HybridAquaticBiomeTags.WARM_OCEAN,
                HybridAquaticBiomeTags.SEAGRASS_BED,
                HybridAquaticBiomeTags.SHALLOW_LUKEWARM_OCEANS),
            3, 1, 2
        )

        addShark(
            HybridAquaticEntityTypes.HOUND_SHARK.get(),
            listOf(
                HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS,
                HybridAquaticBiomeTags.SHALLOW_LUKEWARM_OCEANS),
            3, 1, 2
        )

        addShark(
            HybridAquaticEntityTypes.THRESHER_SHARK.get(),
            listOf(
                HybridAquaticBiomeTags.DEEP_TEMPERATE_OCEANS,
                HybridAquaticBiomeTags.DEEP_LUKEWARM_OCEANS),
            2, 1, 1
        )

        addShark(
            HybridAquaticEntityTypes.BULL_SHARK.get(),
            listOf(
                HybridAquaticBiomeTags.DEEP_LUKEWARM_OCEANS),
            2, 1, 2
        )

        addShark(
            HybridAquaticEntityTypes.WHALE_SHARK.get(),
            listOf(
                HybridAquaticBiomeTags.DEEP_LUKEWARM_OCEANS),
            1, 1, 1
        )

        addShark(
            HybridAquaticEntityTypes.BASKING_SHARK.get(),
            listOf(
                HybridAquaticBiomeTags.DEEP_TEMPERATE_OCEANS,
                HybridAquaticBiomeTags.COLD_OCEANS,
                HybridAquaticBiomeTags.ARCTIC_OCEANS
            ),
            1, 1, 1
        )

        addShark(
            HybridAquaticEntityTypes.FRILLED_SHARK.get(),
            listOf(
                HybridAquaticBiomeTags.DEEP_REEF,
                HybridAquaticBiomeTags.LUKEWARM_TRENCH),
            1, 1, 1
        )

        addShark(
            HybridAquaticEntityTypes.SIXGILL_SHARK.get(),
            listOf(
                HybridAquaticBiomeTags.TEMPERATE_TRENCH,
                HybridAquaticBiomeTags.LUKEWARM_TRENCH),
            1, 1, 1
        )

        addShark(
            HybridAquaticEntityTypes.SLEEPER_SHARK.get(),
            listOf(
                HybridAquaticBiomeTags.ARCTIC_TRENCH,
                HybridAquaticBiomeTags.COLD_TRENCH),
            1, 1, 1
        )

        addShark(
            HybridAquaticEntityTypes.LANTERN_SHARK.get(),
            listOf(
                HybridAquaticBiomeTags.DEEP_REEF,
                HybridAquaticBiomeTags.WARM_TRENCH,
                HybridAquaticBiomeTags.DEEP_TEMPERATE_OCEANS,
                HybridAquaticBiomeTags.TEMPERATE_TRENCH,
                HybridAquaticBiomeTags.DEEP_LUKEWARM_OCEANS,
                HybridAquaticBiomeTags.LUKEWARM_TRENCH),
            2, 1, 2
        )
        //#endregion

        //#region Mammal
        addMammal(
            HybridAquaticEntityTypes.OTTER.get(),
            listOf(
                HybridAquaticBiomeTags.RIVERS,
                HybridAquaticBiomeTags.SANDY_BEACHES,
                HybridAquaticBiomeTags.ROCKY_BEACHES
            ),
            1, 1, 2
        )

        addSirenian(
            HybridAquaticEntityTypes.DUGONG.get(),
            listOf(
                HybridAquaticBiomeTags.SEAGRASS_BED
            ),
            1, 1, 2
        )

        addSirenian(
            HybridAquaticEntityTypes.MANATEE.get(),
            listOf(
                HybridAquaticBiomeTags.SEAGRASS_BED,
                HybridAquaticBiomeTags.MANGROVES,
                HybridAquaticBiomeTags.TROPICAL_RIVERS
            ),
            1, 1, 2
        )

        addDolphin(
            HybridAquaticEntityTypes.ORCA.get(),
            listOf(
                HybridAquaticBiomeTags.ARCTIC_OCEANS,
                HybridAquaticBiomeTags.COLD_OCEANS,
                HybridAquaticBiomeTags.TEMPERATE_OCEANS,
            ),
            1, 1, 3
        )
        //#endregion

        //#region Crustaceans
        addCrustacean(
            HybridAquaticEntityTypes.DUNGENESS_CRAB.get(),
            listOf(
                HybridAquaticBiomeTags.SANDY_BEACHES),
            3, 1, 2
        )

        addCrustacean(
            HybridAquaticEntityTypes.FIDDLER_CRAB.get(),
            listOf(
                HybridAquaticBiomeTags.SWAMP,
                HybridAquaticBiomeTags.MANGROVES,
                HybridAquaticBiomeTags.MARSHES),
            3, 1, 2
        )

        addCrustacean(
            HybridAquaticEntityTypes.GHOST_CRAB.get(),
            listOf(
                HybridAquaticBiomeTags.SANDY_BEACHES),
            3, 1, 2
        )

        addCrustacean(
            HybridAquaticEntityTypes.HORSESHOE_CRAB.get(),
            listOf(
                HybridAquaticBiomeTags.WARM_OCEAN,
                HybridAquaticBiomeTags.RED_MEADOW,
                HybridAquaticBiomeTags.SEAGRASS_BED,
                HybridAquaticBiomeTags.SHALLOW_LUKEWARM_OCEANS,
                HybridAquaticBiomeTags.SANDY_BEACHES),
            3, 1, 2
        )

        addCrustacean(
            HybridAquaticEntityTypes.LIGHTFOOT_CRAB.get(),
            listOf(
                HybridAquaticBiomeTags.ROCKY_BEACHES),
            3, 1, 2
        )

        addCrustacean(
            HybridAquaticEntityTypes.FLOWER_CRAB.get(),
            listOf(
                HybridAquaticBiomeTags.WARM_OCEAN,
                HybridAquaticBiomeTags.RED_MEADOW,
                HybridAquaticBiomeTags.SEAGRASS_BED,
                HybridAquaticBiomeTags.SHALLOW_LUKEWARM_OCEANS,
                HybridAquaticBiomeTags.MANGROVES,
                HybridAquaticBiomeTags.MARSHES),
            3, 1, 2
        )

        addCrustacean(
            HybridAquaticEntityTypes.VAMPIRE_CRAB.get(),
            listOf(
                HybridAquaticBiomeTags.JUNGLE,
                HybridAquaticBiomeTags.TROPICAL_RIVERS),
            1, 1, 2
        )

        addCrustacean(
            HybridAquaticEntityTypes.SHRIMP.get(),
            listOf(
                HybridAquaticBiomeTags.TROPICAL_RIVERS,
                HybridAquaticBiomeTags.DEEP_REEF,
                HybridAquaticBiomeTags.WARM_OCEAN,
                HybridAquaticBiomeTags.RED_MEADOW,
                HybridAquaticBiomeTags.SEAGRASS_BED,
                HybridAquaticBiomeTags.CORAL_REEF),
            3, 2, 3
        )

        addCrustacean(
            HybridAquaticEntityTypes.LOBSTER.get(),
            listOf(
                HybridAquaticBiomeTags.DEEP_REEF,
                HybridAquaticBiomeTags.WARM_OCEAN,
                HybridAquaticBiomeTags.CORAL_REEF,
                HybridAquaticBiomeTags.RED_MEADOW,
                HybridAquaticBiomeTags.SEAGRASS_BED,
                HybridAquaticBiomeTags.SHALLOW_LUKEWARM_OCEANS),
            2, 1, 2
        )
        addCrustacean(
            HybridAquaticEntityTypes.DECORATOR_CRAB.get(),
            listOf(
                HybridAquaticBiomeTags.CORAL_REEF,
                HybridAquaticBiomeTags.RED_MEADOW),
            2, 1, 2
        )

        addCrustacean(
            HybridAquaticEntityTypes.CRAYFISH.get(),
            listOf(
                HybridAquaticBiomeTags.TROPICAL_RIVERS,
                HybridAquaticBiomeTags.PLACER_RIVERS,
                HybridAquaticBiomeTags.SEASONAL_RIVERS,
                HybridAquaticBiomeTags.COLD_RIVERS,
                HybridAquaticBiomeTags.RIVERS),
            2, 1, 2
        )

        addCrustacean(
            HybridAquaticEntityTypes.COCONUT_CRAB.get(),
            listOf(
                HybridAquaticBiomeTags.SANDY_BEACHES),
            2, 1, 2
        )

        addCrustacean(
            HybridAquaticEntityTypes.HERMIT_CRAB.get(),
            listOf(
                HybridAquaticBiomeTags.SANDY_BEACHES,
                HybridAquaticBiomeTags.RED_MEADOW,
                HybridAquaticBiomeTags.SEAGRASS_BED,
                HybridAquaticBiomeTags.SHALLOW_LUKEWARM_OCEANS),
            1, 1, 2
        )

        addCrustacean(
            HybridAquaticEntityTypes.YETI_CRAB.get(),
            listOf(
                HybridAquaticBiomeTags.HAS_THERMAL_VENTS,
                HybridAquaticBiomeTags.ARCTIC_TRENCH,
                HybridAquaticBiomeTags.COLD_TRENCH),
            2, 1, 2
        )

        addCrustacean(
            HybridAquaticEntityTypes.GIANT_ISOPOD.get(),
            listOf(
                HybridAquaticBiomeTags.DEEP_REEF,
                HybridAquaticBiomeTags.WARM_TRENCH,
                HybridAquaticBiomeTags.TEMPERATE_OCEANS,
                HybridAquaticBiomeTags.TEMPERATE_TRENCH,
                HybridAquaticBiomeTags.COLD_TRENCH,
                HybridAquaticBiomeTags.COLD_OCEANS),
            1, 1, 2
        )

        addCrustacean(
            HybridAquaticEntityTypes.SPIDER_CRAB.get(),
            listOf(
                HybridAquaticBiomeTags.DEEP_REEF,
                HybridAquaticBiomeTags.WARM_TRENCH,
                HybridAquaticBiomeTags.TEMPERATE_OCEANS,
                HybridAquaticBiomeTags.TEMPERATE_TRENCH,
                HybridAquaticBiomeTags.COLD_TRENCH,
                HybridAquaticBiomeTags.COLD_OCEANS),
            2, 1, 2
        )
        //#endregion

        //#region Critters
        addCritter(
            HybridAquaticEntityTypes.SEA_SLUG.get(),
            listOf(
                HybridAquaticBiomeTags.SHALLOW_COLD_OCEANS,
                HybridAquaticBiomeTags.SHALLOW_TEMPERATE_OCEANS,
                HybridAquaticBiomeTags.LUKEWARM_OCEANS,
                HybridAquaticBiomeTags.WARM_OCEAN,
                HybridAquaticBiomeTags.SEAGRASS_BED,
                HybridAquaticBiomeTags.RED_MEADOW,
                HybridAquaticBiomeTags.CORAL_REEF),
            1, 0, 2
        )

        addCritter(
            HybridAquaticEntityTypes.SCALYFOOT_SNAIL.get(),
            listOf(
                HybridAquaticBiomeTags.HAS_THERMAL_VENTS),
            1, 0, 2
        )

        addCritter(
            HybridAquaticEntityTypes.STARFISH.get(),
            listOf(
                BiomeTags.IS_OCEAN,
                BiomeTags.IS_DEEP_OCEAN,
                HybridAquaticBiomeTags.TEMPERATE_TRENCH,
                HybridAquaticBiomeTags.LUKEWARM_TRENCH,
                HybridAquaticBiomeTags.COLD_TRENCH,
                HybridAquaticBiomeTags.ARCTIC_TRENCH,
                HybridAquaticBiomeTags.SANDY_BEACHES),
            2, 0, 2
        )

        addCritter(
            HybridAquaticEntityTypes.SEA_CUCUMBER.get(),
            listOf(
                BiomeTags.IS_OCEAN,
                BiomeTags.IS_DEEP_OCEAN,
                HybridAquaticBiomeTags.RED_MEADOW,
                HybridAquaticBiomeTags.TEMPERATE_TRENCH,
                HybridAquaticBiomeTags.LUKEWARM_TRENCH,
                HybridAquaticBiomeTags.COLD_TRENCH,
                HybridAquaticBiomeTags.ARCTIC_TRENCH),
            3, 1, 2
        )

        addCritter(
            HybridAquaticEntityTypes.SEA_URCHIN.get(),
            listOf(
                BiomeTags.IS_OCEAN,
                BiomeTags.IS_DEEP_OCEAN,
                HybridAquaticBiomeTags.RED_MEADOW,
                HybridAquaticBiomeTags.TEMPERATE_TRENCH,
                HybridAquaticBiomeTags.LUKEWARM_TRENCH,
                HybridAquaticBiomeTags.COLD_TRENCH,
                HybridAquaticBiomeTags.ARCTIC_TRENCH),
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