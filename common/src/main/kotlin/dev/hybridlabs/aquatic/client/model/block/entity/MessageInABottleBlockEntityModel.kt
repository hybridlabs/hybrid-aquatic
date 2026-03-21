package dev.hybridlabs.aquatic.client.model.block.entity

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.block.MessageInABottleBlock.Variant
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.model.GeoModel

class MessageInABottleBlockEntityModel : GeoModel<MessageInABottleBlockEntity>() {
    @Deprecated("Deprecated in Java")
    override fun getModelResource(blockEntity: MessageInABottleBlockEntity): ResourceLocation {
        return VARIANT_MODELS[blockEntity.variant] ?: throw NotImplementedError("Model not registered")
    }

    @Deprecated("Deprecated in Java")
    override fun getTextureResource(blockEntity: MessageInABottleBlockEntity): ResourceLocation {
        return VARIANT_TEXTURES[blockEntity.variant] ?: throw NotImplementedError("Model not registered")
    }

    override fun getAnimationResource(blockEntity: MessageInABottleBlockEntity): ResourceLocation? {
        return WATER_BOB_ANIMATION_ID
    }

    override fun getRenderType(animatable: MessageInABottleBlockEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }

    override fun setCustomAnimations(
        animatable: MessageInABottleBlockEntity,
        instanceId: Long,
        animationState: AnimationState<MessageInABottleBlockEntity>
    ) {
        if (animatable.blockState.getValue(WATERLOGGED))
            super.setCustomAnimations(animatable, instanceId, animationState)
    }

    companion object {
        val VARIANT_MODELS = mapOf(
            Variant.BOTTLE to CommonClass.locate("geo/entity/block/message_in_a_bottle/message_in_a_bottle.geo.json"),
            Variant.JAR to CommonClass.locate("geo/entity/block/message_in_a_bottle/message_in_a_jar.geo.json"),
            Variant.LONGNECK to CommonClass.locate("geo/entity/block/message_in_a_bottle/message_in_a_longneck_bottle.geo.json"),
            Variant.POTION to CommonClass.locate("geo/entity/block/message_in_a_bottle/message_in_a_potion_bottle.geo.json"),
            Variant.WINE to CommonClass.locate("geo/entity/block/message_in_a_bottle/message_in_a_wine_bottle.geo.json"),
        )

        val VARIANT_TEXTURES = mapOf(
            Variant.BOTTLE to CommonClass.locate("textures/entity/block/message_in_a_bottle/message_in_a_bottle.png"),
            Variant.JAR to CommonClass.locate("textures/entity/block/message_in_a_bottle/message_in_a_jar.png"),
            Variant.LONGNECK to CommonClass.locate("textures/entity/block/message_in_a_bottle/message_in_a_longneck_bottle.png"),
            Variant.POTION to CommonClass.locate("textures/entity/block/message_in_a_bottle/message_in_a_potion_bottle.png"),
            Variant.WINE to CommonClass.locate("textures/entity/block/message_in_a_bottle/message_in_a_wine_bottle.png"),
        )

        val WATER_BOB_ANIMATION_ID: ResourceLocation? = CommonClass.locate("animations/water_bob.animation.json")
    }
}