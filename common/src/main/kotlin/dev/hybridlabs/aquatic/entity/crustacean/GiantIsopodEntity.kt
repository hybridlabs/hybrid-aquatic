package dev.hybridlabs.aquatic.entity.crustacean

import dev.hybridlabs.aquatic.entity.crustacean.HybridAquaticCrustaceanEntity
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.`object`.PlayState

class GiantIsopodEntity(entityType: EntityType<out HybridAquaticCrustaceanEntity>, world: Level) :
    HybridAquaticCrustaceanEntity(entityType, world, false) {
    private var isHiding: Boolean = false
    private var hidingTimer: Int = 0
    private var lastDamageTime: Long = 0

    //#region Hiding
    private fun startHiding() {
        isHiding = true
        hidingTimer = 200

        attributes.getInstance(Attributes.MOVEMENT_SPEED)?.baseValue = 0.0
        attributes.getInstance(Attributes.ARMOR)?.baseValue = 50.0
    }

    override fun tick() {
        super.tick()

        if (isHiding) {
            hidingTimer--

            if (hidingTimer <= 0 && (level().gameTime - lastDamageTime) >= 200) {
                isHiding = false
                attributes.getInstance(Attributes.MOVEMENT_SPEED)?.baseValue = 0.3
                attributes.getInstance(Attributes.ARMOR)?.baseValue = 5.0
            }
        }
    }

    override fun hurt(source: DamageSource, amount: Float): Boolean {
        if (!isHiding) {
            startHiding()
        }

        lastDamageTime = level().gameTime

        return super.hurt(source, amount)
    }
    //#endregion

    //#region Animations
    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            DefaultAnimations.genericWalkIdleController(this)
        )
        controllerRegistrar.add(
            AnimationController(this, "Hide", 4,
                AnimationController.AnimationStateHandler { state: AnimationState<HybridAquaticCrustaceanEntity> ->
                    if (this.isHiding) {
                        return@AnimationStateHandler state.setAndContinue(HIDE_ANIMATION)
                    } else {
                        PlayState.STOP
                    }
                }
            )
        )
    }
    //#endregion

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}