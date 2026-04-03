package dev.hybridlabs.aquatic.entity.cephalopod

import dev.hybridlabs.aquatic.entity.ai.MobTargetConfiguration
import dev.hybridlabs.aquatic.tag.HAEntityTags
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.RawAnimation

class VampireSquidEntity(type: EntityType<out VampireSquidEntity>, world: Level) : HACephalopodEntity(type, world) {
    override fun getTargetConfig() = MobTargetConfiguration.ofPrey(HAEntityTags.ALL_SHARKS)
    private var isFeeding = false

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(AnimationController(this, "Open/Closed", 8) { state ->
            val animation = when {
                isFeeding -> TENTACLES_EXTENDED
                else -> TENTACLES_RETRACTED
            }
            state.setAndContinue(animation)
        })
        super.registerControllers(controllers)
    }

    override fun tick() {
        super.tick()

        if (hunger < MAX_HUNGER / 4) {
            isFeeding = true
        }

        if (isFeeding) {
            hunger += 2

            if (hunger >= MAX_HUNGER) {
                hunger = MAX_HUNGER
                isFeeding = false
            }
        }
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
