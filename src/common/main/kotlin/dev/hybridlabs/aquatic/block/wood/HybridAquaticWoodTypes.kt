package dev.hybridlabs.aquatic.block.wood

import dev.hybridlabs.aquatic.CommonClass
import net.minecraft.world.level.block.state.properties.WoodType

object HybridAquaticWoodTypes {
    val DRIFTWOOD: WoodType = WoodType.register(
        WoodType(
            CommonClass.locate("driftwood").toString(),
            HybridAquaticBlockSetTypes.DRIFTWOOD)
    )
}
