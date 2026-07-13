package dev.hybridlabs.aquatic.entity.mammal

import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.aquatic.entity.ai.goal.WaterAnimalBreedGoal
import dev.hybridlabs.aquatic.entity.base.HASirenianEntity
import dev.hybridlabs.aquatic.sound.HASoundEvents
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.util.ByIdMap
import net.minecraft.util.StringRepresentable
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import java.util.function.IntFunction
import kotlin.random.Random

@Suppress("DEPRECATION")
class ManateeEntity(type: EntityType<out ManateeEntity>, world: Level) : HASirenianEntity(type, world),
    VariantHolder<ManateeEntity.Companion.Type> {

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(2, WaterAnimalBreedGoal(this, 1.1))
    }

    override fun getBreedOffspring(p0: ServerLevel, p1: AgeableMob): ManateeEntity? {
        return HAEntityTypes.MANATEE.get().create(p0)
    }

    //#region SFX
    override fun getAmbientSound(): SoundEvent {
        return HASoundEvents.MANATEE_AMBIENT.get()
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return HASoundEvents.MANATEE_HURT.get()
    }

    override fun getDeathSound(): SoundEvent {
        return HASoundEvents.MANATEE_DIE.get()
    }

    override fun getSwimSplashSound(): SoundEvent {
        return HASoundEvents.MANATEE_SPLASH.get()
    }

    override fun getSwimSound(): SoundEvent {
        return HASoundEvents.MANATEE_SWIM.get()
    }
    //#endregion

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
                .add(Attributes.MAX_HEALTH, 32.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 6.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 12.0)
        }

        val TYPE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(ManateeEntity::class.java, EntityDataSerializers.INT)

        enum class Type(val id: Int, private val key: String) : StringRepresentable {
            PLAIN(0, "plain"),
            MOSSY(1, "mossy");

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
                    return CODEC.byName(name, PLAIN) as Type
                }

                fun fromId(id: Int): Type {
                    return BY_ID.apply(id) as Type
                }
            }
        }
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