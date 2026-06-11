package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.RatfishEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation
import kotlin.random.Random

class RatfishEntityModel : HAFishEntityModel<RatfishEntity>("ratfish") {
    override fun getRenderType(animatable: RatfishEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }


    override fun getTextureResource(animatable: RatfishEntity): ResourceLocation {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)
        return commonTextures[random.nextInt(commonTextures.size)]
    }

    companion object {
        private val commonTextures = listOf(
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/ratfish/ratfish_brown.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/ratfish/ratfish_silver.png"),
        )
    }
}
