package dev.hybridlabs.aquatic.client.render.armor

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.item.DivingArmorItem
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.model.DefaultedItemGeoModel
import software.bernie.geckolib.renderer.GeoArmorRenderer

class TurtleArmorRenderer : GeoArmorRenderer<DivingArmorItem>(
    DefaultedItemGeoModel(ResourceLocation(HybridAquatic.MOD_ID, "armor/turtle_armor"))
)