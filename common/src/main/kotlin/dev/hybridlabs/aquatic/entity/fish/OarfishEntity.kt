package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.EntityDimensions
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.Pose
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import software.bernie.geckolib.animation.AnimatableManager
import software.bernie.geckolib.animation.AnimationController
import software.bernie.geckolib.animation.AnimationState
import software.bernie.geckolib.constant.DefaultAnimations

@Suppress("DEPRECATION", "UNUSED_PARAMETER")
class OarfishEntity(entityType: EntityType<out OarfishEntity>, world: Level) :
    HybridAquaticFishEntity(
        entityType, world,
        listOf(
            HybridAquaticEntityTags.NONE
        ),
        listOf(
            HybridAquaticEntityTags.SHARK
        )
    ) {

    override fun aiStep() {
        super.aiStep()

        if (!level().isClientSide && this.isEffectiveAi) {
            if (this.isInWater) {
                if (isFeeding()) {
                    this.deltaMovement = deltaMovement.subtract(0.0, 0.01, 0.0)
                    this.xRot = 0f
                }
            } else {
                setFeeding(false)
            }
        }
    }

    override fun tick() {
        super.tick()

        if (!level().isClientSide) {
            if (hunger < MAX_HUNGER / 4 && isInWater) {
                setFeeding(true)
            }

            if (isFeeding()) {
                hunger += 2

                if (hunger >= MAX_HUNGER) {
                    hunger = MAX_HUNGER
                    setFeeding(false)
                }
            }
        }
    }

    override fun getDefaultDimensions(pose: Pose): EntityDimensions {
        return if (isFeeding()) {
            EntityDimensions.scalable(0.5f, 5.0f)
        } else {
            super.getDefaultDimensions(pose)
        }
    }


    override fun onSyncedDataUpdated(key: EntityDataAccessor<*>) {
        super.onSyncedDataUpdated(key)
        if (key == FEEDING) {
            refreshDimensions()
        }
    }

    override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        super.defineSynchedData(builder)
        builder.define(FEEDING, false)
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        super.addAdditionalSaveData(nbt)
        nbt.putBoolean("Feeding", isFeeding())
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        super.readAdditionalSaveData(nbt)
        this.setFeeding(nbt.getBoolean("Feeding"))
        refreshDimensions()
    }

    private fun isFeeding(): Boolean {
        return entityData.get(FEEDING)
    }

    private fun setFeeding(vertical: Boolean) {
        entityData.set(FEEDING, vertical)
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(
            AnimationController(
                this, "Flop/Idle/Swim/Feed", 5
            ) { state: AnimationState<OarfishEntity> ->
                when {
                    this.isFeeding() -> state.setAndContinue(DefaultAnimations.SIT)
                    state.isMoving && isUnderWater -> state.setAndContinue(DefaultAnimations.SWIM)
                    !this.isUnderWater && !this.isSwimming && this.moistness < 595 -> state.setAndContinue(
                        FLOP_ANIMATION
                    )

                    else -> state.setAndContinue(DefaultAnimations.IDLE)
                }
            }
        )
    }

    companion object {
        val FEEDING: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(OarfishEntity::class.java, EntityDataSerializers.BOOLEAN)

        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 12.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 16.0)
        }

        fun canSpawn(
            type: EntityType<out OarfishEntity>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val shallowSpawn = (world.seaLevel - 12)..(world.seaLevel - 2)
            val deepSpawn = (world.seaLevel - 128)..(world.seaLevel - 48)

            val spawnY = if (world.level.isThundering) shallowSpawn else deepSpawn

            return pos.y in spawnY && world.isWaterAt(pos)
        }
    }

    override fun shouldFlopOnLand(): Boolean {
        return false
    }
}