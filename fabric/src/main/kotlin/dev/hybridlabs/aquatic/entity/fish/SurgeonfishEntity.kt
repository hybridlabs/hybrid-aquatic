package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level

class SurgeonfishEntity(entityType: EntityType<out SurgeonfishEntity>, world: Level) :
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

    public override fun getDefaultLootTable(): ResourceLocation {
        return when (this.variant?.variantName) {
            "blue_tang" -> ResourceLocation("hybrid-aquatic", "gameplay/surgeonfish_blue_tang")
            "yellow_tang" -> ResourceLocation("hybrid-aquatic", "gameplay/surgeonfish_yellow_tang")
            "powder_blue_tang" -> ResourceLocation("hybrid-aquatic", "gameplay/surgeonfish_powder_blue_tang")
            "sohal" -> ResourceLocation("hybrid-aquatic", "gameplay/surgeonfish_sohal")
            "orangeshoulder" -> ResourceLocation("hybrid-aquatic", "gameplay/surgeonfish_orangeshoulder")
            "lined" -> ResourceLocation("hybrid-aquatic", "gameplay/surgeonfish_lined")
            "unicornfish" -> ResourceLocation("hybrid-aquatic", "gameplay/surgeonfish_unicornfish")
            else -> super.getDefaultLootTable()
        }
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 3
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }
    }
}