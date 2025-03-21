package dev.hybridlabs.aquatic.client.render.armor

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.item.MoonJellyfishArmorItem
import net.minecraft.util.Identifier
import software.bernie.geckolib3.model.DefaultedItemGeoModel
import software.bernie.geckolib3.renderer.GeoArmorRenderer

class MoonJellyfishArmorRenderer : GeoArmorRenderer<MoonJellyfishArmorItem>(
    DefaultedItemGeoModel(Identifier(HybridAquatic.MOD_ID, "armor/moon_jellyfish_armor"))
)
