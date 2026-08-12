package dev.hybridlabs.aquatic.client.model.entity.cephalopod

import dev.hybridlabs.aquatic.entity.cephalopod.UmbrellaOctopusEntity
import dev.hybridlabs.hapi.client.model.entity.BaseOctopusEntityModel
import net.minecraft.resources.ResourceLocation
import kotlin.random.Random

class UmbrellaOctopusEntityModel : BaseOctopusEntityModel<UmbrellaOctopusEntity>("hybrid_aquatic","umbrella_octopus") {

    companion object {
        private val commonTextures = listOf(
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/cephalopod/umbrella_octopus/umbrella_octopus_yellow.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/cephalopod/umbrella_octopus/umbrella_octopus_brown.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/cephalopod/umbrella_octopus/umbrella_octopus_orange.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/cephalopod/umbrella_octopus/umbrella_octopus_pink.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/cephalopod/umbrella_octopus/umbrella_octopus_purple.png"),
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/cephalopod/umbrella_octopus/umbrella_octopus_white.png"),
        )
    }

    override fun getTextureResource(animatable: UmbrellaOctopusEntity): ResourceLocation {
        val seed = animatable.uuid.leastSignificantBits
        val random = Random(seed)
        return commonTextures[random.nextInt(commonTextures.size)]
    }
}
