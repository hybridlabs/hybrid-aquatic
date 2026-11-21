package dev.hybridlabs.aquatic.entity.miniboss

import dev.hybridlabs.aquatic.entity.ai.goal.MinionAttackGoal
import dev.hybridlabs.aquatic.entity.base.HAMinionEntity
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
import com.geckolib.animatable.manager.AnimatableManager
import com.geckolib.animation.AnimationController
import com.geckolib.animation.AnimationController.AnimationStateHandler
import com.geckolib.animation.RawAnimation
import com.geckolib.animation.state.AnimationTest
import com.geckolib.animation.`object`.PlayState
import com.geckolib.animation.`object`.EasingType
import com.geckolib.constant.DefaultAnimations

class KarcinomaEntity(type: EntityType<out HAMinionEntity>, world: Level) :
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
        goalSelector.addGoal(4, RandomSwimmingGoal(this, 1.0, 2))
    }

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(
            AnimationController<KarcinomaEntity>("Run/Swim/Idle", 4) {
                    state: AnimationTest<KarcinomaEntity> ->
                if (this.isInWater && state.isMoving) state.setAndContinue(
                    if (this.isSprinting) DefaultAnimations.RUN else DefaultAnimations.SWIM)
                else state.setAndContinue(DefaultAnimations.SWIM)
            }
        )

        controllers.add(
            AnimationController<KarcinomaEntity>("Flop", 4) { state ->
                if (!this.isInWater) {
                    return@AnimationController state.setAndContinue(FLOP_ANIMATION)
                }

                PlayState.STOP
            }
        )

        controllers.add(DefaultAnimations.genericAttackAnimation<KarcinomaEntity>(DefaultAnimations.ATTACK_SWING))
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
        return HASoundEvents.KARCINOMA_AMBIENT.get()
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return HASoundEvents.KARCINOMA_HURT.get()
    }

    override fun getDeathSound(): SoundEvent {
        return HASoundEvents.KARCINOMA_DIE.get()
    }
    //#endregion

    companion object {
        val FLOP_ANIMATION: RawAnimation = RawAnimation.begin().thenPlay("misc.flop")

        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.TEMPT_RANGE, 10.0)
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 1.0)
                .add(Attributes.ATTACK_DAMAGE, 4.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 24.0)
        }
    }
}