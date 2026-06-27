package dev.hybridlabs.aquatic.world.gen.structure

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.Holder
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.levelgen.structure.Structure
import net.neoforged.neoforge.common.world.ModifiableStructureInfo
import net.neoforged.neoforge.common.world.StructureModifier


class StructureSpawnModifier(val spawnModifier: SpawnModifier) : StructureModifier {

    companion object {

        fun makeCodec(): MapCodec<out StructureModifier> {
            return RecordCodecBuilder.mapCodec<StructureSpawnModifier> { instance ->
                instance.group(
                    SpawnModifier.CODEC.fieldOf("modifier").forGetter { modifier -> modifier.spawnModifier }
                ).apply(instance, ::StructureSpawnModifier)
            }
        }
    }

    override fun modify(
        structure: Holder<Structure?>,
        phase: StructureModifier.Phase,
        builder: ModifiableStructureInfo.StructureInfo.Builder
    ) {
        if (phase == StructureModifier.Phase.ADD) {
            val key = structure.unwrapKey().get()
            if (spawnModifier.structure == key) {
                for ((categoryName, spawns) in spawnModifier.spawns.entries) {
                    val category = MobCategory.valueOf(categoryName)
                    for (spawn in spawns) {
                        builder.structureSettings.getOrAddSpawnOverrides(category).addSpawn(spawn)
                    }
                }
            }
        }
    }

    override fun codec(): MapCodec<out StructureModifier> {
        return makeCodec()
    }
}