package dev.hybridlabs.aquatic.entity.shark

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.entity.feature.OverlayTextureFeature
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.util.ByIdMap
import net.minecraft.util.StringRepresentable
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.SpawnGroupData
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.RawAnimation
import java.util.function.IntFunction

class WhaleSharkEntity(type: EntityType<out WhaleSharkEntity>, world: Level) :
    HybridAquaticSharkEntity(type, world), OverlayTextureFeature {

    private var isFeeding = false

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

    override fun isFood(stack: ItemStack): Boolean {
        return stack.`is`(HybridAquaticItems.RAW_SHRIMP.get())
    }

    //#region Data
    override fun defineSynchedData() {
        entityData.define(OverlayTexture, 0)
        super.defineSynchedData()
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        nbt.putInt("texture_overlay", this.overlayTexture.id)
        super.addAdditionalSaveData(nbt)
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        if (nbt.contains("texture_overlay")) this.overlayTexture =
            OverlayTextures.byId(nbt.getInt("texture_overlay"))
        super.readAdditionalSaveData(nbt)
    }

    //#region Animations
    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(AnimationController(this, "Open/Closed", 0) { state ->
            val animation = when {
                isFeeding -> MOUTH_OPEN
                else -> MOUTH_CLOSED
            }
            state.setAndContinue(animation)
        })
        super.registerControllers(controllers)
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 60.0)
                .add(Attributes.MOVEMENT_SPEED, 0.75)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 16.0)
        }

        val MOUTH_OPEN: RawAnimation = RawAnimation.begin().thenPlay("misc.mouth_open")
        val MOUTH_CLOSED: RawAnimation = RawAnimation.begin().thenPlay("misc.mouth_closed")

        val OverlayTexture: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(WhaleSharkEntity::class.java, EntityDataSerializers.INT)

        enum class OverlayTextures(val id: Int, val key: String) : StringRepresentable {
            SPOTS(0, "spots"),
            SMALL_SPOTS(1, "small_spots");

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
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?
    ): SpawnGroupData? {
        val overlayID = world.random.nextIntBetweenInclusive(0, OverlayTextures.entries.size - 1)
        overlayTexture = OverlayTextures.byId(overlayID)

        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
    }

    private var overlayTexture
        get() = OverlayTextures.byId(entityData.get(OverlayTexture))
        set(value) {
            entityData.set(OverlayTexture, value.id)
        }

    override fun getOverlayTextureName(): String {
        return OverlayTextures.byId(entityData.get(OverlayTexture)).serializedName
    }
}
