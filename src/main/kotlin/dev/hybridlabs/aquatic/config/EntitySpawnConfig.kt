package dev.hybridlabs.aquatic.config

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.tag.TagKey
import net.minecraft.util.registry.Registry
import net.minecraft.world.biome.Biome

data class EntitySpawnConfig(
    val type: EntityType<*>,
    val biomes: TagKey<Biome>,
    val group: SpawnGroup,
    val weight: Int,
    val minGroupSize: Int,
    val maxGroupSize: Int,
) {
    companion object {
        val CODEC: Codec<EntitySpawnConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                Registry.ENTITY_TYPE.codec.fieldOf("type").forGetter(EntitySpawnConfig::type),
                TagKey.codec(Registry.BIOME_KEY).fieldOf("biomes").forGetter(EntitySpawnConfig::biomes),
                SpawnGroup.CODEC.fieldOf("group").forGetter(EntitySpawnConfig::group),
                Codec.INT.fieldOf("weight").forGetter(EntitySpawnConfig::weight),
                Codec.INT.fieldOf("min_group_size").forGetter(EntitySpawnConfig::minGroupSize),
                Codec.INT.fieldOf("max_group_size").forGetter(EntitySpawnConfig::maxGroupSize),
            ).apply(instance, ::EntitySpawnConfig)
        }
    }
}
