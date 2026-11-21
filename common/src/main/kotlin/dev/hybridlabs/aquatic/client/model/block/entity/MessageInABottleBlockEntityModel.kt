package dev.hybridlabs.aquatic.client.model.block.entity

import software.bernie.geckolib.constant.DataTickets

import software.bernie.geckolib.renderer.base.BoneSnapshots

import dev.hybridlabs.hapi.client.model.HABoneAdjustingModel

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.client.model.getVariant
import software.bernie.geckolib.renderer.base.GeoRenderState
import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import dev.hybridlabs.aquatic.block.MessageInABottleBlock.Variant
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity
import net.minecraft.client.renderer.rendertype.RenderType
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.resources.Identifier
import net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED
import software.bernie.geckolib.animation.state.AnimationTest
import software.bernie.geckolib.model.GeoModel

class MessageInABottleBlockEntityModel : GeoModel<MessageInABottleBlockEntity>() {
    @Deprecated("Deprecated in Java")
    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return VARIANT_MODELS[renderState.getVariant(MessageInABottleBlock.Variant.entries)!!] ?: throw NotImplementedError("Model not registered")
    }

    @Deprecated("Deprecated in Java")
    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return VARIANT_TEXTURES[renderState.getVariant(MessageInABottleBlock.Variant.entries)!!] ?: throw NotImplementedError("Model not registered")
    }

    override fun getAnimationResource(blockEntity: MessageInABottleBlockEntity): Identifier {
        return WATER_BOB_ANIMATION_ID
    }


    companion object {
        val VARIANT_MODELS = mapOf(
            Variant.BOTTLE to CommonClass.locate("entity/block/message_in_a_bottle/message_in_a_bottle"),
            Variant.JAR to CommonClass.locate("entity/block/message_in_a_bottle/message_in_a_jar"),
            Variant.LONGNECK to CommonClass.locate("entity/block/message_in_a_bottle/message_in_a_longneck_bottle"),
            Variant.POTION to CommonClass.locate("entity/block/message_in_a_bottle/message_in_a_potion_bottle"),
            Variant.WINE to CommonClass.locate("entity/block/message_in_a_bottle/message_in_a_wine_bottle"),
        )

        val VARIANT_TEXTURES = mapOf(
            Variant.BOTTLE to CommonClass.locate("textures/entity/block/message_in_a_bottle/message_in_a_bottle.png"),
            Variant.JAR to CommonClass.locate("textures/entity/block/message_in_a_bottle/message_in_a_jar.png"),
            Variant.LONGNECK to CommonClass.locate("textures/entity/block/message_in_a_bottle/message_in_a_longneck_bottle.png"),
            Variant.POTION to CommonClass.locate("textures/entity/block/message_in_a_bottle/message_in_a_potion_bottle.png"),
            Variant.WINE to CommonClass.locate("textures/entity/block/message_in_a_bottle/message_in_a_wine_bottle.png"),
        )

        val WATER_BOB_ANIMATION_ID: Identifier = CommonClass.locate("water_bob")
    }
}