package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.ai.goal.FishJumpGoal
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level

class TunaEntity(entityType: EntityType<out TunaEntity>, world: Level) :
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

    public override fun getDefaultLootTable(): ResourceLocation {
        return when (this.variant?.variantName) {
            "yellowfin" -> ResourceLocation("hybrid-aquatic", "gameplay/yellowfin")
            "bluefin" -> ResourceLocation("hybrid-aquatic", "gameplay/bluefin")
            else -> super.getDefaultLootTable()
        }
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 3
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(5, FishJumpGoal(this, 10))
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.7)
                .add(Attributes.ATTACK_DAMAGE, 5.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 8.0)
        }
    }
}
