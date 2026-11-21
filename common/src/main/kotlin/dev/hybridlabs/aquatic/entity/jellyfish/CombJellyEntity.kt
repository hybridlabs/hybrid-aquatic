package dev.hybridlabs.aquatic.entity.jellyfish

import net.minecraft.world.level.storage.ValueInput
import net.minecraft.world.level.storage.ValueOutput

import dev.hybridlabs.hapi.entity.base.aquatic.BaseJellyfishEntity
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
import java.util.function.IntFunction
import kotlin.random.Random

class CombJellyEntity(entityType: EntityType<out CombJellyEntity>, world: Level) :
    BaseJellyfishEntity(entityType, world, false, 0),
    VariantHolder<CombJellyEntity.Companion.Type> {

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.TEMPT_RANGE, 10.0)
                .add(Attributes.MAX_HEALTH, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.4)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)

        }

        val TYPE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(CombJellyEntity::class.java, EntityDataSerializers.INT)

        enum class Type(val id: Int, private val key: String) : StringRepresentable {
            SEA_WALNUT(0, "sea_walnut"),
            BLOODYBELLY(1, "bloodybelly");

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
                    return CODEC.byName(name, SEA_WALNUT) as Type
                }

                fun fromId(id: Int): Type {
                    return BY_ID.apply(id) as Type
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
        variant = Type.entries.random(Random)
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData)
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