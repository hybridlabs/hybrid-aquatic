package dev.hybridlabs.aquatic.entity.crustacean

import dev.hybridlabs.aquatic.world.WorldHelper
import dev.hybridlabs.hapi.entity.ai.goal.aquatic.WaterAnimalDigGoal
import dev.hybridlabs.hapi.entity.base.aquatic.BaseCrustaceanEntity
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
class CoconutCrabEntity(entityType: EntityType<out CoconutCrabEntity>, world: Level) :
    BaseCrustaceanEntity(entityType, world, false) {

    override fun registerGoals() {
        super.registerGoals()
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
                AnimationStateHandler { state: AnimationState<BaseCrustaceanEntity> ->
                    if (this.tickCount < 20)
                        return@AnimationStateHandler state.setAndContinue(DefaultAnimations.SPAWN)
                    PlayState.STOP
                }
            )
                .setParticleKeyframeHandler { event -> particleEvents(event)
                }
        )

        controllerRegistrar.add(
            AnimationController(
                this, "Digging",
                AnimationStateHandler { state: AnimationState<BaseCrustaceanEntity> ->
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
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 5.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 8.0)
        }

        fun canSpawn(
            type: EntityType<out CoconutCrabEntity>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val seaLevel = world.level.chunkSource.generator.seaLevel
            return pos.y <= seaLevel + 4 &&
                    world.getBlockState(pos.below()).isSolid &&
                    world.isEmptyBlock(pos) &&
                    world.level.isDay &&
                    WorldHelper.canSeeSkyFromBelowWater(world, pos)
        }
    }

    override fun getMaxSize() : Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}