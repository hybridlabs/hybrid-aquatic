package dev.hybridlabs.aquatic.entity.critter

import net.minecraft.world.level.storage.ValueInput
import net.minecraft.world.level.storage.ValueOutput

import dev.hybridlabs.hapi.tag.HAPIBiomeTags
import dev.hybridlabs.hapi.entity.base.aquatic.BaseCritterEntity
import net.minecraft.core.Holder
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.util.ByIdMap
import net.minecraft.util.StringRepresentable
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.EntitySpawnReason
import net.minecraft.world.entity.SpawnGroupData
import dev.hybridlabs.hapi.entity.base.VariantHolder
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.biome.Biome
import java.util.function.IntFunction

@Suppress("DEPRECATION")
class SeaSlugEntity(type: EntityType<out SeaSlugEntity>, world: Level) : BaseCritterEntity(type, world),
    VariantHolder<SeaSlugEntity.Companion.Type> {

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.TEMPT_RANGE, 10.0)
                .add(Attributes.MAX_HEALTH, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 2.0)
        }

        val TYPE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(SeaSlugEntity::class.java, EntityDataSerializers.INT)

        enum class Type(val id: Int, private val key: String) : StringRepresentable {
            SEA_HARE(0, "spotted_sea_hare"),
            NUDIBRANCH(1, "nudibranch");

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

                fun byName(name: String): Type {
                    return CODEC.byName(name, SEA_HARE) as Type
                }

                fun fromId(id: Int): Type {
                    return BY_ID.apply(id) as Type
                }

                fun fromBiome(biome: Holder<Biome>): Type {
                    return when {

                        biome.`is`(HAPIBiomeTags.CORAL_REEF) -> {
                            NUDIBRANCH
                        }

                        biome.`is`(HAPIBiomeTags.LUKEWARM_OCEANS) -> {
                            NUDIBRANCH
                            SEA_HARE
                        }

                        else -> {
                            SEA_HARE
                        }
                    }
                }
            }
        }
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: EntitySpawnReason,
        entityData: SpawnGroupData?
    ): SpawnGroupData? {
        val biome = world.getBiome(this.blockPosition())
        val selectedType = Type.fromBiome(biome)
        this.variant = selectedType
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData)
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }

    override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        builder.define(TYPE, 0)
        super.defineSynchedData(builder)
    }

    override fun addAdditionalSaveData(output: ValueOutput) {
        output.putString("Type", this.variant.serializedName)
        super.addAdditionalSaveData(output)
    }

    override fun readAdditionalSaveData(input: ValueInput) {
        this.variant = Type.byName(input.getStringOr("Type", this.variant.serializedName))
        super.readAdditionalSaveData(input)
    }

    override var variant: Type
        get() = Type.fromId((entityData.get(TYPE) as Int))
        set(value) {
            entityData.set(TYPE, value.id)
        }
}