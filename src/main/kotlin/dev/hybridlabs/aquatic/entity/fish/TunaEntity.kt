package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.ai.goal.FishJumpGoal
import dev.hybridlabs.aquatic.entity.ai.goal.StayNearSurfaceGoal
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
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
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.util.StringIdentifiable
import net.minecraft.util.function.ValueLists
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import net.minecraft.world.biome.Biome
import java.util.function.IntFunction
import kotlin.random.Random

class TunaEntity(entityType: EntityType<out TunaEntity>, world: World) :
    HybridAquaticSchoolingFishEntity(
        entityType, world,
        listOf(
            HybridAquaticEntityTags.SMALL_PREY,
            HybridAquaticEntityTags.CEPHALOPOD,
        ),
        listOf(
            HybridAquaticEntityTags.SHARK
        )
    ),
    VariantHolder<TunaEntity.Type> {

    override fun getLimitPerChunk(): Int {
        return 3
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

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(5, FishJumpGoal(this, 10))
        goalSelector.add(1, StayNearSurfaceGoal(this, 1.0, 1, 16))
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.7)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 8.0)
        }
        val TYPE: TrackedData<Int> = DataTracker.registerData(TunaEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
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
        YELLOWFIN(0, "yellowfin"),
        BLUEFIN(1, "bluefin");

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
                return CODEC.byId(name, YELLOWFIN) as Type
            }

            fun fromId(id: Int): Type {
                return BY_ID.apply(id) as Type
            }

            fun fromBiome(biome: RegistryEntry<Biome>, random: Random): Type {
                return when {
                    biome.isIn(HybridAquaticBiomeTags.TEMPERATE_OCEANS) -> {
                        BLUEFIN
                    }
                    biome.isIn(HybridAquaticBiomeTags.TROPICAL_OCEANS) -> {
                        YELLOWFIN
                    }
                    else -> {
                        Type.fromId(random.nextInt(0, 2))
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