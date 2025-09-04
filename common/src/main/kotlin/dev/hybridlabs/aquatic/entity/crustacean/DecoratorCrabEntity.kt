package dev.hybridlabs.aquatic.entity.crustacean

import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityType
import net.minecraft.entity.MobSpawnType
import net.minecraft.entity.VariantHolder
import net.minecraft.entity.attribute.AttributeSupplier
import net.minecraft.entity.attribute.Attributes
import net.minecraft.entity.data.SynchedEntityData
import net.minecraft.entity.data.EntityDataAccessor
import net.minecraft.entity.data.EntityDataSerializers
import net.minecraft.entity.player.Player
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.nbt.CompoundTag
import net.minecraft.sound.SoundEvents
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.StringRepresentable
import net.minecraft.util.function.ByIdMap
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.ServerLevelAccess
import net.minecraft.world.World
import net.minecraft.world.event.GameEvent
import java.util.function.IntFunction
import kotlin.random.Random

@Suppress("DEPRECATION")
class DecoratorCrabEntity(entityType: EntityType<out HybridAquaticCrustaceanEntity>, world: Level) :
    HybridAquaticCrustaceanEntity(entityType, world, false),
    VariantHolder<DecoratorCrabEntity.Companion.Type> {

    override fun getLootTableId(): ResourceLocation {
        return ResourceLocation("hybrid-aquatic", "entities/decorator_crab")
    }

    var coralTimer: Int
        get() = entityData.get(CORAL_TIMER)
        set(value) = entityData.set(CORAL_TIMER, value)

    override fun interactMob(player:Player, hand: Hand): ActionResult {
        val itemStack = player.getStackInHand(hand)
        if (!itemStack.isEmpty && itemStack.isOf(Items.SHEARS) && coralTimer == 0) {
            if (!world.isClient) {
                this.coralTimer = 3600
                this.playSound(SoundEvents._SHEEP_SHEAR, 1.0f, 1.0f)
                this.emitGameEvent(GameEvent.SHEAR, player)
                itemStack.damage(1, player) { it.sendToolBreakStatus(hand) }
                dropStack(ItemStack(HybridAquaticItems.CORAL_CHUNK))
                return ActionResult.SUCCESS
            }
            return ActionResult.CONSUME
        }
        return super.interactMob(player, hand)
    }

    override fun tick() {
        super.tick()

        if (coralTimer > 0) {
            entityData.set(CORAL_TIMER, coralTimer - 1)
        }
    }

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

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }

        val TYPE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(DecoratorCrabEntity::class.java, EntityDataSerializers.INTEGER)
        val CORAL_TIMER: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(DecoratorCrabEntity::class.java, EntityDataSerializers.INTEGER)

        enum class Type(val id: Int, private val key: String) : StringRepresentable {
            CORAL(0, "coral");

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
                    return CODEC.byName(name, CORAL) as Type
                }

                fun fromId(id: Int): Type {
                    return BY_ID.apply(id) as Type
                }
            }
        }
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }

    override fun initSynchedEntityData() {
        entityData.define(TYPE, 0)
        entityData.define(CORAL_TIMER, 0)
        super.initSynchedEntityData()
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        nbt.putString("Type", this.variant.asString())
        nbt.putInt("CoralTimer", coralTimer)
        super.addAdditionalSaveData(nbt)
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        this.variant = Type.byName(nbt.getString("Type"))
        this.coralTimer = nbt.getInt("CoralTimer")
        super.readAdditionalSaveData(nbt)
    }

    override fun getVariant(): Type {
        return Type.fromId((entityData.get(TYPE) as Int))
    }

    override fun setVariant(type: Type) {
        entityData.set(TYPE, type.id)
    }
}