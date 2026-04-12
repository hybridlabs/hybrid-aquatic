package dev.hybridlabs.aquatic.entity.fish

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.aquatic.entity.ai.MobTargetConfiguration
import dev.hybridlabs.aquatic.entity.ai.goal.CarpBreedGoal
import dev.hybridlabs.aquatic.entity.base.HAWaterAnimal
import dev.hybridlabs.aquatic.entity.feature.CarpPatternTextureFeature
import dev.hybridlabs.aquatic.tag.HABiomeTags
import dev.hybridlabs.aquatic.tag.HAEntityTags
import dev.hybridlabs.aquatic.world.WorldHelper
import net.minecraft.core.BlockPos
import net.minecraft.core.Holder
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.ByIdMap
import net.minecraft.util.RandomSource
import net.minecraft.util.StringRepresentable
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.TemptGoal
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.biome.Biome
import java.util.function.IntFunction
import kotlin.random.Random

@Suppress("DEPRECATION")
class CarpEntity(type: EntityType<out CarpEntity>, world: Level) : HAFishEntity(type, world),
    CarpPatternTextureFeature, VariantHolder<CarpEntity.Companion.Type> {

    override fun getTargetConfig() = MobTargetConfiguration.ofPrey(
        HAEntityTags.MEDIUM_CREATURES,
        HAEntityTags.LARGE_CREATURES,
        HAEntityTags.ALL_SHARKS
    )

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, CarpBreedGoal(this, 1.1))
        goalSelector.addGoal(2, TemptGoal(this, 1.1, BREEDING_INGREDIENT, false))
    }

    override fun removeWhenFarAway(distanceSquared: Double): Boolean {

        return if (variant != Type.COMMON || variant != Type.PRUSSIAN) {
            false
        } else {
            super.removeWhenFarAway(distanceSquared)
        }
    }

    override fun getBreedOffspring(p0: ServerLevel, p1: AgeableMob): AgeableMob? {
        return HAEntityTypes.CARP.get().create(p0)
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    override fun getMinSize(): Int {
        return -5
    }

    override fun getMaxSize(): Int {
        return 0
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

        patternTexture = when (selectedType) {
            Type.PRUSSIAN,
            Type.COMMON,
            Type.TELESCOPE,
            Type.BUBBLE_EYE,
            Type.FANTAIL,
            Type.RYUKIN,
            Type.COMMON_GOLDFISH,
                 -> PatternTextures.NONE
            Type.KOI, Type.SMALL_KOI -> {
                val patternID = world.random.nextIntBetweenInclusive(
                    0, PatternTextures.entries.size - 1
                )
                PatternTextures.byId(patternID)
            }
        }

        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun spawnChildFromBreeding(level: ServerLevel, mate: HAWaterAnimal) {
        val baby = this.getBreedOffspring(level, mate) ?: return

        baby.isBaby = true
        baby.moveTo(this.x, this.y, this.z, 0.0f, 0.0f)

        this.finalizeSpawnChildFromBreeding(level, mate)

        if (baby is CarpEntity) {
            val parentType = this.variant
            baby.variant = getChildVariant(level.random, parentType)

            val patternID = level.random.nextIntBetweenInclusive(
                0, PatternTextures.entries.size - 1
            )
            baby.patternTexture = PatternTextures.byId(patternID)
        }

        level.addFreshEntityWithPassengers(baby)
    }

    private fun getChildVariant(random: RandomSource, parent: Type): Type {
        val roll = random.nextDouble()

        val goldfishTypes = listOf(
            Type.FANTAIL,
            Type.RYUKIN,
            Type.TELESCOPE,
            Type.BUBBLE_EYE,
            Type.COMMON_GOLDFISH
        )

        return when (parent) {
            Type.COMMON -> {
                if (roll < 0.75) Type.KOI else Type.SMALL_KOI
            }

            Type.PRUSSIAN -> {
                if (roll < 0.75) Type.SMALL_KOI else Type.KOI
            }

            Type.KOI -> {
                when {
                    roll < 0.60 -> Type.KOI
                    roll < 0.90 -> Type.SMALL_KOI
                    else -> goldfishTypes[random.nextInt(goldfishTypes.size)]
                }
            }

            Type.SMALL_KOI -> {
                when {
                    roll < 0.60 -> Type.SMALL_KOI
                    else -> goldfishTypes[random.nextInt(goldfishTypes.size)]
                }
            }

            Type.COMMON_GOLDFISH,
            Type.FANTAIL,
            Type.RYUKIN,
            Type.TELESCOPE,
            Type.BUBBLE_EYE -> {
                goldfishTypes[random.nextInt(goldfishTypes.size)]
            }
        }
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }

        fun canSpawn(
            type: EntityType<out CarpEntity>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val seaLevel = world.level.chunkSource.generator.seaLevel
            return  pos.y in (seaLevel - 16)..< seaLevel + 64 &&
                    world.isWaterAt(pos) &&
                    WorldHelper.canSeeSkyFromBelowWater(world, pos)
        }

        enum class Type(val id: Int, private val key: String) : StringRepresentable {
            COMMON(0, "common"),
            PRUSSIAN(1, "prussian"),
            KOI(2, "koi"),
            SMALL_KOI(3, "small_koi"),
            FANTAIL(4, "fantail"),
            RYUKIN(5, "ryukin"),
            TELESCOPE(6, "telescope"),
            BUBBLE_EYE(7, "bubble_eye_goldfish"),
            COMMON_GOLDFISH(8, "common_goldfish");

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
                    return CODEC.byName(name, COMMON) as Type
                }

                fun fromId(id: Int): Type {
                    return BY_ID.apply(id) as Type
                }

                fun fromBiome(biome: Holder<Biome>, random: Random.Default): Type {
                    return when {
                        biome.`is`(HABiomeTags.CHERRY) -> {
                            Type.fromId(random.nextInt(2, 4))
                        }

                        else -> {
                            Type.fromId(random.nextInt(0, 2))
                        }
                    }
                }
            }
        }

        val TYPE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(CarpEntity::class.java, EntityDataSerializers.INT)

        val PATTERN: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(CarpEntity::class.java, EntityDataSerializers.INT)

        enum class PatternTextures(val id: Int, val key: String) : StringRepresentable {
            NONE(0, ""),

            BLACK_CREEPER(1, "black_creeper"),
            BLACK_HEART(2, "black_heart"),
            BLACK_SADDLE(3, "black_saddle"),
            BLACK_SPOT(4, "black_spot"),

            RED_CREEPER(5, "red_creeper"),
            RED_HEART(6, "red_heart"),
            RED_SADDLE(7, "red_saddle"),
            RED_SPOT(8, "red_spot"),

            WHITE_CREEPER(9, "white_creeper"),
            WHITE_HEART(10, "white_heart"),
            WHITE_SADDLE(11, "white_saddle"),
            WHITE_SPOT(12, "white_spot"),

            ORANGE_CREEPER(13, "orange_creeper"),
            ORANGE_HEART(14, "orange_heart"),
            ORANGE_SADDLE(15, "orange_saddle"),
            ORANGE_SPOT(16, "orange_spot");

            override fun getSerializedName(): String {
                return this.key
            }

            companion object {
                val CODEC: Codec<PatternTextures> =
                    StringRepresentable.fromEnum { PatternTextures.entries.toTypedArray() }
                val BY_ID: IntFunction<PatternTextures> = ByIdMap.continuous(
                    { patternTex: PatternTextures -> patternTex.id },
                    PatternTextures.entries.toTypedArray(),
                    ByIdMap.OutOfBoundsStrategy.WRAP
                )

                fun byId(id: Int): PatternTextures {
                    return BY_ID.apply(id)
                }
            }
        }
    }

    private var patternTexture
        get() = PatternTextures.byId(entityData.get(PATTERN))
        set(value) {
            entityData.set(PATTERN, value.id)
        }

    override fun getPatternTextureName(): String {
        return PatternTextures.byId(entityData.get(PATTERN)).serializedName
    }

    override fun defineSynchedData() {
        entityData.define(TYPE, 0)
        entityData.define(PATTERN, 0)
        super.defineSynchedData()
    }

    override fun addAdditionalSaveData(compound: CompoundTag) {
        compound.putString("Type", this.variant.serializedName)
        compound.putInt("pattern_texture", this.patternTexture.id)
        super.addAdditionalSaveData(compound)
    }

    override fun readAdditionalSaveData(compound: CompoundTag) {
        this.variant = Type.byName(compound.getString("Type"))
        if (compound.contains("pattern_texture")) this.patternTexture =
            PatternTextures.byId(compound.getInt("pattern_texture"))
        super.readAdditionalSaveData(compound)
    }

    override fun getVariant(): Type {
        return Type.fromId((entityData.get(TYPE) as Int))
    }

    override fun setVariant(type: Type) {
        entityData.set(TYPE, type.id)
    }
}
