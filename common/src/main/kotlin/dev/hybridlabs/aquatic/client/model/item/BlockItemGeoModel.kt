package dev.hybridlabs.aquatic.client.model.item

import net.minecraft.world.item.Item
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.resources.Identifier
import com.geckolib.animatable.GeoAnimatable
import com.geckolib.model.GeoModel
import com.geckolib.renderer.base.GeoRenderState

/**
 * Adapts a block entity's [GeoModel] to the item form of that block, so the item in a hand or in
 * an inventory slot uses the same model, texture and animation as the placed block.
 */
class BlockItemGeoModel<T : Item, B>(
    private val delegate: GeoModel<B>,
    private val blockEntity: B,
) : GeoModel<T>() where T : GeoAnimatable, B : BlockEntity, B : GeoAnimatable {

    override fun getModelResource(renderState: GeoRenderState): Identifier =
        delegate.getModelResource(renderState)

    override fun getTextureResource(renderState: GeoRenderState): Identifier =
        delegate.getTextureResource(renderState)

    override fun getAnimationResource(animatable: T): Identifier =
        delegate.getAnimationResource(blockEntity)
}
