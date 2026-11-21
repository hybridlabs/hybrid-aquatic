package dev.hybridlabs.aquatic.entity.shark

import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.hapi.entity.ai.goal.aquatic.PassiveFeedingGoal
import dev.hybridlabs.hapi.entity.ai.goal.aquatic.WaterAnimalJumpGoal
import dev.hybridlabs.hapi.entity.base.aquatic.BaseSharkEntity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import com.geckolib.animatable.manager.AnimatableManager
import com.geckolib.animation.AnimationController
import com.geckolib.animation.AnimationController.AnimationStateHandler
import com.geckolib.animation.RawAnimation
import com.geckolib.animation.state.AnimationTest
import com.geckolib.animation.`object`.PlayState
import com.geckolib.animation.`object`.EasingType

class BaskingSharkEntity(type: EntityType<out BaskingSharkEntity>, world: Level) :
    BaseSharkEntity(type, world) {

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, PassiveFeedingGoal(this))
        goalSelector.addGoal(5, WaterAnimalJumpGoal(this, 25, 5.0))
    }

    override fun isFood(stack: ItemStack): Boolean {
        return stack.`is`(HAItems.RAW_SHRIMP.get())
    }

    //#region Animations
    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        super.registerControllers(controllers)
        controllers.add(
            AnimationController<BaseSharkEntity>("Feeding",
                AnimationStateHandler { state: AnimationTest<BaseSharkEntity> ->
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
                .add(Attributes.TEMPT_RANGE, 10.0)
                .add(Attributes.MAX_HEALTH, 40.0)
                .add(Attributes.MOVEMENT_SPEED, 0.75)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 16.0)
        }

        val MOUTH_OPEN: RawAnimation = RawAnimation.begin().thenPlay("misc.mouth_open")
        val MOUTH_CLOSED: RawAnimation = RawAnimation.begin().thenPlay("misc.mouth_closed")
    }
}
