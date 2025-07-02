package dev.hybridlabs.aquatic.client.render.armor

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.item.ManglerfishArmorItem
import net.minecraft.util.Identifier
import software.bernie.geckolib.model.DefaultedItemGeoModel
import software.bernie.geckolib.renderer.GeoArmorRenderer

class ManglerfishArmorRenderer : GeoArmorRenderer<ManglerfishArmorItem>(
    DefaultedItemGeoModel(Identifier(HybridAquatic.MOD_ID, "armor/manglerfish_armor"))
)