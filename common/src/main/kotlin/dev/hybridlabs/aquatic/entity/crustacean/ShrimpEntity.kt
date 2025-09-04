package dev.hybridlabs.aquatic.entity.crustacean

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.entity.feature.OverlayTextureFeature
import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
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

class ShrimpEntity(entityType: EntityType<out HybridAquaticCrustaceanEntity>, world: World) :
    HybridAquaticCrustaceanEntity(entityType, world, false), OverlayTextureFeature {
    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 1.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 4.0)
        }

        val OverlayTexture: TrackedData<Int> =
            DataTracker.registerData(ShrimpEntity::class.java, TrackedDataHandlerRegistry.INTEGER)

        enum class OverlayTextures(val id: Int, val key: String) : StringIdentifiable {
            NONE(0, ""),
            STRIPES(1, "stripes"),
            TAIL(2, "tail"),
            LEGS(3, "legs"),
            STRIPES_LEGS(4, "stripes_legs"),
            STRIPES_TAIL(5, "stripes_tail"),
            STRIPES_LEGS_TAIL(6, "stripes_legs_tail");

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
    }

    override fun initialize(
        world: ServerWorldAccess,
        difficulty: LocalDifficulty,
        spawnReason: SpawnReason,
        entityData: EntityData?,
        entityNbt: NbtCompound?
    ): EntityData? {
        val overlayID = world.random.nextBetween(0, OverlayTextures.entries.size - 1)
        overlayTexture = OverlayTextures.byId(overlayID)

        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun getMaxSize() : Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }

    private var overlayTexture
        get() = ShrimpEntity.Companion.OverlayTextures.byId(dataTracker.get(OverlayTexture))
        set(value) {
            dataTracker.set(OverlayTexture, value.id)
        }

    override fun getOverlayTextureName(): String {
        return ShrimpEntity.Companion.OverlayTextures.byId(dataTracker.get(OverlayTexture)).asString()
    }

    override fun initDataTracker() {
        dataTracker.startTracking(OverlayTexture, 0)
        super.initDataTracker()
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        nbt.putInt("texture_overlay", this.overlayTexture.id)
        super.writeCustomDataToNbt(nbt)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        if(nbt.contains("texture_overlay")) this.overlayTexture = ShrimpEntity.Companion.OverlayTextures.byId(nbt.getInt("texture_overlay"))
        super.readCustomDataFromNbt(nbt)
    }
}