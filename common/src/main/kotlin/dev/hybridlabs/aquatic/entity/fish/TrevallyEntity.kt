package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.ai.goal.FollowCreatureGoal
import dev.hybridlabs.aquatic.entity.base.HADolphinEntity
import dev.hybridlabs.aquatic.entity.base.HASchoolingFishEntity
import dev.hybridlabs.aquatic.entity.base.HASirenianEntity
import dev.hybridlabs.aquatic.entity.base.HASharkEntity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.animal.Dolphin
import net.minecraft.world.entity.animal.Turtle
import net.minecraft.world.level.Level

class TrevallyEntity(type: EntityType<out TrevallyEntity>, world: Level) :
    HASchoolingFishEntity(type, world) {

    override fun getMaxSpawnClusterSize(): Int {
        return 4
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, FollowCreatureGoal(this, HASharkEntity::class.java, 1.5, 4.0F, 16.0F))
        goalSelector.addGoal(1, FollowCreatureGoal(this, HADolphinEntity::class.java, 1.5, 4.0F, 16.0F))
        goalSelector.addGoal(1, FollowCreatureGoal(this, HASirenianEntity::class.java, 1.5, 4.0F, 16.0F))
        goalSelector.addGoal(1, FollowCreatureGoal(this, Dolphin::class.java, 1.5, 4.0F, 16.0F))
        goalSelector.addGoal(1, FollowCreatureGoal(this, Turtle::class.java, 1.5, 4.0F, 16.0F))
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 16.0)
        }
    }
}
