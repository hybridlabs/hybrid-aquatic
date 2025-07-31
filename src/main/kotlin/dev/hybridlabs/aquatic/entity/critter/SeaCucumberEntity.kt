package dev.hybridlabs.aquatic.entity.critter

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
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
import net.minecraft.registry.tag.BiomeTags
import net.minecraft.util.StringIdentifiable
import net.minecraft.util.function.ValueLists
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import net.minecraft.world.biome.Biome
import java.util.function.IntFunction

@Suppress("DEPRECATION")
class SeaCucumberEntity(entityType: EntityType<out SeaCucumberEntity>, world: World) :
    HybridAquaticCritterEntity(entityType, world),
    VariantHolder<SeaCucumberEntity.Companion.Type> {

    override fun remove(reason: RemovalReason) {
        if (!world.isClient && this.isDead) {
            if (world.random.nextInt(4) == 0) {
                val text = this.customName
                val isAiDisabled = this.isAiDisabled
                val spawnCount = 1 + world.random.nextInt(2)

                for (l in 0 until spawnCount) {
                    val offsetX = (world.random.nextFloat() - 0.5f) * 2.0f
                    val offsetZ = (world.random.nextFloat() - 0.5f) * 2.0f
                    val pearlfishEntity = HybridAquaticEntityTypes.PEARLFISH.create(world)

                    pearlfishEntity?.let {
                        it.customName = text
                        it.isAiDisabled = isAiDisabled
                        it.isInvulnerable = this.isInvulnerable
                        it.refreshPositionAndAngles(
                            this.x + offsetX,
                            this.y + 0.5,
                            this.z + offsetZ,
                            world.random.nextFloat() * 360.0f,
                            0.0f
                        )

                        world.spawnEntity(it)
                    }
                }
            }
        }

        super.remove(reason)
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 2.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 2.0)
        }

        val TYPE: TrackedData<Int> =
            DataTracker.registerData(SeaCucumberEntity::class.java, TrackedDataHandlerRegistry.INTEGER)

        enum class Type(val id: Int, private val key: String) : StringIdentifiable {
            COMMON(0, "common"),
            SEA_PIG(1, "sea_pig");

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
                    return CODEC.byId(name, COMMON) as Type
                }

                fun fromId(id: Int): Type {
                    return BY_ID.apply(id) as Type
                }

                fun fromBiome(biome: RegistryEntry<Biome?>): Type {
                    return if (biome.isIn(BiomeTags.IS_DEEP_OCEAN)) {
                        SEA_PIG
                    } else {
                        COMMON
                    }
                }
            }
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
        val selectedType = Type.fromBiome(biome)
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