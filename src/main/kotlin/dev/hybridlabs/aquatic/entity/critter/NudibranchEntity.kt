package dev.hybridlabs.aquatic.entity.critter

import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.VariantHolder
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.StringIdentifiable
import net.minecraft.util.function.ValueLists
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import java.util.function.IntFunction
import kotlin.random.Random

class NudibranchEntity(entityType: EntityType<out NudibranchEntity>, world: World) :
    HybridAquaticCritterEntity(entityType, world),
    VariantHolder<NudibranchEntity.Type> {

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 2.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 2.0)
        }
        val TYPE: TrackedData<Int> = DataTracker.registerData(NudibranchEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
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

    override fun getMaxSize() : Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }

    override fun initDataTracker() {
        dataTracker.startTracking(TYPE, 0)
        super.initDataTracker()
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        nbt.putString("Type", this.variant.asString())
        super.writeCustomDataToNbt(nbt)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        this.variant = Type.byName(nbt.getString("Type"))
        super.readCustomDataFromNbt(nbt)
    }

    enum class Type(val id: Int, private val key: String) : StringIdentifiable {
        BABA(0, "baba"),
        BULLOCK(1, "bullock"),
        FESTIVA(2, "festiva"),
        KUBARYANA(3, "kubaryana"),
        KUNIEI(4, "kuniei"),
        MAGNIFICENT(5, "magnificent"),
        PYJAMA(6, "pyjama"),
        SAGAMI(7, "sagami"),
        YONOWAE(8, "yonowae");

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
                return CODEC.byId(name, BABA) as Type
            }

            fun fromId(id: Int): Type {
                return BY_ID.apply(id) as Type
            }
        }
    }

    override fun getVariant(): Type {
        return Type.fromId((dataTracker.get(TYPE) as Int))
    }

    override fun setVariant(type: Type) {
        dataTracker.set(TYPE, type.id)
    }
}