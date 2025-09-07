package dev.hybridlabs.aquatic.client.render.armor

import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.item.ManglerfishArmorItem
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.model.DefaultedItemGeoModel
import software.bernie.geckolib.renderer.GeoArmorRenderer

class ManglerfishArmorRenderer : GeoArmorRenderer<ManglerfishArmorItem>(
    DefaultedItemGeoModel(ResourceLocation(Constants.MOD_ID, "armor/manglerfish_armor"))
)