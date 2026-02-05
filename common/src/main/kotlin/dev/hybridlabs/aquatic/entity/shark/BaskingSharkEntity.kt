package dev.hybridlabs.aquatic.entity.shark

import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.RawAnimation

class BaskingSharkEntity(type: EntityType<out BaskingSharkEntity>, world: Level) :
    HybridAquaticSharkEntity(type, world) {

    private var isFeeding = false

    override fun tick() {
        super.tick()

        if (hunger < MAX_HUNGER / 4) {
            isFeeding = true
        }

        if (isFeeding) {
            hunger += 10

            if (hunger >= MAX_HUNGER) {
                hunger = MAX_HUNGER
                isFeeding = false
            }
        }
    }

    //#region Animations
    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(AnimationController(this, "Open/Closed", 0) { state ->
            val animation = when {
                isFeeding -> MOUTH_OPEN
                else -> MOUTH_CLOSED
            }
            state.setAndContinue(animation)
        })
        super.registerControllers(controllers)
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 60.0)
                .add(Attributes.MOVEMENT_SPEED, 0.75)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 16.0)
        }

        val MOUTH_OPEN: RawAnimation = RawAnimation.begin().thenPlay("misc.mouth_open")
        val MOUTH_CLOSED: RawAnimation = RawAnimation.begin().thenPlay("misc.mouth_closed")
    }
}
