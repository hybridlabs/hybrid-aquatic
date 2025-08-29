package dev.hybridlabs.aquatic.config

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.biome.Biome

data class EntitySpawnConfig(
    val type: EntityType<*>,
    val biomes: TagKey<Biome>,
    val group: MobCategory,
    val weight: Int,
    val minGroupSize: Int,
    val maxGroupSize: Int,
) {
    companion object {
        val CODEC: Codec<EntitySpawnConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                BuiltInRegistries.ENTITY_TYPE.byNameCodec().fieldOf("type").forGetter(EntitySpawnConfig::type),
                TagKey.codec(Registries.BIOME).fieldOf("biomes").forGetter(EntitySpawnConfig::biomes),
                MobCategory.CODEC.fieldOf("group").forGetter(EntitySpawnConfig::group),
                Codec.INT.fieldOf("weight").forGetter(EntitySpawnConfig::weight),
                Codec.INT.fieldOf("min_group_size").forGetter(EntitySpawnConfig::minGroupSize),
                Codec.INT.fieldOf("max_group_size").forGetter(EntitySpawnConfig::maxGroupSize),
            ).apply(instance, ::EntitySpawnConfig)
        }
    }
}
