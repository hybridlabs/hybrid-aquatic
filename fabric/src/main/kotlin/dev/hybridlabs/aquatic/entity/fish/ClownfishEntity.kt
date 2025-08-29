package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level

class ClownfishEntity(entityType: EntityType<out ClownfishEntity>, world: Level) :
    HybridAquaticFishEntity(entityType, world, variants = hashMapOf(
        "ocellaris" to FishVariant.biomeVariant("ocellaris", listOf(HybridAquaticBiomeTags.REEF),
            ignore = listOf(FishVariant.Ignore.ANIMATION)),
        "percula" to FishVariant.biomeVariant("percula", listOf(HybridAquaticBiomeTags.REEF),
            ignore = listOf(FishVariant.Ignore.ANIMATION)),
        "whiteband" to FishVariant.biomeVariant("whiteband", listOf(HybridAquaticBiomeTags.REEF),
            ignore = listOf(FishVariant.Ignore.ANIMATION)),
        "tomato" to FishVariant.biomeVariant("tomato", listOf(HybridAquaticBiomeTags.REEF),
            ignore = listOf(FishVariant.Ignore.ANIMATION)),
        "cinnamon" to FishVariant.biomeVariant("cinnamon", listOf(HybridAquaticBiomeTags.REEF),
            ignore = listOf(FishVariant.Ignore.ANIMATION)),
        "clarkii" to FishVariant.biomeVariant("clarkii", listOf(HybridAquaticBiomeTags.REEF),
            ignore = listOf(FishVariant.Ignore.ANIMATION)),
        "pink_skunk" to FishVariant.biomeVariant("pink_skunk", listOf(HybridAquaticBiomeTags.REEF),
            ignore = listOf(FishVariant.Ignore.ANIMATION)),
        "orange_skunk" to FishVariant.biomeVariant("orange_skunk", listOf(HybridAquaticBiomeTags.REEF),
            ignore = listOf(FishVariant.Ignore.ANIMATION)),
        ),
        listOf(
            HybridAquaticEntityTags.NONE),
        listOf(
            HybridAquaticEntityTags.MEDIUM_PREY,
            HybridAquaticEntityTags.LARGE_PREY,
            HybridAquaticEntityTags.SHARK)) {

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    override fun getDefaultLootTable(): ResourceLocation {
        return ResourceLocation("hybrid-aquatic", "entities/clownfish")
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }
    }
}