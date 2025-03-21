package dev.hybridlabs.aquatic.client.render.armor

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.client.render.DefaultedItemGeoModel
import dev.hybridlabs.aquatic.item.DivingArmorItem
import net.minecraft.util.Identifier
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer

class DivingArmorRenderer : GeoArmorRenderer<DivingArmorItem>(
    DefaultedItemGeoModel(Identifier(HybridAquatic.MOD_ID, "armor/diving_armor"))
)
