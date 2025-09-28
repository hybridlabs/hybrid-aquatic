package dev.hybridlabs.aquatic.world.gen.structure

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData
import net.minecraft.world.level.levelgen.structure.Structure
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride.BoundingBoxType

data class SpawnModifier(
    val id: String,
    val structure: ResourceKey<Structure>,
    val boundingBoxType: BoundingBoxType = BoundingBoxType.STRUCTURE,
    val spawns: Map<String, List<SpawnerData>>
) {
    companion object {
        val MAP_CODEC: Codec<Map<String, List<SpawnerData>>> =
            Codec.unboundedMap(Codec.STRING, SpawnerData.CODEC.listOf())
        val CODEC: Codec<SpawnModifier> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.STRING.fieldOf("id").forGetter { modifier -> modifier.id },
                ResourceKey.codec<Structure>(Registries.STRUCTURE).fieldOf("structure")
                    .forGetter { modifier -> modifier.structure },
                BoundingBoxType.CODEC.fieldOf("bounding_box_type").forGetter { modifier -> modifier.boundingBoxType },
                MAP_CODEC.fieldOf("spawns").forGetter { modifier -> modifier.spawns }
            ).apply(instance, ::SpawnModifier)
        }
    }
}