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
    VariantHolder<StarfishEntity.Companion.Type> {

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

        enum class Type(val id: Int, private val key: String) : StringIdentifiable {
            BRITTLESTAR(0, "brittlestar"),
            CROWN_OF_THORNS(1, "crown_of_thorns"),
            SMALL(2, "small"),
            MEDIUM(3, "medium");

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
                    return CODEC.byId(name, SMALL) as Type
                }

                fun fromId(id: Int): Type {
                    return BY_ID.apply(id) as Type
                }

                private val REEF_VARIANTS = listOf(
                    CROWN_OF_THORNS,
                    SMALL,
                    MEDIUM,
                )

                fun fromBiome(biome: RegistryEntry<Biome>, random: Random): Type {
                    return when {
                        biome.isIn(BiomeTags.IS_DEEP_OCEAN) -> {
                            BRITTLESTAR
                        }
                        biome.isIn(HybridAquaticBiomeTags.REEF) -> {
                            REEF_VARIANTS[random.nextInt(REEF_VARIANTS.size)]
                        }
                        else -> {
                            Type.fromId(random.nextInt(2, 4))
                        }
                    }
                }
            }
        }
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

    override fun getVariant(): Type {
        return Type.fromId((dataTracker.get(TYPE) as Int))
    }

    override fun setVariant(type: Type) {
        dataTracker.set(TYPE, type.id)
    }
}