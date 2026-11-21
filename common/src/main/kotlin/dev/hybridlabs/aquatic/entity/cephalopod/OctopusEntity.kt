package dev.hybridlabs.aquatic.entity.cephalopod

import net.minecraft.server.level.ServerLevel

import net.minecraft.world.level.storage.ValueInput
import net.minecraft.world.level.storage.ValueOutput

import dev.hybridlabs.hapi.entity.base.VariantHolder
import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.entity.feature.OverlayTextureFeature
import dev.hybridlabs.hapi.tag.HAPIBiomeTags
import dev.hybridlabs.hapi.tag.HAPIEntityTags
import dev.hybridlabs.hapi.entity.ai.MobTargetConfiguration
import dev.hybridlabs.hapi.entity.base.aquatic.BaseOctopusEntity
import dev.hybridlabs.hapi.entity.base.aquatic.InkConfiguration
import net.minecraft.core.Holder
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
class OctopusEntity(type: EntityType<out OctopusEntity>, world: Level) : BaseOctopusEntity(type, world),
    VariantHolder<OctopusEntity.Companion.Type>, OverlayTextureFeature {
    override fun getTargetConfig() = TARGET_CONFIG

    override val inkConfig: InkConfiguration = InkConfiguration.DEFAULT

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: EntitySpawnReason,
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
                HAPIEntityTags.ALL_CRUSTACEANS
            ),
            listOf(
                HAPIEntityTags.ALL_SHARKS
            ),
        )

        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.TEMPT_RANGE, 10.0)
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

                fun byName(name: String): Type {
                    return CODEC.byName(name, OCTOPUS) as Type
                }

                fun fromId(id: Int): Type {
                    return BY_ID.apply(id) as Type
                }

                fun fromBiome(biome: Holder<Biome>, random: Random.Default): Type {
                    return when {
                        biome.`is`(HAPIBiomeTags.CORAL_REEF) -> {
                            Type.fromId(random.nextInt(0, 3))
                        }

                        biome.`is`(HAPIBiomeTags.LUKEWARM_OCEANS) -> {
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

    override fun hurtServer(level: ServerLevel, source: DamageSource, amount: Float): Boolean {
        val attacker = source.directEntity

        if (this.variant == Type.BLUE_RINGED && attacker is LivingEntity) {
            attacker.addEffect(MobEffectInstance(MobEffects.POISON, 100, 1))
        }

        return super.hurtServer(level, source, amount)
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

    override fun addAdditionalSaveData(output: ValueOutput) {
        output.putString("Type", this.variant.serializedName)
        output.putInt("texture_overlay", this.overlayTexture.id)
        super.addAdditionalSaveData(output)
    }

    override fun readAdditionalSaveData(input: ValueInput) {
        this.variant = Type.byName(input.getStringOr("Type", this.variant.serializedName))
        if (input.getInt("texture_overlay").isPresent) this.overlayTexture =
            OverlayTextures.byId(input.getIntOr("texture_overlay", this.overlayTexture.id))
        super.readAdditionalSaveData(input)
    }

    override var variant: Type
        get() = Type.fromId((entityData.get(TYPE) as Int))
        set(value) {
            entityData.set(TYPE, value.id)
        }
}
