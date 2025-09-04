package dev.hybridlabs.aquatic.entity.shark

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
import net.minecraft.util.StringRepresentable
import net.minecraft.util.function.ByIdMap
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.ServerLevelAccess
import net.minecraft.world.World
import java.util.function.IntFunction
import kotlin.random.Random

@Suppress("DEPRECATION")
class HoundSharkEntity(entityType: EntityType<out HoundSharkEntity>, world: Level) :
    HybridAquaticSharkEntity(
        entityType, world, listOf(HybridAquaticEntityTags.CEPHALOPOD, HybridAquaticEntityTags.SMALL_PREY, HybridAquaticEntityTags.CRUSTACEAN), false, false
    ),
    VariantHolder<HoundSharkEntity.Type> {

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?
    ): SpawnGroupData? {
        variant = Type.entries.random(Random)
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun getSpawnClusterSize(): Int {
        return 1
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, RevengeGoal(this))
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 12.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 16.0)
        }
        val TYPE: EntityDataAccessor<Int> = SynchedEntityData.defineId(HoundSharkEntity::class.java, EntityDataSerializers.INTEGER)
    }

    override fun getMaxSize(): Int {
        return 3
    }

    override fun getMinSize(): Int {
        return -3
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

    enum class Type(val id: Int, private val key: String) : StringRepresentable {
        LEOPARD(0, "leopard");

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
                return CODEC.byName(name, LEOPARD) as Type
            }

            fun fromId(id: Int): Type {
                return BY_ID.apply(id) as Type
            }
        }
    }

    override fun getVariant(): Type {
        return Type.fromId((entityData.get(TYPE) as Int))
    }

    override fun setVariant(type: Type) {
        entityData.set(TYPE, type.id)
    }
}