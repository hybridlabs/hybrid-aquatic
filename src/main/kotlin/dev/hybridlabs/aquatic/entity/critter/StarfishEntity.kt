package dev.hybridlabs.aquatic.entity.critter

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.entity.feature.OverlayTextureFeature
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.minecraft.entity.*
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.registry.tag.BiomeTags
import net.minecraft.util.StringIdentifiable
import net.minecraft.util.function.ValueLists
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import net.minecraft.world.biome.Biome
import java.util.function.IntFunction
import kotlin.random.Random

@Suppress("DEPRECATION")
class StarfishEntity(entityType: EntityType<out StarfishEntity>, world: World) :
    HybridAquaticCritterEntity(
        entityType, world
    ),
    VariantHolder<StarfishEntity.Companion.Type>, OverlayTextureFeature {

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 1.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 2.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0)
        }

        val TYPE: TrackedData<Int> =
            DataTracker.registerData(StarfishEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val OverlayTexture: TrackedData<Int> =
            DataTracker.registerData(StarfishEntity::class.java, TrackedDataHandlerRegistry.INTEGER)

        enum class OverlayTextures(val id: Int, val key: String) : StringIdentifiable {
            NONE(0, ""),
            STRIPES_SMALL(1, "stripes_small"),
            STRIPES_CIRCLE_SMALL(2, "stripes_circle_small"),
            STRIPES (3, "stripes"),
            STRIPES_CIRCLE(4, "stripes_circle");

            override fun asString(): String {
                return this.key
            }

            companion object {
                val CODEC: Codec<OverlayTextures> =
                    StringIdentifiable.createCodec { OverlayTextures.entries.toTypedArray() }
                val BY_ID: IntFunction<OverlayTextures> = ValueLists.createIdToValueFunction(
                    { overlayTex: OverlayTextures -> overlayTex.id },
                    OverlayTextures.entries.toTypedArray(),
                    ValueLists.OutOfBoundsHandling.WRAP
                )

                fun byId(id: Int): OverlayTextures {
                    return BY_ID.apply(id)
                }
            }
        }

        enum class Type(val id: Int, private val key: String) : StringIdentifiable {
            BRITTLESTAR(0, "brittlestar"),
            CROWN_OF_THORNS(1, "crown_of_thorns"),
            SMALL(2, "small"),
            MEDIUM(3, "medium");

            override fun asString(): String {
                return this.key
            }

            companion object {
                val CODEC: StringIdentifiable.Codec<Type> = StringIdentifiable.createCodec { entries.toTypedArray() }
                private val BY_ID: IntFunction<Type> = ValueLists.createIdToValueFunction(
                    { obj: Type -> obj.id },
                    entries.toTypedArray(),
                    ValueLists.OutOfBoundsHandling.ZERO
                )

                fun byName(name: String?): Type {
                    return CODEC.byId(name, SMALL) as Type
                }

                fun fromId(id: Int): Type {
                    return BY_ID.apply(id) as Type
                }

                private val REEF_VARIANTS = listOf(
                    CROWN_OF_THORNS,
                    SMALL,
                    MEDIUM,
                )

                fun fromBiome(biome: RegistryEntry<Biome>, random: Random): Type {
                    return when {
                        biome.isIn(BiomeTags.IS_DEEP_OCEAN) -> {
                            BRITTLESTAR
                        }

                        biome.isIn(HybridAquaticBiomeTags.REEF) -> {
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

    override fun damage(source: DamageSource, amount: Float): Boolean {
        val attacker = source.attacker

        if (this.variant == Type.CROWN_OF_THORNS && attacker is LivingEntity) {
            attacker.addStatusEffect(StatusEffectInstance(StatusEffects.POISON, 100, 1))
        }

        return super.damage(source, amount)
    }

    override fun initialize(
        world: ServerWorldAccess,
        difficulty: LocalDifficulty,
        spawnReason: SpawnReason,
        entityData: EntityData?,
        entityNbt: NbtCompound?
    ): EntityData? {
        val biome = world.getBiome(this.blockPos)
        val selectedType = Type.fromBiome(biome, Random.Default)
        this.variant = selectedType

        overlayTexture = when (selectedType) {
            Type.CROWN_OF_THORNS, Type.BRITTLESTAR -> OverlayTextures.NONE

            Type.SMALL -> {
                val smallLayers = listOf(0, 1, 2)
                OverlayTextures.byId(random.nextInt(smallLayers.size))
            }

            Type.MEDIUM -> {
                val mediumLayers = listOf(0, 3, 4)
                OverlayTextures.byId(random.nextInt(mediumLayers.size))
            }
        }
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }

    private var overlayTexture
        get() = StarfishEntity.Companion.OverlayTextures.byId(dataTracker.get(OverlayTexture))
        set(value) {
            dataTracker.set(OverlayTexture, value.id)
        }
    override fun getOverlayTextureName(): String {
        return StarfishEntity.Companion.OverlayTextures.byId(dataTracker.get(OverlayTexture)).asString()
    }

    override fun initDataTracker() {
        dataTracker.startTracking(TYPE, 0)
        dataTracker.startTracking(OverlayTexture, 0)
        super.initDataTracker()
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        nbt.putString("Type", this.variant.asString())
        nbt.putInt("texture_overlay", this.overlayTexture.id)
        super.writeCustomDataToNbt(nbt)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        this.variant = Type.byName(nbt.getString("Type"))
        if(nbt.contains("texture_overlay")) this.overlayTexture = StarfishEntity.Companion.OverlayTextures.byId(nbt.getInt("texture_overlay"))
        super.readCustomDataFromNbt(nbt)
    }

    override fun getVariant(): Type {
        return Type.fromId((dataTracker.get(TYPE) as Int))
    }

    override fun setVariant(type: Type) {
        dataTracker.set(TYPE, type.id)
    }
}