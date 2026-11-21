package dev.hybridlabs.aquatic.entity.crustacean

import dev.hybridlabs.aquatic.entity.ai.goal.CrustaceanDaytimeBurrowGoal
import dev.hybridlabs.aquatic.world.WorldHelper
import dev.hybridlabs.hapi.entity.ai.goal.aquatic.WaterAnimalDigGoal
import dev.hybridlabs.hapi.entity.base.aquatic.BaseCrustaceanEntity
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.EntitySpawnReason
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
import net.minecraft.world.entity.animal.turtle.Turtle
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import com.geckolib.animatable.manager.AnimatableManager
import com.geckolib.animation.AnimationController
import com.geckolib.animation.AnimationController.AnimationStateHandler
import com.geckolib.animation.state.AnimationTest
import com.geckolib.animation.`object`.PlayState
import com.geckolib.constant.DefaultAnimations

@Suppress("UNUSED_PARAMETER", "DEPRECATION")
class GhostCrabEntity(entityType: EntityType<out GhostCrabEntity>, world: Level) :
    BaseCrustaceanEntity(entityType, world, true) {

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
            AnimationController<BaseCrustaceanEntity>("Spawning",
                AnimationStateHandler { state: AnimationTest<BaseCrustaceanEntity> ->
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
            AnimationController<BaseCrustaceanEntity>("Burrowing",
                AnimationStateHandler { state: AnimationTest<BaseCrustaceanEntity> ->
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
            AnimationController<BaseCrustaceanEntity>("Digging",
                AnimationStateHandler { state: AnimationTest<BaseCrustaceanEntity> ->
                    if (this.isDigging())
                        return@AnimationStateHandler state.setAndContinue(DIG_ANIMATION)
                    PlayState.STOP
                }
            )
        )
        controllerRegistrar.add(
            DefaultAnimations.genericAttackAnimation<GhostCrabEntity>(DefaultAnimations.ATTACK_SWING))
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.TEMPT_RANGE, 10.0)
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }

        fun canSpawn(
            type: EntityType<out GhostCrabEntity>,
            world: ServerLevelAccessor,
            reason: EntitySpawnReason,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val seaLevel = world.level.chunkSource.generator.seaLevel
            return pos.y <= seaLevel + 4 &&
                    world.getBlockState(pos.below()).isSolid &&
                    world.isEmptyBlock(pos) &&
                    !world.level.isBrightOutside() &&
                    WorldHelper.canSeeSkyFromBelowWater(world, pos)
        }
    }
}