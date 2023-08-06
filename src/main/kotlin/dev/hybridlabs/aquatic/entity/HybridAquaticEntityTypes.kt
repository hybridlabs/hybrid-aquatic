package dev.hybridlabs.aquatic.entity

import dev.hybridlabs.aquatic.HybridAquatic
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.SpawnGroup
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object HybridAquaticEntityTypes {
    //region Fish
    val CLOWNFISH = registerLiving(
        "clownfish",
        FabricEntityTypeBuilder.create(
            SpawnGroup.WATER_AMBIENT,
            ::ClownfishEntity
        ).build(),
        ClownfishEntity.createClownfishAttributes().build()
    )

    val ANGLERFISH = registerLiving(
        "anglerfish",
        FabricEntityTypeBuilder.create(
            SpawnGroup.WATER_AMBIENT,
            ::HybridAquaticFishEntity
        )
            .dimensions(EntityDimensions.fixed(1.0f, 1.0f))
            .build(),
        ClownfishEntity.createClownfishAttributes().build()
    )

    val BARRELEYE = registerLiving(
        "barreleye",
        FabricEntityTypeBuilder.create(
            SpawnGroup.WATER_AMBIENT,
            ::HybridAquaticFishEntity
        ).build(),
        ClownfishEntity.createClownfishAttributes().build()
    )
    //endregion

    val BULL_SHARK = registerLiving(
        "bull_shark",
        FabricEntityTypeBuilder.create(
            SpawnGroup.WATER_CREATURE,
            ::BullSharkEntity
        )
            .dimensions(EntityDimensions.fixed(2.0f, 1.0f))
            .build(),
        BullSharkEntity.createMobAttributes().build()
    )

    private fun <T : Entity> register(id: String, entity: EntityType<T>): EntityType<T> {
        return Registry.register(Registries.ENTITY_TYPE, Identifier(HybridAquatic.MOD_ID, id), entity)
    }

    private inline fun <reified T : LivingEntity> registerLiving(
        id: String,
        entity: EntityType<T>,
        attributes: DefaultAttributeContainer
    ): EntityType<T> {
        val type = Registry.register(Registries.ENTITY_TYPE, Identifier(HybridAquatic.MOD_ID, id), entity)
        FabricDefaultAttributeRegistry.register(type, attributes)
        return type;
    }
}
