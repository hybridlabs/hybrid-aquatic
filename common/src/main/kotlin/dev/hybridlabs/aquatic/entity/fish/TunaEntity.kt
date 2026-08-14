package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.hapi.tag.HAPIBiomeTags
import dev.hybridlabs.hapi.tag.HAPIEntityTags
import dev.hybridlabs.aquatic.tag.HAItemTags
import dev.hybridlabs.hapi.entity.ai.MobTargetConfiguration
import dev.hybridlabs.hapi.entity.ai.goal.WaterAnimalEatItemGoal
import dev.hybridlabs.hapi.entity.ai.goal.WaterAnimalJumpGoal
import dev.hybridlabs.hapi.entity.ai.goal.boids.BoidGoal
import dev.hybridlabs.hapi.entity.base.aquatic.BaseSchoolingFishEntity
import net.minecraft.core.Holder
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
import net.minecraft.world.level.biome.Biome
import java.util.function.IntFunction
import kotlin.random.Random

@Suppress("DEPRECATION")
class TunaEntity(type: EntityType<out TunaEntity>, world: Level) :
    BaseSchoolingFishEntity(type, world),
    VariantHolder<TunaEntity.Companion.Type> {

    override fun getTargetConfig() = TARGET_CONFIG

    override fun getMaxSpawnClusterSize(): Int {
        return 4
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?
    ): SpawnGroupData? {
        val biome = world.getBiome(this.blockPosition())
        val selectedType = Type.fromBiome(biome, Random)
        this.variant = selectedType
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData)
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(2, WaterAnimalEatItemGoal(this))
        goalSelector.addGoal(5, BoidGoal(this, 0.25f, 0.5f, 8 / 20f, 1 / 20f))
        goalSelector.addGoal(5, WaterAnimalJumpGoal(this, 10, 5.0))
    }

    override fun isFood(stack: ItemStack): Boolean {
        return stack.`is`(HAItems.RAW_TENTACLE.get()) ||
                stack.`is`(HAItemTags.SMALL_FISH)
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
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.7)
                .add(Attributes.ATTACK_DAMAGE, 5.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 8.0)
        }

        val TYPE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(TunaEntity::class.java, EntityDataSerializers.INT)

        enum class Type(val id: Int, private val key: String) : StringRepresentable {
            YELLOWFIN(0, "yellowfin"),
            BLUEFIN(1, "bluefin");

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
                    return CODEC.byName(name, YELLOWFIN) as Type
                }

                fun fromId(id: Int): Type {
                    return BY_ID.apply(id) as Type
                }

                fun fromBiome(biome: Holder<Biome>, random: Random.Default): Type {
                    return when {
                        biome.`is`(HAPIBiomeTags.TEMPERATE_OCEANS) -> {
                            BLUEFIN
                        }

                        biome.`is`(HAPIBiomeTags.LUKEWARM_OCEANS) -> {
                            YELLOWFIN
                        }

                        else -> {
                            Type.fromId(random.nextInt(0, 2))
                        }
                    }
                }
            }
        }
    }

    override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        builder.define(TYPE, 0)
        super.defineSynchedData(builder)
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
