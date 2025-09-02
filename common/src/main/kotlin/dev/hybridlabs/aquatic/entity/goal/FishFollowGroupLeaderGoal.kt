package dev.hybridlabs.aquatic.entity.goal

import com.mojang.datafixers.DataFixUtils
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticSchoolingFishEntity
import net.minecraft.world.entity.ai.goal.Goal
import java.util.function.Predicate

class FishFollowGroupLeaderGoal(
    val fish: HybridAquaticSchoolingFishEntity
) : Goal() {
    private val minSearchDelay = 200
    private var moveDelay = 0
    private var checkSurroundingDelay = getSurroundingSearchDelay(fish)

    fun getSurroundingSearchDelay(fish: HybridAquaticSchoolingFishEntity?): Int {
        return reducedTickDelay(minSearchDelay + fish!!.random.nextInt(minSearchDelay) % 20)
    }

    override fun canUse(): Boolean {
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
                .level()
                .getEntitiesOfClass(fish.javaClass, fish.boundingBox.inflate(8.0, 8.0, 8.0), predicate)
            val schoolingFishEntity =
                DataFixUtils.orElse(
                    list.stream().filter { obj: HybridAquaticSchoolingFishEntity? -> obj!!.canHaveMoreFishInGroup() }
                        .findAny(), fish)
            schoolingFishEntity!!.pullInOtherFish(
                list.stream().filter { fish: HybridAquaticSchoolingFishEntity? -> !fish!!.hasLeader() })
            fish.hasLeader()
        }
    }

    override fun canContinueToUse(): Boolean {
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
            moveDelay = adjustedTickDelay(10)
            fish.moveTowardLeader()
        }
    }
}
