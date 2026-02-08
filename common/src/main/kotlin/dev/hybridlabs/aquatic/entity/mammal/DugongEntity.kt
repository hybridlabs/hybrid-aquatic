package dev.hybridlabs.aquatic.entity.mammal

import net.minecraft.core.BlockPos
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.util.RandomSource
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.pathfinder.BlockPathTypes
import net.minecraft.world.phys.Vec3

@Suppress("DEPRECATION")
class DugongEntity(type: EntityType<out DugongEntity>, world: Level) : HybridAquaticSirenianEntity(type, world) {

    init {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0f)
        setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0f)
        moveControl = SmoothSwimmingMoveControl(this, 45, 3, 0.02F, 0.1F, false)
        lookControl = SmoothSwimmingLookControl(this, 15)
        navigation = WaterBoundPathNavigation(this, world)
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 3
    }

    override fun getStandingEyeHeight(pose: Pose, dimensions: EntityDimensions): Float {
        return dimensions.height * 0.5f
    }

    //#region SFX
    override fun getAmbientSound(): SoundEvent {
        return SoundEvents.COW_AMBIENT
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.COW_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.COW_DEATH
    }

    override fun getSwimSplashSound(): SoundEvent {
        return SoundEvents.DOLPHIN_SPLASH
    }

    override fun getSwimSound(): SoundEvent {
        return SoundEvents.DOLPHIN_SWIM
    }
    //#endregion

    override fun getMobType(): MobType {
        return MobType.WATER
    }

    override fun canBreatheUnderwater(): Boolean {
        return true
    }

    override fun isPushedByFluid(): Boolean {
        return false
    }

    override fun getMaxHeadXRot(): Int {
        return 1
    }

    override fun getMaxHeadYRot(): Int {
        return 1
    }

    companion object {

        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 32.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 6.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 12.0)
        }
    }
}
