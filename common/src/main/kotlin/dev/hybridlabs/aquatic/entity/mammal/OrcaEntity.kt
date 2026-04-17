package dev.hybridlabs.aquatic.entity.mammal

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.aquatic.entity.ai.MobTargetConfiguration
import dev.hybridlabs.aquatic.entity.ai.goal.WaterAnimalBreedGoal
import dev.hybridlabs.aquatic.entity.feature.OrcaEyeTextureFeature
import dev.hybridlabs.aquatic.entity.feature.OrcaSaddleTextureFeature
import dev.hybridlabs.aquatic.entity.fish.ClownfishEntity
import dev.hybridlabs.aquatic.tag.HABiomeTags
import dev.hybridlabs.aquatic.tag.HAEntityTags
import net.minecraft.core.Holder
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.util.ByIdMap
import net.minecraft.util.StringRepresentable
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.biome.Biome
import java.util.function.IntFunction
import kotlin.random.Random

@Suppress("DEPRECATION")
class OrcaEntity(type: EntityType<out OrcaEntity>, world: Level) : HADolphinEntity(type, world),
    OrcaEyeTextureFeature, OrcaSaddleTextureFeature, VariantHolder<OrcaEntity.Companion.Type> {

    override fun getTargetConfig() = MobTargetConfiguration.ofPredator(
        HAEntityTags.SMALL_CREATURES,
        HAEntityTags.MEDIUM_CREATURES,
        HAEntityTags.LARGE_CREATURES,
        HAEntityTags.SEAL,
        HAEntityTags.SMALL_SHARK,
        HAEntityTags.MEDIUM_SHARK
    )

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(2, WaterAnimalBreedGoal(this, 1.1))
    }

    override fun getBreedOffspring(p0: ServerLevel, p1: AgeableMob): OrcaEntity? {
        return HAEntityTypes.ORCA.get().create(p0)
    }

    //#region SFX
    override fun getAmbientSound(): SoundEvent {
        return SoundEvents.DOLPHIN_AMBIENT
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.DOLPHIN_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.DOLPHIN_DEATH
    }

    override fun getSwimSplashSound(): SoundEvent {
        return SoundEvents.DOLPHIN_SPLASH
    }

    override fun getSwimSound(): SoundEvent {
        return SoundEvents.DOLPHIN_SWIM
    }
    //#endregion

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?,
    ): SpawnGroupData? {
        this.airSupply = this.maxAirSupply
        this.yRot = 0.0f

        val biome = world.getBiome(this.blockPosition())
        val selectedType = Type.fromBiome(biome, Random)
        this.variant = selectedType

        val saddleID =
            world.random.nextIntBetweenInclusive(0, SaddleTextures.entries.size - 1)
        saddleTexture = SaddleTextures.byId(saddleID)

        val eyeSpotID =
            world.random.nextIntBetweenInclusive(0, EyeSpotTextures.entries.size - 1)
        eyeSpotTexture = EyeSpotTextures.byId(eyeSpotID)

        if (this.random.nextFloat() < 0.1f) {
            this.setAge(-6000)
        }

        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 50.0)
                .add(Attributes.MOVEMENT_SPEED, 1.0)
                .add(Attributes.ATTACK_DAMAGE, 8.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.2)
                .add(Attributes.FOLLOW_RANGE, 24.0)
        }

        val TYPE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(ClownfishEntity::class.java, EntityDataSerializers.INT)

        enum class Type(val id: Int, private val key: String) : StringRepresentable {
            BLACK(0, "black"),
            GRAY(1, "gray"),
            NAVY(2, "navy"),
            PURPLE(3, "purple"),
            TAN(4, "tan"),
            BROWN(5, "brown");

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
                    return CODEC.byName(name, BLACK) as Type
                }

                fun fromId(id: Int): Type {
                    return BY_ID.apply(id) as Type
                }

                fun fromBiome(biome: Holder<Biome>, random: Random.Default): Type {
                    return when {
                        biome.`is`(HABiomeTags.FROZEN_OCEANS) -> {
                            Type.fromId(random.nextInt(0, 2))
                        }

                        biome.`is`(HABiomeTags.COLD_OCEANS) -> {
                            Type.fromId(random.nextInt(0, 4))
                        }

                        biome.`is`(HABiomeTags.TEMPERATE_OCEANS) -> {
                            Type.fromId(random.nextInt(1, 6))
                        }

                        else -> {
                            Type.fromId(random.nextInt(0, 7))
                        }
                    }
                }
            }
        }

        //#region Eye Spots
        val EyeSpotTexture: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(OrcaEntity::class.java, EntityDataSerializers.INT)

        enum class EyeSpotTextures(val id: Int, val key: String) : StringRepresentable {
            DEFAULT_EYE(0, "default_eye"),
            UP_EYE(1, "up_eye"),
            DOWN_EYE(2, "down_eye"),
            SMALL_EYE(3, "small_eye");

            override fun getSerializedName(): String {
                return this.key
            }

            companion object {
                val CODEC: Codec<EyeSpotTextures> =
                    StringRepresentable.fromEnum { EyeSpotTextures.entries.toTypedArray() }
                val BY_ID: IntFunction<EyeSpotTextures> = ByIdMap.continuous(
                    { eyeTex: EyeSpotTextures -> eyeTex.id },
                    EyeSpotTextures.entries.toTypedArray(),
                    ByIdMap.OutOfBoundsStrategy.WRAP
                )

                fun byId(id: Int): EyeSpotTextures {
                    return BY_ID.apply(id)
                }
            }
        }
        //#endregion

        val SaddleTexture: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(OrcaEntity::class.java, EntityDataSerializers.INT)

        enum class SaddleTextures(val id: Int, val key: String) : StringRepresentable {
            NONE(0, ""),
            BUMP(1, "bump"),
            VERTICAL(2, "vertical"),
            STRIPE(3, "stripe");

            override fun getSerializedName(): String {
                return this.key
            }

            companion object {
                val CODEC: Codec<SaddleTextures> =
                    StringRepresentable.fromEnum { SaddleTextures.entries.toTypedArray() }
                val BY_ID: IntFunction<SaddleTextures> = ByIdMap.continuous(
                    { saddleTex: SaddleTextures -> saddleTex.id },
                    SaddleTextures.entries.toTypedArray(),
                    ByIdMap.OutOfBoundsStrategy.WRAP
                )

                fun byId(id: Int): SaddleTextures {
                    return BY_ID.apply(id)
                }
            }
        }
    }
    //#endregion

    override fun getVariant(): Type {
        return Type.fromId((entityData.get(TYPE) as Int))
    }

    override fun setVariant(type: Type) {
        entityData.set(TYPE, type.id)
    }

    private var eyeSpotTexture
        get() = EyeSpotTextures.byId(entityData.get(EyeSpotTexture))
        set(value) {
            entityData.set(EyeSpotTexture, value.id)
        }

    override fun getEyeSpotTextureName(): String {
        return EyeSpotTextures.byId(entityData.get(EyeSpotTexture)).serializedName
    }

    private var saddleTexture
        get() = SaddleTextures.byId(entityData.get(SaddleTexture))
        set(value) {
            entityData.set(SaddleTexture, value.id)
        }

    override fun getSaddleTextureName(): String {
        return SaddleTextures.byId(entityData.get(SaddleTexture)).serializedName
    }

    //#region Data
    override fun defineSynchedData() {
        entityData.define(TYPE, 0)
        entityData.define(EyeSpotTexture, 0)
        entityData.define(SaddleTexture, 0)
        super.defineSynchedData()
    }

    override fun addAdditionalSaveData(compound: CompoundTag) {
        compound.putString("Type", this.variant.serializedName)
        compound.putInt("eye_spot_texture", this.eyeSpotTexture.id)
        compound.putInt("saddle_texture", this.saddleTexture.id)
        super.addAdditionalSaveData(compound)
    }

    override fun readAdditionalSaveData(compound: CompoundTag) {
        this.variant = Type.byName(compound.getString("Type"))
        this.eyeSpotTexture = EyeSpotTextures.byId(compound.getInt("eye_spot_texture"))
        if (compound.contains("saddle_texture")) this.saddleTexture =
            SaddleTextures.byId(compound.getInt("saddle_texture"))
        super.readAdditionalSaveData(compound)
    }
    //#endregion
}