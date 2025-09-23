package dev.hybridlabs.aquatic.entity.mammal

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.core.Holder
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerLevel
import net.minecraft.tags.BiomeTags
import net.minecraft.util.ByIdMap
import net.minecraft.util.StringRepresentable
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.biome.Biome
import java.util.function.IntFunction

class OtterEntity(entityType: EntityType<out OtterEntity>, world: Level) :
    HybridAquaticMammalEntity(entityType, world,
        listOf(
            HybridAquaticEntityTags.CRUSTACEAN,
            HybridAquaticEntityTags.SMALL_PREY),
        listOf(
            HybridAquaticEntityTags.NONE)),
    VariantHolder<OtterEntity.Companion.Type> {

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?
    ): SpawnGroupData? {
        val biome = world.getBiome(this.blockPosition())
        val selectedType = Type.fromBiome(biome)
        this.variant = selectedType
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
    }


    override fun getBreedOffspring(p0: ServerLevel, p1: AgeableMob): AgeableMob? {
        return null
    }

    override fun getStandingEyeHeight(pose: Pose, dimensions: EntityDimensions): Float {
        return dimensions.height * 0.6f
    }

    override fun getWaterline(): Float {
        return 0.15f
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 12.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ATTACK_KNOCKBACK, 1.0)
                .add(Attributes.FOLLOW_RANGE, 32.0)
        }

        val TYPE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(OtterEntity::class.java, EntityDataSerializers.INT)

        enum class Type(val id: Int, private val key: String) : StringRepresentable {
            RIVER(0, "river"),
            SEA(1, "sea");

            override fun getSerializedName(): String {
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
                    return CODEC.byName(name, RIVER) as Type
                }

                fun fromId(id: Int): Type {
                    return BY_ID.apply(id) as Type
                }

                fun fromBiome(biome: Holder<Biome>): Type {
                    return when {
                        biome.`is`(BiomeTags.IS_BEACH) -> {
                            SEA
                        }

                        biome.`is`(BiomeTags.IS_OCEAN) -> {
                            SEA
                        }

                        else -> {
                            RIVER
                        }
                    }
                }
            }
        }
    }

    override fun defineSynchedData() {
        entityData.define(TYPE, 0)
        super.defineSynchedData()
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        nbt.putString("Type", this.variant.serializedName)

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