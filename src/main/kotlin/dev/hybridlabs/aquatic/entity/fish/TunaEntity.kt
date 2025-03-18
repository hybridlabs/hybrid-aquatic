package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.ai.goal.FishJumpGoal
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.util.Identifier
import net.minecraft.world.World

class TunaEntity(entityType: EntityType<out TunaEntity>, world: World) :
    HybridAquaticSchoolingFishEntity(
        entityType, world,
        listOf(
            HybridAquaticEntityTags.SMALL_PREY,
            HybridAquaticEntityTags.CEPHALOPOD,
        ),
        listOf(
            HybridAquaticEntityTags.SHARK
        ),
        variants = hashMapOf(
            "bluefin" to FishVariant.biomeVariant(
                "bluefin", listOf(HybridAquaticBiomeTags.TEMPERATE_OCEANS),
                ignore = listOf(FishVariant.Ignore.ANIMATION)
            ),
            "yellowfin" to FishVariant.biomeVariant(
                "yellowfin", listOf(HybridAquaticBiomeTags.TROPICAL_OCEANS, HybridAquaticBiomeTags.TEMPERATE_OCEANS),
                ignore = listOf(FishVariant.Ignore.ANIMATION)
            )
        )
    ) {

    public override fun getLootTableId(): Identifier {
        return when (this.variant?.variantName) {
            "yellowfin" -> Identifier("hybrid-aquatic", "gameplay/yellowfin")
            "bluefin" -> Identifier("hybrid-aquatic", "gameplay/bluefin")
            else -> super.getLootTableId()
        }
    }

    override fun getLimitPerChunk(): Int {
        return 3
    }

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(5, FishJumpGoal(this, 10))
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.7)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 8.0)
        }
    }
}
