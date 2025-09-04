package dev.hybridlabs.aquatic.entity.critter

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.entity.feature.OverlayTextureFeature
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.minecraft.entity.*
import net.minecraft.entity.attribute.AttributeSupplier
import net.minecraft.entity.attribute.Attributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.SynchedEntityData
import net.minecraft.entity.data.EntityDataAccessor
import net.minecraft.entity.data.EntityDataSerializers
import net.minecraft.entity.effect.MobEffectInstance
import net.minecraft.entity.effect.MobEffects
import net.minecraft.nbt.CompoundTag
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.registry.tag.BiomeTags
import net.minecraft.util.StringRepresentable
import net.minecraft.util.function.ByIdMap
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.ServerLevelAccess
import net.minecraft.world.World
import net.minecraft.world.biome.Biome
import java.util.function.IntFunction
import kotlin.random.Random

@Suppress("DEPRECATION")
class StarfishEntity(entityType: EntityType<out StarfishEntity>, world: Level) :
    HybridAquaticCritterEntity(
        entityType, world
    ),
    VariantHolder<StarfishEntity.Companion.Type>, OverlayTextureFeature {

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 1.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 2.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
        }

        val TYPE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(StarfishEntity::class.java, EntityDataSerializers.INTEGER)
        val OverlayTexture: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(StarfishEntity::class.java, EntityDataSerializers.INTEGER)

        enum class OverlayTextures(val id: Int, val key: String) : StringRepresentable {
            NONE(0, ""),
            STRIPES_SMALL(1, "stripes_small"),
            CIRCLE_SMALL(2, "circle_small"),
            STRIPES_CIRCLE_SMALL(3, "stripes_circle_small"),
            STRIPES (4, "stripes"),
            CIRCLE(5, "circle"),
            STRIPES_CIRCLE(6, "stripes_circle");

            override fun asString(): String {
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

            override fun asString(): String {
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

                fun fromBiome(biome: RegistryEntry<Biome>, random: RandomSource): Type {
                    return when {
                        biome.`is`(BiomeTags.IS_DEEP_OCEAN) -> {
                            BRITTLESTAR
                        }

                        biome.`is`(HybridAquaticBiomeTags.REEF) -> {
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
            attacker.addMobEffect(MobEffectInstance(MobEffects.POISON, 100, 1))
        }

        return super.damage(source, amount)
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?
    ): SpawnGroupData? {
        val biome = world.getBiome(this.blockPos)
        val selectedType = Type.fromBiome(biome, Random.Default)
        this.variant = selectedType

        overlayTexture = when (selectedType) {
            Type.CROWN_OF_THORNS, Type.BRITTLESTAR -> OverlayTextures.NONE
            Type.SMALL -> OverlayTextures.byId(listOf(0, 1, 2, 3).random(Random))
            Type.MEDIUM -> OverlayTextures.byId(listOf(0, 4, 5, 6).random(Random))
        }
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }

    private var overlayTexture
        get() = StarfishEntity.Companion.OverlayTextures.byId(entityData.get(OverlayTexture))
        set(value) {
            entityData.set(OverlayTexture, value.id)
        }
    override fun getOverlayTextureName(): String {
        return StarfishEntity.Companion.OverlayTextures.byId(entityData.get(OverlayTexture)).asString()
    }

    override fun initSynchedEntityData() {
        entityData.define(TYPE, 0)
        entityData.define(OverlayTexture, 0)
        super.initSynchedEntityData()
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        nbt.putString("Type", this.variant.asString())
        nbt.putInt("texture_overlay", this.overlayTexture.id)
        super.addAdditionalSaveData(nbt)
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        this.variant = Type.byName(nbt.getString("Type"))
        if(nbt.contains("texture_overlay")) this.overlayTexture = StarfishEntity.Companion.OverlayTextures.byId(nbt.getInt("texture_overlay"))
        super.readAdditionalSaveData(nbt)
    }

    override fun getVariant(): Type {
        return Type.fromId((entityData.get(TYPE) as Int))
    }

    override fun setVariant(type: Type) {
        entityData.set(TYPE, type.id)
    }
}