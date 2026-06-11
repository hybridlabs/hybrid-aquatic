package dev.hybridlabs.aquatic.entity.crustacean

import dev.hybridlabs.aquatic.entity.ai.goal.WaterAnimalDigGoal
import dev.hybridlabs.aquatic.entity.base.HACrustaceanEntity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
import net.minecraft.world.entity.animal.Turtle
import net.minecraft.world.level.Level
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.animation.AnimatableManager
import software.bernie.geckolib.animation.AnimationController
import software.bernie.geckolib.animation.AnimationController.AnimationStateHandler
import software.bernie.geckolib.animation.AnimationState
import software.bernie.geckolib.animation.PlayState

class FlowerCrabEntity(entityType: EntityType<out HACrustaceanEntity>, world: Level) :
    HACrustaceanEntity(entityType, world, true) {

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(5, WaterAnimalDigGoal(this))
        targetSelector.addGoal(1, NearestAttackableTargetGoal(
            this, Turtle::class.java, 10, false, false,
            Turtle.BABY_ON_LAND_SELECTOR)
        )
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        super.registerControllers(controllerRegistrar)

        controllerRegistrar.add(
            AnimationController(
                this, "Spawning",
                AnimationStateHandler { state: AnimationState<HACrustaceanEntity> ->
                    if (this.tickCount < 20)
                        return@AnimationStateHandler state.setAndContinue(DefaultAnimations.SPAWN)
                    PlayState.STOP
                }
            )
                .setParticleKeyframeHandler { event -> particleEvents(event)
                }
        )

        controllerRegistrar.add(
            AnimationController(
                this, "Digging",
                AnimationStateHandler { state: AnimationState<HACrustaceanEntity> ->
                    if (this.isDigging())
                        return@AnimationStateHandler state.setAndContinue(DIG_ANIMATION)
                    PlayState.STOP
                }
            )
        )
        controllerRegistrar.add(
            DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_SWING))
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}