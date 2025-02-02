package dev.hybridlabs.aquatic.entity.crustacean

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.loot.LootTable
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier
import net.minecraft.world.World

class HermitCrabEntity(entityType: EntityType<out HybridAquaticCrustaceanEntity>, world: World) :
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

    public override fun getLootTableId(): RegistryKey<LootTable> {
        return when (this.variant?.variantName) {
            "skull" -> RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier(HybridAquatic.MOD_ID, "gameplay/hermit_crab_skull"))
            "shell" -> RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier(HybridAquatic.MOD_ID, "gameplay/hermit_crab_shell"))
            else -> super.getLootTableId()
        }
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 3.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 4.0)
                .add(EntityAttributes.GENERIC_ARMOR, 5.0)
                .add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, 5.0)
        }
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}
