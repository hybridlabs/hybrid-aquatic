package dev.hybridlabs.aquatic.client.render.block.entity;

import dev.hybridlabs.aquatic.block.entity.AnemoneBlockEntity;
import dev.hybridlabs.aquatic.client.model.block.entity.AnemoneBlockEntityModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class AnemoneBlockEntityRenderer extends GeoBlockRenderer<AnemoneBlockEntity> {
    public AnemoneBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        super(new AnemoneBlockEntityModel());
    }
}
