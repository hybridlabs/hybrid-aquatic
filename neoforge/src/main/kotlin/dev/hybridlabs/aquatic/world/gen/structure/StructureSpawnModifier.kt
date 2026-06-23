package dev.hybridlabs.aquatic.world.gen.structure

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import net.minecraft.core.Holder
import net.minecraft.resources.ResourceKey
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.levelgen.structure.Structure
import net.neoforged.neoforge.common.world.ModifiableStructureInfo
import net.neoforged.neoforge.common.world.StructureModifier
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister
import net.neoforged.neoforge.registries.NeoForgeRegistries


class StructureSpawnModifier(val spawnModifier: SpawnModifier) : StructureModifier {

    companion object {
        val structureModifiers: DeferredRegister<MapCodec<out StructureModifier?>?> =
            DeferredRegister.create(NeoForgeRegistries.Keys.STRUCTURE_MODIFIER_SERIALIZERS, Constants.MOD_ID)
        val STRUCTURE_SPAWN_MODIFIER: DeferredHolder<MapCodec<out StructureModifier?>?, MapCodec<StructureSpawnModifier?>?> = structureModifiers.register("ha_structure_spawns",::makeCodec)

        fun makeCodec(): MapCodec<StructureSpawnModifier?>? {
            return RecordCodecBuilder.mapCodec<StructureSpawnModifier> { instance ->
                instance.group(
                    SpawnModifier.CODEC.fieldOf("modifier").forGetter { modifier -> modifier.spawnModifier }
                ).apply(instance, ::StructureSpawnModifier)
            }
        }
    }

    override fun modify(
        structure: Holder<Structure?>?,
        phase: StructureModifier.Phase?,
        builder: ModifiableStructureInfo.StructureInfo.Builder
    ) {
        if (phase == StructureModifier.Phase.ADD) {
            val key = structure!!.unwrapKey().get()
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

    override fun codec(): MapCodec<out StructureModifier?>? {
        return STRUCTURE_SPAWN_MODIFIER.get()
    }
}