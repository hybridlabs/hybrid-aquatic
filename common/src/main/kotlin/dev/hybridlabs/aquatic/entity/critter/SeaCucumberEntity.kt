package dev.hybridlabs.aquatic.entity.critter

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityType
import net.minecraft.entity.MobSpawnType
import net.minecraft.entity.VariantHolder
import net.minecraft.entity.attribute.AttributeSupplier
import net.minecraft.entity.attribute.Attributes
import net.minecraft.entity.data.SynchedEntityData
import net.minecraft.entity.data.EntityDataAccessor
import net.minecraft.entity.data.EntityDataSerializers
import net.minecraft.nbt.CompoundTag
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.registry.tag.BiomeTags
import net.minecraft.util.StringRepresentable
import net.minecraft.util.function.ByIdMap
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.ServerLevelAccess
import net.minecraft.world.World
import net.minecraft.world.biome.Biome
import java.util.function.IntFunction

@Suppress("DEPRECATION")
class SeaCucumberEntity(entityType: EntityType<out SeaCucumberEntity>, world: Level) :
    HybridAquaticCritterEntity(entityType, world),
    VariantHolder<SeaCucumberEntity.Companion.Type> {

    override fun remove(reason: RemovalReason) {
        if (!world.isClientSide && this.isDead) {
            if (world.random.nextInt(4) == 0) {
                val text = this.customName
                val.isNoAi = this.isNoAi
                val spawnCount = 1 + world.random.nextInt(2)

                for (l in 0 until spawnCount) {
                    val offsetX = (world.random.nextFloat() - 0.5f) * 2.0f
                    val offsetZ = (world.random.nextFloat() - 0.5f) * 2.0f
                    val pearlfishEntity = HybridAquaticEntityTypes.PEARLFISH.create(world)

                    pearlfishEntity?.let {
                        it.customName = text
                        it.isNoAi =.isNoAi
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
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 2.0)
        }

        val TYPE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(SeaCucumberEntity::class.java, EntityDataSerializers.INTEGER)

        enum class Type(val id: Int, private val key: String) : StringRepresentable {
            COMMON(0, "common"),
            SEA_PIG(1, "sea_pig");

            override fun asString(): String {
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
                    return CODEC.byName(name, COMMON) as Type
                }

                fun fromId(id: Int): Type {
                    return BY_ID.apply(id) as Type
                }

                fun fromBiome(biome: RegistryEntry<Biome?>): Type {
                    return if (biome.`is`(BiomeTags.IS_DEEP_OCEAN)) {
                        SEA_PIG
                    } else {
                        COMMON
                    }
                }
            }
        }
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?
    ): SpawnGroupData? {
        val biome = world.getBiome(this.blockPos)
        val selectedType = Type.fromBiome(biome)
        this.variant = selectedType
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }

    override fun initSynchedEntityData() {
        entityData.define(TYPE, 0)
        super.initSynchedEntityData()
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        nbt.putString("Type", this.variant.asString())
        super.addAdditionalSaveData(nbt)
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        this.variant = Type.byName(nbt.getString("Type"))
        super.readAdditionalSaveData(nbt)
    }

    override fun getVariant(): Type {
        return Type.fromId((entityData.get(TYPE) as Int))
    }

    override fun setVariant(type: Type) {
        entityData.set(TYPE, type.id)
    }
}