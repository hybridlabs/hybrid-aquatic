package dev.hybridlabs.aquatic.entity.crustacean

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.entity.ai.goal.ShrimpCleanGoal
import dev.hybridlabs.aquatic.entity.feature.OverlayTextureFeature
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
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationController.AnimationStateHandler
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.animation.RawAnimation
import software.bernie.geckolib.core.`object`.PlayState
import java.util.function.IntFunction

class ShrimpEntity(entityType: EntityType<out HACrustaceanEntity>, world: Level) :
    HACrustaceanEntity(entityType, world, false),
    OverlayTextureFeature {

    fun isCleaning(): Boolean {
        return entityData.get(CLEANING)
    }

    private fun setCleaning(cleaning: Boolean) {
        entityData.set(CLEANING, cleaning)
    }

    fun startCleaning() {
        setCleaning(true)
        navigation.stop()
    }

    fun stopCleaning() {
        setCleaning(false)
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(2, ShrimpCleanGoal(this))
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        super.registerControllers(controllerRegistrar)

        controllerRegistrar.add(
            AnimationController(
                this, "Cleaning",
                AnimationStateHandler { state: AnimationState<ShrimpEntity> ->
                    if (this.isCleaning())
                        return@AnimationStateHandler state.setAndContinue(CLEAN_ANIMATION)
                    PlayState.STOP
                }
            )
        )
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 1.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }

        val CLEAN_ANIMATION: RawAnimation = RawAnimation.begin().thenPlay("misc.clean")

        val CLEANING: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(ShrimpEntity::class.java, EntityDataSerializers.BOOLEAN)

        val OverlayTexture: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(ShrimpEntity::class.java, EntityDataSerializers.INT)

        enum class OverlayTextures(val id: Int, val key: String) : StringRepresentable {
            NONE(0, ""),
            STRIPES(1, "stripes"),
            TAIL(2, "tail"),
            LEGS(3, "legs"),
            STRIPES_LEGS(4, "stripes_legs"),
            STRIPES_TAIL(5, "stripes_tail"),
            STRIPES_LEGS_TAIL(6, "stripes_legs_tail");

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

    override fun getMaxSize() : Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }

    private var overlayTexture
        get() = OverlayTextures.byId(entityData.get(OverlayTexture))
        set(value) {
            entityData.set(OverlayTexture, value.id)
        }

    override fun getOverlayTextureName(): String {
        return OverlayTextures.byId(entityData.get(OverlayTexture)).serializedName
    }

    override fun defineSynchedData() {
        entityData.define(OverlayTexture, 0)
        entityData.define(CLEANING, false)
        super.defineSynchedData()
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        nbt.putInt("texture_overlay", this.overlayTexture.id)
        this.setCleaning(nbt.getBoolean("Cleaning"))
        super.addAdditionalSaveData(nbt)
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        if(nbt.contains("texture_overlay")) this.overlayTexture = OverlayTextures.byId(nbt.getInt("texture_overlay"))
        this.setCleaning(nbt.getBoolean("Cleaning"))
        super.readAdditionalSaveData(nbt)
    }
}