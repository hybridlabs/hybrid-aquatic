package dev.hybridlabs.aquatic.entity.critter

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.tag.HybridAquaticBlockTags
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.protocol.game.ClientboundGameEventPacket
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.util.ByIdMap
import net.minecraft.util.StringRepresentable
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.block.Blocks
import java.util.function.IntFunction
import kotlin.random.Random

@Suppress("DEPRECATION")
class SeaUrchinEntity(entityType: EntityType<out SeaUrchinEntity>, world: Level) :
    HybridAquaticCritterEntity(entityType, world),
    VariantHolder<SeaUrchinEntity.Companion.Type> {

    private var timeUntilNextBreak = 0
    private var spawnUrchinOnNextBreak = false

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

    private fun touch(mob: Mob) {
        if (mob.hurt(damageSources().mobAttack(this), 0.5f)) {
            this.playSound(SoundEvents.PUFFER_FISH_STING, 1.0f, 1.0f)
        }
    }

    override fun tick() {
        super.tick()

        if (level().isClientSide) {
            return
        }

        if (timeUntilNextBreak > 0) {
            timeUntilNextBreak--
            return
        }

        if (level().random.nextInt(6000) < 300) {
            breakKelpUnderneath()
            timeUntilNextBreak = 2400 + level().random.nextInt(1200)
        }
    }

    private fun breakKelpUnderneath() {
        val posUnderneath = BlockPos(this.x.toInt(), (this.y + 1).toInt(), this.z.toInt())
        if (level().getBlockState(posUnderneath).`is`(HybridAquaticBlockTags.URCHIN_BREAKABLES)) {
            level().setBlockAndUpdate(posUnderneath, Blocks.AIR.defaultBlockState())
            if (spawnUrchinOnNextBreak) {
                val newUrchin = HybridAquaticEntityTypes.SEA_URCHIN.get().create(level())
                newUrchin?.moveTo(this.x, this.y, this.z, this.xRot, 0.0f)
                level().addFreshEntity(newUrchin)
                spawnUrchinOnNextBreak = false
            } else {
                spawnUrchinOnNextBreak = true
            }
        }
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }

    override fun defineSynchedData() {
        entityData.define(TYPE, 0)
        super.defineSynchedData()
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        nbt.putString("Type", this.variant.toString())
        super.addAdditionalSaveData(nbt)
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        this.variant = Type.byName(nbt.getString("Type"))
        super.readAdditionalSaveData(nbt)
    }

    override fun getVariant(): Type {
        return Type.fromId((entityData.get(TYPE) as Int))
    }

    override fun setVariant(type: Type) {
        entityData.set(TYPE, type.id)
    }
}