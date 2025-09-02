package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.goal.FishJumpGoal
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level

class SunfishEntity(entityType: EntityType<out SunfishEntity>, world: Level) :
    HybridAquaticFishEntity(
        entityType, world, variants = hashMapOf(
            "ocean" to FishVariant.biomeVariant(
                "ocean",
                listOf(HybridAquaticBiomeTags.TEMPERATE_OCEANS, HybridAquaticBiomeTags.TROPICAL_OCEANS),
                ignore = listOf(FishVariant.Ignore.ANIMATION)
            ),
            "hoodwinker" to FishVariant.biomeVariant(
                "hoodwinker",
                listOf(HybridAquaticBiomeTags.TEMPERATE_OCEANS, HybridAquaticBiomeTags.TROPICAL_OCEANS),
                ignore = listOf(FishVariant.Ignore.ANIMATION)
            ),
            "sharptail" to FishVariant.biomeVariant(
                "sharptail",
                listOf(HybridAquaticBiomeTags.TEMPERATE_OCEANS, HybridAquaticBiomeTags.TROPICAL_OCEANS),
                ignore = listOf(FishVariant.Ignore.ANIMATION)
            ),
        ),
        listOf(
            HybridAquaticEntityTags.JELLYFISH
        ),
        listOf(
            HybridAquaticEntityTags.SHARK
        )
    ) {

    public override fun getDefaultLootTable(): ResourceLocation {
        return ResourceLocation("hybrid-aquatic", "entities/sunfish")
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(5, FishJumpGoal(this, 10))
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 12.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 16.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
        }
    }
}