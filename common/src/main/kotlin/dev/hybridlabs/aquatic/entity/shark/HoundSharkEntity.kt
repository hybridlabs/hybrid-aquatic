package dev.hybridlabs.aquatic.entity.shark

import net.minecraft.world.level.storage.ValueInput
import net.minecraft.world.level.storage.ValueOutput

import dev.hybridlabs.hapi.tag.HAPIEntityTags
import dev.hybridlabs.hapi.entity.ai.MobTargetConfiguration
import dev.hybridlabs.hapi.entity.ai.goal.aquatic.WaterAnimalSitGoal
import dev.hybridlabs.hapi.entity.base.aquatic.BaseSharkEntity
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
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import java.util.function.IntFunction
import kotlin.random.Random

@Suppress("DEPRECATION")
class HoundSharkEntity(type: EntityType<out HoundSharkEntity>, world: Level) :
    BaseSharkEntity(type, world), VariantHolder<HoundSharkEntity.Type> {

    override fun getTargetConfig() = TARGET_CONFIG

    override val isPassive: Boolean = false
    override val closePlayerAttack: Boolean = false

    override fun getMaxSpawnClusterSize(): Int {
        return 2
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

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(0, HurtByTargetGoal(this))
        goalSelector.addGoal(1, WaterAnimalSitGoal(this))
    }

    companion object {
        private val TARGET_CONFIG = MobTargetConfiguration.create(
            listOf(
                HAPIEntityTags.SMALL_CREATURES
            ),
            listOf(
                HAPIEntityTags.MEDIUM_CREATURES,
                HAPIEntityTags.LARGE_CREATURES,
                HAPIEntityTags.MEDIUM_SHARK,
                HAPIEntityTags.LARGE_SHARK
            ),
        )

        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.TEMPT_RANGE, 10.0)
                .add(Attributes.MAX_HEALTH, 12.0)
                .add(Attributes.MOVEMENT_SPEED, 0.7)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 16.0)
        }

        val TYPE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HoundSharkEntity::class.java, EntityDataSerializers.INT)
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

            fun byName(name: String): Type {
                return CODEC.byName(name, LEOPARD) as Type
            }

            fun fromId(id: Int): Type {
                return BY_ID.apply(id) as Type
            }
        }
    }

    override var variant: Type
        get() = Type.fromId((entityData.get(TYPE) as Int))
        set(value) {
            entityData.set(TYPE, value.id)
        }
}
