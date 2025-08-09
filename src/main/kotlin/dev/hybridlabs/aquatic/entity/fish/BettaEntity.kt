package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.world.World

class BettaEntity(entityType: EntityType<out BettaEntity>, world: World) :
    HybridAquaticFishEntity(entityType, world, variants = hashMapOf(
        "black" to FishVariant.biomeVariant("black", HybridAquaticBiomeTags.SWAMPLAND,
            ignore = listOf(FishVariant.Ignore.MODEL, FishVariant.Ignore.ANIMATION)),
        "blue" to FishVariant.biomeVariant("blue", HybridAquaticBiomeTags.SWAMPLAND,
            ignore = listOf(FishVariant.Ignore.MODEL, FishVariant.Ignore.ANIMATION)),
        "blue_yellow" to FishVariant.biomeVariant("blue_yellow", HybridAquaticBiomeTags.SWAMPLAND,
            ignore = listOf(FishVariant.Ignore.MODEL, FishVariant.Ignore.ANIMATION)),
        "green" to FishVariant.biomeVariant("green", HybridAquaticBiomeTags.SWAMPLAND,
            ignore = listOf(FishVariant.Ignore.MODEL, FishVariant.Ignore.ANIMATION)),
        "pink" to FishVariant.biomeVariant("pink", HybridAquaticBiomeTags.SWAMPLAND,
            ignore = listOf(FishVariant.Ignore.MODEL, FishVariant.Ignore.ANIMATION)),
        "red" to FishVariant.biomeVariant("red", HybridAquaticBiomeTags.SWAMPLAND,
            ignore = listOf(FishVariant.Ignore.MODEL, FishVariant.Ignore.ANIMATION)),
        "red_blue" to FishVariant.biomeVariant("red_blue", HybridAquaticBiomeTags.SWAMPLAND,
            ignore = listOf(FishVariant.Ignore.MODEL, FishVariant.Ignore.ANIMATION)),
        "white" to FishVariant.biomeVariant("white", HybridAquaticBiomeTags.SWAMPLAND,
            ignore = listOf(FishVariant.Ignore.MODEL, FishVariant.Ignore.ANIMATION)),
        ),
        HybridAquaticEntityTags.NONE, HybridAquaticEntityTags.NONE) {

    override fun getLimitPerChunk(): Int {
        return 2
    }
    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 2.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.7)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 12.0)
        }
    }
}
