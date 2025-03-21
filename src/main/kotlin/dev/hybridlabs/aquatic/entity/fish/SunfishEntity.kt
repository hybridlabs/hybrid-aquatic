package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.ai.goal.FishJumpGoal
import dev.hybridlabs.aquatic.entity.ai.goal.StayNearSurfaceGoal
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.util.Identifier
import net.minecraft.world.World

class SunfishEntity(entityType: EntityType<out SunfishEntity>, world: World) :
    HybridAquaticFishEntity(
        entityType, world, variants = hashMapOf(
            "ocean" to FishVariant.biomeVariant(
                "ocean",
                listOf(HybridAquaticBiomeTags.TEMPERATE_OCEANS, HybridAquaticBiomeTags.TROPICAL_OCEANS),
                ignore = listOf(FishVariant.Ignore.ANIMATION)),
            "hoodwinker" to FishVariant.biomeVariant("hoodwinker",
                listOf(HybridAquaticBiomeTags.TEMPERATE_OCEANS, HybridAquaticBiomeTags.TROPICAL_OCEANS),
                ignore = listOf(FishVariant.Ignore.ANIMATION)),
            "sharptail" to FishVariant.biomeVariant("sharptail",
                listOf(HybridAquaticBiomeTags.TEMPERATE_OCEANS, HybridAquaticBiomeTags.TROPICAL_OCEANS),
                ignore = listOf(FishVariant.Ignore.ANIMATION)),
        ),
        listOf(
            HybridAquaticEntityTags.JELLYFISH
        ),
        listOf(
            HybridAquaticEntityTags.SHARK
        )
    ) {

    public override fun getLootTableId(): Identifier {
        return Identifier("hybrid-aquatic", "entities/sunfish")
    }

    override fun getLimitPerChunk(): Int {
        return 2
    }

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(5, FishJumpGoal(this, 10))
        goalSelector.add(1, StayNearSurfaceGoal(this, 1.0, 1, 16))

    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 12.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0)
        }
    }
}