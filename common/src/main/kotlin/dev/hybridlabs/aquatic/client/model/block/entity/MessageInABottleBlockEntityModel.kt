package dev.hybridlabs.aquatic.client.model.block.entity

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.block.MessageInABottleBlock.Variant
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.model.GeoModel

class MessageInABottleBlockEntityModel : GeoModel<MessageInABottleBlockEntity>() {
    override fun getModelResource(blockEntity: MessageInABottleBlockEntity): ResourceLocation {
        return VARIANT_MODELS[blockEntity.variant] ?: throw NotImplementedError("Model not registered")
    }

    override fun getTextureResource(blockEntity: MessageInABottleBlockEntity): ResourceLocation {
        return VARIANT_TEXTURES[blockEntity.variant] ?: throw NotImplementedError("Model not registered")
    }

    override fun getAnimationResource(blockEntity: MessageInABottleBlockEntity): ResourceLocation {
        return WATER_BOB_ANIMATION_ID
    }

    override fun getRenderType(animatable: MessageInABottleBlockEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }

    companion object {
        val VARIANT_MODELS = mapOf(
            Variant.BOTTLE to CommonClass.locate("geo/entity/block/message_in_a_bottle/message_in_a_bottle.geo.json"),
            Variant.JAR to CommonClass.locate("geo/entity/block/message_in_a_bottle/message_in_a_bottle_jar.geo.json"),
            Variant.LONGNECK to CommonClass.locate("geo/entity/block/message_in_a_bottle/message_in_a_bottle_longneck.geo.json"),
        )

        val VARIANT_TEXTURES = mapOf(
            Variant.BOTTLE to CommonClass.locate("textures/entity/block/message_in_a_bottle/message_in_a_bottle.png"),
            Variant.JAR to CommonClass.locate("textures/entity/block/message_in_a_bottle/message_in_a_bottle_jar.png"),
            Variant.LONGNECK to CommonClass.locate("textures/entity/block/message_in_a_bottle/message_in_a_bottle_longneck.png"),
        )

        val WATER_BOB_ANIMATION_ID = CommonClass.locate("animations/water_bob.animation.json")
    }
}
