package dev.hybridlabs.aquatic.entity.shark

import dev.hybridlabs.aquatic.entity.ai.MobTargetConfiguration
import dev.hybridlabs.aquatic.entity.ai.goal.WaterAnimalSitGoal
import dev.hybridlabs.aquatic.tag.HAEntityTags
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
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import java.util.function.IntFunction
import kotlin.random.Random

@Suppress("DEPRECATION")
class HoundSharkEntity(type: EntityType<out HoundSharkEntity>, world: Level) :
    HASharkEntity(type, world), VariantHolder<HoundSharkEntity.Type> {

    override fun getTargetConfig() = TARGET_CONFIG

    override val isPassive: Boolean = false
    override val closePlayerAttack: Boolean = false

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?,
    ): SpawnGroupData? {
        variant = Type.entries.random(Random)
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(0, HurtByTargetGoal(this))
        goalSelector.addGoal(1, WaterAnimalSitGoal(this))
    }

    companion object {
        private val TARGET_CONFIG = MobTargetConfiguration.create(
            listOf(
                HAEntityTags.SMALL_CREATURES
            ),
            listOf(
                HAEntityTags.MEDIUM_CREATURES,
                HAEntityTags.LARGE_CREATURES,
                HAEntityTags.MEDIUM_SHARK,
                HAEntityTags.LARGE_SHARK
            ),
        )

        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 12.0)
                .add(Attributes.MOVEMENT_SPEED, 0.7)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 16.0)
        }

        val TYPE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HoundSharkEntity::class.java, EntityDataSerializers.INT)
    }

    override fun defineSynchedData() {
        entityData.define(TYPE, 0)
        super.defineSynchedData()
    }

    override fun addAdditionalSaveData(compound: CompoundTag) {
        compound.putString("Type", this.variant.serializedName)
        super.addAdditionalSaveData(compound)
    }

    override fun readAdditionalSaveData(compound: CompoundTag) {
        this.variant = Type.byName(compound.getString("Type"))
        super.readAdditionalSaveData(compound)
    }

    enum class Type(val id: Int, private val key: String) : StringRepresentable {
        LEOPARD(0, "leopard");

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
