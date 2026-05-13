package dev.hybridlabs.aquatic.entity.crustacean

import dev.hybridlabs.aquatic.entity.ai.goal.CrustaceanDaytimeBurrowGoal
import dev.hybridlabs.aquatic.entity.ai.goal.WaterAnimalDigGoal
import dev.hybridlabs.aquatic.entity.base.HACrustaceanEntity
import dev.hybridlabs.aquatic.world.WorldHelper
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
import net.minecraft.world.entity.animal.Turtle
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationController.AnimationStateHandler
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.`object`.PlayState

@Suppress("UNUSED_PARAMETER", "DEPRECATION")
class GhostCrabEntity(entityType: EntityType<out HACrustaceanEntity>, world: Level) :
    HACrustaceanEntity(entityType, world, true) {

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, CrustaceanDaytimeBurrowGoal(this))
        goalSelector.addGoal(5, WaterAnimalDigGoal(this))
        targetSelector.addGoal(1, NearestAttackableTargetGoal(
            this, Turtle::class.java, 10, false, false,
            Turtle.BABY_ON_LAND_SELECTOR)
        )
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        super.registerControllers(controllerRegistrar)

        controllerRegistrar.add(
            AnimationController(
                this, "Spawning",
                AnimationStateHandler { state: AnimationState<HACrustaceanEntity> ->
                    if (this.tickCount < 20)
                        return@AnimationStateHandler state.setAndContinue(DefaultAnimations.SPAWN)
                    PlayState.STOP
                }
            )
                .setParticleKeyframeHandler { event ->
                    particleEvents(event)
                }
        )

        controllerRegistrar.add(
            AnimationController(
                this, "Burrowing",
                AnimationStateHandler { state: AnimationState<HACrustaceanEntity> ->
                    if (this.isBurrowing())
                        return@AnimationStateHandler state.setAndContinue(BURROW_ANIMATION)
                    PlayState.STOP
                }
            )
                .setParticleKeyframeHandler { event ->
                    particleEvents(event)
                }
        )

        controllerRegistrar.add(
            AnimationController(
                this, "Digging",
                AnimationStateHandler { state: AnimationState<HACrustaceanEntity> ->
                    if (this.isDigging())
                        return@AnimationStateHandler state.setAndContinue(DIG_ANIMATION)
                    PlayState.STOP
                }
            )
        )
        controllerRegistrar.add(
            DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_SWING))
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }

        fun canSpawn(
            type: EntityType<out GhostCrabEntity>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val seaLevel = world.level.chunkSource.generator.seaLevel
            return pos.y <= seaLevel + 4 &&
                    world.getBlockState(pos.below()).isSolid &&
                    world.isEmptyBlock(pos) &&
                    !world.level.isDay &&
                    WorldHelper.canSeeSkyFromBelowWater(world, pos)
        }
    }
}