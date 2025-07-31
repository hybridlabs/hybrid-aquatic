package dev.hybridlabs.aquatic.entity.fish

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.entity.feature.OverlayTextureFeature
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
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

class DanioEntity(entityType: EntityType<out DanioEntity>, world: World) :
    HybridAquaticSchoolingFishEntity(entityType, world,
        listOf(
            HybridAquaticEntityTags.NONE),
        listOf(
            HybridAquaticEntityTags.MEDIUM_PREY,
            HybridAquaticEntityTags.LARGE_PREY,
            HybridAquaticEntityTags.SHARK)), OverlayTextureFeature {

    override fun getLimitPerChunk(): Int {
        return 4
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

    override fun initDataTracker() {
        dataTracker.startTracking(OverlayTexture, 0)
        super.initDataTracker()
    }

    override var overlayTextureName: String = ""
    private var overlayTexture
        get() = OverlayTextures.byId(dataTracker.get(OverlayTexture))
        set(value) { overlayTextureName = value.asString(); return dataTracker.set(OverlayTexture, value.id) }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        nbt.putInt("texture_overlay", this.overlayTexture.id)
        super.writeCustomDataToNbt(nbt)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        if(nbt.contains("texture_overlay")) this.overlayTexture = OverlayTextures.byId(nbt.getInt("texture_overlay"))
        super.readCustomDataFromNbt(nbt)
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 2.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 4.0)
        }

        val OverlayTexture: TrackedData<Int> =
            DataTracker.registerData(DanioEntity::class.java, TrackedDataHandlerRegistry.INTEGER)

        enum class OverlayTextures(val id: Int, val key: String) : StringIdentifiable {
            NONE(0, ""),
            SCAR(1, "scar");

            override fun asString(): String {
                return this.key
            }

            companion object {
                val CODEC: Codec<OverlayTextures> = StringIdentifiable.createCodec { OverlayTextures.entries.toTypedArray() }
                val BY_ID: IntFunction<OverlayTextures> = ValueLists.createIdToValueFunction<OverlayTextures>(
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

}