package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.loot.HybridAquaticLootTables
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityType
import net.minecraft.entity.MobSpawnType
import net.minecraft.entity.VariantHolder
import net.minecraft.entity.ai.goal.RevengeGoal
import net.minecraft.entity.attribute.AttributeSupplier
import net.minecraft.entity.attribute.Attributes
import net.minecraft.entity.data.SynchedEntityData
import net.minecraft.entity.data.EntityDataAccessor
import net.minecraft.entity.data.EntityDataSerializers
import net.minecraft.nbt.CompoundTag
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.StringRepresentable
import net.minecraft.util.function.ByIdMap
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.ServerLevelAccess
import net.minecraft.world.World
import net.minecraft.world.biome.Biome
import java.util.function.IntFunction
import kotlin.random.Random

@Suppress("DEPRECATION")
class StingrayEntity(entityType: EntityType<out StingrayEntity>, world: Level) :
    HybridAquaticFishEntity(
        entityType, world,
        listOf(HybridAquaticEntityTags.CRUSTACEAN),
        listOf(HybridAquaticEntityTags.SHARK)
    ),
    VariantHolder<StingrayEntity.Companion.Type> {

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, RevengeGoal(this))
    }

    override fun getLootTableId(): ResourceLocation {
        return when (variant) {
            Type.BLUE_SPOTTED -> HybridAquaticLootTables.BLUE_SPOTTED_STINGRAY
            Type.SPOTTED_EAGLE -> HybridAquaticLootTables.SPOTTED_EAGLE_RAY
        }
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?
    ): SpawnGroupData? {
        val biome = world.getBiome(this.blockPos)
        val selectedType = Type.fromBiome(biome, Random.Default)
        this.variant = selectedType
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 5.0)
                .add(Attributes.FOLLOW_RANGE, 8.0)
        }

        val TYPE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(StingrayEntity::class.java, EntityDataSerializers.INTEGER)

        enum class Type(val id: Int, private val key: String) : StringRepresentable {
            SPOTTED_EAGLE(0, "spotted_eagle"),
            BLUE_SPOTTED(1, "blue_spotted");

            override fun asString(): String {
                return this.key
            }

            companion object {
                val CODEC: StringRepresentable.EnumCodec<Type> = StringRepresentable.fromEnum { entries.toTypedArray() }
                private val BY_ID: IntFunction<Type> = ByIdMap.continuous(
                    { obj: Type -> obj.id },
                    entries.toTypedArray(),
                    ByIdMap.OutOfBoundsStrategy.ZERO
                )

                fun byName(name: String?): Type {
                    return CODEC.byName(name, BLUE_SPOTTED) as Type
                }

                fun fromId(id: Int): Type {
                    return BY_ID.apply(id) as Type
                }

                fun fromBiome(biome: RegistryEntry<Biome>, random: RandomSource): Type {
                    return when {
                        biome.`is`(HybridAquaticBiomeTags.REEF) -> {
                            Type.fromId(random.nextInt(0, 3))
                        }

                        else -> {
                            Type.fromId(random.nextInt(0, 3))
                        }
                    }
                }
            }
        }
    }

    override fun initSynchedEntityData() {
        entityData.define(TYPE, 0)
        super.initSynchedEntityData()
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        nbt.putString("Type", this.variant.asString())
        super.addAdditionalSaveData(nbt)
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        this.variant = Type.byName(nbt.getString("Type"))
        super.readAdditionalSaveData(nbt)
    }

    override fun getVariant(): Type {
        return Type.fromId((entityData.get(TYPE) as Int))
    }

    override fun setVariant(type: Type) {
        entityData.set(TYPE, type.id)
    }
}