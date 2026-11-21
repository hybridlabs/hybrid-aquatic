package dev.hybridlabs.aquatic.entity.crustacean

import dev.hybridlabs.hapi.entity.ai.goal.aquatic.WaterAnimalDigGoal
import dev.hybridlabs.hapi.entity.base.aquatic.BaseCrustaceanEntity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level
import com.geckolib.animatable.manager.AnimatableManager
import com.geckolib.animation.AnimationController
import com.geckolib.animation.AnimationController.AnimationStateHandler
import com.geckolib.animation.state.AnimationTest
import com.geckolib.animation.`object`.PlayState
import com.geckolib.constant.DefaultAnimations

class VampireCrabEntity(entityType: EntityType<out VampireCrabEntity>, world: Level) :
    BaseCrustaceanEntity(entityType, world, true) {

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(5, WaterAnimalDigGoal(this))

    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        super.registerControllers(controllerRegistrar)

        controllerRegistrar.add(
            AnimationController<BaseCrustaceanEntity>("Spawning",
                AnimationStateHandler { state: AnimationTest<BaseCrustaceanEntity> ->
                    if (this.tickCount < 20)
                        return@AnimationStateHandler state.setAndContinue(DefaultAnimations.SPAWN)
                    PlayState.STOP
                }
            )
                .setParticleKeyframeHandler { event -> particleEvents(event) }
        )

        controllerRegistrar.add(
            AnimationController<BaseCrustaceanEntity>("Digging",
                AnimationStateHandler { state: AnimationTest<BaseCrustaceanEntity> ->
                    if (this.isDigging())
                        return@AnimationStateHandler state.setAndContinue(DIG_ANIMATION)
                    PlayState.STOP
                }
            )
        )
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.TEMPT_RANGE, 10.0)
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }
    }

    override fun getMaxSize() : Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}