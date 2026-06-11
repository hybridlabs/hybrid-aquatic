package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.CorydoraEntity
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation
import kotlin.random.Random

class CorydoraEntityModel : HAFishEntityModel<CorydoraEntity>("corydora") {
    override fun getRenderType(animatable: CorydoraEntity, texture: ResourceLocation): RenderType {
        return RenderType.entityTranslucent(texture)
    }

    override fun getTextureResource(animatable: CorydoraEntity): ResourceLocation {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)
        return commonTextures[random.nextInt(commonTextures.size)]
    }

    companion object {
        private val commonTextures = listOf(
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/corydora/corydora_albino.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/corydora/corydora_panda.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/corydora/corydora_bronze.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/fish/corydora/corydora_nattereri.png"),
        )
    }
}
