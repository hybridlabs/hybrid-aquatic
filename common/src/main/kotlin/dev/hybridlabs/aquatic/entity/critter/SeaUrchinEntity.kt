package dev.hybridlabs.aquatic.entity.critter

import dev.hybridlabs.aquatic.entity.ai.goal.UrchinEatKelpGoal
import dev.hybridlabs.aquatic.entity.base.HACritterEntity
import dev.hybridlabs.aquatic.tag.HABlockTags
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.protocol.game.ClientboundGameEventPacket
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerPlayer
import net.minecraft.util.ByIdMap
import net.minecraft.util.StringRepresentable
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import java.util.function.IntFunction
import kotlin.random.Random

@Suppress("DEPRECATION")
class SeaUrchinEntity(type: EntityType<out SeaUrchinEntity>, world: Level) :
    HACritterEntity(type, world),
    VariantHolder<SeaUrchinEntity.Companion.Type> {

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
    ): SpawnGroupData? {
        variant = Type.entries.random(Random)
        this.refreshDimensions()
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData)
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, UrchinEatKelpGoal(this, HABlockTags.KELP))
    }

    override fun getDefaultDimensions(pose: Pose): EntityDimensions {
        val scale = when (variant) {
            Type.LARGE -> 2.0f
            else -> 1.0f
        }
        return super.getDefaultDimensions(pose).scale(scale)
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 2.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
        }

        val TYPE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(SeaUrchinEntity::class.java, EntityDataSerializers.INT)

        enum class Type(val id: Int, private val key: String) : StringRepresentable {
            SMALL(0, "small"),
            LARGE(1, "large");

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
                    return CODEC.byName(name, SMALL) as Type
                }

                fun fromId(id: Int): Type {
                    return BY_ID.apply(id) as Type
                }
            }
        }
    }

    override fun playerTouch(entity: Player) {
        if (entity is ServerPlayer && entity.hurt(damageSources().mobAttack(this), 0.5f)) {
            if (!this.isSilent) {
                entity.connection.send(ClientboundGameEventPacket(ClientboundGameEventPacket.PUFFER_FISH_STING, 0.0f))
            }
        }
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