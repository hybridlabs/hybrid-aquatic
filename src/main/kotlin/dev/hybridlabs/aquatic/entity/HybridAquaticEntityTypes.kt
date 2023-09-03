package dev.hybridlabs.aquatic.entity

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.critter.CrabEntity
import dev.hybridlabs.aquatic.entity.critter.FiddlerCrabEntity
import dev.hybridlabs.aquatic.entity.critter.GiantClamEntity
import dev.hybridlabs.aquatic.entity.critter.HermitCrabEntity
import dev.hybridlabs.aquatic.entity.critter.NudibranchEntity
import dev.hybridlabs.aquatic.entity.critter.SeaCucumberEntity
import dev.hybridlabs.aquatic.entity.critter.SeaUrchinEntity
import dev.hybridlabs.aquatic.entity.critter.StarfishEntity
import dev.hybridlabs.aquatic.entity.fish.AnglerfishEntity
import dev.hybridlabs.aquatic.entity.fish.BarreleyeEntity
import dev.hybridlabs.aquatic.entity.fish.BettaEntity
import dev.hybridlabs.aquatic.entity.fish.BlueSpottedStingrayEntity
import dev.hybridlabs.aquatic.entity.fish.BlueTangEntity
import dev.hybridlabs.aquatic.entity.fish.ClownfishEntity
import dev.hybridlabs.aquatic.entity.fish.CowfishEntity
import dev.hybridlabs.aquatic.entity.fish.CuttlefishEntity
import dev.hybridlabs.aquatic.entity.fish.DiscusEntity
import dev.hybridlabs.aquatic.entity.fish.DragonfishEntity
import dev.hybridlabs.aquatic.entity.fish.FireflySquidEntity
import dev.hybridlabs.aquatic.entity.fish.FlashlightFishEntity
import dev.hybridlabs.aquatic.entity.fish.GlowingSuckerOctopusEntity
import dev.hybridlabs.aquatic.entity.fish.GouramiEntity
import dev.hybridlabs.aquatic.entity.fish.LionfishEntity
import dev.hybridlabs.aquatic.entity.fish.MahiMahiEntity
import dev.hybridlabs.aquatic.entity.fish.MorayEelEntity
import dev.hybridlabs.aquatic.entity.fish.NautilusEntity
import dev.hybridlabs.aquatic.entity.fish.NeedlefishEntity
import dev.hybridlabs.aquatic.entity.fish.OarfishEntity
import dev.hybridlabs.aquatic.entity.fish.OpahEntity
import dev.hybridlabs.aquatic.entity.fish.OscarEntity
import dev.hybridlabs.aquatic.entity.fish.PiranhaEntity
import dev.hybridlabs.aquatic.entity.fish.RatfishEntity
import dev.hybridlabs.aquatic.entity.fish.RockfishEntity
import dev.hybridlabs.aquatic.entity.fish.SeaAngelEntity
import dev.hybridlabs.aquatic.entity.fish.SeahorseEntity
import dev.hybridlabs.aquatic.entity.fish.StonefishEntity
import dev.hybridlabs.aquatic.entity.fish.SunfishEntity
import dev.hybridlabs.aquatic.entity.fish.TetraEntity
import dev.hybridlabs.aquatic.entity.fish.TigerBarbEntity
import dev.hybridlabs.aquatic.entity.fish.ToadfishEntity
import dev.hybridlabs.aquatic.entity.fish.TriggerfishEntity
import dev.hybridlabs.aquatic.entity.fish.UnicornFishEntity
import dev.hybridlabs.aquatic.entity.fish.VampireSquidEntity
import dev.hybridlabs.aquatic.entity.fish.YellowfinTunaEntity
import dev.hybridlabs.aquatic.entity.fish.ZebraDanioEntity
import dev.hybridlabs.aquatic.entity.jellyfish.AtollaJellyfishEntity
import dev.hybridlabs.aquatic.entity.jellyfish.BarrelJellyfishEntity
import dev.hybridlabs.aquatic.entity.jellyfish.BlueJellyfishEntity
import dev.hybridlabs.aquatic.entity.jellyfish.CauliflowerJellyfishEntity
import dev.hybridlabs.aquatic.entity.jellyfish.CompassJellyfishEntity
import dev.hybridlabs.aquatic.entity.jellyfish.FriedEggJellyfishEntity
import dev.hybridlabs.aquatic.entity.jellyfish.LionsManeJellyfishEntity
import dev.hybridlabs.aquatic.entity.jellyfish.MauveStingerEntity
import dev.hybridlabs.aquatic.entity.jellyfish.MoonJellyfishEntity
import dev.hybridlabs.aquatic.entity.jellyfish.NomuraJellyfishEntity
import dev.hybridlabs.aquatic.entity.jellyfish.SeaNettleEntity
import dev.hybridlabs.aquatic.entity.shark.BaskingSharkEntity
import dev.hybridlabs.aquatic.entity.shark.BullSharkEntity
import dev.hybridlabs.aquatic.entity.shark.FrilledSharkEntity
import dev.hybridlabs.aquatic.entity.shark.GreatWhiteSharkEntity
import dev.hybridlabs.aquatic.entity.shark.HammerheadSharkEntity
import dev.hybridlabs.aquatic.entity.shark.ThresherSharkEntity
import dev.hybridlabs.aquatic.entity.shark.TigerSharkEntity
import dev.hybridlabs.aquatic.entity.shark.WhaleSharkEntity
import dev.hybridlabs.aquatic.utils.HybridAquaticSpawnGroup
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityType
import net.minecraft.entity.EntityType.EntityFactory
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.SpawnGroup
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object HybridAquaticEntityTypes {
    val CLOWNFISH = registerFish(
        "clownfish",
        ::ClownfishEntity,
        EntityDimensions.fixed(0.75f, 0.5f),
        ClownfishEntity.createClownfishAttributes()
    )

    val ANGLERFISH = registerFishUnderground(
        "anglerfish",
        ::AnglerfishEntity,
        EntityDimensions.fixed(0.75f, 0.75f),
        AnglerfishEntity.createMobAttributes()
    )

    val BARRELEYE = registerFishUnderground(
        "barreleye",
        ::BarreleyeEntity,
        EntityDimensions.fixed(0.8f, 0.8f),
        BarreleyeEntity.createMobAttributes()
    )

    val YELLOWFIN_TUNA = registerFish(
        "yellowfin_tuna",
        ::YellowfinTunaEntity,
        EntityDimensions.fixed(1.25f, 0.8f),
        YellowfinTunaEntity.createMobAttributes()
    )

    val CUTTLEFISH = registerFish(
        "cuttlefish",
        ::CuttlefishEntity,
        EntityDimensions.fixed(1.0f, 0.6f),
        CuttlefishEntity.createMobAttributes()
    )

    val FLASHLIGHT_FISH = registerFish(
        "flashlight_fish",
        ::FlashlightFishEntity,
        EntityDimensions.fixed(0.75f, 0.75f),
        FlashlightFishEntity.createMobAttributes()
    )

    val LIONFISH = registerFish(
        "lionfish",
        ::LionfishEntity,
        EntityDimensions.fixed(0.8f, 0.6f),
        LionfishEntity.createMobAttributes()
    )

    val OARFISH = registerFish(
        "oarfish",
        ::OarfishEntity,
        EntityDimensions.fixed(2.0f, 1.0f),
        OarfishEntity.createMobAttributes()
    )

    val OPAH = registerFish(
        "opah",
        ::OpahEntity,
        EntityDimensions.fixed(0.8f, 1.0f),
        OpahEntity.createMobAttributes()
    )

    val PIRANHA = registerFish(
        "piranha",
        ::PiranhaEntity,
        EntityDimensions.fixed(0.75f, 0.75f),
        PiranhaEntity.createMobAttributes()
    )

    val SEA_ANGEL = registerFishUnderground(
        "sea_angel",
        ::SeaAngelEntity,
        EntityDimensions.fixed(0.6f, 0.6f),
        SeaAngelEntity.createMobAttributes()
    )

    val SUNFISH = registerFish(
        "sunfish",
        ::SunfishEntity,
        EntityDimensions.fixed(1.25f, 2f),
        SunfishEntity.createMobAttributes()
    )

    val VAMPIRE_SQUID = registerFishUnderground(
        "vampire_squid",
        ::VampireSquidEntity,
        EntityDimensions.fixed(0.8f, 0.6f),
        VampireSquidEntity.createMobAttributes()
    )

    val MAHIMAHI = registerFish(
        "mahimahi",
        ::MahiMahiEntity,
        EntityDimensions.fixed(1.25f, 0.8f),
        MahiMahiEntity.createMobAttributes()
    )

    val MORAY_EEL = registerFish(
        "moray_eel",
        ::MorayEelEntity,
        EntityDimensions.fixed(0.8f, 0.5f),
        MorayEelEntity.createMobAttributes()
    )

    val ROCKFISH = registerFish(
        "rockfish",
        ::RockfishEntity,
        EntityDimensions.fixed(0.8f, 0.6f),
        RockfishEntity.createMobAttributes()
    )

    val TIGER_BARB = registerFish(
        "tiger_barb",
        ::TigerBarbEntity,
        EntityDimensions.fixed(0.75f, 0.6f),
        TigerBarbEntity.createMobAttributes()
    )

    val NEEDLEFISH = registerFish(
        "needlefish",
        ::NeedlefishEntity,
        EntityDimensions.fixed(0.75f, 0.5f),
        NeedlefishEntity.createMobAttributes()
    )

    val RATFISH = registerFish(
        "ratfish",
        ::RatfishEntity,
        EntityDimensions.fixed(0.75f, 0.8f),
        RatfishEntity.createMobAttributes()
    )

    val NAUTILUS = registerFish(
        "nautilus",
        ::NautilusEntity,
        EntityDimensions.fixed(0.6f, 0.6f),
        NautilusEntity.createMobAttributes()
    )

    val TRIGGERFISH = registerFish(
        "triggerfish",
        ::TriggerfishEntity,
        EntityDimensions.fixed(0.75f, 0.75f),
        TriggerfishEntity.createMobAttributes()
    )

    val OSCAR = registerFish(
        "oscar",
        ::OscarEntity,
        EntityDimensions.fixed(0.75f, 0.6f),
        OscarEntity.createMobAttributes()
    )

    val UNICORN_FISH = registerFish(
        "unicorn_fish",
        ::UnicornFishEntity,
        EntityDimensions.fixed(0.6f, 0.6f),
        UnicornFishEntity.createMobAttributes()
    )

    val ZEBRA_DANIO = registerFish(
        "zebra_danio",
        ::ZebraDanioEntity,
        EntityDimensions.fixed(0.6f, 0.6f),
        ZebraDanioEntity.createMobAttributes()
    )

    val TOADFISH = registerFish(
        "toadfish",
        ::ToadfishEntity,
        EntityDimensions.fixed(0.6f, 0.6f),
        ToadfishEntity.createMobAttributes()
    )

    val TETRA = registerFish(
        "tetra",
        ::TetraEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        TetraEntity.createMobAttributes()
    )

    val STONEFISH = registerFish(
        "stonefish",
        ::StonefishEntity,
        EntityDimensions.fixed(0.6f, 0.6f),
        StonefishEntity.createMobAttributes()
    )

    val BETTA = registerFish(
        "betta",
        ::BettaEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        BettaEntity.createMobAttributes()
    )

    val SEAHORSE = registerFish(
        "seahorse",
        ::SeahorseEntity,
        EntityDimensions.fixed(0.5f, 0.7f),
        SeahorseEntity.createMobAttributes()
    )

    val GOURAMI = registerFish(
        "gourami",
        ::GouramiEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        GouramiEntity.createMobAttributes()
    )

    val COWFISH = registerFish(
        "cowfish",
        ::CowfishEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        CowfishEntity.createMobAttributes()
    )

    val GLOWING_SUCKER_OCTOPUS = registerFish(
        "glowing_sucker_octopus",
        ::GlowingSuckerOctopusEntity,
        EntityDimensions.fixed(1.0f, 0.8f),
        GlowingSuckerOctopusEntity.createMobAttributes()
    )

    val DISCUS = registerFish(
        "discus",
        ::DiscusEntity,
        EntityDimensions.fixed(0.6f, 0.6f),
        DiscusEntity.createMobAttributes()
    )

    val FIREFLY_SQUID = registerFish(
        "firefly_squid",
        ::FireflySquidEntity,
        EntityDimensions.fixed(1.0f, 0.6f),
        FireflySquidEntity.createMobAttributes()
    )

    val DRAGONFISH = registerFishUnderground(
        "dragonfish",
        ::DragonfishEntity,
        EntityDimensions.fixed(1.0f, 0.5f),
        DragonfishEntity.createMobAttributes()
    )

    val BLUE_SPOTTED_STINGRAY = registerFish(
        "blue_spotted_stingray",
        ::BlueSpottedStingrayEntity,
        EntityDimensions.fixed(1.0f, 0.4f),
        BlueSpottedStingrayEntity.createMobAttributes()
    )

    val BLUE_TANG = registerFish(
        "blue_tang",
        ::BlueTangEntity,
        EntityDimensions.fixed(0.75f, 0.5f),
        BlueTangEntity.createMobAttributes()
    )

    val CRAB = registerCritter(
        "crab",
        ::CrabEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        CrabEntity.createMobAttributes()
    )
    val FIDDLER_CRAB = registerCritter(
        "fiddler_crab",
        ::FiddlerCrabEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        FiddlerCrabEntity.createMobAttributes()
    )
    val HERMIT_CRAB = registerCritter(
        "hermit_crab",
        ::HermitCrabEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        HermitCrabEntity.createMobAttributes()
    )
    val STARFISH = registerCritter(
        "starfish",
        ::StarfishEntity,
        EntityDimensions.fixed(0.5f, 0.25f),
        StarfishEntity.createMobAttributes()
    )
    val SEA_CUCUMBER = registerCritter(
        "sea_cucumber",
        ::SeaCucumberEntity,
        EntityDimensions.fixed(0.5f, 0.3f),
        SeaCucumberEntity.createMobAttributes()
    )
    val NUDIBRANCH = registerCritter(
        "nudibranch",
        ::NudibranchEntity,
        EntityDimensions.fixed(0.5f, 0.3f),
        NudibranchEntity.createMobAttributes()
    )

    val SEA_URCHIN = registerCritter(
        "sea_urchin",
        ::SeaUrchinEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        SeaUrchinEntity.createMobAttributes()
    )

    val GIANT_CLAM = registerCritter(
        "giant_clam",
        ::GiantClamEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        GiantClamEntity.createMobAttributes()
    )

    val SEA_NETTLE = registerJelly(
        "sea_nettle",
        ::SeaNettleEntity,
        EntityDimensions.fixed(0.75f, 2.75f),
        SeaNettleEntity.createMobAttributes()
    )

    val MOON_JELLYFISH = registerJelly(
        "moon_jellyfish",
        ::MoonJellyfishEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        MoonJellyfishEntity.createMobAttributes()
    )

    val FRIED_EGG_JELLY = registerJelly(
        "fried_egg_jelly",
        ::FriedEggJellyfishEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        FriedEggJellyfishEntity.createMobAttributes()
    )

    val CAULIFLOWER_JELLY = registerJelly(
        "cauliflower_jelly",
        ::CauliflowerJellyfishEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        CauliflowerJellyfishEntity.createMobAttributes()
    )

    val NOMURA_JELLY = registerJelly(
        "nomura_jelly",
        ::NomuraJellyfishEntity,
        EntityDimensions.fixed(2.0f, 2.5f),
        NomuraJellyfishEntity.createMobAttributes()
    )

    val BARREL_JELLY = registerJelly(
        "barrel_jelly",
        ::BarrelJellyfishEntity,
        EntityDimensions.fixed(1.0f, 1.5f),
        BarrelJellyfishEntity.createMobAttributes()
    )

    val COMPASS_JELLY = registerJelly(
        "compass_jelly",
        ::CompassJellyfishEntity,
        EntityDimensions.fixed(1.0f, 1.5f),
        CompassJellyfishEntity.createMobAttributes()
    )

    val BLUE_JELLY = registerJelly(
        "blue_jelly",
        ::BlueJellyfishEntity,
        EntityDimensions.fixed(1.0f, 1.5f),
        BlueJellyfishEntity.createMobAttributes()
    )

    val MAUVE_STINGER = registerJelly(
        "mauve_stinger",
        ::MauveStingerEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        MauveStingerEntity.createMobAttributes()
    )

    val LIONS_MANE_JELLYFISH = registerJelly(
        "lions_mane",
        ::LionsManeJellyfishEntity,
        EntityDimensions.fixed(2.5f, 3.0f),
        LionsManeJellyfishEntity.createMobAttributes()
    )

    val ATOLLA_JELLYFISH = registerJelly(
        "atolla_jelly",
        ::AtollaJellyfishEntity,
        EntityDimensions.fixed(2.5f, 3.0f),
        AtollaJellyfishEntity.createMobAttributes()
    )

    val BULL_SHARK = registerShark(
        "bull_shark",
        ::BullSharkEntity,
        EntityDimensions.fixed(2.0f, 1.0f),
        BullSharkEntity.createMobAttributes()
    )

    val BASKING_SHARK = registerShark(
        "basking_shark",
        ::BaskingSharkEntity,
        EntityDimensions.fixed(2.5f, 1.0f),
        BaskingSharkEntity.createMobAttributes()
    )

    val THRESHER_SHARK = registerShark(
        "thresher_shark",
        ::ThresherSharkEntity,
        EntityDimensions.fixed(2.0f, 0.5f),
        ThresherSharkEntity.createMobAttributes()
    )

    val FRILLED_SHARK = registerFishUnderground(
        "frilled_shark",
        ::FrilledSharkEntity,
        EntityDimensions.fixed(1.5f, 1.0f),
        FrilledSharkEntity.createMobAttributes()
    )

    val GREAT_WHITE_SHARK = registerShark(
        "great_white_shark",
        ::GreatWhiteSharkEntity,
        EntityDimensions.fixed(2.0f, 1.5f),
        GreatWhiteSharkEntity.createMobAttributes()
    )

    val TIGER_SHARK = registerShark(
        "tiger_shark",
        ::TigerSharkEntity,
        EntityDimensions.fixed(2.0f, 1.5f),
        TigerSharkEntity.createMobAttributes()
    )

    val HAMMERHEAD_SHARK = registerShark(
        "hammerhead_shark",
        ::HammerheadSharkEntity,
        EntityDimensions.fixed(2.0f, 1.0f),
        HammerheadSharkEntity.createMobAttributes()
    )

    val WHALE_SHARK = registerShark(
        "whale_shark",
        ::WhaleSharkEntity,
        EntityDimensions.fixed(2.5f, 1.0f),
        WhaleSharkEntity.createMobAttributes()
    )

    private fun <T : LivingEntity> registerShark(
        id: String,
        entityFactory: EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: DefaultAttributeContainer.Builder
    ): EntityType<T> {
        return registerCustomSpawnGroup(id, entityFactory, dimensions, attributeContainer, HybridAquaticSpawnGroup.SHARK)
    }

    private fun <T : LivingEntity> registerCritter(
        id: String,
        entityFactory: EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: DefaultAttributeContainer.Builder
    ): EntityType<T> {
        return registerCustomSpawnGroup(id, entityFactory, dimensions, attributeContainer, HybridAquaticSpawnGroup.CRITTER)
    }

    private fun <T : LivingEntity> registerFish(
        id: String,
        entityFactory: EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: DefaultAttributeContainer.Builder
    ): EntityType<T> {
        return registerCustomSpawnGroup(id, entityFactory, dimensions, attributeContainer, HybridAquaticSpawnGroup.FISH)
    }

    private fun <T : LivingEntity> registerFishUnderground(
        id: String,
        entityFactory: EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: DefaultAttributeContainer.Builder
    ): EntityType<T> {
        return registerCustomSpawnGroup(id, entityFactory, dimensions, attributeContainer, HybridAquaticSpawnGroup.FISH_UNDERGROUND)
    }

    private fun <T : LivingEntity> registerJelly(
        id: String,
        entityFactory: EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: DefaultAttributeContainer.Builder
    ): EntityType<T> {
        return registerCustomSpawnGroup(id, entityFactory, dimensions, attributeContainer, HybridAquaticSpawnGroup.JELLY)
    }

    /**
     * Registers a living entity to the entity type registry with a Hybrid Aquatic spawn group.
     */
    private fun <T : LivingEntity> registerCustomSpawnGroup(
        id: String,
        entityFactory: EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: DefaultAttributeContainer.Builder,
        hybridAquaticSpawnGroup: HybridAquaticSpawnGroup
    ): EntityType<T> {
        return registerLiving(id, entityFactory, dimensions, attributeContainer, hybridAquaticSpawnGroup.spawnGroup)
    }

    /**
     * Registers a living entity to the entity type registry.
     */
    private fun <T : LivingEntity> registerLiving(
        id: String,
        entityFactory: EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: DefaultAttributeContainer.Builder,
        spawnGroup: SpawnGroup
    ): EntityType<T> {
        val entityType = FabricEntityTypeBuilder.create(spawnGroup, entityFactory).dimensions(dimensions).build()
        FabricDefaultAttributeRegistry.register(entityType, attributeContainer)
        return register(id, entityType)
    }

    /**
     * Registers an entity type to the entity type registry.
     */
    private fun <T : Entity> register(id: String, entity: EntityType<T>): EntityType<T> {
        return Registry.register(Registries.ENTITY_TYPE, Identifier(HybridAquatic.MOD_ID, id), entity)
    }
}
