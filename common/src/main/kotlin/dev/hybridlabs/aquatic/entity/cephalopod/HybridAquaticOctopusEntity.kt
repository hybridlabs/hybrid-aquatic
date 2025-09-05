package dev.hybridlabs.aquatic.entity.cephalopod

import net.minecraft.tags.TagKey
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.goal.PanicGoal
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal
import net.minecraft.world.entity.animal.WaterAnimal
import net.minecraft.world.level.Level
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.animation.EasingType
import software.bernie.geckolib.util.GeckoLibUtil

@Suppress("LeakingThis", "unused")
open class HybridAquaticOctopusEntity(
    type: EntityType<out HybridAquaticCephalopodEntity>,
    world: Level,
    open val prey: TagKey<EntityType<*>>,
    open val predator: TagKey<EntityType<*>>,
    open var hasInk: Boolean,
    open var hasGlowInk: Boolean,
    open var canCamouflage: Boolean
) : WaterAnimal(type, world), GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)

    override fun registerGoals() {
        goalSelector.addGoal(0, PanicGoal(this, 1.25))
        goalSelector.addGoal(3, RandomSwimmingGoal(this, 1.0, 10))
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            AnimationController(
                this,
                "Swim/Run",
                20
            ) { state: AnimationState<HybridAquaticOctopusEntity> ->
                if (!this.isUnderWater && onGround()) {
                    state.setAndContinue(DefaultAnimations.SIT)
                } else {
                    if (state.isMoving) {
                        state.setAndContinue(if (this.isSprinting) DefaultAnimations.RUN else DefaultAnimations.SWIM)
                    } else {
                        state.setAndContinue(DefaultAnimations.IDLE)
                    }
                }
            }.setOverrideEasingType(EasingType.EASE_IN_OUT_SINE)
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }
}