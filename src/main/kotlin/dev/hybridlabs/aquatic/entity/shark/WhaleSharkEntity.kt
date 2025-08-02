package dev.hybridlabs.aquatic.entity.shark

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
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.RawAnimation
import java.util.function.IntFunction

class WhaleSharkEntity(entityType: EntityType<out WhaleSharkEntity>, world: World) :
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

    override fun getLimitPerChunk(): Int {
        return 1
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 60.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16.0)
        }

        val MOUTH_OPEN: RawAnimation = RawAnimation.begin().thenPlay("misc.mouth_open")
        val MOUTH_CLOSED: RawAnimation = RawAnimation.begin().thenPlay("misc.mouth_closed")

        val OverlayTexture: TrackedData<Int> =
            DataTracker.registerData(WhaleSharkEntity::class.java, TrackedDataHandlerRegistry.INTEGER)

        enum class OverlayTextures(val id: Int, val key: String) : StringIdentifiable {
            SPOTS(0, "spots"),
            SMALL_SPOTS(1, "small_spots");

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
        val overlayID = world.random.nextBetween(0, WhaleSharkEntity.Companion.OverlayTextures.entries.size - 1)
        overlayTexture = OverlayTextures.byId(overlayID)

        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun getMaxSize(): Int {
        return 3
    }

    override fun getMinSize(): Int {
        return -3
    }

    private var overlayTexture
        get() = WhaleSharkEntity.Companion.OverlayTextures.byId(dataTracker.get(OverlayTexture))
        set(value) {
            dataTracker.set(OverlayTexture, value.id)
        }

    override fun getOverlayTextureName(): String {
        return WhaleSharkEntity.Companion.OverlayTextures.byId(dataTracker.get(OverlayTexture)).asString()
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
        if (nbt.contains("texture_overlay")) this.overlayTexture =
            WhaleSharkEntity.Companion.OverlayTextures.byId(nbt.getInt("texture_overlay"))
        super.readCustomDataFromNbt(nbt)
    }
}