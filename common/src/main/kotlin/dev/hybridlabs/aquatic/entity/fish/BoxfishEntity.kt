package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.hapi.entity.ai.MobTargetConfiguration
import dev.hybridlabs.hapi.entity.base.aquatic.BaseFishEntity
import dev.hybridlabs.hapi.tag.HAPIEntityTags
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.util.ByIdMap
import net.minecraft.util.StringRepresentable
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.SpawnGroupData
import net.minecraft.world.entity.VariantHolder
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import java.util.function.IntFunction
import kotlin.random.Random

@Suppress("DEPRECATION")
class BoxfishEntity(type: EntityType<out BoxfishEntity>, world: Level) :
    BaseFishEntity(type, world), VariantHolder<BoxfishEntity.Companion.Type> {

    override fun getTargetConfig() = MobTargetConfiguration.ofPrey(
        HAPIEntityTags.MEDIUM_CREATURES,
        HAPIEntityTags.LARGE_CREATURES,
        HAPIEntityTags.ALL_SHARKS
    )

    override fun getMaxSpawnClusterSize(): Int {
        return 1
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?
    ): SpawnGroupData? {
        variant = Type.entries.random(Random)
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData)
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }

        enum class Type(val id: Int, private val key: String) : StringRepresentable {
            LONGHORN_COWFISH(0, "longhorn_cowfish"),
            WHITESPOTTED(1, "whitespotted");

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
                    return CODEC.byName(name, LONGHORN_COWFISH) as Type
                }

                fun fromId(id: Int): Type {
                    return BY_ID.apply(id) as Type
                }
            }
        }

        val TYPE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(BoxfishEntity::class.java, EntityDataSerializers.INT)
    }

    override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        super.defineSynchedData(builder)
        builder.define(TYPE, 0)
    }

    override fun addAdditionalSaveData(compound: CompoundTag) {
        compound.putString("Type", this.variant.serializedName)
        super.addAdditionalSaveData(compound)
    }

    override fun readAdditionalSaveData(compound: CompoundTag) {
        this.variant = Type.byName(compound.getString("Type"))
        super.readAdditionalSaveData(compound)
    }

    override fun getVariant(): Type {
        return Type.fromId((entityData.get(TYPE) as Int))
    }

    override fun setVariant(type: Type) {
        entityData.set(TYPE, type.id)
    }
}
