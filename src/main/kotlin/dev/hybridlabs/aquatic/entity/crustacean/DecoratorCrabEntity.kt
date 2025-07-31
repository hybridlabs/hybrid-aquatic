package dev.hybridlabs.aquatic.entity.crustacean

import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.VariantHolder
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.nbt.NbtCompound
import net.minecraft.sound.SoundEvents
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.Identifier
import net.minecraft.util.StringIdentifiable
import net.minecraft.util.function.ValueLists
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import net.minecraft.world.event.GameEvent
import java.util.function.IntFunction
import kotlin.random.Random

class DecoratorCrabEntity(entityType: EntityType<out HybridAquaticCrustaceanEntity>, world: World) :
    HybridAquaticCrustaceanEntity(entityType, world, false),
    VariantHolder<DecoratorCrabEntity.Companion.Type> {

    override fun getLootTableId(): Identifier {
        return Identifier("hybrid-aquatic", "entities/decorator_crab")
    }

    var coralTimer: Int
        get() = dataTracker.get(CORAL_TIMER)
        set(value) = dataTracker.set(CORAL_TIMER, value)

    override fun interactMob(player: PlayerEntity, hand: Hand): ActionResult {
        val itemStack = player.getStackInHand(hand)
        if (!itemStack.isEmpty && itemStack.isOf(Items.SHEARS) && coralTimer == 0) {
            if (!world.isClient) {
                this.coralTimer = 3600
                this.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, 1.0f, 1.0f)
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
            dataTracker.set(CORAL_TIMER, coralTimer - 1)
        }
    }

    override fun initialize(
        world: ServerWorldAccess,
        difficulty: LocalDifficulty,
        spawnReason: SpawnReason,
        entityData: EntityData?,
        entityNbt: NbtCompound?
    ): EntityData? {
        variant = Type.entries.random(Random)
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt)
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 3.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 4.0)
        }

        val TYPE: TrackedData<Int> =
            DataTracker.registerData(DecoratorCrabEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val CORAL_TIMER: TrackedData<Int> =
            DataTracker.registerData(DecoratorCrabEntity::class.java, TrackedDataHandlerRegistry.INTEGER)

        enum class Type(val id: Int, private val key: String) : StringIdentifiable {
            CORAL(0, "coral");

            override fun asString(): String {
                return this.key
            }

            companion object {
                val CODEC: StringIdentifiable.Codec<Type> = StringIdentifiable.createCodec { entries.toTypedArray() }
                private val BY_ID: IntFunction<Type> = ValueLists.createIdToValueFunction(
                    { obj: Type -> obj.id },
                    entries.toTypedArray(),
                    ValueLists.OutOfBoundsHandling.ZERO
                )

                fun byName(name: String?): Type {
                    return CODEC.byId(name, CORAL) as Type
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

    override fun initDataTracker() {
        dataTracker.startTracking(TYPE, 0)
        dataTracker.startTracking(CORAL_TIMER, 0)
        super.initDataTracker()
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        nbt.putString("Type", this.variant.asString())
        nbt.putInt("CoralTimer", coralTimer)
        super.writeCustomDataToNbt(nbt)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        this.variant = Type.byName(nbt.getString("Type"))
        this.coralTimer = nbt.getInt("CoralTimer")
        super.readCustomDataFromNbt(nbt)
    }

    override fun getVariant(): Type {
        return Type.fromId((dataTracker.get(TYPE) as Int))
    }

    override fun setVariant(type: Type) {
        dataTracker.set(TYPE, type.id)
    }
}