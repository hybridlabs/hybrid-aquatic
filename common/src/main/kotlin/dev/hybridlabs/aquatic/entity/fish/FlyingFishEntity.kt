package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.ai.goal.FishJumpGoal
import dev.hybridlabs.aquatic.entity.ai.goal.StayNearSurfaceGoal
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.AttributeSupplier
import net.minecraft.entity.attribute.Attributes
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.`object`.PlayState

class FlyingFishEntity(entityType: EntityType<out FlyingFishEntity>, world: Level) :
    HybridAquaticSchoolingFishEntity(
        entityType, world,
        listOf(HybridAquaticEntityTags.NONE),
        listOf(
            HybridAquaticEntityTags.MEDIUM_PREY,
            HybridAquaticEntityTags.LARGE_PREY,
            HybridAquaticEntityTags.SHARK
        )
    ) {

    private var isGliding = false

    override fun getSpawnClusterSize(): Int {
        return 6
    }

    override fun registerGoals() {
        super.registerGoals()
        targetSelector.addGoal(5, FishJumpGoal(this, 10))
        goalSelector.addGoal(1, StayNearSurfaceGoal(this, 1.0, 1, 4))
    }

    override fun tick() {
        super.tick()

        if (!this.isTouchingWater && !isOnGround) {
            if (!isGliding) {
                startGliding()
            }
            applyGlidingPhysics()
        } else if (isGliding) {
            stopGliding()
        }
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            AnimationController(this, "Fly/Swim/Idle", 5
            ) { state: AnimationState<HybridAquaticFishEntity> ->
                when {
                    this.isGliding -> state.setAndContinue(DefaultAnimations.FLY)
                    state.isMoving -> state.setAndContinue(DefaultAnimations.SWIM)
                    else -> state.setAndContinue(DefaultAnimations.IDLE)
                }
            }
        )
    }

    private fun startGliding() {
        isGliding = true
    }

    private fun stopGliding() {
        isGliding = false
    }

    private fun applyGlidingPhysics() {
        if (!isGliding) return

        val motion = this.velocity
        val newMotion = Vec3d(
            motion.x * 1.1,
            (motion.y * 0.95).coerceAtLeast(-0.1),
            motion.z * 1.1
        )
        this.velocity = newMotion
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }
    }
}
