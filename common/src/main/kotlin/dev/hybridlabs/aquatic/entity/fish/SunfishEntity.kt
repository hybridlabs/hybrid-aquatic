package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.ai.goal.FishJumpGoal
import dev.hybridlabs.aquatic.entity.ai.goal.StayNearSurfaceGoal
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityType
import net.minecraft.entity.MobSpawnType
import net.minecraft.entity.VariantHolder
import net.minecraft.entity.attribute.AttributeSupplier
import net.minecraft.entity.attribute.Attributes
import net.minecraft.entity.data.SynchedEntityData
import net.minecraft.entity.data.EntityDataAccessor
import net.minecraft.entity.data.EntityDataSerializers
import net.minecraft.nbt.CompoundTag
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.util.StringRepresentable
import net.minecraft.util.function.ByIdMap
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.ServerLevelAccess
import net.minecraft.world.World
import net.minecraft.world.biome.Biome
import java.util.function.IntFunction
import kotlin.random.Random

@Suppress("DEPRECATION")
class SunfishEntity(entityType: EntityType<out SunfishEntity>, world: Level) :
    HybridAquaticFishEntity(
        entityType, world,
        listOf(HybridAquaticEntityTags.JELLYFISH),
        listOf(HybridAquaticEntityTags.SHARK)
    ),
    VariantHolder<SunfishEntity.Companion.Type> {

    override fun getSpawnClusterSize(): Int {
        return 2
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

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(5, FishJumpGoal(this, 10))
        goalSelector.addGoal(1, StayNearSurfaceGoal(this, 1.0, 1, 16))

    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 12.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 16.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
        }

        val TYPE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(SunfishEntity::class.java, EntityDataSerializers.INTEGER)

        enum class Type(val id: Int, private val key: String) : StringRepresentable {
            OCEAN(0, "ocean"),
            GIANT(1, "giant"),
            HOODWINKER(2, "hoodwinker"),
            SHARPTAIL(3, "sharptail");

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
                    return CODEC.byName(name, OCEAN) as Type
                }

                fun fromId(id: Int): Type {
                    return BY_ID.apply(id) as Type
                }

                fun fromBiome(biome: RegistryEntry<Biome>, random: RandomSource): Type {
                    return when {
                        biome.`is`(HybridAquaticBiomeTags.TROPICAL_OCEANS) -> {
                            HOODWINKER
                        }

                        biome.`is`(HybridAquaticBiomeTags.DEEP_TROPICAL_OCEANS) -> {
                            SHARPTAIL
                        }

                        biome.`is`(HybridAquaticBiomeTags.TEMPERATE_OCEANS) -> {
                            OCEAN
                        }

                        biome.`is`(HybridAquaticBiomeTags.DEEP_TEMPERATE_OCEANS) -> {
                            GIANT
                        }

                        else -> {
                            Type.fromId(random.nextInt(0, 4))
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