package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.hapi.entity.ai.MobTargetConfiguration
import dev.hybridlabs.hapi.tag.HAPIBiomeTags
import dev.hybridlabs.hapi.entity.ai.goal.aquatic.WaterAnimalJumpGoal
import dev.hybridlabs.hapi.entity.base.aquatic.BaseFishEntity
import dev.hybridlabs.hapi.tag.HAPIEntityTags
import net.minecraft.core.BlockPos
import net.minecraft.core.Holder
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.util.ByIdMap
import net.minecraft.util.RandomSource
import net.minecraft.util.StringRepresentable
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.SpawnGroupData
import net.minecraft.world.entity.VariantHolder
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.biome.Biome
import java.util.function.IntFunction
import kotlin.random.Random

@Suppress("DEPRECATION", "UNUSED_PARAMETER")
class OceanSunfishEntity(type: EntityType<out OceanSunfishEntity>, world: Level) :
    BaseFishEntity(type, world),
    VariantHolder<OceanSunfishEntity.Companion.Type> {

    override fun getTargetConfig() = TARGET_CONFIG

    override fun getMaxSpawnClusterSize(): Int {
        return 1
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?
    ): SpawnGroupData? {
        val biome = world.getBiome(this.blockPosition())
        val selectedType = Type.fromBiome(biome, Random.Default)
        this.variant = selectedType
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(5, WaterAnimalJumpGoal(this, 10, 2.0))
    }

    override fun isFood(stack: ItemStack): Boolean {
        return stack.`is`(Items.SLIME_BALL)
    }

    companion object {
        private val TARGET_CONFIG = MobTargetConfiguration.create(
            listOf(
                HAPIEntityTags.ALL_JELLYFISH
            ),
            listOf(
                HAPIEntityTags.ALL_SHARKS
            ),
        )

        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 16.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 16.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
        }

        fun canSpawn(
            type: EntityType<out OceanSunfishEntity>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val seaLevel = world.level.chunkSource.generator.seaLevel
            val spawnY = (seaLevel - 24) ..< (seaLevel - 8)

            return pos.y in spawnY &&
                    world.isWaterAt(pos) &&
                    !world.level.isRaining &&
                    !world.level.isThundering &&
                    world.level.isDay
        }

        val TYPE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(OceanSunfishEntity::class.java, EntityDataSerializers.INT)

        enum class Type(val id: Int, private val key: String) : StringRepresentable {
            OCEAN(0, "ocean"),
            HOODWINKER(1, "hoodwinker"),
            SHARPTAIL(2, "sharptail"),
            GIANT(3, "giant");

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
                    return CODEC.byName(name, OCEAN) as Type
                }

                fun fromId(id: Int): Type {
                    return BY_ID.apply(id) as Type
                }

                fun fromBiome(biome: Holder<Biome>, random: Random.Default): Type {
                    return when {
                        biome.`is`(HAPIBiomeTags.SHALLOW_LUKEWARM_OCEANS) -> {
                            HOODWINKER
                        }

                        biome.`is`(HAPIBiomeTags.DEEP_LUKEWARM_OCEANS) -> {
                            SHARPTAIL
                        }

                        biome.`is`(HAPIBiomeTags.SHALLOW_TEMPERATE_OCEANS) -> {
                            OCEAN
                        }

                        biome.`is`(HAPIBiomeTags.DEEP_TEMPERATE_OCEANS) -> {
                            GIANT
                        }

                        else -> {
                            Type.fromId(random.nextInt(0, 4))
                        }
                    }
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
