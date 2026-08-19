package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.hapi.entity.ai.MobTargetConfiguration
import dev.hybridlabs.hapi.tag.HAPIEntityTags
import dev.hybridlabs.aquatic.tag.HAItemTags
import dev.hybridlabs.hapi.entity.ai.goal.aquatic.WaterAnimalEatItemGoal
import dev.hybridlabs.hapi.entity.base.aquatic.BaseFishEntity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class GoldenDoradoEntity(type: EntityType<out GoldenDoradoEntity>, world: Level) :
    BaseFishEntity(type, world) {

    override fun getTargetConfig() = TARGET_CONFIG

    override fun getMaxSpawnClusterSize(): Int {
        return 1
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(2, WaterAnimalEatItemGoal(this))
    }

    override fun isFood(stack: ItemStack): Boolean {
        return stack.`is`(HAItemTags.SMALL_FISH) ||
                stack.`is`(HAItemTags.MEDIUM_FISH) ||
                stack.`is`(HAItemTags.CRUSTACEAN_MEAT)
    }

    companion object {
        private val TARGET_CONFIG = MobTargetConfiguration.create(
            listOf(
                HAPIEntityTags.SMALL_CREATURES,
                HAPIEntityTags.MEDIUM_CREATURES,
                HAPIEntityTags.ALL_CRUSTACEANS
            ),
            listOf(
                HAPIEntityTags.ALL_SHARKS
            ),
        )

        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 5.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 16.0)
        }
    }
    //#endregion
}
