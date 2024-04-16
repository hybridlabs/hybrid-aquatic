package dev.hybridlabs.aquatic.block.wood

import dev.hybridlabs.aquatic.HybridAquatic
import net.fabricmc.fabric.api.`object`.builder.v1.block.type.WoodTypeBuilder
import net.minecraft.block.WoodType
import net.minecraft.util.Identifier

object HybridAquaticWoodTypes {
    val DRIFTWOOD = WoodTypeBuilder.copyOf(WoodType.OAK).build(Identifier(HybridAquatic.MOD_ID, "driftwood"), HybridAquaticBlockSetTypes.DRIFTWOOD)
}
