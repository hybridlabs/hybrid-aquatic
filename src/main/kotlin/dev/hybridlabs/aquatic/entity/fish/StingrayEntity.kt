package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.loot.HybridAquaticLootTables
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.VariantHolder
import net.minecraft.entity.ai.goal.RevengeGoal
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.util.Identifier
import net.minecraft.util.StringIdentifiable
import net.minecraft.util.function.ValueLists
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import net.minecraft.world.biome.Biome
import java.util.function.IntFunction
import kotlin.random.Random

@Suppress("DEPRECATION")
class StingrayEntity(entityType: EntityType<out StingrayEntity>, world: World) :
    HybridAquaticFishEntity(
        entityType, world,
        listOf(HybridAquaticEntityTags.CRUSTACEAN),
        listOf(HybridAquaticEntityTags.SHARK)
    ),
    VariantHolder<StingrayEntity.Companion.Type> {

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(1, RevengeGoal(this))
    }

    override fun getLootTableId(): Identifier {
        return when (variant) {
            Type.BLUE_SPOTTED -> HybridAquaticLootTables.BLUE_SPOTTED_STINGRAY
            Type.SPOTTED_EAGLE -> HybridAquaticLootTables.SPOTTED_EAGLE_RAY
        }
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

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 8.0)
        }

        val TYPE: TrackedData<Int> =
            DataTracker.registerData(StingrayEntity::class.java, TrackedDataHandlerRegistry.INTEGER)

        enum class Type(val id: Int, private val key: String) : StringIdentifiable {
            SPOTTED_EAGLE(0, "spotted_eagle"),
            BLUE_SPOTTED(1, "blue_spotted");

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
                    return CODEC.byId(name, BLUE_SPOTTED) as Type
                }

                fun fromId(id: Int): Type {
                    return BY_ID.apply(id) as Type
                }

                fun fromBiome(biome: RegistryEntry<Biome>, random: Random): Type {
                    return when {
                        biome.isIn(HybridAquaticBiomeTags.REEF) -> {
                            Type.fromId(random.nextInt(0, 3))
                        }

                        else -> {
                            Type.fromId(random.nextInt(0, 3))
                        }
                    }
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