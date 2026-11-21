package dev.hybridlabs.aquatic.entity.fish

import net.minecraft.world.entity.EntitySpawnReason

import net.minecraft.world.level.storage.ValueInput
import net.minecraft.world.level.storage.ValueOutput

import dev.hybridlabs.hapi.entity.base.VariantHolder
import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.hapi.entity.ai.MobTargetConfiguration
import dev.hybridlabs.hapi.entity.ai.goal.aquatic.WaterAnimalBreedGoal
import dev.hybridlabs.hapi.entity.ai.goal.aquatic.WaterAnimalGrazeGoal
import dev.hybridlabs.hapi.entity.base.aquatic.BaseFishEntity
import dev.hybridlabs.aquatic.tag.HABlockTags
import dev.hybridlabs.hapi.tag.HAPIEntityTags
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.ByIdMap
import net.minecraft.util.StringRepresentable
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.TemptGoal
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import java.util.function.IntFunction
import kotlin.random.Random

@Suppress("DEPRECATION")
class GoldfishEntity(type: EntityType<out GoldfishEntity>, world: Level) :
    BaseFishEntity(type, world), VariantHolder<GoldfishEntity.Companion.Type> {

    override fun getTargetConfig() = MobTargetConfiguration.ofPrey(
        HAPIEntityTags.MEDIUM_CREATURES,
        HAPIEntityTags.LARGE_CREATURES,
        HAPIEntityTags.ALL_SHARKS
    )

    override fun getMaxSpawnClusterSize(): Int {
        return 1
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, WaterAnimalBreedGoal(this, 1.1))
        goalSelector.addGoal(2, TemptGoal(this, 1.1, BREEDING_INGREDIENT, false))
        goalSelector.addGoal(3, WaterAnimalGrazeGoal(this, HABlockTags.ALGIVORE_EDIBLE))
    }

    override fun removeWhenFarAway(distanceSquared: Double): Boolean {
        return false
    }

    override fun getBreedOffspring(p0: ServerLevel, p1: AgeableMob): AgeableMob? {
        return HAEntityTypes.GOLDFISH.get().create(p0, EntitySpawnReason.MOB_SUMMONED)
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: EntitySpawnReason,
        entityData: SpawnGroupData?,
    ): SpawnGroupData? {
        variant = Type.entries.random(Random)
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData)
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.TEMPT_RANGE, 10.0)
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }

        enum class Type(val id: Int, private val key: String) : StringRepresentable {
            FANTAIL(0, "fantail_goldfish"),
            RYUKIN(1, "ryukin_goldfish"),
            TELESCOPE(2, "telescope_goldfish"),
            BUBBLE_EYE(3, "bubble_eye_goldfish"),
            COMMON_GOLDFISH(4, "common_goldfish"),
            SHUBUNKIN(5, "shubunkin_goldfish");

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
                    return CODEC.byName(name, FANTAIL) as Type
                }

                fun fromId(id: Int): Type {
                    return BY_ID.apply(id) as Type
                }
            }
        }

        val TYPE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(GoldfishEntity::class.java, EntityDataSerializers.INT)
    }

    override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        super.defineSynchedData(builder)
        builder.define(TYPE, 0)
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
