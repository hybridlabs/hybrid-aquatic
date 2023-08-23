package dev.hybridlabs.aquatic.goal

import com.mojang.datafixers.DataFixUtils
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticSchoolingFishEntity
import net.minecraft.entity.ai.goal.Goal
import java.util.function.Predicate

class FishFollowGroupLeaderGoal(
    val fish: HybridAquaticSchoolingFishEntity
): Goal() {
    private val MIN_SEARCH_DELAY = 200
    private var moveDelay = 0
    private var checkSurroundingDelay = getSurroundingSearchDelay(fish)

    protected fun getSurroundingSearchDelay(fish: HybridAquaticSchoolingFishEntity?): Int {
        return toGoalTicks(MIN_SEARCH_DELAY + fish!!.random.nextInt(MIN_SEARCH_DELAY) % 20)
    }

    override fun canStart(): Boolean {
        return if (fish.hasOtherFishInGroup()) {
            false
        } else if (fish.hasLeader()) {
            true
        } else if (checkSurroundingDelay > 0) {
            --checkSurroundingDelay
            false
        } else {
            checkSurroundingDelay = getSurroundingSearchDelay(fish)
            val predicate =
                Predicate { fish: HybridAquaticSchoolingFishEntity -> fish.canHaveMoreFishInGroup() || !fish.hasLeader() }
            val list = fish
                .world
                .getEntitiesByClass(fish.javaClass, fish.boundingBox.expand(8.0, 8.0, 8.0), predicate)
            val schoolingFishEntity =
                DataFixUtils.orElse(list.stream().filter { obj: HybridAquaticSchoolingFishEntity? -> obj!!.canHaveMoreFishInGroup() }
                    .findAny(), fish)
            schoolingFishEntity!!.pullInOtherFish(
                list.stream().filter { fish: HybridAquaticSchoolingFishEntity? -> !fish!!.hasLeader() })
            fish.hasLeader()
        }
    }

    override fun shouldContinue(): Boolean {
        return fish.hasLeader() && fish.isCloseEnoughToLeader()
    }

    override fun start() {
        moveDelay = 0
    }

    override fun stop() {
        fish.leaveGroup()
    }

    override fun tick() {
        if (--moveDelay <= 0) {
            moveDelay = getTickCount(10)
            fish.moveTowardLeader()
        }
    }
}