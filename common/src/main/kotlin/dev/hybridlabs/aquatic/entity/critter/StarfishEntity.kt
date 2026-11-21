package dev.hybridlabs.aquatic.entity.critter

import net.minecraft.server.level.ServerLevel

import net.minecraft.world.level.storage.ValueInput
import net.minecraft.world.level.storage.ValueOutput

import dev.hybridlabs.hapi.entity.base.VariantHolder
import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.entity.feature.OverlayTextureFeature
import dev.hybridlabs.hapi.tag.HAPIBiomeTags
import dev.hybridlabs.hapi.entity.base.aquatic.BaseCritterEntity
import net.minecraft.core.Holder
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.tags.BiomeTags
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
class StarfishEntity(entityType: EntityType<out StarfishEntity>, world: Level) :
    BaseCritterEntity(entityType, world),
    VariantHolder<StarfishEntity.Companion.Type>, OverlayTextureFeature {

    override fun getDefaultDimensions(pose: Pose): EntityDimensions {
        val scale = when (variant) {
            Type.CROWN_OF_THORNS -> 2.0f
            else -> 1.0f
        }
        return super.getDefaultDimensions(pose).scale(scale)
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.TEMPT_RANGE, 10.0)
                .add(Attributes.MAX_HEALTH, 1.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 2.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
        }

        val TYPE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(StarfishEntity::class.java, EntityDataSerializers.INT)
        val OverlayTexture: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(StarfishEntity::class.java, EntityDataSerializers.INT)
        val OverlayColor: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(StarfishEntity::class.java, EntityDataSerializers.INT)
        val StarfishColor: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(StarfishEntity::class.java, EntityDataSerializers.INT)

        enum class OverlayTextures(val id: Int, val key: String) : StringRepresentable {
            NONE(0, ""),
            STRIPES_SMALL(1, "stripes_small"),
            CIRCLE_SMALL(2, "circle_small"),
            STRIPES_CIRCLE_SMALL(3, "stripes_circle_small"),
            STRIPES(4, "stripes"),
            CIRCLE(5, "circle"),
            STRIPES_CIRCLE(6, "stripes_circle");

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
            BRITTLESTAR(0, "brittlestar"),
            CROWN_OF_THORNS(1, "crown_of_thorns"),
            SMALL(2, "small"),
            MEDIUM(3, "medium");

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
                    return CODEC.byName(name, SMALL) as Type
                }

                fun fromId(id: Int): Type {
                    return BY_ID.apply(id) as Type
                }

                private val REEF_VARIANTS = listOf(
                    CROWN_OF_THORNS,
                    SMALL,
                    MEDIUM,
                )

                fun fromBiome(biome: Holder<Biome>, random: Random.Default): Type {
                    return when {
                        biome.`is`(BiomeTags.IS_DEEP_OCEAN) -> {
                            BRITTLESTAR
                        }

                        biome.`is`(HAPIBiomeTags.ALL_TRENCHES) -> {
                            BRITTLESTAR
                        }

                        biome.`is`(HAPIBiomeTags.CORAL_REEF) -> {
                            REEF_VARIANTS[random.nextInt(REEF_VARIANTS.size)]
                        }

                        else -> {
                            Type.fromId(random.nextInt(2, 4))
                        }
                    }
                }
            }
        }
    }

    override fun hurtServer(level: ServerLevel, source: DamageSource, amount: Float): Boolean {
        val attacker = source.directEntity

        if (this.variant == Type.CROWN_OF_THORNS && attacker is LivingEntity) {
            attacker.addEffect(MobEffectInstance(MobEffects.POISON, 100, 1))
        }

        return super.hurtServer(level, source, amount)
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: EntitySpawnReason,
        entityData: SpawnGroupData?
    ): SpawnGroupData? {
        val biome = world.getBiome(this.blockPosition())
        val selectedType = Type.fromBiome(biome, Random.Default)
        this.variant = selectedType
        this.refreshDimensions()
        this.overlayColor = this.overlayColor
        this.starfishColor = this.starfishColor

        overlayTexture = when (selectedType) {
            Type.CROWN_OF_THORNS, Type.BRITTLESTAR -> OverlayTextures.NONE
            Type.SMALL -> OverlayTextures.byId(listOf(0, 1, 2, 3).random(Random))
            Type.MEDIUM -> OverlayTextures.byId(listOf(0, 4, 5, 6).random(Random))
        }
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData)
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }

    private var overlayTexture
        get() = OverlayTextures.byId(entityData.get(OverlayTexture))
        set(value) {
            entityData.set(OverlayTexture, value.id)
        }

    var overlayColor: Int
        get() {
            var color = entityData.get(OverlayColor)
            if (color == -1) {
                val r = random.nextInt(256)
                val g = random.nextInt(256)
                val b = random.nextInt(256)
                color = (255 shl 24) or (r shl 16) or (g shl 8) or b
                entityData.set(OverlayColor, color)
            }
            return color
        }
        set(value) {
            entityData.set(OverlayColor, value)
        }

    var starfishColor: Int
        get() {
            var color = entityData.get(StarfishColor)
            if (color == -1) {
                val r = random.nextInt(256)
                val g = random.nextInt(256)
                val b = random.nextInt(256)
                color = (255 shl 24) or (r shl 16) or (g shl 8) or b
                entityData.set(StarfishColor, color)
            }
            return color
        }
        set(value) {
            entityData.set(StarfishColor, value)
        }

    override fun getOverlayTextureName(): String {
        return OverlayTextures.byId(entityData.get(OverlayTexture)).serializedName
    }

    override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        builder.define(TYPE, 0)
        builder.define(OverlayTexture, 0)
        builder.define(StarfishColor, -1)
        builder.define(OverlayColor, -1)
        super.defineSynchedData(builder)
    }

    override fun addAdditionalSaveData(output: ValueOutput) {
        output.putString("Type", this.variant.serializedName)
        output.putInt("Overlay", this.overlayTexture.id)
        output.putInt("Starfish_Color", starfishColor)
        output.putInt("Overlay_Color", overlayColor)
        super.addAdditionalSaveData(output)
    }

    override fun readAdditionalSaveData(input: ValueInput) {
        this.variant = Type.byName(input.getStringOr("Type", this.variant.serializedName))

        if (input.getInt("Overlay").isPresent) {
            this.overlayTexture = OverlayTextures.byId(input.getIntOr("Overlay", this.overlayTexture.id))
        }

        if (input.getInt("Overlay_Color").isPresent) {
            this.overlayColor = input.getIntOr("Overlay_Color", overlayColor)
        }

        if (input.getInt("Starfish_Color").isPresent) {
            this.starfishColor = input.getIntOr("Starfish_Color", starfishColor)
        }

        super.readAdditionalSaveData(input)
    }

    override var variant: Type
        get() = Type.fromId((entityData.get(TYPE) as Int))
        set(value) {
            entityData.set(TYPE, value.id)
        }
}