package dev.hybridlabs.aquatic.block.wood

import dev.hybridlabs.aquatic.Constants
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.state.properties.WoodType

object HybridAquaticWoodTypes {
    val DRIFTWOOD: WoodType = WoodType.register(
        WoodType(
            ResourceLocation( Constants.MOD_ID, "driftwood" ).toString(),
            HybridAquaticBlockSetTypes.DRIFTWOOD)
    )
}
