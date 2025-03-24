package dev.hybridlabs.aquatic.entity.cephalopod

import dev.hybridlabs.aquatic.entity.ai.goal.StayDeepGoal
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.world.World
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.RawAnimation

class VampireSquidEntity(entityType: EntityType<out VampireSquidEntity>, world: World) :
    HybridAquaticCephalopodEntity(
        entityType,
        world,
        emptyMap(),
        HybridAquaticEntityTags.NONE,
        HybridAquaticEntityTags.SHARK,
        false,
        false
    ) {

    private var isFeeding = false

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(AnimationController(this, "Open/Closed", 4) { state ->
            val animation = when {
                isFeeding -> TENTACLES_EXTENDED
                else -> TENTACLES_RETRACTED
            }
            state.setAndContinue(animation)
        })
        super.registerControllers(controllerRegistrar)
    }

    override fun tick() {
        super.tick()

        if (hunger < MAX_HUNGER / 4) {
            isFeeding = true
        }

        if (isFeeding) {
            hunger += 1

            if (hunger >= MAX_HUNGER) {
                hunger = MAX_HUNGER
                isFeeding = false
            }
        }
    }

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(1, StayDeepGoal(this, 1.0, 1, 12))
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 8.0)
        }

        val TENTACLES_EXTENDED: RawAnimation = RawAnimation.begin().thenPlay("misc.tentacles_extended")
        val TENTACLES_RETRACTED: RawAnimation = RawAnimation.begin().thenPlay("misc.tentacles_retracted")
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}