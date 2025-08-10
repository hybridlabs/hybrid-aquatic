package dev.hybridlabs.aquatic.entity.shark

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.entity.ai.goal.SharkJumpGoal
import dev.hybridlabs.aquatic.entity.feature.BodyScarTextureFeature
import dev.hybridlabs.aquatic.entity.feature.OverlayTextureFeature
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.ai.goal.ChaseBoatGoal
import net.minecraft.entity.ai.goal.RevengeGoal
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.StringIdentifiable
import net.minecraft.util.function.ValueLists
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import java.util.function.IntFunction

class GreatWhiteSharkEntity(entityType: EntityType<out GreatWhiteSharkEntity>, world: World) :
    HybridAquaticSharkEntity(entityType, world, listOf(HybridAquaticEntityTags.LARGE_PREY), false, true),
    OverlayTextureFeature, BodyScarTextureFeature {

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(1, RevengeGoal(this))
        goalSelector.add(8, ChaseBoatGoal(this))
        goalSelector.add(5, SharkJumpGoal(this, 10))
    }

    override fun getLimitPerChunk(): Int {
        return 1
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 54.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.5)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32.0)
        }

        val FaceScarTexture: TrackedData<Int> =
            DataTracker.registerData(GreatWhiteSharkEntity::class.java, TrackedDataHandlerRegistry.INTEGER)

        val BodyScarTexture: TrackedData<Int> =
            DataTracker.registerData(GreatWhiteSharkEntity::class.java, TrackedDataHandlerRegistry.INTEGER)

        enum class FaceScarTextures(val id: Int, val key: String) : StringIdentifiable {
            NONE(0, ""),
            LEFT_EYE(1, "left_eye");

            override fun asString(): String {
                return this.key
            }

            companion object {
                val CODEC: Codec<FaceScarTextures> =
                    StringIdentifiable.createCodec { FaceScarTextures.entries.toTypedArray() }
                val BY_ID: IntFunction<FaceScarTextures> = ValueLists.createIdToValueFunction(
                    { overlayTex: FaceScarTextures -> overlayTex.id },
                    FaceScarTextures.entries.toTypedArray(),
                    ValueLists.OutOfBoundsHandling.WRAP
                )

                fun byId(id: Int): FaceScarTextures {
                    return BY_ID.apply(id)
                }
            }
        }
        enum class BodyScarTextures(val id: Int, val key: String) : StringIdentifiable {
            NONE(0, ""),
            LEFT_SIDE(1, "left_side");

            override fun asString(): String {
                return this.key
            }

            companion object {
                val CODEC: Codec<BodyScarTextures> =
                    StringIdentifiable.createCodec { BodyScarTextures.entries.toTypedArray() }
                val BY_ID: IntFunction<BodyScarTextures> = ValueLists.createIdToValueFunction(
                    { overlayTex: BodyScarTextures -> overlayTex.id },
                    BodyScarTextures.entries.toTypedArray(),
                    ValueLists.OutOfBoundsHandling.WRAP
                )

                fun byId(id: Int): BodyScarTextures {
                    return BY_ID.apply(id)
                }
            }
        }
    }

    override fun initialize(
        world: ServerWorldAccess,
        difficulty: LocalDifficulty,
        spawnReason: SpawnReason,
        entityData: EntityData?
    ): EntityData? {
        val faceID = world.random.nextBetween(0, FaceScarTextures.entries.size - 1)
        val bodyID = world.random.nextBetween(0, BodyScarTextures.entries.size - 1)

        faceScarTexture = FaceScarTextures.byId(faceID)
        bodyScarTexture = BodyScarTextures.byId(bodyID)

        return super.initialize(world, difficulty, spawnReason, entityData)
    }

    override fun getMaxSize(): Int {
        return 3
    }

    override fun getMinSize(): Int {
        return -3
    }

    private var faceScarTexture
        get() = GreatWhiteSharkEntity.Companion.FaceScarTextures.byId(dataTracker.get(FaceScarTexture))
        set(value) {
            dataTracker.set(FaceScarTexture, value.id)
        }

    private var bodyScarTexture
        get() = GreatWhiteSharkEntity.Companion.BodyScarTextures.byId(dataTracker.get(BodyScarTexture))
        set(value) {
            dataTracker.set(BodyScarTexture, value.id)
        }

    override fun getOverlayTextureName(): String {
        return GreatWhiteSharkEntity.Companion.FaceScarTextures.byId(dataTracker.get(FaceScarTexture)).asString()
    }
    override fun getBodyScarTextureName(): String {
        return GreatWhiteSharkEntity.Companion.BodyScarTextures.byId(dataTracker.get(BodyScarTexture)).asString()
    }

    override fun initDataTracker(builder: DataTracker.Builder) {
        builder.add(FaceScarTexture, 0)
        builder.add(BodyScarTexture, 0)
        super.initDataTracker(builder)
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        nbt.putInt("face_texture_overlay", this.faceScarTexture.id)
        nbt.putInt("body_texture_overlay", this.bodyScarTexture.id)
        super.writeCustomDataToNbt(nbt)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        if (nbt.contains("face_texture_overlay")) this.faceScarTexture =
            GreatWhiteSharkEntity.Companion.FaceScarTextures.byId(nbt.getInt("face_texture_overlay"))
        if (nbt.contains("body_texture_overlay")) this.bodyScarTexture =
            GreatWhiteSharkEntity.Companion.BodyScarTextures.byId(nbt.getInt("body_texture_overlay"))
        super.readCustomDataFromNbt(nbt)
    }
}