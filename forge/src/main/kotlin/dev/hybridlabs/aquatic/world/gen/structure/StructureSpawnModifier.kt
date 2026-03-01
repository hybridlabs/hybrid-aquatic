package dev.hybridlabs.aquatic.world.gen.structure

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.Constants
import net.minecraft.core.Holder
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.levelgen.structure.Structure
import net.minecraftforge.common.world.ModifiableStructureInfo
import net.minecraftforge.common.world.StructureModifier
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import thedarkcolour.kotlinforforge.forge.MOD_BUS


class StructureSpawnModifier(val spawnModifier: SpawnModifier) : StructureModifier {

    companion object {
        val SERIALIZER: RegistryObject<Codec<out StructureModifier?>?>? = RegistryObject.create(
            CommonClass.locate("ha_structure_spawns"),
            ForgeRegistries.Keys.STRUCTURE_MODIFIER_SERIALIZERS,
            Constants.MOD_ID
        )

        fun makeCodec(): Codec<StructureSpawnModifier?>? {
            return RecordCodecBuilder.create<StructureSpawnModifier> { instance ->
                instance.group(
                    SpawnModifier.CODEC.fieldOf("modifier").forGetter { modifier -> modifier.spawnModifier }
                ).apply(instance, ::StructureSpawnModifier)
            }
        }

        fun registerHybridAquaticStructureModifiers() {
            val structureModifiers: DeferredRegister<Codec<out StructureModifier?>?> =
                DeferredRegister.create(ForgeRegistries.Keys.STRUCTURE_MODIFIER_SERIALIZERS, Constants.MOD_ID)
            structureModifiers.register(MOD_BUS)
            structureModifiers.register<Codec<out StructureModifier?>?>(
                "ha_structure_spawns",
                StructureSpawnModifier::makeCodec
            )
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
                    val category = MobCategory.byName(categoryName)
                    for (spawn in spawns) {
                        builder.structureSettings.getOrAddSpawnOverrides(category).addSpawn(spawn)
                    }
                }
            }
        }
    }

    override fun codec(): Codec<out StructureModifier?>? {
        return SERIALIZER?.get()
    }
}