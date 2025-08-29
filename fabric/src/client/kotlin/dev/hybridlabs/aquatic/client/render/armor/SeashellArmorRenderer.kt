package dev.hybridlabs.aquatic.client.render.armor

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.item.DivingArmorItem
import net.minecraft.util.Identifier
import software.bernie.geckolib.model.DefaultedItemGeoModel
import software.bernie.geckolib.renderer.GeoArmorRenderer

class SeashellArmorRenderer : GeoArmorRenderer<DivingArmorItem>(
    DefaultedItemGeoModel(Identifier(HybridAquatic.MOD_ID, "armor/seashell_armor"))
)