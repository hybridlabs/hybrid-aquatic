package dev.hybridlabs.aquatic.client.render;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.provider.GeoModelProvider;

public abstract class DefaultedGeoModel<T extends IAnimatable> extends GeoModelProvider<T> {
    private Identifier modelPath;
    private Identifier texturePath;
    private Identifier animationsPath;

    public DefaultedGeoModel(Identifier assetSubpath) {
        this.modelPath = this.buildFormattedModelPath(assetSubpath);
        this.texturePath = this.buildFormattedTexturePath(assetSubpath);
        this.animationsPath = this.buildFormattedAnimationPath(assetSubpath);
    }

    public DefaultedGeoModel<T> withAltModel(Identifier altPath) {
        this.modelPath = this.buildFormattedModelPath(altPath);
        return this;
    }

    public DefaultedGeoModel<T> withAltAnimations(Identifier altPath) {
        this.animationsPath = this.buildFormattedAnimationPath(altPath);
        return this;
    }

    public DefaultedGeoModel<T> withAltTexture(Identifier altPath) {
        this.texturePath = this.buildFormattedTexturePath(altPath);
        return this;
    }

    public Identifier buildFormattedModelPath(Identifier basePath) {
        String var10001 = this.subtype();
        return new Identifier(basePath.getNamespace(), "geo/" + var10001 + "/" + basePath.getPath() + ".geo.json");
    }

    public Identifier buildFormattedAnimationPath(Identifier basePath) {
        String var10001 = this.subtype();
        return new Identifier(basePath.getNamespace(), "animations/" + var10001 + "/" + basePath.getPath() + ".animation.json");
    }

    public Identifier buildFormattedTexturePath(Identifier basePath) {
        String var10001 = this.subtype();
        return new Identifier(basePath.getNamespace(), "textures/" + var10001 + "/" + basePath.getPath() + ".png");
    }

    protected abstract String subtype();

    @Override
    public Identifier getModelResource(T animatable) {
        return this.modelPath;
    }

    @Override
    public Identifier getTextureResource(T animatable) {
        return this.texturePath;
    }

    public Identifier getAnimationResource(T animatable) {
        return this.animationsPath;
    }
}
