package dev.hybridlabs.aquatic.entity.turtle

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.TurtleEggBlock
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal
import net.minecraft.entity.passive.TurtleEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.registry.tag.TagKey
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.random.Random
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import net.minecraft.world.WorldView
import net.minecraft.world.event.GameEvent
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.util.GeckoLibUtil

@Suppress("LeakingThis", "DEPRECATION")
open class HybridAquaticTurtleEntity(
    type: EntityType<out HybridAquaticTurtleEntity>,
    world: World,
    open val prey: List<TagKey<EntityType<*>>>,
    open val predator: List<TagKey<EntityType<*>>>,
    open val eggBlock: Block = Blocks.TURTLE_EGG
) : TurtleEntity(type, world), GeoEntity {

    private val factory = GeckoLibUtil.createInstanceCache(this)

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(AnimationController(this, "Swim/Walk/Idle", 4) { state ->
            val animation = when {
                state.isMoving -> if (this.isSubmergedInWater) DefaultAnimations.SWIM else DefaultAnimations.WALK
                else -> DefaultAnimations.IDLE
            }
            state.setAndContinue(animation)
        })
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    override fun initGoals() {
        goalSelector.add(1, LayEggGoal(this, 1.0))
        super.initGoals()
    }

    companion object {
        @Suppress("UNUSED_PARAMETER", "DEPRECATION")
        fun canSpawn(
            type: EntityType<out TurtleEntity>,
            world: ServerWorldAccess,
            reason: SpawnReason,
            pos: BlockPos,
            random: Random
        ): Boolean {
            return pos.y < world.seaLevel + 4 && isLightLevelValidForNaturalSpawn(world, pos)
        }
    }

    private class LayEggGoal(private val turtle: TurtleEntity, speed: Double) :
        MoveToTargetPosGoal(turtle, speed, 16) {
        override fun canStart(): Boolean {
            return if (turtle.hasEgg() && turtle.homePos.isWithinDistance(
                    turtle.pos, 9.0
                )
            ) super.canStart() else false
        }

        override fun shouldContinue(): Boolean {
            return super.shouldContinue() && turtle.hasEgg() && turtle.homePos.isWithinDistance(
                turtle.pos, 9.0
            )
        }

        override fun tick() {
            super.tick()
            val blockPos = turtle.blockPos
            if (!turtle.isTouchingWater && this.hasReached()) {
                if (turtle.sandDiggingCounter < 1) {
                    turtle.isDiggingSand = true
                } else if (turtle.sandDiggingCounter > this.getTickCount(200)) {
                    val world = turtle.world
                    world.playSound(
                        null as PlayerEntity?,
                        blockPos,
                        SoundEvents.ENTITY_TURTLE_LAY_EGG,
                        SoundCategory.BLOCKS,
                        0.3f,
                        0.9f + world.random.nextFloat() * 0.2f
                    )
                    val blockPos2 = targetPos.up()
                    val blockState: BlockState = (turtle as HybridAquaticTurtleEntity).eggBlock.defaultState.with(
                        TurtleEggBlock.EGGS,
                        turtle.random.nextInt(4) + 1
                    ) as BlockState
                    world.setBlockState(blockPos2, blockState, 3)
                    world.emitGameEvent(GameEvent.BLOCK_PLACE, blockPos2, GameEvent.Emitter.of(this.turtle, blockState))
                    turtle.setHasEgg(false)
                    turtle.isDiggingSand = false
                    turtle.loveTicks = 600
                }

                if (turtle.isDiggingSand) {
                    ++turtle.sandDiggingCounter
                }
            }
        }

        override fun isTargetPos(world: WorldView, pos: BlockPos): Boolean {
            return if (!world.isAir(pos.up())) false else TurtleEggBlock.isSand(world, pos)
        }
    }
}