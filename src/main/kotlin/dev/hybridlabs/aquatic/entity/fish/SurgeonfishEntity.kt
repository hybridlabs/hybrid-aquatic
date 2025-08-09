package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.world.World

class SurgeonfishEntity(entityType: EntityType<out SurgeonfishEntity>, world: World) :
    HybridAquaticFishEntity(entityType, world, variants = hashMapOf(
        "sohal" to FishVariant.biomeVariant("sohal", HybridAquaticBiomeTags.WARM_OCEANS),
        "lined" to FishVariant.biomeVariant("lined", HybridAquaticBiomeTags.WARM_OCEANS),
        "orangeshoulder" to FishVariant.biomeVariant("orangeshoulder", HybridAquaticBiomeTags.WARM_OCEANS),
        "unicornfish" to FishVariant.biomeVariant("unicornfish", HybridAquaticBiomeTags.WARM_OCEANS),
        "powder_blue_tang" to FishVariant.biomeVariant("powder_blue_tang", HybridAquaticBiomeTags.REEF),
        "yellow_tang" to FishVariant.biomeVariant("yellow_tang", HybridAquaticBiomeTags.REEF),
        "blue_tang" to FishVariant.biomeVariant("blue_tang", HybridAquaticBiomeTags.REEF)),
        HybridAquaticEntityTags.NONE, HybridAquaticEntityTags.SURGEONFISH_PREDATOR) {

    override fun getLimitPerChunk(): Int {
        return 4
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 12.0)
        }
    }
}
