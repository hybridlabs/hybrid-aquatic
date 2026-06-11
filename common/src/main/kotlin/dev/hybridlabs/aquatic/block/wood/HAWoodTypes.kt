package dev.hybridlabs.aquatic.block.wood

import dev.hybridlabs.aquatic.CommonClass
import net.minecraft.world.level.block.state.properties.WoodType

object HAWoodTypes {
    val DRIFTWOOD: WoodType = WoodType.register(
        WoodType(
            CommonClass.locate("driftwood").toString(),
            HABlockSetTypes.DRIFTWOOD)
    )
}