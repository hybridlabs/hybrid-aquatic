package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.ai.goal.FishJumpGoal
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.util.Identifier
import net.minecraft.world.World

class MahiEntity(entityType: EntityType<out MahiEntity>, world: World) :
    HybridAquaticFishEntity(
        entityType, world, variants = hashMapOf(
            "mahi" to FishVariant.biomeVariant(
                "mahi", listOf(HybridAquaticBiomeTags.TROPICAL_OCEANS),
                ignore = listOf(FishVariant.Ignore.ANIMATION)
            ),
            "pompano" to FishVariant.biomeVariant(
                "pompano", listOf(HybridAquaticBiomeTags.TROPICAL_OCEANS),
                ignore = listOf(FishVariant.Ignore.ANIMATION)
            ),
        ),
        listOf(
            HybridAquaticEntityTags.SMALL_PREY,
            HybridAquaticEntityTags.CEPHALOPOD
        ),
        listOf(
            HybridAquaticEntityTags.SHARK
        )
    ) {

    public override fun getLootTableId(): Identifier {
        return Identifier("hybrid-aquatic", "entities/mahi")
    }

    override fun getLimitPerChunk(): Int {
        return 2
    }

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(5, FishJumpGoal(this, 10))
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.7)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 8.0)
        }
    }
}