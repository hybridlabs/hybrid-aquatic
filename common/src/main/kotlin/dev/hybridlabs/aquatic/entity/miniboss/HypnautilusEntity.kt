package dev.hybridlabs.aquatic.entity.miniboss

import dev.hybridlabs.aquatic.sound.HASoundEvents
import net.minecraft.core.BlockPos
import net.minecraft.sounds.SoundEvent
import net.minecraft.util.Mth
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.pathfinder.BlockPathTypes
import net.minecraft.world.phys.Vec3
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController


class HypnautilusEntity(type: EntityType<out HAMinionEntity>, world: Level) : HAMinionEntity(type, world) {
    var beastPosition = 0
    val beastDistance = 5.0

    init {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        moveControl = SmoothSwimmingMoveControl(this, 85, 5, 0.02F, 0.1f, true)
        lookControl = SmoothSwimmingLookControl(this, 10)
        navigation = WaterBoundPathNavigation(this, world)
    }

    override fun registerGoals() {
        //super.registerGoals()
        goalSelector.addGoal(4, RandomSwimmingGoal(this, 1.0, 2))
        goalSelector.addGoal(1, HypnautilusSyncedMovementGoal(this))
        goalSelector.addGoal(2, LookAtPlayerGoal(this, Player::class.java, 64.0f, 1f))
        targetSelector.addGoal(1, HurtByTargetGoal(this))
        targetSelector.addGoal(2, NearestAttackableTargetGoal(this, Player::class.java, 10, true, true, null))
    }

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_SWING))
        controllers.add(
            AnimationController(this, "Swim/Run/Idle", 4) { state ->
                when {
                    isInWater -> {
                        state.setAndContinue(DefaultAnimations.IDLE)
                    }

                    else -> {
                        state.setAndContinue(DefaultAnimations.IDLE)
                    }
                }
            })
    }


    override fun tick() {
        super.tick()

        if (!this.isInWater) {
            this.xRot = 0.0f
            this.yRot = 0.0f
        }
    }

    fun calcBeastRelativePos(): Vec3 {
        val owner = this.getOwner() ?: return Vec3.ZERO
        val upVector = Vec3(0.0, 1.0, 0.0)
        //val timeRot: Float = if (this.server != null) (server!!.tickCount % 100 / 100F * Mth.TWO_PI) else 0F

        val angle = Mth.PI / 3.0F * beastPosition + Mth.PI / 6F //+ timeRot

        return owner.position().add(
            upVector
                .zRot(angle)
                .yRot((180 - owner.yRot) * Mth.DEG_TO_RAD)
                .scale(beastDistance)
        )
    }

    //#region SFX
    override fun getAmbientSound(): SoundEvent {
        return HASoundEvents.HYPNAUTILUS_AMBIENT.get()
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return HASoundEvents.HYPNAUTILUS_HURT.get()
    }

    override fun getDeathSound(): SoundEvent {
        return HASoundEvents.HYPNAUTILUS_DIE.get()
    }
    //#endregion

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes().add(Attributes.MAX_HEALTH, 6.0).add(Attributes.MOVEMENT_SPEED, 1.0)
                .add(Attributes.ATTACK_DAMAGE, 4.0).add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 24.0)
        }

        class HypnautilusSyncedMovementGoal(private val hypnautilus: HypnautilusEntity) : Goal() {
            override fun canUse(): Boolean {
                return true
            }

            override fun requiresUpdateEveryTick(): Boolean {
                return true
            }

            override fun tick() {
                val target = hypnautilus.calcBeastRelativePos()
                if (hypnautilus.level().getBlockState(BlockPos.containing(target)).`is`(Blocks.WATER))
                    hypnautilus.moveTo(hypnautilus.calcBeastRelativePos())
            }
        }
    }
}