package dev.hybridlabs.aquatic.world.gen.structure

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.Constants
import net.minecraft.core.Holder
import net.minecraft.world.level.levelgen.structure.Structure
import net.minecraftforge.common.world.ModifiableStructureInfo
import net.minecraftforge.common.world.StructureModifier
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject


class StructureSpawnModifier : StructureModifier {
    companion object {
        val SERIALIZER: RegistryObject<Codec<out StructureModifier?>?>? = RegistryObject.create(
            CommonClass.locate("ha_structure_spawns"),
            ForgeRegistries.Keys.STRUCTURE_MODIFIER_SERIALIZERS,
            Constants.MOD_ID
        )

        fun makeCodec(): Codec<out StructureModifier?>? {
            return Codec.unit(::StructureSpawnModifier)
        }
    }


    override fun modify(
        structure: Holder<Structure?>?,
        phase: StructureModifier.Phase?,
        builder: ModifiableStructureInfo.StructureInfo.Builder
    ) {
        if (phase == StructureModifier.Phase.ADD) {
            val key = structure!!.unwrapKey().get()
            val value = structureModifiers[key]
            if (value != null) {
                val modifier = value.get()
                for (spawn in modifier.spawns) {
                    builder.structureSettings.getOrAddSpawnOverrides(modifier.category).addSpawn(spawn)
                }
            }
        }
    }

    override fun codec(): Codec<out StructureModifier?>? {
        return SERIALIZER?.get()
    }

}