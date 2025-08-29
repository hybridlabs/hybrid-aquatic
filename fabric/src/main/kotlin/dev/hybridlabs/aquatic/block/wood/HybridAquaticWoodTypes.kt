package dev.hybridlabs.aquatic.block.wood

import dev.hybridlabs.aquatic.HybridAquatic
import net.fabricmc.fabric.api.`object`.builder.v1.block.type.WoodTypeBuilder
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.state.properties.WoodType

object HybridAquaticWoodTypes {
    val DRIFTWOOD: WoodType = WoodTypeBuilder.copyOf(WoodType.OAK).build(
        ResourceLocation(
            HybridAquatic.MOD_ID,
            "driftwood"
        ), HybridAquaticBlockSetTypes.DRIFTWOOD
    )
}
