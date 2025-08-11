package dev.hybridlabs.aquatic.entity.fish

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.entity.ai.goal.FishJumpGoal
import dev.hybridlabs.aquatic.entity.feature.OverlayTextureFeature
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
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

class MantaRayEntity(entityType: EntityType<out MantaRayEntity>, world: World) :
    HybridAquaticFishEntity(entityType, world,
        listOf(HybridAquaticEntityTags.NONE), listOf(HybridAquaticEntityTags.NONE)), OverlayTextureFeature {

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(5, FishJumpGoal(this, 10))
        goalSelector.add(1, RevengeGoal(this))
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16.0)
        }

        val OverlayTexture: TrackedData<Int> =
            DataTracker.registerData(MantaRayEntity::class.java, TrackedDataHandlerRegistry.INTEGER)

        enum class OverlayTextures(val id: Int, val key: String) : StringIdentifiable {
            NONE(0, ""),
            BACK(1, "back"),
            WINGS(2, "wings"),
            BACK_WINGS(3, "back_wings"),
            FULL(4, "full");

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
        entityData: EntityData?
    ): EntityData? {
        val overlayID = world.random.nextBetween(0, MantaRayEntity.Companion.OverlayTextures.entries.size - 1)
        overlayTexture = OverlayTextures.byId(overlayID)

        return super.initialize(world, difficulty, spawnReason, entityData)
    }

    override fun getMaxSize(): Int {
        return 3
    }

    override fun getMinSize(): Int {
        return -3
    }

    private var overlayTexture
        get() = MantaRayEntity.Companion.OverlayTextures.byId(dataTracker.get(OverlayTexture))
        set(value) {
            dataTracker.set(OverlayTexture, value.id)
        }

    override fun getOverlayTextureName(): String {
        return MantaRayEntity.Companion.OverlayTextures.byId(dataTracker.get(OverlayTexture)).asString()
    }

    override fun initDataTracker(builder: DataTracker.Builder) {
        builder.add(OverlayTexture, 0)
        super.initDataTracker(builder)
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        nbt.putInt("texture_overlay", this.overlayTexture.id)
        super.writeCustomDataToNbt(nbt)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        if (nbt.contains("texture_overlay")) this.overlayTexture =
            MantaRayEntity.Companion.OverlayTextures.byId(nbt.getInt("texture_overlay"))
        super.readCustomDataFromNbt(nbt)
    }
}