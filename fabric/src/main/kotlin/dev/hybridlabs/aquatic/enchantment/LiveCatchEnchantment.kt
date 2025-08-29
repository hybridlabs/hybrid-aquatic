package dev.hybridlabs.aquatic.enchantment

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.Items
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory

class LiveCatchEnchantment :
    Enchantment(Rarity.RARE, EnchantmentCategory.FISHING_ROD, arrayOf(EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND)) {
    override fun getMinCost(level: Int): Int {
        return 25
    }

    override fun getMaxLevel(): Int {
        return 1
    }

    companion object {
        //TODO: Probably need to move all of this into data so other people can modify this list. Not very important
        val ITEM_TO_ENTITYTYPE = hashMapOf(
            Items.COD to EntityType.COD,
            Items.SALMON to EntityType.SALMON,
            Items.TROPICAL_FISH to EntityType.TROPICAL_FISH,
            Items.PUFFERFISH to EntityType.PUFFERFISH,
            HybridAquaticItems.PIRANHA.get() to HybridAquaticEntityTypes.PIRANHA.get(),
            HybridAquaticItems.ANGLERFISH.get() to HybridAquaticEntityTypes.ANGLERFISH.get(),
            HybridAquaticItems.BARRELEYE.get() to HybridAquaticEntityTypes.BARRELEYE.get(),
            HybridAquaticItems.DRAGONFISH.get() to HybridAquaticEntityTypes.DRAGONFISH.get(),
            HybridAquaticItems.BLUE_TANG.get() to HybridAquaticEntityTypes.SURGEONFISH.get(),
            HybridAquaticItems.SURGEONFISH_SOHAL.get() to HybridAquaticEntityTypes.SURGEONFISH.get(),
            HybridAquaticItems.SURGEONFISH_ORANGESHOULDER.get() to HybridAquaticEntityTypes.SURGEONFISH.get(),
            HybridAquaticItems.SURGEONFISH_LINED.get() to HybridAquaticEntityTypes.SURGEONFISH.get(),
            HybridAquaticItems.POWDER_BLUE_TANG.get() to HybridAquaticEntityTypes.SURGEONFISH.get(),
            HybridAquaticItems.YELLOW_TANG.get() to HybridAquaticEntityTypes.SURGEONFISH.get(),
            HybridAquaticItems.CLOWNFISH.get() to HybridAquaticEntityTypes.CLOWNFISH.get(),
            HybridAquaticItems.BOXFISH.get() to HybridAquaticEntityTypes.BOXFISH.get(),
            HybridAquaticItems.TIGER_BARB.get() to HybridAquaticEntityTypes.TIGER_BARB.get(),
            HybridAquaticItems.BETTA.get() to HybridAquaticEntityTypes.BETTA.get(),
            HybridAquaticItems.NEON_TETRA.get() to HybridAquaticEntityTypes.TETRA.get(),
            HybridAquaticItems.DANIO.get() to HybridAquaticEntityTypes.DANIO.get(),
            HybridAquaticItems.DISCUS.get() to HybridAquaticEntityTypes.DISCUS.get(),
            HybridAquaticItems.FLASHLIGHT_FISH.get() to HybridAquaticEntityTypes.FLASHLIGHT_FISH.get(),
            HybridAquaticItems.GOURAMI.get() to HybridAquaticEntityTypes.GOURAMI.get(),
            HybridAquaticItems.KOI.get() to HybridAquaticEntityTypes.CARP.get(),
            HybridAquaticItems.GOLDFISH.get() to HybridAquaticEntityTypes.GOLDFISH.get(),
            HybridAquaticItems.STONEFISH.get() to HybridAquaticEntityTypes.STONEFISH.get(),
            HybridAquaticItems.SEAHORSE.get() to HybridAquaticEntityTypes.SEAHORSE.get(),
            HybridAquaticItems.TOADFISH.get() to HybridAquaticEntityTypes.TOADFISH.get(),
            HybridAquaticItems.MACKEREL.get() to HybridAquaticEntityTypes.MACKEREL.get(),
            HybridAquaticItems.FLYING_FISH.get() to HybridAquaticEntityTypes.FLYING_FISH.get(),

            HybridAquaticItems.RATFISH.get() to HybridAquaticEntityTypes.RATFISH.get(),
            HybridAquaticItems.TRIGGERFISH.get() to HybridAquaticEntityTypes.TRIGGERFISH.get(),
            HybridAquaticItems.PARROTFISH.get() to HybridAquaticEntityTypes.PARROTFISH.get(),
            HybridAquaticItems.LIONFISH.get() to HybridAquaticEntityTypes.LIONFISH.get(),
            HybridAquaticItems.ROCKFISH.get() to HybridAquaticEntityTypes.ROCKFISH.get(),
            HybridAquaticItems.SEA_BASS.get() to HybridAquaticEntityTypes.SEA_BASS.get(),
            HybridAquaticItems.BLUE_SPOTTED_STINGRAY.get() to HybridAquaticEntityTypes.STINGRAY.get(),
            HybridAquaticItems.SPOTTED_EAGLE_RAY.get() to HybridAquaticEntityTypes.STINGRAY.get(),
            HybridAquaticItems.MORAY_EEL.get() to HybridAquaticEntityTypes.MORAY_EEL.get(),
            HybridAquaticItems.NEEDLEFISH.get() to HybridAquaticEntityTypes.NEEDLEFISH.get(),
            HybridAquaticItems.COELACANTH.get() to HybridAquaticEntityTypes.COELACANTH.get(),
            HybridAquaticItems.GOLDEN_DORADO.get() to HybridAquaticEntityTypes.GOLDEN_DORADO.get(),
            HybridAquaticItems.SQUIRRELFISH.get() to HybridAquaticEntityTypes.SQUIRRELFISH.get(),

            HybridAquaticItems.MAHI.get() to HybridAquaticEntityTypes.MAHI.get(),
            HybridAquaticItems.YELLOWFIN_TUNA.get() to HybridAquaticEntityTypes.TUNA.get(),
            HybridAquaticItems.BLUEFIN_TUNA.get() to HybridAquaticEntityTypes.TUNA.get(),
            HybridAquaticItems.OPAH.get() to HybridAquaticEntityTypes.OPAH.get(),
            HybridAquaticItems.SUNFISH.get() to HybridAquaticEntityTypes.SUNFISH.get(),
            HybridAquaticItems.OARFISH.get() to HybridAquaticEntityTypes.OARFISH.get(),
        )
    }
}