import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.item.TurtleArmorItem
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.model.DefaultedItemGeoModel
import software.bernie.geckolib.renderer.GeoArmorRenderer

class TurtleArmorRenderer : GeoArmorRenderer<TurtleArmorItem>(
    DefaultedItemGeoModel(ResourceLocation(Constants.MOD_ID, "armor/turtle_armor"))
)