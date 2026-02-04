package dev.hybridlabs.aquatic.entity.mammal

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.entity.ai.goal.boids.StayInWaterGoal
import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.util.RandomSource
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl
import net.minecraft.world.entity.ai.goal.BreathAirGoal
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.pathfinder.BlockPathTypes
import net.minecraft.world.phys.Vec3

@Suppress("DEPRECATION")
class DugongEntity(type: EntityType<out DugongEntity>, world: Level) : HybridAquaticMammalEntity(type, world) {
    var prevRoll: Float = 0f
    var currentRoll: Float = 0.0f

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

    override fun getBreedOffspring(p0: ServerLevel, p1: AgeableMob): DugongEntity? {
        return HybridAquaticEntityTypes.DUGONG.get().create(p0)
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(0, StayInWaterGoal(this))
        goalSelector.addGoal(1, RandomSwimmingGoal(this, 1.0, 2))
        goalSelector.addGoal(5, BreathAirGoal(this))
    }

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

    override fun tick() {
        super.tick()
        prevRoll = currentRoll
    }

    override fun travel(travelVector: Vec3) {
        if (this.isEffectiveAi && this.isInWater) {
            this.moveRelative(this.speed, travelVector)
            this.move(MoverType.SELF, this.deltaMovement)
            this.deltaMovement = deltaMovement.scale(0.9)
        } else {
            super.travel(travelVector)
        }
    }

    override fun aiStep() {

        prevRoll = currentRoll
        var targetRoll = ((this.yRot - this.yRotO) * 0.1f).coerceIn(-0.45f, 0.45f)
        targetRoll = -targetRoll
        currentRoll += (targetRoll - currentRoll) * 0.05f

        this.updateSwingTime()
        super.aiStep()
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

        fun canSpawn(
            type: EntityType<out DugongEntity>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val topY = world.seaLevel - 1
            val bottomY = world.seaLevel - 32

            return pos.y in bottomY..topY &&
                    world.isWaterAt(pos) &&
                    world.canSeeSkyFromBelowWater(pos)
        }
    }
}
