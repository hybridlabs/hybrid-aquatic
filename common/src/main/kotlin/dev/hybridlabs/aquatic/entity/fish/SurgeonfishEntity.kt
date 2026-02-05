package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.entity.ai.MobTargetConfiguration
import dev.hybridlabs.aquatic.entity.ai.goal.boids.BoidGoal
import dev.hybridlabs.aquatic.entity.ai.goal.boids.StayInWaterGoal
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.util.ByIdMap
import net.minecraft.util.StringRepresentable
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.SpawnGroupData
import net.minecraft.world.entity.VariantHolder
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import java.util.function.IntFunction
import kotlin.random.Random

@Suppress("DEPRECATION")
class SurgeonfishEntity(type: EntityType<out SurgeonfishEntity>, world: Level) : HybridAquaticSchoolingFishEntity(type, world), VariantHolder<SurgeonfishEntity.Companion.Type> {

    override fun getTargetConfig() = MobTargetConfiguration.ofPrey(
        HybridAquaticEntityTags.MEDIUM_PREY,
        HybridAquaticEntityTags.LARGE_PREY,
        HybridAquaticEntityTags.ALL_SHARKS
    )

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(5, BoidGoal(this, 0.25f, 0.5f, 8 / 20f, 1 / 20f))
        goalSelector.addGoal(3, StayInWaterGoal(this))
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 6
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?
    ): SpawnGroupData? {
        val spawnData = super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)

        val variant = Type.entries.random(Random).id
        this.variant = Type.fromId(variant)

        if (spawnReason == MobSpawnType.CHUNK_GENERATION || spawnReason == MobSpawnType.NATURAL) {
            val fishCount = (this.maxSpawnClusterSize * this.random.nextFloat()).toInt()
            if (fishCount > 0 && !level().isClientSide()) {
                for (i in 0 until  fishCount) {
                    val distance = 1.5f
                    val entity = SurgeonfishEntity(HybridAquaticEntityTypes.SURGEONFISH.get(), this.level())
                    entity.variant = this.variant
                    entity.moveTo(
                        this.x + this.random.nextFloat() * distance,
                        this.y + this.random.nextFloat() * distance,
                        this.z + this.random.nextFloat() * distance
                    )
                    entity.joinGroupOf(this)
                    level().addFreshEntity(entity)
                }
            }
        }
        return spawnData
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }

        val TYPE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(SurgeonfishEntity::class.java, EntityDataSerializers.INT)

        enum class Type(val id: Int, private val key: String) : StringRepresentable {
            BLUE_TANG(0, "blue_tang"),
            POWDER_BLUE_TANG(1, "powder_blue_tang"),
            YELLOW_TANG(2, "yellow_tang"),
            LINED(3, "lined"),
            ORANGESHOULDER(4, "orangeshoulder"),
            SOHAL(5, "sohal"),
            UNICORNFISH(6, "unicornfish");

            override fun getSerializedName(): String {
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
                    return CODEC.byName(name, BLUE_TANG) as Type
                }

                fun fromId(id: Int): Type {
                    return BY_ID.apply(id) as Type
                }
            }
        }
    }

    override fun defineSynchedData() {
        entityData.define(TYPE, 0)
        super.defineSynchedData()
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        nbt.putString("Type", this.variant.serializedName)
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
