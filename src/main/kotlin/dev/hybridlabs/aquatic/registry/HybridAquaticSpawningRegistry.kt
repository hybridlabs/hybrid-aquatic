package dev.hybridlabs.aquatic.registry

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.entity.SpawnRestriction
import net.minecraft.registry.tag.TagKey
import net.minecraft.world.Heightmap
import net.minecraft.world.biome.Biome

object HybridAquaticSpawningRegistry {

    fun register() {
        createFishSpawn(HybridAquaticEntityTypes.CLOWNFISH, HybridAquaticBiomeTags.CLOWNFISH_SPAWN_BIOMES, 15, 2, 4)
    }

    private inline fun <reified T : HybridAquaticFishEntity> createFishSpawn(entityType: EntityType<T>, spawnTag: TagKey<Biome>, weight: Int, minGroup: Int, maxGroup: Int, ) {
        BiomeModifications.addSpawn({
            ctx: BiomeSelectionContext -> ctx.hasTag(spawnTag)
        }, SpawnGroup.WATER_AMBIENT, entityType, weight, minGroup, maxGroup)

        SpawnRestriction.register(
            entityType, SpawnRestriction.Location.IN_WATER,
            Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HybridAquaticFishEntity::canSpawnPredicate
        )
    }
}