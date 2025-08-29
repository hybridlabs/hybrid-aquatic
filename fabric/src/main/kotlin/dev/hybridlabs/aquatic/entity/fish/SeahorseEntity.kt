package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level

class SeahorseEntity(entityType: EntityType<out SeahorseEntity>, world: Level) :
    HybridAquaticFishEntity(
        entityType, world, variants = hashMapOf(
            "common" to FishVariant.biomeVariant(
                "common", listOf(HybridAquaticBiomeTags.REEF),
                ignore = listOf(FishVariant.Ignore.ANIMATION)
            ),
            "big_belly" to FishVariant.biomeVariant(
                "big_belly", listOf(HybridAquaticBiomeTags.REEF),
                ignore = listOf(FishVariant.Ignore.ANIMATION)
            ),
            "thorny" to FishVariant.biomeVariant(
                "thorny", listOf(HybridAquaticBiomeTags.REEF),
                ignore = listOf(FishVariant.Ignore.ANIMATION)
            ),
            "pygmy" to FishVariant.biomeVariant(
                "pygmy", listOf(HybridAquaticBiomeTags.REEF),
                ignore = listOf(FishVariant.Ignore.ANIMATION)
            ),
        ),
        listOf(
            HybridAquaticEntityTags.NONE
        ),
        listOf(
            HybridAquaticEntityTags.SMALL_PREY,
            HybridAquaticEntityTags.MEDIUM_PREY,
            HybridAquaticEntityTags.LARGE_PREY,
            HybridAquaticEntityTags.CEPHALOPOD,
            HybridAquaticEntityTags.SHARK
        )
    ) {

    public override fun getDefaultLootTable(): ResourceLocation {
        return ResourceLocation("hybrid-aquatic", "entities/seahorse")
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 12.0)
        }
    }
}