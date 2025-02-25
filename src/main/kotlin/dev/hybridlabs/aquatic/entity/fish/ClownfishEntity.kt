package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.util.Identifier
import net.minecraft.world.World

class ClownfishEntity(entityType: EntityType<out ClownfishEntity>, world: World) :
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

    override fun getLimitPerChunk(): Int {
        return 2
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 3.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 4.0)
        }
    }
}