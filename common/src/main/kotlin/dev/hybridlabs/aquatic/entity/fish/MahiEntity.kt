package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.hapi.entity.ai.MobTargetConfiguration
import dev.hybridlabs.hapi.entity.ai.goal.aquatic.boids.BoidGoal
import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.hapi.tag.HAPIEntityTags
import dev.hybridlabs.aquatic.tag.HAItemTags
import dev.hybridlabs.hapi.entity.ai.goal.aquatic.WaterAnimalJumpGoal
import dev.hybridlabs.hapi.entity.base.aquatic.BaseSchoolingFishEntity
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
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import java.util.function.IntFunction
import kotlin.random.Random

@Suppress("DEPRECATION")
class MahiEntity(type: EntityType<out MahiEntity>, world: Level) :
    BaseSchoolingFishEntity(type, world),
    VariantHolder<MahiEntity.Companion.Type> {

    override fun getTargetConfig() = TARGET_CONFIG

    override fun getMaxSpawnClusterSize(): Int {
        return 4
    }

    override fun isFood(stack: ItemStack): Boolean {
        return stack.`is`(HAItems.RAW_TENTACLE.get()) ||
                stack.`is`(HAItemTags.SMALL_FISH)
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
                    val entity = MahiEntity(HAEntityTypes.MAHI.get(), this.level())
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

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(5, BoidGoal(this, 0.25f, 0.5f, 8 / 20f, 1 / 20f))
        goalSelector.addGoal(5, WaterAnimalJumpGoal(this, 10, 3.0))
    }

    companion object {
        private val TARGET_CONFIG = MobTargetConfiguration.create(
            listOf(
                HAPIEntityTags.SMALL_CREATURES,
                HAPIEntityTags.ALL_CEPHALOPODS
            ),
            listOf(
                HAPIEntityTags.ALL_SHARKS
            ),
        )

        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.65)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 8.0)
        }

        val TYPE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(MahiEntity::class.java, EntityDataSerializers.INT)

        enum class Type(val id: Int, private val key: String) : StringRepresentable {
            MAHI(0, "mahi"),
            POMPANO(1, "pompano");

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
                    return CODEC.byName(name, MAHI) as Type
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

    override fun addAdditionalSaveData(compound: CompoundTag) {
        compound.putString("Type", this.variant.serializedName)
        super.addAdditionalSaveData(compound)
    }

    override fun readAdditionalSaveData(compound: CompoundTag) {
        this.variant = Type.byName(compound.getString("Type"))
        super.readAdditionalSaveData(compound)
    }

    override fun getVariant(): Type {
        return Type.fromId((entityData.get(TYPE) as Int))
    }

    override fun setVariant(type: Type) {
        entityData.set(TYPE, type.id)
    }
}
