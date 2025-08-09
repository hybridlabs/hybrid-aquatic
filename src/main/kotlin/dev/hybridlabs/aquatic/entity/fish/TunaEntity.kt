package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.ai.goal.FishJumpGoal
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.world.World

class TunaEntity(entityType: EntityType<out TunaEntity>, world: World) :
    HybridAquaticSchoolingFishEntity(entityType, world, HybridAquaticEntityTags.TUNA_PREY, HybridAquaticEntityTags.TUNA_PREDATOR, variants = hashMapOf(
        "bluefin" to FishVariant.biomeVariant("bluefin", HybridAquaticBiomeTags.ALL_OCEANS,
            ignore = listOf(FishVariant.Ignore.ANIMATION)),
        "yellowfin" to FishVariant.biomeVariant("yellowfin", HybridAquaticBiomeTags.ALL_WARM_OCEANS,
            ignore = listOf(FishVariant.Ignore.ANIMATION)))) {

    override fun getLimitPerChunk(): Int {
        return 4
    }

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(5, FishJumpGoal(this, 10))
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 8.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 12.0)
        }
    }
    override fun speedModifier(): Double {
        return 0.005
    }
}
