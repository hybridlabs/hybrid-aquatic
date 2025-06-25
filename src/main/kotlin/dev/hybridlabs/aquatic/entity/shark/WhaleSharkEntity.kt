package dev.hybridlabs.aquatic.entity.shark

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
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.RawAnimation
import java.util.function.IntFunction
import kotlin.random.Random

@Suppress("DEPRECATION")
class WhaleSharkEntity(entityType: EntityType<out WhaleSharkEntity>, world: World) :
    HybridAquaticSharkEntity(entityType, world, listOf(HybridAquaticEntityTags.NONE), true, false),
    VariantHolder<WhaleSharkEntity.Type> {

    private var isFeeding = false

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(AnimationController(this, "Open/Closed", 0) { state ->
            val animation = when {
                isFeeding -> MOUTH_OPEN
                else -> MOUTH_CLOSED
            }
            state.setAndContinue(animation)
        })
        super.registerControllers(controllerRegistrar)
    }

    override fun initialize(
        world: ServerWorldAccess,
        difficulty: LocalDifficulty,
        spawnReason: SpawnReason,
        entityData: EntityData?,
        entityNbt: NbtCompound?
    ): EntityData? {
        setVariant(Type.entries.random(Random))
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun tick() {
        super.tick()

        if (hunger < MAX_HUNGER / 4) {
            isFeeding = true
        }

        if (isFeeding) {
            hunger += 10

            if (hunger >= MAX_HUNGER) {
                hunger = MAX_HUNGER
                isFeeding = false
            }
        }
    }

    override fun getLimitPerChunk(): Int {
        return 1
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 60.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16.0)
        }

        val MOUTH_OPEN: RawAnimation = RawAnimation.begin().thenPlay("misc.mouth_open")
        val MOUTH_CLOSED: RawAnimation = RawAnimation.begin().thenPlay("misc.mouth_closed")
        val TYPE: TrackedData<Int> = DataTracker.registerData(WhaleSharkEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
    }

    override fun getMaxSize(): Int {
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
        BLUE(0, "blue"),
        BROWN(1, "brown"),
        GRAY(2, "gray");

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
                return CODEC.byId(name, BLUE) as Type
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
