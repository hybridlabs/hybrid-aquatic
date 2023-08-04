package dev.hybridlabs.aquatic.entity

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks.ANEMONE
import dev.hybridlabs.aquatic.config.HybridAquaticConfig
import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.Goal
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animation.Animation
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.animation.RawAnimation
import software.bernie.geckolib.core.`object`.PlayState
import java.util.EnumSet

class BullSharkEntity(entityType: EntityType<out BullSharkEntity>, world: World) : HybridAquaticFishEntity(entityType, world) {
    private val isAttacked = false

    override fun initGoals() {
        goalSelector.add(2, GoalHideInAnemone(this))
        super.initGoals()
    }

    class GoalHideInAnemone(private val clownfish: BullSharkEntity) : Goal() {
        private var targetAnemonePos: BlockPos? = null

        init {
            controls = EnumSet.of(Control.MOVE)
        }

        override fun canStart(): Boolean {
            return clownfish.attacker != null
        }

        override fun start() {
            findNearestAnemone()?.let { nearestAnemonePos ->
                targetAnemonePos = nearestAnemonePos
                clownfish.getNavigation().startMovingTo(
                    nearestAnemonePos.x.toDouble(),
                    nearestAnemonePos.y.toDouble(),
                    nearestAnemonePos.z.toDouble(),
                    1.0
                )
            }
        }

        override fun shouldContinue(): Boolean {
            return targetAnemonePos != null && clownfish.isAlive && clownfish.isAttacked
        }

        override fun tick() {
            targetAnemonePos?.let { pos ->
                clownfish.getNavigation().startMovingTo(
                    pos.x.toDouble(),
                    pos.y.toDouble(),
                    pos.z.toDouble(),
                    1.0
                )
            }
        }

        override fun stop() {
            targetAnemonePos = null
            clownfish.getNavigation().stop()
        }

        private fun findNearestAnemone(): BlockPos? {
            val currentPos = clownfish.blockPos
            for (i in -8..8) {
                for (j in -8..8) {
                    for (k in -8..8) {
                        val checkPos = currentPos.add(i, j, k)
                        val block = clownfish.world.getBlockState(checkPos).block
                        if (block === ANEMONE) {
                            return checkPos
                        }
                    }
                }
            }
            return null
        }
    }

    override fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        return if (event.isMoving && !this.isSubmergedInWater) {
            event.controller.setAnimation(RawAnimation.begin().then("flop", Animation.LoopType.LOOP))
            PlayState.CONTINUE
        } else PlayState.STOP

    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, HybridAquaticConfig.CLOWNFISH_HEALTH.toDouble())
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, HybridAquaticConfig.CLOWNFISH_SPEED)
        }
    }
}
