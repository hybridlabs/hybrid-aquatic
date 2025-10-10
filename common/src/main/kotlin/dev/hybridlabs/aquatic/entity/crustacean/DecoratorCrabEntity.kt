package dev.hybridlabs.aquatic.entity.crustacean

import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.core.registries.Registries
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvents
import net.minecraft.util.ByIdMap
import net.minecraft.util.StringRepresentable
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.SpawnGroupData
import net.minecraft.world.entity.VariantHolder
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.RandomStrollGoal
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.level.storage.loot.LootTable
import java.util.function.IntFunction
import kotlin.random.Random

@Suppress("DEPRECATION")
class DecoratorCrabEntity(entityType: EntityType<out HybridAquaticCrustaceanEntity>, world: Level) :
    HybridAquaticCrustaceanEntity(entityType, world, false),
    VariantHolder<DecoratorCrabEntity.Companion.Type> {

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(5, TryFindWaterGoal(this))
        goalSelector.addGoal(3, RandomStrollGoal(this, 0.4))
    }

    override fun getDefaultLootTable(): ResourceKey<LootTable?> {
        return ResourceKey<LootTable?>.create(
            Registries.LOOT_TABLE,
            ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "entities/decorator_crab")
        )
    }

    var coralTimer: Int
        get() = entityData.get(CORAL_TIMER)
        set(value) = entityData.set(CORAL_TIMER, value)

    override fun mobInteract(player: Player, hand: InteractionHand): InteractionResult {
        val itemStack = player.getItemInHand(hand)
        if (!itemStack.isEmpty && itemStack.`is`(Items.SHEARS) && coralTimer == 0) {
            if (!level().isClientSide) {
                this.coralTimer = 3600
                this.playSound(SoundEvents.SHEEP_SHEAR, 1.0f, 1.0f)
                this.gameEvent(GameEvent.SHEAR, player)
                itemStack.hurtAndBreak(1, player,getSlotForHand(hand))
                spawnAtLocation(ItemStack(HybridAquaticItems.CORAL_CHUNK.get()))
                return InteractionResult.SUCCESS
            }
            return InteractionResult.CONSUME
        }
        return super.mobInteract(player, hand)
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
        entityData: SpawnGroupData?
    ): SpawnGroupData? {
        variant = Type.entries.random(Random)
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData)
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
            SynchedEntityData.defineId(DecoratorCrabEntity::class.java, EntityDataSerializers.INT)
        val CORAL_TIMER: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(DecoratorCrabEntity::class.java, EntityDataSerializers.INT)

        enum class Type(val id: Int, private val key: String) : StringRepresentable {
            CORAL(0, "coral");

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

    override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        builder.define(TYPE, 0)
        builder.define(CORAL_TIMER, 0)
        super.defineSynchedData(builder)
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        nbt.putString("Type", this.variant.serializedName)
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