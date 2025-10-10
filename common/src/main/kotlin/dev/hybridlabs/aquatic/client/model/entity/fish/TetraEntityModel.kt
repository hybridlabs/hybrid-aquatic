package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.TetraEntity
import net.minecraft.resources.ResourceLocation

class TetraEntityModel : HybridAquaticFishEntityModel<TetraEntity>("tetra") {

    private val NEON_TEXTURE = ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "textures/entity/fish/tetra/neon_tetra.png")
    private val CAVE_TEXTURE = ResourceLocation.fromNamespaceAndPath("hybrid-aquatic", "textures/entity/fish/tetra/cave_tetra.png")

    override fun getTextureResource(animatable: TetraEntity): ResourceLocation {
        return when (animatable.variant) {
            TetraEntity.Companion.Type.NEON -> NEON_TEXTURE
            TetraEntity.Companion.Type.CAVE -> CAVE_TEXTURE
        }
    }
}
