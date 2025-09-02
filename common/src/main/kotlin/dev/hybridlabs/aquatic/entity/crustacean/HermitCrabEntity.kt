package dev.hybridlabs.aquatic.entity.crustacean

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level

class HermitCrabEntity(entityType: EntityType<out HybridAquaticCrustaceanEntity>, world: Level) :
    HybridAquaticCrustaceanEntity(
        entityType, world, false, variants = hashMapOf(
            "shell" to CrustaceanVariant.biomeVariant(
                "shell", HybridAquaticBiomeTags.SANDY_BEACHES,
                ignore = listOf(CrustaceanVariant.Ignore.ANIMATION)
            ),
            "skull" to CrustaceanVariant.biomeVariant(
                "skull", HybridAquaticBiomeTags.SANDY_BEACHES,
                ignore = listOf(CrustaceanVariant.Ignore.ANIMATION)
            ),
        )
    ) {

    public override fun getDefaultLootTable(): ResourceLocation {
        return when (this.variant?.variantName) {
            "skull" -> ResourceLocation("hybrid-aquatic", "gameplay/hermit_crab_skull")
            "shell" -> ResourceLocation("hybrid-aquatic", "gameplay/hermit_crab_shell")
            else -> super.getDefaultLootTable()
        }
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
                .add(Attributes.ARMOR, 5.0)
                .add(Attributes.ARMOR_TOUGHNESS, 5.0)
        }
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}