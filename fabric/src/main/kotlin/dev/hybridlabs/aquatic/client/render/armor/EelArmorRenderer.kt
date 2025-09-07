package dev.hybridlabs.aquatic.client.render.armor

import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.item.EelArmorItem
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.model.DefaultedItemGeoModel
import software.bernie.geckolib.renderer.GeoArmorRenderer

class EelArmorRenderer : GeoArmorRenderer<EelArmorItem>(
    DefaultedItemGeoModel(ResourceLocation(Constants.MOD_ID, "armor/eel_armor"))
)