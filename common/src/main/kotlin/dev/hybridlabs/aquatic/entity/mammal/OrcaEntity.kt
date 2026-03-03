package dev.hybridlabs.aquatic.entity.mammal

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.entity.ai.goal.WaterAnimalBreedGoal
import dev.hybridlabs.aquatic.entity.feature.OverlayTextureFeature
import dev.hybridlabs.aquatic.entity.fish.ClownfishEntity
import dev.hybridlabs.aquatic.entity.fish.MantaRayEntity
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
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
class OrcaEntity(type: EntityType<out OrcaEntity>, world: Level) : HybridAquaticDolphinEntity(type, world),
    OverlayTextureFeature, VariantHolder<OrcaEntity.Companion.Type> {

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(2, WaterAnimalBreedGoal(this, 1.1))
    }

    override fun getBreedOffspring(p0: ServerLevel, p1: AgeableMob): OrcaEntity? {
        return HybridAquaticEntityTypes.ORCA.get().create(p0)
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
        this.size = this.random.nextIntBetweenInclusive(getMinSize(), getMaxSize())

        val biome = world.getBiome(this.blockPosition())
        val selectedType = Type.fromBiome(biome, Random)
        this.variant = selectedType

        val overlayID =
            world.random.nextIntBetweenInclusive(0, MantaRayEntity.Companion.OverlayTextures.entries.size - 1)
        overlayTexture = OverlayTextures.byId(overlayID)

        if (this.random.nextFloat() < 0.25f) {
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
            TAN(2, "tan");

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
                        biome.`is`(HybridAquaticBiomeTags.ARCTIC_OCEANS) -> {
                            Type.fromId(random.nextInt(0, 2))
                        }

                        biome.`is`(HybridAquaticBiomeTags.COLD_OCEANS) -> {
                            Type.fromId(random.nextInt(0, 3))
                        }

                        biome.`is`(HybridAquaticBiomeTags.TEMPERATE_OCEANS) -> {
                            Type.fromId(random.nextInt(1, 3))
                        }

                        else -> {
                            Type.fromId(random.nextInt(0, 3))
                        }
                    }
                }
            }
        }

        val OverlayTexture: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(MantaRayEntity::class.java, EntityDataSerializers.INT)

        enum class OverlayTextures(val id: Int, val key: String) : StringRepresentable {
            NONE(0, ""),
            SMOOTH(1, "smooth"),
            HOOK(2, "hook"),
            BUMP(3, "bump"),
            HORIZONTAL(4, "horizontal"),
            VERTICAL(5, "vertical");

            override fun getSerializedName(): String {
                return this.key
            }

            companion object {
                val CODEC: Codec<OverlayTextures> =
                    StringRepresentable.fromEnum { OverlayTextures.entries.toTypedArray() }
                val BY_ID: IntFunction<OverlayTextures> = ByIdMap.continuous(
                    { overlayTex: OverlayTextures -> overlayTex.id },
                    OverlayTextures.entries.toTypedArray(),
                    ByIdMap.OutOfBoundsStrategy.WRAP
                )

                fun byId(id: Int): OverlayTextures {
                    return BY_ID.apply(id)
                }
            }
        }
    }

    private var overlayTexture
        get() = OverlayTextures.byId(entityData.get(OverlayTexture))
        set(value) {
            entityData.set(OverlayTexture, value.id)
        }

    override fun getOverlayTextureName(): String {
        return OverlayTextures.byId(entityData.get(OverlayTexture)).serializedName
    }

    override fun defineSynchedData() {
        entityData.define(TYPE, 0)
        entityData.define(OverlayTexture, 0)
        super.defineSynchedData()
    }

    override fun addAdditionalSaveData(compound: CompoundTag) {
        compound.putString("Type", this.variant.serializedName)
        compound.putInt("texture_overlay", this.overlayTexture.id)
        super.addAdditionalSaveData(compound)
    }

    override fun readAdditionalSaveData(compound: CompoundTag) {
        this.variant = Type.byName(compound.getString("Type"))
        if (compound.contains("texture_overlay")) this.overlayTexture =
            OverlayTextures.byId(compound.getInt("texture_overlay"))
        super.readAdditionalSaveData(compound)
    }

    override fun getVariant(): Type {
        return Type.fromId((entityData.get(TYPE) as Int))
    }

    override fun setVariant(type: Type) {
        entityData.set(TYPE, type.id)
    }
}
