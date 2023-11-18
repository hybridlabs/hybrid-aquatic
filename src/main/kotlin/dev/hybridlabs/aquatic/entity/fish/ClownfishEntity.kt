package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks.ANEMONE
import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.Goal
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.*

class ClownfishEntity(entityType: EntityType<out ClownfishEntity>, world: World) :
    HybridAquaticFishEntity(entityType, world) {

    private val isAttacked = false

    override fun initGoals() {
        goalSelector.add(2, GoalHideInAnemone(this))
        goalSelector.add(3, GoalStayNearAnemone(this))
        super.initGoals()
    }

    class GoalHideInAnemone(private val clownfish: ClownfishEntity) : Goal() {
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
    }class GoalStayNearAnemone(private val clownfish: ClownfishEntity) : Goal() {
        private var targetAnemonePos: BlockPos? = null

        init {
            controls = EnumSet.of(Control.MOVE)
        }

        override fun canStart(): Boolean {
            return true
        }

        override fun start() {
            findNearestAnemone()?.let { nearestAnemonePos ->
                targetAnemonePos = nearestAnemonePos
            }
        }

        override fun shouldContinue(): Boolean {
            return targetAnemonePos != null && clownfish.isAlive
        }

        override fun tick() {
            targetAnemonePos?.let { pos ->
                val distanceSq = clownfish.squaredDistanceTo(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble())

                if (distanceSq > 25.0) {
                    clownfish.navigation.startMovingTo(
                        pos.x.toDouble(),
                        pos.y.toDouble(),
                        pos.z.toDouble(),
                        1.0
                    )
                } else {
                    clownfish.navigation.stop()
                }
            }
        }

        override fun stop() {
            targetAnemonePos = null
            clownfish.navigation.stop()
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

    companion object {
        fun createClownfishAttributes(): DefaultAttributeContainer.Builder {
            return createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.0)
        }
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}
