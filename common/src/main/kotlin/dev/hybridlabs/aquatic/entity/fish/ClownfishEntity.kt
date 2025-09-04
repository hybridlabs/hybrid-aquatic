package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
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

@Suppress("DEPRECATION")
class ClownfishEntity(entityType: EntityType<out ClownfishEntity>, world: World) :
    HybridAquaticFishEntity(
        entityType, world,
        listOf(
            HybridAquaticEntityTags.NONE
        ),
        listOf(
            HybridAquaticEntityTags.MEDIUM_PREY,
            HybridAquaticEntityTags.LARGE_PREY,
            HybridAquaticEntityTags.SHARK
        )
    ),
    VariantHolder<ClownfishEntity.Companion.Type> {

    override fun getLimitPerChunk(): Int {
        return 2
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
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 4.0)
        }

        val TYPE: TrackedData<Int> =
            DataTracker.registerData(ClownfishEntity::class.java, TrackedDataHandlerRegistry.INTEGER)


        enum class Type(val id: Int, private val key: String) : StringIdentifiable {
            OCELLARIS(0, "ocellaris"),
            CLARKII(1, "clarkii"),
            TOMATO(2, "tomato"),
            PINK_SKUNK(3, "pink_skunk"),
            ORANGE_SKUNK(4, "orange_skunk"),
            CINNAMON(5, "cinnamon"),
            PERCULA(6, "percula"),
            WHITEBAND(7, "whiteband");

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
                    return CODEC.byId(name, OCELLARIS) as Type
                }

                fun fromId(id: Int): Type {
                    return BY_ID.apply(id) as Type
                }
            }
        }
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

    override fun getVariant(): Type {
        return Type.fromId((dataTracker.get(TYPE) as Int))
    }

    override fun setVariant(type: Type) {
        dataTracker.set(TYPE, type.id)
    }
}