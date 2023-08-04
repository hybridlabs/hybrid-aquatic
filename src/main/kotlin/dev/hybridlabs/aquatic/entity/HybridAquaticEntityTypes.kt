package dev.hybridlabs.aquatic.entity

import dev.hybridlabs.aquatic.HybridAquatic
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.SpawnGroup
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object HybridAquaticEntityTypes {
    val CLOWNFISH = registerLiving(
            "clownfish",
            FabricEntityTypeBuilder.create(
                    SpawnGroup.WATER_AMBIENT,
                    ::ClownfishEntity
            ).build(),
            ClownfishEntity.createClownfishAttributes().build()
    )

    val BULL_SHARK = registerLiving(
            "bull_shark",
            FabricEntityTypeBuilder.create(
                    SpawnGroup.WATER_AMBIENT,
                    ::BullSharkEntity
            ).build(),
            BullSharkEntity.createMobAttributes().build()
    )

    private fun <T : Entity> register(id: String, entity: EntityType<T>): EntityType<T> {
        return Registry.register(Registries.ENTITY_TYPE, Identifier(HybridAquatic.MOD_ID, id), entity)
    }

    private inline fun <reified T : LivingEntity> registerLiving(id: String, entity: EntityType<T>, attributes: DefaultAttributeContainer): EntityType<T> {
        val type = Registry.register(Registries.ENTITY_TYPE, Identifier(HybridAquatic.MOD_ID, id), entity)
        FabricDefaultAttributeRegistry.register(type, attributes)
        return type
    }
}
