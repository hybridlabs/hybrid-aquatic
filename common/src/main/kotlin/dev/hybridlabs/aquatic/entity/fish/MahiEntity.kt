package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.goal.FishJumpGoal
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level

class MahiEntity(entityType: EntityType<out MahiEntity>, world: Level) :
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

    public override fun getDefaultLootTable(): ResourceLocation {
        return ResourceLocation("hybrid-aquatic", "entities/mahi")
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
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.7)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 8.0)
        }
    }
}