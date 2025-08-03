package dev.hybridlabs.aquatic.entity.cephalopod

import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.EscapeDangerGoal
import net.minecraft.entity.ai.goal.SwimAroundGoal
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.registry.tag.TagKey
import net.minecraft.world.World
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.animation.EasingType
import software.bernie.geckolib.util.GeckoLibUtil

@Suppress("LeakingThis", "UNUSED_PARAMETER")
open class HybridAquaticOctopusEntity(
    type: EntityType<out HybridAquaticCephalopodEntity>,
    world: World,
    open val prey: TagKey<EntityType<*>>,
    open val predator: TagKey<EntityType<*>>,
    open var hasInk: Boolean,
    open var hasGlowInk: Boolean,
    open var canCamouflage: Boolean
) : WaterCreatureEntity(type, world), GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)

    override fun initGoals() {
        goalSelector.add(0, EscapeDangerGoal(this, 1.25))
        goalSelector.add(3, SwimAroundGoal(this, 1.0, 10))
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            AnimationController(
                this,
                "Swim/Run",
                20
            ) { state: AnimationState<HybridAquaticOctopusEntity> ->
                if (!this.isSubmergedInWater && isOnGround) {
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