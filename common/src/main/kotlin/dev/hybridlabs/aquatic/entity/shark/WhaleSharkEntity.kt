package dev.hybridlabs.aquatic.entity.shark

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.entity.feature.OverlayTextureFeature
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityType
import net.minecraft.entity.MobSpawnType
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
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.RawAnimation
import java.util.function.IntFunction

class WhaleSharkEntity(entityType: EntityType<out WhaleSharkEntity>, world: Level) :
    HybridAquaticSharkEntity(entityType, world, listOf(HybridAquaticEntityTags.NONE), true, false),
    OverlayTextureFeature {

    private var isFeeding = false

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(AnimationController(this, "Open/Closed", 0) { state ->
            val animation = when {
                isFeeding -> MOUTH_OPEN
                else -> MOUTH_CLOSED
            }
            state.setAndContinue(animation)
        })
        super.registerControllers(controllerRegistrar)
    }

    override fun tick() {
        super.tick()

        if (hunger < MAX_HUNGER / 4) {
            isFeeding = true
        }

        if (isFeeding) {
            hunger += 10

            if (hunger >= MAX_HUNGER) {
                hunger = MAX_HUNGER
                isFeeding = false
            }
        }
    }

    override fun getSpawnClusterSize(): Int {
        return 1
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 60.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 16.0)
        }

        val MOUTH_OPEN: RawAnimation = RawAnimation.begin().thenPlay("misc.mouth_open")
        val MOUTH_CLOSED: RawAnimation = RawAnimation.begin().thenPlay("misc.mouth_closed")

        val OverlayTexture: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(WhaleSharkEntity::class.java, EntityDataSerializers.INTEGER)

        enum class OverlayTextures(val id: Int, val key: String) : StringRepresentable {
            SPOTS(0, "spots"),
            SMALL_SPOTS(1, "small_spots");

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
        val overlayID = world.random.nextIntBetweenInclusive(0, WhaleSharkEntity.Companion.OverlayTextures.entries.size - 1)
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
        get() = WhaleSharkEntity.Companion.OverlayTextures.byId(entityData.get(OverlayTexture))
        set(value) {
            entityData.set(OverlayTexture, value.id)
        }

    override fun getOverlayTextureName(): String {
        return WhaleSharkEntity.Companion.OverlayTextures.byId(entityData.get(OverlayTexture)).asString()
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
            WhaleSharkEntity.Companion.OverlayTextures.byId(nbt.getInt("texture_overlay"))
        super.readAdditionalSaveData(nbt)
    }
}