package dev.hybridlabs.aquatic.entity.jellyfish

import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.aquatic.entity.base.HAJellyfishEntity
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.util.ByIdMap
import net.minecraft.util.StringRepresentable
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import java.util.function.IntFunction
import kotlin.random.Random

@Suppress("DEPRECATION")
class SeaNettleEntity(entityType: EntityType<out SeaNettleEntity>, world: Level) :
    HAJellyfishEntity(entityType, world, true, 1),
    VariantHolder<SeaNettleEntity.Companion.Type> {

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    override fun getDefaultDimensions(pose: Pose): EntityDimensions {
        val scale = when (variant) {
            Type.COMPASS -> 0.6f
            else -> 1.0f
        }
        return super.getDefaultDimensions(pose).scale(scale)
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.4)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }

        val TYPE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(SeaNettleEntity::class.java, EntityDataSerializers.INT)

        enum class Type(val id: Int, private val key: String) : StringRepresentable {
            PACIFIC(0, "pacific"),
            COMPASS(1, "compass");

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
                    return CODEC.byName(name, PACIFIC) as Type
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
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?
    ): SpawnGroupData? {
        val spawnData = super.finalizeSpawn(world, difficulty, spawnReason, entityData)

        val variant = Type.entries.random(Random).id
        this.variant = Type.fromId(variant)

        if (spawnReason == MobSpawnType.CHUNK_GENERATION || spawnReason == MobSpawnType.NATURAL) {
            val jellyfishCount = (this.maxSpawnClusterSize * this.random.nextFloat()).toInt()
            if (jellyfishCount > 0 && !level().isClientSide()) {
                for (i in 0 until  jellyfishCount) {
                    val distance = 1.5f
                    val entity = SeaNettleEntity(HAEntityTypes.SEA_NETTLE.get(), this.level())
                    entity.variant = this.variant
                    entity.moveTo(
                        this.x + this.random.nextFloat() * distance,
                        this.y + this.random.nextFloat() * distance,
                        this.z + this.random.nextFloat() * distance
                    )
                    level().addFreshEntity(entity)
                }
            }
        }
        return spawnData
    }

    //#region Data
    override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        builder.define(TYPE, 0)
        defineSynchedData(builder)
    }

    override fun addAdditionalSaveData(compound: CompoundTag) {
        compound.putString("Type", this.variant.serializedName)
        super.addAdditionalSaveData(compound)
    }

    override fun readAdditionalSaveData(compound: CompoundTag) {
        this.variant = Type.byName(compound.getString("Type"))
        super.readAdditionalSaveData(compound)
    }
    //#endregion

    override fun getVariant(): Type {
        return Type.fromId((entityData.get(TYPE) as Int))
    }

    override fun setVariant(type: Type) {
        entityData.set(TYPE, type.id)
    }
}
