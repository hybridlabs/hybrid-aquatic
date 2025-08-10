package dev.hybridlabs.aquatic.entity.critter

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.tag.HybridAquaticBlockTags
import net.minecraft.block.Blocks
import net.minecraft.entity.*
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.StringIdentifiable
import net.minecraft.util.function.ValueLists
import net.minecraft.util.math.BlockPos
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import java.util.function.IntFunction
import kotlin.random.Random

@Suppress("DEPRECATION")
class SeaUrchinEntity(entityType: EntityType<out SeaUrchinEntity>, world: World) :
    HybridAquaticCritterEntity(entityType, world),
    VariantHolder<SeaUrchinEntity.Companion.Type> {

    private var timeUntilNextBreak = 0
    private var spawnUrchinOnNextBreak = false

    override fun initialize(
        world: ServerWorldAccess,
        difficulty: LocalDifficulty,
        spawnReason: SpawnReason,
        entityData: EntityData?
    ): EntityData? {
        variant = Type.entries.random(Random)
        return super.initialize(world, difficulty, spawnReason, entityData)
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 2.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 2.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0)
        }
        val TYPE: TrackedData<Int> = DataTracker.registerData(SeaUrchinEntity::class.java, TrackedDataHandlerRegistry.INTEGER)

        enum class Type(val id: Int, private val key: String) : StringIdentifiable {
            SMALL(0, "small"),
            LARGE(1, "large");

            override fun asString(): String {
                return this.key
            }

            companion object {
                val CODEC: StringIdentifiable.EnumCodec<Type> = StringIdentifiable.createCodec { SeaUrchinEntity.Companion.Type.entries.toTypedArray() }
                private val BY_ID: IntFunction<Type> = ValueLists.createIdToValueFunction(
                    { obj: Type -> obj.id },
                    entries.toTypedArray(),
                    ValueLists.OutOfBoundsHandling.ZERO
                )

                fun byName(name: String?): Type {
                    return CODEC.byId(name, SMALL) as Type
                }

                fun fromId(id: Int): Type {
                    return BY_ID.apply(id) as Type
                }
            }
        }
    }

    override fun onPlayerCollision(player: PlayerEntity) {
        super.onPlayerCollision(player)

        if (player is ServerPlayerEntity) {
            player.damage(this.damageSources.mobAttack(this), 0.5f)
        }
    }

    override fun damage(source: DamageSource?, amount: Float): Boolean {
        if (super.damage(source, amount)) {

            val attacker = source?.attacker
            if (attacker is LivingEntity && attacker.mainHandStack.isEmpty) {
                attacker.damage(this.damageSources.thorns(this), 2.0f)
            }

            return true
        }

        return false
    }

    override fun tick() {
        super.tick()

        if (world.isClient) {
            return
        }

        if (timeUntilNextBreak > 0) {
            timeUntilNextBreak--
            return
        }

        if (world.random.nextInt(6000) < 300) {
            breakKelpUnderneath()
            timeUntilNextBreak = 2400 + world.random.nextInt(1200)
        }
    }

    private fun breakKelpUnderneath() {
        val posUnderneath = BlockPos(this.x.toInt(), (this.y + 1).toInt(), this.z.toInt())
        if (world.getBlockState(posUnderneath).isIn(HybridAquaticBlockTags.URCHIN_BREAKABLES)) {
            world.setBlockState(posUnderneath, Blocks.AIR.defaultState)
            if (spawnUrchinOnNextBreak) {
                val newUrchin = HybridAquaticEntityTypes.SEA_URCHIN.create(world)
                newUrchin?.refreshPositionAndAngles(this.x, this.y, this.z, this.yaw, 0.0f)
                world.spawnEntity(newUrchin)
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

    override fun initDataTracker(builder: DataTracker.Builder) {
        builder.add(TYPE, 0)
        super.initDataTracker(builder)
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        nbt.putString("Type", this.variant.asString())
        super.writeCustomDataToNbt(nbt)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        this.variant = Type.byName(nbt.getString("Type"))
        super.readCustomDataFromNbt(nbt)
    }

    override fun getVariant(): Type {
        return Type.fromId((dataTracker.get(TYPE) as Int))
    }

    override fun setVariant(type: Type) {
        dataTracker.set(TYPE, type.id)
    }
}