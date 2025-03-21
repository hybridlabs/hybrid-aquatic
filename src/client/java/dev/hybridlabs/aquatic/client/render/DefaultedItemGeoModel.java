package dev.hybridlabs.aquatic.client.render;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.IAnimatable;

public class DefaultedItemGeoModel<T extends IAnimatable> extends DefaultedGeoModel<T> {
    public DefaultedItemGeoModel(Identifier assetSubpath) {
        super(assetSubpath);
    }

    protected String subtype() {
        return "item";
    }

    public DefaultedItemGeoModel<T> withAltModel(Identifier altPath) {
        return (DefaultedItemGeoModel)super.withAltModel(altPath);
    }

    public DefaultedItemGeoModel<T> withAltAnimations(Identifier altPath) {
        return (DefaultedItemGeoModel)super.withAltAnimations(altPath);
    }

    public DefaultedItemGeoModel<T> withAltTexture(Identifier altPath) {
        return (DefaultedItemGeoModel)super.withAltTexture(altPath);
    }
}
