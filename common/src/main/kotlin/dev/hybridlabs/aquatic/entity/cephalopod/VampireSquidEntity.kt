package dev.hybridlabs.aquatic.entity.cephalopod

import dev.hybridlabs.aquatic.entity.ai.MobTargetConfiguration
import dev.hybridlabs.aquatic.entity.ai.goal.PassiveFeedingGoal
import dev.hybridlabs.aquatic.entity.base.HACephalopodEntity
import dev.hybridlabs.aquatic.tag.HAEntityTags
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationController.AnimationStateHandler
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.`object`.PlayState

class VampireSquidEntity(type: EntityType<out VampireSquidEntity>, world: Level) : HACephalopodEntity(type, world) {
    override fun getTargetConfig() = MobTargetConfiguration.ofPrey(HAEntityTags.ALL_SHARKS)

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, PassiveFeedingGoal(this))
    }

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        super.registerControllers(controllers)
        controllers.add(
            AnimationController(
                this, "Feeding",
                AnimationStateHandler { state: AnimationState<HACephalopodEntity> ->
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
