package dev.hybridlabs.aquatic.client.model.entity.shark

import dev.hybridlabs.aquatic.entity.shark.GoblinSharkEntity
import net.minecraft.resources.ResourceLocation

class GoblinSharkEntityModel : HASharkEntityModel<GoblinSharkEntity>("goblin_shark") {

    override fun getTextureResource(animatable: GoblinSharkEntity): ResourceLocation =
        if (animatable.moistness < 590) DEPRESSURIZED_TEXTURE else GOBLIN_SHARK_TEXTURE

    override fun getModelResource(animatable: GoblinSharkEntity): ResourceLocation =
        if (animatable.moistness < 590) DEPRESSURIZED_MODEL else GOBLIN_SHARK_MODEL

    companion object {
        private val GOBLIN_SHARK_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/shark/goblin_shark/goblin_shark.png")
        private val DEPRESSURIZED_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "textures/entity/shark/goblin_shark/goblin_shark_depressurized.png")

        private val GOBLIN_SHARK_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/shark/goblin_shark/goblin_shark.geo.json")
        private val DEPRESSURIZED_MODEL =
            ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "geo/shark/goblin_shark/goblin_shark_depressurized.geo.json")
    }
}