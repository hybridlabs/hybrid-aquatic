package dev.hybridlabs.aquatic.entity

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.fish.ClownfishEntity
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import dev.hybridlabs.aquatic.entity.shark.*
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
    //region Fish
    val CLOWNFISH = registerFish(
        "clownfish",
        ::ClownfishEntity,
        EntityDimensions.fixed(1.0f, 1.0f),
        ClownfishEntity.createClownfishAttributes().build()
    )

    val ANGLERFISH = registerFish(
        "anglerfish",
        ::HybridAquaticFishEntity,
        EntityDimensions.fixed(1.0f, 1.0f),
        HybridAquaticFishEntity.createGenericAttributes().build()
    )

    val BARRELEYE = registerFish(
            "barreleye",
            ::HybridAquaticFishEntity,
            EntityDimensions.fixed(1.0f, 1.0f),
            HybridAquaticFishEntity.createGenericAttributes().build()
    )

    val YELLOWFIN_TUNA = registerFish(
            "yellowfin_tuna",
            ::HybridAquaticFishEntity,
            EntityDimensions.fixed(1.0f, 1.0f),
            HybridAquaticFishEntity.createGenericAttributes().build()
    )

    val CUTTLEFISH = registerFish(
            "cuttlefish",
            ::HybridAquaticFishEntity,
            EntityDimensions.fixed(1.0f, 1.0f),
            HybridAquaticFishEntity.createGenericAttributes().build()
    )

    val VAMPIRE_SQUID = registerFish(
            "vampire_squid",
            ::HybridAquaticFishEntity,
            EntityDimensions.fixed(1.0f, 1.0f),
            HybridAquaticFishEntity.createGenericAttributes().build()
    )

    val FLASHLIGHT_FISH = registerFish(
            "flashlight_fish",
            ::HybridAquaticFishEntity,
            EntityDimensions.fixed(1.0f, 1.0f),
            HybridAquaticFishEntity.createGenericAttributes().build()
    )

    val LIONFISH = registerFish(
            "lionfish",
            ::HybridAquaticFishEntity,
            EntityDimensions.fixed(0.8f, 1.0f),
            HybridAquaticFishEntity.createGenericAttributes().build()
    )

    val OARFISH = registerFish(
            "oarfish",
            ::HybridAquaticFishEntity,
            EntityDimensions.fixed(1.0f, 1.0f),
            HybridAquaticFishEntity.createGenericAttributes().build()
    )

    val OPAH = registerFish(
            "opah",
            ::HybridAquaticFishEntity,
            EntityDimensions.fixed(1.0f, 1.0f),
            HybridAquaticFishEntity.createGenericAttributes().build()
    )

    val PIRANHA = registerFish(
            "piranha",
            ::HybridAquaticFishEntity,
            EntityDimensions.fixed(1.0f, 1.0f),
            HybridAquaticFishEntity.createGenericAttributes().build()
    )

    val SEA_ANGEL = registerFish(
            "sea_angel",
            ::HybridAquaticFishEntity,
            EntityDimensions.fixed(1.0f, 1.0f),
            HybridAquaticFishEntity.createGenericAttributes().build()
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
        EntityDimensions.fixed(5.0f, 2.5f),
        BaskingSharkEntity.createMobAttributes().build()
    )

    val THRESHER_SHARK = registerShark(
        "thresher_shark",
        ::ThresherSharkEntity,
        EntityDimensions.fixed(2f, 0.5f),
        ThresherSharkEntity.createMobAttributes().build()
    )

    val FRILLED_SHARK = registerShark(
        "frilled_shark",
        ::FrilledSharkEntity,
        EntityDimensions.fixed(2f, 1f),
        FrilledSharkEntity.createMobAttributes().build()
    )

    val GREAT_WHITE_SHARK = registerShark(
        "great_white_shark",
        ::GreatWhiteSharkEntity,
        EntityDimensions.fixed(3f, 1.5f),
        GreatWhiteSharkEntity.createMobAttributes().build()
    )

    val TIGER_SHARK = registerShark(
        "tiger_shark",
        ::TigerSharkEntity,
        EntityDimensions.fixed(3f, 1.5f),
        TigerSharkEntity.createMobAttributes().build()
    )

    val HAMMERHEAD_SHARK = registerShark(
        "hammerhead_shark",
        ::HammerheadSharkEntity,
        EntityDimensions.fixed(2f, 1f),
        HammerheadSharkEntity.createMobAttributes().build()
    )

    val WHALE_SHARK = registerShark(
        "whale_shark",
        ::WhaleSharkEntity,
        EntityDimensions.fixed(2f, 1f),
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
