package dev.hybridlabs.aquatic.entity.critter

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.minecraft.entity.*
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.registry.tag.BiomeTags
import net.minecraft.util.StringIdentifiable
import net.minecraft.util.function.ValueLists
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import net.minecraft.world.biome.Biome
import java.util.function.IntFunction
import kotlin.random.Random

@Suppress("DEPRECATION")
class StarfishEntity(entityType: EntityType<out StarfishEntity>, world: World) :
    HybridAquaticCritterEntity(
        entityType, world),
    VariantHolder<StarfishEntity.Type> {

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 1.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 2.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0)
        }
        val TYPE: TrackedData<Int> = DataTracker.registerData(StarfishEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
    }

    override fun damage(source: DamageSource, amount: Float): Boolean {
        val attacker = source.attacker

        if (this.variant == Type.CROWN_OF_THORNS && attacker is LivingEntity) {
            attacker.addStatusEffect(StatusEffectInstance(StatusEffects.POISON, 100, 1))
        }

        return super.damage(source, amount)
    }

    override fun initialize(
        world: ServerWorldAccess,
        difficulty: LocalDifficulty,
        spawnReason: SpawnReason,
        entityData: EntityData?,
        entityNbt: NbtCompound?
    ): EntityData? {
        val biome = world.getBiome(this.blockPos)
        val selectedType = Type.fromBiome(biome, Random.Default)
        this.variant = selectedType
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt)
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
        BRITTLESTAR_BLACK(0, "brittlestar_black"),
        BRITTLESTAR_WHITE(1, "brittlestar_white"),
        BRITTLESTAR_YELLOW(2, "brittlestar_yellow"),
        CROWN_OF_THORNS(3, "crown_of_thorns"),
        GREEN(4, "green"),
        MEDIUM_GREEN(5, "medium_green"),
        MEDIUM_KNOBBED_GREEN(6, "medium_knobbed_green"),
        BLUE(7, "blue"),
        MEDIUM_BLUE(8, "medium_blue"),
        MEDIUM_KNOBBED_BLUE(9, "medium_knobbed_blue"),
        RED(10, "red"),
        MEDIUM_RED(11, "medium_red"),
        MEDIUM_KNOBBED_RED(12, "medium_knobbed_red"),
        YELLOW(13, "yellow"),
        MEDIUM_YELLOW(14, "medium_yellow"),
        MEDIUM_KNOBBED_YELLOW(15, "medium_knobbed_yellow"),
        PURPLE(16, "purple"),
        MEDIUM_PURPLE(17, "medium_purple"),
        MEDIUM_KNOBBED_PURPLE(18, "medium_knobbed_purple"),
        ORANGE(19, "orange"),
        MEDIUM_ORANGE(20, "medium_orange"),
        MEDIUM_KNOBBED_ORANGE(21, "medium_knobbed_orange");

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
                return CODEC.byId(name, RED) as Type
            }

            fun fromId(id: Int): Type {
                return BY_ID.apply(id) as Type
            }

            private val REEF_VARIANTS = listOf(
                CROWN_OF_THORNS,
                GREEN,
                MEDIUM_GREEN,
                MEDIUM_KNOBBED_GREEN,
                BLUE,
                MEDIUM_BLUE,
                MEDIUM_KNOBBED_BLUE,
                RED,
                MEDIUM_RED,
                MEDIUM_KNOBBED_RED,
                YELLOW,
                MEDIUM_YELLOW,
                MEDIUM_KNOBBED_YELLOW,
                PURPLE,
                MEDIUM_PURPLE,
                MEDIUM_KNOBBED_PURPLE,
                ORANGE,
                MEDIUM_ORANGE,
                MEDIUM_KNOBBED_ORANGE
            )

            fun fromBiome(biome: RegistryEntry<Biome>, random: Random): Type {
                return when {
                    biome.isIn(BiomeTags.IS_DEEP_OCEAN) -> {
                        Type.fromId(random.nextInt(0, 3))
                    }
                    biome.isIn(HybridAquaticBiomeTags.REEF) -> {
                        REEF_VARIANTS[random.nextInt(REEF_VARIANTS.size)]
                    }
                    else -> {
                        Type.fromId(random.nextInt(4, 22))
                    }
                }
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