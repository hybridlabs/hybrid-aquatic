package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.ai.MobTargetConfiguration
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.core.BlockPos
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor

class ParrotfishEntity(type: EntityType<out ParrotfishEntity>, world: Level) :
    HybridAquaticFishEntity(type, world) {

    override fun getTargetConfig() =
        MobTargetConfiguration.ofPrey(
            HybridAquaticEntityTags.LARGE_CREATURES,
            HybridAquaticEntityTags.ALL_SHARKS
        )

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    override fun isFood(stack: ItemStack): Boolean {
        return stack.`is`(HybridAquaticItems.CORAL_CHUNK.get())
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 8.0)
        }

        fun canSpawn(
            world: ServerLevelAccessor,
            pos: BlockPos,
        ): Boolean {
            return world.isWaterAt(pos) &&
                    world.level.isDay &&
                    world.canSeeSkyFromBelowWater(pos)
        }
    }
}
