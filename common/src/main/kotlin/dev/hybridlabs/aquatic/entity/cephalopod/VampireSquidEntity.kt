package dev.hybridlabs.aquatic.entity.cephalopod

import dev.hybridlabs.hapi.tag.HAPIEntityTags
import dev.hybridlabs.hapi.entity.ai.MobTargetConfiguration
import dev.hybridlabs.hapi.entity.ai.goal.PassiveFeedingGoal
import dev.hybridlabs.hapi.entity.water.base.BaseCephalopodEntity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level
import software.bernie.geckolib.animation.AnimatableManager
import software.bernie.geckolib.animation.AnimationController
import software.bernie.geckolib.animation.AnimationController.AnimationStateHandler
import software.bernie.geckolib.animation.AnimationState
import software.bernie.geckolib.animation.PlayState

class VampireSquidEntity(type: EntityType<out VampireSquidEntity>, world: Level) : BaseCephalopodEntity(type, world) {
    override fun getTargetConfig() = MobTargetConfiguration.ofPrey(HAPIEntityTags.ALL_SHARKS)

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, PassiveFeedingGoal(this))
    }

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        super.registerControllers(controllers)
        controllers.add(
            AnimationController(
                this, "Feeding",
                AnimationStateHandler { state: AnimationState<BaseCephalopodEntity> ->
                    if (this.isFeeding())
                        return@AnimationStateHandler state.setAndContinue(FEED_ANIMATION)
                    PlayState.STOP
                }
            )
        )
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 8.0)
        }
    }
}
