package dev.hybridlabs.aquatic.client.render.block.entity;

import dev.hybridlabs.aquatic.block.entity.AnemoneBlockEntity;
import dev.hybridlabs.aquatic.client.model.block.entity.AnemoneBlockEntityModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory.Context;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

class AnemoneBlockEntityRenderer(context: Context) : GeoBlockRenderer<AnemoneBlockEntity>(AnemoneBlockEntityModel()) {

}
