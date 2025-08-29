package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.level.Level

class StingrayEntity(entityType: EntityType<out StingrayEntity>, world: Level) :
    HybridAquaticFishEntity(
        entityType, world, variants = hashMapOf(
            "spotted_eagle" to FishVariant.biomeVariant(
                "spotted_eagle",
                listOf(HybridAquaticBiomeTags.TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF)
            ),
            "blue_spotted" to FishVariant.biomeVariant("blue_spotted", listOf(HybridAquaticBiomeTags.REEF)),
        ),
        listOf(HybridAquaticEntityTags.NONE), listOf(HybridAquaticEntityTags.NONE)
    ) {

    public override fun getDefaultLootTable(): ResourceLocation {
        return when (this.variant?.variantName) {
            "blue_spotted" -> ResourceLocation("hybrid-aquatic", "gameplay/blue_spotted_stingray")
            "spotted_eagle" -> ResourceLocation("hybrid-aquatic", "gameplay/spotted_eagle_ray")
            else -> super.getDefaultLootTable()
        }
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, HurtByTargetGoal(this))
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 5.0)
                .add(Attributes.FOLLOW_RANGE, 8.0)
        }
    }
}