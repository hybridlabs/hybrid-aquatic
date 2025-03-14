package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.util.Identifier
import net.minecraft.world.World

class SurgeonfishEntity(entityType: EntityType<out SurgeonfishEntity>, world: World) :
    HybridAquaticFishEntity(
        entityType, world, variants = hashMapOf(
            "sohal" to FishVariant.biomeVariant("sohal", listOf(HybridAquaticBiomeTags.REEF)),
            "lined" to FishVariant.biomeVariant("lined", listOf(HybridAquaticBiomeTags.REEF)),
            "orangeshoulder" to FishVariant.biomeVariant("orangeshoulder", listOf(HybridAquaticBiomeTags.REEF)),
            "unicornfish" to FishVariant.biomeVariant("unicornfish", listOf(HybridAquaticBiomeTags.REEF)),
            "powder_blue_tang" to FishVariant.biomeVariant("powder_blue_tang", listOf(HybridAquaticBiomeTags.REEF)),
            "yellow_tang" to FishVariant.biomeVariant("yellow_tang", listOf(HybridAquaticBiomeTags.REEF)),
            "blue_tang" to FishVariant.biomeVariant("blue_tang", listOf(HybridAquaticBiomeTags.REEF))
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

    public override fun getLootTableId(): Identifier {
        return when (this.variant?.variantName) {
            "blue_tang" -> Identifier("hybrid-aquatic", "gameplay/surgeonfish_blue_tang")
            "yellow_tang" -> Identifier("hybrid-aquatic", "gameplay/surgeonfish_yellow_tang")
            "powder_blue_tang" -> Identifier("hybrid-aquatic", "gameplay/surgeonfish_powder_blue_tang")
            "sohal" -> Identifier("hybrid-aquatic", "gameplay/surgeonfish_sohal")
            "orangeshoulder" -> Identifier("hybrid-aquatic", "gameplay/surgeonfish_orangeshoulder")
            "lined" -> Identifier("hybrid-aquatic", "gameplay/surgeonfish_lined")
            "unicornfish" -> Identifier("hybrid-aquatic", "gameplay/surgeonfish_unicornfish")
            else -> super.getLootTableId()
        }
    }

    override fun getLimitPerChunk(): Int {
        return 3
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 3.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 4.0)
        }
    }
}