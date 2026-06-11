package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.ai.MobTargetConfiguration
import dev.hybridlabs.aquatic.entity.ai.goal.WaterAnimalEatItemGoal
import dev.hybridlabs.aquatic.entity.base.HAFishEntity
import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.aquatic.tag.HAEntityTags
import dev.hybridlabs.aquatic.tag.HAItemTags
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class MorayEelEntity(type: EntityType<out MorayEelEntity>, world: Level) :
    HAFishEntity(type, world) {

    override fun getTargetConfig() = TARGET_CONFIG

    override fun getMaxSpawnClusterSize(): Int {
        return 1
    }

    override fun shouldFlopOnLand(): Boolean {
        return false
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(2, WaterAnimalEatItemGoal(this))
    }

    override fun isFood(stack: ItemStack): Boolean {
        return stack.`is`(HAItems.RAW_TENTACLE.get()) ||
                stack.`is`(HAItemTags.SMALL_FISH) ||
                stack.`is`(HAItemTags.CRUSTACEAN_MEAT)
    }

    companion object {
        private val TARGET_CONFIG = MobTargetConfiguration.create(
            listOf(
                HAEntityTags.SMALL_CREATURES,
                HAEntityTags.ALL_CRUSTACEANS,
                HAEntityTags.ALL_CEPHALOPODS
            ),
            listOf(
                HAEntityTags.LARGE_CREATURES,
                HAEntityTags.ALL_SHARKS
            ),
        )

        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 5.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 8.0)
        }
    }
}
