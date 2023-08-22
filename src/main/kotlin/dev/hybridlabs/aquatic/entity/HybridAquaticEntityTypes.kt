package dev.hybridlabs.aquatic.entity

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.critter.*
import dev.hybridlabs.aquatic.entity.fish.*
import dev.hybridlabs.aquatic.entity.shark.*
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.*
import net.minecraft.entity.EntityType.EntityFactory
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object HybridAquaticEntityTypes {
    //region Fish
    val CLOWNFISH = registerFish(
        "clownfish",
        ::ClownfishEntity,
        EntityDimensions.fixed(0.75f, 0.5f),
        ClownfishEntity.createClownfishAttributes().build()
    )

    val ANGLERFISH = registerFish(
        "anglerfish",
        ::AnglerfishEntity,
        EntityDimensions.fixed(0.75f, 0.75f),
        AnglerfishEntity.createMobAttributes().build()
    )

    val BARRELEYE = registerFish(
        "barreleye",
        ::BarreleyeEntity,
        EntityDimensions.fixed(0.8f, 0.8f),
        BarreleyeEntity.createMobAttributes().build()
    )

    val YELLOWFIN_TUNA = registerFish(
        "yellowfin_tuna",
        ::YellowfinTunaEntity,
        EntityDimensions.fixed(1.25f, 0.8f),
        YellowfinTunaEntity.createMobAttributes().build()
    )

    val CUTTLEFISH = registerFish(
        "cuttlefish",
        ::CuttlefishEntity,
        EntityDimensions.fixed(1.0f, 0.6f),
        CuttlefishEntity.createMobAttributes().build()
    )

    val FLASHLIGHT_FISH = registerFish(
        "flashlight_fish",
        ::FlashlightFishEntity,
        EntityDimensions.fixed(0.75f, 0.75f),
        FlashlightFishEntity.createMobAttributes().build()
    )

    val LIONFISH = registerFish(
        "lionfish",
        ::LionfishEntity,
        EntityDimensions.fixed(0.8f, 0.6f),
        LionfishEntity.createMobAttributes().build()
    )

    val OARFISH = registerFish(
        "oarfish",
        ::OarfishEntity,
        EntityDimensions.fixed(2.0f, 1.0f),
        OarfishEntity.createMobAttributes().build()
    )

    val OPAH = registerFish(
        "opah",
        ::OpahEntity,
        EntityDimensions.fixed(0.8f, 1.0f),
        OpahEntity.createMobAttributes().build()
    )

    val PIRANHA = registerFish(
        "piranha",
        ::PiranhaEntity,
        EntityDimensions.fixed(0.75f, 0.75f),
        PiranhaEntity.createMobAttributes().build()
    )

    val SEA_ANGEL = registerFish(
        "sea_angel",
        ::SeaAngelEntity,
        EntityDimensions.fixed(0.6f, 0.6f),
        SeaAngelEntity.createMobAttributes().build()
    )

    val SUNFISH = registerFish(
        "sunfish",
        ::SunfishEntity,
        EntityDimensions.fixed(1.25f, 2f),
        SunfishEntity.createMobAttributes().build()
    )

    val VAMPIRE_SQUID = registerFish(
        "vampire_squid",
        ::VampireSquidEntity,
        EntityDimensions.fixed(0.8f, 0.6f),
        VampireSquidEntity.createMobAttributes().build()
    )

    val MAHIMAHI = registerFish(
        "mahimahi",
        ::MahiMahiEntity,
        EntityDimensions.fixed(1.25f, 0.8f),
        MahiMahiEntity.createMobAttributes().build()
    )

    val MORAY_EEL = registerFish(
        "moray_eel",
        ::MorayEelEntity,
        EntityDimensions.fixed(0.8f, 0.5f),
        MorayEelEntity.createMobAttributes().build()
    )

    val ROCKFISH = registerFish(
        "rockfish",
        ::RockfishEntity,
        EntityDimensions.fixed(0.8f, 0.6f),
        RockfishEntity.createMobAttributes().build()
    )

    val TIGER_BARB = registerFish(
        "tiger_barb",
        ::TigerBarbEntity,
        EntityDimensions.fixed(0.75f, 0.6f),
        TigerBarbEntity.createMobAttributes().build()
    )

    val NEEDLEFISH = registerFish(
        "needlefish",
        ::NeedlefishEntity,
        EntityDimensions.fixed(0.75f, 0.5f),
        NeedlefishEntity.createMobAttributes().build()
    )

    val RATFISH = registerFish(
        "ratfish",
        ::RatfishEntity,
        EntityDimensions.fixed(0.75f, 0.8f),
        RatfishEntity.createMobAttributes().build()
    )

    val NAUTILUS = registerFish(
        "nautilus",
        ::NautilusEntity,
        EntityDimensions.fixed(0.6f, 0.6f),
        NautilusEntity.createMobAttributes().build()
    )

    val TRIGGERFISH = registerFish(
        "triggerfish",
        ::TriggerfishEntity,
        EntityDimensions.fixed(0.75f, 0.75f),
        TriggerfishEntity.createMobAttributes().build()
    )

    val OSCAR = registerFish(
        "oscar",
        ::OscarEntity,
        EntityDimensions.fixed(0.75f, 0.6f),
        OscarEntity.createMobAttributes().build()
    )

    val UNICORN_FISH = registerFish(
        "unicorn_fish",
        ::UnicornFishEntity,
        EntityDimensions.fixed(0.6f, 0.6f),
        UnicornFishEntity.createMobAttributes().build()
    )

    val ZEBRA_DANIO = registerFish(
        "zebra_danio",
        ::ZebraDanioEntity,
        EntityDimensions.fixed(0.6f, 0.6f),
        ZebraDanioEntity.createMobAttributes().build()
    )

    val TOADFISH = registerFish(
        "toadfish",
        ::ToadfishEntity,
        EntityDimensions.fixed(0.6f, 0.6f),
        ToadfishEntity.createMobAttributes().build()
    )

    val TETRA = registerFish(
        "tetra",
        ::TetraEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        TetraEntity.createMobAttributes().build()
    )

    val STONEFISH = registerFish(
        "stonefish",
        ::StonefishEntity,
        EntityDimensions.fixed(0.6f, 0.6f),
        StonefishEntity.createMobAttributes().build()
    )

    val BETTA = registerFish(
        "betta",
        ::BettaEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        BettaEntity.createMobAttributes().build()
    )

    val SEAHORSE = registerFish(
        "seahorse",
        ::SeahorseEntity,
        EntityDimensions.fixed(0.5f, 0.7f),
        SeahorseEntity.createMobAttributes().build()
    )

    val MOON_JELLY = registerFish(
        "moon_jelly",
        ::MoonJellyEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        MoonJellyEntity.createMobAttributes().build()
    )

    val GOURAMI = registerFish(
        "gourami",
        ::GouramiEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        GouramiEntity.createMobAttributes().build()
    )

    val COWFISH = registerFish(
        "cowfish",
        ::CowfishEntity,
        EntityDimensions.fixed(0.5f, 0.5f),
        CowfishEntity.createMobAttributes().build()
    )

    val GLOWING_SUCKER_OCTOPUS = registerFish(
        "glowing_sucker_octopus",
        ::GlowingSuckerOctopusEntity,
        EntityDimensions.fixed(1.0f, 0.8f),
        GlowingSuckerOctopusEntity.createMobAttributes().build()
    )

    val DISCUS = registerFish(
        "discus",
        ::DiscusEntity,
        EntityDimensions.fixed(0.6f, 0.6f),
        DiscusEntity.createMobAttributes().build()
    )

    val FIREFLY_SQUID = registerFish(
        "firefly_squid",
        ::FireflySquidEntity,
        EntityDimensions.fixed(1.0f, 0.6f),
        FireflySquidEntity.createMobAttributes().build()
    )

    val DRAGONFISH = registerFish(
        "dragonfish",
        ::DragonfishEntity,
        EntityDimensions.fixed(1.0f, 0.5f),
        DragonfishEntity.createMobAttributes().build()
    )


    val BLUE_SPOTTED_STINGRAY = registerFish(
        "blue_spotted_stingray",
        ::BlueSpottedStingrayEntity,
        EntityDimensions.fixed(1.0f, 0.4f),
        BlueSpottedStingrayEntity.createMobAttributes().build()
    )

    val BLUE_TANG = registerFish(
        "blue_tang",
        ::BlueTangEntity,
        EntityDimensions.fixed(0.75f, 0.5f),
        BlueTangEntity.createMobAttributes().build()
    )

    //endregion

    //region critters
    val CRAB = registerCritter(
        "crab",
        ::CrabEntity,
        EntityDimensions.fixed(0.5f, 0.75f),
        CrabEntity.createMobAttributes().build()
    )
    val FIDDLER_CRAB = registerCritter(
        "fiddler_crab",
        ::FiddlerCrabEntity,
        EntityDimensions.fixed(0.5f, 0.75f),
        FiddlerCrabEntity.createMobAttributes().build()
    )
    val HERMIT_CRAB = registerCritter(
        "hermit_crab",
        ::HermitCrabEntity,
        EntityDimensions.fixed(0.5f, 0.75f),
        HermitCrabEntity.createMobAttributes().build()
    )
    val STARFISH = registerCritter(
        "starfish",
        ::StarfishEntity,
        EntityDimensions.fixed(0.5f, 0.75f),
        StarfishEntity.createMobAttributes().build()
    )
    val SEA_CUCUMBER = registerCritter(
        "sea_cucumber",
        ::SeaCucumberEntity,
        EntityDimensions.fixed(0.5f, 0.75f),
        StarfishEntity.createMobAttributes().build()
    )
    val NUDIBRANCH = registerCritter(
        "nudibranch",
        ::NudibranchEntity,
        EntityDimensions.fixed(0.5f, 0.75f),
        StarfishEntity.createMobAttributes().build()
    )

    val SEA_URCHIN = registerCritter(
        "sea_urchin",
        ::SeaUrchinEntity,
        EntityDimensions.fixed(0.5f, 0.75f),
        StarfishEntity.createMobAttributes().build()
    )
    //endregion

    //region sharks
    val BULL_SHARK = registerShark(
        "bull_shark",
        ::BullSharkEntity,
        EntityDimensions.fixed(2.0f, 1.0f),
        BullSharkEntity.createMobAttributes().build()
    )

    val BASKING_SHARK = registerShark(
        "basking_shark",
        ::BaskingSharkEntity,
        EntityDimensions.fixed(2.5f, 1.0f),
        BaskingSharkEntity.createMobAttributes().build()
    )

    val THRESHER_SHARK = registerShark(
        "thresher_shark",
        ::ThresherSharkEntity,
        EntityDimensions.fixed(2.0f, 0.5f),
        ThresherSharkEntity.createMobAttributes().build()
    )

    val FRILLED_SHARK = registerShark(
        "frilled_shark",
        ::FrilledSharkEntity,
        EntityDimensions.fixed(1.5f, 1.0f),
        FrilledSharkEntity.createMobAttributes().build()
    )

    val GREAT_WHITE_SHARK = registerShark(
        "great_white_shark",
        ::GreatWhiteSharkEntity,
        EntityDimensions.fixed(2.0f, 1.5f),
        GreatWhiteSharkEntity.createMobAttributes().build()
    )

    val TIGER_SHARK = registerShark(
        "tiger_shark",
        ::TigerSharkEntity,
        EntityDimensions.fixed(2.0f, 1.5f),
        TigerSharkEntity.createMobAttributes().build()
    )

    val HAMMERHEAD_SHARK = registerShark(
        "hammerhead_shark",
        ::HammerheadSharkEntity,
        EntityDimensions.fixed(2.0f, 1.0f),
        HammerheadSharkEntity.createMobAttributes().build()
    )

    val WHALE_SHARK = registerShark(
        "whale_shark",
        ::WhaleSharkEntity,
        EntityDimensions.fixed(2.5f, 1.0f),
        WhaleSharkEntity.createMobAttributes().build()
    )
    //endregion sharks

    private fun <T : Entity> register(id: String, entity: EntityType<T>): EntityType<T> {
        return Registry.register(Registries.ENTITY_TYPE, Identifier(HybridAquatic.MOD_ID, id), entity)
    }

    private inline fun <reified T : HybridAquaticSharkEntity> registerShark(
        id: String,
        entityFactory: EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: DefaultAttributeContainer
    ): EntityType<T> {
        return registerLiving(id, FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, entityFactory).dimensions(dimensions).build(), attributeContainer)
    }

    private inline fun <reified T : HybridAquaticCritterEntity> registerCritter(
        id: String,
        entityFactory: EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: DefaultAttributeContainer
    ): EntityType<T> {
        return registerLiving(id, FabricEntityTypeBuilder.create(SpawnGroup.WATER_AMBIENT, entityFactory).dimensions(dimensions).build(), attributeContainer)
    }

    private inline fun <reified T : HybridAquaticFishEntity> registerFish(
            id: String,
            entityFactory: EntityFactory<T>,
            dimensions: EntityDimensions,
            attributeContainer: DefaultAttributeContainer
    ): EntityType<T> {
        return registerLiving(id, FabricEntityTypeBuilder.create(SpawnGroup.WATER_AMBIENT, entityFactory).dimensions(dimensions).build(), attributeContainer)
    }

    private inline fun <reified T : LivingEntity> registerLiving(
        id: String,
        entity: EntityType<T>,
        attributes: DefaultAttributeContainer
    ): EntityType<T> {
        val type = Registry.register(Registries.ENTITY_TYPE, Identifier(HybridAquatic.MOD_ID, id), entity)
        FabricDefaultAttributeRegistry.register(type, attributes)
        return type
    }
}
