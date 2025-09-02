package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level

class BettaEntity(entityType: EntityType<out BettaEntity>, world: Level) :
    HybridAquaticFishEntity(
        entityType, world, variants = hashMapOf(
            "black" to FishVariant.biomeVariant(
                "black", listOf(HybridAquaticBiomeTags.SWAMP, HybridAquaticBiomeTags.MARSHES),
                ignore = listOf(FishVariant.Ignore.MODEL, FishVariant.Ignore.ANIMATION)
            ),
            "blue" to FishVariant.biomeVariant(
                "blue", listOf(HybridAquaticBiomeTags.SWAMP, HybridAquaticBiomeTags.MARSHES),
                ignore = listOf(FishVariant.Ignore.MODEL, FishVariant.Ignore.ANIMATION)
            ),
            "blue_yellow" to FishVariant.biomeVariant(
                "blue_yellow", listOf(HybridAquaticBiomeTags.SWAMP, HybridAquaticBiomeTags.MARSHES),
                ignore = listOf(FishVariant.Ignore.MODEL, FishVariant.Ignore.ANIMATION)
            ),
            "green" to FishVariant.biomeVariant(
                "green", listOf(HybridAquaticBiomeTags.SWAMP, HybridAquaticBiomeTags.MARSHES),
                ignore = listOf(FishVariant.Ignore.MODEL, FishVariant.Ignore.ANIMATION)
            ),
            "pink" to FishVariant.biomeVariant(
                "pink", listOf(HybridAquaticBiomeTags.SWAMP, HybridAquaticBiomeTags.MARSHES),
                ignore = listOf(FishVariant.Ignore.MODEL, FishVariant.Ignore.ANIMATION)
            ),
            "red" to FishVariant.biomeVariant(
                "red", listOf(HybridAquaticBiomeTags.SWAMP, HybridAquaticBiomeTags.MARSHES),
                ignore = listOf(FishVariant.Ignore.MODEL, FishVariant.Ignore.ANIMATION)
            ),
            "red_blue" to FishVariant.biomeVariant(
                "red_blue", listOf(HybridAquaticBiomeTags.SWAMP, HybridAquaticBiomeTags.MARSHES),
                ignore = listOf(FishVariant.Ignore.MODEL, FishVariant.Ignore.ANIMATION)
            ),
            "white" to FishVariant.biomeVariant(
                "white", listOf(HybridAquaticBiomeTags.SWAMP, HybridAquaticBiomeTags.MARSHES),
                ignore = listOf(FishVariant.Ignore.MODEL, FishVariant.Ignore.ANIMATION)
            ),
        ),
        listOf(
            HybridAquaticEntityTags.NONE
        ),
        listOf(
            HybridAquaticEntityTags.MEDIUM_PREY,
            HybridAquaticEntityTags.LARGE_PREY,
            HybridAquaticEntityTags.SHARK
        )
    ) {

    public override fun getDefaultLootTable(): ResourceLocation {
        return ResourceLocation("hybrid-aquatic", "entities/betta")
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 2
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