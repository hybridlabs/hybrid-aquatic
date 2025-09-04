package dev.hybridlabs.aquatic.client.renderer.armor

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.item.MoonJellyfishArmorItem
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.model.DefaultedItemGeoModel
import software.bernie.geckolib.renderer.GeoArmorRenderer

class MoonJellyfishArmorRenderer : GeoArmorRenderer<MoonJellyfishArmorItem>(
    DefaultedItemGeoModel(ResourceLocation(Constants.MOD_ID, "armor/moon_jellyfish_armor"))
)