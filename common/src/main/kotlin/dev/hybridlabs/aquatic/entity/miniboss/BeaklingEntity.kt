package dev.hybridlabs.aquatic.entity.miniboss

import dev.hybridlabs.aquatic.entity.ai.goal.MinionAttackGoal
import dev.hybridlabs.aquatic.entity.ai.goal.WaterAnimalFollowCreatureGoal
import dev.hybridlabs.aquatic.entity.base.HAMinionEntity
import dev.hybridlabs.aquatic.entity.projectile.CavitationBubbleEntity
import dev.hybridlabs.aquatic.sound.HASoundEvents
import net.minecraft.sounds.SoundEvent
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MoverType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
import net.minecraft.world.level.Level
import net.minecraft.world.level.pathfinder.PathType
import net.minecraft.world.phys.Vec3
import software.bernie.geckolib.animation.*
import software.bernie.geckolib.constant.DefaultAnimations

class BeaklingEntity(type: EntityType<out HAMinionEntity>, world: Level) :
    HAMinionEntity(type, world) {

    init {
        setPathfindingMalus(PathType.WATER, 0.0f)
        moveControl = SmoothSwimmingMoveControl(this, 85, 5, 0.02F, 0.1f, true)
        lookControl = SmoothSwimmingLookControl(this, 10)
        navigation = WaterBoundPathNavigation(this, world)
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(0, MinionAttackGoal(this, 0.6, true))
        goalSelector.addGoal(1, MoveTowardsTargetGoal(this, 1.0, 16.0F))
        goalSelector.addGoal(3, WaterAnimalFollowCreatureGoal(this, ShellBeastEntity::class.java, 1.5, 4.0F, 8.0F))
        goalSelector.addGoal(4, RandomSwimmingGoal(this, 1.0, 2))
    }

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(
            AnimationController(this, "Run/Swim/Idle", 4) {
                    state: AnimationState<BeaklingEntity> ->
                if (this.isInWaterOrBubble && state.isMoving) state.setAndContinue(
                    if (this.isSprinting) DefaultAnimations.RUN else DefaultAnimations.SWIM)
                else state.setAndContinue(DefaultAnimations.SWIM)
            }
        )

        controllers.add(
            AnimationController(this, "Flop", 4) { state ->
                if (!this.isInWaterOrBubble) {
                    return@AnimationController state.setAndContinue(FLOP_ANIMATION)
                }

                PlayState.STOP
            }
        )

        controllers.add(DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_BITE))
    }

    override fun nextStep(): Float {
        return Float.MAX_VALUE
    }

    override fun travel(travelVector: Vec3) {
        if (this.isEffectiveAi && this.isInWater) {
            this.moveRelative(this.speed, travelVector)
            this.move(MoverType.SELF, this.deltaMovement)
            this.deltaMovement = deltaMovement.scale(0.9)
            if (this.target == null) {
                this.deltaMovement = deltaMovement.add(0.0, -0.005, 0.0)
            }
        } else {
            super.travel(travelVector)
        }
    }

    override fun tick() {
        super.tick()

        if (!this.isInWater) {
            this.xRot = 0.0f
            this.yRot = 0.0f
        }
    }

    //#region SFX
    override fun getAmbientSound(): SoundEvent {
        return HASoundEvents.BEAKLING_AMBIENT.get()
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return HASoundEvents.BEAKLING_HURT.get()
    }

    override fun getDeathSound(): SoundEvent {
        return HASoundEvents.BEAKLING_DIE.get()
    }
    //#endregion

    override fun remove(reason: RemovalReason) {
        if (!level().isClientSide && this.isDeadOrDying) {

            val cavitationBubble = CavitationBubbleEntity(
                level(),
                this,
                0.0,
                0.0,
                0.0,
                2
            )

            cavitationBubble.setPos(
                this.x,
                this.getY(0.5),
                this.z
            )

            level().addFreshEntity(cavitationBubble)
        }

        super.remove(reason)
    }

    companion object {
        val FLOP_ANIMATION: RawAnimation = RawAnimation.begin().thenPlay("misc.flop")

        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 1.0)
                .add(Attributes.ATTACK_DAMAGE, 4.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 24.0)
        }
    }
}