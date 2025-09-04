package dev.hybridlabs.aquatic.entity.fish

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.entity.ai.goal.FishJumpGoal
import dev.hybridlabs.aquatic.entity.feature.OverlayTextureFeature
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityType
import net.minecraft.entity.MobSpawnType
import net.minecraft.entity.ai.goal.RevengeGoal
import net.minecraft.entity.attribute.AttributeSupplier
import net.minecraft.entity.attribute.Attributes
import net.minecraft.entity.data.SynchedEntityData
import net.minecraft.entity.data.EntityDataAccessor
import net.minecraft.entity.data.EntityDataSerializers
import net.minecraft.nbt.CompoundTag
import net.minecraft.util.StringRepresentable
import net.minecraft.util.function.ByIdMap
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.ServerLevelAccess
import net.minecraft.world.World
import java.util.function.IntFunction

class MantaRayEntity(entityType: EntityType<out MantaRayEntity>, world: Level) :
    HybridAquaticFishEntity(entityType, world,
        listOf(HybridAquaticEntityTags.NONE), listOf(HybridAquaticEntityTags.NONE)), OverlayTextureFeature {

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(5, FishJumpGoal(this, 10))
        goalSelector.addGoal(1, RevengeGoal(this))
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 5.0)
                .add(Attributes.FOLLOW_RANGE, 16.0)
        }

        val OverlayTexture: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(MantaRayEntity::class.java, EntityDataSerializers.INTEGER)

        enum class OverlayTextures(val id: Int, val key: String) : StringRepresentable {
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

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?
    ): SpawnGroupData? {
        val overlayID = world.random.nextIntBetweenInclusive(0, MantaRayEntity.Companion.OverlayTextures.entries.size - 1)
        overlayTexture = OverlayTextures.byId(overlayID)

        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun getMaxSize(): Int {
        return 3
    }

    override fun getMinSize(): Int {
        return -3
    }

    private var overlayTexture
        get() = MantaRayEntity.Companion.OverlayTextures.byId(entityData.get(OverlayTexture))
        set(value) {
            entityData.set(OverlayTexture, value.id)
        }

    override fun getOverlayTextureName(): String {
        return MantaRayEntity.Companion.OverlayTextures.byId(entityData.get(OverlayTexture)).asString()
    }

    override fun initSynchedEntityData() {
        entityData.define(OverlayTexture, 0)
        super.initSynchedEntityData()
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        nbt.putInt("texture_overlay", this.overlayTexture.id)
        super.addAdditionalSaveData(nbt)
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        if (nbt.contains("texture_overlay")) this.overlayTexture =
            MantaRayEntity.Companion.OverlayTextures.byId(nbt.getInt("texture_overlay"))
        super.readAdditionalSaveData(nbt)
    }
}