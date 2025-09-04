package dev.hybridlabs.aquatic.client.render.armor

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.item.TurtleArmorItem
import net.minecraft.util.Identifier
import software.bernie.geckolib.model.DefaultedItemGeoModel
import software.bernie.geckolib.renderer.GeoArmorRenderer

class TurtleArmorRenderer : GeoArmorRenderer<TurtleArmorItem>(
    DefaultedItemGeoModel(Identifier(HybridAquatic.MOD_ID, "armor/turtle_armor"))
)