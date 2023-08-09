package dev.hybridlabs.aquatic.client.model.block.entity

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.MessageInABottleBlock.Variant
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier
import software.bernie.geckolib.model.GeoModel

class MessageInABottleBlockEntityModel : GeoModel<MessageInABottleBlockEntity>() {
    override fun getModelResource(blockEntity: MessageInABottleBlockEntity): Identifier {
        return VARIANT_MODELS[blockEntity.variant] ?: throw NotImplementedError("Model not registered")
    }

    override fun getTextureResource(blockEntity: MessageInABottleBlockEntity): Identifier {
        return VARIANT_TEXTURES[blockEntity.variant] ?: throw NotImplementedError("Model not registered")
    }

    override fun getAnimationResource(blockEntity: MessageInABottleBlockEntity): Identifier {
        return WATER_BOB_ANIMATION_ID
    }

    override fun getRenderType(animatable: MessageInABottleBlockEntity, texture: Identifier): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }

    companion object {
        val VARIANT_MODELS = mapOf(
            Variant.BOTTLE to Identifier(HybridAquatic.MOD_ID, "geo/entity/block/message_in_a_bottle/message_in_a_bottle.geo.json"),
            Variant.JAR to Identifier(HybridAquatic.MOD_ID, "geo/entity/block/message_in_a_bottle/message_in_a_bottle_jar.geo.json"),
            Variant.LONGNECK to Identifier(HybridAquatic.MOD_ID, "geo/entity/block/message_in_a_bottle/message_in_a_bottle_longneck.geo.json"),
        )

        val VARIANT_TEXTURES = mapOf(
            Variant.BOTTLE to Identifier(HybridAquatic.MOD_ID, "textures/entity/block/message_in_a_bottle/message_in_a_bottle.png"),
            Variant.JAR to Identifier(HybridAquatic.MOD_ID, "textures/entity/block/message_in_a_bottle/message_in_a_bottle_jar.png"),
            Variant.LONGNECK to Identifier(HybridAquatic.MOD_ID, "textures/entity/block/message_in_a_bottle/message_in_a_bottle_longneck.png"),
        )

        val WATER_BOB_ANIMATION_ID = Identifier(HybridAquatic.MOD_ID, "animations/water_bob.animation.json")
    }
}
