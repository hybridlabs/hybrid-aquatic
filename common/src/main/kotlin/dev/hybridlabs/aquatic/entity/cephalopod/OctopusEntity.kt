package dev.hybridlabs.aquatic.entity.cephalopod

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.entity.ai.MobTargetConfiguration
import dev.hybridlabs.aquatic.entity.base.HAOctopusEntity
import dev.hybridlabs.aquatic.entity.feature.OverlayTextureFeature
import dev.hybridlabs.aquatic.tag.HABiomeTags
import dev.hybridlabs.aquatic.tag.HAEntityTags
import net.minecraft.core.Holder
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.util.ByIdMap
import net.minecraft.util.StringRepresentable
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.biome.Biome
import java.util.function.IntFunction
import kotlin.random.Random

@Suppress("DEPRECATION")
class OctopusEntity(type: EntityType<out OctopusEntity>, world: Level) : HAOctopusEntity(type, world),
    VariantHolder<OctopusEntity.Companion.Type>, OverlayTextureFeature {
    override fun getTargetConfig() = TARGET_CONFIG

    override val inkConfig: InkConfiguration = InkConfiguration.DEFAULT

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
    ): SpawnGroupData? {
        val biome = world.getBiome(this.blockPosition())
        val selectedType = Type.fromBiome(biome, Random.Default)
        this.variant = selectedType

        overlayTexture = when (selectedType) {
            Type.BLUE_RINGED, Type.COCONUT -> OverlayTextures.NONE
            Type.OCTOPUS -> OverlayTextures.TINT
        }

        return super.finalizeSpawn(world, difficulty, spawnReason, entityData)
    }

    companion object {
        private val TARGET_CONFIG = MobTargetConfiguration.create(
            listOf(
                HAEntityTags.ALL_CRUSTACEANS
            ),
            listOf(
                HAEntityTags.ALL_SHARKS
            ),
        )

        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 12.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 4.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 16.0)
        }

        val TYPE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(OctopusEntity::class.java, EntityDataSerializers.INT)
        val OverlayTexture: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(OctopusEntity::class.java, EntityDataSerializers.INT)

        enum class OverlayTextures(val id: Int, val key: String) : StringRepresentable {
            NONE(0, "none"),
            TINT(1, "tint");

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

        enum class Type(val id: Int, private val key: String) : StringRepresentable {
            OCTOPUS(0, "octopus"),
            COCONUT(1, "coconut"),
            BLUE_RINGED(2, "blue_ringed");

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
                    return CODEC.byName(name, OCTOPUS) as Type
                }

                fun fromId(id: Int): Type {
                    return BY_ID.apply(id) as Type
                }

                fun fromBiome(biome: Holder<Biome>, random: Random.Default): Type {
                    return when {
                        biome.`is`(HABiomeTags.CORAL_REEF) -> {
                            Type.fromId(random.nextInt(0, 3))
                        }

                        biome.`is`(HABiomeTags.LUKEWARM_OCEANS) -> {
                            Type.fromId(random.nextInt(0, 2))
                        }

                        else -> {
                            OCTOPUS
                        }
                    }
                }
            }
        }
    }

    override fun hurt(source: DamageSource, amount: Float): Boolean {
        val attacker = source.directEntity

        if (this.variant == Type.BLUE_RINGED && attacker is LivingEntity) {
            attacker.addEffect(MobEffectInstance(MobEffects.POISON, 100, 1))
        }

        return super.hurt(source, amount)
    }

    private var overlayTexture
        get() = OverlayTextures.byId(entityData.get(OverlayTexture))
        set(value) {
            entityData.set(OverlayTexture, value.id)
        }

    override fun getOverlayTextureName(): String {
        return OverlayTextures.byId(entityData.get(OverlayTexture)).serializedName
    }

    override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        builder.define(TYPE, 0)
        builder.define(OverlayTexture, 0)
        super.defineSynchedData(builder)
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
