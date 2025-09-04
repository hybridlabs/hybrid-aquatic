package dev.hybridlabs.aquatic.enchantment

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.EntityType
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.Items

class LiveCatchEnchantment : Enchantment(Rarity.RARE, EnchantmentTarget.FISHING_ROD, arrayOf(EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND)) {
    override fun getMinPower(level: Int): Int {
        return 25
    }

    override fun getMaxLevel(): Int {
        return 1
    }

    companion object {
        //TODO: Probably need to move all of this into datapacks so other people can modify this list.
        val ITEM_TO_ENTITYTYPE = hashMapOf(
            Items.COD to EntityType.COD,
            Items.SALMON to EntityType.SALMON,
            Items.TROPICAL_FISH to EntityType.TROPICAL_FISH,
            Items.PUFFERFISH to EntityType.PUFFERFISH,
            HybridAquaticItems.PIRANHA to HybridAquaticEntityTypes.PIRANHA,
            HybridAquaticItems.ANGLERFISH to HybridAquaticEntityTypes.ANGLERFISH,
            HybridAquaticItems.BARRELEYE to HybridAquaticEntityTypes.BARRELEYE,
            HybridAquaticItems.DRAGONFISH to HybridAquaticEntityTypes.DRAGONFISH,
            HybridAquaticItems.BLUE_TANG to HybridAquaticEntityTypes.SURGEONFISH,
            HybridAquaticItems.SURGEONFISH_SOHAL to HybridAquaticEntityTypes.SURGEONFISH,
            HybridAquaticItems.SURGEONFISH_ORANGESHOULDER to HybridAquaticEntityTypes.SURGEONFISH,
            HybridAquaticItems.SURGEONFISH_LINED to HybridAquaticEntityTypes.SURGEONFISH,
            HybridAquaticItems.POWDER_BLUE_TANG to HybridAquaticEntityTypes.SURGEONFISH,
            HybridAquaticItems.UNICORNFISH to HybridAquaticEntityTypes.SURGEONFISH,
            HybridAquaticItems.YELLOW_TANG to HybridAquaticEntityTypes.SURGEONFISH,
            HybridAquaticItems.CLOWNFISH to HybridAquaticEntityTypes.CLOWNFISH,
            HybridAquaticItems.BOXFISH to HybridAquaticEntityTypes.BOXFISH,
            HybridAquaticItems.TIGER_BARB to HybridAquaticEntityTypes.TIGER_BARB,
            HybridAquaticItems.BETTA to HybridAquaticEntityTypes.BETTA,
            HybridAquaticItems.NEON_TETRA to HybridAquaticEntityTypes.TETRA,
            HybridAquaticItems.DANIO to HybridAquaticEntityTypes.DANIO,
            HybridAquaticItems.DISCUS to HybridAquaticEntityTypes.DISCUS,
            HybridAquaticItems.FLASHLIGHT_FISH to HybridAquaticEntityTypes.FLASHLIGHT_FISH,
            HybridAquaticItems.GOURAMI to HybridAquaticEntityTypes.GOURAMI,
            HybridAquaticItems.KOI to HybridAquaticEntityTypes.CARP,
            HybridAquaticItems.GOLDFISH to HybridAquaticEntityTypes.GOLDFISH,
            HybridAquaticItems.STONEFISH to HybridAquaticEntityTypes.STONEFISH,
            HybridAquaticItems.SEAHORSE to HybridAquaticEntityTypes.SEAHORSE,
            HybridAquaticItems.BLOWFISH to HybridAquaticEntityTypes.BLOWFISH,
            HybridAquaticItems.MACKEREL to HybridAquaticEntityTypes.MACKEREL,
            HybridAquaticItems.FLYING_FISH to HybridAquaticEntityTypes.FLYING_FISH,

            HybridAquaticItems.RATFISH to HybridAquaticEntityTypes.RATFISH,
            HybridAquaticItems.TRIGGERFISH to HybridAquaticEntityTypes.TRIGGERFISH,
            HybridAquaticItems.PARROTFISH to HybridAquaticEntityTypes.PARROTFISH,
            HybridAquaticItems.LIONFISH to HybridAquaticEntityTypes.LIONFISH,
            HybridAquaticItems.ROCKFISH to HybridAquaticEntityTypes.ROCKFISH,
            HybridAquaticItems.SEA_BASS to HybridAquaticEntityTypes.SEA_BASS,
            HybridAquaticItems.BLUE_SPOTTED_STINGRAY to HybridAquaticEntityTypes.STINGRAY,
            HybridAquaticItems.SPOTTED_EAGLE_RAY to HybridAquaticEntityTypes.STINGRAY,
            HybridAquaticItems.MORAY_EEL to HybridAquaticEntityTypes.MORAY_EEL,
            HybridAquaticItems.NEEDLEFISH to HybridAquaticEntityTypes.NEEDLEFISH,
            HybridAquaticItems.COELACANTH to HybridAquaticEntityTypes.COELACANTH,
            HybridAquaticItems.GOLDEN_DORADO to HybridAquaticEntityTypes.GOLDEN_DORADO,
            HybridAquaticItems.SQUIRRELFISH to HybridAquaticEntityTypes.SQUIRRELFISH,

            HybridAquaticItems.MAHI to HybridAquaticEntityTypes.MAHI,
            HybridAquaticItems.YELLOWFIN_TUNA to HybridAquaticEntityTypes.TUNA,
            HybridAquaticItems.BLUEFIN_TUNA to HybridAquaticEntityTypes.TUNA,
            HybridAquaticItems.OPAH to HybridAquaticEntityTypes.OPAH,
            HybridAquaticItems.SUNFISH to HybridAquaticEntityTypes.SUNFISH,
            HybridAquaticItems.OARFISH to HybridAquaticEntityTypes.OARFISH,
        )
    }
}